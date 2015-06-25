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
import entidades_EJB.UceCtc;
import entidades_EJB.UceCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceCtcProcedimientosJpaController implements Serializable {

    public UceCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceCtcProcedimientos uceCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtc idctc = uceCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uceCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(uceCtcProcedimientos);
            if (idctc != null) {
                idctc.getUceCtcProcedimientosSet().add(uceCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceCtcProcedimientos uceCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtcProcedimientos persistentUceCtcProcedimientos = em.find(UceCtcProcedimientos.class, uceCtcProcedimientos.getId());
            UceCtc idctcOld = persistentUceCtcProcedimientos.getIdctc();
            UceCtc idctcNew = uceCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uceCtcProcedimientos.setIdctc(idctcNew);
            }
            uceCtcProcedimientos = em.merge(uceCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUceCtcProcedimientosSet().remove(uceCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUceCtcProcedimientosSet().add(uceCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceCtcProcedimientos.getId();
                if (findUceCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The uceCtcProcedimientos with id " + id + " no longer exists.");
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
            UceCtcProcedimientos uceCtcProcedimientos;
            try {
                uceCtcProcedimientos = em.getReference(UceCtcProcedimientos.class, id);
                uceCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UceCtc idctc = uceCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getUceCtcProcedimientosSet().remove(uceCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(uceCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceCtcProcedimientos> findUceCtcProcedimientosEntities() {
        return findUceCtcProcedimientosEntities(true, -1, -1);
    }

    public List<UceCtcProcedimientos> findUceCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findUceCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UceCtcProcedimientos> findUceCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceCtcProcedimientos.class));
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

    public UceCtcProcedimientos findUceCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceCtcProcedimientos> rt = cq.from(UceCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
