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
import entidades_EJB.ConfigCamas;
import entidades_EJB.ConfigServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class ConfigServicioJpaController implements Serializable {

    public ConfigServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfigServicio configServicio) {
        if (configServicio.getConfigCamasList() == null) {
            configServicio.setConfigCamasList(new ArrayList<ConfigCamas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ConfigCamas> attachedConfigCamasList = new ArrayList<ConfigCamas>();
            for (ConfigCamas configCamasListConfigCamasToAttach : configServicio.getConfigCamasList()) {
                configCamasListConfigCamasToAttach = em.getReference(configCamasListConfigCamasToAttach.getClass(), configCamasListConfigCamasToAttach.getId());
                attachedConfigCamasList.add(configCamasListConfigCamasToAttach);
            }
            configServicio.setConfigCamasList(attachedConfigCamasList);
            em.persist(configServicio);
            for (ConfigCamas configCamasListConfigCamas : configServicio.getConfigCamasList()) {
                ConfigServicio oldServicioOfConfigCamasListConfigCamas = configCamasListConfigCamas.getServicio();
                configCamasListConfigCamas.setServicio(configServicio);
                configCamasListConfigCamas = em.merge(configCamasListConfigCamas);
                if (oldServicioOfConfigCamasListConfigCamas != null) {
                    oldServicioOfConfigCamasListConfigCamas.getConfigCamasList().remove(configCamasListConfigCamas);
                    oldServicioOfConfigCamasListConfigCamas = em.merge(oldServicioOfConfigCamasListConfigCamas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfigServicio configServicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigServicio persistentConfigServicio = em.find(ConfigServicio.class, configServicio.getId());
            List<ConfigCamas> configCamasListOld = persistentConfigServicio.getConfigCamasList();
            List<ConfigCamas> configCamasListNew = configServicio.getConfigCamasList();
            List<String> illegalOrphanMessages = null;
            for (ConfigCamas configCamasListOldConfigCamas : configCamasListOld) {
                if (!configCamasListNew.contains(configCamasListOldConfigCamas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConfigCamas " + configCamasListOldConfigCamas + " since its servicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ConfigCamas> attachedConfigCamasListNew = new ArrayList<ConfigCamas>();
            for (ConfigCamas configCamasListNewConfigCamasToAttach : configCamasListNew) {
                configCamasListNewConfigCamasToAttach = em.getReference(configCamasListNewConfigCamasToAttach.getClass(), configCamasListNewConfigCamasToAttach.getId());
                attachedConfigCamasListNew.add(configCamasListNewConfigCamasToAttach);
            }
            configCamasListNew = attachedConfigCamasListNew;
            configServicio.setConfigCamasList(configCamasListNew);
            configServicio = em.merge(configServicio);
            for (ConfigCamas configCamasListNewConfigCamas : configCamasListNew) {
                if (!configCamasListOld.contains(configCamasListNewConfigCamas)) {
                    ConfigServicio oldServicioOfConfigCamasListNewConfigCamas = configCamasListNewConfigCamas.getServicio();
                    configCamasListNewConfigCamas.setServicio(configServicio);
                    configCamasListNewConfigCamas = em.merge(configCamasListNewConfigCamas);
                    if (oldServicioOfConfigCamasListNewConfigCamas != null && !oldServicioOfConfigCamasListNewConfigCamas.equals(configServicio)) {
                        oldServicioOfConfigCamasListNewConfigCamas.getConfigCamasList().remove(configCamasListNewConfigCamas);
                        oldServicioOfConfigCamasListNewConfigCamas = em.merge(oldServicioOfConfigCamasListNewConfigCamas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configServicio.getId();
                if (findConfigServicio(id) == null) {
                    throw new NonexistentEntityException("The configServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigServicio configServicio;
            try {
                configServicio = em.getReference(ConfigServicio.class, id);
                configServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configServicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ConfigCamas> configCamasListOrphanCheck = configServicio.getConfigCamasList();
            for (ConfigCamas configCamasListOrphanCheckConfigCamas : configCamasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConfigServicio (" + configServicio + ") cannot be destroyed since the ConfigCamas " + configCamasListOrphanCheckConfigCamas + " in its configCamasList field has a non-nullable servicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(configServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfigServicio> findConfigServicioEntities() {
        return findConfigServicioEntities(true, -1, -1);
    }

    public List<ConfigServicio> findConfigServicioEntities(int maxResults, int firstResult) {
        return findConfigServicioEntities(false, maxResults, firstResult);
    }

    private List<ConfigServicio> findConfigServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfigServicio.class));
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

    public ConfigServicio findConfigServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfigServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfigServicio> rt = cq.from(ConfigServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ConfigServicio> getServicios() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT s FROM ConfigServicio s WHERE s.estado='0'");
        return q.getResultList();
    }
}
