/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.LabListadoProced;
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
public class LabListadoProcedJpaController implements Serializable {

    public LabListadoProcedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LabListadoProced labListadoProced) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(labListadoProced);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LabListadoProced labListadoProced) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            labListadoProced = em.merge(labListadoProced);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = labListadoProced.getId();
                if (findLabListadoProced(id) == null) {
                    throw new NonexistentEntityException("The labListadoProced with id " + id + " no longer exists.");
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
            LabListadoProced labListadoProced;
            try {
                labListadoProced = em.getReference(LabListadoProced.class, id);
                labListadoProced.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The labListadoProced with id " + id + " no longer exists.", enfe);
            }
            em.remove(labListadoProced);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LabListadoProced> findLabListadoProcedEntities() {
        return findLabListadoProcedEntities(true, -1, -1);
    }

    public List<LabListadoProced> findLabListadoProcedEntities(int maxResults, int firstResult) {
        return findLabListadoProcedEntities(false, maxResults, firstResult);
    }

    private List<LabListadoProced> findLabListadoProcedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LabListadoProced.class));
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

    public LabListadoProced findLabListadoProced(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LabListadoProced.class, id);
        } finally {
            em.close();
        }
    }

    public int getLabListadoProcedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LabListadoProced> rt = cq.from(LabListadoProced.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
