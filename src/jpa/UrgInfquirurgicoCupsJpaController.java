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
import entidades_EJB.UrgInfquirurgico;
import entidades_EJB.UrgInfquirurgicoCups;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgInfquirurgicoCupsJpaController implements Serializable {

    public UrgInfquirurgicoCupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgInfquirurgicoCups urgInfquirurgicoCups) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgico idquirurgico = urgInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                urgInfquirurgicoCups.setIdquirurgico(idquirurgico);
            }
            em.persist(urgInfquirurgicoCups);
            if (idquirurgico != null) {
                idquirurgico.getUrgInfquirurgicoCupsList().add(urgInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgInfquirurgicoCups urgInfquirurgicoCups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgicoCups persistentUrgInfquirurgicoCups = em.find(UrgInfquirurgicoCups.class, urgInfquirurgicoCups.getId());
            UrgInfquirurgico idquirurgicoOld = persistentUrgInfquirurgicoCups.getIdquirurgico();
            UrgInfquirurgico idquirurgicoNew = urgInfquirurgicoCups.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                urgInfquirurgicoCups.setIdquirurgico(idquirurgicoNew);
            }
            urgInfquirurgicoCups = em.merge(urgInfquirurgicoCups);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getUrgInfquirurgicoCupsList().remove(urgInfquirurgicoCups);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getUrgInfquirurgicoCupsList().add(urgInfquirurgicoCups);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgInfquirurgicoCups.getId();
                if (findUrgInfquirurgicoCups(id) == null) {
                    throw new NonexistentEntityException("The urgInfquirurgicoCups with id " + id + " no longer exists.");
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
            UrgInfquirurgicoCups urgInfquirurgicoCups;
            try {
                urgInfquirurgicoCups = em.getReference(UrgInfquirurgicoCups.class, id);
                urgInfquirurgicoCups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgInfquirurgicoCups with id " + id + " no longer exists.", enfe);
            }
            UrgInfquirurgico idquirurgico = urgInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getUrgInfquirurgicoCupsList().remove(urgInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(urgInfquirurgicoCups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgInfquirurgicoCups> findUrgInfquirurgicoCupsEntities() {
        return findUrgInfquirurgicoCupsEntities(true, -1, -1);
    }

    public List<UrgInfquirurgicoCups> findUrgInfquirurgicoCupsEntities(int maxResults, int firstResult) {
        return findUrgInfquirurgicoCupsEntities(false, maxResults, firstResult);
    }

    private List<UrgInfquirurgicoCups> findUrgInfquirurgicoCupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgInfquirurgicoCups.class));
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

    public UrgInfquirurgicoCups findUrgInfquirurgicoCups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgInfquirurgicoCups.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgInfquirurgicoCupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgInfquirurgicoCups> rt = cq.from(UrgInfquirurgicoCups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
