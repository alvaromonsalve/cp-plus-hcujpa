/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospEvoOrdenProcedimiento;
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
public class HospEvoOrdenProcedimientoJpaController implements Serializable {

    public HospEvoOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoOrdenProcedimiento hospEvoOrdenProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoOrdenProcedimiento hospEvoOrdenProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospEvoOrdenProcedimiento = em.merge(hospEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoOrdenProcedimiento.getId();
                if (findHospEvoOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hospEvoOrdenProcedimiento with id " + id + " no longer exists.");
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
            HospEvoOrdenProcedimiento hospEvoOrdenProcedimiento;
            try {
                hospEvoOrdenProcedimiento = em.getReference(HospEvoOrdenProcedimiento.class, id);
                hospEvoOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoOrdenProcedimiento> findHospEvoOrdenProcedimientoEntities() {
        return findHospEvoOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<HospEvoOrdenProcedimiento> findHospEvoOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findHospEvoOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HospEvoOrdenProcedimiento> findHospEvoOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoOrdenProcedimiento.class));
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

    public HospEvoOrdenProcedimiento findHospEvoOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoOrdenProcedimiento> rt = cq.from(HospEvoOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
