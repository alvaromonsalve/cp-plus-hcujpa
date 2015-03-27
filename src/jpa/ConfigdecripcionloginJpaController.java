/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.Configdecripcionlogin;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.Configlogin;
import entidades_EJB.InfoHistoriac;
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
public class ConfigdecripcionloginJpaController implements Serializable {

    public ConfigdecripcionloginJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Configdecripcionlogin configdecripcionlogin) {
        if (configdecripcionlogin.getInfoHistoriac() == null) {
            configdecripcionlogin.setInfoHistoriac(new ArrayList<InfoHistoriac>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Configlogin idLogin = configdecripcionlogin.getIdLogin();
            if (idLogin != null) {
                idLogin = em.getReference(idLogin.getClass(), idLogin.getId());
                configdecripcionlogin.setIdLogin(idLogin);
            }
            List<InfoHistoriac> attachedInfoHistoriac = new ArrayList<InfoHistoriac>();
            for (InfoHistoriac infoHistoriacInfoHistoriacToAttach : configdecripcionlogin.getInfoHistoriac()) {
                infoHistoriacInfoHistoriacToAttach = em.getReference(infoHistoriacInfoHistoriacToAttach.getClass(), infoHistoriacInfoHistoriacToAttach.getId());
                attachedInfoHistoriac.add(infoHistoriacInfoHistoriacToAttach);
            }
            configdecripcionlogin.setInfoHistoriac(attachedInfoHistoriac);
            em.persist(configdecripcionlogin);
            if (idLogin != null) {
                idLogin.getConfigdecripcionloginList().add(configdecripcionlogin);
                idLogin = em.merge(idLogin);
            }
            for (InfoHistoriac infoHistoriacInfoHistoriac : configdecripcionlogin.getInfoHistoriac()) {
                Configdecripcionlogin oldIdConfigdecripcionloginOfInfoHistoriacInfoHistoriac = infoHistoriacInfoHistoriac.getIdConfigdecripcionlogin();
                infoHistoriacInfoHistoriac.setIdConfigdecripcionlogin(configdecripcionlogin);
                infoHistoriacInfoHistoriac = em.merge(infoHistoriacInfoHistoriac);
                if (oldIdConfigdecripcionloginOfInfoHistoriacInfoHistoriac != null) {
                    oldIdConfigdecripcionloginOfInfoHistoriacInfoHistoriac.getInfoHistoriac().remove(infoHistoriacInfoHistoriac);
                    oldIdConfigdecripcionloginOfInfoHistoriacInfoHistoriac = em.merge(oldIdConfigdecripcionloginOfInfoHistoriacInfoHistoriac);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Configdecripcionlogin configdecripcionlogin) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Configdecripcionlogin persistentConfigdecripcionlogin = em.find(Configdecripcionlogin.class, configdecripcionlogin.getId());
            Configlogin idLoginOld = persistentConfigdecripcionlogin.getIdLogin();
            Configlogin idLoginNew = configdecripcionlogin.getIdLogin();
            List<InfoHistoriac> infoHistoriacOld = persistentConfigdecripcionlogin.getInfoHistoriac();
            List<InfoHistoriac> infoHistoriacNew = configdecripcionlogin.getInfoHistoriac();
            List<String> illegalOrphanMessages = null;
            for (InfoHistoriac infoHistoriacOldInfoHistoriac : infoHistoriacOld) {
                if (!infoHistoriacNew.contains(infoHistoriacOldInfoHistoriac)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoHistoriac " + infoHistoriacOldInfoHistoriac + " since its idConfigdecripcionlogin field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idLoginNew != null) {
                idLoginNew = em.getReference(idLoginNew.getClass(), idLoginNew.getId());
                configdecripcionlogin.setIdLogin(idLoginNew);
            }
            List<InfoHistoriac> attachedInfoHistoriacNew = new ArrayList<InfoHistoriac>();
            for (InfoHistoriac infoHistoriacNewInfoHistoriacToAttach : infoHistoriacNew) {
                infoHistoriacNewInfoHistoriacToAttach = em.getReference(infoHistoriacNewInfoHistoriacToAttach.getClass(), infoHistoriacNewInfoHistoriacToAttach.getId());
                attachedInfoHistoriacNew.add(infoHistoriacNewInfoHistoriacToAttach);
            }
            infoHistoriacNew = attachedInfoHistoriacNew;
            configdecripcionlogin.setInfoHistoriac(infoHistoriacNew);
            configdecripcionlogin = em.merge(configdecripcionlogin);
            if (idLoginOld != null && !idLoginOld.equals(idLoginNew)) {
                idLoginOld.getConfigdecripcionloginList().remove(configdecripcionlogin);
                idLoginOld = em.merge(idLoginOld);
            }
            if (idLoginNew != null && !idLoginNew.equals(idLoginOld)) {
                idLoginNew.getConfigdecripcionloginList().add(configdecripcionlogin);
                idLoginNew = em.merge(idLoginNew);
            }
            for (InfoHistoriac infoHistoriacNewInfoHistoriac : infoHistoriacNew) {
                if (!infoHistoriacOld.contains(infoHistoriacNewInfoHistoriac)) {
                    Configdecripcionlogin oldIdConfigdecripcionloginOfInfoHistoriacNewInfoHistoriac = infoHistoriacNewInfoHistoriac.getIdConfigdecripcionlogin();
                    infoHistoriacNewInfoHistoriac.setIdConfigdecripcionlogin(configdecripcionlogin);
                    infoHistoriacNewInfoHistoriac = em.merge(infoHistoriacNewInfoHistoriac);
                    if (oldIdConfigdecripcionloginOfInfoHistoriacNewInfoHistoriac != null && !oldIdConfigdecripcionloginOfInfoHistoriacNewInfoHistoriac.equals(configdecripcionlogin)) {
                        oldIdConfigdecripcionloginOfInfoHistoriacNewInfoHistoriac.getInfoHistoriac().remove(infoHistoriacNewInfoHistoriac);
                        oldIdConfigdecripcionloginOfInfoHistoriacNewInfoHistoriac = em.merge(oldIdConfigdecripcionloginOfInfoHistoriacNewInfoHistoriac);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configdecripcionlogin.getId();
                if (findConfigdecripcionlogin(id) == null) {
                    throw new NonexistentEntityException("The configdecripcionlogin with id " + id + " no longer exists.");
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
            Configdecripcionlogin configdecripcionlogin;
            try {
                configdecripcionlogin = em.getReference(Configdecripcionlogin.class, id);
                configdecripcionlogin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configdecripcionlogin with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InfoHistoriac> infoHistoriacOrphanCheck = configdecripcionlogin.getInfoHistoriac();
            for (InfoHistoriac infoHistoriacOrphanCheckInfoHistoriac : infoHistoriacOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Configdecripcionlogin (" + configdecripcionlogin + ") cannot be destroyed since the InfoHistoriac " + infoHistoriacOrphanCheckInfoHistoriac + " in its infoHistoriac field has a non-nullable idConfigdecripcionlogin field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Configlogin idLogin = configdecripcionlogin.getIdLogin();
            if (idLogin != null) {
                idLogin.getConfigdecripcionloginList().remove(configdecripcionlogin);
                idLogin = em.merge(idLogin);
            }
            em.remove(configdecripcionlogin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Configdecripcionlogin> findConfigdecripcionloginEntities() {
        return findConfigdecripcionloginEntities(true, -1, -1);
    }

    public List<Configdecripcionlogin> findConfigdecripcionloginEntities(int maxResults, int firstResult) {
        return findConfigdecripcionloginEntities(false, maxResults, firstResult);
    }

    private List<Configdecripcionlogin> findConfigdecripcionloginEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Configdecripcionlogin.class));
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

    public Configdecripcionlogin findConfigdecripcionlogin(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Configdecripcionlogin.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfigdecripcionloginCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Configdecripcionlogin> rt = cq.from(Configdecripcionlogin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
