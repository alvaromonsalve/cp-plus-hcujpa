/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.EnfuTransfusiones;
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
public class EnfuTransfusionesJpaController implements Serializable {

    public EnfuTransfusionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuTransfusiones enfuTransfusiones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuTransfusiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuTransfusiones enfuTransfusiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuTransfusiones = em.merge(enfuTransfusiones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuTransfusiones.getId();
                if (findEnfuTransfusiones(id) == null) {
                    throw new NonexistentEntityException("The enfuTransfusiones with id " + id + " no longer exists.");
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
            EnfuTransfusiones enfuTransfusiones;
            try {
                enfuTransfusiones = em.getReference(EnfuTransfusiones.class, id);
                enfuTransfusiones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuTransfusiones with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuTransfusiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuTransfusiones> findEnfuTransfusionesEntities() {
        return findEnfuTransfusionesEntities(true, -1, -1);
    }

    public List<EnfuTransfusiones> findEnfuTransfusionesEntities(int maxResults, int firstResult) {
        return findEnfuTransfusionesEntities(false, maxResults, firstResult);
    }

    private List<EnfuTransfusiones> findEnfuTransfusionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuTransfusiones.class));
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

    public EnfuTransfusiones findEnfuTransfusiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuTransfusiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuTransfusionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuTransfusiones> rt = cq.from(EnfuTransfusiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
