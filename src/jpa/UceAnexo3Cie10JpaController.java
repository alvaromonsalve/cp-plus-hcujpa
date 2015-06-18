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
import entidades_EJB.UceAnexo3Cie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceAnexo3Cie10JpaController implements Serializable {

    public UceAnexo3Cie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceAnexo3Cie10 uceAnexo3Cie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceAnexo3 idanexo = uceAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo = em.getReference(idanexo.getClass(), idanexo.getId());
                uceAnexo3Cie10.setIdanexo(idanexo);
            }
            em.persist(uceAnexo3Cie10);
            if (idanexo != null) {
                idanexo.getUceAnexo3Cie10List().add(uceAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceAnexo3Cie10 uceAnexo3Cie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceAnexo3Cie10 persistentUceAnexo3Cie10 = em.find(UceAnexo3Cie10.class, uceAnexo3Cie10.getId());
            UceAnexo3 idanexoOld = persistentUceAnexo3Cie10.getIdanexo();
            UceAnexo3 idanexoNew = uceAnexo3Cie10.getIdanexo();
            if (idanexoNew != null) {
                idanexoNew = em.getReference(idanexoNew.getClass(), idanexoNew.getId());
                uceAnexo3Cie10.setIdanexo(idanexoNew);
            }
            uceAnexo3Cie10 = em.merge(uceAnexo3Cie10);
            if (idanexoOld != null && !idanexoOld.equals(idanexoNew)) {
                idanexoOld.getUceAnexo3Cie10List().remove(uceAnexo3Cie10);
                idanexoOld = em.merge(idanexoOld);
            }
            if (idanexoNew != null && !idanexoNew.equals(idanexoOld)) {
                idanexoNew.getUceAnexo3Cie10List().add(uceAnexo3Cie10);
                idanexoNew = em.merge(idanexoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceAnexo3Cie10.getId();
                if (findUceAnexo3Cie10(id) == null) {
                    throw new NonexistentEntityException("The uceAnexo3Cie10 with id " + id + " no longer exists.");
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
            UceAnexo3Cie10 uceAnexo3Cie10;
            try {
                uceAnexo3Cie10 = em.getReference(UceAnexo3Cie10.class, id);
                uceAnexo3Cie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceAnexo3Cie10 with id " + id + " no longer exists.", enfe);
            }
            UceAnexo3 idanexo = uceAnexo3Cie10.getIdanexo();
            if (idanexo != null) {
                idanexo.getUceAnexo3Cie10List().remove(uceAnexo3Cie10);
                idanexo = em.merge(idanexo);
            }
            em.remove(uceAnexo3Cie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceAnexo3Cie10> findUceAnexo3Cie10Entities() {
        return findUceAnexo3Cie10Entities(true, -1, -1);
    }

    public List<UceAnexo3Cie10> findUceAnexo3Cie10Entities(int maxResults, int firstResult) {
        return findUceAnexo3Cie10Entities(false, maxResults, firstResult);
    }

    private List<UceAnexo3Cie10> findUceAnexo3Cie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceAnexo3Cie10.class));
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

    public UceAnexo3Cie10 findUceAnexo3Cie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceAnexo3Cie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceAnexo3Cie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceAnexo3Cie10> rt = cq.from(UceAnexo3Cie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
