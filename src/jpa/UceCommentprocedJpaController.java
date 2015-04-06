/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceCommentproced;
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
public class UceCommentprocedJpaController implements Serializable {

    public UceCommentprocedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceCommentproced uceCommentproced) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceCommentproced);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceCommentproced uceCommentproced) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceCommentproced = em.merge(uceCommentproced);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceCommentproced.getId();
                if (findUceCommentproced(id) == null) {
                    throw new NonexistentEntityException("The uceCommentproced with id " + id + " no longer exists.");
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
            UceCommentproced uceCommentproced;
            try {
                uceCommentproced = em.getReference(UceCommentproced.class, id);
                uceCommentproced.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceCommentproced with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceCommentproced);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceCommentproced> findUceCommentprocedEntities() {
        return findUceCommentprocedEntities(true, -1, -1);
    }

    public List<UceCommentproced> findUceCommentprocedEntities(int maxResults, int firstResult) {
        return findUceCommentprocedEntities(false, maxResults, firstResult);
    }

    private List<UceCommentproced> findUceCommentprocedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceCommentproced.class));
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

    public UceCommentproced findUceCommentproced(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceCommentproced.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceCommentprocedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceCommentproced> rt = cq.from(UceCommentproced.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
