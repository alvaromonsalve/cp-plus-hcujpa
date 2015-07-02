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
import entidades_EJB.HospIngCtc;
import entidades_EJB.HospIngCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngCtcCie10JpaController implements Serializable {

    public HospIngCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngCtcCie10 hospIngCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtc idctc = hospIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                hospIngCtcCie10.setIdctc(idctc);
            }
            em.persist(hospIngCtcCie10);
            if (idctc != null) {
                idctc.getHospIngCtcCie10List().add(hospIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngCtcCie10 hospIngCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtcCie10 persistentHospIngCtcCie10 = em.find(HospIngCtcCie10.class, hospIngCtcCie10.getId());
            HospIngCtc idctcOld = persistentHospIngCtcCie10.getIdctc();
            HospIngCtc idctcNew = hospIngCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                hospIngCtcCie10.setIdctc(idctcNew);
            }
            hospIngCtcCie10 = em.merge(hospIngCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getHospIngCtcCie10List().remove(hospIngCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getHospIngCtcCie10List().add(hospIngCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngCtcCie10.getId();
                if (findHospIngCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The hospIngCtcCie10 with id " + id + " no longer exists.");
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
            HospIngCtcCie10 hospIngCtcCie10;
            try {
                hospIngCtcCie10 = em.getReference(HospIngCtcCie10.class, id);
                hospIngCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            HospIngCtc idctc = hospIngCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getHospIngCtcCie10List().remove(hospIngCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(hospIngCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngCtcCie10> findHospIngCtcCie10Entities() {
        return findHospIngCtcCie10Entities(true, -1, -1);
    }

    public List<HospIngCtcCie10> findHospIngCtcCie10Entities(int maxResults, int firstResult) {
        return findHospIngCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<HospIngCtcCie10> findHospIngCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngCtcCie10.class));
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

    public HospIngCtcCie10 findHospIngCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngCtcCie10> rt = cq.from(HospIngCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
