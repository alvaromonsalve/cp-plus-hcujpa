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
import entidades_EJB.HospAnexo3;
import entidades_EJB.HospAnexo3Procedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospAnexo3ProcedimientosJpaController implements Serializable {

    public HospAnexo3ProcedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospAnexo3Procedimientos hospAnexo3Procedimientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospAnexo3 idanexo = hospAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                hospAnexo3Procedimientos.setIdanexo(idanexo);
            }
            em.persist(hospAnexo3Procedimientos);
            if (idanexo != null) {
                idanexo.getHospAnexo3ProcedimientosList().add(hospAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospAnexo3Procedimientos hospAnexo3Procedimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospAnexo3Procedimientos persistentHospAnexo3Procedimientos = em.find(HospAnexo3Procedimientos.class, hospAnexo3Procedimientos.getId());
            HospAnexo3 idanexoOld = persistentHospAnexo3Procedimientos.getIdanexo();
            HospAnexo3 idanexoNew = hospAnexo3Procedimientos.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                hospAnexo3Procedimientos.setIdanexo(idanexoNew);
            }
            hospAnexo3Procedimientos = em.merge(hospAnexo3Procedimientos);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getHospAnexo3ProcedimientosList().remove(hospAnexo3Procedimientos);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getHospAnexo3ProcedimientosList().add(hospAnexo3Procedimientos);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospAnexo3Procedimientos.getId();
                if (findHospAnexo3Procedimientos(id) == null) {
                    throw new NonexistentEntityException("The hospAnexo3Procedimientos with id " + id + " no longer exists.");
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
            HospAnexo3Procedimientos hospAnexo3Procedimientos;
            try {
                hospAnexo3Procedimientos = em.getReference(HospAnexo3Procedimientos.class, id);
                hospAnexo3Procedimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospAnexo3Procedimientos with id " + id + " no longer exists.", enfe);
            }
            HospAnexo3 idanexo = hospAnexo3Procedimientos.getIdanexo();
            if (idanexo != null) {
                idanexo.getHospAnexo3ProcedimientosList().remove(hospAnexo3Procedimientos);
                idanexo = em.merge(idanexo);
            }
            em.remove(hospAnexo3Procedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospAnexo3Procedimientos> findHospAnexo3ProcedimientosEntities() {
        return findHospAnexo3ProcedimientosEntities(true, -1, -1);
    }

    public List<HospAnexo3Procedimientos> findHospAnexo3ProcedimientosEntities(int maxResults, int firstResult) {
        return findHospAnexo3ProcedimientosEntities(false, maxResults, firstResult);
    }

    private List<HospAnexo3Procedimientos> findHospAnexo3ProcedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospAnexo3Procedimientos.class));
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

    public HospAnexo3Procedimientos findHospAnexo3Procedimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospAnexo3Procedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospAnexo3ProcedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospAnexo3Procedimientos> rt = cq.from(HospAnexo3Procedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
