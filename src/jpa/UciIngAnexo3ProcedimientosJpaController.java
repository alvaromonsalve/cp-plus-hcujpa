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
import entidades_EJB.UciIngAnexo3;
import entidades_EJB.UciIngAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngAnexo3ProcedimientosJpaController implements Serializable {

    public UciIngAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngAnexo3Procedimientos uciIngAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngAnexo3 idanexo = uciIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uciIngAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(uciIngAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getUciIngAnexo3ProcedimientosList().add(uciIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngAnexo3Procedimientos uciIngAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngAnexo3Procedimientos persistentUciIngAnexo3Procedimientos = em.find(UciIngAnexo3Procedimientos.class, uciIngAnexo3Procedimientos.getId());
            UciIngAnexo3 idanexoOld = persistentUciIngAnexo3Procedimientos.getIdanexo();
            UciIngAnexo3 idanexoNew = uciIngAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uciIngAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            uciIngAnexo3Procedimientos = em.merge(uciIngAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUciIngAnexo3ProcedimientosList().remove(uciIngAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUciIngAnexo3ProcedimientosList().add(uciIngAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngAnexo3Procedimientos.getId();
                if (findUciIngAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The uciIngAnexo3Procedimientos with id " + id + " no longer exists.");
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
            UciIngAnexo3Procedimientos uciIngAnexo3Procedimientos;
            try {
                uciIngAnexo3Procedimientos = em.getReference(UciIngAnexo3Procedimientos.class, id);
                uciIngAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            UciIngAnexo3 idanexo = uciIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getUciIngAnexo3ProcedimientosList().remove(uciIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(uciIngAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngAnexo3Procedimientos> findUciIngAnexo3ProcedimientosEntities() {
        return findUciIngAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<UciIngAnexo3Procedimientos> findUciIngAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findUciIngAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UciIngAnexo3Procedimientos> findUciIngAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngAnexo3Procedimientos.class));
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

    public UciIngAnexo3Procedimientos findUciIngAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngAnexo3Procedimientos> rt = cq.from(UciIngAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
