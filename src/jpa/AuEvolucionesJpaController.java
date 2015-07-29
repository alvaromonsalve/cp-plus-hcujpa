/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.AuEvoluciones;
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
 * @author Juan Camilo
 */
public class AuEvolucionesJpaController implements Serializable {

    public AuEvolucionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AuEvoluciones auEvoluciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(auEvoluciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AuEvoluciones auEvoluciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            auEvoluciones = em.merge(auEvoluciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = auEvoluciones.getId();
                if (findAuEvoluciones(id) == null) {
                    throw new NonexistentEntityException("The auEvoluciones with id " + id + " no longer exists.");
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
            AuEvoluciones auEvoluciones;
            try {
                auEvoluciones = em.getReference(AuEvoluciones.class, id);
                auEvoluciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auEvoluciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(auEvoluciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AuEvoluciones> findAuEvolucionesEntities() {
        return findAuEvolucionesEntities(true, -1, -1);
    }

    public List<AuEvoluciones> findAuEvolucionesEntities(int maxResults, int firstResult) {
        return findAuEvolucionesEntities(false, maxResults, firstResult);
    }

    private List<AuEvoluciones> findAuEvolucionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuEvoluciones.class));
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

    public AuEvoluciones findAuEvoluciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuEvoluciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuEvolucionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuEvoluciones> rt = cq.from(AuEvoluciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
