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
import entidades_EJB.UceIngCtc;
import entidades_EJB.UceIngCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngCtcMedicamentoJpaController implements Serializable {

    public UceIngCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngCtcMedicamento uceIngCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtc idctc = uceIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                uceIngCtcMedicamento.setIdctc(idctc);
            }
            em.persist(uceIngCtcMedicamento);
            if (idctc != null) {
                idctc.getUceIngCtcMedicamentoList().add(uceIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngCtcMedicamento uceIngCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtcMedicamento persistentUceIngCtcMedicamento = em.find(UceIngCtcMedicamento.class, uceIngCtcMedicamento.getId());
            UceIngCtc idctcOld = persistentUceIngCtcMedicamento.getIdctc();
            UceIngCtc idctcNew = uceIngCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                uceIngCtcMedicamento.setIdctc(idctcNew);
            }
            uceIngCtcMedicamento = em.merge(uceIngCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getUceIngCtcMedicamentoList().remove(uceIngCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getUceIngCtcMedicamentoList().add(uceIngCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngCtcMedicamento.getId();
                if (findUceIngCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The uceIngCtcMedicamento with id " + id + " no longer exists.");
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
            UceIngCtcMedicamento uceIngCtcMedicamento;
            try {
                uceIngCtcMedicamento = em.getReference(UceIngCtcMedicamento.class, id);
                uceIngCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            UceIngCtc idctc = uceIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getUceIngCtcMedicamentoList().remove(uceIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(uceIngCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngCtcMedicamento> findUceIngCtcMedicamentoEntities() {
        return findUceIngCtcMedicamentoEntities(true, -1, -1);
    }

    public List<UceIngCtcMedicamento> findUceIngCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findUceIngCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<UceIngCtcMedicamento> findUceIngCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngCtcMedicamento.class));
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

    public UceIngCtcMedicamento findUceIngCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngCtcMedicamento> rt = cq.from(UceIngCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
