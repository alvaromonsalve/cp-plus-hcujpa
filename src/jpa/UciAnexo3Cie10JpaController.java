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
import entidades_EJB.UciAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciAnexo3Cie10JpaController implements Serializable {

    public UciAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAnexo3Cie10 uciAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAnexo3 idanexo = uciAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uciAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(uciAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getUciAnexo3Cie10List().add(uciAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAnexo3Cie10 uciAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAnexo3Cie10 persistentUciAnexo3Cie10 = em.find(UciAnexo3Cie10.class, uciAnexo3Cie10.getId());
            UciAnexo3 idanexoOld = persistentUciAnexo3Cie10.getIdanexo();
            UciAnexo3 idanexoNew = uciAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uciAnexo3Cie10.setIdanexo(idanexoNew);
            }
            uciAnexo3Cie10 = em.merge(uciAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUciAnexo3Cie10List().remove(uciAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUciAnexo3Cie10List().add(uciAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAnexo3Cie10.getId();
                if (findUciAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The uciAnexo3Cie10 with id " + id + " no longer exists.");
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
            UciAnexo3Cie10 uciAnexo3Cie10;
            try {
                uciAnexo3Cie10 = em.getReference(UciAnexo3Cie10.class, id);
                uciAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            UciAnexo3 idanexo = uciAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getUciAnexo3Cie10List().remove(uciAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(uciAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAnexo3Cie10> findUciAnexo3Cie10Entities() {
        return findUciAnexo3Cie10Entities(true, -1, -1);
    }

    public List<UciAnexo3Cie10> findUciAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findUciAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<UciAnexo3Cie10> findUciAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAnexo3Cie10.class));
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

    public UciAnexo3Cie10 findUciAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAnexo3Cie10> rt = cq.from(UciAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
