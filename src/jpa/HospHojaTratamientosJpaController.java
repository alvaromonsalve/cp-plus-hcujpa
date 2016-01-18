/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospHojaTratamientos;
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
 * @author IdlhDeveloper
 */
public class HospHojaTratamientosJpaController implements Serializable {

    public HospHojaTratamientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospHojaTratamientos hospHojaTratamientos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospHojaTratamientos hospHojaTratamientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospHojaTratamientos = em.merge(hospHojaTratamientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospHojaTratamientos.getId();
                if (findHospHojaTratamientos(id) == null) {
                    throw new NonexistentEntityException("The hospHojaTratamientos with id " + id + " no longer exists.");
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
            HospHojaTratamientos hospHojaTratamientos;
            try {
                hospHojaTratamientos = em.getReference(HospHojaTratamientos.class, id);
                hospHojaTratamientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospHojaTratamientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospHojaTratamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospHojaTratamientos> findHospHojaTratamientosEntities() {
        return findHospHojaTratamientosEntities(true, -1, -1);
    }

    public List<HospHojaTratamientos> findHospHojaTratamientosEntities(int maxResults, int firstResult) {
        return findHospHojaTratamientosEntities(false, maxResults, firstResult);
    }

    private List<HospHojaTratamientos> findHospHojaTratamientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospHojaTratamientos.class));
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

    public HospHojaTratamientos findHospHojaTratamientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospHojaTratamientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospHojaTratamientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospHojaTratamientos> rt = cq.from(HospHojaTratamientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<HospHojaTratamientos>getAplicaciones(int hc, int sum, int tipo, int ident){
        EntityManager em=getEntityManager();
        Query q=null;
        q=em.createQuery("SELECT a FROM HospHojaTratamientos a WHERE a.idHistoria.id=:h AND a.idSuministro.id=:s AND a.tipo=:t AND a.identificador=:i AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        q.setParameter("s", sum);
        q.setParameter("t", tipo);
        q.setParameter("i", ident);
        return q.getResultList();
    }
    public List<HospHojaTratamientos>getAplicaciones2(int hc){
        EntityManager em=getEntityManager();
        Query q=null;
        q=em.createQuery("SELECT a FROM HospHojaTratamientos a WHERE a.idHistoria=:h AND a.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", hc);
        return q.getResultList();
    }
}
