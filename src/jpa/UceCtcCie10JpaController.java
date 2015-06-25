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
import entidades_EJB.UceCtc;
import entidades_EJB.UceCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceCtcCie10JpaController implements Serializable {

    public UceCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceCtcCie10 uceCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtc idctc = uceCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uceCtcCie10.setIdctc(idctc);
            }
            em.persist(uceCtcCie10);
            if (idctc != null) {
                idctc.getUceCtcCie10Set().add(uceCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceCtcCie10 uceCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtcCie10 persistentUceCtcCie10 = em.find(UceCtcCie10.class, uceCtcCie10.getId());
            UceCtc idctcOld = persistentUceCtcCie10.getIdctc();
            UceCtc idctcNew = uceCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uceCtcCie10.setIdctc(idctcNew);
            }
            uceCtcCie10 = em.merge(uceCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUceCtcCie10Set().remove(uceCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUceCtcCie10Set().add(uceCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceCtcCie10.getId();
                if (findUceCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The uceCtcCie10 with id " + id + " no longer exists.");
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
            UceCtcCie10 uceCtcCie10;
            try {
                uceCtcCie10 = em.getReference(UceCtcCie10.class, id);
                uceCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            UceCtc idctc = uceCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getUceCtcCie10Set().remove(uceCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(uceCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceCtcCie10> findUceCtcCie10Entities() {
        return findUceCtcCie10Entities(true, -1, -1);
    }

    public List<UceCtcCie10> findUceCtcCie10Entities(int maxResults, int firstResult) {
        return findUceCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<UceCtcCie10> findUceCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceCtcCie10.class));
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

    public UceCtcCie10 findUceCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceCtcCie10> rt = cq.from(UceCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
