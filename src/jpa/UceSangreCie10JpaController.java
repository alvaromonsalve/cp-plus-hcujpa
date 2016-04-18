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
import entidades_EJB.UceSangre;
import entidades_EJB.UceSangreCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceSangreCie10JpaController implements Serializable {

    public UceSangreCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceSangreCie10 uceSangreCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceSangre idsangre = uceSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                uceSangreCie10.setIdsangre(idsangre);
            }
            em.persist(uceSangreCie10);
            if (idsangre != null) {
                idsangre.getUceSangreCie10List().add(uceSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceSangreCie10 uceSangreCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceSangreCie10 persistentUceSangreCie10 = em.find(UceSangreCie10.class, uceSangreCie10.getId());
            UceSangre idsangreOld = persistentUceSangreCie10.getIdsangre();
            UceSangre idsangreNew = uceSangreCie10.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                uceSangreCie10.setIdsangre(idsangreNew);
            }
            uceSangreCie10 = em.merge(uceSangreCie10);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUceSangreCie10List().remove(uceSangreCie10);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUceSangreCie10List().add(uceSangreCie10);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceSangreCie10.getId();
                if (findUceSangreCie10(id) == null) {
                    throw new NonexistentEntityException("The uceSangreCie10 with id " + id + " no longer exists.");
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
            UceSangreCie10 uceSangreCie10;
            try {
                uceSangreCie10 = em.getReference(UceSangreCie10.class, id);
                uceSangreCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceSangreCie10 with id " + id + " no longer exists.", enfe);
            }
            UceSangre idsangre = uceSangreCie10.getIdsangre();
            if (idsangre != null) {
                idsangre.getUceSangreCie10List().remove(uceSangreCie10);
                idsangre = em.merge(idsangre);
            }
            em.remove(uceSangreCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceSangreCie10> findUceSangreCie10Entities() {
        return findUceSangreCie10Entities(true, -1, -1);
    }

    public List<UceSangreCie10> findUceSangreCie10Entities(int maxResults, int firstResult) {
        return findUceSangreCie10Entities(false, maxResults, firstResult);
    }

    private List<UceSangreCie10> findUceSangreCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceSangreCie10.class));
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

    public UceSangreCie10 findUceSangreCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceSangreCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceSangreCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceSangreCie10> rt = cq.from(UceSangreCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
