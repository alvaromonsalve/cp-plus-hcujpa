/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuFactsNotas;
import entidades_EJB.EnfuSignosVitalesMat;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuSignosVitalesMatJpaController implements Serializable {

    public EnfuSignosVitalesMatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuSignosVitalesMat enfuSignosVitalesMat) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsNotas idFactsNotas = enfuSignosVitalesMat.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                enfuSignosVitalesMat.setIdFactsNotas(idFactsNotas);
            }
            em.persist(enfuSignosVitalesMat);
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuSignosVitalesMatCollection().add(enfuSignosVitalesMat);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuSignosVitalesMat enfuSignosVitalesMat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuSignosVitalesMat persistentEnfuSignosVitalesMat = em.find(EnfuSignosVitalesMat.class, enfuSignosVitalesMat.getId());
            EnfuFactsNotas idFactsNotasOld = persistentEnfuSignosVitalesMat.getIdFactsNotas();
            EnfuFactsNotas idFactsNotasNew = enfuSignosVitalesMat.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                enfuSignosVitalesMat.setIdFactsNotas(idFactsNotasNew);
            }
            enfuSignosVitalesMat = em.merge(enfuSignosVitalesMat);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getEnfuSignosVitalesMatCollection().remove(enfuSignosVitalesMat);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getEnfuSignosVitalesMatCollection().add(enfuSignosVitalesMat);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuSignosVitalesMat.getId();
                if (findEnfuSignosVitalesMat(id) == null) {
                    throw new NonexistentEntityException("The enfuSignosVitalesMat with id " + id + " no longer exists.");
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
            EnfuSignosVitalesMat enfuSignosVitalesMat;
            try {
                enfuSignosVitalesMat = em.getReference(EnfuSignosVitalesMat.class, id);
                enfuSignosVitalesMat.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuSignosVitalesMat with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsNotas idFactsNotas = enfuSignosVitalesMat.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuSignosVitalesMatCollection().remove(enfuSignosVitalesMat);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(enfuSignosVitalesMat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuSignosVitalesMat> findEnfuSignosVitalesMatEntities() {
        return findEnfuSignosVitalesMatEntities(true, -1, -1);
    }

    public List<EnfuSignosVitalesMat> findEnfuSignosVitalesMatEntities(int maxResults, int firstResult) {
        return findEnfuSignosVitalesMatEntities(false, maxResults, firstResult);
    }

    private List<EnfuSignosVitalesMat> findEnfuSignosVitalesMatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuSignosVitalesMat.class));
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

    public EnfuSignosVitalesMat findEnfuSignosVitalesMat(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuSignosVitalesMat.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuSignosVitalesMatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuSignosVitalesMat> rt = cq.from(EnfuSignosVitalesMat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
