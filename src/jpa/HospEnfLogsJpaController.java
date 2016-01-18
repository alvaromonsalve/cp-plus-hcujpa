/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospEnfLogs;
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
public class HospEnfLogsJpaController implements Serializable {

    public HospEnfLogsJpaController(EntityManagerFactory emf) {
       this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEnfLogs hospEnfLogs) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospEnfLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEnfLogs hospEnfLogs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospEnfLogs = em.merge(hospEnfLogs);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEnfLogs.getId();
                if (findHospEnfLogs(id) == null) {
                    throw new NonexistentEntityException("The hospEnfLogs with id " + id + " no longer exists.");
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
            HospEnfLogs hospEnfLogs;
            try {
                hospEnfLogs = em.getReference(HospEnfLogs.class, id);
                hospEnfLogs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEnfLogs with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospEnfLogs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEnfLogs> findHospEnfLogsEntities() {
        return findHospEnfLogsEntities(true, -1, -1);
    }

    public List<HospEnfLogs> findHospEnfLogsEntities(int maxResults, int firstResult) {
        return findHospEnfLogsEntities(false, maxResults, firstResult);
    }

    private List<HospEnfLogs> findHospEnfLogsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEnfLogs.class));
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

    public HospEnfLogs findHospEnfLogs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEnfLogs.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEnfLogsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEnfLogs> rt = cq.from(HospEnfLogs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
