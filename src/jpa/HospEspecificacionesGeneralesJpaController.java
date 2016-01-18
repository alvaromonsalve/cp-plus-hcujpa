/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.HospEspecificacionesGenerales;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospFactsNotas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JUDMEZ
 */
public class HospEspecificacionesGeneralesJpaController implements Serializable {

    public HospEspecificacionesGeneralesJpaController(EntityManagerFactory emf) {
       this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospEspecificacionesGenerales hospEspecificacionesGenerales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospFactsNotas idFactsNotas = hospEspecificacionesGenerales.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                hospEspecificacionesGenerales.setIdFactsNotas(idFactsNotas);
            }
            em.persist(hospEspecificacionesGenerales);
            if (idFactsNotas != null) {
                idFactsNotas.getHospEspecificacionesGeneralesList().add(hospEspecificacionesGenerales);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospEspecificacionesGenerales hospEspecificacionesGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospEspecificacionesGenerales persistentHospEspecificacionesGenerales = em.find(HospEspecificacionesGenerales.class, hospEspecificacionesGenerales.getId());
            HospFactsNotas idFactsNotasOld = persistentHospEspecificacionesGenerales.getIdFactsNotas();
            HospFactsNotas idFactsNotasNew = hospEspecificacionesGenerales.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                hospEspecificacionesGenerales.setIdFactsNotas(idFactsNotasNew);
            }
            hospEspecificacionesGenerales = em.merge(hospEspecificacionesGenerales);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getHospEspecificacionesGeneralesList().remove(hospEspecificacionesGenerales);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getHospEspecificacionesGeneralesList().add(hospEspecificacionesGenerales);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospEspecificacionesGenerales.getId();
                if (findHospEspecificacionesGenerales(id) == null) {
                    throw new NonexistentEntityException("The hospEspecificacionesGenerales with id " + id + " no longer exists.");
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
            HospEspecificacionesGenerales hospEspecificacionesGenerales;
            try {
                hospEspecificacionesGenerales = em.getReference(HospEspecificacionesGenerales.class, id);
                hospEspecificacionesGenerales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospEspecificacionesGenerales with id " + id + " no longer exists.", enfe);
            }
            HospFactsNotas idFactsNotas = hospEspecificacionesGenerales.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getHospEspecificacionesGeneralesList().remove(hospEspecificacionesGenerales);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(hospEspecificacionesGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospEspecificacionesGenerales> findHospEspecificacionesGeneralesEntities() {
        return findHospEspecificacionesGeneralesEntities(true, -1, -1);
    }

    public List<HospEspecificacionesGenerales> findHospEspecificacionesGeneralesEntities(int maxResults, int firstResult) {
        return findHospEspecificacionesGeneralesEntities(false, maxResults, firstResult);
    }

    private List<HospEspecificacionesGenerales> findHospEspecificacionesGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospEspecificacionesGenerales.class));
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

    public HospEspecificacionesGenerales findHospEspecificacionesGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospEspecificacionesGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospEspecificacionesGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospEspecificacionesGenerales> rt = cq.from(HospEspecificacionesGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public HospEspecificacionesGenerales getEspecificaciones(HospFactsNotas n){
        HospEspecificacionesGenerales espgen;    
        EntityManager em= getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT es FROM HospEspecificacionesGenerales es WHERE es.idFactsNotas=:no AND es.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
          if(!results.isEmpty()){
               espgen = (HospEspecificacionesGenerales) results.get(0);
          }else{
               espgen = null;
          }
          return espgen;
      } 
}
