/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.RxListadoProced;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class RxListadoProcedJpaController implements Serializable {

    public RxListadoProcedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RxListadoProced rxListadoProced) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rxListadoProced);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RxListadoProced rxListadoProced) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rxListadoProced = em.merge(rxListadoProced);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rxListadoProced.getId();
                if (findRxListadoProced(id) == null) {
                    throw new NonexistentEntityException("The rxListadoProced with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RxListadoProced rxListadoProced;
            try {
                rxListadoProced = em.getReference(RxListadoProced.class, id);
                rxListadoProced.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rxListadoProced with id " + id + " no longer exists.", enfe);
            }
            em.remove(rxListadoProced);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RxListadoProced> findRxListadoProcedEntities() {
        return findRxListadoProcedEntities(true, -1, -1);
    }

    public List<RxListadoProced> findRxListadoProcedEntities(int maxResults, int firstResult) {
        return findRxListadoProcedEntities(false, maxResults, firstResult);
    }

    private List<RxListadoProced> findRxListadoProcedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RxListadoProced.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RxListadoProced findRxListadoProced(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RxListadoProced.class, id);
        } finally {
            em.close();
        }
    }

    public int getRxListadoProcedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RxListadoProced> rt = cq.from(RxListadoProced.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
