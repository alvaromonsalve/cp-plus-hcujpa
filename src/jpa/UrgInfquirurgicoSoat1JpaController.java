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
import entidades_EJB.UrgInfquirurgicoSoat1;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgInfquirurgicoSoat1JpaController implements Serializable {

    public UrgInfquirurgicoSoat1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgInfquirurgicoSoat1 urgInfquirurgicoSoat1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgico idinforme = urgInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme = em.getReference(idinforme.getClass(), idinforme.getId());
                urgInfquirurgicoSoat1.setIdinforme(idinforme);
            }
            em.persist(urgInfquirurgicoSoat1);
            if (idinforme != null) {
                idinforme.getUrgInfquirurgicoSoat1List().add(urgInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgInfquirurgicoSoat1 urgInfquirurgicoSoat1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgicoSoat1 persistentUrgInfquirurgicoSoat1 = em.find(UrgInfquirurgicoSoat1.class, urgInfquirurgicoSoat1.getId());
            UrgInfquirurgico idinformeOld = persistentUrgInfquirurgicoSoat1.getIdinforme();
            UrgInfquirurgico idinformeNew = urgInfquirurgicoSoat1.getIdinforme();
            if (idinformeNew != null) {
                idinformeNew = em.getReference(idinformeNew.getClass(), idinformeNew.getId());
                urgInfquirurgicoSoat1.setIdinforme(idinformeNew);
            }
            urgInfquirurgicoSoat1 = em.merge(urgInfquirurgicoSoat1);
            if (idinformeOld != null && !idinformeOld.equals(idinformeNew)) {
                idinformeOld.getUrgInfquirurgicoSoat1List().remove(urgInfquirurgicoSoat1);
                idinformeOld = em.merge(idinformeOld);
            }
            if (idinformeNew != null && !idinformeNew.equals(idinformeOld)) {
                idinformeNew.getUrgInfquirurgicoSoat1List().add(urgInfquirurgicoSoat1);
                idinformeNew = em.merge(idinformeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgInfquirurgicoSoat1.getId();
                if (findUrgInfquirurgicoSoat1(id) == null) {
                    throw new NonexistentEntityException("The urgInfquirurgicoSoat1 with id " + id + " no longer exists.");
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
            UrgInfquirurgicoSoat1 urgInfquirurgicoSoat1;
            try {
                urgInfquirurgicoSoat1 = em.getReference(UrgInfquirurgicoSoat1.class, id);
                urgInfquirurgicoSoat1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgInfquirurgicoSoat1 with id " + id + " no longer exists.", enfe);
            }
            UrgInfquirurgico idinforme = urgInfquirurgicoSoat1.getIdinforme();
            if (idinforme != null) {
                idinforme.getUrgInfquirurgicoSoat1List().remove(urgInfquirurgicoSoat1);
                idinforme = em.merge(idinforme);
            }
            em.remove(urgInfquirurgicoSoat1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgInfquirurgicoSoat1> findUrgInfquirurgicoSoat1Entities() {
        return findUrgInfquirurgicoSoat1Entities(true, -1, -1);
    }

    public List<UrgInfquirurgicoSoat1> findUrgInfquirurgicoSoat1Entities(int maxResults, int firstResult) {
        return findUrgInfquirurgicoSoat1Entities(false, maxResults, firstResult);
    }

    private List<UrgInfquirurgicoSoat1> findUrgInfquirurgicoSoat1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgInfquirurgicoSoat1.class));
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

    public UrgInfquirurgicoSoat1 findUrgInfquirurgicoSoat1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgInfquirurgicoSoat1.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgInfquirurgicoSoat1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgInfquirurgicoSoat1> rt = cq.from(UrgInfquirurgicoSoat1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
