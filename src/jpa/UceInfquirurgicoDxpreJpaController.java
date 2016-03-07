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
import entidades_EJB.UceInfquirurgico;
import entidades_EJB.UceInfquirurgicoDxpre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceInfquirurgicoDxpreJpaController implements Serializable {

    public UceInfquirurgicoDxpreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInfquirurgicoDxpre uceInfquirurgicoDxpre) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgico idinfquirurgico = uceInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                uceInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(uceInfquirurgicoDxpre);
            if (idinfquirurgico != null) {
                idinfquirurgico.getUceInfquirurgicoDxpreList().add(uceInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInfquirurgicoDxpre uceInfquirurgicoDxpre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgicoDxpre persistentUceInfquirurgicoDxpre = em.find(UceInfquirurgicoDxpre.class, uceInfquirurgicoDxpre.getId());
            UceInfquirurgico idinfquirurgicoOld = persistentUceInfquirurgicoDxpre.getIdinfquirurgico();
            UceInfquirurgico idinfquirurgicoNew = uceInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                uceInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgicoNew);
            }
            uceInfquirurgicoDxpre = em.merge(uceInfquirurgicoDxpre);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getUceInfquirurgicoDxpreList().remove(uceInfquirurgicoDxpre);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getUceInfquirurgicoDxpreList().add(uceInfquirurgicoDxpre);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInfquirurgicoDxpre.getId();
                if (findUceInfquirurgicoDxpre(id) == null) {
                    throw new NonexistentEntityException("The uceInfquirurgicoDxpre with id " + id + " no longer exists.");
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
            UceInfquirurgicoDxpre uceInfquirurgicoDxpre;
            try {
                uceInfquirurgicoDxpre = em.getReference(UceInfquirurgicoDxpre.class, id);
                uceInfquirurgicoDxpre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInfquirurgicoDxpre with id " + id + " no longer exists.", enfe);
            }
            UceInfquirurgico idinfquirurgico = uceInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getUceInfquirurgicoDxpreList().remove(uceInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(uceInfquirurgicoDxpre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInfquirurgicoDxpre> findUceInfquirurgicoDxpreEntities() {
        return findUceInfquirurgicoDxpreEntities(true, -1, -1);
    }

    public List<UceInfquirurgicoDxpre> findUceInfquirurgicoDxpreEntities(int maxResults, int firstResult) {
        return findUceInfquirurgicoDxpreEntities(false, maxResults, firstResult);
    }

    private List<UceInfquirurgicoDxpre> findUceInfquirurgicoDxpreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInfquirurgicoDxpre.class));
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

    public UceInfquirurgicoDxpre findUceInfquirurgicoDxpre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInfquirurgicoDxpre.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInfquirurgicoDxpreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInfquirurgicoDxpre> rt = cq.from(UceInfquirurgicoDxpre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
