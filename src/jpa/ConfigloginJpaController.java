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
import entidades.Configdecripcionlogin;
import entidades.Configlogin;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class ConfigloginJpaController implements Serializable {

    public ConfigloginJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Configlogin configlogin) {
        if (configlogin.getConfigdecripcionloginList() == null) {
            configlogin.setConfigdecripcionloginList(new ArrayList<Configdecripcionlogin>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Configdecripcionlogin> attachedConfigdecripcionloginList = new ArrayList<Configdecripcionlogin>();
            for (Configdecripcionlogin configdecripcionloginListConfigdecripcionloginToAttach : configlogin.getConfigdecripcionloginList()) {
                configdecripcionloginListConfigdecripcionloginToAttach = em.getReference(configdecripcionloginListConfigdecripcionloginToAttach.getClass(), configdecripcionloginListConfigdecripcionloginToAttach.getId());
                attachedConfigdecripcionloginList.add(configdecripcionloginListConfigdecripcionloginToAttach);
            }
            configlogin.setConfigdecripcionloginList(attachedConfigdecripcionloginList);
            em.persist(configlogin);
            for (Configdecripcionlogin configdecripcionloginListConfigdecripcionlogin : configlogin.getConfigdecripcionloginList()) {
                Configlogin oldIdLoginOfConfigdecripcionloginListConfigdecripcionlogin = configdecripcionloginListConfigdecripcionlogin.getIdLogin();
                configdecripcionloginListConfigdecripcionlogin.setIdLogin(configlogin);
                configdecripcionloginListConfigdecripcionlogin = em.merge(configdecripcionloginListConfigdecripcionlogin);
                if (oldIdLoginOfConfigdecripcionloginListConfigdecripcionlogin != null) {
                    oldIdLoginOfConfigdecripcionloginListConfigdecripcionlogin.getConfigdecripcionloginList().remove(configdecripcionloginListConfigdecripcionlogin);
                    oldIdLoginOfConfigdecripcionloginListConfigdecripcionlogin = em.merge(oldIdLoginOfConfigdecripcionloginListConfigdecripcionlogin);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Configlogin configlogin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Configlogin persistentConfiglogin = em.find(Configlogin.class, configlogin.getId());
            List<Configdecripcionlogin> configdecripcionloginListOld = persistentConfiglogin.getConfigdecripcionloginList();
            List<Configdecripcionlogin> configdecripcionloginListNew = configlogin.getConfigdecripcionloginList();
            List<Configdecripcionlogin> attachedConfigdecripcionloginListNew = new ArrayList<Configdecripcionlogin>();
            for (Configdecripcionlogin configdecripcionloginListNewConfigdecripcionloginToAttach : configdecripcionloginListNew) {
                configdecripcionloginListNewConfigdecripcionloginToAttach = em.getReference(configdecripcionloginListNewConfigdecripcionloginToAttach.getClass(), configdecripcionloginListNewConfigdecripcionloginToAttach.getId());
                attachedConfigdecripcionloginListNew.add(configdecripcionloginListNewConfigdecripcionloginToAttach);
            }
            configdecripcionloginListNew = attachedConfigdecripcionloginListNew;
            configlogin.setConfigdecripcionloginList(configdecripcionloginListNew);
            configlogin = em.merge(configlogin);
            for (Configdecripcionlogin configdecripcionloginListOldConfigdecripcionlogin : configdecripcionloginListOld) {
                if (!configdecripcionloginListNew.contains(configdecripcionloginListOldConfigdecripcionlogin)) {
                    configdecripcionloginListOldConfigdecripcionlogin.setIdLogin(null);
                    configdecripcionloginListOldConfigdecripcionlogin = em.merge(configdecripcionloginListOldConfigdecripcionlogin);
                }
            }
            for (Configdecripcionlogin configdecripcionloginListNewConfigdecripcionlogin : configdecripcionloginListNew) {
                if (!configdecripcionloginListOld.contains(configdecripcionloginListNewConfigdecripcionlogin)) {
                    Configlogin oldIdLoginOfConfigdecripcionloginListNewConfigdecripcionlogin = configdecripcionloginListNewConfigdecripcionlogin.getIdLogin();
                    configdecripcionloginListNewConfigdecripcionlogin.setIdLogin(configlogin);
                    configdecripcionloginListNewConfigdecripcionlogin = em.merge(configdecripcionloginListNewConfigdecripcionlogin);
                    if (oldIdLoginOfConfigdecripcionloginListNewConfigdecripcionlogin != null && !oldIdLoginOfConfigdecripcionloginListNewConfigdecripcionlogin.equals(configlogin)) {
                        oldIdLoginOfConfigdecripcionloginListNewConfigdecripcionlogin.getConfigdecripcionloginList().remove(configdecripcionloginListNewConfigdecripcionlogin);
                        oldIdLoginOfConfigdecripcionloginListNewConfigdecripcionlogin = em.merge(oldIdLoginOfConfigdecripcionloginListNewConfigdecripcionlogin);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configlogin.getId();
                if (findConfiglogin(id) == null) {
                    throw new NonexistentEntityException("The configlogin with id " + id + " no longer exists.");
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
            Configlogin configlogin;
            try {
                configlogin = em.getReference(Configlogin.class, id);
                configlogin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configlogin with id " + id + " no longer exists.", enfe);
            }
            List<Configdecripcionlogin> configdecripcionloginList = configlogin.getConfigdecripcionloginList();
            for (Configdecripcionlogin configdecripcionloginListConfigdecripcionlogin : configdecripcionloginList) {
                configdecripcionloginListConfigdecripcionlogin.setIdLogin(null);
                configdecripcionloginListConfigdecripcionlogin = em.merge(configdecripcionloginListConfigdecripcionlogin);
            }
            em.remove(configlogin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Configlogin> findConfigloginEntities() {
        return findConfigloginEntities(true, -1, -1);
    }

    public List<Configlogin> findConfigloginEntities(int maxResults, int firstResult) {
        return findConfigloginEntities(false, maxResults, firstResult);
    }

    private List<Configlogin> findConfigloginEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Configlogin.class));
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

    public Configlogin findConfiglogin(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Configlogin.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigloginCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Configlogin> rt = cq.from(Configlogin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        //Codigo no Auto-generado
    
    public Configlogin findConfiglogin(String name, String pass){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c FROM Configlogin c WHERE c.loGin = :name AND c.pasSword = :pass");
            
            q.setParameter("name", name);
            q.setParameter("pass", pass);
            return (Configlogin)q.getSingleResult();
        }catch(Exception ex){
            return null;
        } finally {
            em.close();
        }
    }    
}