/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceOrdenProcedimientoDesc;
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
public class UceOrdenProcedimientoDescJpaController implements Serializable {

    public UceOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceOrdenProcedimientoDesc uceOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceOrdenProcedimientoDesc uceOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceOrdenProcedimientoDesc = em.merge(uceOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceOrdenProcedimientoDesc.getId();
                if (findUceOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The uceOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            UceOrdenProcedimientoDesc uceOrdenProcedimientoDesc;
            try {
                uceOrdenProcedimientoDesc = em.getReference(UceOrdenProcedimientoDesc.class, id);
                uceOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceOrdenProcedimientoDesc> findUceOrdenProcedimientoDescEntities() {
        return findUceOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<UceOrdenProcedimientoDesc> findUceOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findUceOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<UceOrdenProcedimientoDesc> findUceOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceOrdenProcedimientoDesc.class));
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

    public UceOrdenProcedimientoDesc findUceOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceOrdenProcedimientoDesc> rt = cq.from(UceOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
