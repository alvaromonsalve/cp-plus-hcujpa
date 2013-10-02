/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.HcuMezclasMedicamentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HcuMezclasMedicamentosDesc;
import entidades.InfoHistoriac;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class HcuMezclasMedicamentosJpaController implements Serializable {

    public HcuMezclasMedicamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HcuMezclasMedicamentos hcuMezclasMedicamentos) {
        if (hcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList() == null) {
            hcuMezclasMedicamentos.setHcuMezclasMedicamentosDescList(new ArrayList<HcuMezclasMedicamentosDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HcuMezclasMedicamentosDesc> attachedHcuMezclasMedicamentosDescList = new ArrayList<HcuMezclasMedicamentosDesc>();
            for (HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDescToAttach : hcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList()) {
                hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDescToAttach = em.getReference(hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDescToAttach.getClass(), hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDescToAttach.getId());
                attachedHcuMezclasMedicamentosDescList.add(hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDescToAttach);
            }
            hcuMezclasMedicamentos.setHcuMezclasMedicamentosDescList(attachedHcuMezclasMedicamentosDescList);
            em.persist(hcuMezclasMedicamentos);
            for (HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc : hcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList()) {
                HcuMezclasMedicamentos oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc = hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc.getIdHcuMezclasMedicamentos();
                hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc.setIdHcuMezclasMedicamentos(hcuMezclasMedicamentos);
                hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc = em.merge(hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc);
                if (oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc != null) {
                    oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc.getHcuMezclasMedicamentosDescList().remove(hcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc);
                    oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc = em.merge(oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListHcuMezclasMedicamentosDesc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HcuMezclasMedicamentos hcuMezclasMedicamentos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HcuMezclasMedicamentos persistentHcuMezclasMedicamentos = em.find(HcuMezclasMedicamentos.class, hcuMezclasMedicamentos.getId());
            List<HcuMezclasMedicamentosDesc> hcuMezclasMedicamentosDescListOld = persistentHcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList();
            List<HcuMezclasMedicamentosDesc> hcuMezclasMedicamentosDescListNew = hcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList();
            List<String> illegalOrphanMessages = null;
            for (HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDescListOldHcuMezclasMedicamentosDesc : hcuMezclasMedicamentosDescListOld) {
                if (!hcuMezclasMedicamentosDescListNew.contains(hcuMezclasMedicamentosDescListOldHcuMezclasMedicamentosDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HcuMezclasMedicamentosDesc " + hcuMezclasMedicamentosDescListOldHcuMezclasMedicamentosDesc + " since its idHcuMezclasMedicamentos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HcuMezclasMedicamentosDesc> attachedHcuMezclasMedicamentosDescListNew = new ArrayList<HcuMezclasMedicamentosDesc>();
            for (HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDescToAttach : hcuMezclasMedicamentosDescListNew) {
                hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDescToAttach = em.getReference(hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDescToAttach.getClass(), hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDescToAttach.getId());
                attachedHcuMezclasMedicamentosDescListNew.add(hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDescToAttach);
            }
            hcuMezclasMedicamentosDescListNew = attachedHcuMezclasMedicamentosDescListNew;
            hcuMezclasMedicamentos.setHcuMezclasMedicamentosDescList(hcuMezclasMedicamentosDescListNew);
            hcuMezclasMedicamentos = em.merge(hcuMezclasMedicamentos);
            for (HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc : hcuMezclasMedicamentosDescListNew) {
                if (!hcuMezclasMedicamentosDescListOld.contains(hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc)) {
                    HcuMezclasMedicamentos oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc = hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc.getIdHcuMezclasMedicamentos();
                    hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc.setIdHcuMezclasMedicamentos(hcuMezclasMedicamentos);
                    hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc = em.merge(hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc);
                    if (oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc != null && !oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc.equals(hcuMezclasMedicamentos)) {
                        oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc.getHcuMezclasMedicamentosDescList().remove(hcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc);
                        oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc = em.merge(oldIdHcuMezclasMedicamentosOfHcuMezclasMedicamentosDescListNewHcuMezclasMedicamentosDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hcuMezclasMedicamentos.getId();
                if (findHcuMezclasMedicamentos(id) == null) {
                    throw new NonexistentEntityException("The hcuMezclasMedicamentos with id " + id + " no longer exists.");
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
            HcuMezclasMedicamentos hcuMezclasMedicamentos;
            try {
                hcuMezclasMedicamentos = em.getReference(HcuMezclasMedicamentos.class, id);
                hcuMezclasMedicamentos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hcuMezclasMedicamentos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HcuMezclasMedicamentosDesc> hcuMezclasMedicamentosDescListOrphanCheck = hcuMezclasMedicamentos.getHcuMezclasMedicamentosDescList();
            for (HcuMezclasMedicamentosDesc hcuMezclasMedicamentosDescListOrphanCheckHcuMezclasMedicamentosDesc : hcuMezclasMedicamentosDescListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HcuMezclasMedicamentos (" + hcuMezclasMedicamentos + ") cannot be destroyed since the HcuMezclasMedicamentosDesc " + hcuMezclasMedicamentosDescListOrphanCheckHcuMezclasMedicamentosDesc + " in its hcuMezclasMedicamentosDescList field has a non-nullable idHcuMezclasMedicamentos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hcuMezclasMedicamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HcuMezclasMedicamentos> findHcuMezclasMedicamentosEntities() {
        return findHcuMezclasMedicamentosEntities(true, -1, -1);
    }

    public List<HcuMezclasMedicamentos> findHcuMezclasMedicamentosEntities(int maxResults, int firstResult) {
        return findHcuMezclasMedicamentosEntities(false, maxResults, firstResult);
    }

    private List<HcuMezclasMedicamentos> findHcuMezclasMedicamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HcuMezclasMedicamentos.class));
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

    public HcuMezclasMedicamentos findHcuMezclasMedicamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HcuMezclasMedicamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHcuMezclasMedicamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HcuMezclasMedicamentos> rt = cq.from(HcuMezclasMedicamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
      //Codigo no Auto-generado    
   public List<HcuMezclasMedicamentos> ListFindHcuMezclas(InfoHistoriac ihc){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM HcuMezclasMedicamentos h WHERE h.idHistoriac = :hc AND h.estado='0'")
            .setParameter("hc", ihc)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
        } finally {
            em.close();
        }
   }
    
}
