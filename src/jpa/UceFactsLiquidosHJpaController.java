/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFactsLiquidosH;
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
public class UceFactsLiquidosHJpaController implements Serializable {

    public UceFactsLiquidosHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceFactsLiquidosH uceFactsLiquidosH) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceFactsLiquidosH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceFactsLiquidosH uceFactsLiquidosH) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceFactsLiquidosH = em.merge(uceFactsLiquidosH);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceFactsLiquidosH.getId();
                if (findUceFactsLiquidosH(id) == null) {
                    throw new NonexistentEntityException("The uceFactsLiquidosH with id " + id + " no longer exists.");
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
            UceFactsLiquidosH uceFactsLiquidosH;
            try {
                uceFactsLiquidosH = em.getReference(UceFactsLiquidosH.class, id);
                uceFactsLiquidosH.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceFactsLiquidosH with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceFactsLiquidosH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceFactsLiquidosH> findUceFactsLiquidosHEntities() {
        return findUceFactsLiquidosHEntities(true, -1, -1);
    }

    public List<UceFactsLiquidosH> findUceFactsLiquidosHEntities(int maxResults, int firstResult) {
        return findUceFactsLiquidosHEntities(false, maxResults, firstResult);
    }

    private List<UceFactsLiquidosH> findUceFactsLiquidosHEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceFactsLiquidosH.class));
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

    public UceFactsLiquidosH findUceFactsLiquidosH(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceFactsLiquidosH.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceFactsLiquidosHCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceFactsLiquidosH> rt = cq.from(UceFactsLiquidosH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
