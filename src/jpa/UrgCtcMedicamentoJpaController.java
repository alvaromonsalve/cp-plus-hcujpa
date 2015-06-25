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
import entidades_EJB.UrgCtc;
import entidades_EJB.UrgCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgCtcMedicamentoJpaController implements Serializable {

    public UrgCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgCtcMedicamento urgCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtc idctc = urgCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                urgCtcMedicamento.setIdctc(idctc);
            }
            em.persist(urgCtcMedicamento);
            if (idctc != null) {
                idctc.getUrgCtcMedicamentoSet().add(urgCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgCtcMedicamento urgCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgCtcMedicamento persistentUrgCtcMedicamento = em.find(UrgCtcMedicamento.class, urgCtcMedicamento.getId());
            UrgCtc idctcOld = persistentUrgCtcMedicamento.getIdctc();
            UrgCtc idctcNew = urgCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                urgCtcMedicamento.setIdctc(idctcNew);
            }
            urgCtcMedicamento = em.merge(urgCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUrgCtcMedicamentoSet().remove(urgCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUrgCtcMedicamentoSet().add(urgCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgCtcMedicamento.getId();
                if (findUrgCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The urgCtcMedicamento with id " + id + " no longer exists.");
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
            UrgCtcMedicamento urgCtcMedicamento;
            try {
                urgCtcMedicamento = em.getReference(UrgCtcMedicamento.class, id);
                urgCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            UrgCtc idctc = urgCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getUrgCtcMedicamentoSet().remove(urgCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(urgCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgCtcMedicamento> findUrgCtcMedicamentoEntities() {
        return findUrgCtcMedicamentoEntities(true, -1, -1);
    }

    public List<UrgCtcMedicamento> findUrgCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findUrgCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<UrgCtcMedicamento> findUrgCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgCtcMedicamento.class));
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

    public UrgCtcMedicamento findUrgCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgCtcMedicamento> rt = cq.from(UrgCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
