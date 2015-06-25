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
import entidades_EJB.UrgCtc;
import entidades_EJB.UrgCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgCtcCie10JpaController implements Serializable {

    public UrgCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgCtcCie10 urgCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtc idctc = urgCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                urgCtcCie10.setIdctc(idctc);
            }
            em.persist(urgCtcCie10);
            if (idctc != null) {
                idctc.getUrgCtcCie10Set().add(urgCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgCtcCie10 urgCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtcCie10 persistentUrgCtcCie10 = em.find(UrgCtcCie10.class, urgCtcCie10.getId());
            UrgCtc idctcOld = persistentUrgCtcCie10.getIdctc();
            UrgCtc idctcNew = urgCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                urgCtcCie10.setIdctc(idctcNew);
            }
            urgCtcCie10 = em.merge(urgCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUrgCtcCie10Set().remove(urgCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUrgCtcCie10Set().add(urgCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgCtcCie10.getId();
                if (findUrgCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The urgCtcCie10 with id " + id + " no longer exists.");
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
            UrgCtcCie10 urgCtcCie10;
            try {
                urgCtcCie10 = em.getReference(UrgCtcCie10.class, id);
                urgCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            UrgCtc idctc = urgCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getUrgCtcCie10Set().remove(urgCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(urgCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgCtcCie10> findUrgCtcCie10Entities() {
        return findUrgCtcCie10Entities(true, -1, -1);
    }

    public List<UrgCtcCie10> findUrgCtcCie10Entities(int maxResults, int firstResult) {
        return findUrgCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<UrgCtcCie10> findUrgCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgCtcCie10.class));
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

    public UrgCtcCie10 findUrgCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgCtcCie10> rt = cq.from(UrgCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
