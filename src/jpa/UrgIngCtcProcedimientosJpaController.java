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
import entidades_EJB.UrgIngCtc;
import entidades_EJB.UrgIngCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngCtcProcedimientosJpaController implements Serializable {

    public UrgIngCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngCtcProcedimientos urgIngCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtc idctc = urgIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                urgIngCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(urgIngCtcProcedimientos);
            if (idctc != null) {
                idctc.getUrgIngCtcProcedimientosList().add(urgIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngCtcProcedimientos urgIngCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtcProcedimientos persistentUrgIngCtcProcedimientos = em.find(UrgIngCtcProcedimientos.class, urgIngCtcProcedimientos.getId());
            UrgIngCtc idctcOld = persistentUrgIngCtcProcedimientos.getIdctc();
            UrgIngCtc idctcNew = urgIngCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                urgIngCtcProcedimientos.setIdctc(idctcNew);
            }
            urgIngCtcProcedimientos = em.merge(urgIngCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUrgIngCtcProcedimientosList().remove(urgIngCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUrgIngCtcProcedimientosList().add(urgIngCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngCtcProcedimientos.getId();
                if (findUrgIngCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The urgIngCtcProcedimientos with id " + id + " no longer exists.");
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
            UrgIngCtcProcedimientos urgIngCtcProcedimientos;
            try {
                urgIngCtcProcedimientos = em.getReference(UrgIngCtcProcedimientos.class, id);
                urgIngCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UrgIngCtc idctc = urgIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getUrgIngCtcProcedimientosList().remove(urgIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(urgIngCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngCtcProcedimientos> findUrgIngCtcProcedimientosEntities() {
        return findUrgIngCtcProcedimientosEntities(true, -1, -1);
    }

    public List<UrgIngCtcProcedimientos> findUrgIngCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findUrgIngCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UrgIngCtcProcedimientos> findUrgIngCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngCtcProcedimientos.class));
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

    public UrgIngCtcProcedimientos findUrgIngCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngCtcProcedimientos> rt = cq.from(UrgIngCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
