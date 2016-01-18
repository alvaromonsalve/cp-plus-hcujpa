/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospFinalizacionesOxigeno;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author JUDMEZ
 */
public class HospFinalizacionesOxigenoJpaController implements Serializable {

    public HospFinalizacionesOxigenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospFinalizacionesOxigeno hospFinalizacionesOxigeno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospFinalizacionesOxigeno hospFinalizacionesOxigeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospFinalizacionesOxigeno = em.merge(hospFinalizacionesOxigeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospFinalizacionesOxigeno.getId();
                if (findHospFinalizacionesOxigeno(id) == null) {
                    throw new NonexistentEntityException("The hospFinalizacionesOxigeno with id " + id + " no longer exists.");
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
            HospFinalizacionesOxigeno hospFinalizacionesOxigeno;
            try {
                hospFinalizacionesOxigeno = em.getReference(HospFinalizacionesOxigeno.class, id);
                hospFinalizacionesOxigeno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospFinalizacionesOxigeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospFinalizacionesOxigeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospFinalizacionesOxigeno> findHospFinalizacionesOxigenoEntities() {
        return findHospFinalizacionesOxigenoEntities(true, -1, -1);
    }

    public List<HospFinalizacionesOxigeno> findHospFinalizacionesOxigenoEntities(int maxResults, int firstResult) {
        return findHospFinalizacionesOxigenoEntities(false, maxResults, firstResult);
    }

    private List<HospFinalizacionesOxigeno> findHospFinalizacionesOxigenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospFinalizacionesOxigeno.class));
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

    public HospFinalizacionesOxigeno findHospFinalizacionesOxigeno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospFinalizacionesOxigeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospFinalizacionesOxigenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospFinalizacionesOxigeno> rt = cq.from(HospFinalizacionesOxigeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
   public List<HospFinalizacionesOxigeno>getFinalizados(int h){
        EntityManager em=getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT o FROM HospFinalizacionesOxigeno o WHERE o.idAplicacion.idHistoriac.id=:historia AND o.idAplicacion.estado='2' AND o.estado='1'");
        Q.setParameter("historia", h);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
    public List<HospFinalizacionesOxigeno>getFinalizacion(int i){
        EntityManager em=getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT o FROM HospFinalizacionesOxigeno o WHERE o.idAplicacion.id=:i AND o.estado='1'");
        Q.setParameter("i", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
    public Object getCountFinalizacionO(int i){
        EntityManager em=getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT COUNT(o.id) FROM HospFinalizacionesOxigeno o WHERE o.idAplicacion.id=:ia AND o.estado='1'");
        Q.setParameter("ia", i);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getSingleResult();
    }
}
