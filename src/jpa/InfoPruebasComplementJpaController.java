/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoHistoriac;
import entidades.InfoPruebasComplement;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class InfoPruebasComplementJpaController implements Serializable {

    public InfoPruebasComplementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoPruebasComplement infoPruebasComplement) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoHistoriac idInfohistoriac = infoPruebasComplement.getIdInfohistoriac();
            if (idInfohistoriac != null) {
                idInfohistoriac = em.getReference(idInfohistoriac.getClass(), idInfohistoriac.getId());
                infoPruebasComplement.setIdInfohistoriac(idInfohistoriac);
            }
            em.persist(infoPruebasComplement);
            if (idInfohistoriac != null) {
                idInfohistoriac.getInfoPruebasComplements().add(infoPruebasComplement);
                idInfohistoriac = em.merge(idInfohistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoPruebasComplement infoPruebasComplement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoPruebasComplement persistentInfoPruebasComplement = em.find(InfoPruebasComplement.class, infoPruebasComplement.getId());
            InfoHistoriac idInfohistoriacOld = persistentInfoPruebasComplement.getIdInfohistoriac();
            InfoHistoriac idInfohistoriacNew = infoPruebasComplement.getIdInfohistoriac();
            if (idInfohistoriacNew != null) {
                idInfohistoriacNew = em.getReference(idInfohistoriacNew.getClass(), idInfohistoriacNew.getId());
                infoPruebasComplement.setIdInfohistoriac(idInfohistoriacNew);
            }
            infoPruebasComplement = em.merge(infoPruebasComplement);
            if (idInfohistoriacOld != null && !idInfohistoriacOld.equals(idInfohistoriacNew)) {
                idInfohistoriacOld.getInfoPruebasComplements().remove(infoPruebasComplement);
                idInfohistoriacOld = em.merge(idInfohistoriacOld);
            }
            if (idInfohistoriacNew != null && !idInfohistoriacNew.equals(idInfohistoriacOld)) {
                idInfohistoriacNew.getInfoPruebasComplements().add(infoPruebasComplement);
                idInfohistoriacNew = em.merge(idInfohistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoPruebasComplement.getId();
                if (findInfoPruebasComplement(id) == null) {
                    throw new NonexistentEntityException("The infoPruebasComplement with id " + id + " no longer exists.");
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
            InfoPruebasComplement infoPruebasComplement;
            try {
                infoPruebasComplement = em.getReference(InfoPruebasComplement.class, id);
                infoPruebasComplement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoPruebasComplement with id " + id + " no longer exists.", enfe);
            }
            InfoHistoriac idInfohistoriac = infoPruebasComplement.getIdInfohistoriac();
            if (idInfohistoriac != null) {
                idInfohistoriac.getInfoPruebasComplements().remove(infoPruebasComplement);
                idInfohistoriac = em.merge(idInfohistoriac);
            }
            em.remove(infoPruebasComplement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoPruebasComplement> findInfoPruebasComplementEntities() {
        return findInfoPruebasComplementEntities(true, -1, -1);
    }

    public List<InfoPruebasComplement> findInfoPruebasComplementEntities(int maxResults, int firstResult) {
        return findInfoPruebasComplementEntities(false, maxResults, firstResult);
    }

    private List<InfoPruebasComplement> findInfoPruebasComplementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoPruebasComplement.class));
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

    public InfoPruebasComplement findInfoPruebasComplement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoPruebasComplement.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoPruebasComplementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoPruebasComplement> rt = cq.from(InfoPruebasComplement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //codigo no Auto-generado
    
        
    public List<InfoPruebasComplement> getListInfoPruebasComplement(InfoHistoriac infoHistoriac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT i FROM InfoPruebasComplement i WHERE i.idInfohistoriac = :idInfohistoriac");
            q.setParameter("idInfohistoriac", infoHistoriac);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
