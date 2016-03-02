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
import entidades_EJB.HospInfquirurgicoCups;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospInfquirurgicoCupsJpaController implements Serializable {

    public HospInfquirurgicoCupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInfquirurgicoCups hospInfquirurgicoCups) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgico idquirurgico = hospInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                hospInfquirurgicoCups.setIdquirurgico(idquirurgico);
            }
            em.persist(hospInfquirurgicoCups);
            if (idquirurgico != null) {
                idquirurgico.getHospInfquirurgicoCupsList().add(hospInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInfquirurgicoCups hospInfquirurgicoCups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgicoCups persistentHospInfquirurgicoCups = em.find(HospInfquirurgicoCups.class, hospInfquirurgicoCups.getId());
            HospInfquirurgico idquirurgicoOld = persistentHospInfquirurgicoCups.getIdquirurgico();
            HospInfquirurgico idquirurgicoNew = hospInfquirurgicoCups.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                hospInfquirurgicoCups.setIdquirurgico(idquirurgicoNew);
            }
            hospInfquirurgicoCups = em.merge(hospInfquirurgicoCups);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getHospInfquirurgicoCupsList().remove(hospInfquirurgicoCups);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getHospInfquirurgicoCupsList().add(hospInfquirurgicoCups);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInfquirurgicoCups.getId();
                if (findHospInfquirurgicoCups(id) == null) {
                    throw new NonexistentEntityException("The hospInfquirurgicoCups with id " + id + " no longer exists.");
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
            HospInfquirurgicoCups hospInfquirurgicoCups;
            try {
                hospInfquirurgicoCups = em.getReference(HospInfquirurgicoCups.class, id);
                hospInfquirurgicoCups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInfquirurgicoCups with id " + id + " no longer exists.", enfe);
            }
            HospInfquirurgico idquirurgico = hospInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getHospInfquirurgicoCupsList().remove(hospInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(hospInfquirurgicoCups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInfquirurgicoCups> findHospInfquirurgicoCupsEntities() {
        return findHospInfquirurgicoCupsEntities(true, -1, -1);
    }

    public List<HospInfquirurgicoCups> findHospInfquirurgicoCupsEntities(int maxResults, int firstResult) {
        return findHospInfquirurgicoCupsEntities(false, maxResults, firstResult);
    }

    private List<HospInfquirurgicoCups> findHospInfquirurgicoCupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInfquirurgicoCups.class));
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

    public HospInfquirurgicoCups findHospInfquirurgicoCups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInfquirurgicoCups.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInfquirurgicoCupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInfquirurgicoCups> rt = cq.from(HospInfquirurgicoCups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
