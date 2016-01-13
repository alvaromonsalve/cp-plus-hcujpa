/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.EnfuAplicacionesOxigeno;
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
public class EnfuAplicacionesOxigenoJpaController implements Serializable {

    public EnfuAplicacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuAplicacionesOxigeno enfuAplicacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuAplicacionesOxigeno enfuAplicacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuAplicacionesOxigeno = em.merge(enfuAplicacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuAplicacionesOxigeno.getId();
                if (findEnfuAplicacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The enfuAplicacionesOxigeno with id " + id + " no longer exists.");
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
            EnfuAplicacionesOxigeno enfuAplicacionesOxigeno;
            try {
                enfuAplicacionesOxigeno = em.getReference(EnfuAplicacionesOxigeno.class, id);
                enfuAplicacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuAplicacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuAplicacionesOxigeno> findEnfuAplicacionesOxigenoEntities() {
        return findEnfuAplicacionesOxigenoEntities(true, -1, -1);
    }

    public List<EnfuAplicacionesOxigeno> findEnfuAplicacionesOxigenoEntities(int maxResults, int firstResult) {
        return findEnfuAplicacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<EnfuAplicacionesOxigeno> findEnfuAplicacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuAplicacionesOxigeno.class));
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

    public EnfuAplicacionesOxigeno findEnfuAplicacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuAplicacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuAplicacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuAplicacionesOxigeno> rt = cq.from(EnfuAplicacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuAplicacionesOxigeno> find_aplicacionesO2(int hc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM EnfuAplicacionesOxigeno i WHERE (i.idInfoHistoriac.id=:idhc) AND (i.estado='1')");
            Q.setParameter("idhc", hc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return Q.getResultList();
    }

    public List<EnfuAplicacionesOxigeno> get_AplicacionesOxigeno(int h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        try {
            Q = em.createQuery("SELECT O FROM EnfuAplicacionesOxigeno O WHERE O.idInfoHistoriac.id=:hc AND O.estado <> '0'");
            Q.setParameter("hc", h);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }
    
}
