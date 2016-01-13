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
import entidades_EJB.EnfuSignosVitalesDet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuSignosVitalesDetJpaController implements Serializable {

    public EnfuSignosVitalesDetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuSignosVitalesDet enfuSignosVitalesDet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsNotas idFactsNotas = enfuSignosVitalesDet.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                enfuSignosVitalesDet.setIdFactsNotas(idFactsNotas);
            }
            em.persist(enfuSignosVitalesDet);
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuSignosVitalesDetCollection().add(enfuSignosVitalesDet);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuSignosVitalesDet enfuSignosVitalesDet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuSignosVitalesDet persistentEnfuSignosVitalesDet = em.find(EnfuSignosVitalesDet.class, enfuSignosVitalesDet.getId());
            EnfuFactsNotas idFactsNotasOld = persistentEnfuSignosVitalesDet.getIdFactsNotas();
            EnfuFactsNotas idFactsNotasNew = enfuSignosVitalesDet.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                enfuSignosVitalesDet.setIdFactsNotas(idFactsNotasNew);
            }
            enfuSignosVitalesDet = em.merge(enfuSignosVitalesDet);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getEnfuSignosVitalesDetCollection().remove(enfuSignosVitalesDet);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getEnfuSignosVitalesDetCollection().add(enfuSignosVitalesDet);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuSignosVitalesDet.getId();
                if (findEnfuSignosVitalesDet(id) == null) {
                    throw new NonexistentEntityException("The enfuSignosVitalesDet with id " + id + " no longer exists.");
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
            EnfuSignosVitalesDet enfuSignosVitalesDet;
            try {
                enfuSignosVitalesDet = em.getReference(EnfuSignosVitalesDet.class, id);
                enfuSignosVitalesDet.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuSignosVitalesDet with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsNotas idFactsNotas = enfuSignosVitalesDet.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuSignosVitalesDetCollection().remove(enfuSignosVitalesDet);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(enfuSignosVitalesDet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuSignosVitalesDet> findEnfuSignosVitalesDetEntities() {
        return findEnfuSignosVitalesDetEntities(true, -1, -1);
    }

    public List<EnfuSignosVitalesDet> findEnfuSignosVitalesDetEntities(int maxResults, int firstResult) {
        return findEnfuSignosVitalesDetEntities(false, maxResults, firstResult);
    }

    private List<EnfuSignosVitalesDet> findEnfuSignosVitalesDetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuSignosVitalesDet.class));
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

    public EnfuSignosVitalesDet findEnfuSignosVitalesDet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuSignosVitalesDet.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuSignosVitalesDetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuSignosVitalesDet> rt = cq.from(EnfuSignosVitalesDet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public EnfuSignosVitalesDet get_Signos(EnfuFactsNotas n) {
        EnfuSignosVitalesDet signos;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT s FROM EnfuSignosVitalesDet s WHERE s.idFactsNotas=:no AND s.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            signos = (EnfuSignosVitalesDet) results.get(0);
        } else {
            signos = null;
        }
        return signos;
    }

}
