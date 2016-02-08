/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.ConfigProcedimientos;
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
public class ConfigProcedimientosJpaController implements Serializable {

    public ConfigProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfigProcedimientos configProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(configProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfigProcedimientos configProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            configProcedimientos = em.merge(configProcedimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configProcedimientos.getId();
                if (findConfigProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The configProcedimientos with id " + id + " no longer exists.");
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
            ConfigProcedimientos configProcedimientos;
            try {
                configProcedimientos = em.getReference(ConfigProcedimientos.class, id);
                configProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configProcedimientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(configProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfigProcedimientos> findConfigProcedimientosEntities() {
        return findConfigProcedimientosEntities(true, -1, -1);
    }

    public List<ConfigProcedimientos> findConfigProcedimientosEntities(int maxResults, int firstResult) {
        return findConfigProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<ConfigProcedimientos> findConfigProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfigProcedimientos.class));
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

    public ConfigProcedimientos findConfigProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfigProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfigProcedimientos> rt = cq.from(ConfigProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ConfigProcedimientos> getProcedimientos() {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT c FROM ConfigProcedimientos c WHERE  (c.idCups.estado='1' AND c.estado='1')");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
