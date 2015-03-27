/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.CmEspPprofesional;
import entidades_EJB.CmProfesionales;
import entidades_EJB.Configdecripcionlogin;
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
public class CmProfesionalesJpaController implements Serializable {

    public CmProfesionalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CmProfesionales cmProfesionales) {
        if (cmProfesionales.getCmEspPprofesionalList() == null) {
            cmProfesionales.setCmEspPprofesionalList(new ArrayList<CmEspPprofesional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CmEspPprofesional> attachedCmEspPprofesionalList = new ArrayList<CmEspPprofesional>();
            for (CmEspPprofesional cmEspPprofesionalListCmEspPprofesionalToAttach : cmProfesionales.getCmEspPprofesionalList()) {
                cmEspPprofesionalListCmEspPprofesionalToAttach = em.getReference(cmEspPprofesionalListCmEspPprofesionalToAttach.getClass(), cmEspPprofesionalListCmEspPprofesionalToAttach.getId());
                attachedCmEspPprofesionalList.add(cmEspPprofesionalListCmEspPprofesionalToAttach);
            }
            cmProfesionales.setCmEspPprofesionalList(attachedCmEspPprofesionalList);
            em.persist(cmProfesionales);
            for (CmEspPprofesional cmEspPprofesionalListCmEspPprofesional : cmProfesionales.getCmEspPprofesionalList()) {
                CmProfesionales oldIdProfesionalOfCmEspPprofesionalListCmEspPprofesional = cmEspPprofesionalListCmEspPprofesional.getIdProfesional();
                cmEspPprofesionalListCmEspPprofesional.setIdProfesional(cmProfesionales);
                cmEspPprofesionalListCmEspPprofesional = em.merge(cmEspPprofesionalListCmEspPprofesional);
                if (oldIdProfesionalOfCmEspPprofesionalListCmEspPprofesional != null) {
                    oldIdProfesionalOfCmEspPprofesionalListCmEspPprofesional.getCmEspPprofesionalList().remove(cmEspPprofesionalListCmEspPprofesional);
                    oldIdProfesionalOfCmEspPprofesionalListCmEspPprofesional = em.merge(oldIdProfesionalOfCmEspPprofesionalListCmEspPprofesional);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CmProfesionales cmProfesionales) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CmProfesionales persistentCmProfesionales = em.find(CmProfesionales.class, cmProfesionales.getId());
            List<CmEspPprofesional> cmEspPprofesionalListOld = persistentCmProfesionales.getCmEspPprofesionalList();
            List<CmEspPprofesional> cmEspPprofesionalListNew = cmProfesionales.getCmEspPprofesionalList();
            List<String> illegalOrphanMessages = null;
            for (CmEspPprofesional cmEspPprofesionalListOldCmEspPprofesional : cmEspPprofesionalListOld) {
                if (!cmEspPprofesionalListNew.contains(cmEspPprofesionalListOldCmEspPprofesional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CmEspPprofesional " + cmEspPprofesionalListOldCmEspPprofesional + " since its idProfesional field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CmEspPprofesional> attachedCmEspPprofesionalListNew = new ArrayList<CmEspPprofesional>();
            for (CmEspPprofesional cmEspPprofesionalListNewCmEspPprofesionalToAttach : cmEspPprofesionalListNew) {
                cmEspPprofesionalListNewCmEspPprofesionalToAttach = em.getReference(cmEspPprofesionalListNewCmEspPprofesionalToAttach.getClass(), cmEspPprofesionalListNewCmEspPprofesionalToAttach.getId());
                attachedCmEspPprofesionalListNew.add(cmEspPprofesionalListNewCmEspPprofesionalToAttach);
            }
            cmEspPprofesionalListNew = attachedCmEspPprofesionalListNew;
            cmProfesionales.setCmEspPprofesionalList(cmEspPprofesionalListNew);
            cmProfesionales = em.merge(cmProfesionales);
            for (CmEspPprofesional cmEspPprofesionalListNewCmEspPprofesional : cmEspPprofesionalListNew) {
                if (!cmEspPprofesionalListOld.contains(cmEspPprofesionalListNewCmEspPprofesional)) {
                    CmProfesionales oldIdProfesionalOfCmEspPprofesionalListNewCmEspPprofesional = cmEspPprofesionalListNewCmEspPprofesional.getIdProfesional();
                    cmEspPprofesionalListNewCmEspPprofesional.setIdProfesional(cmProfesionales);
                    cmEspPprofesionalListNewCmEspPprofesional = em.merge(cmEspPprofesionalListNewCmEspPprofesional);
                    if (oldIdProfesionalOfCmEspPprofesionalListNewCmEspPprofesional != null && !oldIdProfesionalOfCmEspPprofesionalListNewCmEspPprofesional.equals(cmProfesionales)) {
                        oldIdProfesionalOfCmEspPprofesionalListNewCmEspPprofesional.getCmEspPprofesionalList().remove(cmEspPprofesionalListNewCmEspPprofesional);
                        oldIdProfesionalOfCmEspPprofesionalListNewCmEspPprofesional = em.merge(oldIdProfesionalOfCmEspPprofesionalListNewCmEspPprofesional);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cmProfesionales.getId();
                if (findCmProfesionales(id) == null) {
                    throw new NonexistentEntityException("The cmProfesionales with id " + id + " no longer exists.");
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
            CmProfesionales cmProfesionales;
            try {
                cmProfesionales = em.getReference(CmProfesionales.class, id);
                cmProfesionales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cmProfesionales with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CmEspPprofesional> cmEspPprofesionalListOrphanCheck = cmProfesionales.getCmEspPprofesionalList();
            for (CmEspPprofesional cmEspPprofesionalListOrphanCheckCmEspPprofesional : cmEspPprofesionalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CmProfesionales (" + cmProfesionales + ") cannot be destroyed since the CmEspPprofesional " + cmEspPprofesionalListOrphanCheckCmEspPprofesional + " in its cmEspPprofesionalList field has a non-nullable idProfesional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cmProfesionales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CmProfesionales> findCmProfesionalesEntities() {
        return findCmProfesionalesEntities(true, -1, -1);
    }

    public List<CmProfesionales> findCmProfesionalesEntities(int maxResults, int firstResult) {
        return findCmProfesionalesEntities(false, maxResults, firstResult);
    }

    private List<CmProfesionales> findCmProfesionalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CmProfesionales.class));
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

    public CmProfesionales findCmProfesionales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CmProfesionales.class, id);
        } finally {
            em.close();
        }
    }

    public int getCmProfesionalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CmProfesionales> rt = cq.from(CmProfesionales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        //Codigo no auto-generado
    
    public CmProfesionales pprofesional(Configdecripcionlogin config){
        EntityManager em = getEntityManager();
        try {
            return (CmProfesionales) em.createQuery("SELECT c FROM CmProfesionales c WHERE c.idDescripcionLogin=:config AND c.estado='1'")
                    .setParameter("config", config)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getSingleResult();
        }catch(Exception e){
            return null;
        }finally {
            em.close();
        }
    }
    
}
