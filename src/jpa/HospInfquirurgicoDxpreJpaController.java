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
import entidades_EJB.HospInfquirurgico;
import entidades_EJB.HospInfquirurgicoDxpre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospInfquirurgicoDxpreJpaController implements Serializable {

    public HospInfquirurgicoDxpreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInfquirurgicoDxpre hospInfquirurgicoDxpre) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgico idinfquirurgico = hospInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                hospInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(hospInfquirurgicoDxpre);
            if (idinfquirurgico != null) {
                idinfquirurgico.getHospInfquirurgicoDxpreList().add(hospInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInfquirurgicoDxpre hospInfquirurgicoDxpre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgicoDxpre persistentHospInfquirurgicoDxpre = em.find(HospInfquirurgicoDxpre.class, hospInfquirurgicoDxpre.getId());
            HospInfquirurgico idinfquirurgicoOld = persistentHospInfquirurgicoDxpre.getIdinfquirurgico();
            HospInfquirurgico idinfquirurgicoNew = hospInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                hospInfquirurgicoDxpre.setIdinfquirurgico(idinfquirurgicoNew);
            }
            hospInfquirurgicoDxpre = em.merge(hospInfquirurgicoDxpre);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getHospInfquirurgicoDxpreList().remove(hospInfquirurgicoDxpre);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getHospInfquirurgicoDxpreList().add(hospInfquirurgicoDxpre);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInfquirurgicoDxpre.getId();
                if (findHospInfquirurgicoDxpre(id) == null) {
                    throw new NonexistentEntityException("The hospInfquirurgicoDxpre with id " + id + " no longer exists.");
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
            HospInfquirurgicoDxpre hospInfquirurgicoDxpre;
            try {
                hospInfquirurgicoDxpre = em.getReference(HospInfquirurgicoDxpre.class, id);
                hospInfquirurgicoDxpre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInfquirurgicoDxpre with id " + id + " no longer exists.", enfe);
            }
            HospInfquirurgico idinfquirurgico = hospInfquirurgicoDxpre.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getHospInfquirurgicoDxpreList().remove(hospInfquirurgicoDxpre);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(hospInfquirurgicoDxpre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInfquirurgicoDxpre> findHospInfquirurgicoDxpreEntities() {
        return findHospInfquirurgicoDxpreEntities(true, -1, -1);
    }

    public List<HospInfquirurgicoDxpre> findHospInfquirurgicoDxpreEntities(int maxResults, int firstResult) {
        return findHospInfquirurgicoDxpreEntities(false, maxResults, firstResult);
    }

    private List<HospInfquirurgicoDxpre> findHospInfquirurgicoDxpreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInfquirurgicoDxpre.class));
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

    public HospInfquirurgicoDxpre findHospInfquirurgicoDxpre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInfquirurgicoDxpre.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInfquirurgicoDxpreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInfquirurgicoDxpre> rt = cq.from(HospInfquirurgicoDxpre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
