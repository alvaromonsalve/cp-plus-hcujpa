/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeHistoriac;
import entidades_EJB.CePosologia;
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
public class CePosologiaJpaController implements Serializable {

    public CePosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CePosologia cePosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cePosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CePosologia cePosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cePosologia = em.merge(cePosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cePosologia.getId();
                if (findCePosologia(id) == null) {
                    throw new NonexistentEntityException("The cePosologia with id " + id + " no longer exists.");
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
            CePosologia cePosologia;
            try {
                cePosologia = em.getReference(CePosologia.class, id);
                cePosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cePosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(cePosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CePosologia> findCePosologiaEntities() {
        return findCePosologiaEntities(true, -1, -1);
    }

    public List<CePosologia> findCePosologiaEntities(int maxResults, int firstResult) {
        return findCePosologiaEntities(false, maxResults, firstResult);
    }

    private List<CePosologia> findCePosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CePosologia.class));
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

    public CePosologia findCePosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CePosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCePosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CePosologia> rt = cq.from(CePosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CePosologia> getPosologia(CeHistoriac hist) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT p FROM CePosologia p WHERE p.idHistoriace=:h AND p.estado='1'");
        q.setParameter("h", hist); // Lunes 9 de Febrero 2015 - 11:54 AM
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return q.getResultList();
    }
}
