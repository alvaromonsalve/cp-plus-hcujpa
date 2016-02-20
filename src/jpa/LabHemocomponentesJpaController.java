/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.LabHemocomponentes;
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
 * @author IdlhDeveloper
 */
public class LabHemocomponentesJpaController implements Serializable {

    public LabHemocomponentesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LabHemocomponentes labHemocomponentes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(labHemocomponentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LabHemocomponentes labHemocomponentes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            labHemocomponentes = em.merge(labHemocomponentes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = labHemocomponentes.getId();
                if (findLabHemocomponentes(id) == null) {
                    throw new NonexistentEntityException("The labHemocomponentes with id " + id + " no longer exists.");
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
            LabHemocomponentes labHemocomponentes;
            try {
                labHemocomponentes = em.getReference(LabHemocomponentes.class, id);
                labHemocomponentes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The labHemocomponentes with id " + id + " no longer exists.", enfe);
            }
            em.remove(labHemocomponentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LabHemocomponentes> findLabHemocomponentesEntities() {
        return findLabHemocomponentesEntities(true, -1, -1);
    }

    public List<LabHemocomponentes> findLabHemocomponentesEntities(int maxResults, int firstResult) {
        return findLabHemocomponentesEntities(false, maxResults, firstResult);
    }

    private List<LabHemocomponentes> findLabHemocomponentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LabHemocomponentes.class));
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

    public LabHemocomponentes findLabHemocomponentes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LabHemocomponentes.class, id);
        } finally {
            em.close();
        }
    }

    public int getLabHemocomponentesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LabHemocomponentes> rt = cq.from(LabHemocomponentes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
