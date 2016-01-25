/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFinalizacionesOxigeno;
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
public class UceFinalizacionesOxigenoJpaController implements Serializable {

    public UceFinalizacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceFinalizacionesOxigeno uceFinalizacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceFinalizacionesOxigeno uceFinalizacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceFinalizacionesOxigeno = em.merge(uceFinalizacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceFinalizacionesOxigeno.getId();
                if (findUceFinalizacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The uceFinalizacionesOxigeno with id " + id + " no longer exists.");
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
            UceFinalizacionesOxigeno uceFinalizacionesOxigeno;
            try {
                uceFinalizacionesOxigeno = em.getReference(UceFinalizacionesOxigeno.class, id);
                uceFinalizacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceFinalizacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceFinalizacionesOxigeno> findUceFinalizacionesOxigenoEntities() {
        return findUceFinalizacionesOxigenoEntities(true, -1, -1);
    }

    public List<UceFinalizacionesOxigeno> findUceFinalizacionesOxigenoEntities(int maxResults, int firstResult) {
        return findUceFinalizacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<UceFinalizacionesOxigeno> findUceFinalizacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceFinalizacionesOxigeno.class));
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

    public UceFinalizacionesOxigeno findUceFinalizacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceFinalizacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceFinalizacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceFinalizacionesOxigeno> rt = cq.from(UceFinalizacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceFinalizacionesOxigeno> getFinalizados(int h) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UceFinalizacionesOxigeno o WHERE o.idAplicacion.idHistoriac.id=:historia AND o.idAplicacion.estado='2' AND o.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<UceFinalizacionesOxigeno> getFinalizacion(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT o FROM UceFinalizacionesOxigeno o WHERE o.idAplicacion.id=:i AND o.estado='1'");
        Q.setParameter("i", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public Object getCountFinalizacionO(int i) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT COUNT(o.id) FROM UceFinalizacionesOxigeno o WHERE o.idAplicacion.id=:ia AND o.estado='1'");
        Q.setParameter("ia", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }
}
