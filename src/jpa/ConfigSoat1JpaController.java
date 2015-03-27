/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.ConfigCups;
import entidades_EJB.ConfigSoat1;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class ConfigSoat1JpaController implements Serializable {

    public ConfigSoat1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfigSoat1 configSoat1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCups codigoCups = configSoat1.getCodigoCups();
            if (codigoCups != null) {
                codigoCups = em.getReference(codigoCups.getClass(), codigoCups.getId());
                configSoat1.setCodigoCups(codigoCups);
            }
            em.persist(configSoat1);
            if (codigoCups != null) {
                codigoCups.getConfigSoat1List().add(configSoat1);
                codigoCups = em.merge(codigoCups);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfigSoat1 configSoat1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigSoat1 persistentConfigSoat1 = em.find(ConfigSoat1.class, configSoat1.getId());
            ConfigCups codigoCupsOld = persistentConfigSoat1.getCodigoCups();
            ConfigCups codigoCupsNew = configSoat1.getCodigoCups();
            if (codigoCupsNew != null) {
                codigoCupsNew = em.getReference(codigoCupsNew.getClass(), codigoCupsNew.getId());
                configSoat1.setCodigoCups(codigoCupsNew);
            }
            configSoat1 = em.merge(configSoat1);
            if (codigoCupsOld != null && !codigoCupsOld.equals(codigoCupsNew)) {
                codigoCupsOld.getConfigSoat1List().remove(configSoat1);
                codigoCupsOld = em.merge(codigoCupsOld);
            }
            if (codigoCupsNew != null && !codigoCupsNew.equals(codigoCupsOld)) {
                codigoCupsNew.getConfigSoat1List().add(configSoat1);
                codigoCupsNew = em.merge(codigoCupsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configSoat1.getId();
                if (findConfigSoat1(id) == null) {
                    throw new NonexistentEntityException("The configSoat1 with id " + id + " no longer exists.");
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
            ConfigSoat1 configSoat1;
            try {
                configSoat1 = em.getReference(ConfigSoat1.class, id);
                configSoat1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configSoat1 with id " + id + " no longer exists.", enfe);
            }
            ConfigCups codigoCups = configSoat1.getCodigoCups();
            if (codigoCups != null) {
                codigoCups.getConfigSoat1List().remove(configSoat1);
                codigoCups = em.merge(codigoCups);
            }
            em.remove(configSoat1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfigSoat1> findConfigSoat1Entities() {
        return findConfigSoat1Entities(true, -1, -1);
    }

    public List<ConfigSoat1> findConfigSoat1Entities(int maxResults, int firstResult) {
        return findConfigSoat1Entities(false, maxResults, firstResult);
    }

    private List<ConfigSoat1> findConfigSoat1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfigSoat1.class));
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

    public ConfigSoat1 findConfigSoat1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfigSoat1.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigSoat1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfigSoat1> rt = cq.from(ConfigSoat1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
