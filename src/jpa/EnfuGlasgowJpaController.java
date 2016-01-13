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
import entidades_EJB.EnfuGlasgow;
import entidades_EJB.InfoHistoriac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuGlasgowJpaController implements Serializable {

    public EnfuGlasgowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuGlasgow enfuGlasgow) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsNotas idFactsNotas = enfuGlasgow.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas = em.getReference(idFactsNotas.getClass(), idFactsNotas.getId());
                enfuGlasgow.setIdFactsNotas(idFactsNotas);
            }
            em.persist(enfuGlasgow);
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuGlasgowCollection().add(enfuGlasgow);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuGlasgow enfuGlasgow) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuGlasgow persistentEnfuGlasgow = em.find(EnfuGlasgow.class, enfuGlasgow.getId());
            EnfuFactsNotas idFactsNotasOld = persistentEnfuGlasgow.getIdFactsNotas();
            EnfuFactsNotas idFactsNotasNew = enfuGlasgow.getIdFactsNotas();
            if (idFactsNotasNew != null) {
                idFactsNotasNew = em.getReference(idFactsNotasNew.getClass(), idFactsNotasNew.getId());
                enfuGlasgow.setIdFactsNotas(idFactsNotasNew);
            }
            enfuGlasgow = em.merge(enfuGlasgow);
            if (idFactsNotasOld != null && !idFactsNotasOld.equals(idFactsNotasNew)) {
                idFactsNotasOld.getEnfuGlasgowCollection().remove(enfuGlasgow);
                idFactsNotasOld = em.merge(idFactsNotasOld);
            }
            if (idFactsNotasNew != null && !idFactsNotasNew.equals(idFactsNotasOld)) {
                idFactsNotasNew.getEnfuGlasgowCollection().add(enfuGlasgow);
                idFactsNotasNew = em.merge(idFactsNotasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuGlasgow.getId();
                if (findEnfuGlasgow(id) == null) {
                    throw new NonexistentEntityException("The enfuGlasgow with id " + id + " no longer exists.");
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
            EnfuGlasgow enfuGlasgow;
            try {
                enfuGlasgow = em.getReference(EnfuGlasgow.class, id);
                enfuGlasgow.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuGlasgow with id " + id + " no longer exists.", enfe);
            }
            EnfuFactsNotas idFactsNotas = enfuGlasgow.getIdFactsNotas();
            if (idFactsNotas != null) {
                idFactsNotas.getEnfuGlasgowCollection().remove(enfuGlasgow);
                idFactsNotas = em.merge(idFactsNotas);
            }
            em.remove(enfuGlasgow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuGlasgow> findEnfuGlasgowEntities() {
        return findEnfuGlasgowEntities(true, -1, -1);
    }

    public List<EnfuGlasgow> findEnfuGlasgowEntities(int maxResults, int firstResult) {
        return findEnfuGlasgowEntities(false, maxResults, firstResult);
    }

    private List<EnfuGlasgow> findEnfuGlasgowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuGlasgow.class));
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

    public EnfuGlasgow findEnfuGlasgow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuGlasgow.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuGlasgowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuGlasgow> rt = cq.from(EnfuGlasgow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List findEnfuGlasgow_(InfoHistoriac in) {
        Query Q = null;
        try {
            EntityManager em = getEntityManager();
            Q = em.createQuery("SELECT i FROM EnfuGlasgow i WHERE (i.idHistoriac=:hc) AND (i.estado='1')");
            Q.setParameter("hc", in);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public EnfuGlasgow get_glassgow(EnfuFactsNotas n) {
        EnfuGlasgow glass;
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT g FROM EnfuGlasgow g WHERE g.idFactsNotas=:no AND g.estado='1'");
        Q.setParameter("no", n);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List results = Q.getResultList();
        if (!results.isEmpty()) {
            glass = (EnfuGlasgow) results.get(0);
        } else {
            glass = null;
        }
        em.close();
        return glass;
    }
}
