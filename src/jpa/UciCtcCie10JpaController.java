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
import entidades_EJB.UciCtc;
import entidades_EJB.UciCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciCtcCie10JpaController implements Serializable {

    public UciCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciCtcCie10 uciCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtc idctc = uciCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uciCtcCie10.setIdctc(idctc);
            }
            em.persist(uciCtcCie10);
            if (idctc != null) {
                idctc.getUciCtcCie10Set().add(uciCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciCtcCie10 uciCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtcCie10 persistentUciCtcCie10 = em.find(UciCtcCie10.class, uciCtcCie10.getId());
            UciCtc idctcOld = persistentUciCtcCie10.getIdctc();
            UciCtc idctcNew = uciCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uciCtcCie10.setIdctc(idctcNew);
            }
            uciCtcCie10 = em.merge(uciCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUciCtcCie10Set().remove(uciCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUciCtcCie10Set().add(uciCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciCtcCie10.getId();
                if (findUciCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The uciCtcCie10 with id " + id + " no longer exists.");
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
            UciCtcCie10 uciCtcCie10;
            try {
                uciCtcCie10 = em.getReference(UciCtcCie10.class, id);
                uciCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            UciCtc idctc = uciCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getUciCtcCie10Set().remove(uciCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(uciCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciCtcCie10> findUciCtcCie10Entities() {
        return findUciCtcCie10Entities(true, -1, -1);
    }

    public List<UciCtcCie10> findUciCtcCie10Entities(int maxResults, int firstResult) {
        return findUciCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<UciCtcCie10> findUciCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciCtcCie10.class));
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

    public UciCtcCie10 findUciCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciCtcCie10> rt = cq.from(UciCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
