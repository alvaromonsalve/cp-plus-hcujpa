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
import entidades_EJB.HospIngAnexo3;
import entidades_EJB.HospIngAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngAnexo3ProcedimientosJpaController implements Serializable {

    public HospIngAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngAnexo3Procedimientos hospIngAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngAnexo3 idanexo = hospIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                hospIngAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(hospIngAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getHospIngAnexo3ProcedimientosList().add(hospIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngAnexo3Procedimientos hospIngAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngAnexo3Procedimientos persistentHospIngAnexo3Procedimientos = em.find(HospIngAnexo3Procedimientos.class, hospIngAnexo3Procedimientos.getId());
            HospIngAnexo3 idanexoOld = persistentHospIngAnexo3Procedimientos.getIdanexo();
            HospIngAnexo3 idanexoNew = hospIngAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                hospIngAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            hospIngAnexo3Procedimientos = em.merge(hospIngAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getHospIngAnexo3ProcedimientosList().remove(hospIngAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getHospIngAnexo3ProcedimientosList().add(hospIngAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngAnexo3Procedimientos.getId();
                if (findHospIngAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The hospIngAnexo3Procedimientos with id " + id + " no longer exists.");
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
            HospIngAnexo3Procedimientos hospIngAnexo3Procedimientos;
            try {
                hospIngAnexo3Procedimientos = em.getReference(HospIngAnexo3Procedimientos.class, id);
                hospIngAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            HospIngAnexo3 idanexo = hospIngAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getHospIngAnexo3ProcedimientosList().remove(hospIngAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(hospIngAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngAnexo3Procedimientos> findHospIngAnexo3ProcedimientosEntities() {
        return findHospIngAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<HospIngAnexo3Procedimientos> findHospIngAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findHospIngAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<HospIngAnexo3Procedimientos> findHospIngAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngAnexo3Procedimientos.class));
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

    public HospIngAnexo3Procedimientos findHospIngAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngAnexo3Procedimientos> rt = cq.from(HospIngAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
