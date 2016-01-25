/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceHojaTratamientos2;
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
public class UceHojaTratamientos2JpaController implements Serializable {

    public UceHojaTratamientos2JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceHojaTratamientos2 uceHojaTratamientos2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceHojaTratamientos2 uceHojaTratamientos2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceHojaTratamientos2 = em.merge(uceHojaTratamientos2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceHojaTratamientos2.getId();
                if (findUceHojaTratamientos2(id) == null) {
                    throw new NonexistentEntityException("The uceHojaTratamientos2 with id " + id + " no longer exists.");
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
            UceHojaTratamientos2 uceHojaTratamientos2;
            try {
                uceHojaTratamientos2 = em.getReference(UceHojaTratamientos2.class, id);
                uceHojaTratamientos2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceHojaTratamientos2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceHojaTratamientos2> findUceHojaTratamientos2Entities() {
        return findUceHojaTratamientos2Entities(true, -1, -1);
    }

    public List<UceHojaTratamientos2> findUceHojaTratamientos2Entities(int maxResults, int firstResult) {
        return findUceHojaTratamientos2Entities(false, maxResults, firstResult);
    }

    private List<UceHojaTratamientos2> findUceHojaTratamientos2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceHojaTratamientos2.class));
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

    public UceHojaTratamientos2 findUceHojaTratamientos2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceHojaTratamientos2.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceHojaTratamientos2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceHojaTratamientos2> rt = cq.from(UceHojaTratamientos2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceHojaTratamientos2> getTratamientos2(int h, int e, int i) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT t FROM UceHojaTratamientos2 t WHERE t.idHistoria.id=:hist AND t.idIngOEvo=:ev AND t.identificador=:ident AND t.estado='1' ");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        Q.setParameter("hist", h);
        Q.setParameter("ev", e);
        Q.setParameter("ident", i);
        return Q.getResultList();
    }
}
