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
import entidades_EJB.UceAnexo3;
import entidades_EJB.UceAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceAnexo3ProcedimientosJpaController implements Serializable {

    public UceAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceAnexo3Procedimientos uceAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceAnexo3 idanexo = uceAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uceAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(uceAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getUceAnexo3ProcedimientosList().add(uceAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceAnexo3Procedimientos uceAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceAnexo3Procedimientos persistentUceAnexo3Procedimientos = em.find(UceAnexo3Procedimientos.class, uceAnexo3Procedimientos.getId());
            UceAnexo3 idanexoOld = persistentUceAnexo3Procedimientos.getIdanexo();
            UceAnexo3 idanexoNew = uceAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uceAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            uceAnexo3Procedimientos = em.merge(uceAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUceAnexo3ProcedimientosList().remove(uceAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUceAnexo3ProcedimientosList().add(uceAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceAnexo3Procedimientos.getId();
                if (findUceAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The uceAnexo3Procedimientos with id " + id + " no longer exists.");
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
            UceAnexo3Procedimientos uceAnexo3Procedimientos;
            try {
                uceAnexo3Procedimientos = em.getReference(UceAnexo3Procedimientos.class, id);
                uceAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            UceAnexo3 idanexo = uceAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getUceAnexo3ProcedimientosList().remove(uceAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(uceAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceAnexo3Procedimientos> findUceAnexo3ProcedimientosEntities() {
        return findUceAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<UceAnexo3Procedimientos> findUceAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findUceAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<UceAnexo3Procedimientos> findUceAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceAnexo3Procedimientos.class));
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

    public UceAnexo3Procedimientos findUceAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceAnexo3Procedimientos> rt = cq.from(UceAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
