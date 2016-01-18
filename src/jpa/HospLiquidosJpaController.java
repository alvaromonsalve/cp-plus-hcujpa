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
import entidades_EJB.HospFactsLiquidos;
import entidades_EJB.HospLiquidos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JUDMEZ
 */
public class HospLiquidosJpaController implements Serializable {

    public HospLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospLiquidos hospLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospFactsLiquidos idControlO = hospLiquidos.getIdControlO();
            if (idControlO != null) {
                idControlO = em.getReference(idControlO.getClass(), idControlO.getId());
                hospLiquidos.setIdControlO(idControlO);
            }
            em.persist(hospLiquidos);
            if (idControlO != null) {
                idControlO.getHospLiquidosList().add(hospLiquidos);
                idControlO = em.merge(idControlO);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospLiquidos hospLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospLiquidos persistentHospLiquidos = em.find(HospLiquidos.class, hospLiquidos.getId());
            HospFactsLiquidos idControlOOld = persistentHospLiquidos.getIdControlO();
            HospFactsLiquidos idControlONew = hospLiquidos.getIdControlO();
            if (idControlONew != null) {
                idControlONew = em.getReference(idControlONew.getClass(), idControlONew.getId());
                hospLiquidos.setIdControlO(idControlONew);
            }
            hospLiquidos = em.merge(hospLiquidos);
            if (idControlOOld != null && !idControlOOld.equals(idControlONew)) {
                idControlOOld.getHospLiquidosList().remove(hospLiquidos);
                idControlOOld = em.merge(idControlOOld);
            }
            if (idControlONew != null && !idControlONew.equals(idControlOOld)) {
                idControlONew.getHospLiquidosList().add(hospLiquidos);
                idControlONew = em.merge(idControlONew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospLiquidos.getId();
                if (findHospLiquidos(id) == null) {
                    throw new NonexistentEntityException("The hospLiquidos with id " + id + " no longer exists.");
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
            HospLiquidos hospLiquidos;
            try {
                hospLiquidos = em.getReference(HospLiquidos.class, id);
                hospLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospLiquidos with id " + id + " no longer exists.", enfe);
            }
            HospFactsLiquidos idControlO = hospLiquidos.getIdControlO();
            if (idControlO != null) {
                idControlO.getHospLiquidosList().remove(hospLiquidos);
                idControlO = em.merge(idControlO);
            }
            em.remove(hospLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospLiquidos> findHospLiquidosEntities() {
        return findHospLiquidosEntities(true, -1, -1);
    }

    public List<HospLiquidos> findHospLiquidosEntities(int maxResults, int firstResult) {
        return findHospLiquidosEntities(false, maxResults, firstResult);
    }

    private List<HospLiquidos> findHospLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospLiquidos.class));
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

    public HospLiquidos findHospLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospLiquidos> rt = cq.from(HospLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<HospLiquidos>getLiquidos(int control){
        EntityManager em = getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT l FROM HospLiquidos l WHERE l.idControlO.id=:idc AND l.estado='1'");
        Q.setParameter("idc", control);
        return Q.getResultList();
    }
}
