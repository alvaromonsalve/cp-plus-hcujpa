/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospFactsNotas;
import entidades_EJB.HospHistoriac;
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
public class HospFactsNotasJpaController implements Serializable {

    public HospFactsNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospFactsNotas hospFactsNotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hospFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospFactsNotas hospFactsNotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hospFactsNotas = em.merge(hospFactsNotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospFactsNotas.getId();
                if (findHospFactsNotas(id) == null) {
                    throw new NonexistentEntityException("The hospFactsNotas with id " + id + " no longer exists.");
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
            HospFactsNotas hospFactsNotas;
            try {
                hospFactsNotas = em.getReference(HospFactsNotas.class, id);
                hospFactsNotas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospFactsNotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(hospFactsNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospFactsNotas> findHospFactsNotasEntities() {
        return findHospFactsNotasEntities(true, -1, -1);
    }

    public List<HospFactsNotas> findHospFactsNotasEntities(int maxResults, int firstResult) {
        return findHospFactsNotasEntities(false, maxResults, firstResult);
    }

    private List<HospFactsNotas> findHospFactsNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospFactsNotas.class));
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

    public HospFactsNotas findHospFactsNotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospFactsNotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospFactsNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospFactsNotas> rt = cq.from(HospFactsNotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HospFactsNotas> getNotasHospitalizacion(int historia) {
        EntityManager em = getEntityManager();
        Query q = null;
        try {
            q = em.createQuery("SELECT n FROM HospFactsNotas n WHERE n.idHistoriac.id=:h AND n.estado='1'");
            q.setParameter("h", historia);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<HospFactsNotas> find_Notas(HospHistoriac hi) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT i FROM HospFactsNotas i WHERE i.idHistoriac=:h AND i.estado='1'");
        Q.setParameter("h", hi);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

    public List<HospFactsNotas> find_Notas2(HospHistoriac hi) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT i FROM HospFactsNotas i WHERE i.idHistoriac=:h");
        Q.setParameter("h", hi);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }

}
