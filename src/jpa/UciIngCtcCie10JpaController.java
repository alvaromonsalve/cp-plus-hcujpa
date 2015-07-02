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
import entidades_EJB.UciIngCtc;
import entidades_EJB.UciIngCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngCtcCie10JpaController implements Serializable {

    public UciIngCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngCtcCie10 uciIngCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtc idctc = uciIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uciIngCtcCie10.setIdctc(idctc);
            }
            em.persist(uciIngCtcCie10);
            if (idctc != null) {
                idctc.getUciIngCtcCie10List().add(uciIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngCtcCie10 uciIngCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtcCie10 persistentUciIngCtcCie10 = em.find(UciIngCtcCie10.class, uciIngCtcCie10.getId());
            UciIngCtc idctcOld = persistentUciIngCtcCie10.getIdctc();
            UciIngCtc idctcNew = uciIngCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uciIngCtcCie10.setIdctc(idctcNew);
            }
            uciIngCtcCie10 = em.merge(uciIngCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUciIngCtcCie10List().remove(uciIngCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUciIngCtcCie10List().add(uciIngCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngCtcCie10.getId();
                if (findUciIngCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The uciIngCtcCie10 with id " + id + " no longer exists.");
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
            UciIngCtcCie10 uciIngCtcCie10;
            try {
                uciIngCtcCie10 = em.getReference(UciIngCtcCie10.class, id);
                uciIngCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            UciIngCtc idctc = uciIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getUciIngCtcCie10List().remove(uciIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(uciIngCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngCtcCie10> findUciIngCtcCie10Entities() {
        return findUciIngCtcCie10Entities(true, -1, -1);
    }

    public List<UciIngCtcCie10> findUciIngCtcCie10Entities(int maxResults, int firstResult) {
        return findUciIngCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<UciIngCtcCie10> findUciIngCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngCtcCie10.class));
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

    public UciIngCtcCie10 findUciIngCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngCtcCie10> rt = cq.from(UciIngCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
