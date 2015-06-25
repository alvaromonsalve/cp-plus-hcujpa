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
import entidades_EJB.UciCtc;
import entidades_EJB.UciCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciCtcMedicamentoJpaController implements Serializable {

    public UciCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciCtcMedicamento uciCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtc idctc = uciCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uciCtcMedicamento.setIdctc(idctc);
            }
            em.persist(uciCtcMedicamento);
            if (idctc != null) {
                idctc.getUciCtcMedicamentoSet().add(uciCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciCtcMedicamento uciCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtcMedicamento persistentUciCtcMedicamento = em.find(UciCtcMedicamento.class, uciCtcMedicamento.getId());
            UciCtc idctcOld = persistentUciCtcMedicamento.getIdctc();
            UciCtc idctcNew = uciCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uciCtcMedicamento.setIdctc(idctcNew);
            }
            uciCtcMedicamento = em.merge(uciCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUciCtcMedicamentoSet().remove(uciCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUciCtcMedicamentoSet().add(uciCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciCtcMedicamento.getId();
                if (findUciCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The uciCtcMedicamento with id " + id + " no longer exists.");
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
            UciCtcMedicamento uciCtcMedicamento;
            try {
                uciCtcMedicamento = em.getReference(UciCtcMedicamento.class, id);
                uciCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            UciCtc idctc = uciCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getUciCtcMedicamentoSet().remove(uciCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(uciCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciCtcMedicamento> findUciCtcMedicamentoEntities() {
        return findUciCtcMedicamentoEntities(true, -1, -1);
    }

    public List<UciCtcMedicamento> findUciCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findUciCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<UciCtcMedicamento> findUciCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciCtcMedicamento.class));
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

    public UciCtcMedicamento findUciCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciCtcMedicamento> rt = cq.from(UciCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
