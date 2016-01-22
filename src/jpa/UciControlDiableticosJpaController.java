/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciControlDiableticos;
import entidades_EJB.UciHistoriac;
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
public class UciControlDiableticosJpaController implements Serializable {

    public UciControlDiableticosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciControlDiableticos uciControlDiableticos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciControlDiableticos uciControlDiableticos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciControlDiableticos = em.merge(uciControlDiableticos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciControlDiableticos.getId();
                if (findUciControlDiableticos(id) == null) {
                    throw new NonexistentEntityException("The uciControlDiableticos with id " + id + " no longer exists.");
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
            UciControlDiableticos uciControlDiableticos;
            try {
                uciControlDiableticos = em.getReference(UciControlDiableticos.class, id);
                uciControlDiableticos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciControlDiableticos with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciControlDiableticos> findUciControlDiableticosEntities() {
        return findUciControlDiableticosEntities(true, -1, -1);
    }

    public List<UciControlDiableticos> findUciControlDiableticosEntities(int maxResults, int firstResult) {
        return findUciControlDiableticosEntities(false, maxResults, firstResult);
    }

    private List<UciControlDiableticos> findUciControlDiableticosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciControlDiableticos.class));
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

    public UciControlDiableticos findUciControlDiableticos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciControlDiableticos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciControlDiableticosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciControlDiableticos> rt = cq.from(UciControlDiableticos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciControlDiableticos> find_Hist_Diabetics(UciHistoriac hc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT d FROM UciControlDiableticos d WHERE d.idHistoria=:hist AND d.estado='1' ORDER BY d.fecha, d.hora ASC");
        Q.setParameter("hist", hc);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
