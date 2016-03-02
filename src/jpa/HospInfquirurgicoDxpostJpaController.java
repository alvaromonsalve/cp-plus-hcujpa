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
import entidades_EJB.HospInfquirurgicoDxpost;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospInfquirurgicoDxpostJpaController implements Serializable {

    public HospInfquirurgicoDxpostJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInfquirurgicoDxpost hospInfquirurgicoDxpost) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgico idinfquirurgico = hospInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico = em.getReference(idinfquirurgico.getClass(), idinfquirurgico.getId());
                hospInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgico);
            }
            em.persist(hospInfquirurgicoDxpost);
            if (idinfquirurgico != null) {
                idinfquirurgico.getHospInfquirurgicoDxpostList().add(hospInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInfquirurgicoDxpost hospInfquirurgicoDxpost) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgicoDxpost persistentHospInfquirurgicoDxpost = em.find(HospInfquirurgicoDxpost.class, hospInfquirurgicoDxpost.getId());
            HospInfquirurgico idinfquirurgicoOld = persistentHospInfquirurgicoDxpost.getIdinfquirurgico();
            HospInfquirurgico idinfquirurgicoNew = hospInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgicoNew != null) {
                idinfquirurgicoNew = em.getReference(idinfquirurgicoNew.getClass(), idinfquirurgicoNew.getId());
                hospInfquirurgicoDxpost.setIdinfquirurgico(idinfquirurgicoNew);
            }
            hospInfquirurgicoDxpost = em.merge(hospInfquirurgicoDxpost);
            if (idinfquirurgicoOld != null && !idinfquirurgicoOld.equals(idinfquirurgicoNew)) {
                idinfquirurgicoOld.getHospInfquirurgicoDxpostList().remove(hospInfquirurgicoDxpost);
                idinfquirurgicoOld = em.merge(idinfquirurgicoOld);
            }
            if (idinfquirurgicoNew != null && !idinfquirurgicoNew.equals(idinfquirurgicoOld)) {
                idinfquirurgicoNew.getHospInfquirurgicoDxpostList().add(hospInfquirurgicoDxpost);
                idinfquirurgicoNew = em.merge(idinfquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInfquirurgicoDxpost.getId();
                if (findHospInfquirurgicoDxpost(id) == null) {
                    throw new NonexistentEntityException("The hospInfquirurgicoDxpost with id " + id + " no longer exists.");
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
            HospInfquirurgicoDxpost hospInfquirurgicoDxpost;
            try {
                hospInfquirurgicoDxpost = em.getReference(HospInfquirurgicoDxpost.class, id);
                hospInfquirurgicoDxpost.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInfquirurgicoDxpost with id " + id + " no longer exists.", enfe);
            }
            HospInfquirurgico idinfquirurgico = hospInfquirurgicoDxpost.getIdinfquirurgico();
            if (idinfquirurgico != null) {
                idinfquirurgico.getHospInfquirurgicoDxpostList().remove(hospInfquirurgicoDxpost);
                idinfquirurgico = em.merge(idinfquirurgico);
            }
            em.remove(hospInfquirurgicoDxpost);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInfquirurgicoDxpost> findHospInfquirurgicoDxpostEntities() {
        return findHospInfquirurgicoDxpostEntities(true, -1, -1);
    }

    public List<HospInfquirurgicoDxpost> findHospInfquirurgicoDxpostEntities(int maxResults, int firstResult) {
        return findHospInfquirurgicoDxpostEntities(false, maxResults, firstResult);
    }

    private List<HospInfquirurgicoDxpost> findHospInfquirurgicoDxpostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInfquirurgicoDxpost.class));
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

    public HospInfquirurgicoDxpost findHospInfquirurgicoDxpost(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInfquirurgicoDxpost.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInfquirurgicoDxpostCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInfquirurgicoDxpost> rt = cq.from(HospInfquirurgicoDxpost.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
