/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import entidades_EJB.EnfuListael12h;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuListael12hJpaController implements Serializable {

    public EnfuListael12hJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuListael12h enfuListael12h) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuListael12h);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnfuListael12h(enfuListael12h.getId()) != null) {
                throw new PreexistingEntityException("EnfuListael12h " + enfuListael12h + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuListael12h enfuListael12h) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuListael12h = em.merge(enfuListael12h);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = enfuListael12h.getId();
                if (findEnfuListael12h(id) == null) {
                    throw new NonexistentEntityException("The enfuListael12h with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuListael12h enfuListael12h;
            try {
                enfuListael12h = em.getReference(EnfuListael12h.class, id);
                enfuListael12h.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuListael12h with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuListael12h);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuListael12h> findEnfuListael12hEntities() {
        return findEnfuListael12hEntities(true, -1, -1);
    }

    public List<EnfuListael12h> findEnfuListael12hEntities(int maxResults, int firstResult) {
        return findEnfuListael12hEntities(false, maxResults, firstResult);
    }

    private List<EnfuListael12h> findEnfuListael12hEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuListael12h.class));
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

    public EnfuListael12h findEnfuListael12h(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuListael12h.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuListael12hCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuListael12h> rt = cq.from(EnfuListael12h.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuListael12h> Load_vista12hEL(int control) {
        Query Q = null;
        EntityManager em = getEntityManager();
        try {
            Q = em.createQuery("SELECT i FROM EnfuListael12h i WHERE i.idControles=:control");
            Q.setParameter("control", control);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

}
