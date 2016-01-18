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
import entidades_EJB.HospMedSuministrados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JUDMEZ
 */
public class HospMedSuministradosJpaController implements Serializable {

    public HospMedSuministradosJpaController(EntityManagerFactory emf) {
       this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospMedSuministrados hospMedSuministrados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospFactsNotas idFactsNotas = hospMedSuministrados.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                hospMedSuministrados.setIdFactsNotas(idFactsNotas);
            }
            em.persist(hospMedSuministrados);
            if (idFactsNotas != null) {
                idFactsNotas.getHospMedSuministradosList().add(hospMedSuministrados);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospMedSuministrados hospMedSuministrados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospMedSuministrados persistentHospMedSuministrados = em.find(HospMedSuministrados.class, hospMedSuministrados.getId());
            HospFactsNotas idFactsNotasOld = persistentHospMedSuministrados.getIdFactsNotas();
            HospFactsNotas idFactsNotasNew = hospMedSuministrados.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                hospMedSuministrados.setIdFactsNotas(idFactsNotasNew);
            }
            hospMedSuministrados = em.merge(hospMedSuministrados);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getHospMedSuministradosList().remove(hospMedSuministrados);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getHospMedSuministradosList().add(hospMedSuministrados);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospMedSuministrados.getId();
                if (findHospMedSuministrados(id) == null) {
                    throw new NonexistentEntityException("The hospMedSuministrados with id " + id + " no longer exists.");
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
            HospMedSuministrados hospMedSuministrados;
            try {
                hospMedSuministrados = em.getReference(HospMedSuministrados.class, id);
                hospMedSuministrados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospMedSuministrados with id " + id + " no longer exists.", enfe);
            }
            HospFactsNotas idFactsNotas = hospMedSuministrados.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getHospMedSuministradosList().remove(hospMedSuministrados);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(hospMedSuministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospMedSuministrados> findHospMedSuministradosEntities() {
        return findHospMedSuministradosEntities(true, -1, -1);
    }

    public List<HospMedSuministrados> findHospMedSuministradosEntities(int maxResults, int firstResult) {
        return findHospMedSuministradosEntities(false, maxResults, firstResult);
    }

    private List<HospMedSuministrados> findHospMedSuministradosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospMedSuministrados.class));
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

    public HospMedSuministrados findHospMedSuministrados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospMedSuministrados.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospMedSuministradosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospMedSuministrados> rt = cq.from(HospMedSuministrados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public HospMedSuministrados get_Medicamentos(HospFactsNotas n){
        HospMedSuministrados med;    
        EntityManager em= getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT m FROM HospMedSuministrados m WHERE m.idFactsNotas=:no AND m.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
          if(!results.isEmpty()){
               med = (HospMedSuministrados) results.get(0);
          }else{
               med = null;
          }
          return med;   
      } 
}
