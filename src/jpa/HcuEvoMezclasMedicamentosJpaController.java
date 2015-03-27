/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.HcuEvoMezclasMedicamentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HcuEvolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuEvoMezclasMedicamentosJpaController implements Serializable {

    public HcuEvoMezclasMedicamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvolucion idHcuEvolucion = hcuEvoMezclasMedicamentos.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion = em.getReference(idHcuEvolucion.getClass(), idHcuEvolucion.getId());
                hcuEvoMezclasMedicamentos.setIdHcuEvolucion(idHcuEvolucion);
            }
            em.persist(hcuEvoMezclasMedicamentos);
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoMezclasMedicamentoses().add(hcuEvoMezclasMedicamentos);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoMezclasMedicamentos persistentHcuEvoMezclasMedicamentos = em.find(HcuEvoMezclasMedicamentos.class, hcuEvoMezclasMedicamentos.getId());
            HcuEvolucion idHcuEvolucionOld = persistentHcuEvoMezclasMedicamentos.getIdHcuEvolucion();
            HcuEvolucion idHcuEvolucionNew = hcuEvoMezclasMedicamentos.getIdHcuEvolucion();
            if (idHcuEvolucionNew != null) {
                idHcuEvolucionNew = em.getReference(idHcuEvolucionNew.getClass(), idHcuEvolucionNew.getId());
                hcuEvoMezclasMedicamentos.setIdHcuEvolucion(idHcuEvolucionNew);
            }
            hcuEvoMezclasMedicamentos = em.merge(hcuEvoMezclasMedicamentos);
            if (idHcuEvolucionOld != null && !idHcuEvolucionOld.equals(idHcuEvolucionNew)) {
                idHcuEvolucionOld.getHcuEvoMezclasMedicamentoses().remove(hcuEvoMezclasMedicamentos);
                idHcuEvolucionOld = em.merge(idHcuEvolucionOld);
            }
            if (idHcuEvolucionNew != null && !idHcuEvolucionNew.equals(idHcuEvolucionOld)) {
                idHcuEvolucionNew.getHcuEvoMezclasMedicamentoses().add(hcuEvoMezclasMedicamentos);
                idHcuEvolucionNew = em.merge(idHcuEvolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoMezclasMedicamentos.getId();
                if (findHcuEvoMezclasMedicamentos(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoMezclasMedicamentos with id " + id + " no longer exists.");
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
            HcuEvoMezclasMedicamentos hcuEvoMezclasMedicamentos;
            try {
                hcuEvoMezclasMedicamentos = em.getReference(HcuEvoMezclasMedicamentos.class, id);
                hcuEvoMezclasMedicamentos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoMezclasMedicamentos with id " + id + " no longer exists.", enfe);
            }
            HcuEvolucion idHcuEvolucion = hcuEvoMezclasMedicamentos.getIdHcuEvolucion();
            if (idHcuEvolucion != null) {
                idHcuEvolucion.getHcuEvoMezclasMedicamentoses().remove(hcuEvoMezclasMedicamentos);
                idHcuEvolucion = em.merge(idHcuEvolucion);
            }
            em.remove(hcuEvoMezclasMedicamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoMezclasMedicamentos> findHcuEvoMezclasMedicamentosEntities() {
        return findHcuEvoMezclasMedicamentosEntities(true, -1, -1);
    }

    public List<HcuEvoMezclasMedicamentos> findHcuEvoMezclasMedicamentosEntities(int maxResults, int firstResult) {
        return findHcuEvoMezclasMedicamentosEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoMezclasMedicamentos> findHcuEvoMezclasMedicamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoMezclasMedicamentos.class));
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

    public HcuEvoMezclasMedicamentos findHcuEvoMezclasMedicamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoMezclasMedicamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoMezclasMedicamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoMezclasMedicamentos> rt = cq.from(HcuEvoMezclasMedicamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
          //Codigo no Auto-generado    
   public List<HcuEvoMezclasMedicamentos> ListFindHcuMezclas(HcuEvolucion evo){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HcuEvoMezclasMedicamentos h WHERE h.idHcuEvolucion = :evo AND h.estado='1'")
            .setParameter("evo", evo)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
}
