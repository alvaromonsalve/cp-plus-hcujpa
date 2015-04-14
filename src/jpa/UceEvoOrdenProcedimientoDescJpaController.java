/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceEvoOrdenProcedimientoDesc;
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
public class UceEvoOrdenProcedimientoDescJpaController implements Serializable {

    public UceEvoOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEvoOrdenProcedimientoDesc uceEvoOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEvoOrdenProcedimientoDesc uceEvoOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceEvoOrdenProcedimientoDesc = em.merge(uceEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEvoOrdenProcedimientoDesc.getId();
                if (findUceEvoOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The uceEvoOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            UceEvoOrdenProcedimientoDesc uceEvoOrdenProcedimientoDesc;
            try {
                uceEvoOrdenProcedimientoDesc = em.getReference(UceEvoOrdenProcedimientoDesc.class, id);
                uceEvoOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEvoOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEvoOrdenProcedimientoDesc> findUceEvoOrdenProcedimientoDescEntities() {
        return findUceEvoOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<UceEvoOrdenProcedimientoDesc> findUceEvoOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findUceEvoOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<UceEvoOrdenProcedimientoDesc> findUceEvoOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEvoOrdenProcedimientoDesc.class));
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

    public UceEvoOrdenProcedimientoDesc findUceEvoOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEvoOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEvoOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEvoOrdenProcedimientoDesc> rt = cq.from(UceEvoOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
