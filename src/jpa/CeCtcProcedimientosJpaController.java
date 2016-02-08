/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeCtc;
import entidades_EJB.CeCtcProcedimientos;
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
public class CeCtcProcedimientosJpaController implements Serializable {

    public CeCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeCtcProcedimientos ceCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeCtcProcedimientos ceCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceCtcProcedimientos = em.merge(ceCtcProcedimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceCtcProcedimientos.getId();
                if (findCeCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The ceCtcProcedimientos with id " + id + " no longer exists.");
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
            CeCtcProcedimientos ceCtcProcedimientos;
            try {
                ceCtcProcedimientos = em.getReference(CeCtcProcedimientos.class, id);
                ceCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeCtcProcedimientos> findCeCtcProcedimientosEntities() {
        return findCeCtcProcedimientosEntities(true, -1, -1);
    }

    public List<CeCtcProcedimientos> findCeCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findCeCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<CeCtcProcedimientos> findCeCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeCtcProcedimientos.class));
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

    public CeCtcProcedimientos findCeCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeCtcProcedimientos> rt = cq.from(CeCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeCtcProcedimientos> getProcedimientosNOPOS(CeCtc c) {
        Query q = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT p FROM CeCtcProcedimientos p WHERE p.idctc=:ctc AND (p.estado='1' AND p.tipo='1')");
        q.setParameter("ctc", c);
        return q.getResultList();
    }
}
