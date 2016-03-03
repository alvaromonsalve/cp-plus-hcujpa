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
import entidades_EJB.UrgInfquirurgicoDxpre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgInfquirurgicoDxpreJpaController implements Serializable {

    public UrgInfquirurgicoDxpreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgInfquirurgicoDxpre urgInfquirurgicoDxpre) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgico idinfquirurgico = urgInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                urgInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(urgInfquirurgicoDxpre);
            if (idinfquirurgico != null) {
                idinfquirurgico.getUrgInfquirurgicoDxpreList().add(urgInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgInfquirurgicoDxpre urgInfquirurgicoDxpre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgicoDxpre persistentUrgInfquirurgicoDxpre = em.find(UrgInfquirurgicoDxpre.class, urgInfquirurgicoDxpre.getId());
            UrgInfquirurgico idinfquirurgicoOld = persistentUrgInfquirurgicoDxpre.getIdinfquirurgico();
            UrgInfquirurgico idinfquirurgicoNew = urgInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                urgInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgicoNew);
            }
            urgInfquirurgicoDxpre = em.merge(urgInfquirurgicoDxpre);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getUrgInfquirurgicoDxpreList().remove(urgInfquirurgicoDxpre);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getUrgInfquirurgicoDxpreList().add(urgInfquirurgicoDxpre);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgInfquirurgicoDxpre.getId();
                if (findUrgInfquirurgicoDxpre(id) == null) {
                    throw new NonexistentEntityException("The urgInfquirurgicoDxpre with id " + id + " no longer exists.");
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
            UrgInfquirurgicoDxpre urgInfquirurgicoDxpre;
            try {
                urgInfquirurgicoDxpre = em.getReference(UrgInfquirurgicoDxpre.class, id);
                urgInfquirurgicoDxpre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgInfquirurgicoDxpre with id " + id + " no longer exists.", enfe);
            }
            UrgInfquirurgico idinfquirurgico = urgInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getUrgInfquirurgicoDxpreList().remove(urgInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(urgInfquirurgicoDxpre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgInfquirurgicoDxpre> findUrgInfquirurgicoDxpreEntities() {
        return findUrgInfquirurgicoDxpreEntities(true, -1, -1);
    }

    public List<UrgInfquirurgicoDxpre> findUrgInfquirurgicoDxpreEntities(int maxResults, int firstResult) {
        return findUrgInfquirurgicoDxpreEntities(false, maxResults, firstResult);
    }

    private List<UrgInfquirurgicoDxpre> findUrgInfquirurgicoDxpreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgInfquirurgicoDxpre.class));
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

    public UrgInfquirurgicoDxpre findUrgInfquirurgicoDxpre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgInfquirurgicoDxpre.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgInfquirurgicoDxpreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgInfquirurgicoDxpre> rt = cq.from(UrgInfquirurgicoDxpre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
