/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.HcuHistoriac2;
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
 * @author Alvaro Monsalve
 */
public class HcuHistoriac2JpaController implements Serializable {

    public HcuHistoriac2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuHistoriac2 hcuHistoriac2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hcuHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuHistoriac2 hcuHistoriac2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hcuHistoriac2 = em.merge(hcuHistoriac2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuHistoriac2.getId();
                if (findHcuHistoriac2(id) == null) {
                    throw new NonexistentEntityException("The hcuHistoriac2 with id " + id + " no longer exists.");
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
            HcuHistoriac2 hcuHistoriac2;
            try {
                hcuHistoriac2 = em.getReference(HcuHistoriac2.class, id);
                hcuHistoriac2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuHistoriac2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(hcuHistoriac2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuHistoriac2> findHcuHistoriac2Entities() {
        return findHcuHistoriac2Entities(true, -1, -1);
    }

    public List<HcuHistoriac2> findHcuHistoriac2Entities(int maxResults, int firstResult) {
        return findHcuHistoriac2Entities(false, maxResults, firstResult);
    }

    private List<HcuHistoriac2> findHcuHistoriac2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuHistoriac2.class));
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

    public HcuHistoriac2 findHcuHistoriac2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuHistoriac2.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuHistoriac2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuHistoriac2> rt = cq.from(HcuHistoriac2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
