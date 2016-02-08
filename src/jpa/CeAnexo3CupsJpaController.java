/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeAnexo3Cups;
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
public class CeAnexo3CupsJpaController implements Serializable {

    public CeAnexo3CupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeAnexo3Cups ceAnexo3Cups) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceAnexo3Cups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeAnexo3Cups ceAnexo3Cups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceAnexo3Cups = em.merge(ceAnexo3Cups);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceAnexo3Cups.getId();
                if (findCeAnexo3Cups(id) == null) {
                    throw new NonexistentEntityException("The ceAnexo3Cups with id " + id + " no longer exists.");
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
            CeAnexo3Cups ceAnexo3Cups;
            try {
                ceAnexo3Cups = em.getReference(CeAnexo3Cups.class, id);
                ceAnexo3Cups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceAnexo3Cups with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceAnexo3Cups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeAnexo3Cups> findCeAnexo3CupsEntities() {
        return findCeAnexo3CupsEntities(true, -1, -1);
    }

    public List<CeAnexo3Cups> findCeAnexo3CupsEntities(int maxResults, int firstResult) {
        return findCeAnexo3CupsEntities(false, maxResults, firstResult);
    }

    private List<CeAnexo3Cups> findCeAnexo3CupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeAnexo3Cups.class));
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

    public CeAnexo3Cups findCeAnexo3Cups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeAnexo3Cups.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeAnexo3CupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeAnexo3Cups> rt = cq.from(CeAnexo3Cups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
