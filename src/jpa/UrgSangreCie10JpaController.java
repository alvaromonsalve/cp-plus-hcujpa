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
import entidades_EJB.UrgSangre;
import entidades_EJB.UrgSangreCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgSangreCie10JpaController implements Serializable {

    public UrgSangreCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgSangreCie10 urgSangreCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgSangre idsangre = urgSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                urgSangreCie10.setIdsangre(idsangre);
            }
            em.persist(urgSangreCie10);
            if (idsangre != null) {
                idsangre.getUrgSangreCie10List().add(urgSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgSangreCie10 urgSangreCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgSangreCie10 persistentUrgSangreCie10 = em.find(UrgSangreCie10.class, urgSangreCie10.getId());
            UrgSangre idsangreOld = persistentUrgSangreCie10.getIdsangre();
            UrgSangre idsangreNew = urgSangreCie10.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                urgSangreCie10.setIdsangre(idsangreNew);
            }
            urgSangreCie10 = em.merge(urgSangreCie10);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUrgSangreCie10List().remove(urgSangreCie10);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUrgSangreCie10List().add(urgSangreCie10);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgSangreCie10.getId();
                if (findUrgSangreCie10(id) == null) {
                    throw new NonexistentEntityException("The urgSangreCie10 with id " + id + " no longer exists.");
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
            UrgSangreCie10 urgSangreCie10;
            try {
                urgSangreCie10 = em.getReference(UrgSangreCie10.class, id);
                urgSangreCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgSangreCie10 with id " + id + " no longer exists.", enfe);
            }
            UrgSangre idsangre = urgSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre.getUrgSangreCie10List().remove(urgSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.remove(urgSangreCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgSangreCie10> findUrgSangreCie10Entities() {
        return findUrgSangreCie10Entities(true, -1, -1);
    }

    public List<UrgSangreCie10> findUrgSangreCie10Entities(int maxResults, int firstResult) {
        return findUrgSangreCie10Entities(false, maxResults, firstResult);
    }

    private List<UrgSangreCie10> findUrgSangreCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgSangreCie10.class));
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

    public UrgSangreCie10 findUrgSangreCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgSangreCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgSangreCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgSangreCie10> rt = cq.from(UrgSangreCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
