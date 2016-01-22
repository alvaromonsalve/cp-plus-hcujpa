/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFinalizacionesOxigeno;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UciFinalizacionesOxigenoJpaController implements Serializable {

    public UciFinalizacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciFinalizacionesOxigeno uciFinalizacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciFinalizacionesOxigeno uciFinalizacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciFinalizacionesOxigeno = em.merge(uciFinalizacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciFinalizacionesOxigeno.getId();
                if (findUciFinalizacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The uciFinalizacionesOxigeno with id " + id + " no longer exists.");
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
            UciFinalizacionesOxigeno uciFinalizacionesOxigeno;
            try {
                uciFinalizacionesOxigeno = em.getReference(UciFinalizacionesOxigeno.class, id);
                uciFinalizacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciFinalizacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciFinalizacionesOxigeno> findUciFinalizacionesOxigenoEntities() {
        return findUciFinalizacionesOxigenoEntities(true, -1, -1);
    }

    public List<UciFinalizacionesOxigeno> findUciFinalizacionesOxigenoEntities(int maxResults, int firstResult) {
        return findUciFinalizacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<UciFinalizacionesOxigeno> findUciFinalizacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciFinalizacionesOxigeno.class));
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

    public UciFinalizacionesOxigeno findUciFinalizacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciFinalizacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciFinalizacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciFinalizacionesOxigeno> rt = cq.from(UciFinalizacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciFinalizacionesOxigeno> getFinalizados(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UciFinalizacionesOxigeno o WHERE o.idAplicacion.idHistoriac.id=:historia AND o.idAplicacion.estado='2' AND o.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<UciFinalizacionesOxigeno> getFinalizacion(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UciFinalizacionesOxigeno o WHERE o.idAplicacion.id=:i AND o.estado='1'");
        Q.setParameter("i", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public Object getCountFinalizacionO(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(o.id) FROM UciFinalizacionesOxigeno o WHERE o.idAplicacion.id=:ia AND o.estado='1'");
        Q.setParameter("ia", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }
}
