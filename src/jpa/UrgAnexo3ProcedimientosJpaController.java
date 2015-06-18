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
import entidades_EJB.UrgAnexo3;
import entidades_EJB.UrgAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgAnexo3ProcedimientosJpaController implements Serializable {

    public UrgAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgAnexo3Procedimientos urgAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgAnexo3 idanexo = urgAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                urgAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(urgAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getUrgAnexo3ProcedimientosList().add(urgAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgAnexo3Procedimientos urgAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgAnexo3Procedimientos persistentUrgAnexo3Procedimientos = em.find(UrgAnexo3Procedimientos.class, urgAnexo3Procedimientos.getId());
            UrgAnexo3 idanexoOld = persistentUrgAnexo3Procedimientos.getIdanexo();
            UrgAnexo3 idanexoNew = urgAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                urgAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            urgAnexo3Procedimientos = em.merge(urgAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUrgAnexo3ProcedimientosList().remove(urgAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUrgAnexo3ProcedimientosList().add(urgAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgAnexo3Procedimientos.getId();
                if (findUrgAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The urgAnexo3Procedimientos with id " + id + " no longer exists.");
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
            UrgAnexo3Procedimientos urgAnexo3Procedimientos;
            try {
                urgAnexo3Procedimientos = em.getReference(UrgAnexo3Procedimientos.class, id);
                urgAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            UrgAnexo3 idanexo = urgAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getUrgAnexo3ProcedimientosList().remove(urgAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(urgAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgAnexo3Procedimientos> findUrgAnexo3ProcedimientosEntities() {
        return findUrgAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<UrgAnexo3Procedimientos> findUrgAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findUrgAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UrgAnexo3Procedimientos> findUrgAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgAnexo3Procedimientos.class));
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

    public UrgAnexo3Procedimientos findUrgAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgAnexo3Procedimientos> rt = cq.from(UrgAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
