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
import entidades_EJB.UrgIngCtc;
import entidades_EJB.UrgIngCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngCtcCie10JpaController implements Serializable {

    public UrgIngCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngCtcCie10 urgIngCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtc idctc = urgIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                urgIngCtcCie10.setIdctc(idctc);
            }
            em.persist(urgIngCtcCie10);
            if (idctc != null) {
                idctc.getUrgIngCtcCie10List().add(urgIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngCtcCie10 urgIngCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtcCie10 persistentUrgIngCtcCie10 = em.find(UrgIngCtcCie10.class, urgIngCtcCie10.getId());
            UrgIngCtc idctcOld = persistentUrgIngCtcCie10.getIdctc();
            UrgIngCtc idctcNew = urgIngCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                urgIngCtcCie10.setIdctc(idctcNew);
            }
            urgIngCtcCie10 = em.merge(urgIngCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUrgIngCtcCie10List().remove(urgIngCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUrgIngCtcCie10List().add(urgIngCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngCtcCie10.getId();
                if (findUrgIngCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The urgIngCtcCie10 with id " + id + " no longer exists.");
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
            UrgIngCtcCie10 urgIngCtcCie10;
            try {
                urgIngCtcCie10 = em.getReference(UrgIngCtcCie10.class, id);
                urgIngCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            UrgIngCtc idctc = urgIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getUrgIngCtcCie10List().remove(urgIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(urgIngCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngCtcCie10> findUrgIngCtcCie10Entities() {
        return findUrgIngCtcCie10Entities(true, -1, -1);
    }

    public List<UrgIngCtcCie10> findUrgIngCtcCie10Entities(int maxResults, int firstResult) {
        return findUrgIngCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<UrgIngCtcCie10> findUrgIngCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngCtcCie10.class));
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

    public UrgIngCtcCie10 findUrgIngCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngCtcCie10> rt = cq.from(UrgIngCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
