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
import entidades_EJB.UrgIngAnexo3;
import entidades_EJB.UrgIngAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngAnexo3ProcedimientosJpaController implements Serializable {

    public UrgIngAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngAnexo3Procedimientos urgIngAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngAnexo3 idanexo = urgIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                urgIngAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(urgIngAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getUrgIngAnexo3ProcedimientosList().add(urgIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngAnexo3Procedimientos urgIngAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngAnexo3Procedimientos persistentUrgIngAnexo3Procedimientos = em.find(UrgIngAnexo3Procedimientos.class, urgIngAnexo3Procedimientos.getId());
            UrgIngAnexo3 idanexoOld = persistentUrgIngAnexo3Procedimientos.getIdanexo();
            UrgIngAnexo3 idanexoNew = urgIngAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                urgIngAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            urgIngAnexo3Procedimientos = em.merge(urgIngAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUrgIngAnexo3ProcedimientosList().remove(urgIngAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUrgIngAnexo3ProcedimientosList().add(urgIngAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngAnexo3Procedimientos.getId();
                if (findUrgIngAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The urgIngAnexo3Procedimientos with id " + id + " no longer exists.");
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
            UrgIngAnexo3Procedimientos urgIngAnexo3Procedimientos;
            try {
                urgIngAnexo3Procedimientos = em.getReference(UrgIngAnexo3Procedimientos.class, id);
                urgIngAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            UrgIngAnexo3 idanexo = urgIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getUrgIngAnexo3ProcedimientosList().remove(urgIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(urgIngAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngAnexo3Procedimientos> findUrgIngAnexo3ProcedimientosEntities() {
        return findUrgIngAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<UrgIngAnexo3Procedimientos> findUrgIngAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findUrgIngAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UrgIngAnexo3Procedimientos> findUrgIngAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngAnexo3Procedimientos.class));
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

    public UrgIngAnexo3Procedimientos findUrgIngAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngAnexo3Procedimientos> rt = cq.from(UrgIngAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
