/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.AccessConfigUser;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.AccessPerfiles;
import entidades_EJB.Configdecripcionlogin;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class AccessConfigUserJpaController implements Serializable {

    public AccessConfigUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccessConfigUser accessConfigUser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccessPerfiles idPerfiles = accessConfigUser.getIdPerfiles();
            if (idPerfiles != null) {
                idPerfiles = em.getReference(idPerfiles.getClass(), idPerfiles.getId());
                accessConfigUser.setIdPerfiles(idPerfiles);
            }
            em.persist(accessConfigUser);
            if (idPerfiles != null) {
                idPerfiles.getAccessConfigUserList().add(accessConfigUser);
                idPerfiles = em.merge(idPerfiles);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccessConfigUser accessConfigUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccessConfigUser persistentAccessConfigUser = em.find(AccessConfigUser.class, accessConfigUser.getId());
            AccessPerfiles idPerfilesOld = persistentAccessConfigUser.getIdPerfiles();
            AccessPerfiles idPerfilesNew = accessConfigUser.getIdPerfiles();
            if (idPerfilesNew != null) {
                idPerfilesNew = em.getReference(idPerfilesNew.getClass(), idPerfilesNew.getId());
                accessConfigUser.setIdPerfiles(idPerfilesNew);
            }
            accessConfigUser = em.merge(accessConfigUser);
            if (idPerfilesOld != null && !idPerfilesOld.equals(idPerfilesNew)) {
                idPerfilesOld.getAccessConfigUserList().remove(accessConfigUser);
                idPerfilesOld = em.merge(idPerfilesOld);
            }
            if (idPerfilesNew != null && !idPerfilesNew.equals(idPerfilesOld)) {
                idPerfilesNew.getAccessConfigUserList().add(accessConfigUser);
                idPerfilesNew = em.merge(idPerfilesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accessConfigUser.getId();
                if (findAccessConfigUser(id) == null) {
                    throw new NonexistentEntityException("The accessConfigUser with id " + id + " no longer exists.");
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
            AccessConfigUser accessConfigUser;
            try {
                accessConfigUser = em.getReference(AccessConfigUser.class, id);
                accessConfigUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accessConfigUser with id " + id + " no longer exists.", enfe);
            }
            AccessPerfiles idPerfiles = accessConfigUser.getIdPerfiles();
            if (idPerfiles != null) {
                idPerfiles.getAccessConfigUserList().remove(accessConfigUser);
                idPerfiles = em.merge(idPerfiles);
            }
            em.remove(accessConfigUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AccessConfigUser> findAccessConfigUserEntities() {
        return findAccessConfigUserEntities(true, -1, -1);
    }

    public List<AccessConfigUser> findAccessConfigUserEntities(int maxResults, int firstResult) {
        return findAccessConfigUserEntities(false, maxResults, firstResult);
    }

    private List<AccessConfigUser> findAccessConfigUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccessConfigUser.class));
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

    public AccessConfigUser findAccessConfigUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccessConfigUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccessConfigUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccessConfigUser> rt = cq.from(AccessConfigUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado    
    public List<AccessConfigUser> FindConfigUsers(Configdecripcionlogin cf ){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM AccessConfigUser a WHERE a.configdecripcionlogin = :cf AND a.estado <> '0'")
            .setParameter("cf", cf)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
}
