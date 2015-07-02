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
import entidades_EJB.EnfuSolicitudSum;
import entidades_EJB.EnfuSolicitudSumDesc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class EnfuSolicitudSumDescJpaController implements Serializable {

    public EnfuSolicitudSumDescJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuSolicitudSumDesc enfuSolicitudSumDesc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuSolicitudSum idEnfuSolicitudSum = enfuSolicitudSumDesc.getIdEnfuSolicitudSum();
            if (idEnfuSolicitudSum != null) {
                idEnfuSolicitudSum = em.getReference(idEnfuSolicitudSum.getClass(), idEnfuSolicitudSum.getId());
                enfuSolicitudSumDesc.setIdEnfuSolicitudSum(idEnfuSolicitudSum);
            }
            em.persist(enfuSolicitudSumDesc);
            if (idEnfuSolicitudSum != null) {
                idEnfuSolicitudSum.getEnfuSolicitudSumDescList().add(enfuSolicitudSumDesc);
                idEnfuSolicitudSum = em.merge(idEnfuSolicitudSum);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuSolicitudSumDesc enfuSolicitudSumDesc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuSolicitudSumDesc persistentEnfuSolicitudSumDesc = em.find(EnfuSolicitudSumDesc.class, enfuSolicitudSumDesc.getId());
            EnfuSolicitudSum idEnfuSolicitudSumOld = persistentEnfuSolicitudSumDesc.getIdEnfuSolicitudSum();
            EnfuSolicitudSum idEnfuSolicitudSumNew = enfuSolicitudSumDesc.getIdEnfuSolicitudSum();
            if (idEnfuSolicitudSumNew != null) {
                idEnfuSolicitudSumNew = em.getReference(idEnfuSolicitudSumNew.getClass(), idEnfuSolicitudSumNew.getId());
                enfuSolicitudSumDesc.setIdEnfuSolicitudSum(idEnfuSolicitudSumNew);
            }
            enfuSolicitudSumDesc = em.merge(enfuSolicitudSumDesc);
            if (idEnfuSolicitudSumOld != null && !idEnfuSolicitudSumOld.equals(idEnfuSolicitudSumNew)) {
                idEnfuSolicitudSumOld.getEnfuSolicitudSumDescList().remove(enfuSolicitudSumDesc);
                idEnfuSolicitudSumOld = em.merge(idEnfuSolicitudSumOld);
            }
            if (idEnfuSolicitudSumNew != null && !idEnfuSolicitudSumNew.equals(idEnfuSolicitudSumOld)) {
                idEnfuSolicitudSumNew.getEnfuSolicitudSumDescList().add(enfuSolicitudSumDesc);
                idEnfuSolicitudSumNew = em.merge(idEnfuSolicitudSumNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuSolicitudSumDesc.getId();
                if (findEnfuSolicitudSumDesc(id) == null) {
                    throw new NonexistentEntityException("The enfuSolicitudSumDesc with id " + id + " no longer exists.");
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
            EnfuSolicitudSumDesc enfuSolicitudSumDesc;
            try {
                enfuSolicitudSumDesc = em.getReference(EnfuSolicitudSumDesc.class, id);
                enfuSolicitudSumDesc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuSolicitudSumDesc with id " + id + " no longer exists.", enfe);
            }
            EnfuSolicitudSum idEnfuSolicitudSum = enfuSolicitudSumDesc.getIdEnfuSolicitudSum();
            if (idEnfuSolicitudSum != null) {
                idEnfuSolicitudSum.getEnfuSolicitudSumDescList().remove(enfuSolicitudSumDesc);
                idEnfuSolicitudSum = em.merge(idEnfuSolicitudSum);
            }
            em.remove(enfuSolicitudSumDesc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuSolicitudSumDesc> findEnfuSolicitudSumDescEntities() {
        return findEnfuSolicitudSumDescEntities(true, -1, -1);
    }

    public List<EnfuSolicitudSumDesc> findEnfuSolicitudSumDescEntities(int maxResults, int firstResult) {
        return findEnfuSolicitudSumDescEntities(false, maxResults, firstResult);
    }

    private List<EnfuSolicitudSumDesc> findEnfuSolicitudSumDescEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuSolicitudSumDesc.class));
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

    public EnfuSolicitudSumDesc findEnfuSolicitudSumDesc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuSolicitudSumDesc.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuSolicitudSumDescCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuSolicitudSumDesc> rt = cq.from(EnfuSolicitudSumDesc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
