/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceControlDiableticos;
import entidades_EJB.UceHistoriac;
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
public class UceControlDiableticosJpaController implements Serializable {

    public UceControlDiableticosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceControlDiableticos uceControlDiableticos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceControlDiableticos uceControlDiableticos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceControlDiableticos = em.merge(uceControlDiableticos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceControlDiableticos.getId();
                if (findUceControlDiableticos(id) == null) {
                    throw new NonexistentEntityException("The uceControlDiableticos with id " + id + " no longer exists.");
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
            UceControlDiableticos uceControlDiableticos;
            try {
                uceControlDiableticos = em.getReference(UceControlDiableticos.class, id);
                uceControlDiableticos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceControlDiableticos with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceControlDiableticos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceControlDiableticos> findUceControlDiableticosEntities() {
        return findUceControlDiableticosEntities(true, -1, -1);
    }

    public List<UceControlDiableticos> findUceControlDiableticosEntities(int maxResults, int firstResult) {
        return findUceControlDiableticosEntities(false, maxResults, firstResult);
    }

    private List<UceControlDiableticos> findUceControlDiableticosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceControlDiableticos.class));
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

    public UceControlDiableticos findUceControlDiableticos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceControlDiableticos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceControlDiableticosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceControlDiableticos> rt = cq.from(UceControlDiableticos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<UceControlDiableticos>find_Hist_Diabetics(UceHistoriac hc){
        EntityManager em = getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT d FROM UceControlDiableticos d WHERE d.idHistoria=:hist AND d.estado='1'  ORDER BY d.fecha, d.hora ASC");
        Q.setParameter("hist", hc);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
