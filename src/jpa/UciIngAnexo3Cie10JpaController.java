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
import entidades_EJB.UciIngAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngAnexo3Cie10JpaController implements Serializable {

    public UciIngAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngAnexo3Cie10 uciIngAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngAnexo3 idanexo = uciIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uciIngAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(uciIngAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getUciIngAnexo3Cie10List().add(uciIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngAnexo3Cie10 uciIngAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngAnexo3Cie10 persistentUciIngAnexo3Cie10 = em.find(UciIngAnexo3Cie10.class, uciIngAnexo3Cie10.getId());
            UciIngAnexo3 idanexoOld = persistentUciIngAnexo3Cie10.getIdanexo();
            UciIngAnexo3 idanexoNew = uciIngAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uciIngAnexo3Cie10.setIdanexo(idanexoNew);
            }
            uciIngAnexo3Cie10 = em.merge(uciIngAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUciIngAnexo3Cie10List().remove(uciIngAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUciIngAnexo3Cie10List().add(uciIngAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngAnexo3Cie10.getId();
                if (findUciIngAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The uciIngAnexo3Cie10 with id " + id + " no longer exists.");
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
            UciIngAnexo3Cie10 uciIngAnexo3Cie10;
            try {
                uciIngAnexo3Cie10 = em.getReference(UciIngAnexo3Cie10.class, id);
                uciIngAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            UciIngAnexo3 idanexo = uciIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getUciIngAnexo3Cie10List().remove(uciIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(uciIngAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngAnexo3Cie10> findUciIngAnexo3Cie10Entities() {
        return findUciIngAnexo3Cie10Entities(true, -1, -1);
    }

    public List<UciIngAnexo3Cie10> findUciIngAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findUciIngAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<UciIngAnexo3Cie10> findUciIngAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngAnexo3Cie10.class));
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

    public UciIngAnexo3Cie10 findUciIngAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngAnexo3Cie10> rt = cq.from(UciIngAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
