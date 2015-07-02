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
import entidades_EJB.UrgIngAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngAnexo3Cie10JpaController implements Serializable {

    public UrgIngAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngAnexo3Cie10 urgIngAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngAnexo3 idanexo = urgIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                urgIngAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(urgIngAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getUrgIngAnexo3Cie10List().add(urgIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngAnexo3Cie10 urgIngAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngAnexo3Cie10 persistentUrgIngAnexo3Cie10 = em.find(UrgIngAnexo3Cie10.class, urgIngAnexo3Cie10.getId());
            UrgIngAnexo3 idanexoOld = persistentUrgIngAnexo3Cie10.getIdanexo();
            UrgIngAnexo3 idanexoNew = urgIngAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                urgIngAnexo3Cie10.setIdanexo(idanexoNew);
            }
            urgIngAnexo3Cie10 = em.merge(urgIngAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUrgIngAnexo3Cie10List().remove(urgIngAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUrgIngAnexo3Cie10List().add(urgIngAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngAnexo3Cie10.getId();
                if (findUrgIngAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The urgIngAnexo3Cie10 with id " + id + " no longer exists.");
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
            UrgIngAnexo3Cie10 urgIngAnexo3Cie10;
            try {
                urgIngAnexo3Cie10 = em.getReference(UrgIngAnexo3Cie10.class, id);
                urgIngAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            UrgIngAnexo3 idanexo = urgIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getUrgIngAnexo3Cie10List().remove(urgIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(urgIngAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngAnexo3Cie10> findUrgIngAnexo3Cie10Entities() {
        return findUrgIngAnexo3Cie10Entities(true, -1, -1);
    }

    public List<UrgIngAnexo3Cie10> findUrgIngAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findUrgIngAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<UrgIngAnexo3Cie10> findUrgIngAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngAnexo3Cie10.class));
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

    public UrgIngAnexo3Cie10 findUrgIngAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngAnexo3Cie10> rt = cq.from(UrgIngAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
