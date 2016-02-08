/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.CeHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CeCtcJpaController implements Serializable {

    public CeCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeCtc ceCtc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeHistoriac idHistoria = ceCtc.getIdHistoria();
            if (idHistoria != null) {
                idHistoria = em.getReference(idHistoria.getClass(), idHistoria.getId());
                ceCtc.setIdHistoria(idHistoria);
            }
            em.persist(ceCtc);
            if (idHistoria != null) {
                idHistoria.getCeCtcList().add(ceCtc);
                idHistoria = em.merge(idHistoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeCtc ceCtc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeCtc persistentCeCtc = em.find(CeCtc.class, ceCtc.getId());
            CeHistoriac idHistoriaOld = persistentCeCtc.getIdHistoria();
            CeHistoriac idHistoriaNew = ceCtc.getIdHistoria();
            if (idHistoriaNew != null) {
                idHistoriaNew = em.getReference(idHistoriaNew.getClass(), idHistoriaNew.getId());
                ceCtc.setIdHistoria(idHistoriaNew);
            }
            ceCtc = em.merge(ceCtc);
            if (idHistoriaOld != null && !idHistoriaOld.equals(idHistoriaNew)) {
                idHistoriaOld.getCeCtcList().remove(ceCtc);
                idHistoriaOld = em.merge(idHistoriaOld);
            }
            if (idHistoriaNew != null && !idHistoriaNew.equals(idHistoriaOld)) {
                idHistoriaNew.getCeCtcList().add(ceCtc);
                idHistoriaNew = em.merge(idHistoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceCtc.getId();
                if (findCeCtc(id) == null) {
                    throw new NonexistentEntityException("The ceCtc with id " + id + " no longer exists.");
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
            CeCtc ceCtc;
            try {
                ceCtc = em.getReference(CeCtc.class, id);
                ceCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceCtc with id " + id + " no longer exists.", enfe);
            }
            CeHistoriac idHistoria = ceCtc.getIdHistoria();
            if (idHistoria != null) {
                idHistoria.getCeCtcList().remove(ceCtc);
                idHistoria = em.merge(idHistoria);
            }
            em.remove(ceCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeCtc> findCeCtcEntities() {
        return findCeCtcEntities(true, -1, -1);
    }

    public List<CeCtc> findCeCtcEntities(int maxResults, int firstResult) {
        return findCeCtcEntities(false, maxResults, firstResult);
    }

    private List<CeCtc> findCeCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeCtc.class));
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

    public CeCtc findCeCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeCtc> rt = cq.from(CeCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<CeCtc>getCTCS(int his){
        EntityManager em=getEntityManager();
        Query q=em.createQuery("SELECT c FROM CeCtc c WHERE c.idHistoria.id=:h AND c.estado='1'");
        q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        q.setParameter("h", his);
        return q.getResultList();
    }
}
