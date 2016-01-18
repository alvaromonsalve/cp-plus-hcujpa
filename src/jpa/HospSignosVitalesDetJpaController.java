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
import entidades_EJB.HospHistoriac;
import entidades_EJB.HospSignosVitalesDet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JUDMEZ
 */
public class HospSignosVitalesDetJpaController implements Serializable {

    public HospSignosVitalesDetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospSignosVitalesDet hospSignosVitalesDet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospFactsNotas idFactsNotas = hospSignosVitalesDet.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                hospSignosVitalesDet.setIdFactsNotas(idFactsNotas);
            }
            em.persist(hospSignosVitalesDet);
            if (idFactsNotas != null) {
                idFactsNotas.getHospSignosVitalesDetList().add(hospSignosVitalesDet);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospSignosVitalesDet hospSignosVitalesDet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSignosVitalesDet persistentHospSignosVitalesDet = em.find(HospSignosVitalesDet.class, hospSignosVitalesDet.getId());
            HospFactsNotas idFactsNotasOld = persistentHospSignosVitalesDet.getIdFactsNotas();
            HospFactsNotas idFactsNotasNew = hospSignosVitalesDet.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                hospSignosVitalesDet.setIdFactsNotas(idFactsNotasNew);
            }
            hospSignosVitalesDet = em.merge(hospSignosVitalesDet);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getHospSignosVitalesDetList().remove(hospSignosVitalesDet);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getHospSignosVitalesDetList().add(hospSignosVitalesDet);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospSignosVitalesDet.getId();
                if (findHospSignosVitalesDet(id) == null) {
                    throw new NonexistentEntityException("The hospSignosVitalesDet with id " + id + " no longer exists.");
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
            HospSignosVitalesDet hospSignosVitalesDet;
            try {
                hospSignosVitalesDet = em.getReference(HospSignosVitalesDet.class, id);
                hospSignosVitalesDet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospSignosVitalesDet with id " + id + " no longer exists.", enfe);
            }
            HospFactsNotas idFactsNotas = hospSignosVitalesDet.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getHospSignosVitalesDetList().remove(hospSignosVitalesDet);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(hospSignosVitalesDet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospSignosVitalesDet> findHospSignosVitalesDetEntities() {
        return findHospSignosVitalesDetEntities(true, -1, -1);
    }

    public List<HospSignosVitalesDet> findHospSignosVitalesDetEntities(int maxResults, int firstResult) {
        return findHospSignosVitalesDetEntities(false, maxResults, firstResult);
    }

    private List<HospSignosVitalesDet> findHospSignosVitalesDetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospSignosVitalesDet.class));
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

    public HospSignosVitalesDet findHospSignosVitalesDet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospSignosVitalesDet.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospSignosVitalesDetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospSignosVitalesDet> rt = cq.from(HospSignosVitalesDet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public HospSignosVitalesDet get_Signos(HospFactsNotas n){
        HospSignosVitalesDet signos;    
        EntityManager em= getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT s FROM HospSignosVitalesDet s WHERE s.idFactsNotas=:no AND s.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
          if(!results.isEmpty()){
               signos = (HospSignosVitalesDet) results.get(0);
          }else{
               signos = null;
          }
          return signos;
      } 
    public List<HospSignosVitalesDet> get_SignosHist(HospHistoriac n) {
        HospSignosVitalesDet signos;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT s FROM HospSignosVitalesDet s WHERE s.idFactsNotas.idHistoriac=:h AND s.estado='1' GROUP BY s.idFactsNotas.fecha");
        Q.setParameter("h", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
