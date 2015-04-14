/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HcuOrdenProcedimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HcuOrdenProcedimientoDesc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HcuOrdenProcedimientoJpaController implements Serializable {

    public HcuOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuOrdenProcedimiento hcuOrdenProcedimiento) {
        if (hcuOrdenProcedimiento.getHcuOrdenProcedimientoDescList() == null) {
            hcuOrdenProcedimiento.setHcuOrdenProcedimientoDescList(new ArrayList<HcuOrdenProcedimientoDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HcuOrdenProcedimientoDesc> attachedHcuOrdenProcedimientoDescList = new ArrayList<HcuOrdenProcedimientoDesc>();
            for (HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDescToAttach : hcuOrdenProcedimiento.getHcuOrdenProcedimientoDescList()) {
                hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDescToAttach = em.getReference(hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDescToAttach.getClass(), hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDescToAttach.getId());
                attachedHcuOrdenProcedimientoDescList.add(hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDescToAttach);
            }
            hcuOrdenProcedimiento.setHcuOrdenProcedimientoDescList(attachedHcuOrdenProcedimientoDescList);
            em.persist(hcuOrdenProcedimiento);
            for (HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc : hcuOrdenProcedimiento.getHcuOrdenProcedimientoDescList()) {
                HcuOrdenProcedimiento oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc = hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc.getIdOrdenProcedimiento();
                hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc.setIdOrdenProcedimiento(hcuOrdenProcedimiento);
                hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc = em.merge(hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc);
                if (oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc != null) {
                    oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc.getHcuOrdenProcedimientoDescList().remove(hcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc);
                    oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc = em.merge(oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListHcuOrdenProcedimientoDesc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuOrdenProcedimiento hcuOrdenProcedimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuOrdenProcedimiento persistentHcuOrdenProcedimiento = em.find(HcuOrdenProcedimiento.class, hcuOrdenProcedimiento.getId());
            List<HcuOrdenProcedimientoDesc> hcuOrdenProcedimientoDescListOld = persistentHcuOrdenProcedimiento.getHcuOrdenProcedimientoDescList();
            List<HcuOrdenProcedimientoDesc> hcuOrdenProcedimientoDescListNew = hcuOrdenProcedimiento.getHcuOrdenProcedimientoDescList();
            List<String> illegalOrphanMessages = null;
            for (HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDescListOldHcuOrdenProcedimientoDesc : hcuOrdenProcedimientoDescListOld) {
                if (!hcuOrdenProcedimientoDescListNew.contains(hcuOrdenProcedimientoDescListOldHcuOrdenProcedimientoDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuOrdenProcedimientoDesc " + hcuOrdenProcedimientoDescListOldHcuOrdenProcedimientoDesc + " since its idOrdenProcedimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HcuOrdenProcedimientoDesc> attachedHcuOrdenProcedimientoDescListNew = new ArrayList<HcuOrdenProcedimientoDesc>();
            for (HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDescToAttach : hcuOrdenProcedimientoDescListNew) {
                hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDescToAttach = em.getReference(hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDescToAttach.getClass(), hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDescToAttach.getId());
                attachedHcuOrdenProcedimientoDescListNew.add(hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDescToAttach);
            }
            hcuOrdenProcedimientoDescListNew = attachedHcuOrdenProcedimientoDescListNew;
            hcuOrdenProcedimiento.setHcuOrdenProcedimientoDescList(hcuOrdenProcedimientoDescListNew);
            hcuOrdenProcedimiento = em.merge(hcuOrdenProcedimiento);
            for (HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc : hcuOrdenProcedimientoDescListNew) {
                if (!hcuOrdenProcedimientoDescListOld.contains(hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc)) {
                    HcuOrdenProcedimiento oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc = hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc.getIdOrdenProcedimiento();
                    hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc.setIdOrdenProcedimiento(hcuOrdenProcedimiento);
                    hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc = em.merge(hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc);
                    if (oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc != null && !oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc.equals(hcuOrdenProcedimiento)) {
                        oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc.getHcuOrdenProcedimientoDescList().remove(hcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc);
                        oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc = em.merge(oldIdOrdenProcedimientoOfHcuOrdenProcedimientoDescListNewHcuOrdenProcedimientoDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuOrdenProcedimiento.getId();
                if (findHcuOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hcuOrdenProcedimiento with id " + id + " no longer exists.");
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
            HcuOrdenProcedimiento hcuOrdenProcedimiento;
            try {
                hcuOrdenProcedimiento = em.getReference(HcuOrdenProcedimiento.class, id);
                hcuOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HcuOrdenProcedimientoDesc> hcuOrdenProcedimientoDescListOrphanCheck = hcuOrdenProcedimiento.getHcuOrdenProcedimientoDescList();
            for (HcuOrdenProcedimientoDesc hcuOrdenProcedimientoDescListOrphanCheckHcuOrdenProcedimientoDesc : hcuOrdenProcedimientoDescListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuOrdenProcedimiento (" + hcuOrdenProcedimiento + ") cannot be destroyed since the HcuOrdenProcedimientoDesc " + hcuOrdenProcedimientoDescListOrphanCheckHcuOrdenProcedimientoDesc + " in its hcuOrdenProcedimientoDescList field has a non-nullable idOrdenProcedimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hcuOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuOrdenProcedimiento> findHcuOrdenProcedimientoEntities() {
        return findHcuOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<HcuOrdenProcedimiento> findHcuOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findHcuOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HcuOrdenProcedimiento> findHcuOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuOrdenProcedimiento.class));
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

    public HcuOrdenProcedimiento findHcuOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuOrdenProcedimiento> rt = cq.from(HcuOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
