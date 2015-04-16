/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospFactsLiquidosH;
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
public class HospFactsLiquidosHJpaController implements Serializable {

    public HospFactsLiquidosHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospFactsLiquidosH hospFactsLiquidosH) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospFactsLiquidosH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospFactsLiquidosH hospFactsLiquidosH) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospFactsLiquidosH = em.merge(hospFactsLiquidosH);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospFactsLiquidosH.getId();
                if (findHospFactsLiquidosH(id) == null) {
                    throw new NonexistentEntityException("The hospFactsLiquidosH with id " + id + " no longer exists.");
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
            HospFactsLiquidosH hospFactsLiquidosH;
            try {
                hospFactsLiquidosH = em.getReference(HospFactsLiquidosH.class, id);
                hospFactsLiquidosH.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospFactsLiquidosH with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospFactsLiquidosH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospFactsLiquidosH> findHospFactsLiquidosHEntities() {
        return findHospFactsLiquidosHEntities(true, -1, -1);
    }

    public List<HospFactsLiquidosH> findHospFactsLiquidosHEntities(int maxResults, int firstResult) {
        return findHospFactsLiquidosHEntities(false, maxResults, firstResult);
    }

    private List<HospFactsLiquidosH> findHospFactsLiquidosHEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospFactsLiquidosH.class));
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

    public HospFactsLiquidosH findHospFactsLiquidosH(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospFactsLiquidosH.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospFactsLiquidosHCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospFactsLiquidosH> rt = cq.from(HospFactsLiquidosH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
