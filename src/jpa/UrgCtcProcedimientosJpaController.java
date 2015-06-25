/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgCtc;
import entidades_EJB.UrgCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgCtcProcedimientosJpaController implements Serializable {

    public UrgCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgCtcProcedimientos urgCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtc idctc = urgCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                urgCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(urgCtcProcedimientos);
            if (idctc != null) {
                idctc.getUrgCtcProcedimientosSet().add(urgCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgCtcProcedimientos urgCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtcProcedimientos persistentUrgCtcProcedimientos = em.find(UrgCtcProcedimientos.class, urgCtcProcedimientos.getId());
            UrgCtc idctcOld = persistentUrgCtcProcedimientos.getIdctc();
            UrgCtc idctcNew = urgCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                urgCtcProcedimientos.setIdctc(idctcNew);
            }
            urgCtcProcedimientos = em.merge(urgCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUrgCtcProcedimientosSet().remove(urgCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUrgCtcProcedimientosSet().add(urgCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgCtcProcedimientos.getId();
                if (findUrgCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The urgCtcProcedimientos with id " + id + " no longer exists.");
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
            UrgCtcProcedimientos urgCtcProcedimientos;
            try {
                urgCtcProcedimientos = em.getReference(UrgCtcProcedimientos.class, id);
                urgCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UrgCtc idctc = urgCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getUrgCtcProcedimientosSet().remove(urgCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(urgCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgCtcProcedimientos> findUrgCtcProcedimientosEntities() {
        return findUrgCtcProcedimientosEntities(true, -1, -1);
    }

    public List<UrgCtcProcedimientos> findUrgCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findUrgCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UrgCtcProcedimientos> findUrgCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgCtcProcedimientos.class));
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

    public UrgCtcProcedimientos findUrgCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgCtcProcedimientos> rt = cq.from(UrgCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
