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
import entidades_EJB.UceCtc;
import entidades_EJB.UceCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceCtcMedicamentoJpaController implements Serializable {

    public UceCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceCtcMedicamento uceCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtc idctc = uceCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uceCtcMedicamento.setIdctc(idctc);
            }
            em.persist(uceCtcMedicamento);
            if (idctc != null) {
                idctc.getUceCtcMedicamentoSet().add(uceCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceCtcMedicamento uceCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceCtcMedicamento persistentUceCtcMedicamento = em.find(UceCtcMedicamento.class, uceCtcMedicamento.getId());
            UceCtc idctcOld = persistentUceCtcMedicamento.getIdctc();
            UceCtc idctcNew = uceCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uceCtcMedicamento.setIdctc(idctcNew);
            }
            uceCtcMedicamento = em.merge(uceCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUceCtcMedicamentoSet().remove(uceCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUceCtcMedicamentoSet().add(uceCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceCtcMedicamento.getId();
                if (findUceCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The uceCtcMedicamento with id " + id + " no longer exists.");
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
            UceCtcMedicamento uceCtcMedicamento;
            try {
                uceCtcMedicamento = em.getReference(UceCtcMedicamento.class, id);
                uceCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            UceCtc idctc = uceCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getUceCtcMedicamentoSet().remove(uceCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(uceCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceCtcMedicamento> findUceCtcMedicamentoEntities() {
        return findUceCtcMedicamentoEntities(true, -1, -1);
    }

    public List<UceCtcMedicamento> findUceCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findUceCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<UceCtcMedicamento> findUceCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceCtcMedicamento.class));
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

    public UceCtcMedicamento findUceCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceCtcMedicamento> rt = cq.from(UceCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
