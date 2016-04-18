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
import entidades_EJB.HospSangre;
import entidades_EJB.HospSangreCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospSangreCie10JpaController implements Serializable {

    public HospSangreCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospSangreCie10 hospSangreCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSangre idsangre = hospSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                hospSangreCie10.setIdsangre(idsangre);
            }
            em.persist(hospSangreCie10);
            if (idsangre != null) {
                idsangre.getHospSangreCie10List().add(hospSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospSangreCie10 hospSangreCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSangreCie10 persistentHospSangreCie10 = em.find(HospSangreCie10.class, hospSangreCie10.getId());
            HospSangre idsangreOld = persistentHospSangreCie10.getIdsangre();
            HospSangre idsangreNew = hospSangreCie10.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                hospSangreCie10.setIdsangre(idsangreNew);
            }
            hospSangreCie10 = em.merge(hospSangreCie10);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getHospSangreCie10List().remove(hospSangreCie10);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getHospSangreCie10List().add(hospSangreCie10);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospSangreCie10.getId();
                if (findHospSangreCie10(id) == null) {
                    throw new NonexistentEntityException("The hospSangreCie10 with id " + id + " no longer exists.");
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
            HospSangreCie10 hospSangreCie10;
            try {
                hospSangreCie10 = em.getReference(HospSangreCie10.class, id);
                hospSangreCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospSangreCie10 with id " + id + " no longer exists.", enfe);
            }
            HospSangre idsangre = hospSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre.getHospSangreCie10List().remove(hospSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.remove(hospSangreCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospSangreCie10> findHospSangreCie10Entities() {
        return findHospSangreCie10Entities(true, -1, -1);
    }

    public List<HospSangreCie10> findHospSangreCie10Entities(int maxResults, int firstResult) {
        return findHospSangreCie10Entities(false, maxResults, firstResult);
    }

    private List<HospSangreCie10> findHospSangreCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospSangreCie10.class));
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

    public HospSangreCie10 findHospSangreCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospSangreCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospSangreCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospSangreCie10> rt = cq.from(HospSangreCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
