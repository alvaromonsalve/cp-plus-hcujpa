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
import entidades_EJB.UceInfquirurgicoCups;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceInfquirurgicoCupsJpaController implements Serializable {

    public UceInfquirurgicoCupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInfquirurgicoCups uceInfquirurgicoCups) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgico idquirurgico = uceInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                uceInfquirurgicoCups.setIdquirurgico(idquirurgico);
            }
            em.persist(uceInfquirurgicoCups);
            if (idquirurgico != null) {
                idquirurgico.getUceInfquirurgicoCupsList().add(uceInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInfquirurgicoCups uceInfquirurgicoCups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgicoCups persistentUceInfquirurgicoCups = em.find(UceInfquirurgicoCups.class, uceInfquirurgicoCups.getId());
            UceInfquirurgico idquirurgicoOld = persistentUceInfquirurgicoCups.getIdquirurgico();
            UceInfquirurgico idquirurgicoNew = uceInfquirurgicoCups.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                uceInfquirurgicoCups.setIdquirurgico(idquirurgicoNew);
            }
            uceInfquirurgicoCups = em.merge(uceInfquirurgicoCups);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getUceInfquirurgicoCupsList().remove(uceInfquirurgicoCups);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getUceInfquirurgicoCupsList().add(uceInfquirurgicoCups);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInfquirurgicoCups.getId();
                if (findUceInfquirurgicoCups(id) == null) {
                    throw new NonexistentEntityException("The uceInfquirurgicoCups with id " + id + " no longer exists.");
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
            UceInfquirurgicoCups uceInfquirurgicoCups;
            try {
                uceInfquirurgicoCups = em.getReference(UceInfquirurgicoCups.class, id);
                uceInfquirurgicoCups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInfquirurgicoCups with id " + id + " no longer exists.", enfe);
            }
            UceInfquirurgico idquirurgico = uceInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getUceInfquirurgicoCupsList().remove(uceInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(uceInfquirurgicoCups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInfquirurgicoCups> findUceInfquirurgicoCupsEntities() {
        return findUceInfquirurgicoCupsEntities(true, -1, -1);
    }

    public List<UceInfquirurgicoCups> findUceInfquirurgicoCupsEntities(int maxResults, int firstResult) {
        return findUceInfquirurgicoCupsEntities(false, maxResults, firstResult);
    }

    private List<UceInfquirurgicoCups> findUceInfquirurgicoCupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInfquirurgicoCups.class));
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

    public UceInfquirurgicoCups findUceInfquirurgicoCups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInfquirurgicoCups.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInfquirurgicoCupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInfquirurgicoCups> rt = cq.from(UceInfquirurgicoCups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
