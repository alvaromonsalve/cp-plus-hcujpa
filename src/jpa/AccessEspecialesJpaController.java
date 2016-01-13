/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.AccessEspeciales;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IdlhDeveloper
 */
public class AccessEspecialesJpaController implements Serializable {

    public AccessEspecialesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccessEspeciales accessEspeciales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(accessEspeciales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccessEspeciales accessEspeciales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            accessEspeciales = em.merge(accessEspeciales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accessEspeciales.getId();
                if (findAccessEspeciales(id) == null) {
                    throw new NonexistentEntityException("The accessEspeciales with id " + id + " no longer exists.");
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
            AccessEspeciales accessEspeciales;
            try {
                accessEspeciales = em.getReference(AccessEspeciales.class, id);
                accessEspeciales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accessEspeciales with id " + id + " no longer exists.", enfe);
            }
            em.remove(accessEspeciales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AccessEspeciales> findAccessEspecialesEntities() {
        return findAccessEspecialesEntities(true, -1, -1);
    }

    public List<AccessEspeciales> findAccessEspecialesEntities(int maxResults, int firstResult) {
        return findAccessEspecialesEntities(false, maxResults, firstResult);
    }

    private List<AccessEspeciales> findAccessEspecialesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccessEspeciales.class));
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

    public AccessEspeciales findAccessEspeciales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccessEspeciales.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccessEspecialesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccessEspeciales> rt = cq.from(AccessEspeciales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Object getPermisosEspeciales(int u) {
        EntityManager em = getEntityManager();
        try {
            Query Q = null;
            Q = em.createQuery("SELECT COUNT(e.id) FROM AccessEspeciales e WHERE e.idConfigUsuario.configdecripcionlogin.id=:us AND e.idConfigUsuario.idPerfiles.id='4' AND e.idConfigUsuario.idPerfiles.estado='1' AND e.acceso='Editar_Enfermeria'");
            Q.setParameter("us", u);
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return Q.getSingleResult();
        } finally {
            em.close();
        }
    }

}
