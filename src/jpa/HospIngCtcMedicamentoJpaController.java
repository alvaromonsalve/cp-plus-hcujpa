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
import entidades_EJB.HospIngCtc;
import entidades_EJB.HospIngCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngCtcMedicamentoJpaController implements Serializable {

    public HospIngCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngCtcMedicamento hospIngCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtc idctc = hospIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                hospIngCtcMedicamento.setIdctc(idctc);
            }
            em.persist(hospIngCtcMedicamento);
            if (idctc != null) {
                idctc.getHospIngCtcMedicamentoList().add(hospIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngCtcMedicamento hospIngCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtcMedicamento persistentHospIngCtcMedicamento = em.find(HospIngCtcMedicamento.class, hospIngCtcMedicamento.getId());
            HospIngCtc idctcOld = persistentHospIngCtcMedicamento.getIdctc();
            HospIngCtc idctcNew = hospIngCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                hospIngCtcMedicamento.setIdctc(idctcNew);
            }
            hospIngCtcMedicamento = em.merge(hospIngCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getHospIngCtcMedicamentoList().remove(hospIngCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getHospIngCtcMedicamentoList().add(hospIngCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngCtcMedicamento.getId();
                if (findHospIngCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The hospIngCtcMedicamento with id " + id + " no longer exists.");
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
            HospIngCtcMedicamento hospIngCtcMedicamento;
            try {
                hospIngCtcMedicamento = em.getReference(HospIngCtcMedicamento.class, id);
                hospIngCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            HospIngCtc idctc = hospIngCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getHospIngCtcMedicamentoList().remove(hospIngCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(hospIngCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngCtcMedicamento> findHospIngCtcMedicamentoEntities() {
        return findHospIngCtcMedicamentoEntities(true, -1, -1);
    }

    public List<HospIngCtcMedicamento> findHospIngCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findHospIngCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<HospIngCtcMedicamento> findHospIngCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngCtcMedicamento.class));
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

    public HospIngCtcMedicamento findHospIngCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngCtcMedicamento> rt = cq.from(HospIngCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
