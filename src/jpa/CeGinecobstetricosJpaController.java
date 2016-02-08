/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeGinecobstetricos;
import entidades_EJB.CeHistoriac;
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
public class CeGinecobstetricosJpaController implements Serializable {

    public CeGinecobstetricosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeGinecobstetricos ceGinecobstetricos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceGinecobstetricos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeGinecobstetricos ceGinecobstetricos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceGinecobstetricos = em.merge(ceGinecobstetricos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceGinecobstetricos.getId();
                if (findCeGinecobstetricos(id) == null) {
                    throw new NonexistentEntityException("The ceGinecobstetricos with id " + id + " no longer exists.");
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
            CeGinecobstetricos ceGinecobstetricos;
            try {
                ceGinecobstetricos = em.getReference(CeGinecobstetricos.class, id);
                ceGinecobstetricos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceGinecobstetricos with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceGinecobstetricos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeGinecobstetricos> findCeGinecobstetricosEntities() {
        return findCeGinecobstetricosEntities(true, -1, -1);
    }

    public List<CeGinecobstetricos> findCeGinecobstetricosEntities(int maxResults, int firstResult) {
        return findCeGinecobstetricosEntities(false, maxResults, firstResult);
    }

    private List<CeGinecobstetricos> findCeGinecobstetricosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeGinecobstetricos.class));
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

    public CeGinecobstetricos findCeGinecobstetricos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeGinecobstetricos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeGinecobstetricosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeGinecobstetricos> rt = cq.from(CeGinecobstetricos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public CeGinecobstetricos getEntidadGinecobstetricosAtencion(CeHistoriac hce) {
        CeGinecobstetricos g = null;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT g FROM CeGinecobstetricos g WHERE g.idhistoriace=:ceh AND g.estado='1'");
        Q.setParameter("ceh", hce);
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            g = (CeGinecobstetricos) results.get(0);
        } else {
            g = null;
        }
        return g;
    }
}
