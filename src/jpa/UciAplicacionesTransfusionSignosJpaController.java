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
import entidades_EJB.UciAplicacionesTransfusionSignos;
import entidades_EJB.UciAplicacionesTransfusion_2;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UciAplicacionesTransfusionSignosJpaController implements Serializable {

    public UciAplicacionesTransfusionSignosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAplicacionesTransfusionSignos uciAplicacionesTransfusionSignos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAplicacionesTransfusion_2 idAplicacionTransfusion = uciAplicacionesTransfusionSignos.getIdAplicacionTransfusion();
            if (idAplicacionTransfusion != null) {
                idAplicacionTransfusion = em.getReference(idAplicacionTransfusion.getClass(), idAplicacionTransfusion.getId());
                uciAplicacionesTransfusionSignos.setIdAplicacionTransfusion(idAplicacionTransfusion);
            }
            em.persist(uciAplicacionesTransfusionSignos);
            if (idAplicacionTransfusion != null) {
                idAplicacionTransfusion.getUciAplicacionesTransfusionSignosList().add(uciAplicacionesTransfusionSignos);
                idAplicacionTransfusion = em.merge(idAplicacionTransfusion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAplicacionesTransfusionSignos uciAplicacionesTransfusionSignos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciAplicacionesTransfusionSignos persistentUciAplicacionesTransfusionSignos = em.find(UciAplicacionesTransfusionSignos.class, uciAplicacionesTransfusionSignos.getId());
            UciAplicacionesTransfusion_2 idAplicacionTransfusionOld = persistentUciAplicacionesTransfusionSignos.getIdAplicacionTransfusion();
            UciAplicacionesTransfusion_2 idAplicacionTransfusionNew = uciAplicacionesTransfusionSignos.getIdAplicacionTransfusion();
            if (idAplicacionTransfusionNew != null) {
                idAplicacionTransfusionNew = em.getReference(idAplicacionTransfusionNew.getClass(), idAplicacionTransfusionNew.getId());
                uciAplicacionesTransfusionSignos.setIdAplicacionTransfusion(idAplicacionTransfusionNew);
            }
            uciAplicacionesTransfusionSignos = em.merge(uciAplicacionesTransfusionSignos);
            if (idAplicacionTransfusionOld != null && !idAplicacionTransfusionOld.equals(idAplicacionTransfusionNew)) {
                idAplicacionTransfusionOld.getUciAplicacionesTransfusionSignosList().remove(uciAplicacionesTransfusionSignos);
                idAplicacionTransfusionOld = em.merge(idAplicacionTransfusionOld);
            }
            if (idAplicacionTransfusionNew != null && !idAplicacionTransfusionNew.equals(idAplicacionTransfusionOld)) {
                idAplicacionTransfusionNew.getUciAplicacionesTransfusionSignosList().add(uciAplicacionesTransfusionSignos);
                idAplicacionTransfusionNew = em.merge(idAplicacionTransfusionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAplicacionesTransfusionSignos.getId();
                if (findUciAplicacionesTransfusionSignos(id) == null) {
                    throw new NonexistentEntityException("The uciAplicacionesTransfusionSignos with id " + id + " no longer exists.");
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
            UciAplicacionesTransfusionSignos uciAplicacionesTransfusionSignos;
            try {
                uciAplicacionesTransfusionSignos = em.getReference(UciAplicacionesTransfusionSignos.class, id);
                uciAplicacionesTransfusionSignos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAplicacionesTransfusionSignos with id " + id + " no longer exists.", enfe);
            }
            UciAplicacionesTransfusion_2 idAplicacionTransfusion = uciAplicacionesTransfusionSignos.getIdAplicacionTransfusion();
            if (idAplicacionTransfusion != null) {
                idAplicacionTransfusion.getUciAplicacionesTransfusionSignosList().remove(uciAplicacionesTransfusionSignos);
                idAplicacionTransfusion = em.merge(idAplicacionTransfusion);
            }
            em.remove(uciAplicacionesTransfusionSignos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAplicacionesTransfusionSignos> findUciAplicacionesTransfusionSignosEntities() {
        return findUciAplicacionesTransfusionSignosEntities(true, -1, -1);
    }

    public List<UciAplicacionesTransfusionSignos> findUciAplicacionesTransfusionSignosEntities(int maxResults, int firstResult) {
        return findUciAplicacionesTransfusionSignosEntities(false, maxResults, firstResult);
    }

    private List<UciAplicacionesTransfusionSignos> findUciAplicacionesTransfusionSignosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAplicacionesTransfusionSignos.class));
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

    public UciAplicacionesTransfusionSignos findUciAplicacionesTransfusionSignos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAplicacionesTransfusionSignos.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAplicacionesTransfusionSignosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAplicacionesTransfusionSignos> rt = cq.from(UciAplicacionesTransfusionSignos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
