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
import entidades_EJB.UceIngAnexo3;
import entidades_EJB.UceIngAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngAnexo3ProcedimientosJpaController implements Serializable {

    public UceIngAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngAnexo3Procedimientos uceIngAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngAnexo3 idanexo = uceIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uceIngAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(uceIngAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getUceIngAnexo3ProcedimientosList().add(uceIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngAnexo3Procedimientos uceIngAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngAnexo3Procedimientos persistentUceIngAnexo3Procedimientos = em.find(UceIngAnexo3Procedimientos.class, uceIngAnexo3Procedimientos.getId());
            UceIngAnexo3 idanexoOld = persistentUceIngAnexo3Procedimientos.getIdanexo();
            UceIngAnexo3 idanexoNew = uceIngAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uceIngAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            uceIngAnexo3Procedimientos = em.merge(uceIngAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUceIngAnexo3ProcedimientosList().remove(uceIngAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUceIngAnexo3ProcedimientosList().add(uceIngAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngAnexo3Procedimientos.getId();
                if (findUceIngAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The uceIngAnexo3Procedimientos with id " + id + " no longer exists.");
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
            UceIngAnexo3Procedimientos uceIngAnexo3Procedimientos;
            try {
                uceIngAnexo3Procedimientos = em.getReference(UceIngAnexo3Procedimientos.class, id);
                uceIngAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            UceIngAnexo3 idanexo = uceIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getUceIngAnexo3ProcedimientosList().remove(uceIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(uceIngAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngAnexo3Procedimientos> findUceIngAnexo3ProcedimientosEntities() {
        return findUceIngAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<UceIngAnexo3Procedimientos> findUceIngAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findUceIngAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UceIngAnexo3Procedimientos> findUceIngAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngAnexo3Procedimientos.class));
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

    public UceIngAnexo3Procedimientos findUceIngAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngAnexo3Procedimientos> rt = cq.from(UceIngAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
