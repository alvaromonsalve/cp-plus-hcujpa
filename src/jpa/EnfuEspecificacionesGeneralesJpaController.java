/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuEspecificacionesGenerales;
import entidades_EJB.EnfuFactsNotas;
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
public class EnfuEspecificacionesGeneralesJpaController implements Serializable {

    public EnfuEspecificacionesGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuEspecificacionesGenerales enfuEspecificacionesGenerales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsNotas idFactsNotas = enfuEspecificacionesGenerales.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                enfuEspecificacionesGenerales.setIdFactsNotas(idFactsNotas);
            }
            em.persist(enfuEspecificacionesGenerales);
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuEspecificacionesGeneralesCollection().add(enfuEspecificacionesGenerales);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuEspecificacionesGenerales enfuEspecificacionesGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuEspecificacionesGenerales persistentEnfuEspecificacionesGenerales = em.find(EnfuEspecificacionesGenerales.class, enfuEspecificacionesGenerales.getId());
            EnfuFactsNotas idFactsNotasOld = persistentEnfuEspecificacionesGenerales.getIdFactsNotas();
            EnfuFactsNotas idFactsNotasNew = enfuEspecificacionesGenerales.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                enfuEspecificacionesGenerales.setIdFactsNotas(idFactsNotasNew);
            }
            enfuEspecificacionesGenerales = em.merge(enfuEspecificacionesGenerales);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getEnfuEspecificacionesGeneralesCollection().remove(enfuEspecificacionesGenerales);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getEnfuEspecificacionesGeneralesCollection().add(enfuEspecificacionesGenerales);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuEspecificacionesGenerales.getId();
                if (findEnfuEspecificacionesGenerales(id) == null) {
                    throw new NonexistentEntityException("The enfuEspecificacionesGenerales with id " + id + " no longer exists.");
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
            EnfuEspecificacionesGenerales enfuEspecificacionesGenerales;
            try {
                enfuEspecificacionesGenerales = em.getReference(EnfuEspecificacionesGenerales.class, id);
                enfuEspecificacionesGenerales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuEspecificacionesGenerales with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsNotas idFactsNotas = enfuEspecificacionesGenerales.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuEspecificacionesGeneralesCollection().remove(enfuEspecificacionesGenerales);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(enfuEspecificacionesGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuEspecificacionesGenerales> findEnfuEspecificacionesGeneralesEntities() {
        return findEnfuEspecificacionesGeneralesEntities(true, -1, -1);
    }

    public List<EnfuEspecificacionesGenerales> findEnfuEspecificacionesGeneralesEntities(int maxResults, int firstResult) {
        return findEnfuEspecificacionesGeneralesEntities(false, maxResults, firstResult);
    }

    private List<EnfuEspecificacionesGenerales> findEnfuEspecificacionesGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuEspecificacionesGenerales.class));
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

    public EnfuEspecificacionesGenerales findEnfuEspecificacionesGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuEspecificacionesGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuEspecificacionesGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuEspecificacionesGenerales> rt = cq.from(EnfuEspecificacionesGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public EnfuEspecificacionesGenerales getEspecificaciones(EnfuFactsNotas n) {
        EnfuEspecificacionesGenerales espgen;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT es FROM EnfuEspecificacionesGenerales es WHERE es.idFactsNotas=:no AND es.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            espgen = (EnfuEspecificacionesGenerales) results.get(0);
        } else {
            espgen = null;
        }
        return espgen;
    }
}
