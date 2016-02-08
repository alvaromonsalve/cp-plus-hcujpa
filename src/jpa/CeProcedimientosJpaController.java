/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeHistoriac;
import entidades_EJB.CeProcedimientos;
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
public class CeProcedimientosJpaController implements Serializable {

    public CeProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeProcedimientos ceProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeProcedimientos ceProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceProcedimientos = em.merge(ceProcedimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceProcedimientos.getId();
                if (findCeProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The ceProcedimientos with id " + id + " no longer exists.");
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
            CeProcedimientos ceProcedimientos;
            try {
                ceProcedimientos = em.getReference(CeProcedimientos.class, id);
                ceProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceProcedimientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeProcedimientos> findCeProcedimientosEntities() {
        return findCeProcedimientosEntities(true, -1, -1);
    }

    public List<CeProcedimientos> findCeProcedimientosEntities(int maxResults, int firstResult) {
        return findCeProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<CeProcedimientos> findCeProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeProcedimientos.class));
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

    public CeProcedimientos findCeProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeProcedimientos> rt = cq.from(CeProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeProcedimientos> getProcedimientos(CeHistoriac h) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT p FROM CeProcedimientos p WHERE p.idHistoriace=:hi AND p.estado='1'");
        q.setParameter("hi", h); //Lunes 9 de Febrero de 2015 - 05:45 PM
        return q.getResultList();
    }
}
