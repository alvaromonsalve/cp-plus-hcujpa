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
import entidades_EJB.UceIngCtc;
import entidades_EJB.UceIngCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngCtcProcedimientosJpaController implements Serializable {

    public UceIngCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngCtcProcedimientos uceIngCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtc idctc = uceIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uceIngCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(uceIngCtcProcedimientos);
            if (idctc != null) {
                idctc.getUceIngCtcProcedimientosList().add(uceIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngCtcProcedimientos uceIngCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtcProcedimientos persistentUceIngCtcProcedimientos = em.find(UceIngCtcProcedimientos.class, uceIngCtcProcedimientos.getId());
            UceIngCtc idctcOld = persistentUceIngCtcProcedimientos.getIdctc();
            UceIngCtc idctcNew = uceIngCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uceIngCtcProcedimientos.setIdctc(idctcNew);
            }
            uceIngCtcProcedimientos = em.merge(uceIngCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUceIngCtcProcedimientosList().remove(uceIngCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUceIngCtcProcedimientosList().add(uceIngCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngCtcProcedimientos.getId();
                if (findUceIngCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The uceIngCtcProcedimientos with id " + id + " no longer exists.");
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
            UceIngCtcProcedimientos uceIngCtcProcedimientos;
            try {
                uceIngCtcProcedimientos = em.getReference(UceIngCtcProcedimientos.class, id);
                uceIngCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UceIngCtc idctc = uceIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getUceIngCtcProcedimientosList().remove(uceIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(uceIngCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngCtcProcedimientos> findUceIngCtcProcedimientosEntities() {
        return findUceIngCtcProcedimientosEntities(true, -1, -1);
    }

    public List<UceIngCtcProcedimientos> findUceIngCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findUceIngCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UceIngCtcProcedimientos> findUceIngCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngCtcProcedimientos.class));
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

    public UceIngCtcProcedimientos findUceIngCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngCtcProcedimientos> rt = cq.from(UceIngCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
