/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFactsLiquidosH;
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
public class UciFactsLiquidosHJpaController implements Serializable {

    public UciFactsLiquidosHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciFactsLiquidosH uciFactsLiquidosH) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciFactsLiquidosH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciFactsLiquidosH uciFactsLiquidosH) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciFactsLiquidosH = em.merge(uciFactsLiquidosH);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciFactsLiquidosH.getId();
                if (findUciFactsLiquidosH(id) == null) {
                    throw new NonexistentEntityException("The uciFactsLiquidosH with id " + id + " no longer exists.");
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
            UciFactsLiquidosH uciFactsLiquidosH;
            try {
                uciFactsLiquidosH = em.getReference(UciFactsLiquidosH.class, id);
                uciFactsLiquidosH.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciFactsLiquidosH with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciFactsLiquidosH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciFactsLiquidosH> findUciFactsLiquidosHEntities() {
        return findUciFactsLiquidosHEntities(true, -1, -1);
    }

    public List<UciFactsLiquidosH> findUciFactsLiquidosHEntities(int maxResults, int firstResult) {
        return findUciFactsLiquidosHEntities(false, maxResults, firstResult);
    }

    private List<UciFactsLiquidosH> findUciFactsLiquidosHEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciFactsLiquidosH.class));
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

    public UciFactsLiquidosH findUciFactsLiquidosH(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciFactsLiquidosH.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciFactsLiquidosHCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciFactsLiquidosH> rt = cq.from(UciFactsLiquidosH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
