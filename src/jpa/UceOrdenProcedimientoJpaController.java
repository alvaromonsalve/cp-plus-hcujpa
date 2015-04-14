/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceOrdenProcedimiento;
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
public class UceOrdenProcedimientoJpaController implements Serializable {

    public UceOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceOrdenProcedimiento uceOrdenProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceOrdenProcedimiento uceOrdenProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceOrdenProcedimiento = em.merge(uceOrdenProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceOrdenProcedimiento.getId();
                if (findUceOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The uceOrdenProcedimiento with id " + id + " no longer exists.");
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
            UceOrdenProcedimiento uceOrdenProcedimiento;
            try {
                uceOrdenProcedimiento = em.getReference(UceOrdenProcedimiento.class, id);
                uceOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceOrdenProcedimiento> findUceOrdenProcedimientoEntities() {
        return findUceOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<UceOrdenProcedimiento> findUceOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findUceOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<UceOrdenProcedimiento> findUceOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceOrdenProcedimiento.class));
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

    public UceOrdenProcedimiento findUceOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceOrdenProcedimiento> rt = cq.from(UceOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
