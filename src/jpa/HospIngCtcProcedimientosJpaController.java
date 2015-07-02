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
import entidades_EJB.HospIngCtc;
import entidades_EJB.HospIngCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngCtcProcedimientosJpaController implements Serializable {

    public HospIngCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngCtcProcedimientos hospIngCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtc idctc = hospIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                hospIngCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(hospIngCtcProcedimientos);
            if (idctc != null) {
                idctc.getHospIngCtcProcedimientosList().add(hospIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngCtcProcedimientos hospIngCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtcProcedimientos persistentHospIngCtcProcedimientos = em.find(HospIngCtcProcedimientos.class, hospIngCtcProcedimientos.getId());
            HospIngCtc idctcOld = persistentHospIngCtcProcedimientos.getIdctc();
            HospIngCtc idctcNew = hospIngCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                hospIngCtcProcedimientos.setIdctc(idctcNew);
            }
            hospIngCtcProcedimientos = em.merge(hospIngCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getHospIngCtcProcedimientosList().remove(hospIngCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getHospIngCtcProcedimientosList().add(hospIngCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngCtcProcedimientos.getId();
                if (findHospIngCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The hospIngCtcProcedimientos with id " + id + " no longer exists.");
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
            HospIngCtcProcedimientos hospIngCtcProcedimientos;
            try {
                hospIngCtcProcedimientos = em.getReference(HospIngCtcProcedimientos.class, id);
                hospIngCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            HospIngCtc idctc = hospIngCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getHospIngCtcProcedimientosList().remove(hospIngCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(hospIngCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngCtcProcedimientos> findHospIngCtcProcedimientosEntities() {
        return findHospIngCtcProcedimientosEntities(true, -1, -1);
    }

    public List<HospIngCtcProcedimientos> findHospIngCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findHospIngCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<HospIngCtcProcedimientos> findHospIngCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngCtcProcedimientos.class));
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

    public HospIngCtcProcedimientos findHospIngCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngCtcProcedimientos> rt = cq.from(HospIngCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
