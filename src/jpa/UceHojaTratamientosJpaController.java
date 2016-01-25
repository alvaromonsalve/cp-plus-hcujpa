/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceHojaTratamientos;
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
public class UceHojaTratamientosJpaController implements Serializable {

    public UceHojaTratamientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceHojaTratamientos uceHojaTratamientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceHojaTratamientos uceHojaTratamientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceHojaTratamientos = em.merge(uceHojaTratamientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceHojaTratamientos.getId();
                if (findUceHojaTratamientos(id) == null) {
                    throw new NonexistentEntityException("The uceHojaTratamientos with id " + id + " no longer exists.");
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
            UceHojaTratamientos uceHojaTratamientos;
            try {
                uceHojaTratamientos = em.getReference(UceHojaTratamientos.class, id);
                uceHojaTratamientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceHojaTratamientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceHojaTratamientos> findUceHojaTratamientosEntities() {
        return findUceHojaTratamientosEntities(true, -1, -1);
    }

    public List<UceHojaTratamientos> findUceHojaTratamientosEntities(int maxResults, int firstResult) {
        return findUceHojaTratamientosEntities(false, maxResults, firstResult);
    }

    private List<UceHojaTratamientos> findUceHojaTratamientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceHojaTratamientos.class));
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

    public UceHojaTratamientos findUceHojaTratamientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceHojaTratamientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceHojaTratamientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceHojaTratamientos> rt = cq.from(UceHojaTratamientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceHojaTratamientos> getAplicaciones(int hc, int sum, int tipo, int ident) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT a FROM UceHojaTratamientos a WHERE a.idHistoria.id=:h AND a.idSuministro.id=:s AND a.tipo=:t AND a.identificador=:i AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        q.setParameter("s", sum);
        q.setParameter("t", tipo);
        q.setParameter("i", ident);
        return q.getResultList();
    }

    public List<UceHojaTratamientos> getAplicaciones2(int hc) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT a FROM UceHojaTratamientos a WHERE a.idHistoria=:h AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        return q.getResultList();
    }
}
