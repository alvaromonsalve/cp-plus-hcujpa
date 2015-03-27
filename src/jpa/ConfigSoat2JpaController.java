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
import entidades_EJB.ConfigSoat2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class ConfigSoat2JpaController implements Serializable {

    public ConfigSoat2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfigSoat2 configSoat2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCups codigoCups = configSoat2.getCodigoCups();
            if (codigoCups != null) {
                codigoCups = em.getReference(codigoCups.getClass(), codigoCups.getId());
                configSoat2.setCodigoCups(codigoCups);
            }
            em.persist(configSoat2);
            if (codigoCups != null) {
                codigoCups.getConfigSoat2List().add(configSoat2);
                codigoCups = em.merge(codigoCups);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfigSoat2 configSoat2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigSoat2 persistentConfigSoat2 = em.find(ConfigSoat2.class, configSoat2.getId());
            ConfigCups codigoCupsOld = persistentConfigSoat2.getCodigoCups();
            ConfigCups codigoCupsNew = configSoat2.getCodigoCups();
            if (codigoCupsNew != null) {
                codigoCupsNew = em.getReference(codigoCupsNew.getClass(), codigoCupsNew.getId());
                configSoat2.setCodigoCups(codigoCupsNew);
            }
            configSoat2 = em.merge(configSoat2);
            if (codigoCupsOld != null && !codigoCupsOld.equals(codigoCupsNew)) {
                codigoCupsOld.getConfigSoat2List().remove(configSoat2);
                codigoCupsOld = em.merge(codigoCupsOld);
            }
            if (codigoCupsNew != null && !codigoCupsNew.equals(codigoCupsOld)) {
                codigoCupsNew.getConfigSoat2List().add(configSoat2);
                codigoCupsNew = em.merge(codigoCupsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configSoat2.getId();
                if (findConfigSoat2(id) == null) {
                    throw new NonexistentEntityException("The configSoat2 with id " + id + " no longer exists.");
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
            ConfigSoat2 configSoat2;
            try {
                configSoat2 = em.getReference(ConfigSoat2.class, id);
                configSoat2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configSoat2 with id " + id + " no longer exists.", enfe);
            }
            ConfigCups codigoCups = configSoat2.getCodigoCups();
            if (codigoCups != null) {
                codigoCups.getConfigSoat2List().remove(configSoat2);
                codigoCups = em.merge(codigoCups);
            }
            em.remove(configSoat2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfigSoat2> findConfigSoat2Entities() {
        return findConfigSoat2Entities(true, -1, -1);
    }

    public List<ConfigSoat2> findConfigSoat2Entities(int maxResults, int firstResult) {
        return findConfigSoat2Entities(false, maxResults, firstResult);
    }

    private List<ConfigSoat2> findConfigSoat2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfigSoat2.class));
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

    public ConfigSoat2 findConfigSoat2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfigSoat2.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigSoat2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfigSoat2> rt = cq.from(ConfigSoat2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
