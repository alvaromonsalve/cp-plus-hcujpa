/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoOtrosdatosAdmision;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class InfoOtrosdatosAdmisionJpaController implements Serializable {

    public InfoOtrosdatosAdmisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoOtrosdatosAdmision infoOtrosdatosAdmision) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoOtrosdatosAdmision);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInfoOtrosdatosAdmision(infoOtrosdatosAdmision.getIdAdmision()) != null) {
                throw new PreexistingEntityException("InfoOtrosdatosAdmision " + infoOtrosdatosAdmision + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoOtrosdatosAdmision infoOtrosdatosAdmision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoOtrosdatosAdmision = em.merge(infoOtrosdatosAdmision);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoOtrosdatosAdmision.getIdAdmision();
                if (findInfoOtrosdatosAdmision(id) == null) {
                    throw new NonexistentEntityException("The infoOtrosdatosAdmision with id " + id + " no longer exists.");
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
            InfoOtrosdatosAdmision infoOtrosdatosAdmision;
            try {
                infoOtrosdatosAdmision = em.getReference(InfoOtrosdatosAdmision.class, id);
                infoOtrosdatosAdmision.getIdAdmision();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoOtrosdatosAdmision with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoOtrosdatosAdmision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoOtrosdatosAdmision> findInfoOtrosdatosAdmisionEntities() {
        return findInfoOtrosdatosAdmisionEntities(true, -1, -1);
    }

    public List<InfoOtrosdatosAdmision> findInfoOtrosdatosAdmisionEntities(int maxResults, int firstResult) {
        return findInfoOtrosdatosAdmisionEntities(false, maxResults, firstResult);
    }

    private List<InfoOtrosdatosAdmision> findInfoOtrosdatosAdmisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoOtrosdatosAdmision.class));
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

    public InfoOtrosdatosAdmision findInfoOtrosdatosAdmision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoOtrosdatosAdmision.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoOtrosdatosAdmisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoOtrosdatosAdmision> rt = cq.from(InfoOtrosdatosAdmision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
       public Integer getInfoOtrosdatosAdmisionCount(Integer id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(i.idAdmision) FROM InfoOtrosdatosAdmision i WHERE i.idAdmision = :id");
            q.setParameter("id", id);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
   }
    
}
