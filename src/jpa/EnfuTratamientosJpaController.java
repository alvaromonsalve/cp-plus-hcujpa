/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuTratamientos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuTratamientosJpaController implements Serializable {

    public EnfuTratamientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuTratamientos enfuTratamientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuTratamientos enfuTratamientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuTratamientos = em.merge(enfuTratamientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuTratamientos.getId();
                if (findEnfuTratamientos(id) == null) {
                    throw new NonexistentEntityException("The enfuTratamientos with id " + id + " no longer exists.");
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
            EnfuTratamientos enfuTratamientos;
            try {
                enfuTratamientos = em.getReference(EnfuTratamientos.class, id);
                enfuTratamientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuTratamientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuTratamientos> findEnfuTratamientosEntities() {
        return findEnfuTratamientosEntities(true, -1, -1);
    }

    public List<EnfuTratamientos> findEnfuTratamientosEntities(int maxResults, int firstResult) {
        return findEnfuTratamientosEntities(false, maxResults, firstResult);
    }

    private List<EnfuTratamientos> findEnfuTratamientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuTratamientos.class));
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

    public EnfuTratamientos findEnfuTratamientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuTratamientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuTratamientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuTratamientos> rt = cq.from(EnfuTratamientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuTratamientos> getTratamientos(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT f FROM EnfuTratamientos f WHERE f.idHistoria.id=:historia AND f.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

}
