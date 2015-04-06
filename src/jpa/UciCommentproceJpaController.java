/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciCommentproce;
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
public class UciCommentproceJpaController implements Serializable {

    public UciCommentproceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciCommentproce uciCommentproce) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciCommentproce);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciCommentproce uciCommentproce) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciCommentproce = em.merge(uciCommentproce);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciCommentproce.getId();
                if (findUciCommentproce(id) == null) {
                    throw new NonexistentEntityException("The uciCommentproce with id " + id + " no longer exists.");
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
            UciCommentproce uciCommentproce;
            try {
                uciCommentproce = em.getReference(UciCommentproce.class, id);
                uciCommentproce.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciCommentproce with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciCommentproce);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciCommentproce> findUciCommentproceEntities() {
        return findUciCommentproceEntities(true, -1, -1);
    }

    public List<UciCommentproce> findUciCommentproceEntities(int maxResults, int firstResult) {
        return findUciCommentproceEntities(false, maxResults, firstResult);
    }

    private List<UciCommentproce> findUciCommentproceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciCommentproce.class));
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

    public UciCommentproce findUciCommentproce(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciCommentproce.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciCommentproceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciCommentproce> rt = cq.from(UciCommentproce.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
