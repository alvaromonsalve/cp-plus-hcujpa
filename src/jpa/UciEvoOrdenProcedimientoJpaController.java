/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciEvoOrdenProcedimiento;
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
 * @author Juan Camilo
 */
public class UciEvoOrdenProcedimientoJpaController implements Serializable {

    public UciEvoOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciEvoOrdenProcedimiento uciEvoOrdenProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciEvoOrdenProcedimiento uciEvoOrdenProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciEvoOrdenProcedimiento = em.merge(uciEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciEvoOrdenProcedimiento.getId();
                if (findUciEvoOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uciEvoOrdenProcedimiento with id " + id + " no longer exists.");
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
            UciEvoOrdenProcedimiento uciEvoOrdenProcedimiento;
            try {
                uciEvoOrdenProcedimiento = em.getReference(UciEvoOrdenProcedimiento.class, id);
                uciEvoOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciEvoOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciEvoOrdenProcedimiento> findUciEvoOrdenProcedimientoEntities() {
        return findUciEvoOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<UciEvoOrdenProcedimiento> findUciEvoOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findUciEvoOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UciEvoOrdenProcedimiento> findUciEvoOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciEvoOrdenProcedimiento.class));
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

    public UciEvoOrdenProcedimiento findUciEvoOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciEvoOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciEvoOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciEvoOrdenProcedimiento> rt = cq.from(UciEvoOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
