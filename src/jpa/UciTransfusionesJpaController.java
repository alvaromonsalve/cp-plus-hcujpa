/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciSangre;
import entidades_EJB.UciTransfusiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciTransfusionesJpaController implements Serializable {

    public UciTransfusionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciTransfusiones uciTransfusiones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciSangre idsangre = uciTransfusiones.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                uciTransfusiones.setIdsangre(idsangre);
            }
            em.persist(uciTransfusiones);
            if (idsangre != null) {
                idsangre.getUciTransfusionesList().add(uciTransfusiones);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciTransfusiones uciTransfusiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciTransfusiones persistentUciTransfusiones = em.find(UciTransfusiones.class, uciTransfusiones.getId());
            UciSangre idsangreOld = persistentUciTransfusiones.getIdsangre();
            UciSangre idsangreNew = uciTransfusiones.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                uciTransfusiones.setIdsangre(idsangreNew);
            }
            uciTransfusiones = em.merge(uciTransfusiones);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUciTransfusionesList().remove(uciTransfusiones);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUciTransfusionesList().add(uciTransfusiones);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciTransfusiones.getId();
                if (findUciTransfusiones(id) == null) {
                    throw new NonexistentEntityException("The uciTransfusiones with id " + id + " no longer exists.");
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
            UciTransfusiones uciTransfusiones;
            try {
                uciTransfusiones = em.getReference(UciTransfusiones.class, id);
                uciTransfusiones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciTransfusiones with id " + id + " no longer exists.", enfe);
            }
            UciSangre idsangre = uciTransfusiones.getIdsangre();
            if (idsangre != null) {
                idsangre.getUciTransfusionesList().remove(uciTransfusiones);
                idsangre = em.merge(idsangre);
            }
            em.remove(uciTransfusiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciTransfusiones> findUciTransfusionesEntities() {
        return findUciTransfusionesEntities(true, -1, -1);
    }

    public List<UciTransfusiones> findUciTransfusionesEntities(int maxResults, int firstResult) {
        return findUciTransfusionesEntities(false, maxResults, firstResult);
    }

    private List<UciTransfusiones> findUciTransfusionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciTransfusiones.class));
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

    public UciTransfusiones findUciTransfusiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciTransfusiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciTransfusionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciTransfusiones> rt = cq.from(UciTransfusiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
