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
import entidades_EJB.HospCtc;
import entidades_EJB.HospCtcProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospCtcProcedimientosJpaController implements Serializable {

    public HospCtcProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospCtcProcedimientos hospCtcProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtc idctc = hospCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                hospCtcProcedimientos.setIdctc(idctc);
            }
            em.persist(hospCtcProcedimientos);
            if (idctc != null) {
                idctc.getHospCtcProcedimientosList().add(hospCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospCtcProcedimientos hospCtcProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtcProcedimientos persistentHospCtcProcedimientos = em.find(HospCtcProcedimientos.class, hospCtcProcedimientos.getId());
            HospCtc idctcOld = persistentHospCtcProcedimientos.getIdctc();
            HospCtc idctcNew = hospCtcProcedimientos.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                hospCtcProcedimientos.setIdctc(idctcNew);
            }
            hospCtcProcedimientos = em.merge(hospCtcProcedimientos);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getHospCtcProcedimientosList().remove(hospCtcProcedimientos);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getHospCtcProcedimientosList().add(hospCtcProcedimientos);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospCtcProcedimientos.getId();
                if (findHospCtcProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The hospCtcProcedimientos with id " + id + " no longer exists.");
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
            HospCtcProcedimientos hospCtcProcedimientos;
            try {
                hospCtcProcedimientos = em.getReference(HospCtcProcedimientos.class, id);
                hospCtcProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospCtcProcedimientos with id " + id + " no longer exists.", enfe);
            }
            HospCtc idctc = hospCtcProcedimientos.getIdctc();
            if (idctc != null) {
                idctc.getHospCtcProcedimientosList().remove(hospCtcProcedimientos);
                idctc = em.merge(idctc);
            }
            em.remove(hospCtcProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospCtcProcedimientos> findHospCtcProcedimientosEntities() {
        return findHospCtcProcedimientosEntities(true, -1, -1);
    }

    public List<HospCtcProcedimientos> findHospCtcProcedimientosEntities(int maxResults, int firstResult) {
        return findHospCtcProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<HospCtcProcedimientos> findHospCtcProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospCtcProcedimientos.class));
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

    public HospCtcProcedimientos findHospCtcProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospCtcProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospCtcProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospCtcProcedimientos> rt = cq.from(HospCtcProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
