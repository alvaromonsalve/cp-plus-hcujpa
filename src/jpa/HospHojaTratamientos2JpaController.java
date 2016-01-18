/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospHojaTratamientos2;
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
public class HospHojaTratamientos2JpaController implements Serializable {

    public HospHojaTratamientos2JpaController(EntityManagerFactory emf) {
         this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospHojaTratamientos2 hospHojaTratamientos2) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospHojaTratamientos2 hospHojaTratamientos2) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospHojaTratamientos2 = em.merge(hospHojaTratamientos2);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospHojaTratamientos2.getId();
                if (findHospHojaTratamientos2(id) == null) {
                    throw new NonexistentEntityException("The hospHojaTratamientos2 with id " + id + " no longer exists.");
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
            HospHojaTratamientos2 hospHojaTratamientos2;
            try {
                hospHojaTratamientos2 = em.getReference(HospHojaTratamientos2.class, id);
                hospHojaTratamientos2.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospHojaTratamientos2 with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospHojaTratamientos2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospHojaTratamientos2> findHospHojaTratamientos2Entities() {
        return findHospHojaTratamientos2Entities(true, -1, -1);
    }

    public List<HospHojaTratamientos2> findHospHojaTratamientos2Entities(int maxResults, int firstResult) {
        return findHospHojaTratamientos2Entities(false, maxResults, firstResult);
    }

    private List<HospHojaTratamientos2> findHospHojaTratamientos2Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospHojaTratamientos2.class));
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

    public HospHojaTratamientos2 findHospHojaTratamientos2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospHojaTratamientos2.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospHojaTratamientos2Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospHojaTratamientos2> rt = cq.from(HospHojaTratamientos2.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<HospHojaTratamientos2>getTratamientos2(int h, int e, int i){
        Query Q=null;
        EntityManager em=getEntityManager();
        Q=em.createQuery("SELECT t FROM HospHojaTratamientos2 t WHERE t.idHistoria.id=:hist AND t.idIngOEvo=:ev AND t.identificador=:ident AND t.estado='1' ");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");    
        Q.setParameter("hist", h);
        Q.setParameter("ev", e);
        Q.setParameter("ident", i);
        return Q.getResultList();
    }
}
