/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospFactsNotas;
import entidades_EJB.HospGlasgow;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JUDMEZ
 */
public class HospGlasgowJpaController implements Serializable {

    public HospGlasgowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospGlasgow hospGlasgow) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospFactsNotas idFactsNotas = hospGlasgow.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                hospGlasgow.setIdFactsNotas(idFactsNotas);
            }
            em.persist(hospGlasgow);
            if (idFactsNotas != null) {
                idFactsNotas.getHospGlasgowList().add(hospGlasgow);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospGlasgow hospGlasgow) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospGlasgow persistentHospGlasgow = em.find(HospGlasgow.class, hospGlasgow.getId());
            HospFactsNotas idFactsNotasOld = persistentHospGlasgow.getIdFactsNotas();
            HospFactsNotas idFactsNotasNew = hospGlasgow.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                hospGlasgow.setIdFactsNotas(idFactsNotasNew);
            }
            hospGlasgow = em.merge(hospGlasgow);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getHospGlasgowList().remove(hospGlasgow);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getHospGlasgowList().add(hospGlasgow);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospGlasgow.getId();
                if (findHospGlasgow(id) == null) {
                    throw new NonexistentEntityException("The hospGlasgow with id " + id + " no longer exists.");
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
            HospGlasgow hospGlasgow;
            try {
                hospGlasgow = em.getReference(HospGlasgow.class, id);
                hospGlasgow.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospGlasgow with id " + id + " no longer exists.", enfe);
            }
            HospFactsNotas idFactsNotas = hospGlasgow.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getHospGlasgowList().remove(hospGlasgow);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(hospGlasgow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospGlasgow> findHospGlasgowEntities() {
        return findHospGlasgowEntities(true, -1, -1);
    }

    public List<HospGlasgow> findHospGlasgowEntities(int maxResults, int firstResult) {
        return findHospGlasgowEntities(false, maxResults, firstResult);
    }

    private List<HospGlasgow> findHospGlasgowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospGlasgow.class));
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

    public HospGlasgow findHospGlasgow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospGlasgow.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospGlasgowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospGlasgow> rt = cq.from(HospGlasgow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public HospGlasgow get_glassgow(HospFactsNotas n){
        HospGlasgow glass;    
        EntityManager em= getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT g FROM HospGlasgow g WHERE g.idFactsNotas=:no AND g.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
          if(!results.isEmpty()){
               glass = (HospGlasgow) results.get(0);
          }else{
               glass = null;
          }
          em.close();
          return glass;   
      } 
}
