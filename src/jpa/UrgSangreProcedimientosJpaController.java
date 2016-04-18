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
import entidades_EJB.UrgSangre;
import entidades_EJB.UrgSangreProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgSangreProcedimientosJpaController implements Serializable {

    public UrgSangreProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgSangreProcedimientos urgSangreProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgSangre idsangre = urgSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                urgSangreProcedimientos.setIdsangre(idsangre);
            }
            em.persist(urgSangreProcedimientos);
            if (idsangre != null) {
                idsangre.getUrgSangreProcedimientosList().add(urgSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgSangreProcedimientos urgSangreProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgSangreProcedimientos persistentUrgSangreProcedimientos = em.find(UrgSangreProcedimientos.class, urgSangreProcedimientos.getId());
            UrgSangre idsangreOld = persistentUrgSangreProcedimientos.getIdsangre();
            UrgSangre idsangreNew = urgSangreProcedimientos.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                urgSangreProcedimientos.setIdsangre(idsangreNew);
            }
            urgSangreProcedimientos = em.merge(urgSangreProcedimientos);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUrgSangreProcedimientosList().remove(urgSangreProcedimientos);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUrgSangreProcedimientosList().add(urgSangreProcedimientos);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgSangreProcedimientos.getId();
                if (findUrgSangreProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The urgSangreProcedimientos with id " + id + " no longer exists.");
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
            UrgSangreProcedimientos urgSangreProcedimientos;
            try {
                urgSangreProcedimientos = em.getReference(UrgSangreProcedimientos.class, id);
                urgSangreProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgSangreProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UrgSangre idsangre = urgSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre.getUrgSangreProcedimientosList().remove(urgSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.remove(urgSangreProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgSangreProcedimientos> findUrgSangreProcedimientosEntities() {
        return findUrgSangreProcedimientosEntities(true, -1, -1);
    }

    public List<UrgSangreProcedimientos> findUrgSangreProcedimientosEntities(int maxResults, int firstResult) {
        return findUrgSangreProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UrgSangreProcedimientos> findUrgSangreProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgSangreProcedimientos.class));
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

    public UrgSangreProcedimientos findUrgSangreProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgSangreProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgSangreProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgSangreProcedimientos> rt = cq.from(UrgSangreProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
