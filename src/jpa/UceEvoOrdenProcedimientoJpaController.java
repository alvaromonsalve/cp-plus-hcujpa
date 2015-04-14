/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceEvoOrdenProcedimiento;
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
public class UceEvoOrdenProcedimientoJpaController implements Serializable {

    public UceEvoOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoOrdenProcedimiento uceEvoOrdenProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoOrdenProcedimiento uceEvoOrdenProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceEvoOrdenProcedimiento = em.merge(uceEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoOrdenProcedimiento.getId();
                if (findUceEvoOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uceEvoOrdenProcedimiento with id " + id + " no longer exists.");
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
            UceEvoOrdenProcedimiento uceEvoOrdenProcedimiento;
            try {
                uceEvoOrdenProcedimiento = em.getReference(UceEvoOrdenProcedimiento.class, id);
                uceEvoOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoOrdenProcedimiento> findUceEvoOrdenProcedimientoEntities() {
        return findUceEvoOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<UceEvoOrdenProcedimiento> findUceEvoOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findUceEvoOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UceEvoOrdenProcedimiento> findUceEvoOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoOrdenProcedimiento.class));
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

    public UceEvoOrdenProcedimiento findUceEvoOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoOrdenProcedimiento> rt = cq.from(UceEvoOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
