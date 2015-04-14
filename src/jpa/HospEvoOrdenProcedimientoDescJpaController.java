/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospEvoOrdenProcedimientoDesc;
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
public class HospEvoOrdenProcedimientoDescJpaController implements Serializable {

    public HospEvoOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEvoOrdenProcedimientoDesc hospEvoOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEvoOrdenProcedimientoDesc hospEvoOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospEvoOrdenProcedimientoDesc = em.merge(hospEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEvoOrdenProcedimientoDesc.getId();
                if (findHospEvoOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The hospEvoOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            HospEvoOrdenProcedimientoDesc hospEvoOrdenProcedimientoDesc;
            try {
                hospEvoOrdenProcedimientoDesc = em.getReference(HospEvoOrdenProcedimientoDesc.class, id);
                hospEvoOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEvoOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEvoOrdenProcedimientoDesc> findHospEvoOrdenProcedimientoDescEntities() {
        return findHospEvoOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<HospEvoOrdenProcedimientoDesc> findHospEvoOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findHospEvoOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<HospEvoOrdenProcedimientoDesc> findHospEvoOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEvoOrdenProcedimientoDesc.class));
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

    public HospEvoOrdenProcedimientoDesc findHospEvoOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEvoOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEvoOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEvoOrdenProcedimientoDesc> rt = cq.from(HospEvoOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
