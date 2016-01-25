/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceAplicacionesOxigeno;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UceAplicacionesOxigenoJpaController implements Serializable {

    public UceAplicacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceAplicacionesOxigeno uceAplicacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceAplicacionesOxigeno uceAplicacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceAplicacionesOxigeno = em.merge(uceAplicacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceAplicacionesOxigeno.getId();
                if (findUceAplicacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The uceAplicacionesOxigeno with id " + id + " no longer exists.");
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
            UceAplicacionesOxigeno uceAplicacionesOxigeno;
            try {
                uceAplicacionesOxigeno = em.getReference(UceAplicacionesOxigeno.class, id);
                uceAplicacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceAplicacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceAplicacionesOxigeno> findUceAplicacionesOxigenoEntities() {
        return findUceAplicacionesOxigenoEntities(true, -1, -1);
    }

    public List<UceAplicacionesOxigeno> findUceAplicacionesOxigenoEntities(int maxResults, int firstResult) {
        return findUceAplicacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<UceAplicacionesOxigeno> findUceAplicacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceAplicacionesOxigeno.class));
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

    public UceAplicacionesOxigeno findUceAplicacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceAplicacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceAplicacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceAplicacionesOxigeno> rt = cq.from(UceAplicacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceAplicacionesOxigeno> find_aplicacionesO2(int hc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM UceAplicacionesOxigeno i WHERE (i.idHistoriac.id=:idhc) AND (i.estado='1')");
            Q.setParameter("idhc", hc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return Q.getResultList();
    }

    public List<UceAplicacionesOxigeno> get_AplicacionesOxigeno(int h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        try {
            Q = em.createQuery("SELECT O FROM UceAplicacionesOxigeno O WHERE O.idHistoriac.id=:hc AND O.estado <> '0'");
            Q.setParameter("hc", h);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }
}
