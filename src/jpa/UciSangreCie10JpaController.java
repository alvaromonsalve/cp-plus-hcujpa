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
import entidades_EJB.UciSangre;
import entidades_EJB.UciSangreCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciSangreCie10JpaController implements Serializable {

    public UciSangreCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciSangreCie10 uciSangreCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSangre idsangre = uciSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                uciSangreCie10.setIdsangre(idsangre);
            }
            em.persist(uciSangreCie10);
            if (idsangre != null) {
                idsangre.getUciSangreCie10List().add(uciSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciSangreCie10 uciSangreCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSangreCie10 persistentUciSangreCie10 = em.find(UciSangreCie10.class, uciSangreCie10.getId());
            UciSangre idsangreOld = persistentUciSangreCie10.getIdsangre();
            UciSangre idsangreNew = uciSangreCie10.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                uciSangreCie10.setIdsangre(idsangreNew);
            }
            uciSangreCie10 = em.merge(uciSangreCie10);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUciSangreCie10List().remove(uciSangreCie10);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUciSangreCie10List().add(uciSangreCie10);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciSangreCie10.getId();
                if (findUciSangreCie10(id) == null) {
                    throw new NonexistentEntityException("The uciSangreCie10 with id " + id + " no longer exists.");
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
            UciSangreCie10 uciSangreCie10;
            try {
                uciSangreCie10 = em.getReference(UciSangreCie10.class, id);
                uciSangreCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciSangreCie10 with id " + id + " no longer exists.", enfe);
            }
            UciSangre idsangre = uciSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre.getUciSangreCie10List().remove(uciSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.remove(uciSangreCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciSangreCie10> findUciSangreCie10Entities() {
        return findUciSangreCie10Entities(true, -1, -1);
    }

    public List<UciSangreCie10> findUciSangreCie10Entities(int maxResults, int firstResult) {
        return findUciSangreCie10Entities(false, maxResults, firstResult);
    }

    private List<UciSangreCie10> findUciSangreCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciSangreCie10.class));
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

    public UciSangreCie10 findUciSangreCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciSangreCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciSangreCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciSangreCie10> rt = cq.from(UciSangreCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
