/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import entidades_EJB.EnfuFactsLiquidos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.EnfuLiqAdministrados;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.EnfuLiqEliminados;
import entidades_EJB.InfoHistoriac;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class EnfuFactsLiquidosJpaController implements Serializable {

    public EnfuFactsLiquidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuFactsLiquidos enfuFactsLiquidos) {
        if (enfuFactsLiquidos.getEnfuLiqAdministradosList() == null) {
            enfuFactsLiquidos.setEnfuLiqAdministradosList(new ArrayList<EnfuLiqAdministrados>());
        }
        if (enfuFactsLiquidos.getEnfuLiqEliminadosList() == null) {
            enfuFactsLiquidos.setEnfuLiqEliminadosList(new ArrayList<EnfuLiqEliminados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EnfuLiqAdministrados> attachedEnfuLiqAdministradosList = new ArrayList<EnfuLiqAdministrados>();
            for (EnfuLiqAdministrados enfuLiqAdministradosListEnfuLiqAdministradosToAttach : enfuFactsLiquidos.getEnfuLiqAdministradosList()) {
                enfuLiqAdministradosListEnfuLiqAdministradosToAttach = em.getReference(enfuLiqAdministradosListEnfuLiqAdministradosToAttach.getClass(), enfuLiqAdministradosListEnfuLiqAdministradosToAttach.getId());
                attachedEnfuLiqAdministradosList.add(enfuLiqAdministradosListEnfuLiqAdministradosToAttach);
            }
            enfuFactsLiquidos.setEnfuLiqAdministradosList(attachedEnfuLiqAdministradosList);
            List<EnfuLiqEliminados> attachedEnfuLiqEliminadosList = new ArrayList<EnfuLiqEliminados>();
            for (EnfuLiqEliminados enfuLiqEliminadosListEnfuLiqEliminadosToAttach : enfuFactsLiquidos.getEnfuLiqEliminadosList()) {
                enfuLiqEliminadosListEnfuLiqEliminadosToAttach = em.getReference(enfuLiqEliminadosListEnfuLiqEliminadosToAttach.getClass(), enfuLiqEliminadosListEnfuLiqEliminadosToAttach.getId());
                attachedEnfuLiqEliminadosList.add(enfuLiqEliminadosListEnfuLiqEliminadosToAttach);
            }
            enfuFactsLiquidos.setEnfuLiqEliminadosList(attachedEnfuLiqEliminadosList);
            em.persist(enfuFactsLiquidos);
            for (EnfuLiqAdministrados enfuLiqAdministradosListEnfuLiqAdministrados : enfuFactsLiquidos.getEnfuLiqAdministradosList()) {
                EnfuFactsLiquidos oldIdFactsLiquidosOfEnfuLiqAdministradosListEnfuLiqAdministrados = enfuLiqAdministradosListEnfuLiqAdministrados.getIdFactsLiquidos();
                enfuLiqAdministradosListEnfuLiqAdministrados.setIdFactsLiquidos(enfuFactsLiquidos);
                enfuLiqAdministradosListEnfuLiqAdministrados = em.merge(enfuLiqAdministradosListEnfuLiqAdministrados);
                if (oldIdFactsLiquidosOfEnfuLiqAdministradosListEnfuLiqAdministrados != null) {
                    oldIdFactsLiquidosOfEnfuLiqAdministradosListEnfuLiqAdministrados.getEnfuLiqAdministradosList().remove(enfuLiqAdministradosListEnfuLiqAdministrados);
                    oldIdFactsLiquidosOfEnfuLiqAdministradosListEnfuLiqAdministrados = em.merge(oldIdFactsLiquidosOfEnfuLiqAdministradosListEnfuLiqAdministrados);
                }
            }
            for (EnfuLiqEliminados enfuLiqEliminadosListEnfuLiqEliminados : enfuFactsLiquidos.getEnfuLiqEliminadosList()) {
                EnfuFactsLiquidos oldIdFactsLiquidosOfEnfuLiqEliminadosListEnfuLiqEliminados = enfuLiqEliminadosListEnfuLiqEliminados.getIdFactsLiquidos();
                enfuLiqEliminadosListEnfuLiqEliminados.setIdFactsLiquidos(enfuFactsLiquidos);
                enfuLiqEliminadosListEnfuLiqEliminados = em.merge(enfuLiqEliminadosListEnfuLiqEliminados);
                if (oldIdFactsLiquidosOfEnfuLiqEliminadosListEnfuLiqEliminados != null) {
                    oldIdFactsLiquidosOfEnfuLiqEliminadosListEnfuLiqEliminados.getEnfuLiqEliminadosList().remove(enfuLiqEliminadosListEnfuLiqEliminados);
                    oldIdFactsLiquidosOfEnfuLiqEliminadosListEnfuLiqEliminados = em.merge(oldIdFactsLiquidosOfEnfuLiqEliminadosListEnfuLiqEliminados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuFactsLiquidos enfuFactsLiquidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsLiquidos persistentEnfuFactsLiquidos = em.find(EnfuFactsLiquidos.class, enfuFactsLiquidos.getId());
            List<EnfuLiqAdministrados> enfuLiqAdministradosListOld = persistentEnfuFactsLiquidos.getEnfuLiqAdministradosList();
            List<EnfuLiqAdministrados> enfuLiqAdministradosListNew = enfuFactsLiquidos.getEnfuLiqAdministradosList();
            List<EnfuLiqEliminados> enfuLiqEliminadosListOld = persistentEnfuFactsLiquidos.getEnfuLiqEliminadosList();
            List<EnfuLiqEliminados> enfuLiqEliminadosListNew = enfuFactsLiquidos.getEnfuLiqEliminadosList();
            List<String> illegalOrphanMessages = null;
            for (EnfuLiqAdministrados enfuLiqAdministradosListOldEnfuLiqAdministrados : enfuLiqAdministradosListOld) {
                if (!enfuLiqAdministradosListNew.contains(enfuLiqAdministradosListOldEnfuLiqAdministrados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuLiqAdministrados " + enfuLiqAdministradosListOldEnfuLiqAdministrados + " since its idFactsLiquidos field is not nullable.");
                }
            }
            for (EnfuLiqEliminados enfuLiqEliminadosListOldEnfuLiqEliminados : enfuLiqEliminadosListOld) {
                if (!enfuLiqEliminadosListNew.contains(enfuLiqEliminadosListOldEnfuLiqEliminados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuLiqEliminados " + enfuLiqEliminadosListOldEnfuLiqEliminados + " since its idFactsLiquidos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<EnfuLiqAdministrados> attachedEnfuLiqAdministradosListNew = new ArrayList<EnfuLiqAdministrados>();
            for (EnfuLiqAdministrados enfuLiqAdministradosListNewEnfuLiqAdministradosToAttach : enfuLiqAdministradosListNew) {
                enfuLiqAdministradosListNewEnfuLiqAdministradosToAttach = em.getReference(enfuLiqAdministradosListNewEnfuLiqAdministradosToAttach.getClass(), enfuLiqAdministradosListNewEnfuLiqAdministradosToAttach.getId());
                attachedEnfuLiqAdministradosListNew.add(enfuLiqAdministradosListNewEnfuLiqAdministradosToAttach);
            }
            enfuLiqAdministradosListNew = attachedEnfuLiqAdministradosListNew;
            enfuFactsLiquidos.setEnfuLiqAdministradosList(enfuLiqAdministradosListNew);
            List<EnfuLiqEliminados> attachedEnfuLiqEliminadosListNew = new ArrayList<EnfuLiqEliminados>();
            for (EnfuLiqEliminados enfuLiqEliminadosListNewEnfuLiqEliminadosToAttach : enfuLiqEliminadosListNew) {
                enfuLiqEliminadosListNewEnfuLiqEliminadosToAttach = em.getReference(enfuLiqEliminadosListNewEnfuLiqEliminadosToAttach.getClass(), enfuLiqEliminadosListNewEnfuLiqEliminadosToAttach.getId());
                attachedEnfuLiqEliminadosListNew.add(enfuLiqEliminadosListNewEnfuLiqEliminadosToAttach);
            }
            enfuLiqEliminadosListNew = attachedEnfuLiqEliminadosListNew;
            enfuFactsLiquidos.setEnfuLiqEliminadosList(enfuLiqEliminadosListNew);
            enfuFactsLiquidos = em.merge(enfuFactsLiquidos);
            for (EnfuLiqAdministrados enfuLiqAdministradosListNewEnfuLiqAdministrados : enfuLiqAdministradosListNew) {
                if (!enfuLiqAdministradosListOld.contains(enfuLiqAdministradosListNewEnfuLiqAdministrados)) {
                    EnfuFactsLiquidos oldIdFactsLiquidosOfEnfuLiqAdministradosListNewEnfuLiqAdministrados = enfuLiqAdministradosListNewEnfuLiqAdministrados.getIdFactsLiquidos();
                    enfuLiqAdministradosListNewEnfuLiqAdministrados.setIdFactsLiquidos(enfuFactsLiquidos);
                    enfuLiqAdministradosListNewEnfuLiqAdministrados = em.merge(enfuLiqAdministradosListNewEnfuLiqAdministrados);
                    if (oldIdFactsLiquidosOfEnfuLiqAdministradosListNewEnfuLiqAdministrados != null && !oldIdFactsLiquidosOfEnfuLiqAdministradosListNewEnfuLiqAdministrados.equals(enfuFactsLiquidos)) {
                        oldIdFactsLiquidosOfEnfuLiqAdministradosListNewEnfuLiqAdministrados.getEnfuLiqAdministradosList().remove(enfuLiqAdministradosListNewEnfuLiqAdministrados);
                        oldIdFactsLiquidosOfEnfuLiqAdministradosListNewEnfuLiqAdministrados = em.merge(oldIdFactsLiquidosOfEnfuLiqAdministradosListNewEnfuLiqAdministrados);
                    }
                }
            }
            for (EnfuLiqEliminados enfuLiqEliminadosListNewEnfuLiqEliminados : enfuLiqEliminadosListNew) {
                if (!enfuLiqEliminadosListOld.contains(enfuLiqEliminadosListNewEnfuLiqEliminados)) {
                    EnfuFactsLiquidos oldIdFactsLiquidosOfEnfuLiqEliminadosListNewEnfuLiqEliminados = enfuLiqEliminadosListNewEnfuLiqEliminados.getIdFactsLiquidos();
                    enfuLiqEliminadosListNewEnfuLiqEliminados.setIdFactsLiquidos(enfuFactsLiquidos);
                    enfuLiqEliminadosListNewEnfuLiqEliminados = em.merge(enfuLiqEliminadosListNewEnfuLiqEliminados);
                    if (oldIdFactsLiquidosOfEnfuLiqEliminadosListNewEnfuLiqEliminados != null && !oldIdFactsLiquidosOfEnfuLiqEliminadosListNewEnfuLiqEliminados.equals(enfuFactsLiquidos)) {
                        oldIdFactsLiquidosOfEnfuLiqEliminadosListNewEnfuLiqEliminados.getEnfuLiqEliminadosList().remove(enfuLiqEliminadosListNewEnfuLiqEliminados);
                        oldIdFactsLiquidosOfEnfuLiqEliminadosListNewEnfuLiqEliminados = em.merge(oldIdFactsLiquidosOfEnfuLiqEliminadosListNewEnfuLiqEliminados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuFactsLiquidos.getId();
                if (findEnfuFactsLiquidos(id) == null) {
                    throw new NonexistentEntityException("The enfuFactsLiquidos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuFactsLiquidos enfuFactsLiquidos;
            try {
                enfuFactsLiquidos = em.getReference(EnfuFactsLiquidos.class, id);
                enfuFactsLiquidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuFactsLiquidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EnfuLiqAdministrados> enfuLiqAdministradosListOrphanCheck = enfuFactsLiquidos.getEnfuLiqAdministradosList();
            for (EnfuLiqAdministrados enfuLiqAdministradosListOrphanCheckEnfuLiqAdministrados : enfuLiqAdministradosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsLiquidos (" + enfuFactsLiquidos + ") cannot be destroyed since the EnfuLiqAdministrados " + enfuLiqAdministradosListOrphanCheckEnfuLiqAdministrados + " in its enfuLiqAdministradosList field has a non-nullable idFactsLiquidos field.");
            }
            List<EnfuLiqEliminados> enfuLiqEliminadosListOrphanCheck = enfuFactsLiquidos.getEnfuLiqEliminadosList();
            for (EnfuLiqEliminados enfuLiqEliminadosListOrphanCheckEnfuLiqEliminados : enfuLiqEliminadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuFactsLiquidos (" + enfuFactsLiquidos + ") cannot be destroyed since the EnfuLiqEliminados " + enfuLiqEliminadosListOrphanCheckEnfuLiqEliminados + " in its enfuLiqEliminadosList field has a non-nullable idFactsLiquidos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(enfuFactsLiquidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuFactsLiquidos> findEnfuFactsLiquidosEntities() {
        return findEnfuFactsLiquidosEntities(true, -1, -1);
    }

    public List<EnfuFactsLiquidos> findEnfuFactsLiquidosEntities(int maxResults, int firstResult) {
        return findEnfuFactsLiquidosEntities(false, maxResults, firstResult);
    }

    private List<EnfuFactsLiquidos> findEnfuFactsLiquidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuFactsLiquidos.class));
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

    public EnfuFactsLiquidos findEnfuFactsLiquidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuFactsLiquidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuFactsLiquidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuFactsLiquidos> rt = cq.from(EnfuFactsLiquidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EnfuFactsLiquidos> getControlesLiquidos(InfoHistoriac hc) {
        Query q = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT c FROM EnfuFactsLiquidos c WHERE c.idHistoria=:h AND c.estado='1'");
        q.setParameter("h", hc);
        return q.getResultList();
    }

    public Object getCountLiquidos(int h) {
        EntityManager em = getEntityManager();
        Query q = null;
        try {
            q = em.createQuery("SELECT COUNT(L) FROM EnfuFactsLiquidos L WHERE L.idHistoria.id=:ht AND L.estado='1'");
            q.setParameter("ht", h);
            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
