/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.AuHistorias;
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
public class AuHistoriasJpaController implements Serializable {

    public AuHistoriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AuHistorias auHistorias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(auHistorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AuHistorias auHistorias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            auHistorias = em.merge(auHistorias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = auHistorias.getId();
                if (findAuHistorias(id) == null) {
                    throw new NonexistentEntityException("The auHistorias with id " + id + " no longer exists.");
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
            AuHistorias auHistorias;
            try {
                auHistorias = em.getReference(AuHistorias.class, id);
                auHistorias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auHistorias with id " + id + " no longer exists.", enfe);
            }
            em.remove(auHistorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AuHistorias> findAuHistoriasEntities() {
        return findAuHistoriasEntities(true, -1, -1);
    }

    public List<AuHistorias> findAuHistoriasEntities(int maxResults, int firstResult) {
        return findAuHistoriasEntities(false, maxResults, firstResult);
    }

    private List<AuHistorias> findAuHistoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuHistorias.class));
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

    public AuHistorias findAuHistorias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuHistorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuHistoriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuHistorias> rt = cq.from(AuHistorias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
