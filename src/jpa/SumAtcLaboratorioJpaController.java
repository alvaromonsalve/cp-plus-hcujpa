/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.SumAtcLaboratorio;
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
public class SumAtcLaboratorioJpaController implements Serializable {

    public SumAtcLaboratorioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcLaboratorio sumAtcLaboratorio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sumAtcLaboratorio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcLaboratorio sumAtcLaboratorio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sumAtcLaboratorio = em.merge(sumAtcLaboratorio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcLaboratorio.getId();
                if (findSumAtcLaboratorio(id) == null) {
                    throw new NonexistentEntityException("The sumAtcLaboratorio with id " + id + " no longer exists.");
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
            SumAtcLaboratorio sumAtcLaboratorio;
            try {
                sumAtcLaboratorio = em.getReference(SumAtcLaboratorio.class, id);
                sumAtcLaboratorio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcLaboratorio with id " + id + " no longer exists.", enfe);
            }
            em.remove(sumAtcLaboratorio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcLaboratorio> findSumAtcLaboratorioEntities() {
        return findSumAtcLaboratorioEntities(true, -1, -1);
    }

    public List<SumAtcLaboratorio> findSumAtcLaboratorioEntities(int maxResults, int firstResult) {
        return findSumAtcLaboratorioEntities(false, maxResults, firstResult);
    }

    private List<SumAtcLaboratorio> findSumAtcLaboratorioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcLaboratorio.class));
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

    public SumAtcLaboratorio findSumAtcLaboratorio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcLaboratorio.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcLaboratorioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcLaboratorio> rt = cq.from(SumAtcLaboratorio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
