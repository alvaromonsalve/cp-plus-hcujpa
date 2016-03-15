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
import entidades_EJB.UrgInfquirurgicoSoat2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgInfquirurgicoSoat2JpaController implements Serializable {

    public UrgInfquirurgicoSoat2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgInfquirurgicoSoat2 urgInfquirurgicoSoat2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgico idquirurgico = urgInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                urgInfquirurgicoSoat2.setIdquirurgico(idquirurgico);
            }
            em.persist(urgInfquirurgicoSoat2);
            if (idquirurgico != null) {
                idquirurgico.getUrgInfquirurgicoSoat2List().add(urgInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgInfquirurgicoSoat2 urgInfquirurgicoSoat2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgicoSoat2 persistentUrgInfquirurgicoSoat2 = em.find(UrgInfquirurgicoSoat2.class, urgInfquirurgicoSoat2.getId());
            UrgInfquirurgico idquirurgicoOld = persistentUrgInfquirurgicoSoat2.getIdquirurgico();
            UrgInfquirurgico idquirurgicoNew = urgInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                urgInfquirurgicoSoat2.setIdquirurgico(idquirurgicoNew);
            }
            urgInfquirurgicoSoat2 = em.merge(urgInfquirurgicoSoat2);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getUrgInfquirurgicoSoat2List().remove(urgInfquirurgicoSoat2);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getUrgInfquirurgicoSoat2List().add(urgInfquirurgicoSoat2);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgInfquirurgicoSoat2.getId();
                if (findUrgInfquirurgicoSoat2(id) == null) {
                    throw new NonexistentEntityException("The urgInfquirurgicoSoat2 with id " + id + " no longer exists.");
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
            UrgInfquirurgicoSoat2 urgInfquirurgicoSoat2;
            try {
                urgInfquirurgicoSoat2 = em.getReference(UrgInfquirurgicoSoat2.class, id);
                urgInfquirurgicoSoat2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgInfquirurgicoSoat2 with id " + id + " no longer exists.", enfe);
            }
            UrgInfquirurgico idquirurgico = urgInfquirurgicoSoat2.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getUrgInfquirurgicoSoat2List().remove(urgInfquirurgicoSoat2);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(urgInfquirurgicoSoat2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgInfquirurgicoSoat2> findUrgInfquirurgicoSoat2Entities() {
        return findUrgInfquirurgicoSoat2Entities(true, -1, -1);
    }

    public List<UrgInfquirurgicoSoat2> findUrgInfquirurgicoSoat2Entities(int maxResults, int firstResult) {
        return findUrgInfquirurgicoSoat2Entities(false, maxResults, firstResult);
    }

    private List<UrgInfquirurgicoSoat2> findUrgInfquirurgicoSoat2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgInfquirurgicoSoat2.class));
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

    public UrgInfquirurgicoSoat2 findUrgInfquirurgicoSoat2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgInfquirurgicoSoat2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgInfquirurgicoSoat2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgInfquirurgicoSoat2> rt = cq.from(UrgInfquirurgicoSoat2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
