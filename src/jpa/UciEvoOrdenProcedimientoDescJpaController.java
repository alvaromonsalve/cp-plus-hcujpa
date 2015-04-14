/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciEvoOrdenProcedimientoDesc;
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
public class UciEvoOrdenProcedimientoDescJpaController implements Serializable {

    public UciEvoOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoOrdenProcedimientoDesc uciEvoOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoOrdenProcedimientoDesc uciEvoOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciEvoOrdenProcedimientoDesc = em.merge(uciEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoOrdenProcedimientoDesc.getId();
                if (findUciEvoOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The uciEvoOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            UciEvoOrdenProcedimientoDesc uciEvoOrdenProcedimientoDesc;
            try {
                uciEvoOrdenProcedimientoDesc = em.getReference(UciEvoOrdenProcedimientoDesc.class, id);
                uciEvoOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvoOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoOrdenProcedimientoDesc> findUciEvoOrdenProcedimientoDescEntities() {
        return findUciEvoOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<UciEvoOrdenProcedimientoDesc> findUciEvoOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findUciEvoOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<UciEvoOrdenProcedimientoDesc> findUciEvoOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoOrdenProcedimientoDesc.class));
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

    public UciEvoOrdenProcedimientoDesc findUciEvoOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoOrdenProcedimientoDesc> rt = cq.from(UciEvoOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
