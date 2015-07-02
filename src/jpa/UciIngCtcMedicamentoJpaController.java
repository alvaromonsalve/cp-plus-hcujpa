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
import entidades_EJB.UciIngCtc;
import entidades_EJB.UciIngCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngCtcMedicamentoJpaController implements Serializable {

    public UciIngCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngCtcMedicamento uciIngCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtc idctc = uciIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uciIngCtcMedicamento.setIdctc(idctc);
            }
            em.persist(uciIngCtcMedicamento);
            if (idctc != null) {
                idctc.getUciIngCtcMedicamentoList().add(uciIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngCtcMedicamento uciIngCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtcMedicamento persistentUciIngCtcMedicamento = em.find(UciIngCtcMedicamento.class, uciIngCtcMedicamento.getId());
            UciIngCtc idctcOld = persistentUciIngCtcMedicamento.getIdctc();
            UciIngCtc idctcNew = uciIngCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uciIngCtcMedicamento.setIdctc(idctcNew);
            }
            uciIngCtcMedicamento = em.merge(uciIngCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUciIngCtcMedicamentoList().remove(uciIngCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUciIngCtcMedicamentoList().add(uciIngCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngCtcMedicamento.getId();
                if (findUciIngCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The uciIngCtcMedicamento with id " + id + " no longer exists.");
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
            UciIngCtcMedicamento uciIngCtcMedicamento;
            try {
                uciIngCtcMedicamento = em.getReference(UciIngCtcMedicamento.class, id);
                uciIngCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            UciIngCtc idctc = uciIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getUciIngCtcMedicamentoList().remove(uciIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(uciIngCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngCtcMedicamento> findUciIngCtcMedicamentoEntities() {
        return findUciIngCtcMedicamentoEntities(true, -1, -1);
    }

    public List<UciIngCtcMedicamento> findUciIngCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findUciIngCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<UciIngCtcMedicamento> findUciIngCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngCtcMedicamento.class));
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

    public UciIngCtcMedicamento findUciIngCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngCtcMedicamento> rt = cq.from(UciIngCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
