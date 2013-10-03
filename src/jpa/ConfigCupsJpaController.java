/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.ConfigCups;
import entidades.ConfigSoat1;
import entidades.ConfigSoat2;
import entidades.StaticEstructuraCups;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class ConfigCupsJpaController implements Serializable {

    public ConfigCupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfigCups configCups) {
        if (configCups.getConfigSoat2List() == null) {
            configCups.setConfigSoat2List(new ArrayList<ConfigSoat2>());
        }
        if (configCups.getConfigSoat1List() == null) {
            configCups.setConfigSoat1List(new ArrayList<ConfigSoat1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StaticEstructuraCups idEstructuraCups = configCups.getIdEstructuraCups();
            if (idEstructuraCups != null) {
                idEstructuraCups = em.getReference(idEstructuraCups.getClass(), idEstructuraCups.getId());
                configCups.setIdEstructuraCups(idEstructuraCups);
            }
            List<ConfigSoat2> attachedConfigSoat2List = new ArrayList<ConfigSoat2>();
            for (ConfigSoat2 configSoat2ListConfigSoat2ToAttach : configCups.getConfigSoat2List()) {
                configSoat2ListConfigSoat2ToAttach = em.getReference(configSoat2ListConfigSoat2ToAttach.getClass(), configSoat2ListConfigSoat2ToAttach.getId());
                attachedConfigSoat2List.add(configSoat2ListConfigSoat2ToAttach);
            }
            configCups.setConfigSoat2List(attachedConfigSoat2List);
            List<ConfigSoat1> attachedConfigSoat1List = new ArrayList<ConfigSoat1>();
            for (ConfigSoat1 configSoat1ListConfigSoat1ToAttach : configCups.getConfigSoat1List()) {
                configSoat1ListConfigSoat1ToAttach = em.getReference(configSoat1ListConfigSoat1ToAttach.getClass(), configSoat1ListConfigSoat1ToAttach.getId());
                attachedConfigSoat1List.add(configSoat1ListConfigSoat1ToAttach);
            }
            configCups.setConfigSoat1List(attachedConfigSoat1List);
            em.persist(configCups);
            if (idEstructuraCups != null) {
                idEstructuraCups.getConfigCupsList().add(configCups);
                idEstructuraCups = em.merge(idEstructuraCups);
            }
            for (ConfigSoat2 configSoat2ListConfigSoat2 : configCups.getConfigSoat2List()) {
                ConfigCups oldCodigoCupsOfConfigSoat2ListConfigSoat2 = configSoat2ListConfigSoat2.getCodigoCups();
                configSoat2ListConfigSoat2.setCodigoCups(configCups);
                configSoat2ListConfigSoat2 = em.merge(configSoat2ListConfigSoat2);
                if (oldCodigoCupsOfConfigSoat2ListConfigSoat2 != null) {
                    oldCodigoCupsOfConfigSoat2ListConfigSoat2.getConfigSoat2List().remove(configSoat2ListConfigSoat2);
                    oldCodigoCupsOfConfigSoat2ListConfigSoat2 = em.merge(oldCodigoCupsOfConfigSoat2ListConfigSoat2);
                }
            }
            for (ConfigSoat1 configSoat1ListConfigSoat1 : configCups.getConfigSoat1List()) {
                ConfigCups oldCodigoCupsOfConfigSoat1ListConfigSoat1 = configSoat1ListConfigSoat1.getCodigoCups();
                configSoat1ListConfigSoat1.setCodigoCups(configCups);
                configSoat1ListConfigSoat1 = em.merge(configSoat1ListConfigSoat1);
                if (oldCodigoCupsOfConfigSoat1ListConfigSoat1 != null) {
                    oldCodigoCupsOfConfigSoat1ListConfigSoat1.getConfigSoat1List().remove(configSoat1ListConfigSoat1);
                    oldCodigoCupsOfConfigSoat1ListConfigSoat1 = em.merge(oldCodigoCupsOfConfigSoat1ListConfigSoat1);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfigCups configCups) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCups persistentConfigCups = em.find(ConfigCups.class, configCups.getId());
            StaticEstructuraCups idEstructuraCupsOld = persistentConfigCups.getIdEstructuraCups();
            StaticEstructuraCups idEstructuraCupsNew = configCups.getIdEstructuraCups();
            List<ConfigSoat2> configSoat2ListOld = persistentConfigCups.getConfigSoat2List();
            List<ConfigSoat2> configSoat2ListNew = configCups.getConfigSoat2List();
            List<ConfigSoat1> configSoat1ListOld = persistentConfigCups.getConfigSoat1List();
            List<ConfigSoat1> configSoat1ListNew = configCups.getConfigSoat1List();
            List<String> illegalOrphanMessages = null;
            for (ConfigSoat2 configSoat2ListOldConfigSoat2 : configSoat2ListOld) {
                if (!configSoat2ListNew.contains(configSoat2ListOldConfigSoat2)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConfigSoat2 " + configSoat2ListOldConfigSoat2 + " since its codigoCups field is not nullable.");
                }
            }
            for (ConfigSoat1 configSoat1ListOldConfigSoat1 : configSoat1ListOld) {
                if (!configSoat1ListNew.contains(configSoat1ListOldConfigSoat1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConfigSoat1 " + configSoat1ListOldConfigSoat1 + " since its codigoCups field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEstructuraCupsNew != null) {
                idEstructuraCupsNew = em.getReference(idEstructuraCupsNew.getClass(), idEstructuraCupsNew.getId());
                configCups.setIdEstructuraCups(idEstructuraCupsNew);
            }
            List<ConfigSoat2> attachedConfigSoat2ListNew = new ArrayList<ConfigSoat2>();
            for (ConfigSoat2 configSoat2ListNewConfigSoat2ToAttach : configSoat2ListNew) {
                configSoat2ListNewConfigSoat2ToAttach = em.getReference(configSoat2ListNewConfigSoat2ToAttach.getClass(), configSoat2ListNewConfigSoat2ToAttach.getId());
                attachedConfigSoat2ListNew.add(configSoat2ListNewConfigSoat2ToAttach);
            }
            configSoat2ListNew = attachedConfigSoat2ListNew;
            configCups.setConfigSoat2List(configSoat2ListNew);
            List<ConfigSoat1> attachedConfigSoat1ListNew = new ArrayList<ConfigSoat1>();
            for (ConfigSoat1 configSoat1ListNewConfigSoat1ToAttach : configSoat1ListNew) {
                configSoat1ListNewConfigSoat1ToAttach = em.getReference(configSoat1ListNewConfigSoat1ToAttach.getClass(), configSoat1ListNewConfigSoat1ToAttach.getId());
                attachedConfigSoat1ListNew.add(configSoat1ListNewConfigSoat1ToAttach);
            }
            configSoat1ListNew = attachedConfigSoat1ListNew;
            configCups.setConfigSoat1List(configSoat1ListNew);
            configCups = em.merge(configCups);
            if (idEstructuraCupsOld != null && !idEstructuraCupsOld.equals(idEstructuraCupsNew)) {
                idEstructuraCupsOld.getConfigCupsList().remove(configCups);
                idEstructuraCupsOld = em.merge(idEstructuraCupsOld);
            }
            if (idEstructuraCupsNew != null && !idEstructuraCupsNew.equals(idEstructuraCupsOld)) {
                idEstructuraCupsNew.getConfigCupsList().add(configCups);
                idEstructuraCupsNew = em.merge(idEstructuraCupsNew);
            }
            for (ConfigSoat2 configSoat2ListNewConfigSoat2 : configSoat2ListNew) {
                if (!configSoat2ListOld.contains(configSoat2ListNewConfigSoat2)) {
                    ConfigCups oldCodigoCupsOfConfigSoat2ListNewConfigSoat2 = configSoat2ListNewConfigSoat2.getCodigoCups();
                    configSoat2ListNewConfigSoat2.setCodigoCups(configCups);
                    configSoat2ListNewConfigSoat2 = em.merge(configSoat2ListNewConfigSoat2);
                    if (oldCodigoCupsOfConfigSoat2ListNewConfigSoat2 != null && !oldCodigoCupsOfConfigSoat2ListNewConfigSoat2.equals(configCups)) {
                        oldCodigoCupsOfConfigSoat2ListNewConfigSoat2.getConfigSoat2List().remove(configSoat2ListNewConfigSoat2);
                        oldCodigoCupsOfConfigSoat2ListNewConfigSoat2 = em.merge(oldCodigoCupsOfConfigSoat2ListNewConfigSoat2);
                    }
                }
            }
            for (ConfigSoat1 configSoat1ListNewConfigSoat1 : configSoat1ListNew) {
                if (!configSoat1ListOld.contains(configSoat1ListNewConfigSoat1)) {
                    ConfigCups oldCodigoCupsOfConfigSoat1ListNewConfigSoat1 = configSoat1ListNewConfigSoat1.getCodigoCups();
                    configSoat1ListNewConfigSoat1.setCodigoCups(configCups);
                    configSoat1ListNewConfigSoat1 = em.merge(configSoat1ListNewConfigSoat1);
                    if (oldCodigoCupsOfConfigSoat1ListNewConfigSoat1 != null && !oldCodigoCupsOfConfigSoat1ListNewConfigSoat1.equals(configCups)) {
                        oldCodigoCupsOfConfigSoat1ListNewConfigSoat1.getConfigSoat1List().remove(configSoat1ListNewConfigSoat1);
                        oldCodigoCupsOfConfigSoat1ListNewConfigSoat1 = em.merge(oldCodigoCupsOfConfigSoat1ListNewConfigSoat1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configCups.getId();
                if (findConfigCups(id) == null) {
                    throw new NonexistentEntityException("The configCups with id " + id + " no longer exists.");
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
            ConfigCups configCups;
            try {
                configCups = em.getReference(ConfigCups.class, id);
                configCups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configCups with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ConfigSoat2> configSoat2ListOrphanCheck = configCups.getConfigSoat2List();
            for (ConfigSoat2 configSoat2ListOrphanCheckConfigSoat2 : configSoat2ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConfigCups (" + configCups + ") cannot be destroyed since the ConfigSoat2 " + configSoat2ListOrphanCheckConfigSoat2 + " in its configSoat2List field has a non-nullable codigoCups field.");
            }
            List<ConfigSoat1> configSoat1ListOrphanCheck = configCups.getConfigSoat1List();
            for (ConfigSoat1 configSoat1ListOrphanCheckConfigSoat1 : configSoat1ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConfigCups (" + configCups + ") cannot be destroyed since the ConfigSoat1 " + configSoat1ListOrphanCheckConfigSoat1 + " in its configSoat1List field has a non-nullable codigoCups field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            StaticEstructuraCups idEstructuraCups = configCups.getIdEstructuraCups();
            if (idEstructuraCups != null) {
                idEstructuraCups.getConfigCupsList().remove(configCups);
                idEstructuraCups = em.merge(idEstructuraCups);
            }
            em.remove(configCups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfigCups> findConfigCupsEntities() {
        return findConfigCupsEntities(true, -1, -1);
    }

    public List<ConfigCups> findConfigCupsEntities(int maxResults, int firstResult) {
        return findConfigCupsEntities(false, maxResults, firstResult);
    }

    private List<ConfigCups> findConfigCupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfigCups.class));
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

    public ConfigCups findConfigCups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfigCups.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigCupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfigCups> rt = cq.from(ConfigCups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //codigo no Auto-Generado
    public List<ConfigCups> listConfigCups(StaticEstructuraCups idCups){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM ConfigCups c WHERE c.idEstructuraCups = :idCups AND c.estadoUrg=1 AND c.estado=1" )
                    .setParameter("idCups", idCups)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
}
