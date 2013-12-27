/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.ConfigCups;
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
public class StaticEstructuraCupsJpaController implements Serializable {

    public StaticEstructuraCupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StaticEstructuraCups staticEstructuraCups) {
        if (staticEstructuraCups.getConfigCupsList() == null) {
            staticEstructuraCups.setConfigCupsList(new ArrayList<ConfigCups>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ConfigCups> attachedConfigCupsList = new ArrayList<ConfigCups>();
            for (ConfigCups configCupsListConfigCupsToAttach : staticEstructuraCups.getConfigCupsList()) {
                configCupsListConfigCupsToAttach = em.getReference(configCupsListConfigCupsToAttach.getClass(), configCupsListConfigCupsToAttach.getId());
                attachedConfigCupsList.add(configCupsListConfigCupsToAttach);
            }
            staticEstructuraCups.setConfigCupsList(attachedConfigCupsList);
            em.persist(staticEstructuraCups);
            for (ConfigCups configCupsListConfigCups : staticEstructuraCups.getConfigCupsList()) {
                StaticEstructuraCups oldIdEstructuraCupsOfConfigCupsListConfigCups = configCupsListConfigCups.getIdEstructuraCups();
                configCupsListConfigCups.setIdEstructuraCups(staticEstructuraCups);
                configCupsListConfigCups = em.merge(configCupsListConfigCups);
                if (oldIdEstructuraCupsOfConfigCupsListConfigCups != null) {
                    oldIdEstructuraCupsOfConfigCupsListConfigCups.getConfigCupsList().remove(configCupsListConfigCups);
                    oldIdEstructuraCupsOfConfigCupsListConfigCups = em.merge(oldIdEstructuraCupsOfConfigCupsListConfigCups);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StaticEstructuraCups staticEstructuraCups) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StaticEstructuraCups persistentStaticEstructuraCups = em.find(StaticEstructuraCups.class, staticEstructuraCups.getId());
            List<ConfigCups> configCupsListOld = persistentStaticEstructuraCups.getConfigCupsList();
            List<ConfigCups> configCupsListNew = staticEstructuraCups.getConfigCupsList();
            List<String> illegalOrphanMessages = null;
            for (ConfigCups configCupsListOldConfigCups : configCupsListOld) {
                if (!configCupsListNew.contains(configCupsListOldConfigCups)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ConfigCups " + configCupsListOldConfigCups + " since its idEstructuraCups field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ConfigCups> attachedConfigCupsListNew = new ArrayList<ConfigCups>();
            for (ConfigCups configCupsListNewConfigCupsToAttach : configCupsListNew) {
                configCupsListNewConfigCupsToAttach = em.getReference(configCupsListNewConfigCupsToAttach.getClass(), configCupsListNewConfigCupsToAttach.getId());
                attachedConfigCupsListNew.add(configCupsListNewConfigCupsToAttach);
            }
            configCupsListNew = attachedConfigCupsListNew;
            staticEstructuraCups.setConfigCupsList(configCupsListNew);
            staticEstructuraCups = em.merge(staticEstructuraCups);
            for (ConfigCups configCupsListNewConfigCups : configCupsListNew) {
                if (!configCupsListOld.contains(configCupsListNewConfigCups)) {
                    StaticEstructuraCups oldIdEstructuraCupsOfConfigCupsListNewConfigCups = configCupsListNewConfigCups.getIdEstructuraCups();
                    configCupsListNewConfigCups.setIdEstructuraCups(staticEstructuraCups);
                    configCupsListNewConfigCups = em.merge(configCupsListNewConfigCups);
                    if (oldIdEstructuraCupsOfConfigCupsListNewConfigCups != null && !oldIdEstructuraCupsOfConfigCupsListNewConfigCups.equals(staticEstructuraCups)) {
                        oldIdEstructuraCupsOfConfigCupsListNewConfigCups.getConfigCupsList().remove(configCupsListNewConfigCups);
                        oldIdEstructuraCupsOfConfigCupsListNewConfigCups = em.merge(oldIdEstructuraCupsOfConfigCupsListNewConfigCups);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = staticEstructuraCups.getId();
                if (findStaticEstructuraCups(id) == null) {
                    throw new NonexistentEntityException("The staticEstructuraCups with id " + id + " no longer exists.");
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
            StaticEstructuraCups staticEstructuraCups;
            try {
                staticEstructuraCups = em.getReference(StaticEstructuraCups.class, id);
                staticEstructuraCups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The staticEstructuraCups with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ConfigCups> configCupsListOrphanCheck = staticEstructuraCups.getConfigCupsList();
            for (ConfigCups configCupsListOrphanCheckConfigCups : configCupsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This StaticEstructuraCups (" + staticEstructuraCups + ") cannot be destroyed since the ConfigCups " + configCupsListOrphanCheckConfigCups + " in its configCupsList field has a non-nullable idEstructuraCups field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(staticEstructuraCups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StaticEstructuraCups> findStaticEstructuraCupsEntities() {
        return findStaticEstructuraCupsEntities(true, -1, -1);
    }

    public List<StaticEstructuraCups> findStaticEstructuraCupsEntities(int maxResults, int firstResult) {
        return findStaticEstructuraCupsEntities(false, maxResults, firstResult);
    }

    private List<StaticEstructuraCups> findStaticEstructuraCupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StaticEstructuraCups.class));
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

    public StaticEstructuraCups findStaticEstructuraCups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StaticEstructuraCups.class, id);
        } finally {
            em.close();
        }
    }

    public int getStaticEstructuraCupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StaticEstructuraCups> rt = cq.from(StaticEstructuraCups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }    
    
    //Codigo no Auto-generado    
    public ConfigCups FindCups(String codigo){
        EntityManager em = getEntityManager();
        ConfigCups cc = null;
        try {
            cc = (ConfigCups) em.createQuery("SELECT c FROM ConfigCups c WHERE c.codigo = :codigo")
            .setParameter("codigo", codigo)
            .getSingleResult();
        }catch(Exception e){
            cc=null;
        } finally {
            em.close();
        }
        return cc;
   }
    
    
    
    
}
