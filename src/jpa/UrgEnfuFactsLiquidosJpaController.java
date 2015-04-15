/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UrgEnfuFactsLiquidos;
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
public class UrgEnfuFactsLiquidosJpaController implements Serializable {

    public UrgEnfuFactsLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgEnfuFactsLiquidos urgEnfuFactsLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(urgEnfuFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgEnfuFactsLiquidos urgEnfuFactsLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            urgEnfuFactsLiquidos = em.merge(urgEnfuFactsLiquidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgEnfuFactsLiquidos.getId();
                if (findUrgEnfuFactsLiquidos(id) == null) {
                    throw new NonexistentEntityException("The urgEnfuFactsLiquidos with id " + id + " no longer exists.");
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
            UrgEnfuFactsLiquidos urgEnfuFactsLiquidos;
            try {
                urgEnfuFactsLiquidos = em.getReference(UrgEnfuFactsLiquidos.class, id);
                urgEnfuFactsLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgEnfuFactsLiquidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(urgEnfuFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgEnfuFactsLiquidos> findUrgEnfuFactsLiquidosEntities() {
        return findUrgEnfuFactsLiquidosEntities(true, -1, -1);
    }

    public List<UrgEnfuFactsLiquidos> findUrgEnfuFactsLiquidosEntities(int maxResults, int firstResult) {
        return findUrgEnfuFactsLiquidosEntities(false, maxResults, firstResult);
    }

    private List<UrgEnfuFactsLiquidos> findUrgEnfuFactsLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgEnfuFactsLiquidos.class));
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

    public UrgEnfuFactsLiquidos findUrgEnfuFactsLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgEnfuFactsLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgEnfuFactsLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgEnfuFactsLiquidos> rt = cq.from(UrgEnfuFactsLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
