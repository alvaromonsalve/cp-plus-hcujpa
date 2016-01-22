/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFactsNotas;
import entidades_EJB.UciGlasgow;
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
public class UciGlasgowJpaController implements Serializable {

    public UciGlasgowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciGlasgow uciGlasgow) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciGlasgow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciGlasgow uciGlasgow) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciGlasgow = em.merge(uciGlasgow);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciGlasgow.getId();
                if (findUciGlasgow(id) == null) {
                    throw new NonexistentEntityException("The uciGlasgow with id " + id + " no longer exists.");
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
            UciGlasgow uciGlasgow;
            try {
                uciGlasgow = em.getReference(UciGlasgow.class, id);
                uciGlasgow.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciGlasgow with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciGlasgow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciGlasgow> findUciGlasgowEntities() {
        return findUciGlasgowEntities(true, -1, -1);
    }

    public List<UciGlasgow> findUciGlasgowEntities(int maxResults, int firstResult) {
        return findUciGlasgowEntities(false, maxResults, firstResult);
    }

    private List<UciGlasgow> findUciGlasgowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciGlasgow.class));
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

    public UciGlasgow findUciGlasgow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciGlasgow.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciGlasgowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciGlasgow> rt = cq.from(UciGlasgow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UciGlasgow get_glassgow(UciFactsNotas n) {
        UciGlasgow glass;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT g FROM UciGlasgow g WHERE g.idFactsNotas=:no AND g.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            glass = (UciGlasgow) results.get(0);
        } else {
            glass = null;
        }
        em.close();
        return glass;
    }
}
