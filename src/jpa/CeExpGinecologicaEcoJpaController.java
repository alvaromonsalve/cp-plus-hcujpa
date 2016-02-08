/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeExpGinecologicaEco;
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
 * @author IdlhDeveloper
 */
public class CeExpGinecologicaEcoJpaController implements Serializable {

    public CeExpGinecologicaEcoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeExpGinecologicaEco ceExpGinecologicaEco) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceExpGinecologicaEco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeExpGinecologicaEco ceExpGinecologicaEco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceExpGinecologicaEco = em.merge(ceExpGinecologicaEco);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceExpGinecologicaEco.getId();
                if (findCeExpGinecologicaEco(id) == null) {
                    throw new NonexistentEntityException("The ceExpGinecologicaEco with id " + id + " no longer exists.");
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
            CeExpGinecologicaEco ceExpGinecologicaEco;
            try {
                ceExpGinecologicaEco = em.getReference(CeExpGinecologicaEco.class, id);
                ceExpGinecologicaEco.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceExpGinecologicaEco with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceExpGinecologicaEco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeExpGinecologicaEco> findCeExpGinecologicaEcoEntities() {
        return findCeExpGinecologicaEcoEntities(true, -1, -1);
    }

    public List<CeExpGinecologicaEco> findCeExpGinecologicaEcoEntities(int maxResults, int firstResult) {
        return findCeExpGinecologicaEcoEntities(false, maxResults, firstResult);
    }

    private List<CeExpGinecologicaEco> findCeExpGinecologicaEcoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeExpGinecologicaEco.class));
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

    public CeExpGinecologicaEco findCeExpGinecologicaEco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeExpGinecologicaEco.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeExpGinecologicaEcoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeExpGinecologicaEco> rt = cq.from(CeExpGinecologicaEco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
