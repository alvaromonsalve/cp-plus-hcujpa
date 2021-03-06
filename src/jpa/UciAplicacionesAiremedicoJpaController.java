/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciAplicacionesAiremedico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class UciAplicacionesAiremedicoJpaController implements Serializable {

    public UciAplicacionesAiremedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciAplicacionesAiremedico uciAplicacionesAiremedico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uciAplicacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciAplicacionesAiremedico uciAplicacionesAiremedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uciAplicacionesAiremedico = em.merge(uciAplicacionesAiremedico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciAplicacionesAiremedico.getId();
                if (findUciAplicacionesAiremedico(id) == null) {
                    throw new NonexistentEntityException("The uciAplicacionesAiremedico with id " + id + " no longer exists.");
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
            UciAplicacionesAiremedico uciAplicacionesAiremedico;
            try {
                uciAplicacionesAiremedico = em.getReference(UciAplicacionesAiremedico.class, id);
                uciAplicacionesAiremedico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciAplicacionesAiremedico with id " + id + " no longer exists.", enfe);
            }
            em.remove(uciAplicacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciAplicacionesAiremedico> findUciAplicacionesAiremedicoEntities() {
        return findUciAplicacionesAiremedicoEntities(true, -1, -1);
    }

    public List<UciAplicacionesAiremedico> findUciAplicacionesAiremedicoEntities(int maxResults, int firstResult) {
        return findUciAplicacionesAiremedicoEntities(false, maxResults, firstResult);
    }

    private List<UciAplicacionesAiremedico> findUciAplicacionesAiremedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciAplicacionesAiremedico.class));
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

    public UciAplicacionesAiremedico findUciAplicacionesAiremedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciAplicacionesAiremedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciAplicacionesAiremedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciAplicacionesAiremedico> rt = cq.from(UciAplicacionesAiremedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UciAplicacionesAiremedico> find_aplicacionesO2(int hc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM UciAplicacionesAiremedico i WHERE (i.idHistoriac.id=:idhc) AND (i.estado='1')");
            Q.setParameter("idhc", hc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return Q.getResultList();
    }

    public List<UciAplicacionesAiremedico> get_AplicacionesOxigeno(int h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        try {
            Q = em.createQuery("SELECT O FROM UciAplicacionesAiremedico O WHERE O.idHistoriac.id=:hc AND O.estado <> '0'");
            Q.setParameter("hc", h);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

}
