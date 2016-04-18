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
import entidades_EJB.UciSangre;
import entidades_EJB.UciSangreProcedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciSangreProcedimientosJpaController implements Serializable {

    public UciSangreProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciSangreProcedimientos uciSangreProcedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSangre idsangre = uciSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                uciSangreProcedimientos.setIdsangre(idsangre);
            }
            em.persist(uciSangreProcedimientos);
            if (idsangre != null) {
                idsangre.getUciSangreProcedimientosList().add(uciSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciSangreProcedimientos uciSangreProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSangreProcedimientos persistentUciSangreProcedimientos = em.find(UciSangreProcedimientos.class, uciSangreProcedimientos.getId());
            UciSangre idsangreOld = persistentUciSangreProcedimientos.getIdsangre();
            UciSangre idsangreNew = uciSangreProcedimientos.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                uciSangreProcedimientos.setIdsangre(idsangreNew);
            }
            uciSangreProcedimientos = em.merge(uciSangreProcedimientos);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUciSangreProcedimientosList().remove(uciSangreProcedimientos);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUciSangreProcedimientosList().add(uciSangreProcedimientos);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciSangreProcedimientos.getId();
                if (findUciSangreProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The uciSangreProcedimientos with id " + id + " no longer exists.");
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
            UciSangreProcedimientos uciSangreProcedimientos;
            try {
                uciSangreProcedimientos = em.getReference(UciSangreProcedimientos.class, id);
                uciSangreProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciSangreProcedimientos with id " + id + " no longer exists.", enfe);
            }
            UciSangre idsangre = uciSangreProcedimientos.getIdsangre();
            if (idsangre != null) {
                idsangre.getUciSangreProcedimientosList().remove(uciSangreProcedimientos);
                idsangre = em.merge(idsangre);
            }
            em.remove(uciSangreProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciSangreProcedimientos> findUciSangreProcedimientosEntities() {
        return findUciSangreProcedimientosEntities(true, -1, -1);
    }

    public List<UciSangreProcedimientos> findUciSangreProcedimientosEntities(int maxResults, int firstResult) {
        return findUciSangreProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UciSangreProcedimientos> findUciSangreProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciSangreProcedimientos.class));
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

    public UciSangreProcedimientos findUciSangreProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciSangreProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciSangreProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciSangreProcedimientos> rt = cq.from(UciSangreProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
