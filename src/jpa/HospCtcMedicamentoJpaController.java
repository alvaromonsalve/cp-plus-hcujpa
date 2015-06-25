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
import entidades_EJB.HospCtc;
import entidades_EJB.HospCtcMedicamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospCtcMedicamentoJpaController implements Serializable {

    public HospCtcMedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospCtcMedicamento hospCtcMedicamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtc idctc = hospCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc = em.getReference(idctc.getClass(), idctc.getId());
                hospCtcMedicamento.setIdctc(idctc);
            }
            em.persist(hospCtcMedicamento);
            if (idctc != null) {
                idctc.getHospCtcMedicamentoList().add(hospCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospCtcMedicamento hospCtcMedicamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtcMedicamento persistentHospCtcMedicamento = em.find(HospCtcMedicamento.class, hospCtcMedicamento.getId());
            HospCtc idctcOld = persistentHospCtcMedicamento.getIdctc();
            HospCtc idctcNew = hospCtcMedicamento.getIdctc();
            if (idctcNew != null) {
                idctcNew = em.getReference(idctcNew.getClass(), idctcNew.getId());
                hospCtcMedicamento.setIdctc(idctcNew);
            }
            hospCtcMedicamento = em.merge(hospCtcMedicamento);
            if (idctcOld != null && !idctcOld.equals(idctcNew)) {
                idctcOld.getHospCtcMedicamentoList().remove(hospCtcMedicamento);
                idctcOld = em.merge(idctcOld);
            }
            if (idctcNew != null && !idctcNew.equals(idctcOld)) {
                idctcNew.getHospCtcMedicamentoList().add(hospCtcMedicamento);
                idctcNew = em.merge(idctcNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospCtcMedicamento.getId();
                if (findHospCtcMedicamento(id) == null) {
                    throw new NonexistentEntityException("The hospCtcMedicamento with id " + id + " no longer exists.");
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
            HospCtcMedicamento hospCtcMedicamento;
            try {
                hospCtcMedicamento = em.getReference(HospCtcMedicamento.class, id);
                hospCtcMedicamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospCtcMedicamento with id " + id + " no longer exists.", enfe);
            }
            HospCtc idctc = hospCtcMedicamento.getIdctc();
            if (idctc != null) {
                idctc.getHospCtcMedicamentoList().remove(hospCtcMedicamento);
                idctc = em.merge(idctc);
            }
            em.remove(hospCtcMedicamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospCtcMedicamento> findHospCtcMedicamentoEntities() {
        return findHospCtcMedicamentoEntities(true, -1, -1);
    }

    public List<HospCtcMedicamento> findHospCtcMedicamentoEntities(int maxResults, int firstResult) {
        return findHospCtcMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<HospCtcMedicamento> findHospCtcMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospCtcMedicamento.class));
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

    public HospCtcMedicamento findHospCtcMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospCtcMedicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospCtcMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospCtcMedicamento> rt = cq.from(HospCtcMedicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
