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
import entidades_EJB.UceIngCtc;
import entidades_EJB.UceIngCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngCtcCie10JpaController implements Serializable {

    public UceIngCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngCtcCie10 uceIngCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtc idctc = uceIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uceIngCtcCie10.setIdctc(idctc);
            }
            em.persist(uceIngCtcCie10);
            if (idctc != null) {
                idctc.getUceIngCtcCie10List().add(uceIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngCtcCie10 uceIngCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtcCie10 persistentUceIngCtcCie10 = em.find(UceIngCtcCie10.class, uceIngCtcCie10.getId());
            UceIngCtc idctcOld = persistentUceIngCtcCie10.getIdctc();
            UceIngCtc idctcNew = uceIngCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uceIngCtcCie10.setIdctc(idctcNew);
            }
            uceIngCtcCie10 = em.merge(uceIngCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUceIngCtcCie10List().remove(uceIngCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUceIngCtcCie10List().add(uceIngCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngCtcCie10.getId();
                if (findUceIngCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The uceIngCtcCie10 with id " + id + " no longer exists.");
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
            UceIngCtcCie10 uceIngCtcCie10;
            try {
                uceIngCtcCie10 = em.getReference(UceIngCtcCie10.class, id);
                uceIngCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            UceIngCtc idctc = uceIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getUceIngCtcCie10List().remove(uceIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(uceIngCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngCtcCie10> findUceIngCtcCie10Entities() {
        return findUceIngCtcCie10Entities(true, -1, -1);
    }

    public List<UceIngCtcCie10> findUceIngCtcCie10Entities(int maxResults, int firstResult) {
        return findUceIngCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<UceIngCtcCie10> findUceIngCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngCtcCie10.class));
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

    public UceIngCtcCie10 findUceIngCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngCtcCie10> rt = cq.from(UceIngCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
