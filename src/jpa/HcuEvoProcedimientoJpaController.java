/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades.HcuEvoProcedimiento;
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
public class HcuEvoProcedimientoJpaController implements Serializable {

    public HcuEvoProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoProcedimiento hcuEvoProcedimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvolucion idHcuEvolucion = hcuEvoProcedimiento.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion = em.getReference(idHcuEvolucion.getClass(), idHcuEvolucion.getId());
                hcuEvoProcedimiento.setIdHcuEvolucion(idHcuEvolucion);
            }
            em.persist(hcuEvoProcedimiento);
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoProcedimientos().add(hcuEvoProcedimiento);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoProcedimiento hcuEvoProcedimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoProcedimiento persistentHcuEvoProcedimiento = em.find(HcuEvoProcedimiento.class, hcuEvoProcedimiento.getId());
            HcuEvolucion idHcuEvolucionOld = persistentHcuEvoProcedimiento.getIdHcuEvolucion();
            HcuEvolucion idHcuEvolucionNew = hcuEvoProcedimiento.getIdHcuEvolucion();
            if (idHcuEvolucionNew != null) {
                idHcuEvolucionNew = em.getReference(idHcuEvolucionNew.getClass(), idHcuEvolucionNew.getId());
                hcuEvoProcedimiento.setIdHcuEvolucion(idHcuEvolucionNew);
            }
            hcuEvoProcedimiento = em.merge(hcuEvoProcedimiento);
            if (idHcuEvolucionOld != null && !idHcuEvolucionOld.equals(idHcuEvolucionNew)) {
                idHcuEvolucionOld.getHcuEvoProcedimientos().remove(hcuEvoProcedimiento);
                idHcuEvolucionOld = em.merge(idHcuEvolucionOld);
            }
            if (idHcuEvolucionNew != null && !idHcuEvolucionNew.equals(idHcuEvolucionOld)) {
                idHcuEvolucionNew.getHcuEvoProcedimientos().add(hcuEvoProcedimiento);
                idHcuEvolucionNew = em.merge(idHcuEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoProcedimiento.getId();
                if (findHcuEvoProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoProcedimiento with id " + id + " no longer exists.");
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
            HcuEvoProcedimiento hcuEvoProcedimiento;
            try {
                hcuEvoProcedimiento = em.getReference(HcuEvoProcedimiento.class, id);
                hcuEvoProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoProcedimiento with id " + id + " no longer exists.", enfe);
            }
            HcuEvolucion idHcuEvolucion = hcuEvoProcedimiento.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoProcedimientos().remove(hcuEvoProcedimiento);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.remove(hcuEvoProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoProcedimiento> findHcuEvoProcedimientoEntities() {
        return findHcuEvoProcedimientoEntities(true, -1, -1);
    }

    public List<HcuEvoProcedimiento> findHcuEvoProcedimientoEntities(int maxResults, int firstResult) {
        return findHcuEvoProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoProcedimiento> findHcuEvoProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoProcedimiento.class));
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

    public HcuEvoProcedimiento findHcuEvoProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoProcedimiento> rt = cq.from(HcuEvoProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   //Codigo no Auto-generado
   public List<HcuEvoProcedimiento> ListFindInfoProcedimientoEvo(HcuEvolucion evo){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM HcuEvoProcedimiento h WHERE h.idHcuEvolucion = :evo AND h.estado = '1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
   
      public List<HcuEvoProcedimiento> ListFindInfoProcedimientoEvo(HcuEvolucion evo, Integer ConfigCups){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return em.createQuery("SELECT h FROM HcuEvoProcedimiento h WHERE h.idHcuEvolucion = :evo AND h.estado = '1' AND h.idConfigCups.idEstructuraCups.id = :cc")
            .setParameter("evo", evo)
            .setParameter("cc", ConfigCups)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
}
