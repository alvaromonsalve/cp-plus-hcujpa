/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoAntPersonales;
import entidades.InfoPaciente;
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
public class InfoAntPersonalesJpaController implements Serializable {

    public InfoAntPersonalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoAntPersonales infoAntPersonales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoAntPersonales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoAntPersonales infoAntPersonales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoAntPersonales = em.merge(infoAntPersonales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoAntPersonales.getId();
                if (findInfoAntPersonales(id) == null) {
                    throw new NonexistentEntityException("The infoAntPersonales with id " + id + " no longer exists.");
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
            InfoAntPersonales infoAntPersonales;
            try {
                infoAntPersonales = em.getReference(InfoAntPersonales.class, id);
                infoAntPersonales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoAntPersonales with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoAntPersonales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoAntPersonales> findInfoAntPersonalesEntities() {
        return findInfoAntPersonalesEntities(true, -1, -1);
    }

    public List<InfoAntPersonales> findInfoAntPersonalesEntities(int maxResults, int firstResult) {
        return findInfoAntPersonalesEntities(false, maxResults, firstResult);
    }

    private List<InfoAntPersonales> findInfoAntPersonalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoAntPersonales.class));
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

    public InfoAntPersonales findInfoAntPersonales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoAntPersonales.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoAntPersonalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoAntPersonales> rt = cq.from(InfoAntPersonales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        //Codigo no Auto-generado
    
    public InfoAntPersonales findInfoAntPersonalesIDPac(InfoPaciente ip){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM InfoAntPersonales i WHERE i.idPaciente = :ip");
            q.setParameter("ip", ip.getId());
            return (InfoAntPersonales)q.getSingleResult();
        }catch(Exception ex){
            return null;
        } finally {
            em.close();
        }
    }
    
}
