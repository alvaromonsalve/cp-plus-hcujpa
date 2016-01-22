/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFactsNotas;
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
public class UceFactsNotasJpaController implements Serializable {

    public UceFactsNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceFactsNotas uceFactsNotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceFactsNotas uceFactsNotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceFactsNotas = em.merge(uceFactsNotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceFactsNotas.getId();
                if (findUceFactsNotas(id) == null) {
                    throw new NonexistentEntityException("The uceFactsNotas with id " + id + " no longer exists.");
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
            UceFactsNotas uceFactsNotas;
            try {
                uceFactsNotas = em.getReference(UceFactsNotas.class, id);
                uceFactsNotas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceFactsNotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceFactsNotas> findUceFactsNotasEntities() {
        return findUceFactsNotasEntities(true, -1, -1);
    }

    public List<UceFactsNotas> findUceFactsNotasEntities(int maxResults, int firstResult) {
        return findUceFactsNotasEntities(false, maxResults, firstResult);
    }

    private List<UceFactsNotas> findUceFactsNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceFactsNotas.class));
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

    public UceFactsNotas findUceFactsNotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceFactsNotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceFactsNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceFactsNotas> rt = cq.from(UceFactsNotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceFactsNotas> getNotasUCE(int historia) {
        EntityManager em = getEntityManager();
        Query q = null;
        try {
            q = em.createQuery("SELECT n FROM UceFactsNotas n WHERE n.idHistoriac.id=:h AND n.estado='1'");
            q.setParameter("h", historia);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
