/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.EnfuFactsLiquidos;
import entidades_EJB.EnfuLiqAdministrados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuLiqAdministradosJpaController implements Serializable {

    public EnfuLiqAdministradosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuLiqAdministrados enfuLiqAdministrados) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsLiquidos idFactsLiquidos = enfuLiqAdministrados.getIdFactsLiquidos();
            if (idFactsLiquidos != null) {
                idFactsLiquidos = em.getReference(idFactsLiquidos.getClass(), idFactsLiquidos.getId());
                enfuLiqAdministrados.setIdFactsLiquidos(idFactsLiquidos);
            }
            em.persist(enfuLiqAdministrados);
            if (idFactsLiquidos != null) {
                idFactsLiquidos.getEnfuLiqAdministradosList().add(enfuLiqAdministrados);
                idFactsLiquidos = em.merge(idFactsLiquidos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnfuLiqAdministrados(enfuLiqAdministrados.getId()) != null) {
                throw new PreexistingEntityException("EnfuLiqAdministrados " + enfuLiqAdministrados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuLiqAdministrados enfuLiqAdministrados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuLiqAdministrados persistentEnfuLiqAdministrados = em.find(EnfuLiqAdministrados.class, enfuLiqAdministrados.getId());
            EnfuFactsLiquidos idFactsLiquidosOld = persistentEnfuLiqAdministrados.getIdFactsLiquidos();
            EnfuFactsLiquidos idFactsLiquidosNew = enfuLiqAdministrados.getIdFactsLiquidos();
            if (idFactsLiquidosNew != null) {
                idFactsLiquidosNew = em.getReference(idFactsLiquidosNew.getClass(), idFactsLiquidosNew.getId());
                enfuLiqAdministrados.setIdFactsLiquidos(idFactsLiquidosNew);
            }
            enfuLiqAdministrados = em.merge(enfuLiqAdministrados);
            if (idFactsLiquidosOld != null && !idFactsLiquidosOld.equals(idFactsLiquidosNew)) {
                idFactsLiquidosOld.getEnfuLiqAdministradosList().remove(enfuLiqAdministrados);
                idFactsLiquidosOld = em.merge(idFactsLiquidosOld);
            }
            if (idFactsLiquidosNew != null && !idFactsLiquidosNew.equals(idFactsLiquidosOld)) {
                idFactsLiquidosNew.getEnfuLiqAdministradosList().add(enfuLiqAdministrados);
                idFactsLiquidosNew = em.merge(idFactsLiquidosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuLiqAdministrados.getId();
                if (findEnfuLiqAdministrados(id) == null) {
                    throw new NonexistentEntityException("The enfuLiqAdministrados with id " + id + " no longer exists.");
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
            EnfuLiqAdministrados enfuLiqAdministrados;
            try {
                enfuLiqAdministrados = em.getReference(EnfuLiqAdministrados.class, id);
                enfuLiqAdministrados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuLiqAdministrados with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsLiquidos idFactsLiquidos = enfuLiqAdministrados.getIdFactsLiquidos();
            if (idFactsLiquidos != null) {
                idFactsLiquidos.getEnfuLiqAdministradosList().remove(enfuLiqAdministrados);
                idFactsLiquidos = em.merge(idFactsLiquidos);
            }
            em.remove(enfuLiqAdministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuLiqAdministrados> findEnfuLiqAdministradosEntities() {
        return findEnfuLiqAdministradosEntities(true, -1, -1);
    }

    public List<EnfuLiqAdministrados> findEnfuLiqAdministradosEntities(int maxResults, int firstResult) {
        return findEnfuLiqAdministradosEntities(false, maxResults, firstResult);
    }

    private List<EnfuLiqAdministrados> findEnfuLiqAdministradosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuLiqAdministrados.class));
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

    public EnfuLiqAdministrados findEnfuLiqAdministrados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuLiqAdministrados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuLiqAdministradosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuLiqAdministrados> rt = cq.from(EnfuLiqAdministrados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuLiqAdministrados> getLiquidosAdministrados(int idf) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT a FROM EnfuLiqAdministrados a WHERE a.idFactsLiquidos.id=:idf AND a.estado='1'");
        Q.setParameter("idf", idf);
        return Q.getResultList();
    }

}
