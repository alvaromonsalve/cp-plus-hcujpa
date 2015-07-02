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
import entidades_EJB.UciIngCtc;
import entidades_EJB.UciIngCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngCtcProcedimientosJpaController implements Serializable {

    public UciIngCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngCtcProcedimientos uciIngCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtc idctc = uciIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uciIngCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(uciIngCtcProcedimientos);
            if (idctc != null) {
                idctc.getUciIngCtcProcedimientosList().add(uciIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngCtcProcedimientos uciIngCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtcProcedimientos persistentUciIngCtcProcedimientos = em.find(UciIngCtcProcedimientos.class, uciIngCtcProcedimientos.getId());
            UciIngCtc idctcOld = persistentUciIngCtcProcedimientos.getIdctc();
            UciIngCtc idctcNew = uciIngCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uciIngCtcProcedimientos.setIdctc(idctcNew);
            }
            uciIngCtcProcedimientos = em.merge(uciIngCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUciIngCtcProcedimientosList().remove(uciIngCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUciIngCtcProcedimientosList().add(uciIngCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngCtcProcedimientos.getId();
                if (findUciIngCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The uciIngCtcProcedimientos with id " + id + " no longer exists.");
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
            UciIngCtcProcedimientos uciIngCtcProcedimientos;
            try {
                uciIngCtcProcedimientos = em.getReference(UciIngCtcProcedimientos.class, id);
                uciIngCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UciIngCtc idctc = uciIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getUciIngCtcProcedimientosList().remove(uciIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(uciIngCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngCtcProcedimientos> findUciIngCtcProcedimientosEntities() {
        return findUciIngCtcProcedimientosEntities(true, -1, -1);
    }

    public List<UciIngCtcProcedimientos> findUciIngCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findUciIngCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UciIngCtcProcedimientos> findUciIngCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngCtcProcedimientos.class));
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

    public UciIngCtcProcedimientos findUciIngCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngCtcProcedimientos> rt = cq.from(UciIngCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
