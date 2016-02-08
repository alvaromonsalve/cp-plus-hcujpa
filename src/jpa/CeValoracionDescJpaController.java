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
import entidades_EJB.CeValoracion;
import entidades_EJB.CeValoracionDesc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CeValoracionDescJpaController implements Serializable {

    public CeValoracionDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeValoracionDesc ceValoracionDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeValoracion idCeValoracion = ceValoracionDesc.getIdCeValoracion();
            if (idCeValoracion != null) {
                idCeValoracion = em.getReference(idCeValoracion.getClass(), idCeValoracion.getId());
                ceValoracionDesc.setIdCeValoracion(idCeValoracion);
            }
            em.persist(ceValoracionDesc);
            if (idCeValoracion != null) {
                idCeValoracion.getCeValoracionDescList().add(ceValoracionDesc);
                idCeValoracion = em.merge(idCeValoracion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeValoracionDesc ceValoracionDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeValoracionDesc persistentCeValoracionDesc = em.find(CeValoracionDesc.class, ceValoracionDesc.getId());
            CeValoracion idCeValoracionOld = persistentCeValoracionDesc.getIdCeValoracion();
            CeValoracion idCeValoracionNew = ceValoracionDesc.getIdCeValoracion();
            if (idCeValoracionNew != null) {
                idCeValoracionNew = em.getReference(idCeValoracionNew.getClass(), idCeValoracionNew.getId());
                ceValoracionDesc.setIdCeValoracion(idCeValoracionNew);
            }
            ceValoracionDesc = em.merge(ceValoracionDesc);
            if (idCeValoracionOld != null && !idCeValoracionOld.equals(idCeValoracionNew)) {
                idCeValoracionOld.getCeValoracionDescList().remove(ceValoracionDesc);
                idCeValoracionOld = em.merge(idCeValoracionOld);
            }
            if (idCeValoracionNew != null && !idCeValoracionNew.equals(idCeValoracionOld)) {
                idCeValoracionNew.getCeValoracionDescList().add(ceValoracionDesc);
                idCeValoracionNew = em.merge(idCeValoracionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceValoracionDesc.getId();
                if (findCeValoracionDesc(id) == null) {
                    throw new NonexistentEntityException("The ceValoracionDesc with id " + id + " no longer exists.");
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
            CeValoracionDesc ceValoracionDesc;
            try {
                ceValoracionDesc = em.getReference(CeValoracionDesc.class, id);
                ceValoracionDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceValoracionDesc with id " + id + " no longer exists.", enfe);
            }
            CeValoracion idCeValoracion = ceValoracionDesc.getIdCeValoracion();
            if (idCeValoracion != null) {
                idCeValoracion.getCeValoracionDescList().remove(ceValoracionDesc);
                idCeValoracion = em.merge(idCeValoracion);
            }
            em.remove(ceValoracionDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeValoracionDesc> findCeValoracionDescEntities() {
        return findCeValoracionDescEntities(true, -1, -1);
    }

    public List<CeValoracionDesc> findCeValoracionDescEntities(int maxResults, int firstResult) {
        return findCeValoracionDescEntities(false, maxResults, firstResult);
    }

    private List<CeValoracionDesc> findCeValoracionDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeValoracionDesc.class));
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

    public CeValoracionDesc findCeValoracionDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeValoracionDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeValoracionDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeValoracionDesc> rt = cq.from(CeValoracionDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeValoracionDesc> getValoraciones(CeValoracion va) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT v FROM CeValoracionDesc v WHERE v.idCeValoracion=:val AND v.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("val", va);
        return q.getResultList();
    }
}
