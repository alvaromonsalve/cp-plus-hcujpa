/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.UrgInfquirurgico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgInfquirurgicoDxpre;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UrgInfquirurgicoCups;
import entidades_EJB.UrgInfquirurgicoDxpost;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UrgInfquirurgicoJpaController implements Serializable {

    public UrgInfquirurgicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgInfquirurgico urgInfquirurgico) {
        if (urgInfquirurgico.getUrgInfquirurgicoDxpreList() == null) {
            urgInfquirurgico.setUrgInfquirurgicoDxpreList(new ArrayList<UrgInfquirurgicoDxpre>());
        }
        if (urgInfquirurgico.getUrgInfquirurgicoCupsList() == null) {
            urgInfquirurgico.setUrgInfquirurgicoCupsList(new ArrayList<UrgInfquirurgicoCups>());
        }
        if (urgInfquirurgico.getUrgInfquirurgicoDxpostList() == null) {
            urgInfquirurgico.setUrgInfquirurgicoDxpostList(new ArrayList<UrgInfquirurgicoDxpost>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UrgInfquirurgicoDxpre> attachedUrgInfquirurgicoDxpreList = new ArrayList<UrgInfquirurgicoDxpre>();
            for (UrgInfquirurgicoDxpre urgInfquirurgicoDxpreListUrgInfquirurgicoDxpreToAttach : urgInfquirurgico.getUrgInfquirurgicoDxpreList()) {
                urgInfquirurgicoDxpreListUrgInfquirurgicoDxpreToAttach = em.getReference(urgInfquirurgicoDxpreListUrgInfquirurgicoDxpreToAttach.getClass(), urgInfquirurgicoDxpreListUrgInfquirurgicoDxpreToAttach.getId());
                attachedUrgInfquirurgicoDxpreList.add(urgInfquirurgicoDxpreListUrgInfquirurgicoDxpreToAttach);
            }
            urgInfquirurgico.setUrgInfquirurgicoDxpreList(attachedUrgInfquirurgicoDxpreList);
            List<UrgInfquirurgicoCups> attachedUrgInfquirurgicoCupsList = new ArrayList<UrgInfquirurgicoCups>();
            for (UrgInfquirurgicoCups urgInfquirurgicoCupsListUrgInfquirurgicoCupsToAttach : urgInfquirurgico.getUrgInfquirurgicoCupsList()) {
                urgInfquirurgicoCupsListUrgInfquirurgicoCupsToAttach = em.getReference(urgInfquirurgicoCupsListUrgInfquirurgicoCupsToAttach.getClass(), urgInfquirurgicoCupsListUrgInfquirurgicoCupsToAttach.getId());
                attachedUrgInfquirurgicoCupsList.add(urgInfquirurgicoCupsListUrgInfquirurgicoCupsToAttach);
            }
            urgInfquirurgico.setUrgInfquirurgicoCupsList(attachedUrgInfquirurgicoCupsList);
            List<UrgInfquirurgicoDxpost> attachedUrgInfquirurgicoDxpostList = new ArrayList<UrgInfquirurgicoDxpost>();
            for (UrgInfquirurgicoDxpost urgInfquirurgicoDxpostListUrgInfquirurgicoDxpostToAttach : urgInfquirurgico.getUrgInfquirurgicoDxpostList()) {
                urgInfquirurgicoDxpostListUrgInfquirurgicoDxpostToAttach = em.getReference(urgInfquirurgicoDxpostListUrgInfquirurgicoDxpostToAttach.getClass(), urgInfquirurgicoDxpostListUrgInfquirurgicoDxpostToAttach.getId());
                attachedUrgInfquirurgicoDxpostList.add(urgInfquirurgicoDxpostListUrgInfquirurgicoDxpostToAttach);
            }
            urgInfquirurgico.setUrgInfquirurgicoDxpostList(attachedUrgInfquirurgicoDxpostList);
            em.persist(urgInfquirurgico);
            for (UrgInfquirurgicoDxpre urgInfquirurgicoDxpreListUrgInfquirurgicoDxpre : urgInfquirurgico.getUrgInfquirurgicoDxpreList()) {
                UrgInfquirurgico oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListUrgInfquirurgicoDxpre = urgInfquirurgicoDxpreListUrgInfquirurgicoDxpre.getIdinfquirurgico();
                urgInfquirurgicoDxpreListUrgInfquirurgicoDxpre.setIdinfquirurgico(urgInfquirurgico);
                urgInfquirurgicoDxpreListUrgInfquirurgicoDxpre = em.merge(urgInfquirurgicoDxpreListUrgInfquirurgicoDxpre);
                if (oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListUrgInfquirurgicoDxpre != null) {
                    oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListUrgInfquirurgicoDxpre.getUrgInfquirurgicoDxpreList().remove(urgInfquirurgicoDxpreListUrgInfquirurgicoDxpre);
                    oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListUrgInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListUrgInfquirurgicoDxpre);
                }
            }
            for (UrgInfquirurgicoCups urgInfquirurgicoCupsListUrgInfquirurgicoCups : urgInfquirurgico.getUrgInfquirurgicoCupsList()) {
                UrgInfquirurgico oldIdquirurgicoOfUrgInfquirurgicoCupsListUrgInfquirurgicoCups = urgInfquirurgicoCupsListUrgInfquirurgicoCups.getIdquirurgico();
                urgInfquirurgicoCupsListUrgInfquirurgicoCups.setIdquirurgico(urgInfquirurgico);
                urgInfquirurgicoCupsListUrgInfquirurgicoCups = em.merge(urgInfquirurgicoCupsListUrgInfquirurgicoCups);
                if (oldIdquirurgicoOfUrgInfquirurgicoCupsListUrgInfquirurgicoCups != null) {
                    oldIdquirurgicoOfUrgInfquirurgicoCupsListUrgInfquirurgicoCups.getUrgInfquirurgicoCupsList().remove(urgInfquirurgicoCupsListUrgInfquirurgicoCups);
                    oldIdquirurgicoOfUrgInfquirurgicoCupsListUrgInfquirurgicoCups = em.merge(oldIdquirurgicoOfUrgInfquirurgicoCupsListUrgInfquirurgicoCups);
                }
            }
            for (UrgInfquirurgicoDxpost urgInfquirurgicoDxpostListUrgInfquirurgicoDxpost : urgInfquirurgico.getUrgInfquirurgicoDxpostList()) {
                UrgInfquirurgico oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListUrgInfquirurgicoDxpost = urgInfquirurgicoDxpostListUrgInfquirurgicoDxpost.getIdinfquirurgico();
                urgInfquirurgicoDxpostListUrgInfquirurgicoDxpost.setIdinfquirurgico(urgInfquirurgico);
                urgInfquirurgicoDxpostListUrgInfquirurgicoDxpost = em.merge(urgInfquirurgicoDxpostListUrgInfquirurgicoDxpost);
                if (oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListUrgInfquirurgicoDxpost != null) {
                    oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListUrgInfquirurgicoDxpost.getUrgInfquirurgicoDxpostList().remove(urgInfquirurgicoDxpostListUrgInfquirurgicoDxpost);
                    oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListUrgInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListUrgInfquirurgicoDxpost);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgInfquirurgico urgInfquirurgico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgInfquirurgico persistentUrgInfquirurgico = em.find(UrgInfquirurgico.class, urgInfquirurgico.getId());
            List<UrgInfquirurgicoDxpre> urgInfquirurgicoDxpreListOld = persistentUrgInfquirurgico.getUrgInfquirurgicoDxpreList();
            List<UrgInfquirurgicoDxpre> urgInfquirurgicoDxpreListNew = urgInfquirurgico.getUrgInfquirurgicoDxpreList();
            List<UrgInfquirurgicoCups> urgInfquirurgicoCupsListOld = persistentUrgInfquirurgico.getUrgInfquirurgicoCupsList();
            List<UrgInfquirurgicoCups> urgInfquirurgicoCupsListNew = urgInfquirurgico.getUrgInfquirurgicoCupsList();
            List<UrgInfquirurgicoDxpost> urgInfquirurgicoDxpostListOld = persistentUrgInfquirurgico.getUrgInfquirurgicoDxpostList();
            List<UrgInfquirurgicoDxpost> urgInfquirurgicoDxpostListNew = urgInfquirurgico.getUrgInfquirurgicoDxpostList();
            List<String> illegalOrphanMessages = null;
            for (UrgInfquirurgicoDxpre urgInfquirurgicoDxpreListOldUrgInfquirurgicoDxpre : urgInfquirurgicoDxpreListOld) {
                if (!urgInfquirurgicoDxpreListNew.contains(urgInfquirurgicoDxpreListOldUrgInfquirurgicoDxpre)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgInfquirurgicoDxpre " + urgInfquirurgicoDxpreListOldUrgInfquirurgicoDxpre + " since its idinfquirurgico field is not nullable.");
                }
            }
            for (UrgInfquirurgicoCups urgInfquirurgicoCupsListOldUrgInfquirurgicoCups : urgInfquirurgicoCupsListOld) {
                if (!urgInfquirurgicoCupsListNew.contains(urgInfquirurgicoCupsListOldUrgInfquirurgicoCups)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgInfquirurgicoCups " + urgInfquirurgicoCupsListOldUrgInfquirurgicoCups + " since its idquirurgico field is not nullable.");
                }
            }
            for (UrgInfquirurgicoDxpost urgInfquirurgicoDxpostListOldUrgInfquirurgicoDxpost : urgInfquirurgicoDxpostListOld) {
                if (!urgInfquirurgicoDxpostListNew.contains(urgInfquirurgicoDxpostListOldUrgInfquirurgicoDxpost)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgInfquirurgicoDxpost " + urgInfquirurgicoDxpostListOldUrgInfquirurgicoDxpost + " since its idinfquirurgico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgInfquirurgicoDxpre> attachedUrgInfquirurgicoDxpreListNew = new ArrayList<UrgInfquirurgicoDxpre>();
            for (UrgInfquirurgicoDxpre urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpreToAttach : urgInfquirurgicoDxpreListNew) {
                urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpreToAttach = em.getReference(urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpreToAttach.getClass(), urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpreToAttach.getId());
                attachedUrgInfquirurgicoDxpreListNew.add(urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpreToAttach);
            }
            urgInfquirurgicoDxpreListNew = attachedUrgInfquirurgicoDxpreListNew;
            urgInfquirurgico.setUrgInfquirurgicoDxpreList(urgInfquirurgicoDxpreListNew);
            List<UrgInfquirurgicoCups> attachedUrgInfquirurgicoCupsListNew = new ArrayList<UrgInfquirurgicoCups>();
            for (UrgInfquirurgicoCups urgInfquirurgicoCupsListNewUrgInfquirurgicoCupsToAttach : urgInfquirurgicoCupsListNew) {
                urgInfquirurgicoCupsListNewUrgInfquirurgicoCupsToAttach = em.getReference(urgInfquirurgicoCupsListNewUrgInfquirurgicoCupsToAttach.getClass(), urgInfquirurgicoCupsListNewUrgInfquirurgicoCupsToAttach.getId());
                attachedUrgInfquirurgicoCupsListNew.add(urgInfquirurgicoCupsListNewUrgInfquirurgicoCupsToAttach);
            }
            urgInfquirurgicoCupsListNew = attachedUrgInfquirurgicoCupsListNew;
            urgInfquirurgico.setUrgInfquirurgicoCupsList(urgInfquirurgicoCupsListNew);
            List<UrgInfquirurgicoDxpost> attachedUrgInfquirurgicoDxpostListNew = new ArrayList<UrgInfquirurgicoDxpost>();
            for (UrgInfquirurgicoDxpost urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpostToAttach : urgInfquirurgicoDxpostListNew) {
                urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpostToAttach = em.getReference(urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpostToAttach.getClass(), urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpostToAttach.getId());
                attachedUrgInfquirurgicoDxpostListNew.add(urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpostToAttach);
            }
            urgInfquirurgicoDxpostListNew = attachedUrgInfquirurgicoDxpostListNew;
            urgInfquirurgico.setUrgInfquirurgicoDxpostList(urgInfquirurgicoDxpostListNew);
            urgInfquirurgico = em.merge(urgInfquirurgico);
            for (UrgInfquirurgicoDxpre urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre : urgInfquirurgicoDxpreListNew) {
                if (!urgInfquirurgicoDxpreListOld.contains(urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre)) {
                    UrgInfquirurgico oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre = urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre.getIdinfquirurgico();
                    urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre.setIdinfquirurgico(urgInfquirurgico);
                    urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre = em.merge(urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre);
                    if (oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre != null && !oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre.equals(urgInfquirurgico)) {
                        oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre.getUrgInfquirurgicoDxpreList().remove(urgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre);
                        oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfUrgInfquirurgicoDxpreListNewUrgInfquirurgicoDxpre);
                    }
                }
            }
            for (UrgInfquirurgicoCups urgInfquirurgicoCupsListNewUrgInfquirurgicoCups : urgInfquirurgicoCupsListNew) {
                if (!urgInfquirurgicoCupsListOld.contains(urgInfquirurgicoCupsListNewUrgInfquirurgicoCups)) {
                    UrgInfquirurgico oldIdquirurgicoOfUrgInfquirurgicoCupsListNewUrgInfquirurgicoCups = urgInfquirurgicoCupsListNewUrgInfquirurgicoCups.getIdquirurgico();
                    urgInfquirurgicoCupsListNewUrgInfquirurgicoCups.setIdquirurgico(urgInfquirurgico);
                    urgInfquirurgicoCupsListNewUrgInfquirurgicoCups = em.merge(urgInfquirurgicoCupsListNewUrgInfquirurgicoCups);
                    if (oldIdquirurgicoOfUrgInfquirurgicoCupsListNewUrgInfquirurgicoCups != null && !oldIdquirurgicoOfUrgInfquirurgicoCupsListNewUrgInfquirurgicoCups.equals(urgInfquirurgico)) {
                        oldIdquirurgicoOfUrgInfquirurgicoCupsListNewUrgInfquirurgicoCups.getUrgInfquirurgicoCupsList().remove(urgInfquirurgicoCupsListNewUrgInfquirurgicoCups);
                        oldIdquirurgicoOfUrgInfquirurgicoCupsListNewUrgInfquirurgicoCups = em.merge(oldIdquirurgicoOfUrgInfquirurgicoCupsListNewUrgInfquirurgicoCups);
                    }
                }
            }
            for (UrgInfquirurgicoDxpost urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost : urgInfquirurgicoDxpostListNew) {
                if (!urgInfquirurgicoDxpostListOld.contains(urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost)) {
                    UrgInfquirurgico oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost = urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost.getIdinfquirurgico();
                    urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost.setIdinfquirurgico(urgInfquirurgico);
                    urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost = em.merge(urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost);
                    if (oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost != null && !oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost.equals(urgInfquirurgico)) {
                        oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost.getUrgInfquirurgicoDxpostList().remove(urgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost);
                        oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfUrgInfquirurgicoDxpostListNewUrgInfquirurgicoDxpost);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgInfquirurgico.getId();
                if (findUrgInfquirurgico(id) == null) {
                    throw new NonexistentEntityException("The urgInfquirurgico with id " + id + " no longer exists.");
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
            UrgInfquirurgico urgInfquirurgico;
            try {
                urgInfquirurgico = em.getReference(UrgInfquirurgico.class, id);
                urgInfquirurgico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgInfquirurgico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UrgInfquirurgicoDxpre> urgInfquirurgicoDxpreListOrphanCheck = urgInfquirurgico.getUrgInfquirurgicoDxpreList();
            for (UrgInfquirurgicoDxpre urgInfquirurgicoDxpreListOrphanCheckUrgInfquirurgicoDxpre : urgInfquirurgicoDxpreListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgInfquirurgico (" + urgInfquirurgico + ") cannot be destroyed since the UrgInfquirurgicoDxpre " + urgInfquirurgicoDxpreListOrphanCheckUrgInfquirurgicoDxpre + " in its urgInfquirurgicoDxpreList field has a non-nullable idinfquirurgico field.");
            }
            List<UrgInfquirurgicoCups> urgInfquirurgicoCupsListOrphanCheck = urgInfquirurgico.getUrgInfquirurgicoCupsList();
            for (UrgInfquirurgicoCups urgInfquirurgicoCupsListOrphanCheckUrgInfquirurgicoCups : urgInfquirurgicoCupsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgInfquirurgico (" + urgInfquirurgico + ") cannot be destroyed since the UrgInfquirurgicoCups " + urgInfquirurgicoCupsListOrphanCheckUrgInfquirurgicoCups + " in its urgInfquirurgicoCupsList field has a non-nullable idquirurgico field.");
            }
            List<UrgInfquirurgicoDxpost> urgInfquirurgicoDxpostListOrphanCheck = urgInfquirurgico.getUrgInfquirurgicoDxpostList();
            for (UrgInfquirurgicoDxpost urgInfquirurgicoDxpostListOrphanCheckUrgInfquirurgicoDxpost : urgInfquirurgicoDxpostListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgInfquirurgico (" + urgInfquirurgico + ") cannot be destroyed since the UrgInfquirurgicoDxpost " + urgInfquirurgicoDxpostListOrphanCheckUrgInfquirurgicoDxpost + " in its urgInfquirurgicoDxpostList field has a non-nullable idinfquirurgico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(urgInfquirurgico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgInfquirurgico> findUrgInfquirurgicoEntities() {
        return findUrgInfquirurgicoEntities(true, -1, -1);
    }

    public List<UrgInfquirurgico> findUrgInfquirurgicoEntities(int maxResults, int firstResult) {
        return findUrgInfquirurgicoEntities(false, maxResults, firstResult);
    }

    private List<UrgInfquirurgico> findUrgInfquirurgicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgInfquirurgico.class));
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

    public UrgInfquirurgico findUrgInfquirurgico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgInfquirurgico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgInfquirurgicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgInfquirurgico> rt = cq.from(UrgInfquirurgico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
