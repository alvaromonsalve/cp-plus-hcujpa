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
import entidades_EJB.UciInfquirurgicoCups;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciInfquirurgicoCupsJpaController implements Serializable {

    public UciInfquirurgicoCupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInfquirurgicoCups uciInfquirurgicoCups) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgico idquirurgico = uciInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico = em.getReference(idquirurgico.getClass(), idquirurgico.getId());
                uciInfquirurgicoCups.setIdquirurgico(idquirurgico);
            }
            em.persist(uciInfquirurgicoCups);
            if (idquirurgico != null) {
                idquirurgico.getUciInfquirurgicoCupsList().add(uciInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInfquirurgicoCups uciInfquirurgicoCups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgicoCups persistentUciInfquirurgicoCups = em.find(UciInfquirurgicoCups.class, uciInfquirurgicoCups.getId());
            UciInfquirurgico idquirurgicoOld = persistentUciInfquirurgicoCups.getIdquirurgico();
            UciInfquirurgico idquirurgicoNew = uciInfquirurgicoCups.getIdquirurgico();
            if (idquirurgicoNew != null) {
                idquirurgicoNew = em.getReference(idquirurgicoNew.getClass(), idquirurgicoNew.getId());
                uciInfquirurgicoCups.setIdquirurgico(idquirurgicoNew);
            }
            uciInfquirurgicoCups = em.merge(uciInfquirurgicoCups);
            if (idquirurgicoOld != null && !idquirurgicoOld.equals(idquirurgicoNew)) {
                idquirurgicoOld.getUciInfquirurgicoCupsList().remove(uciInfquirurgicoCups);
                idquirurgicoOld = em.merge(idquirurgicoOld);
            }
            if (idquirurgicoNew != null && !idquirurgicoNew.equals(idquirurgicoOld)) {
                idquirurgicoNew.getUciInfquirurgicoCupsList().add(uciInfquirurgicoCups);
                idquirurgicoNew = em.merge(idquirurgicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInfquirurgicoCups.getId();
                if (findUciInfquirurgicoCups(id) == null) {
                    throw new NonexistentEntityException("The uciInfquirurgicoCups with id " + id + " no longer exists.");
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
            UciInfquirurgicoCups uciInfquirurgicoCups;
            try {
                uciInfquirurgicoCups = em.getReference(UciInfquirurgicoCups.class, id);
                uciInfquirurgicoCups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInfquirurgicoCups with id " + id + " no longer exists.", enfe);
            }
            UciInfquirurgico idquirurgico = uciInfquirurgicoCups.getIdquirurgico();
            if (idquirurgico != null) {
                idquirurgico.getUciInfquirurgicoCupsList().remove(uciInfquirurgicoCups);
                idquirurgico = em.merge(idquirurgico);
            }
            em.remove(uciInfquirurgicoCups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInfquirurgicoCups> findUciInfquirurgicoCupsEntities() {
        return findUciInfquirurgicoCupsEntities(true, -1, -1);
    }

    public List<UciInfquirurgicoCups> findUciInfquirurgicoCupsEntities(int maxResults, int firstResult) {
        return findUciInfquirurgicoCupsEntities(false, maxResults, firstResult);
    }

    private List<UciInfquirurgicoCups> findUciInfquirurgicoCupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInfquirurgicoCups.class));
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

    public UciInfquirurgicoCups findUciInfquirurgicoCups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInfquirurgicoCups.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInfquirurgicoCupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInfquirurgicoCups> rt = cq.from(UciInfquirurgicoCups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
