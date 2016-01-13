/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.ConfigCamas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.ConfigServicio;
import entidades_EJB.InfoCamas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class ConfigCamasJpaController implements Serializable {

    public ConfigCamasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfigCamas configCamas) {
        if (configCamas.getInfoCamasList() == null) {
            configCamas.setInfoCamasList(new ArrayList<InfoCamas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigServicio servicio = configCamas.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getId());
                configCamas.setServicio(servicio);
            }
            List<InfoCamas> attachedInfoCamasList = new ArrayList<InfoCamas>();
            for (InfoCamas infoCamasListInfoCamasToAttach : configCamas.getInfoCamasList()) {
                infoCamasListInfoCamasToAttach = em.getReference(infoCamasListInfoCamasToAttach.getClass(), infoCamasListInfoCamasToAttach.getId());
                attachedInfoCamasList.add(infoCamasListInfoCamasToAttach);
            }
            configCamas.setInfoCamasList(attachedInfoCamasList);
            em.persist(configCamas);
            if (servicio != null) {
                servicio.getConfigCamasList().add(configCamas);
                servicio = em.merge(servicio);
            }
            for (InfoCamas infoCamasListInfoCamas : configCamas.getInfoCamasList()) {
                ConfigCamas oldIdConfigCamasOfInfoCamasListInfoCamas = infoCamasListInfoCamas.getIdConfigCamas();
                infoCamasListInfoCamas.setIdConfigCamas(configCamas);
                infoCamasListInfoCamas = em.merge(infoCamasListInfoCamas);
                if (oldIdConfigCamasOfInfoCamasListInfoCamas != null) {
                    oldIdConfigCamasOfInfoCamasListInfoCamas.getInfoCamasList().remove(infoCamasListInfoCamas);
                    oldIdConfigCamasOfInfoCamasListInfoCamas = em.merge(oldIdConfigCamasOfInfoCamasListInfoCamas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfigCamas configCamas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCamas persistentConfigCamas = em.find(ConfigCamas.class, configCamas.getId());
            ConfigServicio servicioOld = persistentConfigCamas.getServicio();
            ConfigServicio servicioNew = configCamas.getServicio();
            List<InfoCamas> infoCamasListOld = persistentConfigCamas.getInfoCamasList();
            List<InfoCamas> infoCamasListNew = configCamas.getInfoCamasList();
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getId());
                configCamas.setServicio(servicioNew);
            }
            List<InfoCamas> attachedInfoCamasListNew = new ArrayList<InfoCamas>();
            for (InfoCamas infoCamasListNewInfoCamasToAttach : infoCamasListNew) {
                infoCamasListNewInfoCamasToAttach = em.getReference(infoCamasListNewInfoCamasToAttach.getClass(), infoCamasListNewInfoCamasToAttach.getId());
                attachedInfoCamasListNew.add(infoCamasListNewInfoCamasToAttach);
            }
            infoCamasListNew = attachedInfoCamasListNew;
            configCamas.setInfoCamasList(infoCamasListNew);
            configCamas = em.merge(configCamas);
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getConfigCamasList().remove(configCamas);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getConfigCamasList().add(configCamas);
                servicioNew = em.merge(servicioNew);
            }
            for (InfoCamas infoCamasListOldInfoCamas : infoCamasListOld) {
                if (!infoCamasListNew.contains(infoCamasListOldInfoCamas)) {
                    infoCamasListOldInfoCamas.setIdConfigCamas(null);
                    infoCamasListOldInfoCamas = em.merge(infoCamasListOldInfoCamas);
                }
            }
            for (InfoCamas infoCamasListNewInfoCamas : infoCamasListNew) {
                if (!infoCamasListOld.contains(infoCamasListNewInfoCamas)) {
                    ConfigCamas oldIdConfigCamasOfInfoCamasListNewInfoCamas = infoCamasListNewInfoCamas.getIdConfigCamas();
                    infoCamasListNewInfoCamas.setIdConfigCamas(configCamas);
                    infoCamasListNewInfoCamas = em.merge(infoCamasListNewInfoCamas);
                    if (oldIdConfigCamasOfInfoCamasListNewInfoCamas != null && !oldIdConfigCamasOfInfoCamasListNewInfoCamas.equals(configCamas)) {
                        oldIdConfigCamasOfInfoCamasListNewInfoCamas.getInfoCamasList().remove(infoCamasListNewInfoCamas);
                        oldIdConfigCamasOfInfoCamasListNewInfoCamas = em.merge(oldIdConfigCamasOfInfoCamasListNewInfoCamas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configCamas.getId();
                if (findConfigCamas(id) == null) {
                    throw new NonexistentEntityException("The configCamas with id " + id + " no longer exists.");
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
            ConfigCamas configCamas;
            try {
                configCamas = em.getReference(ConfigCamas.class, id);
                configCamas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configCamas with id " + id + " no longer exists.", enfe);
            }
            ConfigServicio servicio = configCamas.getServicio();
            if (servicio != null) {
                servicio.getConfigCamasList().remove(configCamas);
                servicio = em.merge(servicio);
            }
            List<InfoCamas> infoCamasList = configCamas.getInfoCamasList();
            for (InfoCamas infoCamasListInfoCamas : infoCamasList) {
                infoCamasListInfoCamas.setIdConfigCamas(null);
                infoCamasListInfoCamas = em.merge(infoCamasListInfoCamas);
            }
            em.remove(configCamas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfigCamas> findConfigCamasEntities() {
        return findConfigCamasEntities(true, -1, -1);
    }

    public List<ConfigCamas> findConfigCamasEntities(int maxResults, int firstResult) {
        return findConfigCamasEntities(false, maxResults, firstResult);
    }

    private List<ConfigCamas> findConfigCamasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfigCamas.class));
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

    public ConfigCamas findConfigCamas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfigCamas.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigCamasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfigCamas> rt = cq.from(ConfigCamas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ConfigCamas> find_config_camas() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM ConfigCamas i WHERE i.servicio.id=1 AND i.estado='0'");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public List<ConfigCamas> findconfigCamasEntities() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM ConfigCamas i");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public List<ConfigCamas> findconfigCamasEntities2() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM ConfigCamas i");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public ConfigCamas getEntidad(int id) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM ConfigCamas i WHERE i.id=:idd AND i.estado='0'");
            Q.setParameter("idd", id);
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return (ConfigCamas) Q.getSingleResult();
    }

}
