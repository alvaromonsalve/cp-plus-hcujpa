/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospCommentproce;
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
public class HospCommentproceJpaController implements Serializable {

    public HospCommentproceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospCommentproce hospCommentproce) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospCommentproce);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospCommentproce hospCommentproce) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospCommentproce = em.merge(hospCommentproce);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospCommentproce.getId();
                if (findHospCommentproce(id) == null) {
                    throw new NonexistentEntityException("The hospCommentproce with id " + id + " no longer exists.");
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
            HospCommentproce hospCommentproce;
            try {
                hospCommentproce = em.getReference(HospCommentproce.class, id);
                hospCommentproce.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospCommentproce with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospCommentproce);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospCommentproce> findHospCommentproceEntities() {
        return findHospCommentproceEntities(true, -1, -1);
    }

    public List<HospCommentproce> findHospCommentproceEntities(int maxResults, int firstResult) {
        return findHospCommentproceEntities(false, maxResults, firstResult);
    }

    private List<HospCommentproce> findHospCommentproceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospCommentproce.class));
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

    public HospCommentproce findHospCommentproce(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospCommentproce.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospCommentproceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospCommentproce> rt = cq.from(HospCommentproce.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
