/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.InfoPaciente;
import entidades_EJB.PypInfoAntecedentesg;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class PypInfoAntecedentesgJpaController implements Serializable {

    public PypInfoAntecedentesgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PypInfoAntecedentesg pypInfoAntecedentesg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pypInfoAntecedentesg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PypInfoAntecedentesg pypInfoAntecedentesg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pypInfoAntecedentesg = em.merge(pypInfoAntecedentesg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pypInfoAntecedentesg.getId();
                if (findPypInfoAntecedentesg(id) == null) {
                    throw new NonexistentEntityException("The pypInfoAntecedentesg with id " + id + " no longer exists.");
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
            PypInfoAntecedentesg pypInfoAntecedentesg;
            try {
                pypInfoAntecedentesg = em.getReference(PypInfoAntecedentesg.class, id);
                pypInfoAntecedentesg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pypInfoAntecedentesg with id " + id + " no longer exists.", enfe);
            }
            em.remove(pypInfoAntecedentesg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PypInfoAntecedentesg> findPypInfoAntecedentesgEntities() {
        return findPypInfoAntecedentesgEntities(true, -1, -1);
    }

    public List<PypInfoAntecedentesg> findPypInfoAntecedentesgEntities(int maxResults, int firstResult) {
        return findPypInfoAntecedentesgEntities(false, maxResults, firstResult);
    }

    private List<PypInfoAntecedentesg> findPypInfoAntecedentesgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PypInfoAntecedentesg.class));
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

    public PypInfoAntecedentesg findPypInfoAntecedentesg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PypInfoAntecedentesg.class, id);
        } finally {
            em.close();
        }
    }

    public int getPypInfoAntecedentesgCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PypInfoAntecedentesg> rt = cq.from(PypInfoAntecedentesg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Object countAntecedenteGineco(int id) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT COUNT(g.id) FROM PypInfoAntecedentesg g WHERE g.infoPaciente.id=:p");
        q.setParameter("p", id);
        return q.getSingleResult();
    }

    public PypInfoAntecedentesg getEntidadAntecedentesPersonales(InfoPaciente pa) {
        PypInfoAntecedentesg antp = null;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT p FROM PypInfoAntecedentesg p WHERE p.infoPaciente=:idp");
        Q.setParameter("idp", pa);
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            antp = (PypInfoAntecedentesg) results.get(0);
        } else {
            antp = null;
        }
        return antp;
    }

}
