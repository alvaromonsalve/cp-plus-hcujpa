/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuControlLiquidos;
import entidades_EJB.EnfuElLiquidos;
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
public class EnfuElLiquidosJpaController implements Serializable {

    public EnfuElLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuElLiquidos enfuElLiquidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuControlLiquidos idControles = enfuElLiquidos.getIdControles();
            if (idControles != null) {
                idControles = em.getReference(idControles.getClass(), idControles.getId());
                enfuElLiquidos.setIdControles(idControles);
            }
            em.persist(enfuElLiquidos);
            if (idControles != null) {
                idControles.getEnfuElLiquidosList().add(enfuElLiquidos);
                idControles = em.merge(idControles);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuElLiquidos enfuElLiquidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuElLiquidos persistentEnfuElLiquidos = em.find(EnfuElLiquidos.class, enfuElLiquidos.getId());
            EnfuControlLiquidos idControlesOld = persistentEnfuElLiquidos.getIdControles();
            EnfuControlLiquidos idControlesNew = enfuElLiquidos.getIdControles();
            if (idControlesNew != null) {
                idControlesNew = em.getReference(idControlesNew.getClass(), idControlesNew.getId());
                enfuElLiquidos.setIdControles(idControlesNew);
            }
            enfuElLiquidos = em.merge(enfuElLiquidos);
            if (idControlesOld != null && !idControlesOld.equals(idControlesNew)) {
                idControlesOld.getEnfuElLiquidosList().remove(enfuElLiquidos);
                idControlesOld = em.merge(idControlesOld);
            }
            if (idControlesNew != null && !idControlesNew.equals(idControlesOld)) {
                idControlesNew.getEnfuElLiquidosList().add(enfuElLiquidos);
                idControlesNew = em.merge(idControlesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuElLiquidos.getId();
                if (findEnfuElLiquidos(id) == null) {
                    throw new NonexistentEntityException("The enfuElLiquidos with id " + id + " no longer exists.");
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
            EnfuElLiquidos enfuElLiquidos;
            try {
                enfuElLiquidos = em.getReference(EnfuElLiquidos.class, id);
                enfuElLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuElLiquidos with id " + id + " no longer exists.", enfe);
            }
            EnfuControlLiquidos idControles = enfuElLiquidos.getIdControles();
            if (idControles != null) {
                idControles.getEnfuElLiquidosList().remove(enfuElLiquidos);
                idControles = em.merge(idControles);
            }
            em.remove(enfuElLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuElLiquidos> findEnfuElLiquidosEntities() {
        return findEnfuElLiquidosEntities(true, -1, -1);
    }

    public List<EnfuElLiquidos> findEnfuElLiquidosEntities(int maxResults, int firstResult) {
        return findEnfuElLiquidosEntities(false, maxResults, firstResult);
    }

    private List<EnfuElLiquidos> findEnfuElLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuElLiquidos.class));
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

    public EnfuElLiquidos findEnfuElLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuElLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuElLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuElLiquidos> rt = cq.from(EnfuElLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<EnfuElLiquidos>find_Eliminados(EnfuControlLiquidos control){
        Query Q=null;
        EntityManager em = getEntityManager();
        try {
          Q=em.createQuery("SELECT i FROM EnfuElLiquidos i WHERE i.idControles=:ctr AND i.estado='1'");
          Q.setParameter("ctr", control);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }
    
}
