/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospFactsLiquidos;
import entidades_EJB.HospHistoriac;
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
public class HospFactsLiquidosJpaController implements Serializable {

    public HospFactsLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospFactsLiquidos hospFactsLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospFactsLiquidos hospFactsLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospFactsLiquidos = em.merge(hospFactsLiquidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospFactsLiquidos.getId();
                if (findHospFactsLiquidos(id) == null) {
                    throw new NonexistentEntityException("The hospFactsLiquidos with id " + id + " no longer exists.");
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
            HospFactsLiquidos hospFactsLiquidos;
            try {
                hospFactsLiquidos = em.getReference(HospFactsLiquidos.class, id);
                hospFactsLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospFactsLiquidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospFactsLiquidos> findHospFactsLiquidosEntities() {
        return findHospFactsLiquidosEntities(true, -1, -1);
    }

    public List<HospFactsLiquidos> findHospFactsLiquidosEntities(int maxResults, int firstResult) {
        return findHospFactsLiquidosEntities(false, maxResults, firstResult);
    }

    private List<HospFactsLiquidos> findHospFactsLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospFactsLiquidos.class));
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

    public HospFactsLiquidos findHospFactsLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospFactsLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospFactsLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospFactsLiquidos> rt = cq.from(HospFactsLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HospFactsLiquidos> getControlesLiquidos(HospHistoriac hc) {
        Query q = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT c FROM HospFactsLiquidos c WHERE c.idHistoria=:h AND c.estado='1'");
        q.setParameter("h", hc);
        return q.getResultList();
    }

}
