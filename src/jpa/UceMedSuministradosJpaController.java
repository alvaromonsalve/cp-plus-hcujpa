/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceFactsNotas;
import entidades_EJB.UceMedSuministrados;
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
public class UceMedSuministradosJpaController implements Serializable {

    public UceMedSuministradosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceMedSuministrados uceMedSuministrados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceMedSuministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceMedSuministrados uceMedSuministrados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceMedSuministrados = em.merge(uceMedSuministrados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceMedSuministrados.getId();
                if (findUceMedSuministrados(id) == null) {
                    throw new NonexistentEntityException("The uceMedSuministrados with id " + id + " no longer exists.");
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
            UceMedSuministrados uceMedSuministrados;
            try {
                uceMedSuministrados = em.getReference(UceMedSuministrados.class, id);
                uceMedSuministrados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceMedSuministrados with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceMedSuministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceMedSuministrados> findUceMedSuministradosEntities() {
        return findUceMedSuministradosEntities(true, -1, -1);
    }

    public List<UceMedSuministrados> findUceMedSuministradosEntities(int maxResults, int firstResult) {
        return findUceMedSuministradosEntities(false, maxResults, firstResult);
    }

    private List<UceMedSuministrados> findUceMedSuministradosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceMedSuministrados.class));
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

    public UceMedSuministrados findUceMedSuministrados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceMedSuministrados.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceMedSuministradosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceMedSuministrados> rt = cq.from(UceMedSuministrados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public UceMedSuministrados get_Medicamentos(UceFactsNotas n) {
        UceMedSuministrados med;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT m FROM UceMedSuministrados m WHERE m.idFactsNotas=:no AND m.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            med = (UceMedSuministrados) results.get(0);
        } else {
            med = null;
        }
        return med;
    }
}
