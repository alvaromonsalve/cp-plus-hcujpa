/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciAplicacionesOxigeno;
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
public class UciAplicacionesOxigenoJpaController implements Serializable {

    public UciAplicacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAplicacionesOxigeno uciAplicacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAplicacionesOxigeno uciAplicacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciAplicacionesOxigeno = em.merge(uciAplicacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAplicacionesOxigeno.getId();
                if (findUciAplicacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The uciAplicacionesOxigeno with id " + id + " no longer exists.");
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
            UciAplicacionesOxigeno uciAplicacionesOxigeno;
            try {
                uciAplicacionesOxigeno = em.getReference(UciAplicacionesOxigeno.class, id);
                uciAplicacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAplicacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAplicacionesOxigeno> findUciAplicacionesOxigenoEntities() {
        return findUciAplicacionesOxigenoEntities(true, -1, -1);
    }

    public List<UciAplicacionesOxigeno> findUciAplicacionesOxigenoEntities(int maxResults, int firstResult) {
        return findUciAplicacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<UciAplicacionesOxigeno> findUciAplicacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAplicacionesOxigeno.class));
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

    public UciAplicacionesOxigeno findUciAplicacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAplicacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAplicacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAplicacionesOxigeno> rt = cq.from(UciAplicacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
