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
import entidades_EJB.HospSangre;
import entidades_EJB.HospSangreProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospSangreProcedimientosJpaController implements Serializable {

    public HospSangreProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospSangreProcedimientos hospSangreProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSangre idsangre = hospSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                hospSangreProcedimientos.setIdsangre(idsangre);
            }
            em.persist(hospSangreProcedimientos);
            if (idsangre != null) {
                idsangre.getHospSangreProcedimientosList().add(hospSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospSangreProcedimientos hospSangreProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSangreProcedimientos persistentHospSangreProcedimientos = em.find(HospSangreProcedimientos.class, hospSangreProcedimientos.getId());
            HospSangre idsangreOld = persistentHospSangreProcedimientos.getIdsangre();
            HospSangre idsangreNew = hospSangreProcedimientos.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                hospSangreProcedimientos.setIdsangre(idsangreNew);
            }
            hospSangreProcedimientos = em.merge(hospSangreProcedimientos);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getHospSangreProcedimientosList().remove(hospSangreProcedimientos);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getHospSangreProcedimientosList().add(hospSangreProcedimientos);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospSangreProcedimientos.getId();
                if (findHospSangreProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The hospSangreProcedimientos with id " + id + " no longer exists.");
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
            HospSangreProcedimientos hospSangreProcedimientos;
            try {
                hospSangreProcedimientos = em.getReference(HospSangreProcedimientos.class, id);
                hospSangreProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospSangreProcedimientos with id " + id + " no longer exists.", enfe);
            }
            HospSangre idsangre = hospSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre.getHospSangreProcedimientosList().remove(hospSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.remove(hospSangreProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospSangreProcedimientos> findHospSangreProcedimientosEntities() {
        return findHospSangreProcedimientosEntities(true, -1, -1);
    }

    public List<HospSangreProcedimientos> findHospSangreProcedimientosEntities(int maxResults, int firstResult) {
        return findHospSangreProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<HospSangreProcedimientos> findHospSangreProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospSangreProcedimientos.class));
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

    public HospSangreProcedimientos findHospSangreProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospSangreProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospSangreProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospSangreProcedimientos> rt = cq.from(HospSangreProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
