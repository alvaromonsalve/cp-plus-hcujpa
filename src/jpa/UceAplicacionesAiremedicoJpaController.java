/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceAplicacionesAiremedico;
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
public class UceAplicacionesAiremedicoJpaController implements Serializable {

    public UceAplicacionesAiremedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceAplicacionesAiremedico uceAplicacionesAiremedico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uceAplicacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceAplicacionesAiremedico uceAplicacionesAiremedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uceAplicacionesAiremedico = em.merge(uceAplicacionesAiremedico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceAplicacionesAiremedico.getId();
                if (findUceAplicacionesAiremedico(id) == null) {
                    throw new NonexistentEntityException("The uceAplicacionesAiremedico with id " + id + " no longer exists.");
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
            UceAplicacionesAiremedico uceAplicacionesAiremedico;
            try {
                uceAplicacionesAiremedico = em.getReference(UceAplicacionesAiremedico.class, id);
                uceAplicacionesAiremedico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceAplicacionesAiremedico with id " + id + " no longer exists.", enfe);
            }
            em.remove(uceAplicacionesAiremedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceAplicacionesAiremedico> findUceAplicacionesAiremedicoEntities() {
        return findUceAplicacionesAiremedicoEntities(true, -1, -1);
    }

    public List<UceAplicacionesAiremedico> findUceAplicacionesAiremedicoEntities(int maxResults, int firstResult) {
        return findUceAplicacionesAiremedicoEntities(false, maxResults, firstResult);
    }

    private List<UceAplicacionesAiremedico> findUceAplicacionesAiremedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceAplicacionesAiremedico.class));
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

    public UceAplicacionesAiremedico findUceAplicacionesAiremedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceAplicacionesAiremedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceAplicacionesAiremedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceAplicacionesAiremedico> rt = cq.from(UceAplicacionesAiremedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UceAplicacionesAiremedico> find_aplicacionesO2(int hc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM UceAplicacionesAiremedico i WHERE (i.idHistoriac.id=:idhc) AND (i.estado='1')");
            Q.setParameter("idhc", hc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return Q.getResultList();
    }

    public List<UceAplicacionesAiremedico> get_AplicacionesOxigeno(int h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        try {
            Q = em.createQuery("SELECT O FROM UceAplicacionesAiremedico O WHERE O.idHistoriac.id=:hc AND O.estado <> '0'");
            Q.setParameter("hc", h);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

}
