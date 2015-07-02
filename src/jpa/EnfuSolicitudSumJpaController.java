/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.EnfuSolicitudSum;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.EnfuSolicitudSumDesc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class EnfuSolicitudSumJpaController implements Serializable {

    public EnfuSolicitudSumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EnfuSolicitudSum enfuSolicitudSum) {
        if (enfuSolicitudSum.getEnfuSolicitudSumDescList() == null) {
            enfuSolicitudSum.setEnfuSolicitudSumDescList(new ArrayList<EnfuSolicitudSumDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EnfuSolicitudSumDesc> attachedEnfuSolicitudSumDescList = new ArrayList<EnfuSolicitudSumDesc>();
            for (EnfuSolicitudSumDesc enfuSolicitudSumDescListEnfuSolicitudSumDescToAttach : enfuSolicitudSum.getEnfuSolicitudSumDescList()) {
                enfuSolicitudSumDescListEnfuSolicitudSumDescToAttach = em.getReference(enfuSolicitudSumDescListEnfuSolicitudSumDescToAttach.getClass(), enfuSolicitudSumDescListEnfuSolicitudSumDescToAttach.getId());
                attachedEnfuSolicitudSumDescList.add(enfuSolicitudSumDescListEnfuSolicitudSumDescToAttach);
            }
            enfuSolicitudSum.setEnfuSolicitudSumDescList(attachedEnfuSolicitudSumDescList);
            em.persist(enfuSolicitudSum);
            for (EnfuSolicitudSumDesc enfuSolicitudSumDescListEnfuSolicitudSumDesc : enfuSolicitudSum.getEnfuSolicitudSumDescList()) {
                EnfuSolicitudSum oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListEnfuSolicitudSumDesc = enfuSolicitudSumDescListEnfuSolicitudSumDesc.getIdEnfuSolicitudSum();
                enfuSolicitudSumDescListEnfuSolicitudSumDesc.setIdEnfuSolicitudSum(enfuSolicitudSum);
                enfuSolicitudSumDescListEnfuSolicitudSumDesc = em.merge(enfuSolicitudSumDescListEnfuSolicitudSumDesc);
                if (oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListEnfuSolicitudSumDesc != null) {
                    oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListEnfuSolicitudSumDesc.getEnfuSolicitudSumDescList().remove(enfuSolicitudSumDescListEnfuSolicitudSumDesc);
                    oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListEnfuSolicitudSumDesc = em.merge(oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListEnfuSolicitudSumDesc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfuSolicitudSum enfuSolicitudSum) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfuSolicitudSum persistentEnfuSolicitudSum = em.find(EnfuSolicitudSum.class, enfuSolicitudSum.getId());
            List<EnfuSolicitudSumDesc> enfuSolicitudSumDescListOld = persistentEnfuSolicitudSum.getEnfuSolicitudSumDescList();
            List<EnfuSolicitudSumDesc> enfuSolicitudSumDescListNew = enfuSolicitudSum.getEnfuSolicitudSumDescList();
            List<String> illegalOrphanMessages = null;
            for (EnfuSolicitudSumDesc enfuSolicitudSumDescListOldEnfuSolicitudSumDesc : enfuSolicitudSumDescListOld) {
                if (!enfuSolicitudSumDescListNew.contains(enfuSolicitudSumDescListOldEnfuSolicitudSumDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EnfuSolicitudSumDesc " + enfuSolicitudSumDescListOldEnfuSolicitudSumDesc + " since its idEnfuSolicitudSum field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<EnfuSolicitudSumDesc> attachedEnfuSolicitudSumDescListNew = new ArrayList<EnfuSolicitudSumDesc>();
            for (EnfuSolicitudSumDesc enfuSolicitudSumDescListNewEnfuSolicitudSumDescToAttach : enfuSolicitudSumDescListNew) {
                enfuSolicitudSumDescListNewEnfuSolicitudSumDescToAttach = em.getReference(enfuSolicitudSumDescListNewEnfuSolicitudSumDescToAttach.getClass(), enfuSolicitudSumDescListNewEnfuSolicitudSumDescToAttach.getId());
                attachedEnfuSolicitudSumDescListNew.add(enfuSolicitudSumDescListNewEnfuSolicitudSumDescToAttach);
            }
            enfuSolicitudSumDescListNew = attachedEnfuSolicitudSumDescListNew;
            enfuSolicitudSum.setEnfuSolicitudSumDescList(enfuSolicitudSumDescListNew);
            enfuSolicitudSum = em.merge(enfuSolicitudSum);
            for (EnfuSolicitudSumDesc enfuSolicitudSumDescListNewEnfuSolicitudSumDesc : enfuSolicitudSumDescListNew) {
                if (!enfuSolicitudSumDescListOld.contains(enfuSolicitudSumDescListNewEnfuSolicitudSumDesc)) {
                    EnfuSolicitudSum oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListNewEnfuSolicitudSumDesc = enfuSolicitudSumDescListNewEnfuSolicitudSumDesc.getIdEnfuSolicitudSum();
                    enfuSolicitudSumDescListNewEnfuSolicitudSumDesc.setIdEnfuSolicitudSum(enfuSolicitudSum);
                    enfuSolicitudSumDescListNewEnfuSolicitudSumDesc = em.merge(enfuSolicitudSumDescListNewEnfuSolicitudSumDesc);
                    if (oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListNewEnfuSolicitudSumDesc != null && !oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListNewEnfuSolicitudSumDesc.equals(enfuSolicitudSum)) {
                        oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListNewEnfuSolicitudSumDesc.getEnfuSolicitudSumDescList().remove(enfuSolicitudSumDescListNewEnfuSolicitudSumDesc);
                        oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListNewEnfuSolicitudSumDesc = em.merge(oldIdEnfuSolicitudSumOfEnfuSolicitudSumDescListNewEnfuSolicitudSumDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfuSolicitudSum.getId();
                if (findEnfuSolicitudSum(id) == null) {
                    throw new NonexistentEntityException("The enfuSolicitudSum with id " + id + " no longer exists.");
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
            EnfuSolicitudSum enfuSolicitudSum;
            try {
                enfuSolicitudSum = em.getReference(EnfuSolicitudSum.class, id);
                enfuSolicitudSum.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfuSolicitudSum with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EnfuSolicitudSumDesc> enfuSolicitudSumDescListOrphanCheck = enfuSolicitudSum.getEnfuSolicitudSumDescList();
            for (EnfuSolicitudSumDesc enfuSolicitudSumDescListOrphanCheckEnfuSolicitudSumDesc : enfuSolicitudSumDescListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EnfuSolicitudSum (" + enfuSolicitudSum + ") cannot be destroyed since the EnfuSolicitudSumDesc " + enfuSolicitudSumDescListOrphanCheckEnfuSolicitudSumDesc + " in its enfuSolicitudSumDescList field has a non-nullable idEnfuSolicitudSum field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(enfuSolicitudSum);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfuSolicitudSum> findEnfuSolicitudSumEntities() {
        return findEnfuSolicitudSumEntities(true, -1, -1);
    }

    public List<EnfuSolicitudSum> findEnfuSolicitudSumEntities(int maxResults, int firstResult) {
        return findEnfuSolicitudSumEntities(false, maxResults, firstResult);
    }

    private List<EnfuSolicitudSum> findEnfuSolicitudSumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfuSolicitudSum.class));
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

    public EnfuSolicitudSum findEnfuSolicitudSum(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfuSolicitudSum.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfuSolicitudSumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfuSolicitudSum> rt = cq.from(EnfuSolicitudSum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
