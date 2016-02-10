/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciHojaTratamientos;
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
public class UciHojaTratamientosJpaController implements Serializable {

    public UciHojaTratamientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciHojaTratamientos uciHojaTratamientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciHojaTratamientos uciHojaTratamientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciHojaTratamientos = em.merge(uciHojaTratamientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciHojaTratamientos.getId();
                if (findUciHojaTratamientos(id) == null) {
                    throw new NonexistentEntityException("The uciHojaTratamientos with id " + id + " no longer exists.");
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
            UciHojaTratamientos uciHojaTratamientos;
            try {
                uciHojaTratamientos = em.getReference(UciHojaTratamientos.class, id);
                uciHojaTratamientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciHojaTratamientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciHojaTratamientos> findUciHojaTratamientosEntities() {
        return findUciHojaTratamientosEntities(true, -1, -1);
    }

    public List<UciHojaTratamientos> findUciHojaTratamientosEntities(int maxResults, int firstResult) {
        return findUciHojaTratamientosEntities(false, maxResults, firstResult);
    }

    private List<UciHojaTratamientos> findUciHojaTratamientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciHojaTratamientos.class));
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

    public UciHojaTratamientos findUciHojaTratamientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciHojaTratamientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciHojaTratamientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciHojaTratamientos> rt = cq.from(UciHojaTratamientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciHojaTratamientos> getAplicaciones(Integer hc, Integer sum, Integer tipo, Integer ident) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM UciHojaTratamientos a WHERE a.idHistoria.id =:hc AND a.idSuministro.id =:sum AND a.tipo =:tipo AND a.identificador =:ident AND a.estado='1'")
                    .setParameter("hc", hc)
                    .setParameter("sum", sum)
                    .setParameter("tipo", tipo)
                    .setParameter("ident", ident)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
//        Query q = null;
//        q = em.createQuery("SELECT a FROM UciHojaTratamientos a WHERE a.idHistoria.id=:h AND a.idSuministro.id=:s AND a.tipo=:t AND a.identificador=:i AND a.estado=1");
//        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
//        q.setParameter("h", hc);
//        q.setParameter("s", sum);
//        q.setParameter("t", tipo);
//        q.setParameter("i", ident);
//        return q.getResultList();
    }

    public List<UciHojaTratamientos> getAplicaciones2(int hc) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT a FROM UciHojaTratamientos a WHERE a.idHistoria=:h AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        return q.getResultList();
    }
}
