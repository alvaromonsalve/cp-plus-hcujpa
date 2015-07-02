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
import entidades_EJB.UrgIngCtc;
import entidades_EJB.UrgIngCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngCtcMedicamentoJpaController implements Serializable {

    public UrgIngCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngCtcMedicamento urgIngCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtc idctc = urgIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                urgIngCtcMedicamento.setIdctc(idctc);
            }
            em.persist(urgIngCtcMedicamento);
            if (idctc != null) {
                idctc.getUrgIngCtcMedicamentoList().add(urgIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngCtcMedicamento urgIngCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtcMedicamento persistentUrgIngCtcMedicamento = em.find(UrgIngCtcMedicamento.class, urgIngCtcMedicamento.getId());
            UrgIngCtc idctcOld = persistentUrgIngCtcMedicamento.getIdctc();
            UrgIngCtc idctcNew = urgIngCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                urgIngCtcMedicamento.setIdctc(idctcNew);
            }
            urgIngCtcMedicamento = em.merge(urgIngCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUrgIngCtcMedicamentoList().remove(urgIngCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUrgIngCtcMedicamentoList().add(urgIngCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngCtcMedicamento.getId();
                if (findUrgIngCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The urgIngCtcMedicamento with id " + id + " no longer exists.");
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
            UrgIngCtcMedicamento urgIngCtcMedicamento;
            try {
                urgIngCtcMedicamento = em.getReference(UrgIngCtcMedicamento.class, id);
                urgIngCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            UrgIngCtc idctc = urgIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getUrgIngCtcMedicamentoList().remove(urgIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(urgIngCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngCtcMedicamento> findUrgIngCtcMedicamentoEntities() {
        return findUrgIngCtcMedicamentoEntities(true, -1, -1);
    }

    public List<UrgIngCtcMedicamento> findUrgIngCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findUrgIngCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<UrgIngCtcMedicamento> findUrgIngCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngCtcMedicamento.class));
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

    public UrgIngCtcMedicamento findUrgIngCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngCtcMedicamento> rt = cq.from(UrgIngCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
