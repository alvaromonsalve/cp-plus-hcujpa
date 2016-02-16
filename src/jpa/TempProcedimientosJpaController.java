/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.TempProcedimientos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TempProcedimientosJpaController implements Serializable {

    public TempProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TempProcedimientos tempProcedimientos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tempProcedimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTempProcedimientos(tempProcedimientos.getId()) != null) {
                throw new PreexistingEntityException("TempProcedimientos " + tempProcedimientos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TempProcedimientos tempProcedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tempProcedimientos = em.merge(tempProcedimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tempProcedimientos.getId();
                if (findTempProcedimientos(id) == null) {
                    throw new NonexistentEntityException("The tempProcedimientos with id " + id + " no longer exists.");
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
            TempProcedimientos tempProcedimientos;
            try {
                tempProcedimientos = em.getReference(TempProcedimientos.class, id);
                tempProcedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tempProcedimientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tempProcedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TempProcedimientos> findTempProcedimientosEntities() {
        return findTempProcedimientosEntities(true, -1, -1);
    }

    public List<TempProcedimientos> findTempProcedimientosEntities(int maxResults, int firstResult) {
        return findTempProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<TempProcedimientos> findTempProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TempProcedimientos.class));
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

    public TempProcedimientos findTempProcedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TempProcedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTempProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TempProcedimientos> rt = cq.from(TempProcedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
