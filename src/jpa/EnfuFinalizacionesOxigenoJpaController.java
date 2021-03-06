/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.EnfuFinalizacionesOxigeno;
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
public class EnfuFinalizacionesOxigenoJpaController implements Serializable {

    public EnfuFinalizacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuFinalizacionesOxigeno enfuFinalizacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuFinalizacionesOxigeno enfuFinalizacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuFinalizacionesOxigeno = em.merge(enfuFinalizacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuFinalizacionesOxigeno.getId();
                if (findEnfuFinalizacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The enfuFinalizacionesOxigeno with id " + id + " no longer exists.");
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
            EnfuFinalizacionesOxigeno enfuFinalizacionesOxigeno;
            try {
                enfuFinalizacionesOxigeno = em.getReference(EnfuFinalizacionesOxigeno.class, id);
                enfuFinalizacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuFinalizacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuFinalizacionesOxigeno> findEnfuFinalizacionesOxigenoEntities() {
        return findEnfuFinalizacionesOxigenoEntities(true, -1, -1);
    }

    public List<EnfuFinalizacionesOxigeno> findEnfuFinalizacionesOxigenoEntities(int maxResults, int firstResult) {
        return findEnfuFinalizacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<EnfuFinalizacionesOxigeno> findEnfuFinalizacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuFinalizacionesOxigeno.class));
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

    public EnfuFinalizacionesOxigeno findEnfuFinalizacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuFinalizacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuFinalizacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuFinalizacionesOxigeno> rt = cq.from(EnfuFinalizacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuFinalizacionesOxigeno> getFinalizados(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM EnfuFinalizacionesOxigeno o WHERE o.idAplicacion.idInfoHistoriac.id=:historia AND o.idAplicacion.estado='2' AND o.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<EnfuFinalizacionesOxigeno> getFinalizacion(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM EnfuFinalizacionesOxigeno o WHERE o.idAplicacion.id=:i AND o.estado='1'");
        Q.setParameter("i", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public Object getCountFinalizacionO(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(o.id) FROM EnfuFinalizacionesOxigeno o WHERE o.idAplicacion.id=:ia AND o.estado='1'");
        Q.setParameter("ia", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }
    
}
