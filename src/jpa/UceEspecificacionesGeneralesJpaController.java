/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceEspecificacionesGenerales;
import entidades_EJB.UceFactsNotas;
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
public class UceEspecificacionesGeneralesJpaController implements Serializable {

    public UceEspecificacionesGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceEspecificacionesGenerales uceEspecificacionesGenerales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceEspecificacionesGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceEspecificacionesGenerales uceEspecificacionesGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceEspecificacionesGenerales = em.merge(uceEspecificacionesGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceEspecificacionesGenerales.getId();
                if (findUceEspecificacionesGenerales(id) == null) {
                    throw new NonexistentEntityException("The uceEspecificacionesGenerales with id " + id + " no longer exists.");
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
            UceEspecificacionesGenerales uceEspecificacionesGenerales;
            try {
                uceEspecificacionesGenerales = em.getReference(UceEspecificacionesGenerales.class, id);
                uceEspecificacionesGenerales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceEspecificacionesGenerales with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceEspecificacionesGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceEspecificacionesGenerales> findUceEspecificacionesGeneralesEntities() {
        return findUceEspecificacionesGeneralesEntities(true, -1, -1);
    }

    public List<UceEspecificacionesGenerales> findUceEspecificacionesGeneralesEntities(int maxResults, int firstResult) {
        return findUceEspecificacionesGeneralesEntities(false, maxResults, firstResult);
    }

    private List<UceEspecificacionesGenerales> findUceEspecificacionesGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceEspecificacionesGenerales.class));
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

    public UceEspecificacionesGenerales findUceEspecificacionesGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceEspecificacionesGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceEspecificacionesGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceEspecificacionesGenerales> rt = cq.from(UceEspecificacionesGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public UceEspecificacionesGenerales getEspecificaciones(UceFactsNotas n){
        UceEspecificacionesGenerales espgen;    
        EntityManager em= getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT es FROM UceEspecificacionesGenerales es WHERE es.idFactsNotas=:no AND es.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
          if(!results.isEmpty()){
               espgen = (UceEspecificacionesGenerales) results.get(0);
          }else{
               espgen = null;
          }
          return espgen;
      } 
}
