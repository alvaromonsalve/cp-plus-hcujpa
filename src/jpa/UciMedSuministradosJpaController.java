/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFactsNotas;
import entidades_EJB.UciMedSuministrados;
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
public class UciMedSuministradosJpaController implements Serializable {

    public UciMedSuministradosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciMedSuministrados uciMedSuministrados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciMedSuministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciMedSuministrados uciMedSuministrados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciMedSuministrados = em.merge(uciMedSuministrados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciMedSuministrados.getId();
                if (findUciMedSuministrados(id) == null) {
                    throw new NonexistentEntityException("The uciMedSuministrados with id " + id + " no longer exists.");
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
            UciMedSuministrados uciMedSuministrados;
            try {
                uciMedSuministrados = em.getReference(UciMedSuministrados.class, id);
                uciMedSuministrados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciMedSuministrados with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciMedSuministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciMedSuministrados> findUciMedSuministradosEntities() {
        return findUciMedSuministradosEntities(true, -1, -1);
    }

    public List<UciMedSuministrados> findUciMedSuministradosEntities(int maxResults, int firstResult) {
        return findUciMedSuministradosEntities(false, maxResults, firstResult);
    }

    private List<UciMedSuministrados> findUciMedSuministradosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciMedSuministrados.class));
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

    public UciMedSuministrados findUciMedSuministrados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciMedSuministrados.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciMedSuministradosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciMedSuministrados> rt = cq.from(UciMedSuministrados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UciMedSuministrados get_Medicamentos(UciFactsNotas n) {
        UciMedSuministrados med;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT m FROM UciMedSuministrados m WHERE m.idFactsNotas=:no AND m.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            med = (UciMedSuministrados) results.get(0);
        } else {
            med = null;
        }
        return med;
    }

}
