/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuHojaTratamientos;
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
public class EnfuHojaTratamientosJpaController implements Serializable {

    public EnfuHojaTratamientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuHojaTratamientos enfuHojaTratamientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuHojaTratamientos enfuHojaTratamientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuHojaTratamientos = em.merge(enfuHojaTratamientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuHojaTratamientos.getId();
                if (findEnfuHojaTratamientos(id) == null) {
                    throw new NonexistentEntityException("The enfuHojaTratamientos with id " + id + " no longer exists.");
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
            EnfuHojaTratamientos enfuHojaTratamientos;
            try {
                enfuHojaTratamientos = em.getReference(EnfuHojaTratamientos.class, id);
                enfuHojaTratamientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuHojaTratamientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuHojaTratamientos> findEnfuHojaTratamientosEntities() {
        return findEnfuHojaTratamientosEntities(true, -1, -1);
    }

    public List<EnfuHojaTratamientos> findEnfuHojaTratamientosEntities(int maxResults, int firstResult) {
        return findEnfuHojaTratamientosEntities(false, maxResults, firstResult);
    }

    private List<EnfuHojaTratamientos> findEnfuHojaTratamientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuHojaTratamientos.class));
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

    public EnfuHojaTratamientos findEnfuHojaTratamientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuHojaTratamientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuHojaTratamientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuHojaTratamientos> rt = cq.from(EnfuHojaTratamientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuHojaTratamientos> getAplicaciones(int hc, int sum, int tipo, int ident) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT a FROM EnfuHojaTratamientos a WHERE a.idHistoria=:h AND a.idSuministro=:s AND a.tipo=:t AND a.identificador=:i AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        q.setParameter("s", sum);
        q.setParameter("t", tipo);
        q.setParameter("i", ident);
        return q.getResultList();
    }

    public List<EnfuHojaTratamientos> getAplicaciones2(int hc) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT a FROM EnfuHojaTratamientos a WHERE a.idHistoria=:h AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        return q.getResultList();
    }

    public Object countHojaTratamiento1(int h) {
        EntityManager em = getEntityManager();
        Query q = null;
        try {
            q = em.createQuery("SELECT COUNT(N) FROM EnfuHojaTratamientos N WHERE N.idHistoria=:ht AND N.estado='1'");
            q.setParameter("ht", h);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
