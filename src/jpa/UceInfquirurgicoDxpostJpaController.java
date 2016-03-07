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
import entidades_EJB.UceInfquirurgicoDxpost;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceInfquirurgicoDxpostJpaController implements Serializable {

    public UceInfquirurgicoDxpostJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInfquirurgicoDxpost uceInfquirurgicoDxpost) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgico idinfquirurgico = uceInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                uceInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(uceInfquirurgicoDxpost);
            if (idinfquirurgico != null) {
                idinfquirurgico.getUceInfquirurgicoDxpostList().add(uceInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInfquirurgicoDxpost uceInfquirurgicoDxpost) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgicoDxpost persistentUceInfquirurgicoDxpost = em.find(UceInfquirurgicoDxpost.class, uceInfquirurgicoDxpost.getId());
            UceInfquirurgico idinfquirurgicoOld = persistentUceInfquirurgicoDxpost.getIdinfquirurgico();
            UceInfquirurgico idinfquirurgicoNew = uceInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                uceInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgicoNew);
            }
            uceInfquirurgicoDxpost = em.merge(uceInfquirurgicoDxpost);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getUceInfquirurgicoDxpostList().remove(uceInfquirurgicoDxpost);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getUceInfquirurgicoDxpostList().add(uceInfquirurgicoDxpost);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInfquirurgicoDxpost.getId();
                if (findUceInfquirurgicoDxpost(id) == null) {
                    throw new NonexistentEntityException("The uceInfquirurgicoDxpost with id " + id + " no longer exists.");
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
            UceInfquirurgicoDxpost uceInfquirurgicoDxpost;
            try {
                uceInfquirurgicoDxpost = em.getReference(UceInfquirurgicoDxpost.class, id);
                uceInfquirurgicoDxpost.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInfquirurgicoDxpost with id " + id + " no longer exists.", enfe);
            }
            UceInfquirurgico idinfquirurgico = uceInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getUceInfquirurgicoDxpostList().remove(uceInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(uceInfquirurgicoDxpost);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInfquirurgicoDxpost> findUceInfquirurgicoDxpostEntities() {
        return findUceInfquirurgicoDxpostEntities(true, -1, -1);
    }

    public List<UceInfquirurgicoDxpost> findUceInfquirurgicoDxpostEntities(int maxResults, int firstResult) {
        return findUceInfquirurgicoDxpostEntities(false, maxResults, firstResult);
    }

    private List<UceInfquirurgicoDxpost> findUceInfquirurgicoDxpostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInfquirurgicoDxpost.class));
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

    public UceInfquirurgicoDxpost findUceInfquirurgicoDxpost(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInfquirurgicoDxpost.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInfquirurgicoDxpostCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInfquirurgicoDxpost> rt = cq.from(UceInfquirurgicoDxpost.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
