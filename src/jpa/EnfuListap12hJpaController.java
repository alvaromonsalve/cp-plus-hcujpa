/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import entidades_EJB.EnfuApLiquidos;
import entidades_EJB.EnfuControlLiquidos;
import entidades_EJB.EnfuListap12h;
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
public class EnfuListap12hJpaController implements Serializable {

    public EnfuListap12hJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuListap12h enfuListap12h) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfuListap12h);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnfuListap12h(enfuListap12h.getId()) != null) {
                throw new PreexistingEntityException("EnfuListap12h " + enfuListap12h + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuListap12h enfuListap12h) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfuListap12h = em.merge(enfuListap12h);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = enfuListap12h.getId();
                if (findEnfuListap12h(id) == null) {
                    throw new NonexistentEntityException("The enfuListap12h with id " + id + " no longer exists.");
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
            EnfuListap12h enfuListap12h;
            try {
                enfuListap12h = em.getReference(EnfuListap12h.class, id);
                enfuListap12h.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuListap12h with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfuListap12h);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuListap12h> findEnfuListap12hEntities() {
        return findEnfuListap12hEntities(true, -1, -1);
    }

    public List<EnfuListap12h> findEnfuListap12hEntities(int maxResults, int firstResult) {
        return findEnfuListap12hEntities(false, maxResults, firstResult);
    }

    private List<EnfuListap12h> findEnfuListap12hEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuListap12h.class));
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

    public EnfuListap12h findEnfuListap12h(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuListap12h.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuListap12hCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuListap12h> rt = cq.from(EnfuListap12h.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuListap12h> Load_vista12h(int cont) {
        Query Q = null;
        EntityManager em = getEntityManager();
        try {
            Q = em.createQuery("SELECT i FROM EnfuListap12h i WHERE i.idControles=:control");
            Q.setParameter("control", cont);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

}
