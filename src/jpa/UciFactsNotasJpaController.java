/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFactsNotas;
import entidades_EJB.UciHistoriac;
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
public class UciFactsNotasJpaController implements Serializable {

    public UciFactsNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciFactsNotas uciFactsNotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciFactsNotas uciFactsNotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciFactsNotas = em.merge(uciFactsNotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciFactsNotas.getId();
                if (findUciFactsNotas(id) == null) {
                    throw new NonexistentEntityException("The uciFactsNotas with id " + id + " no longer exists.");
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
            UciFactsNotas uciFactsNotas;
            try {
                uciFactsNotas = em.getReference(UciFactsNotas.class, id);
                uciFactsNotas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciFactsNotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciFactsNotas> findUciFactsNotasEntities() {
        return findUciFactsNotasEntities(true, -1, -1);
    }

    public List<UciFactsNotas> findUciFactsNotasEntities(int maxResults, int firstResult) {
        return findUciFactsNotasEntities(false, maxResults, firstResult);
    }

    private List<UciFactsNotas> findUciFactsNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciFactsNotas.class));
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

    public UciFactsNotas findUciFactsNotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciFactsNotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciFactsNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciFactsNotas> rt = cq.from(UciFactsNotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciFactsNotas> getNotasUCI(int historia) {
        EntityManager em = getEntityManager();
        Query q = null;
        try {
            q = em.createQuery("SELECT n FROM UciFactsNotas n WHERE n.idHistoriac.id=:h AND n.estado='1'");
            q.setParameter("h", historia);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<UciFactsNotas> find_Notas(UciHistoriac hi) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT i FROM UciFactsNotas i WHERE i.idHistoriac=:h AND i.estado='1'");
        Q.setParameter("h", hi);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<UciFactsNotas> find_Notas2(UciHistoriac hi) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT i FROM UciFactsNotas i WHERE i.idHistoriac=:h");
        Q.setParameter("h", hi);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
