/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.UciAplicacionesTransfusion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IdlhDeveloper
 */
public class UciAplicacionesTransfusionJpaController implements Serializable {

    public UciAplicacionesTransfusionJpaController(EntityManagerFactory emf) {
       this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAplicacionesTransfusion uciAplicacionesTransfusion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciAplicacionesTransfusion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAplicacionesTransfusion uciAplicacionesTransfusion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciAplicacionesTransfusion = em.merge(uciAplicacionesTransfusion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAplicacionesTransfusion.getId();
                if (findUciAplicacionesTransfusion(id) == null) {
                    throw new NonexistentEntityException("The uciAplicacionesTransfusion with id " + id + " no longer exists.");
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
            UciAplicacionesTransfusion uciAplicacionesTransfusion;
            try {
                uciAplicacionesTransfusion = em.getReference(UciAplicacionesTransfusion.class, id);
                uciAplicacionesTransfusion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAplicacionesTransfusion with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciAplicacionesTransfusion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAplicacionesTransfusion> findUciAplicacionesTransfusionEntities() {
        return findUciAplicacionesTransfusionEntities(true, -1, -1);
    }

    public List<UciAplicacionesTransfusion> findUciAplicacionesTransfusionEntities(int maxResults, int firstResult) {
        return findUciAplicacionesTransfusionEntities(false, maxResults, firstResult);
    }

    private List<UciAplicacionesTransfusion> findUciAplicacionesTransfusionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAplicacionesTransfusion.class));
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

    public UciAplicacionesTransfusion findUciAplicacionesTransfusion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAplicacionesTransfusion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAplicacionesTransfusionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAplicacionesTransfusion> rt = cq.from(UciAplicacionesTransfusion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
