/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuApLiquidos;
import entidades_EJB.EnfuControlLiquidos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuApLiquidosJpaControllerE implements Serializable {

    public EnfuApLiquidosJpaControllerE(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuApLiquidos enfuApLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuControlLiquidos idControles = enfuApLiquidos.getIdControles();
            if (idControles != null) {
                idControles = em.getReference(idControles.getClass(), idControles.getId());
                enfuApLiquidos.setIdControles(idControles);
            }
            em.persist(enfuApLiquidos);
            if (idControles != null) {
                idControles.getEnfuApLiquidosList().add(enfuApLiquidos);
                idControles = em.merge(idControles);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuApLiquidos enfuApLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuApLiquidos persistentEnfuApLiquidos = em.find(EnfuApLiquidos.class, enfuApLiquidos.getId());
            EnfuControlLiquidos idControlesOld = persistentEnfuApLiquidos.getIdControles();
            EnfuControlLiquidos idControlesNew = enfuApLiquidos.getIdControles();
            if (idControlesNew != null) {
                idControlesNew = em.getReference(idControlesNew.getClass(), idControlesNew.getId());
                enfuApLiquidos.setIdControles(idControlesNew);
            }
            enfuApLiquidos = em.merge(enfuApLiquidos);
            if (idControlesOld != null && !idControlesOld.equals(idControlesNew)) {
                idControlesOld.getEnfuApLiquidosList().remove(enfuApLiquidos);
                idControlesOld = em.merge(idControlesOld);
            }
            if (idControlesNew != null && !idControlesNew.equals(idControlesOld)) {
                idControlesNew.getEnfuApLiquidosList().add(enfuApLiquidos);
                idControlesNew = em.merge(idControlesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuApLiquidos.getId();
                if (findEnfuApLiquidos(id) == null) {
                    throw new NonexistentEntityException("The enfuApLiquidos with id " + id + " no longer exists.");
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
            EnfuApLiquidos enfuApLiquidos;
            try {
                enfuApLiquidos = em.getReference(EnfuApLiquidos.class, id);
                enfuApLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuApLiquidos with id " + id + " no longer exists.", enfe);
            }
            EnfuControlLiquidos idControles = enfuApLiquidos.getIdControles();
            if (idControles != null) {
                idControles.getEnfuApLiquidosList().remove(enfuApLiquidos);
                idControles = em.merge(idControles);
            }
            em.remove(enfuApLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuApLiquidos> findEnfuApLiquidosEntities() {
        return findEnfuApLiquidosEntities(true, -1, -1);
    }

    public List<EnfuApLiquidos> findEnfuApLiquidosEntities(int maxResults, int firstResult) {
        return findEnfuApLiquidosEntities(false, maxResults, firstResult);
    }

    private List<EnfuApLiquidos> findEnfuApLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuApLiquidos.class));
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

    public EnfuApLiquidos findEnfuApLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuApLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuApLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuApLiquidos> rt = cq.from(EnfuApLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuApLiquidos> find_Aplicacion_LiQ(EnfuControlLiquidos enc) {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM EnfuApLiquidos i WHERE i.idControles=:id_control AND i.estado='1'");
            Q.setParameter("id_control", enc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

}
