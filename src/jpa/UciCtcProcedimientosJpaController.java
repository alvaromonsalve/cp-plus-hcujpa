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
import entidades_EJB.UciCtc;
import entidades_EJB.UciCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciCtcProcedimientosJpaController implements Serializable {

    public UciCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciCtcProcedimientos uciCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtc idctc = uciCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uciCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(uciCtcProcedimientos);
            if (idctc != null) {
                idctc.getUciCtcProcedimientosSet().add(uciCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciCtcProcedimientos uciCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtcProcedimientos persistentUciCtcProcedimientos = em.find(UciCtcProcedimientos.class, uciCtcProcedimientos.getId());
            UciCtc idctcOld = persistentUciCtcProcedimientos.getIdctc();
            UciCtc idctcNew = uciCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uciCtcProcedimientos.setIdctc(idctcNew);
            }
            uciCtcProcedimientos = em.merge(uciCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUciCtcProcedimientosSet().remove(uciCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUciCtcProcedimientosSet().add(uciCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciCtcProcedimientos.getId();
                if (findUciCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The uciCtcProcedimientos with id " + id + " no longer exists.");
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
            UciCtcProcedimientos uciCtcProcedimientos;
            try {
                uciCtcProcedimientos = em.getReference(UciCtcProcedimientos.class, id);
                uciCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UciCtc idctc = uciCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getUciCtcProcedimientosSet().remove(uciCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(uciCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciCtcProcedimientos> findUciCtcProcedimientosEntities() {
        return findUciCtcProcedimientosEntities(true, -1, -1);
    }

    public List<UciCtcProcedimientos> findUciCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findUciCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UciCtcProcedimientos> findUciCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciCtcProcedimientos.class));
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

    public UciCtcProcedimientos findUciCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciCtcProcedimientos> rt = cq.from(UciCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
