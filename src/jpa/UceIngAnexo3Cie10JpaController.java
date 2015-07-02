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
import entidades_EJB.UceIngAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngAnexo3Cie10JpaController implements Serializable {

    public UceIngAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngAnexo3Cie10 uceIngAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngAnexo3 idanexo = uceIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uceIngAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(uceIngAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getUceIngAnexo3Cie10List().add(uceIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngAnexo3Cie10 uceIngAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngAnexo3Cie10 persistentUceIngAnexo3Cie10 = em.find(UceIngAnexo3Cie10.class, uceIngAnexo3Cie10.getId());
            UceIngAnexo3 idanexoOld = persistentUceIngAnexo3Cie10.getIdanexo();
            UceIngAnexo3 idanexoNew = uceIngAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uceIngAnexo3Cie10.setIdanexo(idanexoNew);
            }
            uceIngAnexo3Cie10 = em.merge(uceIngAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUceIngAnexo3Cie10List().remove(uceIngAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUceIngAnexo3Cie10List().add(uceIngAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngAnexo3Cie10.getId();
                if (findUceIngAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The uceIngAnexo3Cie10 with id " + id + " no longer exists.");
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
            UceIngAnexo3Cie10 uceIngAnexo3Cie10;
            try {
                uceIngAnexo3Cie10 = em.getReference(UceIngAnexo3Cie10.class, id);
                uceIngAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            UceIngAnexo3 idanexo = uceIngAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getUceIngAnexo3Cie10List().remove(uceIngAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(uceIngAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngAnexo3Cie10> findUceIngAnexo3Cie10Entities() {
        return findUceIngAnexo3Cie10Entities(true, -1, -1);
    }

    public List<UceIngAnexo3Cie10> findUceIngAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findUceIngAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<UceIngAnexo3Cie10> findUceIngAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngAnexo3Cie10.class));
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

    public UceIngAnexo3Cie10 findUceIngAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngAnexo3Cie10> rt = cq.from(UceIngAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
