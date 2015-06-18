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
import entidades_EJB.UciAnexo3;
import entidades_EJB.UciAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciAnexo3ProcedimientosJpaController implements Serializable {

    public UciAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAnexo3Procedimientos uciAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAnexo3 idanexo = uciAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uciAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(uciAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getUciAnexo3ProcedimientosList().add(uciAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAnexo3Procedimientos uciAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAnexo3Procedimientos persistentUciAnexo3Procedimientos = em.find(UciAnexo3Procedimientos.class, uciAnexo3Procedimientos.getId());
            UciAnexo3 idanexoOld = persistentUciAnexo3Procedimientos.getIdanexo();
            UciAnexo3 idanexoNew = uciAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uciAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            uciAnexo3Procedimientos = em.merge(uciAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUciAnexo3ProcedimientosList().remove(uciAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUciAnexo3ProcedimientosList().add(uciAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAnexo3Procedimientos.getId();
                if (findUciAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The uciAnexo3Procedimientos with id " + id + " no longer exists.");
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
            UciAnexo3Procedimientos uciAnexo3Procedimientos;
            try {
                uciAnexo3Procedimientos = em.getReference(UciAnexo3Procedimientos.class, id);
                uciAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            UciAnexo3 idanexo = uciAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getUciAnexo3ProcedimientosList().remove(uciAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(uciAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAnexo3Procedimientos> findUciAnexo3ProcedimientosEntities() {
        return findUciAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<UciAnexo3Procedimientos> findUciAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findUciAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UciAnexo3Procedimientos> findUciAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAnexo3Procedimientos.class));
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

    public UciAnexo3Procedimientos findUciAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAnexo3Procedimientos> rt = cq.from(UciAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
