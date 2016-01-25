/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFactsNotas;
import entidades_EJB.UceGlasgow;
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
public class UceGlasgowJpaController implements Serializable {

    public UceGlasgowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceGlasgow uceGlasgow) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceGlasgow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceGlasgow uceGlasgow) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceGlasgow = em.merge(uceGlasgow);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceGlasgow.getId();
                if (findUceGlasgow(id) == null) {
                    throw new NonexistentEntityException("The uceGlasgow with id " + id + " no longer exists.");
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
            UceGlasgow uceGlasgow;
            try {
                uceGlasgow = em.getReference(UceGlasgow.class, id);
                uceGlasgow.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceGlasgow with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceGlasgow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceGlasgow> findUceGlasgowEntities() {
        return findUceGlasgowEntities(true, -1, -1);
    }

    public List<UceGlasgow> findUceGlasgowEntities(int maxResults, int firstResult) {
        return findUceGlasgowEntities(false, maxResults, firstResult);
    }

    private List<UceGlasgow> findUceGlasgowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceGlasgow.class));
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

    public UceGlasgow findUceGlasgow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceGlasgow.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceGlasgowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceGlasgow> rt = cq.from(UceGlasgow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UceGlasgow get_glassgow(UceFactsNotas n) {
        UceGlasgow glass;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT g FROM UceGlasgow g WHERE g.idFactsNotas=:no AND g.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            glass = (UceGlasgow) results.get(0);
        } else {
            glass = null;
        }
        em.close();
        return glass;
    }
}
