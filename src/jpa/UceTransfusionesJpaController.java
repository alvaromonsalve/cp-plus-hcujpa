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
import entidades_EJB.UceSangre;
import entidades_EJB.UceTransfusiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceTransfusionesJpaController implements Serializable {

    public UceTransfusionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceTransfusiones uceTransfusiones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceSangre idsangre = uceTransfusiones.getIdsangre();
            if (idsangre != null) {
                idsangre = em.getReference(idsangre.getClass(), idsangre.getId());
                uceTransfusiones.setIdsangre(idsangre);
            }
            em.persist(uceTransfusiones);
            if (idsangre != null) {
                idsangre.getUceTransfusionesList().add(uceTransfusiones);
                idsangre = em.merge(idsangre);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceTransfusiones uceTransfusiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceTransfusiones persistentUceTransfusiones = em.find(UceTransfusiones.class, uceTransfusiones.getId());
            UceSangre idsangreOld = persistentUceTransfusiones.getIdsangre();
            UceSangre idsangreNew = uceTransfusiones.getIdsangre();
            if (idsangreNew != null) {
                idsangreNew = em.getReference(idsangreNew.getClass(), idsangreNew.getId());
                uceTransfusiones.setIdsangre(idsangreNew);
            }
            uceTransfusiones = em.merge(uceTransfusiones);
            if (idsangreOld != null && !idsangreOld.equals(idsangreNew)) {
                idsangreOld.getUceTransfusionesList().remove(uceTransfusiones);
                idsangreOld = em.merge(idsangreOld);
            }
            if (idsangreNew != null && !idsangreNew.equals(idsangreOld)) {
                idsangreNew.getUceTransfusionesList().add(uceTransfusiones);
                idsangreNew = em.merge(idsangreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceTransfusiones.getId();
                if (findUceTransfusiones(id) == null) {
                    throw new NonexistentEntityException("The uceTransfusiones with id " + id + " no longer exists.");
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
            UceTransfusiones uceTransfusiones;
            try {
                uceTransfusiones = em.getReference(UceTransfusiones.class, id);
                uceTransfusiones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceTransfusiones with id " + id + " no longer exists.", enfe);
            }
            UceSangre idsangre = uceTransfusiones.getIdsangre();
            if (idsangre != null) {
                idsangre.getUceTransfusionesList().remove(uceTransfusiones);
                idsangre = em.merge(idsangre);
            }
            em.remove(uceTransfusiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceTransfusiones> findUceTransfusionesEntities() {
        return findUceTransfusionesEntities(true, -1, -1);
    }

    public List<UceTransfusiones> findUceTransfusionesEntities(int maxResults, int firstResult) {
        return findUceTransfusionesEntities(false, maxResults, firstResult);
    }

    private List<UceTransfusiones> findUceTransfusionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceTransfusiones.class));
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

    public UceTransfusiones findUceTransfusiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceTransfusiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceTransfusionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceTransfusiones> rt = cq.from(UceTransfusiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
