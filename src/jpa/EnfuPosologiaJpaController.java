/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.EnfuPosologia;
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
 * @author Alvaro Monsalve
 */
public class EnfuPosologiaJpaController implements Serializable {

    public EnfuPosologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuPosologia enfuPosologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuPosologia enfuPosologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuPosologia = em.merge(enfuPosologia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuPosologia.getId();
                if (findEnfuPosologia(id) == null) {
                    throw new NonexistentEntityException("The enfuPosologia with id " + id + " no longer exists.");
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
            EnfuPosologia enfuPosologia;
            try {
                enfuPosologia = em.getReference(EnfuPosologia.class, id);
                enfuPosologia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuPosologia with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuPosologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuPosologia> findEnfuPosologiaEntities() {
        return findEnfuPosologiaEntities(true, -1, -1);
    }

    public List<EnfuPosologia> findEnfuPosologiaEntities(int maxResults, int firstResult) {
        return findEnfuPosologiaEntities(false, maxResults, firstResult);
    }

    private List<EnfuPosologia> findEnfuPosologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuPosologia.class));
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

    public EnfuPosologia findEnfuPosologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuPosologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuPosologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuPosologia> rt = cq.from(EnfuPosologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
