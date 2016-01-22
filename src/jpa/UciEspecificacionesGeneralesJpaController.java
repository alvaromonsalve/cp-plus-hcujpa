/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciEspecificacionesGenerales;
import entidades_EJB.UciFactsNotas;
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
public class UciEspecificacionesGeneralesJpaController implements Serializable {

    public UciEspecificacionesGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEspecificacionesGenerales uciEspecificacionesGenerales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciEspecificacionesGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEspecificacionesGenerales uciEspecificacionesGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciEspecificacionesGenerales = em.merge(uciEspecificacionesGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEspecificacionesGenerales.getId();
                if (findUciEspecificacionesGenerales(id) == null) {
                    throw new NonexistentEntityException("The uciEspecificacionesGenerales with id " + id + " no longer exists.");
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
            UciEspecificacionesGenerales uciEspecificacionesGenerales;
            try {
                uciEspecificacionesGenerales = em.getReference(UciEspecificacionesGenerales.class, id);
                uciEspecificacionesGenerales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEspecificacionesGenerales with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciEspecificacionesGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEspecificacionesGenerales> findUciEspecificacionesGeneralesEntities() {
        return findUciEspecificacionesGeneralesEntities(true, -1, -1);
    }

    public List<UciEspecificacionesGenerales> findUciEspecificacionesGeneralesEntities(int maxResults, int firstResult) {
        return findUciEspecificacionesGeneralesEntities(false, maxResults, firstResult);
    }

    private List<UciEspecificacionesGenerales> findUciEspecificacionesGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEspecificacionesGenerales.class));
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

    public UciEspecificacionesGenerales findUciEspecificacionesGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEspecificacionesGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEspecificacionesGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEspecificacionesGenerales> rt = cq.from(UciEspecificacionesGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UciEspecificacionesGenerales getEspecificaciones(UciFactsNotas n) {
        UciEspecificacionesGenerales espgen;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT es FROM UciEspecificacionesGenerales es WHERE es.idFactsNotas=:no AND es.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            espgen = (UciEspecificacionesGenerales) results.get(0);
        } else {
            espgen = null;
        }
        return espgen;
    }
}
