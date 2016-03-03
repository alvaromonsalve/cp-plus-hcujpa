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
import entidades_EJB.UciInfquirurgico;
import entidades_EJB.UciInfquirurgicoDxpost;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciInfquirurgicoDxpostJpaController implements Serializable {

    public UciInfquirurgicoDxpostJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInfquirurgicoDxpost uciInfquirurgicoDxpost) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgico idinfquirurgico = uciInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                uciInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(uciInfquirurgicoDxpost);
            if (idinfquirurgico != null) {
                idinfquirurgico.getUciInfquirurgicoDxpostList().add(uciInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInfquirurgicoDxpost uciInfquirurgicoDxpost) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgicoDxpost persistentUciInfquirurgicoDxpost = em.find(UciInfquirurgicoDxpost.class, uciInfquirurgicoDxpost.getId());
            UciInfquirurgico idinfquirurgicoOld = persistentUciInfquirurgicoDxpost.getIdinfquirurgico();
            UciInfquirurgico idinfquirurgicoNew = uciInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                uciInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgicoNew);
            }
            uciInfquirurgicoDxpost = em.merge(uciInfquirurgicoDxpost);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getUciInfquirurgicoDxpostList().remove(uciInfquirurgicoDxpost);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getUciInfquirurgicoDxpostList().add(uciInfquirurgicoDxpost);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInfquirurgicoDxpost.getId();
                if (findUciInfquirurgicoDxpost(id) == null) {
                    throw new NonexistentEntityException("The uciInfquirurgicoDxpost with id " + id + " no longer exists.");
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
            UciInfquirurgicoDxpost uciInfquirurgicoDxpost;
            try {
                uciInfquirurgicoDxpost = em.getReference(UciInfquirurgicoDxpost.class, id);
                uciInfquirurgicoDxpost.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInfquirurgicoDxpost with id " + id + " no longer exists.", enfe);
            }
            UciInfquirurgico idinfquirurgico = uciInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getUciInfquirurgicoDxpostList().remove(uciInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(uciInfquirurgicoDxpost);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInfquirurgicoDxpost> findUciInfquirurgicoDxpostEntities() {
        return findUciInfquirurgicoDxpostEntities(true, -1, -1);
    }

    public List<UciInfquirurgicoDxpost> findUciInfquirurgicoDxpostEntities(int maxResults, int firstResult) {
        return findUciInfquirurgicoDxpostEntities(false, maxResults, firstResult);
    }

    private List<UciInfquirurgicoDxpost> findUciInfquirurgicoDxpostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInfquirurgicoDxpost.class));
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

    public UciInfquirurgicoDxpost findUciInfquirurgicoDxpost(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInfquirurgicoDxpost.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInfquirurgicoDxpostCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInfquirurgicoDxpost> rt = cq.from(UciInfquirurgicoDxpost.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
