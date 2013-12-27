/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades.HcuEvoInterconsulta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HcuEvolucion;
import entidades.StaticEspecialidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuEvoInterconsultaJpaController implements Serializable {

    public HcuEvoInterconsultaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoInterconsulta hcuEvoInterconsulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvolucion idHcuEvolucion = hcuEvoInterconsulta.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion = em.getReference(idHcuEvolucion.getClass(), idHcuEvolucion.getId());
                hcuEvoInterconsulta.setIdHcuEvolucion(idHcuEvolucion);
            }
            em.persist(hcuEvoInterconsulta);
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoInterconsultas().add(hcuEvoInterconsulta);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoInterconsulta hcuEvoInterconsulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoInterconsulta persistentHcuEvoInterconsulta = em.find(HcuEvoInterconsulta.class, hcuEvoInterconsulta.getId());
            HcuEvolucion idHcuEvolucionOld = persistentHcuEvoInterconsulta.getIdHcuEvolucion();
            HcuEvolucion idHcuEvolucionNew = hcuEvoInterconsulta.getIdHcuEvolucion();
            if (idHcuEvolucionNew != null) {
                idHcuEvolucionNew = em.getReference(idHcuEvolucionNew.getClass(), idHcuEvolucionNew.getId());
                hcuEvoInterconsulta.setIdHcuEvolucion(idHcuEvolucionNew);
            }
            hcuEvoInterconsulta = em.merge(hcuEvoInterconsulta);
            if (idHcuEvolucionOld != null && !idHcuEvolucionOld.equals(idHcuEvolucionNew)) {
                idHcuEvolucionOld.getHcuEvoInterconsultas().remove(hcuEvoInterconsulta);
                idHcuEvolucionOld = em.merge(idHcuEvolucionOld);
            }
            if (idHcuEvolucionNew != null && !idHcuEvolucionNew.equals(idHcuEvolucionOld)) {
                idHcuEvolucionNew.getHcuEvoInterconsultas().add(hcuEvoInterconsulta);
                idHcuEvolucionNew = em.merge(idHcuEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoInterconsulta.getId();
                if (findHcuEvoInterconsulta(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoInterconsulta with id " + id + " no longer exists.");
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
            HcuEvoInterconsulta hcuEvoInterconsulta;
            try {
                hcuEvoInterconsulta = em.getReference(HcuEvoInterconsulta.class, id);
                hcuEvoInterconsulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoInterconsulta with id " + id + " no longer exists.", enfe);
            }
            HcuEvolucion idHcuEvolucion = hcuEvoInterconsulta.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoInterconsultas().remove(hcuEvoInterconsulta);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.remove(hcuEvoInterconsulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoInterconsulta> findHcuEvoInterconsultaEntities() {
        return findHcuEvoInterconsultaEntities(true, -1, -1);
    }

    public List<HcuEvoInterconsulta> findHcuEvoInterconsultaEntities(int maxResults, int firstResult) {
        return findHcuEvoInterconsultaEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoInterconsulta> findHcuEvoInterconsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoInterconsulta.class));
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

    public HcuEvoInterconsulta findHcuEvoInterconsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoInterconsulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoInterconsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoInterconsulta> rt = cq.from(HcuEvoInterconsulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Codigo no Auto-generado
    
    public HcuEvoInterconsulta findEvoInterconsulta_EVO(HcuEvolucion evo,StaticEspecialidades se){
        EntityManager em = getEntityManager();
        try {
            return (HcuEvoInterconsulta) em.createQuery("SELECT h FROM HcuEvoInterconsulta h WHERE h.idHcuEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado ='1'")
                    .setParameter("evo", evo)
                    .setParameter("se", se)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        }catch(Exception ex){ return null;
        } finally {
            em.close();
        }
    }
    
        public List<HcuEvoInterconsulta> listInterconsultaOtrasEvo(HcuEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HcuEvoInterconsulta h WHERE h.idHcuEvolucion = :evo "
                    + "AND h.idStaticEspecialidades.id <> 28 AND h.idStaticEspecialidades.id <> 22 "
                    + "AND h.idStaticEspecialidades.id <> 14 AND h.idStaticEspecialidades.id <> 10 "
                    + "AND h.idStaticEspecialidades.id <> 39 "
                    + "AND h.idStaticEspecialidades.id <> 3 AND h.estado = '1'")
                    .setParameter("evo", evo)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }
        
    public Long CountInterconsultas(HcuEvolucion evo, StaticEspecialidades se){
        EntityManager em = getEntityManager();
        em.clear();
        try {
            return (Long) em.createQuery("SELECT COUNT(h) FROM HcuEvoInterconsulta h WHERE h.idHcuEvolucion = :evo AND h.idStaticEspecialidades = :se AND h.estado='1'")
            .setParameter("evo", evo)
            .setParameter("se", se)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getSingleResult();
        } finally {
            em.close();
        }
   }
    
    
    
}
