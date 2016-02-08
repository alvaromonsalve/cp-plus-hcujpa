/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeAnexo3;
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
public class CeAnexo3JpaController implements Serializable {

    public CeAnexo3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeAnexo3 ceAnexo3) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeHistoriac idHistoria = ceAnexo3.getIdHistoria();
            if (idHistoria != null) {
                idHistoria = em.getReference(idHistoria.getClass(), idHistoria.getId());
                ceAnexo3.setIdHistoria(idHistoria);
            }
            em.persist(ceAnexo3);
            if (idHistoria != null) {
                idHistoria.getCeAnexo3List().add(ceAnexo3);
                idHistoria = em.merge(idHistoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeAnexo3 ceAnexo3) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeAnexo3 persistentCeAnexo3 = em.find(CeAnexo3.class, ceAnexo3.getId());
            CeHistoriac idHistoriaOld = persistentCeAnexo3.getIdHistoria();
            CeHistoriac idHistoriaNew = ceAnexo3.getIdHistoria();
            if (idHistoriaNew != null) {
                idHistoriaNew = em.getReference(idHistoriaNew.getClass(), idHistoriaNew.getId());
                ceAnexo3.setIdHistoria(idHistoriaNew);
            }
            ceAnexo3 = em.merge(ceAnexo3);
            if (idHistoriaOld != null && !idHistoriaOld.equals(idHistoriaNew)) {
                idHistoriaOld.getCeAnexo3List().remove(ceAnexo3);
                idHistoriaOld = em.merge(idHistoriaOld);
            }
            if (idHistoriaNew != null && !idHistoriaNew.equals(idHistoriaOld)) {
                idHistoriaNew.getCeAnexo3List().add(ceAnexo3);
                idHistoriaNew = em.merge(idHistoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceAnexo3.getId();
                if (findCeAnexo3(id) == null) {
                    throw new NonexistentEntityException("The ceAnexo3 with id " + id + " no longer exists.");
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
            CeAnexo3 ceAnexo3;
            try {
                ceAnexo3 = em.getReference(CeAnexo3.class, id);
                ceAnexo3.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceAnexo3 with id " + id + " no longer exists.", enfe);
            }
            CeHistoriac idHistoria = ceAnexo3.getIdHistoria();
            if (idHistoria != null) {
                idHistoria.getCeAnexo3List().remove(ceAnexo3);
                idHistoria = em.merge(idHistoria);
            }
            em.remove(ceAnexo3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeAnexo3> findCeAnexo3Entities() {
        return findCeAnexo3Entities(true, -1, -1);
    }

    public List<CeAnexo3> findCeAnexo3Entities(int maxResults, int firstResult) {
        return findCeAnexo3Entities(false, maxResults, firstResult);
    }

    private List<CeAnexo3> findCeAnexo3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeAnexo3.class));
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

    public CeAnexo3 findCeAnexo3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeAnexo3.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeAnexo3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeAnexo3> rt = cq.from(CeAnexo3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CeAnexo3> getAnexos_3(int hi) {
        Query q = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT a FROM CeAnexo3 a WHERE a.idHistoria.id=:h AND a.estado='1'");
        q.setParameter("h", hi);
        return q.getResultList();
    }
}
