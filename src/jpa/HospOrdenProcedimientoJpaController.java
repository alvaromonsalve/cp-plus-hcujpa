/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospOrdenProcedimiento;
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
public class HospOrdenProcedimientoJpaController implements Serializable {

    public HospOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospOrdenProcedimiento hospOrdenProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospOrdenProcedimiento hospOrdenProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospOrdenProcedimiento = em.merge(hospOrdenProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospOrdenProcedimiento.getId();
                if (findHospOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hospOrdenProcedimiento with id " + id + " no longer exists.");
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
            HospOrdenProcedimiento hospOrdenProcedimiento;
            try {
                hospOrdenProcedimiento = em.getReference(HospOrdenProcedimiento.class, id);
                hospOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospOrdenProcedimiento> findHospOrdenProcedimientoEntities() {
        return findHospOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<HospOrdenProcedimiento> findHospOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findHospOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HospOrdenProcedimiento> findHospOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospOrdenProcedimiento.class));
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

    public HospOrdenProcedimiento findHospOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospOrdenProcedimiento> rt = cq.from(HospOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
