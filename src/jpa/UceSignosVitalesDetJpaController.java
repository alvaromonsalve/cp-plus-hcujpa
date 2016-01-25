/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFactsNotas;
import entidades_EJB.UceHistoriac;
import entidades_EJB.UceSignosVitalesDet;
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
public class UceSignosVitalesDetJpaController implements Serializable {

    public UceSignosVitalesDetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceSignosVitalesDet uceSignosVitalesDet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceSignosVitalesDet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceSignosVitalesDet uceSignosVitalesDet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceSignosVitalesDet = em.merge(uceSignosVitalesDet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceSignosVitalesDet.getId();
                if (findUceSignosVitalesDet(id) == null) {
                    throw new NonexistentEntityException("The uceSignosVitalesDet with id " + id + " no longer exists.");
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
            UceSignosVitalesDet uceSignosVitalesDet;
            try {
                uceSignosVitalesDet = em.getReference(UceSignosVitalesDet.class, id);
                uceSignosVitalesDet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceSignosVitalesDet with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceSignosVitalesDet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceSignosVitalesDet> findUceSignosVitalesDetEntities() {
        return findUceSignosVitalesDetEntities(true, -1, -1);
    }

    public List<UceSignosVitalesDet> findUceSignosVitalesDetEntities(int maxResults, int firstResult) {
        return findUceSignosVitalesDetEntities(false, maxResults, firstResult);
    }

    private List<UceSignosVitalesDet> findUceSignosVitalesDetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceSignosVitalesDet.class));
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

    public UceSignosVitalesDet findUceSignosVitalesDet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceSignosVitalesDet.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceSignosVitalesDetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceSignosVitalesDet> rt = cq.from(UceSignosVitalesDet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UceSignosVitalesDet get_Signos(UceFactsNotas n) {
        UceSignosVitalesDet signos;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT s FROM UceSignosVitalesDet s WHERE s.idFactsNotas=:no AND s.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            signos = (UceSignosVitalesDet) results.get(0);
        } else {
            signos = null;
        }
        return signos;
    }

    public List<UceSignosVitalesDet> get_SignosHist(UceHistoriac n) {
        UceSignosVitalesDet signos;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT s FROM UceSignosVitalesDet s WHERE s.idFactsNotas.idHistoriac=:h AND s.estado='1' GROUP BY s.idFactsNotas.fecha");
        Q.setParameter("h", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
