/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeCtc;
import entidades_EJB.CeCtcMedicamento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CeCtcMedicamentoJpaController implements Serializable {

    public CeCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeCtcMedicamento ceCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeCtcMedicamento ceCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceCtcMedicamento = em.merge(ceCtcMedicamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceCtcMedicamento.getId();
                if (findCeCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The ceCtcMedicamento with id " + id + " no longer exists.");
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
            CeCtcMedicamento ceCtcMedicamento;
            try {
                ceCtcMedicamento = em.getReference(CeCtcMedicamento.class, id);
                ceCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeCtcMedicamento> findCeCtcMedicamentoEntities() {
        return findCeCtcMedicamentoEntities(true, -1, -1);
    }

    public List<CeCtcMedicamento> findCeCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findCeCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<CeCtcMedicamento> findCeCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeCtcMedicamento.class));
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

    public CeCtcMedicamento findCeCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeCtcMedicamento> rt = cq.from(CeCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeCtcMedicamento> getMedicamentosNOPOS(CeCtc c) {
        Query q = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT m FROM CeCtcMedicamento m WHERE m.idctc=:ctc AND (m.tipo='0' OR m.tipo='1')");
        q.setParameter("ctc", c);
        return q.getResultList();
    }
}
