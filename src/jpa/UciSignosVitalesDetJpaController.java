/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciFactsNotas;
import entidades_EJB.UciHistoriac;
import entidades_EJB.UciSignosVitalesDet;
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
public class UciSignosVitalesDetJpaController implements Serializable {

    public UciSignosVitalesDetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciSignosVitalesDet uciSignosVitalesDet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciSignosVitalesDet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciSignosVitalesDet uciSignosVitalesDet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciSignosVitalesDet = em.merge(uciSignosVitalesDet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciSignosVitalesDet.getId();
                if (findUciSignosVitalesDet(id) == null) {
                    throw new NonexistentEntityException("The uciSignosVitalesDet with id " + id + " no longer exists.");
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
            UciSignosVitalesDet uciSignosVitalesDet;
            try {
                uciSignosVitalesDet = em.getReference(UciSignosVitalesDet.class, id);
                uciSignosVitalesDet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciSignosVitalesDet with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciSignosVitalesDet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciSignosVitalesDet> findUciSignosVitalesDetEntities() {
        return findUciSignosVitalesDetEntities(true, -1, -1);
    }

    public List<UciSignosVitalesDet> findUciSignosVitalesDetEntities(int maxResults, int firstResult) {
        return findUciSignosVitalesDetEntities(false, maxResults, firstResult);
    }

    private List<UciSignosVitalesDet> findUciSignosVitalesDetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciSignosVitalesDet.class));
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

    public UciSignosVitalesDet findUciSignosVitalesDet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciSignosVitalesDet.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciSignosVitalesDetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciSignosVitalesDet> rt = cq.from(UciSignosVitalesDet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UciSignosVitalesDet get_Signos(UciFactsNotas n) {
        UciSignosVitalesDet signos;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT s FROM UciSignosVitalesDet s WHERE s.idFactsNotas=:no AND s.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            signos = (UciSignosVitalesDet) results.get(0);
        } else {
            signos = null;
        }
        return signos;
    }

    public List<UciSignosVitalesDet> get_SignosHist(UciHistoriac n) {
        UciSignosVitalesDet signos;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT s FROM UciSignosVitalesDet s WHERE s.idFactsNotas.idHistoriac=:h AND s.estado='1' GROUP BY s.idFactsNotas.fecha");
        Q.setParameter("h", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
