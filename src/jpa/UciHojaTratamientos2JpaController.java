/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciHojaTratamientos2;
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
public class UciHojaTratamientos2JpaController implements Serializable {

    public UciHojaTratamientos2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciHojaTratamientos2 uciHojaTratamientos2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciHojaTratamientos2 uciHojaTratamientos2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciHojaTratamientos2 = em.merge(uciHojaTratamientos2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciHojaTratamientos2.getId();
                if (findUciHojaTratamientos2(id) == null) {
                    throw new NonexistentEntityException("The uciHojaTratamientos2 with id " + id + " no longer exists.");
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
            UciHojaTratamientos2 uciHojaTratamientos2;
            try {
                uciHojaTratamientos2 = em.getReference(UciHojaTratamientos2.class, id);
                uciHojaTratamientos2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciHojaTratamientos2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciHojaTratamientos2> findUciHojaTratamientos2Entities() {
        return findUciHojaTratamientos2Entities(true, -1, -1);
    }

    public List<UciHojaTratamientos2> findUciHojaTratamientos2Entities(int maxResults, int firstResult) {
        return findUciHojaTratamientos2Entities(false, maxResults, firstResult);
    }

    private List<UciHojaTratamientos2> findUciHojaTratamientos2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciHojaTratamientos2.class));
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

    public UciHojaTratamientos2 findUciHojaTratamientos2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciHojaTratamientos2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciHojaTratamientos2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciHojaTratamientos2> rt = cq.from(UciHojaTratamientos2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciHojaTratamientos2> getTratamientos2(int h, int e, int i) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT t FROM UciHojaTratamientos2 t WHERE t.idHistoria.id=:hist AND t.idIngOEvo=:ev AND t.identificador=:ident AND t.estado='1' ");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        Q.setParameter("hist", h);
        Q.setParameter("ev", e);
        Q.setParameter("ident", i);
        return Q.getResultList();
    }
}
