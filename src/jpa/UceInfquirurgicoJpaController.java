/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.UceInfquirurgico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceInfquirurgicoCups;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UceInfquirurgicoDxpre;
import entidades_EJB.UceInfquirurgicoDxpost;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UceInfquirurgicoJpaController implements Serializable {

    public UceInfquirurgicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceInfquirurgico uceInfquirurgico) {
        if (uceInfquirurgico.getUceInfquirurgicoCupsList() == null) {
            uceInfquirurgico.setUceInfquirurgicoCupsList(new ArrayList<UceInfquirurgicoCups>());
        }
        if (uceInfquirurgico.getUceInfquirurgicoDxpreList() == null) {
            uceInfquirurgico.setUceInfquirurgicoDxpreList(new ArrayList<UceInfquirurgicoDxpre>());
        }
        if (uceInfquirurgico.getUceInfquirurgicoDxpostList() == null) {
            uceInfquirurgico.setUceInfquirurgicoDxpostList(new ArrayList<UceInfquirurgicoDxpost>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceInfquirurgicoCups> attachedUceInfquirurgicoCupsList = new ArrayList<UceInfquirurgicoCups>();
            for (UceInfquirurgicoCups uceInfquirurgicoCupsListUceInfquirurgicoCupsToAttach : uceInfquirurgico.getUceInfquirurgicoCupsList()) {
                uceInfquirurgicoCupsListUceInfquirurgicoCupsToAttach = em.getReference(uceInfquirurgicoCupsListUceInfquirurgicoCupsToAttach.getClass(), uceInfquirurgicoCupsListUceInfquirurgicoCupsToAttach.getId());
                attachedUceInfquirurgicoCupsList.add(uceInfquirurgicoCupsListUceInfquirurgicoCupsToAttach);
            }
            uceInfquirurgico.setUceInfquirurgicoCupsList(attachedUceInfquirurgicoCupsList);
            List<UceInfquirurgicoDxpre> attachedUceInfquirurgicoDxpreList = new ArrayList<UceInfquirurgicoDxpre>();
            for (UceInfquirurgicoDxpre uceInfquirurgicoDxpreListUceInfquirurgicoDxpreToAttach : uceInfquirurgico.getUceInfquirurgicoDxpreList()) {
                uceInfquirurgicoDxpreListUceInfquirurgicoDxpreToAttach = em.getReference(uceInfquirurgicoDxpreListUceInfquirurgicoDxpreToAttach.getClass(), uceInfquirurgicoDxpreListUceInfquirurgicoDxpreToAttach.getId());
                attachedUceInfquirurgicoDxpreList.add(uceInfquirurgicoDxpreListUceInfquirurgicoDxpreToAttach);
            }
            uceInfquirurgico.setUceInfquirurgicoDxpreList(attachedUceInfquirurgicoDxpreList);
            List<UceInfquirurgicoDxpost> attachedUceInfquirurgicoDxpostList = new ArrayList<UceInfquirurgicoDxpost>();
            for (UceInfquirurgicoDxpost uceInfquirurgicoDxpostListUceInfquirurgicoDxpostToAttach : uceInfquirurgico.getUceInfquirurgicoDxpostList()) {
                uceInfquirurgicoDxpostListUceInfquirurgicoDxpostToAttach = em.getReference(uceInfquirurgicoDxpostListUceInfquirurgicoDxpostToAttach.getClass(), uceInfquirurgicoDxpostListUceInfquirurgicoDxpostToAttach.getId());
                attachedUceInfquirurgicoDxpostList.add(uceInfquirurgicoDxpostListUceInfquirurgicoDxpostToAttach);
            }
            uceInfquirurgico.setUceInfquirurgicoDxpostList(attachedUceInfquirurgicoDxpostList);
            em.persist(uceInfquirurgico);
            for (UceInfquirurgicoCups uceInfquirurgicoCupsListUceInfquirurgicoCups : uceInfquirurgico.getUceInfquirurgicoCupsList()) {
                UceInfquirurgico oldIdquirurgicoOfUceInfquirurgicoCupsListUceInfquirurgicoCups = uceInfquirurgicoCupsListUceInfquirurgicoCups.getIdquirurgico();
                uceInfquirurgicoCupsListUceInfquirurgicoCups.setIdquirurgico(uceInfquirurgico);
                uceInfquirurgicoCupsListUceInfquirurgicoCups = em.merge(uceInfquirurgicoCupsListUceInfquirurgicoCups);
                if (oldIdquirurgicoOfUceInfquirurgicoCupsListUceInfquirurgicoCups != null) {
                    oldIdquirurgicoOfUceInfquirurgicoCupsListUceInfquirurgicoCups.getUceInfquirurgicoCupsList().remove(uceInfquirurgicoCupsListUceInfquirurgicoCups);
                    oldIdquirurgicoOfUceInfquirurgicoCupsListUceInfquirurgicoCups = em.merge(oldIdquirurgicoOfUceInfquirurgicoCupsListUceInfquirurgicoCups);
                }
            }
            for (UceInfquirurgicoDxpre uceInfquirurgicoDxpreListUceInfquirurgicoDxpre : uceInfquirurgico.getUceInfquirurgicoDxpreList()) {
                UceInfquirurgico oldIdinfquirurgicoOfUceInfquirurgicoDxpreListUceInfquirurgicoDxpre = uceInfquirurgicoDxpreListUceInfquirurgicoDxpre.getIdinfquirurgico();
                uceInfquirurgicoDxpreListUceInfquirurgicoDxpre.setIdinfquirurgico(uceInfquirurgico);
                uceInfquirurgicoDxpreListUceInfquirurgicoDxpre = em.merge(uceInfquirurgicoDxpreListUceInfquirurgicoDxpre);
                if (oldIdinfquirurgicoOfUceInfquirurgicoDxpreListUceInfquirurgicoDxpre != null) {
                    oldIdinfquirurgicoOfUceInfquirurgicoDxpreListUceInfquirurgicoDxpre.getUceInfquirurgicoDxpreList().remove(uceInfquirurgicoDxpreListUceInfquirurgicoDxpre);
                    oldIdinfquirurgicoOfUceInfquirurgicoDxpreListUceInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfUceInfquirurgicoDxpreListUceInfquirurgicoDxpre);
                }
            }
            for (UceInfquirurgicoDxpost uceInfquirurgicoDxpostListUceInfquirurgicoDxpost : uceInfquirurgico.getUceInfquirurgicoDxpostList()) {
                UceInfquirurgico oldIdinfquirurgicoOfUceInfquirurgicoDxpostListUceInfquirurgicoDxpost = uceInfquirurgicoDxpostListUceInfquirurgicoDxpost.getIdinfquirurgico();
                uceInfquirurgicoDxpostListUceInfquirurgicoDxpost.setIdinfquirurgico(uceInfquirurgico);
                uceInfquirurgicoDxpostListUceInfquirurgicoDxpost = em.merge(uceInfquirurgicoDxpostListUceInfquirurgicoDxpost);
                if (oldIdinfquirurgicoOfUceInfquirurgicoDxpostListUceInfquirurgicoDxpost != null) {
                    oldIdinfquirurgicoOfUceInfquirurgicoDxpostListUceInfquirurgicoDxpost.getUceInfquirurgicoDxpostList().remove(uceInfquirurgicoDxpostListUceInfquirurgicoDxpost);
                    oldIdinfquirurgicoOfUceInfquirurgicoDxpostListUceInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfUceInfquirurgicoDxpostListUceInfquirurgicoDxpost);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceInfquirurgico uceInfquirurgico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceInfquirurgico persistentUceInfquirurgico = em.find(UceInfquirurgico.class, uceInfquirurgico.getId());
            List<UceInfquirurgicoCups> uceInfquirurgicoCupsListOld = persistentUceInfquirurgico.getUceInfquirurgicoCupsList();
            List<UceInfquirurgicoCups> uceInfquirurgicoCupsListNew = uceInfquirurgico.getUceInfquirurgicoCupsList();
            List<UceInfquirurgicoDxpre> uceInfquirurgicoDxpreListOld = persistentUceInfquirurgico.getUceInfquirurgicoDxpreList();
            List<UceInfquirurgicoDxpre> uceInfquirurgicoDxpreListNew = uceInfquirurgico.getUceInfquirurgicoDxpreList();
            List<UceInfquirurgicoDxpost> uceInfquirurgicoDxpostListOld = persistentUceInfquirurgico.getUceInfquirurgicoDxpostList();
            List<UceInfquirurgicoDxpost> uceInfquirurgicoDxpostListNew = uceInfquirurgico.getUceInfquirurgicoDxpostList();
            List<String> illegalOrphanMessages = null;
            for (UceInfquirurgicoCups uceInfquirurgicoCupsListOldUceInfquirurgicoCups : uceInfquirurgicoCupsListOld) {
                if (!uceInfquirurgicoCupsListNew.contains(uceInfquirurgicoCupsListOldUceInfquirurgicoCups)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceInfquirurgicoCups " + uceInfquirurgicoCupsListOldUceInfquirurgicoCups + " since its idquirurgico field is not nullable.");
                }
            }
            for (UceInfquirurgicoDxpre uceInfquirurgicoDxpreListOldUceInfquirurgicoDxpre : uceInfquirurgicoDxpreListOld) {
                if (!uceInfquirurgicoDxpreListNew.contains(uceInfquirurgicoDxpreListOldUceInfquirurgicoDxpre)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceInfquirurgicoDxpre " + uceInfquirurgicoDxpreListOldUceInfquirurgicoDxpre + " since its idinfquirurgico field is not nullable.");
                }
            }
            for (UceInfquirurgicoDxpost uceInfquirurgicoDxpostListOldUceInfquirurgicoDxpost : uceInfquirurgicoDxpostListOld) {
                if (!uceInfquirurgicoDxpostListNew.contains(uceInfquirurgicoDxpostListOldUceInfquirurgicoDxpost)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceInfquirurgicoDxpost " + uceInfquirurgicoDxpostListOldUceInfquirurgicoDxpost + " since its idinfquirurgico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceInfquirurgicoCups> attachedUceInfquirurgicoCupsListNew = new ArrayList<UceInfquirurgicoCups>();
            for (UceInfquirurgicoCups uceInfquirurgicoCupsListNewUceInfquirurgicoCupsToAttach : uceInfquirurgicoCupsListNew) {
                uceInfquirurgicoCupsListNewUceInfquirurgicoCupsToAttach = em.getReference(uceInfquirurgicoCupsListNewUceInfquirurgicoCupsToAttach.getClass(), uceInfquirurgicoCupsListNewUceInfquirurgicoCupsToAttach.getId());
                attachedUceInfquirurgicoCupsListNew.add(uceInfquirurgicoCupsListNewUceInfquirurgicoCupsToAttach);
            }
            uceInfquirurgicoCupsListNew = attachedUceInfquirurgicoCupsListNew;
            uceInfquirurgico.setUceInfquirurgicoCupsList(uceInfquirurgicoCupsListNew);
            List<UceInfquirurgicoDxpre> attachedUceInfquirurgicoDxpreListNew = new ArrayList<UceInfquirurgicoDxpre>();
            for (UceInfquirurgicoDxpre uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpreToAttach : uceInfquirurgicoDxpreListNew) {
                uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpreToAttach = em.getReference(uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpreToAttach.getClass(), uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpreToAttach.getId());
                attachedUceInfquirurgicoDxpreListNew.add(uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpreToAttach);
            }
            uceInfquirurgicoDxpreListNew = attachedUceInfquirurgicoDxpreListNew;
            uceInfquirurgico.setUceInfquirurgicoDxpreList(uceInfquirurgicoDxpreListNew);
            List<UceInfquirurgicoDxpost> attachedUceInfquirurgicoDxpostListNew = new ArrayList<UceInfquirurgicoDxpost>();
            for (UceInfquirurgicoDxpost uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpostToAttach : uceInfquirurgicoDxpostListNew) {
                uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpostToAttach = em.getReference(uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpostToAttach.getClass(), uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpostToAttach.getId());
                attachedUceInfquirurgicoDxpostListNew.add(uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpostToAttach);
            }
            uceInfquirurgicoDxpostListNew = attachedUceInfquirurgicoDxpostListNew;
            uceInfquirurgico.setUceInfquirurgicoDxpostList(uceInfquirurgicoDxpostListNew);
            uceInfquirurgico = em.merge(uceInfquirurgico);
            for (UceInfquirurgicoCups uceInfquirurgicoCupsListNewUceInfquirurgicoCups : uceInfquirurgicoCupsListNew) {
                if (!uceInfquirurgicoCupsListOld.contains(uceInfquirurgicoCupsListNewUceInfquirurgicoCups)) {
                    UceInfquirurgico oldIdquirurgicoOfUceInfquirurgicoCupsListNewUceInfquirurgicoCups = uceInfquirurgicoCupsListNewUceInfquirurgicoCups.getIdquirurgico();
                    uceInfquirurgicoCupsListNewUceInfquirurgicoCups.setIdquirurgico(uceInfquirurgico);
                    uceInfquirurgicoCupsListNewUceInfquirurgicoCups = em.merge(uceInfquirurgicoCupsListNewUceInfquirurgicoCups);
                    if (oldIdquirurgicoOfUceInfquirurgicoCupsListNewUceInfquirurgicoCups != null && !oldIdquirurgicoOfUceInfquirurgicoCupsListNewUceInfquirurgicoCups.equals(uceInfquirurgico)) {
                        oldIdquirurgicoOfUceInfquirurgicoCupsListNewUceInfquirurgicoCups.getUceInfquirurgicoCupsList().remove(uceInfquirurgicoCupsListNewUceInfquirurgicoCups);
                        oldIdquirurgicoOfUceInfquirurgicoCupsListNewUceInfquirurgicoCups = em.merge(oldIdquirurgicoOfUceInfquirurgicoCupsListNewUceInfquirurgicoCups);
                    }
                }
            }
            for (UceInfquirurgicoDxpre uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre : uceInfquirurgicoDxpreListNew) {
                if (!uceInfquirurgicoDxpreListOld.contains(uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre)) {
                    UceInfquirurgico oldIdinfquirurgicoOfUceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre = uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre.getIdinfquirurgico();
                    uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre.setIdinfquirurgico(uceInfquirurgico);
                    uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre = em.merge(uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre);
                    if (oldIdinfquirurgicoOfUceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre != null && !oldIdinfquirurgicoOfUceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre.equals(uceInfquirurgico)) {
                        oldIdinfquirurgicoOfUceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre.getUceInfquirurgicoDxpreList().remove(uceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre);
                        oldIdinfquirurgicoOfUceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfUceInfquirurgicoDxpreListNewUceInfquirurgicoDxpre);
                    }
                }
            }
            for (UceInfquirurgicoDxpost uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost : uceInfquirurgicoDxpostListNew) {
                if (!uceInfquirurgicoDxpostListOld.contains(uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost)) {
                    UceInfquirurgico oldIdinfquirurgicoOfUceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost = uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost.getIdinfquirurgico();
                    uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost.setIdinfquirurgico(uceInfquirurgico);
                    uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost = em.merge(uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost);
                    if (oldIdinfquirurgicoOfUceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost != null && !oldIdinfquirurgicoOfUceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost.equals(uceInfquirurgico)) {
                        oldIdinfquirurgicoOfUceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost.getUceInfquirurgicoDxpostList().remove(uceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost);
                        oldIdinfquirurgicoOfUceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfUceInfquirurgicoDxpostListNewUceInfquirurgicoDxpost);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceInfquirurgico.getId();
                if (findUceInfquirurgico(id) == null) {
                    throw new NonexistentEntityException("The uceInfquirurgico with id " + id + " no longer exists.");
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
            UceInfquirurgico uceInfquirurgico;
            try {
                uceInfquirurgico = em.getReference(UceInfquirurgico.class, id);
                uceInfquirurgico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceInfquirurgico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceInfquirurgicoCups> uceInfquirurgicoCupsListOrphanCheck = uceInfquirurgico.getUceInfquirurgicoCupsList();
            for (UceInfquirurgicoCups uceInfquirurgicoCupsListOrphanCheckUceInfquirurgicoCups : uceInfquirurgicoCupsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceInfquirurgico (" + uceInfquirurgico + ") cannot be destroyed since the UceInfquirurgicoCups " + uceInfquirurgicoCupsListOrphanCheckUceInfquirurgicoCups + " in its uceInfquirurgicoCupsList field has a non-nullable idquirurgico field.");
            }
            List<UceInfquirurgicoDxpre> uceInfquirurgicoDxpreListOrphanCheck = uceInfquirurgico.getUceInfquirurgicoDxpreList();
            for (UceInfquirurgicoDxpre uceInfquirurgicoDxpreListOrphanCheckUceInfquirurgicoDxpre : uceInfquirurgicoDxpreListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceInfquirurgico (" + uceInfquirurgico + ") cannot be destroyed since the UceInfquirurgicoDxpre " + uceInfquirurgicoDxpreListOrphanCheckUceInfquirurgicoDxpre + " in its uceInfquirurgicoDxpreList field has a non-nullable idinfquirurgico field.");
            }
            List<UceInfquirurgicoDxpost> uceInfquirurgicoDxpostListOrphanCheck = uceInfquirurgico.getUceInfquirurgicoDxpostList();
            for (UceInfquirurgicoDxpost uceInfquirurgicoDxpostListOrphanCheckUceInfquirurgicoDxpost : uceInfquirurgicoDxpostListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceInfquirurgico (" + uceInfquirurgico + ") cannot be destroyed since the UceInfquirurgicoDxpost " + uceInfquirurgicoDxpostListOrphanCheckUceInfquirurgicoDxpost + " in its uceInfquirurgicoDxpostList field has a non-nullable idinfquirurgico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceInfquirurgico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceInfquirurgico> findUceInfquirurgicoEntities() {
        return findUceInfquirurgicoEntities(true, -1, -1);
    }

    public List<UceInfquirurgico> findUceInfquirurgicoEntities(int maxResults, int firstResult) {
        return findUceInfquirurgicoEntities(false, maxResults, firstResult);
    }

    private List<UceInfquirurgico> findUceInfquirurgicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceInfquirurgico.class));
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

    public UceInfquirurgico findUceInfquirurgico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceInfquirurgico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceInfquirurgicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceInfquirurgico> rt = cq.from(UceInfquirurgico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
