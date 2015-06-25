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
import entidades_EJB.HospCtc;
import entidades_EJB.HospCtcCie10;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospCtcCie10JpaController implements Serializable {

    public HospCtcCie10JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospCtcCie10 hospCtcCie10) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtc idctc = hospCtcCie10.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                hospCtcCie10.setIdctc(idctc);
            }
            em.persist(hospCtcCie10);
            if (idctc != null) {
                idctc.getHospCtcCie10List().add(hospCtcCie10);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospCtcCie10 hospCtcCie10) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtcCie10 persistentHospCtcCie10 = em.find(HospCtcCie10.class, hospCtcCie10.getId());
            HospCtc idctcOld = persistentHospCtcCie10.getIdctc();
            HospCtc idctcNew = hospCtcCie10.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                hospCtcCie10.setIdctc(idctcNew);
            }
            hospCtcCie10 = em.merge(hospCtcCie10);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getHospCtcCie10List().remove(hospCtcCie10);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getHospCtcCie10List().add(hospCtcCie10);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospCtcCie10.getId();
                if (findHospCtcCie10(id) == null) {
                    throw new NonexistentEntityException("The hospCtcCie10 with id " + id + " no longer exists.");
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
            HospCtcCie10 hospCtcCie10;
            try {
                hospCtcCie10 = em.getReference(HospCtcCie10.class, id);
                hospCtcCie10.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospCtcCie10 with id " + id + " no longer exists.", enfe);
            }
            HospCtc idctc = hospCtcCie10.getIdctc();
            if (idctc != null) {
                idctc.getHospCtcCie10List().remove(hospCtcCie10);
                idctc = em.merge(idctc);
            }
            em.remove(hospCtcCie10);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospCtcCie10> findHospCtcCie10Entities() {
        return findHospCtcCie10Entities(true, -1, -1);
    }

    public List<HospCtcCie10> findHospCtcCie10Entities(int maxResults, int firstResult) {
        return findHospCtcCie10Entities(false, maxResults, firstResult);
    }

    private List<HospCtcCie10> findHospCtcCie10Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospCtcCie10.class));
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

    public HospCtcCie10 findHospCtcCie10(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospCtcCie10.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospCtcCie10Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospCtcCie10> rt = cq.from(HospCtcCie10.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
