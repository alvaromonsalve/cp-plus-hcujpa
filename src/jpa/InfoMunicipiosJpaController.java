/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoMunicipios;
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
 * @author Administrador
 */
public class InfoMunicipiosJpaController implements Serializable {

    public InfoMunicipiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoMunicipios infoMunicipios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(infoMunicipios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoMunicipios infoMunicipios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            infoMunicipios = em.merge(infoMunicipios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoMunicipios.getId();
                if (findInfoMunicipios(id) == null) {
                    throw new NonexistentEntityException("The infoMunicipios with id " + id + " no longer exists.");
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
            InfoMunicipios infoMunicipios;
            try {
                infoMunicipios = em.getReference(InfoMunicipios.class, id);
                infoMunicipios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoMunicipios with id " + id + " no longer exists.", enfe);
            }
            em.remove(infoMunicipios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoMunicipios> findInfoMunicipiosEntities() {
        return findInfoMunicipiosEntities(true, -1, -1);
    }

    public List<InfoMunicipios> findInfoMunicipiosEntities(int maxResults, int firstResult) {
        return findInfoMunicipiosEntities(false, maxResults, firstResult);
    }

    private List<InfoMunicipios> findInfoMunicipiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoMunicipios.class));
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

    public InfoMunicipios findInfoMunicipios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoMunicipios.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoMunicipiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoMunicipios> rt = cq.from(InfoMunicipios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
