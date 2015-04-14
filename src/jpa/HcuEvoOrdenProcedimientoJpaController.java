/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HcuEvoOrdenProcedimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HcuEvoOrdenProcedimientoDesc;
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
public class HcuEvoOrdenProcedimientoJpaController implements Serializable {

    public HcuEvoOrdenProcedimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuEvoOrdenProcedimiento hcuEvoOrdenProcedimiento) {
        if (hcuEvoOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList() == null) {
            hcuEvoOrdenProcedimiento.setHcuEvoOrdenProcedimientoDescList(new ArrayList<HcuEvoOrdenProcedimientoDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HcuEvoOrdenProcedimientoDesc> attachedHcuEvoOrdenProcedimientoDescList = new ArrayList<HcuEvoOrdenProcedimientoDesc>();
            for (HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDescToAttach : hcuEvoOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList()) {
                hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDescToAttach = em.getReference(hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDescToAttach.getClass(), hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDescToAttach.getId());
                attachedHcuEvoOrdenProcedimientoDescList.add(hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDescToAttach);
            }
            hcuEvoOrdenProcedimiento.setHcuEvoOrdenProcedimientoDescList(attachedHcuEvoOrdenProcedimientoDescList);
            em.persist(hcuEvoOrdenProcedimiento);
            for (HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc : hcuEvoOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList()) {
                HcuEvoOrdenProcedimiento oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc = hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc.getIdEvuOrdenProcedimiento();
                hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc.setIdEvuOrdenProcedimiento(hcuEvoOrdenProcedimiento);
                hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc = em.merge(hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc);
                if (oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc != null) {
                    oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc.getHcuEvoOrdenProcedimientoDescList().remove(hcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc);
                    oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc = em.merge(oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListHcuEvoOrdenProcedimientoDesc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuEvoOrdenProcedimiento hcuEvoOrdenProcedimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuEvoOrdenProcedimiento persistentHcuEvoOrdenProcedimiento = em.find(HcuEvoOrdenProcedimiento.class, hcuEvoOrdenProcedimiento.getId());
            List<HcuEvoOrdenProcedimientoDesc> hcuEvoOrdenProcedimientoDescListOld = persistentHcuEvoOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList();
            List<HcuEvoOrdenProcedimientoDesc> hcuEvoOrdenProcedimientoDescListNew = hcuEvoOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList();
            List<String> illegalOrphanMessages = null;
            for (HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDescListOldHcuEvoOrdenProcedimientoDesc : hcuEvoOrdenProcedimientoDescListOld) {
                if (!hcuEvoOrdenProcedimientoDescListNew.contains(hcuEvoOrdenProcedimientoDescListOldHcuEvoOrdenProcedimientoDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuEvoOrdenProcedimientoDesc " + hcuEvoOrdenProcedimientoDescListOldHcuEvoOrdenProcedimientoDesc + " since its idEvuOrdenProcedimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HcuEvoOrdenProcedimientoDesc> attachedHcuEvoOrdenProcedimientoDescListNew = new ArrayList<HcuEvoOrdenProcedimientoDesc>();
            for (HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDescToAttach : hcuEvoOrdenProcedimientoDescListNew) {
                hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDescToAttach = em.getReference(hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDescToAttach.getClass(), hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDescToAttach.getId());
                attachedHcuEvoOrdenProcedimientoDescListNew.add(hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDescToAttach);
            }
            hcuEvoOrdenProcedimientoDescListNew = attachedHcuEvoOrdenProcedimientoDescListNew;
            hcuEvoOrdenProcedimiento.setHcuEvoOrdenProcedimientoDescList(hcuEvoOrdenProcedimientoDescListNew);
            hcuEvoOrdenProcedimiento = em.merge(hcuEvoOrdenProcedimiento);
            for (HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc : hcuEvoOrdenProcedimientoDescListNew) {
                if (!hcuEvoOrdenProcedimientoDescListOld.contains(hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc)) {
                    HcuEvoOrdenProcedimiento oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc = hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc.getIdEvuOrdenProcedimiento();
                    hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc.setIdEvuOrdenProcedimiento(hcuEvoOrdenProcedimiento);
                    hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc = em.merge(hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc);
                    if (oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc != null && !oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc.equals(hcuEvoOrdenProcedimiento)) {
                        oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc.getHcuEvoOrdenProcedimientoDescList().remove(hcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc);
                        oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc = em.merge(oldIdEvuOrdenProcedimientoOfHcuEvoOrdenProcedimientoDescListNewHcuEvoOrdenProcedimientoDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuEvoOrdenProcedimiento.getId();
                if (findHcuEvoOrdenProcedimiento(id) == null) {
                    throw new NonexistentEntityException("The hcuEvoOrdenProcedimiento with id " + id + " no longer exists.");
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
            HcuEvoOrdenProcedimiento hcuEvoOrdenProcedimiento;
            try {
                hcuEvoOrdenProcedimiento = em.getReference(HcuEvoOrdenProcedimiento.class, id);
                hcuEvoOrdenProcedimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuEvoOrdenProcedimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HcuEvoOrdenProcedimientoDesc> hcuEvoOrdenProcedimientoDescListOrphanCheck = hcuEvoOrdenProcedimiento.getHcuEvoOrdenProcedimientoDescList();
            for (HcuEvoOrdenProcedimientoDesc hcuEvoOrdenProcedimientoDescListOrphanCheckHcuEvoOrdenProcedimientoDesc : hcuEvoOrdenProcedimientoDescListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuEvoOrdenProcedimiento (" + hcuEvoOrdenProcedimiento + ") cannot be destroyed since the HcuEvoOrdenProcedimientoDesc " + hcuEvoOrdenProcedimientoDescListOrphanCheckHcuEvoOrdenProcedimientoDesc + " in its hcuEvoOrdenProcedimientoDescList field has a non-nullable idEvuOrdenProcedimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hcuEvoOrdenProcedimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuEvoOrdenProcedimiento> findHcuEvoOrdenProcedimientoEntities() {
        return findHcuEvoOrdenProcedimientoEntities(true, -1, -1);
    }

    public List<HcuEvoOrdenProcedimiento> findHcuEvoOrdenProcedimientoEntities(int maxResults, int firstResult) {
        return findHcuEvoOrdenProcedimientoEntities(false, maxResults, firstResult);
    }

    private List<HcuEvoOrdenProcedimiento> findHcuEvoOrdenProcedimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuEvoOrdenProcedimiento.class));
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

    public HcuEvoOrdenProcedimiento findHcuEvoOrdenProcedimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuEvoOrdenProcedimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuEvoOrdenProcedimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuEvoOrdenProcedimiento> rt = cq.from(HcuEvoOrdenProcedimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
