/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciEnfLogs;
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
public class UciEnfLogsJpaController implements Serializable {

    public UciEnfLogsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEnfLogs uciEnfLogs) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciEnfLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEnfLogs uciEnfLogs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciEnfLogs = em.merge(uciEnfLogs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEnfLogs.getId();
                if (findUciEnfLogs(id) == null) {
                    throw new NonexistentEntityException("The uciEnfLogs with id " + id + " no longer exists.");
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
            UciEnfLogs uciEnfLogs;
            try {
                uciEnfLogs = em.getReference(UciEnfLogs.class, id);
                uciEnfLogs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEnfLogs with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciEnfLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEnfLogs> findUciEnfLogsEntities() {
        return findUciEnfLogsEntities(true, -1, -1);
    }

    public List<UciEnfLogs> findUciEnfLogsEntities(int maxResults, int firstResult) {
        return findUciEnfLogsEntities(false, maxResults, firstResult);
    }

    private List<UciEnfLogs> findUciEnfLogsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEnfLogs.class));
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

    public UciEnfLogs findUciEnfLogs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEnfLogs.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEnfLogsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEnfLogs> rt = cq.from(UciEnfLogs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
