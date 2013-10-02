/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InvDatosCompra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.InvSumGeneral;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class InvDatosCompraJpaController implements Serializable {

    public InvDatosCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InvDatosCompra invDatosCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvSumGeneral idSumgeneral = invDatosCompra.getIdSumgeneral();
            if (idSumgeneral != null) {
                idSumgeneral = em.getReference(idSumgeneral.getClass(), idSumgeneral.getId());
                invDatosCompra.setIdSumgeneral(idSumgeneral);
            }
            em.persist(invDatosCompra);
            if (idSumgeneral != null) {
                idSumgeneral.getInvDatosCompraList().add(invDatosCompra);
                idSumgeneral = em.merge(idSumgeneral);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InvDatosCompra invDatosCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InvDatosCompra persistentInvDatosCompra = em.find(InvDatosCompra.class, invDatosCompra.getId());
            InvSumGeneral idSumgeneralOld = persistentInvDatosCompra.getIdSumgeneral();
            InvSumGeneral idSumgeneralNew = invDatosCompra.getIdSumgeneral();
            if (idSumgeneralNew != null) {
                idSumgeneralNew = em.getReference(idSumgeneralNew.getClass(), idSumgeneralNew.getId());
                invDatosCompra.setIdSumgeneral(idSumgeneralNew);
            }
            invDatosCompra = em.merge(invDatosCompra);
            if (idSumgeneralOld != null && !idSumgeneralOld.equals(idSumgeneralNew)) {
                idSumgeneralOld.getInvDatosCompraList().remove(invDatosCompra);
                idSumgeneralOld = em.merge(idSumgeneralOld);
            }
            if (idSumgeneralNew != null && !idSumgeneralNew.equals(idSumgeneralOld)) {
                idSumgeneralNew.getInvDatosCompraList().add(invDatosCompra);
                idSumgeneralNew = em.merge(idSumgeneralNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = invDatosCompra.getId();
                if (findInvDatosCompra(id) == null) {
                    throw new NonexistentEntityException("The invDatosCompra with id " + id + " no longer exists.");
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
            InvDatosCompra invDatosCompra;
            try {
                invDatosCompra = em.getReference(InvDatosCompra.class, id);
                invDatosCompra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invDatosCompra with id " + id + " no longer exists.", enfe);
            }
            InvSumGeneral idSumgeneral = invDatosCompra.getIdSumgeneral();
            if (idSumgeneral != null) {
                idSumgeneral.getInvDatosCompraList().remove(invDatosCompra);
                idSumgeneral = em.merge(idSumgeneral);
            }
            em.remove(invDatosCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InvDatosCompra> findInvDatosCompraEntities() {
        return findInvDatosCompraEntities(true, -1, -1);
    }

    public List<InvDatosCompra> findInvDatosCompraEntities(int maxResults, int firstResult) {
        return findInvDatosCompraEntities(false, maxResults, firstResult);
    }

    private List<InvDatosCompra> findInvDatosCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InvDatosCompra.class));
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

    public InvDatosCompra findInvDatosCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InvDatosCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvDatosCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InvDatosCompra> rt = cq.from(InvDatosCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
