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
import entidades_EJB.UrgAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgAnexo3Cie10JpaController implements Serializable {

    public UrgAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgAnexo3Cie10 urgAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgAnexo3 idanexo = urgAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                urgAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(urgAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getUrgAnexo3Cie10List().add(urgAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgAnexo3Cie10 urgAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgAnexo3Cie10 persistentUrgAnexo3Cie10 = em.find(UrgAnexo3Cie10.class, urgAnexo3Cie10.getId());
            UrgAnexo3 idanexoOld = persistentUrgAnexo3Cie10.getIdanexo();
            UrgAnexo3 idanexoNew = urgAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                urgAnexo3Cie10.setIdanexo(idanexoNew);
            }
            urgAnexo3Cie10 = em.merge(urgAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUrgAnexo3Cie10List().remove(urgAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUrgAnexo3Cie10List().add(urgAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgAnexo3Cie10.getId();
                if (findUrgAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The urgAnexo3Cie10 with id " + id + " no longer exists.");
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
            UrgAnexo3Cie10 urgAnexo3Cie10;
            try {
                urgAnexo3Cie10 = em.getReference(UrgAnexo3Cie10.class, id);
                urgAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            UrgAnexo3 idanexo = urgAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getUrgAnexo3Cie10List().remove(urgAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(urgAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgAnexo3Cie10> findUrgAnexo3Cie10Entities() {
        return findUrgAnexo3Cie10Entities(true, -1, -1);
    }

    public List<UrgAnexo3Cie10> findUrgAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findUrgAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<UrgAnexo3Cie10> findUrgAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgAnexo3Cie10.class));
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

    public UrgAnexo3Cie10 findUrgAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgAnexo3Cie10> rt = cq.from(UrgAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
