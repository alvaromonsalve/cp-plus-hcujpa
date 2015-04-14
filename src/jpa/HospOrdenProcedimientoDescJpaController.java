/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospOrdenProcedimientoDesc;
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
public class HospOrdenProcedimientoDescJpaController implements Serializable {

    public HospOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospOrdenProcedimientoDesc hospOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospOrdenProcedimientoDesc hospOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospOrdenProcedimientoDesc = em.merge(hospOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospOrdenProcedimientoDesc.getId();
                if (findHospOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The hospOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            HospOrdenProcedimientoDesc hospOrdenProcedimientoDesc;
            try {
                hospOrdenProcedimientoDesc = em.getReference(HospOrdenProcedimientoDesc.class, id);
                hospOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospOrdenProcedimientoDesc> findHospOrdenProcedimientoDescEntities() {
        return findHospOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<HospOrdenProcedimientoDesc> findHospOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findHospOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<HospOrdenProcedimientoDesc> findHospOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospOrdenProcedimientoDesc.class));
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

    public HospOrdenProcedimientoDesc findHospOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospOrdenProcedimientoDesc> rt = cq.from(HospOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
