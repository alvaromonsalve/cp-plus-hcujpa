/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuLiquidos;
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
public class EnfuLiquidosJpaController implements Serializable {

    public EnfuLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuLiquidos enfuLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuLiquidos enfuLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuLiquidos = em.merge(enfuLiquidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuLiquidos.getId();
                if (findEnfuLiquidos(id) == null) {
                    throw new NonexistentEntityException("The enfuLiquidos with id " + id + " no longer exists.");
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
            EnfuLiquidos enfuLiquidos;
            try {
                enfuLiquidos = em.getReference(EnfuLiquidos.class, id);
                enfuLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuLiquidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuLiquidos> findEnfuLiquidosEntities() {
        return findEnfuLiquidosEntities(true, -1, -1);
    }

    public List<EnfuLiquidos> findEnfuLiquidosEntities(int maxResults, int firstResult) {
        return findEnfuLiquidosEntities(false, maxResults, firstResult);
    }

    private List<EnfuLiquidos> findEnfuLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuLiquidos.class));
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

    public EnfuLiquidos findEnfuLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuLiquidos> rt = cq.from(EnfuLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuLiquidos> getLiquidos(int control) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT l FROM EnfuLiquidos l WHERE l.idControlO.id=:idc AND l.estado='1'");
        Q.setParameter("idc", control);
        return Q.getResultList();
    }

}
