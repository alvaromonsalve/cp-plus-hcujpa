/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospAplicacionesOxigeno;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author JUDMEZ
 */
public class HospAplicacionesOxigenoJpaController implements Serializable {

    public HospAplicacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospAplicacionesOxigeno hospAplicacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospAplicacionesOxigeno hospAplicacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospAplicacionesOxigeno = em.merge(hospAplicacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospAplicacionesOxigeno.getId();
                if (findHospAplicacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The hospAplicacionesOxigeno with id " + id + " no longer exists.");
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
            HospAplicacionesOxigeno hospAplicacionesOxigeno;
            try {
                hospAplicacionesOxigeno = em.getReference(HospAplicacionesOxigeno.class, id);
                hospAplicacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospAplicacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospAplicacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospAplicacionesOxigeno> findHospAplicacionesOxigenoEntities() {
        return findHospAplicacionesOxigenoEntities(true, -1, -1);
    }

    public List<HospAplicacionesOxigeno> findHospAplicacionesOxigenoEntities(int maxResults, int firstResult) {
        return findHospAplicacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<HospAplicacionesOxigeno> findHospAplicacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospAplicacionesOxigeno.class));
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

    public HospAplicacionesOxigeno findHospAplicacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospAplicacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospAplicacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospAplicacionesOxigeno> rt = cq.from(HospAplicacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<HospAplicacionesOxigeno>find_aplicacionesO2(int hc){
     EntityManager em = getEntityManager();
     Query Q=null;
        try {
            Q=em.createQuery("SELECT i FROM HospAplicacionesOxigeno i WHERE (i.idHistoriac.id=:idhc) AND (i.estado='1')");
            Q.setParameter("idhc", hc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
         } 
        return Q.getResultList();
    }
    public List<HospAplicacionesOxigeno>get_AplicacionesOxigeno(int h){
        Query Q=null;
        EntityManager em=getEntityManager();
        try {
            Q=em.createQuery("SELECT O FROM HospAplicacionesOxigeno O WHERE O.idHistoriac.id=:hc AND O.estado <> '0'");
            Q.setParameter("hc", h);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }
}
