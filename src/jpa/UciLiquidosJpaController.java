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
import entidades_EJB.UciFactsLiquidos;
import entidades_EJB.UciLiquidos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class UciLiquidosJpaController implements Serializable {

    public UciLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciLiquidos uciLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciFactsLiquidos idControlO = uciLiquidos.getIdControlO();
            if (idControlO != null) {
                idControlO = em.getReference(idControlO.getClass(), idControlO.getId());
                uciLiquidos.setIdControlO(idControlO);
            }
            em.persist(uciLiquidos);
            if (idControlO != null) {
                idControlO.getUciLiquidosList().add(uciLiquidos);
                idControlO = em.merge(idControlO);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciLiquidos uciLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciLiquidos persistentUciLiquidos = em.find(UciLiquidos.class, uciLiquidos.getId());
            UciFactsLiquidos idControlOOld = persistentUciLiquidos.getIdControlO();
            UciFactsLiquidos idControlONew = uciLiquidos.getIdControlO();
            if (idControlONew != null) {
                idControlONew = em.getReference(idControlONew.getClass(), idControlONew.getId());
                uciLiquidos.setIdControlO(idControlONew);
            }
            uciLiquidos = em.merge(uciLiquidos);
            if (idControlOOld != null && !idControlOOld.equals(idControlONew)) {
                idControlOOld.getUciLiquidosList().remove(uciLiquidos);
                idControlOOld = em.merge(idControlOOld);
            }
            if (idControlONew != null && !idControlONew.equals(idControlOOld)) {
                idControlONew.getUciLiquidosList().add(uciLiquidos);
                idControlONew = em.merge(idControlONew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciLiquidos.getId();
                if (findUciLiquidos(id) == null) {
                    throw new NonexistentEntityException("The uciLiquidos with id " + id + " no longer exists.");
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
            UciLiquidos uciLiquidos;
            try {
                uciLiquidos = em.getReference(UciLiquidos.class, id);
                uciLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciLiquidos with id " + id + " no longer exists.", enfe);
            }
            UciFactsLiquidos idControlO = uciLiquidos.getIdControlO();
            if (idControlO != null) {
                idControlO.getUciLiquidosList().remove(uciLiquidos);
                idControlO = em.merge(idControlO);
            }
            em.remove(uciLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciLiquidos> findUciLiquidosEntities() {
        return findUciLiquidosEntities(true, -1, -1);
    }

    public List<UciLiquidos> findUciLiquidosEntities(int maxResults, int firstResult) {
        return findUciLiquidosEntities(false, maxResults, firstResult);
    }

    private List<UciLiquidos> findUciLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciLiquidos.class));
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

    public UciLiquidos findUciLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciLiquidos> rt = cq.from(UciLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

     public List<UciLiquidos>getLiquidos(int control){
        EntityManager em = getEntityManager();
        Query Q=null;
        Q=em.createQuery("SELECT l FROM UciLiquidos l WHERE l.idControlO.id=:idc AND l.estado='1'");
        Q.setParameter("idc", control);
        return Q.getResultList();
    }
}
