/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades.HcuEvoMedidasg;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HcuEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuEvoMedidasgJpaController implements Serializable {

    public HcuEvoMedidasgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoMedidasg hcuEvoMedidasg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvolucion idHcuEvolucion = hcuEvoMedidasg.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion = em.getReference(idHcuEvolucion.getClass(), idHcuEvolucion.getId());
                hcuEvoMedidasg.setIdHcuEvolucion(idHcuEvolucion);
            }
            em.persist(hcuEvoMedidasg);
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoMedidasgs().add(hcuEvoMedidasg);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoMedidasg hcuEvoMedidasg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoMedidasg persistentHcuEvoMedidasg = em.find(HcuEvoMedidasg.class, hcuEvoMedidasg.getId());
            HcuEvolucion idHcuEvolucionOld = persistentHcuEvoMedidasg.getIdHcuEvolucion();
            HcuEvolucion idHcuEvolucionNew = hcuEvoMedidasg.getIdHcuEvolucion();
            if (idHcuEvolucionNew != null) {
                idHcuEvolucionNew = em.getReference(idHcuEvolucionNew.getClass(), idHcuEvolucionNew.getId());
                hcuEvoMedidasg.setIdHcuEvolucion(idHcuEvolucionNew);
            }
            hcuEvoMedidasg = em.merge(hcuEvoMedidasg);
            if (idHcuEvolucionOld != null && !idHcuEvolucionOld.equals(idHcuEvolucionNew)) {
                idHcuEvolucionOld.getHcuEvoMedidasgs().remove(hcuEvoMedidasg);
                idHcuEvolucionOld = em.merge(idHcuEvolucionOld);
            }
            if (idHcuEvolucionNew != null && !idHcuEvolucionNew.equals(idHcuEvolucionOld)) {
                idHcuEvolucionNew.getHcuEvoMedidasgs().add(hcuEvoMedidasg);
                idHcuEvolucionNew = em.merge(idHcuEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoMedidasg.getId();
                if (findHcuEvoMedidasg(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoMedidasg with id " + id + " no longer exists.");
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
            HcuEvoMedidasg hcuEvoMedidasg;
            try {
                hcuEvoMedidasg = em.getReference(HcuEvoMedidasg.class, id);
                hcuEvoMedidasg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoMedidasg with id " + id + " no longer exists.", enfe);
            }
            HcuEvolucion idHcuEvolucion = hcuEvoMedidasg.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoMedidasgs().remove(hcuEvoMedidasg);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.remove(hcuEvoMedidasg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoMedidasg> findHcuEvoMedidasgEntities() {
        return findHcuEvoMedidasgEntities(true, -1, -1);
    }

    public List<HcuEvoMedidasg> findHcuEvoMedidasgEntities(int maxResults, int firstResult) {
        return findHcuEvoMedidasgEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoMedidasg> findHcuEvoMedidasgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoMedidasg.class));
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

    public HcuEvoMedidasg findHcuEvoMedidasg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoMedidasg.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoMedidasgCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoMedidasg> rt = cq.from(HcuEvoMedidasg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado
    
   public List<HcuEvoMedidasg> ListFindHcuEvoMedidasG(HcuEvolucion evol){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HcuEvoMedidasg h WHERE h.idHcuEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evol)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
}
