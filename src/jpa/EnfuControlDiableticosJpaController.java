/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.EnfuControlDiableticos;
import entidades_EJB.InfoHistoriac;
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
public class EnfuControlDiableticosJpaController implements Serializable {

    public EnfuControlDiableticosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuControlDiableticos enfuControlDiableticos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuControlDiableticos enfuControlDiableticos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuControlDiableticos = em.merge(enfuControlDiableticos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuControlDiableticos.getId();
                if (findEnfuControlDiableticos(id) == null) {
                    throw new NonexistentEntityException("The enfuControlDiableticos with id " + id + " no longer exists.");
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
            EnfuControlDiableticos enfuControlDiableticos;
            try {
                enfuControlDiableticos = em.getReference(EnfuControlDiableticos.class, id);
                enfuControlDiableticos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuControlDiableticos with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuControlDiableticos> findEnfuControlDiableticosEntities() {
        return findEnfuControlDiableticosEntities(true, -1, -1);
    }

    public List<EnfuControlDiableticos> findEnfuControlDiableticosEntities(int maxResults, int firstResult) {
        return findEnfuControlDiableticosEntities(false, maxResults, firstResult);
    }

    private List<EnfuControlDiableticos> findEnfuControlDiableticosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuControlDiableticos.class));
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

    public EnfuControlDiableticos findEnfuControlDiableticos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuControlDiableticos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuControlDiableticosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuControlDiableticos> rt = cq.from(EnfuControlDiableticos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuControlDiableticos> find_Hist_Diabetics(InfoHistoriac hc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT d FROM EnfuControlDiableticos d WHERE d.idHistoria=:hist AND d.estado='1'");
        Q.setParameter("hist", hc);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
    
}
