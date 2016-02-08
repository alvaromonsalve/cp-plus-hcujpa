/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeHistoriac;
import entidades_EJB.CeRecomendaciones;
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
public class CeRecomendacionesJpaController implements Serializable {

    public CeRecomendacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeRecomendaciones ceRecomendaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceRecomendaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeRecomendaciones ceRecomendaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceRecomendaciones = em.merge(ceRecomendaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceRecomendaciones.getId();
                if (findCeRecomendaciones(id) == null) {
                    throw new NonexistentEntityException("The ceRecomendaciones with id " + id + " no longer exists.");
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
            CeRecomendaciones ceRecomendaciones;
            try {
                ceRecomendaciones = em.getReference(CeRecomendaciones.class, id);
                ceRecomendaciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceRecomendaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceRecomendaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeRecomendaciones> findCeRecomendacionesEntities() {
        return findCeRecomendacionesEntities(true, -1, -1);
    }

    public List<CeRecomendaciones> findCeRecomendacionesEntities(int maxResults, int firstResult) {
        return findCeRecomendacionesEntities(false, maxResults, firstResult);
    }

    private List<CeRecomendaciones> findCeRecomendacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeRecomendaciones.class));
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

    public CeRecomendaciones findCeRecomendaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeRecomendaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeRecomendacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeRecomendaciones> rt = cq.from(CeRecomendaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public CeRecomendaciones getEntidadRecomendacion(CeHistoriac historia) {
        CeRecomendaciones rec = null;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT r FROM CeRecomendaciones r WHERE r.idHistoriace=:hc AND r.estado='1'");
        Q.setParameter("hc", historia);
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            rec = (CeRecomendaciones) results.get(0);
        } else {
            rec = null;
        }
        return rec;
    }
}
