/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospControlDiableticos;
import entidades_EJB.HospHistoriac;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author JUDMEZ
 */
public class HospControlDiableticosJpaController implements Serializable {

    public HospControlDiableticosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospControlDiableticos hospControlDiableticos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospControlDiableticos hospControlDiableticos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospControlDiableticos = em.merge(hospControlDiableticos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospControlDiableticos.getId();
                if (findHospControlDiableticos(id) == null) {
                    throw new NonexistentEntityException("The hospControlDiableticos with id " + id + " no longer exists.");
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
            HospControlDiableticos hospControlDiableticos;
            try {
                hospControlDiableticos = em.getReference(HospControlDiableticos.class, id);
                hospControlDiableticos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospControlDiableticos with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospControlDiableticos> findHospControlDiableticosEntities() {
        return findHospControlDiableticosEntities(true, -1, -1);
    }

    public List<HospControlDiableticos> findHospControlDiableticosEntities(int maxResults, int firstResult) {
        return findHospControlDiableticosEntities(false, maxResults, firstResult);
    }

    private List<HospControlDiableticos> findHospControlDiableticosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospControlDiableticos.class));
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

    public HospControlDiableticos findHospControlDiableticos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospControlDiableticos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospControlDiableticosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospControlDiableticos> rt = cq.from(HospControlDiableticos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<HospControlDiableticos>find_Hist_Diabetics(HospHistoriac hc){
        EntityManager em = getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT d FROM HospControlDiableticos d WHERE d.idHistoria=:hist AND d.estado='1'");
        Q.setParameter("hist", hc);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
