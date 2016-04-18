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
import entidades_EJB.HospSangre;
import entidades_EJB.HospTransfusiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospTransfusionesJpaController implements Serializable {

    public HospTransfusionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospTransfusiones hospTransfusiones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospSangre idsangre = hospTransfusiones.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                hospTransfusiones.setIdsangre(idsangre);
            }
            em.persist(hospTransfusiones);
            if (idsangre != null) {
                idsangre.getHospTransfusionesList().add(hospTransfusiones);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospTransfusiones hospTransfusiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospTransfusiones persistentHospTransfusiones = em.find(HospTransfusiones.class, hospTransfusiones.getId());
            HospSangre idsangreOld = persistentHospTransfusiones.getIdsangre();
            HospSangre idsangreNew = hospTransfusiones.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                hospTransfusiones.setIdsangre(idsangreNew);
            }
            hospTransfusiones = em.merge(hospTransfusiones);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getHospTransfusionesList().remove(hospTransfusiones);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getHospTransfusionesList().add(hospTransfusiones);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospTransfusiones.getId();
                if (findHospTransfusiones(id) == null) {
                    throw new NonexistentEntityException("The hospTransfusiones with id " + id + " no longer exists.");
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
            HospTransfusiones hospTransfusiones;
            try {
                hospTransfusiones = em.getReference(HospTransfusiones.class, id);
                hospTransfusiones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospTransfusiones with id " + id + " no longer exists.", enfe);
            }
            HospSangre idsangre = hospTransfusiones.getIdsangre();
            if (idsangre != null) {
                idsangre.getHospTransfusionesList().remove(hospTransfusiones);
                idsangre = em.merge(idsangre);
            }
            em.remove(hospTransfusiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospTransfusiones> findHospTransfusionesEntities() {
        return findHospTransfusionesEntities(true, -1, -1);
    }

    public List<HospTransfusiones> findHospTransfusionesEntities(int maxResults, int firstResult) {
        return findHospTransfusionesEntities(false, maxResults, firstResult);
    }

    private List<HospTransfusiones> findHospTransfusionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospTransfusiones.class));
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

    public HospTransfusiones findHospTransfusiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospTransfusiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospTransfusionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospTransfusiones> rt = cq.from(HospTransfusiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
