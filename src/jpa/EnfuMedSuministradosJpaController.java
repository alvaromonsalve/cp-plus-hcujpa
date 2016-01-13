/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.EnfuFactsNotas;
import entidades_EJB.EnfuMedSuministrados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuMedSuministradosJpaController implements Serializable {

    public EnfuMedSuministradosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuMedSuministrados enfuMedSuministrados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsNotas idFactsNotas = enfuMedSuministrados.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                enfuMedSuministrados.setIdFactsNotas(idFactsNotas);
            }
            em.persist(enfuMedSuministrados);
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuMedSuministradosCollection().add(enfuMedSuministrados);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuMedSuministrados enfuMedSuministrados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuMedSuministrados persistentEnfuMedSuministrados = em.find(EnfuMedSuministrados.class, enfuMedSuministrados.getId());
            EnfuFactsNotas idFactsNotasOld = persistentEnfuMedSuministrados.getIdFactsNotas();
            EnfuFactsNotas idFactsNotasNew = enfuMedSuministrados.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                enfuMedSuministrados.setIdFactsNotas(idFactsNotasNew);
            }
            enfuMedSuministrados = em.merge(enfuMedSuministrados);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getEnfuMedSuministradosCollection().remove(enfuMedSuministrados);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getEnfuMedSuministradosCollection().add(enfuMedSuministrados);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuMedSuministrados.getId();
                if (findEnfuMedSuministrados(id) == null) {
                    throw new NonexistentEntityException("The enfuMedSuministrados with id " + id + " no longer exists.");
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
            EnfuMedSuministrados enfuMedSuministrados;
            try {
                enfuMedSuministrados = em.getReference(EnfuMedSuministrados.class, id);
                enfuMedSuministrados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuMedSuministrados with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsNotas idFactsNotas = enfuMedSuministrados.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuMedSuministradosCollection().remove(enfuMedSuministrados);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(enfuMedSuministrados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuMedSuministrados> findEnfuMedSuministradosEntities() {
        return findEnfuMedSuministradosEntities(true, -1, -1);
    }

    public List<EnfuMedSuministrados> findEnfuMedSuministradosEntities(int maxResults, int firstResult) {
        return findEnfuMedSuministradosEntities(false, maxResults, firstResult);
    }

    private List<EnfuMedSuministrados> findEnfuMedSuministradosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuMedSuministrados.class));
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

    public EnfuMedSuministrados findEnfuMedSuministrados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuMedSuministrados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuMedSuministradosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuMedSuministrados> rt = cq.from(EnfuMedSuministrados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public EnfuMedSuministrados get_Medicamentos(EnfuFactsNotas n) {
        EnfuMedSuministrados med;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT m FROM EnfuMedSuministrados m WHERE m.idFactsNotas=:no AND m.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            med = (EnfuMedSuministrados) results.get(0);
        } else {
            med = null;
        }
        return med;
    }

}
