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
import entidades_EJB.HcuEvoOrdenProcedimiento;
import entidades_EJB.HcuEvoOrdenProcedimientoDesc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HcuEvoOrdenProcedimientoDescJpaController implements Serializable {

    public HcuEvoOrdenProcedimientoDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoOrdenProcedimiento idEvuOrdenProcedimiento = hcuEvoOrdenProcedimientoDesc.getIdEvuOrdenProcedimiento();
            if (idEvuOrdenProcedimiento != null) {
                idEvuOrdenProcedimiento = em.getReference(idEvuOrdenProcedimiento.getClass(), idEvuOrdenProcedimiento.getId());
                hcuEvoOrdenProcedimientoDesc.setIdEvuOrdenProcedimiento(idEvuOrdenProcedimiento);
            }
            em.persist(hcuEvoOrdenProcedimientoDesc);
            if (idEvuOrdenProcedimiento != null) {
                idEvuOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList().add(hcuEvoOrdenProcedimientoDesc);
                idEvuOrdenProcedimiento = em.merge(idEvuOrdenProcedimiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoOrdenProcedimientoDesc persistentHcuEvoOrdenProcedimientoDesc = em.find(HcuEvoOrdenProcedimientoDesc.class, hcuEvoOrdenProcedimientoDesc.getId());
            HcuEvoOrdenProcedimiento idEvuOrdenProcedimientoOld = persistentHcuEvoOrdenProcedimientoDesc.getIdEvuOrdenProcedimiento();
            HcuEvoOrdenProcedimiento idEvuOrdenProcedimientoNew = hcuEvoOrdenProcedimientoDesc.getIdEvuOrdenProcedimiento();
            if (idEvuOrdenProcedimientoNew != null) {
                idEvuOrdenProcedimientoNew = em.getReference(idEvuOrdenProcedimientoNew.getClass(), idEvuOrdenProcedimientoNew.getId());
                hcuEvoOrdenProcedimientoDesc.setIdEvuOrdenProcedimiento(idEvuOrdenProcedimientoNew);
            }
            hcuEvoOrdenProcedimientoDesc = em.merge(hcuEvoOrdenProcedimientoDesc);
            if (idEvuOrdenProcedimientoOld != null && !idEvuOrdenProcedimientoOld.equals(idEvuOrdenProcedimientoNew)) {
                idEvuOrdenProcedimientoOld.getHcuEvoOrdenProcedimientoDescList().remove(hcuEvoOrdenProcedimientoDesc);
                idEvuOrdenProcedimientoOld = em.merge(idEvuOrdenProcedimientoOld);
            }
            if (idEvuOrdenProcedimientoNew != null && !idEvuOrdenProcedimientoNew.equals(idEvuOrdenProcedimientoOld)) {
                idEvuOrdenProcedimientoNew.getHcuEvoOrdenProcedimientoDescList().add(hcuEvoOrdenProcedimientoDesc);
                idEvuOrdenProcedimientoNew = em.merge(idEvuOrdenProcedimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoOrdenProcedimientoDesc.getId();
                if (findHcuEvoOrdenProcedimientoDesc(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoOrdenProcedimientoDesc with id " + id + " no longer exists.");
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
            HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDesc;
            try {
                hcuEvoOrdenProcedimientoDesc = em.getReference(HcuEvoOrdenProcedimientoDesc.class, id);
                hcuEvoOrdenProcedimientoDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoOrdenProcedimientoDesc with id " + id + " no longer exists.", enfe);
            }
            HcuEvoOrdenProcedimiento idEvuOrdenProcedimiento = hcuEvoOrdenProcedimientoDesc.getIdEvuOrdenProcedimiento();
            if (idEvuOrdenProcedimiento != null) {
                idEvuOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList().remove(hcuEvoOrdenProcedimientoDesc);
                idEvuOrdenProcedimiento = em.merge(idEvuOrdenProcedimiento);
            }
            em.remove(hcuEvoOrdenProcedimientoDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoOrdenProcedimientoDesc> findHcuEvoOrdenProcedimientoDescEntities() {
        return findHcuEvoOrdenProcedimientoDescEntities(true, -1, -1);
    }

    public List<HcuEvoOrdenProcedimientoDesc> findHcuEvoOrdenProcedimientoDescEntities(int maxResults, int firstResult) {
        return findHcuEvoOrdenProcedimientoDescEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoOrdenProcedimientoDesc> findHcuEvoOrdenProcedimientoDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoOrdenProcedimientoDesc.class));
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

    public HcuEvoOrdenProcedimientoDesc findHcuEvoOrdenProcedimientoDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoOrdenProcedimientoDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoOrdenProcedimientoDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoOrdenProcedimientoDesc> rt = cq.from(HcuEvoOrdenProcedimientoDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
