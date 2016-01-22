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
import entidades_EJB.UciAplicacionesTransfusion;
import entidades_EJB.UciAplicacionesTransfusionSellos;
import entidades_EJB.UciAplicacionesTransfusion_2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UciAplicacionesTransfusionSellosJpaController implements Serializable {

    public UciAplicacionesTransfusionSellosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAplicacionesTransfusionSellos uciAplicacionesTransfusionSellos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAplicacionesTransfusion_2 idAplicacionTransfusion = uciAplicacionesTransfusionSellos.getIdAplicacionTransfusion();
            if (idAplicacionTransfusion != null) {
                idAplicacionTransfusion = em.getReference(idAplicacionTransfusion.getClass(), idAplicacionTransfusion.getId());
                uciAplicacionesTransfusionSellos.setIdAplicacionTransfusion(idAplicacionTransfusion);
            }
            em.persist(uciAplicacionesTransfusionSellos);
            if (idAplicacionTransfusion != null) {
                idAplicacionTransfusion.getUciAplicacionesTransfusionSellosList().add(uciAplicacionesTransfusionSellos);
                idAplicacionTransfusion = em.merge(idAplicacionTransfusion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAplicacionesTransfusionSellos uciAplicacionesTransfusionSellos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAplicacionesTransfusionSellos persistentUciAplicacionesTransfusionSellos = em.find(UciAplicacionesTransfusionSellos.class, uciAplicacionesTransfusionSellos.getId());
            UciAplicacionesTransfusion_2 idAplicacionTransfusionOld = persistentUciAplicacionesTransfusionSellos.getIdAplicacionTransfusion();
            UciAplicacionesTransfusion_2 idAplicacionTransfusionNew = uciAplicacionesTransfusionSellos.getIdAplicacionTransfusion();
            if (idAplicacionTransfusionNew != null) {
                idAplicacionTransfusionNew = em.getReference(idAplicacionTransfusionNew.getClass(), idAplicacionTransfusionNew.getId());
                uciAplicacionesTransfusionSellos.setIdAplicacionTransfusion(idAplicacionTransfusionNew);
            }
            uciAplicacionesTransfusionSellos = em.merge(uciAplicacionesTransfusionSellos);
            if (idAplicacionTransfusionOld != null && !idAplicacionTransfusionOld.equals(idAplicacionTransfusionNew)) {
                idAplicacionTransfusionOld.getUciAplicacionesTransfusionSellosList().remove(uciAplicacionesTransfusionSellos);
                idAplicacionTransfusionOld = em.merge(idAplicacionTransfusionOld);
            }
            if (idAplicacionTransfusionNew != null && !idAplicacionTransfusionNew.equals(idAplicacionTransfusionOld)) {
                idAplicacionTransfusionNew.getUciAplicacionesTransfusionSellosList().add(uciAplicacionesTransfusionSellos);
                idAplicacionTransfusionNew = em.merge(idAplicacionTransfusionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAplicacionesTransfusionSellos.getId();
                if (findUciAplicacionesTransfusionSellos(id) == null) {
                    throw new NonexistentEntityException("The uciAplicacionesTransfusionSellos with id " + id + " no longer exists.");
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
            UciAplicacionesTransfusionSellos uciAplicacionesTransfusionSellos;
            try {
                uciAplicacionesTransfusionSellos = em.getReference(UciAplicacionesTransfusionSellos.class, id);
                uciAplicacionesTransfusionSellos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAplicacionesTransfusionSellos with id " + id + " no longer exists.", enfe);
            }
            UciAplicacionesTransfusion_2 idAplicacionTransfusion = uciAplicacionesTransfusionSellos.getIdAplicacionTransfusion();
            if (idAplicacionTransfusion != null) {
                idAplicacionTransfusion.getUciAplicacionesTransfusionSellosList().remove(uciAplicacionesTransfusionSellos);
                idAplicacionTransfusion = em.merge(idAplicacionTransfusion);
            }
            em.remove(uciAplicacionesTransfusionSellos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAplicacionesTransfusionSellos> findUciAplicacionesTransfusionSellosEntities() {
        return findUciAplicacionesTransfusionSellosEntities(true, -1, -1);
    }

    public List<UciAplicacionesTransfusionSellos> findUciAplicacionesTransfusionSellosEntities(int maxResults, int firstResult) {
        return findUciAplicacionesTransfusionSellosEntities(false, maxResults, firstResult);
    }

    private List<UciAplicacionesTransfusionSellos> findUciAplicacionesTransfusionSellosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAplicacionesTransfusionSellos.class));
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

    public UciAplicacionesTransfusionSellos findUciAplicacionesTransfusionSellos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAplicacionesTransfusionSellos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAplicacionesTransfusionSellosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAplicacionesTransfusionSellos> rt = cq.from(UciAplicacionesTransfusionSellos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
