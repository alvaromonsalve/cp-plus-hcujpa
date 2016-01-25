/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceEnfLogs;
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
public class UceEnfLogsJpaController implements Serializable {

    public UceEnfLogsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEnfLogs uceEnfLogs) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceEnfLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEnfLogs uceEnfLogs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceEnfLogs = em.merge(uceEnfLogs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEnfLogs.getId();
                if (findUceEnfLogs(id) == null) {
                    throw new NonexistentEntityException("The uceEnfLogs with id " + id + " no longer exists.");
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
            UceEnfLogs uceEnfLogs;
            try {
                uceEnfLogs = em.getReference(UceEnfLogs.class, id);
                uceEnfLogs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEnfLogs with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceEnfLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEnfLogs> findUceEnfLogsEntities() {
        return findUceEnfLogsEntities(true, -1, -1);
    }

    public List<UceEnfLogs> findUceEnfLogsEntities(int maxResults, int firstResult) {
        return findUceEnfLogsEntities(false, maxResults, firstResult);
    }

    private List<UceEnfLogs> findUceEnfLogsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEnfLogs.class));
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

    public UceEnfLogs findUceEnfLogs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEnfLogs.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEnfLogsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEnfLogs> rt = cq.from(UceEnfLogs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
