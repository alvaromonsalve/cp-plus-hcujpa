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
import entidades_EJB.HcuOrdenProcedimiento;
import entidades_EJB.HcuOrdenProcedimientoDesc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HcuOrdenProcedimientoDescJpaController implements Serializable {

    public HcuOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuOrdenProcedimiento idOrdenProcedimiento = hcuOrdenProcedimientoDesc.getIdOrdenProcedimiento();
            if (idOrdenProcedimiento != null) {
                idOrdenProcedimiento = em.getReference(idOrdenProcedimiento.getClass(), idOrdenProcedimiento.getId());
                hcuOrdenProcedimientoDesc.setIdOrdenProcedimiento(idOrdenProcedimiento);
            }
            em.persist(hcuOrdenProcedimientoDesc);
            if (idOrdenProcedimiento != null) {
                idOrdenProcedimiento.getHcuOrdenProcedimientoDescList().add(hcuOrdenProcedimientoDesc);
                idOrdenProcedimiento = em.merge(idOrdenProcedimiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuOrdenProcedimientoDesc persistentHcuOrdenProcedimientoDesc = em.find(HcuOrdenProcedimientoDesc.class, hcuOrdenProcedimientoDesc.getId());
            HcuOrdenProcedimiento idOrdenProcedimientoOld = persistentHcuOrdenProcedimientoDesc.getIdOrdenProcedimiento();
            HcuOrdenProcedimiento idOrdenProcedimientoNew = hcuOrdenProcedimientoDesc.getIdOrdenProcedimiento();
            if (idOrdenProcedimientoNew != null) {
                idOrdenProcedimientoNew = em.getReference(idOrdenProcedimientoNew.getClass(), idOrdenProcedimientoNew.getId());
                hcuOrdenProcedimientoDesc.setIdOrdenProcedimiento(idOrdenProcedimientoNew);
            }
            hcuOrdenProcedimientoDesc = em.merge(hcuOrdenProcedimientoDesc);
            if (idOrdenProcedimientoOld != null && !idOrdenProcedimientoOld.equals(idOrdenProcedimientoNew)) {
                idOrdenProcedimientoOld.getHcuOrdenProcedimientoDescList().remove(hcuOrdenProcedimientoDesc);
                idOrdenProcedimientoOld = em.merge(idOrdenProcedimientoOld);
            }
            if (idOrdenProcedimientoNew != null && !idOrdenProcedimientoNew.equals(idOrdenProcedimientoOld)) {
                idOrdenProcedimientoNew.getHcuOrdenProcedimientoDescList().add(hcuOrdenProcedimientoDesc);
                idOrdenProcedimientoNew = em.merge(idOrdenProcedimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuOrdenProcedimientoDesc.getId();
                if (findHcuOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The hcuOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDesc;
            try {
                hcuOrdenProcedimientoDesc = em.getReference(HcuOrdenProcedimientoDesc.class, id);
                hcuOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            HcuOrdenProcedimiento idOrdenProcedimiento = hcuOrdenProcedimientoDesc.getIdOrdenProcedimiento();
            if (idOrdenProcedimiento != null) {
                idOrdenProcedimiento.getHcuOrdenProcedimientoDescList().remove(hcuOrdenProcedimientoDesc);
                idOrdenProcedimiento = em.merge(idOrdenProcedimiento);
            }
            em.remove(hcuOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuOrdenProcedimientoDesc> findHcuOrdenProcedimientoDescEntities() {
        return findHcuOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<HcuOrdenProcedimientoDesc> findHcuOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findHcuOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<HcuOrdenProcedimientoDesc> findHcuOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuOrdenProcedimientoDesc.class));
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

    public HcuOrdenProcedimientoDesc findHcuOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuOrdenProcedimientoDesc> rt = cq.from(HcuOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
