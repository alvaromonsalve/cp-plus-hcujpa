/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.SumAtcPrincipioactivo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SumAtcSubgrupoquimico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class SumAtcPrincipioactivoJpaController implements Serializable {

    public SumAtcPrincipioactivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcPrincipioactivo sumAtcPrincipioactivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcSubgrupoquimico idSubgrupoquimico = sumAtcPrincipioactivo.getIdSubgrupoquimico();
            if (idSubgrupoquimico != null) {
                idSubgrupoquimico = em.getReference(idSubgrupoquimico.getClass(), idSubgrupoquimico.getId());
                sumAtcPrincipioactivo.setIdSubgrupoquimico(idSubgrupoquimico);
            }
            em.persist(sumAtcPrincipioactivo);
            if (idSubgrupoquimico != null) {
                idSubgrupoquimico.getSumAtcPrincipioactivoList().add(sumAtcPrincipioactivo);
                idSubgrupoquimico = em.merge(idSubgrupoquimico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcPrincipioactivo sumAtcPrincipioactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcPrincipioactivo persistentSumAtcPrincipioactivo = em.find(SumAtcPrincipioactivo.class, sumAtcPrincipioactivo.getId());
            SumAtcSubgrupoquimico idSubgrupoquimicoOld = persistentSumAtcPrincipioactivo.getIdSubgrupoquimico();
            SumAtcSubgrupoquimico idSubgrupoquimicoNew = sumAtcPrincipioactivo.getIdSubgrupoquimico();
            if (idSubgrupoquimicoNew != null) {
                idSubgrupoquimicoNew = em.getReference(idSubgrupoquimicoNew.getClass(), idSubgrupoquimicoNew.getId());
                sumAtcPrincipioactivo.setIdSubgrupoquimico(idSubgrupoquimicoNew);
            }
            sumAtcPrincipioactivo = em.merge(sumAtcPrincipioactivo);
            if (idSubgrupoquimicoOld != null && !idSubgrupoquimicoOld.equals(idSubgrupoquimicoNew)) {
                idSubgrupoquimicoOld.getSumAtcPrincipioactivoList().remove(sumAtcPrincipioactivo);
                idSubgrupoquimicoOld = em.merge(idSubgrupoquimicoOld);
            }
            if (idSubgrupoquimicoNew != null && !idSubgrupoquimicoNew.equals(idSubgrupoquimicoOld)) {
                idSubgrupoquimicoNew.getSumAtcPrincipioactivoList().add(sumAtcPrincipioactivo);
                idSubgrupoquimicoNew = em.merge(idSubgrupoquimicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcPrincipioactivo.getId();
                if (findSumAtcPrincipioactivo(id) == null) {
                    throw new NonexistentEntityException("The sumAtcPrincipioactivo with id " + id + " no longer exists.");
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
            SumAtcPrincipioactivo sumAtcPrincipioactivo;
            try {
                sumAtcPrincipioactivo = em.getReference(SumAtcPrincipioactivo.class, id);
                sumAtcPrincipioactivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcPrincipioactivo with id " + id + " no longer exists.", enfe);
            }
            SumAtcSubgrupoquimico idSubgrupoquimico = sumAtcPrincipioactivo.getIdSubgrupoquimico();
            if (idSubgrupoquimico != null) {
                idSubgrupoquimico.getSumAtcPrincipioactivoList().remove(sumAtcPrincipioactivo);
                idSubgrupoquimico = em.merge(idSubgrupoquimico);
            }
            em.remove(sumAtcPrincipioactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcPrincipioactivo> findSumAtcPrincipioactivoEntities() {
        return findSumAtcPrincipioactivoEntities(true, -1, -1);
    }

    public List<SumAtcPrincipioactivo> findSumAtcPrincipioactivoEntities(int maxResults, int firstResult) {
        return findSumAtcPrincipioactivoEntities(false, maxResults, firstResult);
    }

    private List<SumAtcPrincipioactivo> findSumAtcPrincipioactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcPrincipioactivo.class));
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

    public SumAtcPrincipioactivo findSumAtcPrincipioactivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcPrincipioactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcPrincipioactivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcPrincipioactivo> rt = cq.from(SumAtcPrincipioactivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
