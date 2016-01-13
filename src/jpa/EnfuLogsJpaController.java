/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuLogs;
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
public class EnfuLogsJpaController implements Serializable {

    public EnfuLogsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuLogs enfuLogs) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuLogs enfuLogs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuLogs = em.merge(enfuLogs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuLogs.getId();
                if (findEnfuLogs(id) == null) {
                    throw new NonexistentEntityException("The enfuLogs with id " + id + " no longer exists.");
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
            EnfuLogs enfuLogs;
            try {
                enfuLogs = em.getReference(EnfuLogs.class, id);
                enfuLogs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuLogs with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuLogs> findEnfuLogsEntities() {
        return findEnfuLogsEntities(true, -1, -1);
    }

    public List<EnfuLogs> findEnfuLogsEntities(int maxResults, int firstResult) {
        return findEnfuLogsEntities(false, maxResults, firstResult);
    }

    private List<EnfuLogs> findEnfuLogsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuLogs.class));
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

    public EnfuLogs findEnfuLogs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuLogs.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuLogsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuLogs> rt = cq.from(EnfuLogs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
