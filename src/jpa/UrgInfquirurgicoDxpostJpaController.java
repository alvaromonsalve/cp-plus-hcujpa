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
import entidades_EJB.UrgInfquirurgicoDxpost;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgInfquirurgicoDxpostJpaController implements Serializable {

    public UrgInfquirurgicoDxpostJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgInfquirurgicoDxpost urgInfquirurgicoDxpost) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgico idinfquirurgico = urgInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                urgInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(urgInfquirurgicoDxpost);
            if (idinfquirurgico != null) {
                idinfquirurgico.getUrgInfquirurgicoDxpostList().add(urgInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgInfquirurgicoDxpost urgInfquirurgicoDxpost) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgicoDxpost persistentUrgInfquirurgicoDxpost = em.find(UrgInfquirurgicoDxpost.class, urgInfquirurgicoDxpost.getId());
            UrgInfquirurgico idinfquirurgicoOld = persistentUrgInfquirurgicoDxpost.getIdinfquirurgico();
            UrgInfquirurgico idinfquirurgicoNew = urgInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                urgInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgicoNew);
            }
            urgInfquirurgicoDxpost = em.merge(urgInfquirurgicoDxpost);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getUrgInfquirurgicoDxpostList().remove(urgInfquirurgicoDxpost);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getUrgInfquirurgicoDxpostList().add(urgInfquirurgicoDxpost);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgInfquirurgicoDxpost.getId();
                if (findUrgInfquirurgicoDxpost(id) == null) {
                    throw new NonexistentEntityException("The urgInfquirurgicoDxpost with id " + id + " no longer exists.");
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
            UrgInfquirurgicoDxpost urgInfquirurgicoDxpost;
            try {
                urgInfquirurgicoDxpost = em.getReference(UrgInfquirurgicoDxpost.class, id);
                urgInfquirurgicoDxpost.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgInfquirurgicoDxpost with id " + id + " no longer exists.", enfe);
            }
            UrgInfquirurgico idinfquirurgico = urgInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getUrgInfquirurgicoDxpostList().remove(urgInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(urgInfquirurgicoDxpost);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgInfquirurgicoDxpost> findUrgInfquirurgicoDxpostEntities() {
        return findUrgInfquirurgicoDxpostEntities(true, -1, -1);
    }

    public List<UrgInfquirurgicoDxpost> findUrgInfquirurgicoDxpostEntities(int maxResults, int firstResult) {
        return findUrgInfquirurgicoDxpostEntities(false, maxResults, firstResult);
    }

    private List<UrgInfquirurgicoDxpost> findUrgInfquirurgicoDxpostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgInfquirurgicoDxpost.class));
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

    public UrgInfquirurgicoDxpost findUrgInfquirurgicoDxpost(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgInfquirurgicoDxpost.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgInfquirurgicoDxpostCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgInfquirurgicoDxpost> rt = cq.from(UrgInfquirurgicoDxpost.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
