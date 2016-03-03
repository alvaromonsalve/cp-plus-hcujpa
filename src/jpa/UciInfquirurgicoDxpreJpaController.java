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
import entidades_EJB.UciInfquirurgicoDxpre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciInfquirurgicoDxpreJpaController implements Serializable {

    public UciInfquirurgicoDxpreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInfquirurgicoDxpre uciInfquirurgicoDxpre) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgico idinfquirurgico = uciInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                uciInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(uciInfquirurgicoDxpre);
            if (idinfquirurgico != null) {
                idinfquirurgico.getUciInfquirurgicoDxpreList().add(uciInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInfquirurgicoDxpre uciInfquirurgicoDxpre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgicoDxpre persistentUciInfquirurgicoDxpre = em.find(UciInfquirurgicoDxpre.class, uciInfquirurgicoDxpre.getId());
            UciInfquirurgico idinfquirurgicoOld = persistentUciInfquirurgicoDxpre.getIdinfquirurgico();
            UciInfquirurgico idinfquirurgicoNew = uciInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                uciInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgicoNew);
            }
            uciInfquirurgicoDxpre = em.merge(uciInfquirurgicoDxpre);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getUciInfquirurgicoDxpreList().remove(uciInfquirurgicoDxpre);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getUciInfquirurgicoDxpreList().add(uciInfquirurgicoDxpre);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInfquirurgicoDxpre.getId();
                if (findUciInfquirurgicoDxpre(id) == null) {
                    throw new NonexistentEntityException("The uciInfquirurgicoDxpre with id " + id + " no longer exists.");
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
            UciInfquirurgicoDxpre uciInfquirurgicoDxpre;
            try {
                uciInfquirurgicoDxpre = em.getReference(UciInfquirurgicoDxpre.class, id);
                uciInfquirurgicoDxpre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInfquirurgicoDxpre with id " + id + " no longer exists.", enfe);
            }
            UciInfquirurgico idinfquirurgico = uciInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getUciInfquirurgicoDxpreList().remove(uciInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(uciInfquirurgicoDxpre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInfquirurgicoDxpre> findUciInfquirurgicoDxpreEntities() {
        return findUciInfquirurgicoDxpreEntities(true, -1, -1);
    }

    public List<UciInfquirurgicoDxpre> findUciInfquirurgicoDxpreEntities(int maxResults, int firstResult) {
        return findUciInfquirurgicoDxpreEntities(false, maxResults, firstResult);
    }

    private List<UciInfquirurgicoDxpre> findUciInfquirurgicoDxpreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInfquirurgicoDxpre.class));
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

    public UciInfquirurgicoDxpre findUciInfquirurgicoDxpre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInfquirurgicoDxpre.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInfquirurgicoDxpreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInfquirurgicoDxpre> rt = cq.from(UciInfquirurgicoDxpre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
