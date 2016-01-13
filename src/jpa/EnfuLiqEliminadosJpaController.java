/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.EnfuFactsLiquidos;
import entidades_EJB.EnfuLiqEliminados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuLiqEliminadosJpaController implements Serializable {

    public EnfuLiqEliminadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuLiqEliminados enfuLiqEliminados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsLiquidos idFactsLiquidos = enfuLiqEliminados.getIdFactsLiquidos();
            if (idFactsLiquidos != null) {
                idFactsLiquidos = em.getReference(idFactsLiquidos.getClass(), idFactsLiquidos.getId());
                enfuLiqEliminados.setIdFactsLiquidos(idFactsLiquidos);
            }
            em.persist(enfuLiqEliminados);
            if (idFactsLiquidos != null) {
                idFactsLiquidos.getEnfuLiqEliminadosList().add(enfuLiqEliminados);
                idFactsLiquidos = em.merge(idFactsLiquidos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuLiqEliminados enfuLiqEliminados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuLiqEliminados persistentEnfuLiqEliminados = em.find(EnfuLiqEliminados.class, enfuLiqEliminados.getId());
            EnfuFactsLiquidos idFactsLiquidosOld = persistentEnfuLiqEliminados.getIdFactsLiquidos();
            EnfuFactsLiquidos idFactsLiquidosNew = enfuLiqEliminados.getIdFactsLiquidos();
            if (idFactsLiquidosNew != null) {
                idFactsLiquidosNew = em.getReference(idFactsLiquidosNew.getClass(), idFactsLiquidosNew.getId());
                enfuLiqEliminados.setIdFactsLiquidos(idFactsLiquidosNew);
            }
            enfuLiqEliminados = em.merge(enfuLiqEliminados);
            if (idFactsLiquidosOld != null && !idFactsLiquidosOld.equals(idFactsLiquidosNew)) {
                idFactsLiquidosOld.getEnfuLiqEliminadosList().remove(enfuLiqEliminados);
                idFactsLiquidosOld = em.merge(idFactsLiquidosOld);
            }
            if (idFactsLiquidosNew != null && !idFactsLiquidosNew.equals(idFactsLiquidosOld)) {
                idFactsLiquidosNew.getEnfuLiqEliminadosList().add(enfuLiqEliminados);
                idFactsLiquidosNew = em.merge(idFactsLiquidosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuLiqEliminados.getId();
                if (findEnfuLiqEliminados(id) == null) {
                    throw new NonexistentEntityException("The enfuLiqEliminados with id " + id + " no longer exists.");
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
            EnfuLiqEliminados enfuLiqEliminados;
            try {
                enfuLiqEliminados = em.getReference(EnfuLiqEliminados.class, id);
                enfuLiqEliminados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuLiqEliminados with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsLiquidos idFactsLiquidos = enfuLiqEliminados.getIdFactsLiquidos();
            if (idFactsLiquidos != null) {
                idFactsLiquidos.getEnfuLiqEliminadosList().remove(enfuLiqEliminados);
                idFactsLiquidos = em.merge(idFactsLiquidos);
            }
            em.remove(enfuLiqEliminados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuLiqEliminados> findEnfuLiqEliminadosEntities() {
        return findEnfuLiqEliminadosEntities(true, -1, -1);
    }

    public List<EnfuLiqEliminados> findEnfuLiqEliminadosEntities(int maxResults, int firstResult) {
        return findEnfuLiqEliminadosEntities(false, maxResults, firstResult);
    }

    private List<EnfuLiqEliminados> findEnfuLiqEliminadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuLiqEliminados.class));
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

    public EnfuLiqEliminados findEnfuLiqEliminados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuLiqEliminados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuLiqEliminadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuLiqEliminados> rt = cq.from(EnfuLiqEliminados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuLiqEliminados> getLiqEliminados(int id) {
        EntityManager em = getEntityManager();
        Query q = null;
        q = em.createQuery("SELECT e FROM EnfuLiqEliminados e WHERE e.idFactsLiquidos.id=:liq AND e.estado='1'");
        q.setParameter("liq", id);
        return q.getResultList();
    }
}
