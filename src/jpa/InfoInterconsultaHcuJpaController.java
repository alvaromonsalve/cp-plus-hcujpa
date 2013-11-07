/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoHistoriac;
import entidades.InfoInterconsultaHcu;
import entidades.StaticEspecialidades;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class InfoInterconsultaHcuJpaController implements Serializable {

    public InfoInterconsultaHcuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoInterconsultaHcu infoInterconsultaHcu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoInterconsultaHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoInterconsultaHcu infoInterconsultaHcu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoInterconsultaHcu = em.merge(infoInterconsultaHcu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoInterconsultaHcu.getId();
                if (findInfoInterconsultaHcu(id) == null) {
                    throw new NonexistentEntityException("The infoInterconsultaHcu with id " + id + " no longer exists.");
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
            InfoInterconsultaHcu infoInterconsultaHcu;
            try {
                infoInterconsultaHcu = em.getReference(InfoInterconsultaHcu.class, id);
                infoInterconsultaHcu.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoInterconsultaHcu with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoInterconsultaHcu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoInterconsultaHcu> findInfoInterconsultaHcuEntities() {
        return findInfoInterconsultaHcuEntities(true, -1, -1);
    }

    public List<InfoInterconsultaHcu> findInfoInterconsultaHcuEntities(int maxResults, int firstResult) {
        return findInfoInterconsultaHcuEntities(false, maxResults, firstResult);
    }

    private List<InfoInterconsultaHcu> findInfoInterconsultaHcuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoInterconsultaHcu.class));
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

    public InfoInterconsultaHcu findInfoInterconsultaHcu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoInterconsultaHcu.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoInterconsultaHcuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoInterconsultaHcu> rt = cq.from(InfoInterconsultaHcu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado
    
    public InfoInterconsultaHcu findInterconsultaHcu_HC(InfoHistoriac ih, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM InfoInterconsultaHcu i WHERE i.idHistoriac = :ih AND i.idEspecialidades = :se");
            q.setParameter("ih", ih.getId());
            q.setParameter("se", se.getId());            
            return (InfoInterconsultaHcu)q.getSingleResult();
        }catch(Exception ex){
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<InfoInterconsultaHcu> listInterconsultaOtrasHcu_HC(InfoHistoriac ih){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM InfoInterconsultaHcu i WHERE i.idHistoriac = :ih AND i.idEspecialidades <> 28 "
                    + "AND i.idEspecialidades <> 22 AND i.idEspecialidades <> 14 AND i.idEspecialidades <> 10 AND i.idEspecialidades <> 3");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<InfoInterconsultaHcu> listInterconsultaHcu(InfoHistoriac ih){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            Query q = em.createQuery("SELECT i FROM InfoInterconsultaHcu i WHERE i.idHistoriac = :ih");
            q.setParameter("ih", ih.getId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
