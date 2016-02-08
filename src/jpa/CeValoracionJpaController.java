/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.CeHistoriac;
import entidades_EJB.CeValoracion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.CeValoracionDesc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author IdlhDeveloper
 */
public class CeValoracionJpaController implements Serializable {

    public CeValoracionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeValoracion ceValoracion) {
        if (ceValoracion.getCeValoracionDescList() == null) {
            ceValoracion.setCeValoracionDescList(new ArrayList<CeValoracionDesc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CeValoracionDesc> attachedCeValoracionDescList = new ArrayList<CeValoracionDesc>();
            for (CeValoracionDesc ceValoracionDescListCeValoracionDescToAttach : ceValoracion.getCeValoracionDescList()) {
                ceValoracionDescListCeValoracionDescToAttach = em.getReference(ceValoracionDescListCeValoracionDescToAttach.getClass(), ceValoracionDescListCeValoracionDescToAttach.getId());
                attachedCeValoracionDescList.add(ceValoracionDescListCeValoracionDescToAttach);
            }
            ceValoracion.setCeValoracionDescList(attachedCeValoracionDescList);
            em.persist(ceValoracion);
            for (CeValoracionDesc ceValoracionDescListCeValoracionDesc : ceValoracion.getCeValoracionDescList()) {
                CeValoracion oldIdCeValoracionOfCeValoracionDescListCeValoracionDesc = ceValoracionDescListCeValoracionDesc.getIdCeValoracion();
                ceValoracionDescListCeValoracionDesc.setIdCeValoracion(ceValoracion);
                ceValoracionDescListCeValoracionDesc = em.merge(ceValoracionDescListCeValoracionDesc);
                if (oldIdCeValoracionOfCeValoracionDescListCeValoracionDesc != null) {
                    oldIdCeValoracionOfCeValoracionDescListCeValoracionDesc.getCeValoracionDescList().remove(ceValoracionDescListCeValoracionDesc);
                    oldIdCeValoracionOfCeValoracionDescListCeValoracionDesc = em.merge(oldIdCeValoracionOfCeValoracionDescListCeValoracionDesc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeValoracion ceValoracion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeValoracion persistentCeValoracion = em.find(CeValoracion.class, ceValoracion.getId());
            List<CeValoracionDesc> ceValoracionDescListOld = persistentCeValoracion.getCeValoracionDescList();
            List<CeValoracionDesc> ceValoracionDescListNew = ceValoracion.getCeValoracionDescList();
            List<String> illegalOrphanMessages = null;
            for (CeValoracionDesc ceValoracionDescListOldCeValoracionDesc : ceValoracionDescListOld) {
                if (!ceValoracionDescListNew.contains(ceValoracionDescListOldCeValoracionDesc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CeValoracionDesc " + ceValoracionDescListOldCeValoracionDesc + " since its idCeValoracion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CeValoracionDesc> attachedCeValoracionDescListNew = new ArrayList<CeValoracionDesc>();
            for (CeValoracionDesc ceValoracionDescListNewCeValoracionDescToAttach : ceValoracionDescListNew) {
                ceValoracionDescListNewCeValoracionDescToAttach = em.getReference(ceValoracionDescListNewCeValoracionDescToAttach.getClass(), ceValoracionDescListNewCeValoracionDescToAttach.getId());
                attachedCeValoracionDescListNew.add(ceValoracionDescListNewCeValoracionDescToAttach);
            }
            ceValoracionDescListNew = attachedCeValoracionDescListNew;
            ceValoracion.setCeValoracionDescList(ceValoracionDescListNew);
            ceValoracion = em.merge(ceValoracion);
            for (CeValoracionDesc ceValoracionDescListNewCeValoracionDesc : ceValoracionDescListNew) {
                if (!ceValoracionDescListOld.contains(ceValoracionDescListNewCeValoracionDesc)) {
                    CeValoracion oldIdCeValoracionOfCeValoracionDescListNewCeValoracionDesc = ceValoracionDescListNewCeValoracionDesc.getIdCeValoracion();
                    ceValoracionDescListNewCeValoracionDesc.setIdCeValoracion(ceValoracion);
                    ceValoracionDescListNewCeValoracionDesc = em.merge(ceValoracionDescListNewCeValoracionDesc);
                    if (oldIdCeValoracionOfCeValoracionDescListNewCeValoracionDesc != null && !oldIdCeValoracionOfCeValoracionDescListNewCeValoracionDesc.equals(ceValoracion)) {
                        oldIdCeValoracionOfCeValoracionDescListNewCeValoracionDesc.getCeValoracionDescList().remove(ceValoracionDescListNewCeValoracionDesc);
                        oldIdCeValoracionOfCeValoracionDescListNewCeValoracionDesc = em.merge(oldIdCeValoracionOfCeValoracionDescListNewCeValoracionDesc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceValoracion.getId();
                if (findCeValoracion(id) == null) {
                    throw new NonexistentEntityException("The ceValoracion with id " + id + " no longer exists.");
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
            CeValoracion ceValoracion;
            try {
                ceValoracion = em.getReference(CeValoracion.class, id);
                ceValoracion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceValoracion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CeValoracionDesc> ceValoracionDescListOrphanCheck = ceValoracion.getCeValoracionDescList();
            for (CeValoracionDesc ceValoracionDescListOrphanCheckCeValoracionDesc : ceValoracionDescListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CeValoracion (" + ceValoracion + ") cannot be destroyed since the CeValoracionDesc " + ceValoracionDescListOrphanCheckCeValoracionDesc + " in its ceValoracionDescList field has a non-nullable idCeValoracion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ceValoracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeValoracion> findCeValoracionEntities() {
        return findCeValoracionEntities(true, -1, -1);
    }

    public List<CeValoracion> findCeValoracionEntities(int maxResults, int firstResult) {
        return findCeValoracionEntities(false, maxResults, firstResult);
    }

    private List<CeValoracion> findCeValoracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeValoracion.class));
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

    public CeValoracion findCeValoracion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeValoracion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeValoracionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeValoracion> rt = cq.from(CeValoracion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public CeValoracion getEntidadValoracion(CeHistoriac h) {
        CeValoracion v = null;
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT v FROM CeValoracion v WHERE v.idHistoriace=:ht AND v.estado='1'");
        q.setParameter("ht", h);
        List<CeValoracion> val = q.getResultList();
        if (!val.isEmpty()) {
            v = (CeValoracion) val.get(0);
        } else {
            v = null;
        }
        return v;
    }
}
