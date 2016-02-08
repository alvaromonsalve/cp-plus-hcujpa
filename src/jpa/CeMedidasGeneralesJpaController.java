/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeHistoriac;
import entidades_EJB.CeMedidasGenerales;
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
public class CeMedidasGeneralesJpaController implements Serializable {

    public CeMedidasGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeMedidasGenerales ceMedidasGenerales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceMedidasGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeMedidasGenerales ceMedidasGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceMedidasGenerales = em.merge(ceMedidasGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceMedidasGenerales.getId();
                if (findCeMedidasGenerales(id) == null) {
                    throw new NonexistentEntityException("The ceMedidasGenerales with id " + id + " no longer exists.");
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
            CeMedidasGenerales ceMedidasGenerales;
            try {
                ceMedidasGenerales = em.getReference(CeMedidasGenerales.class, id);
                ceMedidasGenerales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceMedidasGenerales with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceMedidasGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeMedidasGenerales> findCeMedidasGeneralesEntities() {
        return findCeMedidasGeneralesEntities(true, -1, -1);
    }

    public List<CeMedidasGenerales> findCeMedidasGeneralesEntities(int maxResults, int firstResult) {
        return findCeMedidasGeneralesEntities(false, maxResults, firstResult);
    }

    private List<CeMedidasGenerales> findCeMedidasGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeMedidasGenerales.class));
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

    public CeMedidasGenerales findCeMedidasGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeMedidasGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeMedidasGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeMedidasGenerales> rt = cq.from(CeMedidasGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeMedidasGenerales> getMedidas(CeHistoriac h) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT m FROM CeMedidasGenerales m WHERE m.idHistorice=:ht AND m.estado='1'");
        q.setParameter("ht", h);
        return q.getResultList();
    }
}
