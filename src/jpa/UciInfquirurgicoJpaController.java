/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.UciInfquirurgico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciInfquirurgicoCups;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciInfquirurgicoDxpost;
import entidades_EJB.UciInfquirurgicoDxpre;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class UciInfquirurgicoJpaController implements Serializable {

    public UciInfquirurgicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciInfquirurgico uciInfquirurgico) {
        if (uciInfquirurgico.getUciInfquirurgicoCupsList() == null) {
            uciInfquirurgico.setUciInfquirurgicoCupsList(new ArrayList<UciInfquirurgicoCups>());
        }
        if (uciInfquirurgico.getUciInfquirurgicoDxpostList() == null) {
            uciInfquirurgico.setUciInfquirurgicoDxpostList(new ArrayList<UciInfquirurgicoDxpost>());
        }
        if (uciInfquirurgico.getUciInfquirurgicoDxpreList() == null) {
            uciInfquirurgico.setUciInfquirurgicoDxpreList(new ArrayList<UciInfquirurgicoDxpre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciInfquirurgicoCups> attachedUciInfquirurgicoCupsList = new ArrayList<UciInfquirurgicoCups>();
            for (UciInfquirurgicoCups uciInfquirurgicoCupsListUciInfquirurgicoCupsToAttach : uciInfquirurgico.getUciInfquirurgicoCupsList()) {
                uciInfquirurgicoCupsListUciInfquirurgicoCupsToAttach = em.getReference(uciInfquirurgicoCupsListUciInfquirurgicoCupsToAttach.getClass(), uciInfquirurgicoCupsListUciInfquirurgicoCupsToAttach.getId());
                attachedUciInfquirurgicoCupsList.add(uciInfquirurgicoCupsListUciInfquirurgicoCupsToAttach);
            }
            uciInfquirurgico.setUciInfquirurgicoCupsList(attachedUciInfquirurgicoCupsList);
            List<UciInfquirurgicoDxpost> attachedUciInfquirurgicoDxpostList = new ArrayList<UciInfquirurgicoDxpost>();
            for (UciInfquirurgicoDxpost uciInfquirurgicoDxpostListUciInfquirurgicoDxpostToAttach : uciInfquirurgico.getUciInfquirurgicoDxpostList()) {
                uciInfquirurgicoDxpostListUciInfquirurgicoDxpostToAttach = em.getReference(uciInfquirurgicoDxpostListUciInfquirurgicoDxpostToAttach.getClass(), uciInfquirurgicoDxpostListUciInfquirurgicoDxpostToAttach.getId());
                attachedUciInfquirurgicoDxpostList.add(uciInfquirurgicoDxpostListUciInfquirurgicoDxpostToAttach);
            }
            uciInfquirurgico.setUciInfquirurgicoDxpostList(attachedUciInfquirurgicoDxpostList);
            List<UciInfquirurgicoDxpre> attachedUciInfquirurgicoDxpreList = new ArrayList<UciInfquirurgicoDxpre>();
            for (UciInfquirurgicoDxpre uciInfquirurgicoDxpreListUciInfquirurgicoDxpreToAttach : uciInfquirurgico.getUciInfquirurgicoDxpreList()) {
                uciInfquirurgicoDxpreListUciInfquirurgicoDxpreToAttach = em.getReference(uciInfquirurgicoDxpreListUciInfquirurgicoDxpreToAttach.getClass(), uciInfquirurgicoDxpreListUciInfquirurgicoDxpreToAttach.getId());
                attachedUciInfquirurgicoDxpreList.add(uciInfquirurgicoDxpreListUciInfquirurgicoDxpreToAttach);
            }
            uciInfquirurgico.setUciInfquirurgicoDxpreList(attachedUciInfquirurgicoDxpreList);
            em.persist(uciInfquirurgico);
            for (UciInfquirurgicoCups uciInfquirurgicoCupsListUciInfquirurgicoCups : uciInfquirurgico.getUciInfquirurgicoCupsList()) {
                UciInfquirurgico oldIdquirurgicoOfUciInfquirurgicoCupsListUciInfquirurgicoCups = uciInfquirurgicoCupsListUciInfquirurgicoCups.getIdquirurgico();
                uciInfquirurgicoCupsListUciInfquirurgicoCups.setIdquirurgico(uciInfquirurgico);
                uciInfquirurgicoCupsListUciInfquirurgicoCups = em.merge(uciInfquirurgicoCupsListUciInfquirurgicoCups);
                if (oldIdquirurgicoOfUciInfquirurgicoCupsListUciInfquirurgicoCups != null) {
                    oldIdquirurgicoOfUciInfquirurgicoCupsListUciInfquirurgicoCups.getUciInfquirurgicoCupsList().remove(uciInfquirurgicoCupsListUciInfquirurgicoCups);
                    oldIdquirurgicoOfUciInfquirurgicoCupsListUciInfquirurgicoCups = em.merge(oldIdquirurgicoOfUciInfquirurgicoCupsListUciInfquirurgicoCups);
                }
            }
            for (UciInfquirurgicoDxpost uciInfquirurgicoDxpostListUciInfquirurgicoDxpost : uciInfquirurgico.getUciInfquirurgicoDxpostList()) {
                UciInfquirurgico oldIdinfquirurgicoOfUciInfquirurgicoDxpostListUciInfquirurgicoDxpost = uciInfquirurgicoDxpostListUciInfquirurgicoDxpost.getIdinfquirurgico();
                uciInfquirurgicoDxpostListUciInfquirurgicoDxpost.setIdinfquirurgico(uciInfquirurgico);
                uciInfquirurgicoDxpostListUciInfquirurgicoDxpost = em.merge(uciInfquirurgicoDxpostListUciInfquirurgicoDxpost);
                if (oldIdinfquirurgicoOfUciInfquirurgicoDxpostListUciInfquirurgicoDxpost != null) {
                    oldIdinfquirurgicoOfUciInfquirurgicoDxpostListUciInfquirurgicoDxpost.getUciInfquirurgicoDxpostList().remove(uciInfquirurgicoDxpostListUciInfquirurgicoDxpost);
                    oldIdinfquirurgicoOfUciInfquirurgicoDxpostListUciInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfUciInfquirurgicoDxpostListUciInfquirurgicoDxpost);
                }
            }
            for (UciInfquirurgicoDxpre uciInfquirurgicoDxpreListUciInfquirurgicoDxpre : uciInfquirurgico.getUciInfquirurgicoDxpreList()) {
                UciInfquirurgico oldIdinfquirurgicoOfUciInfquirurgicoDxpreListUciInfquirurgicoDxpre = uciInfquirurgicoDxpreListUciInfquirurgicoDxpre.getIdinfquirurgico();
                uciInfquirurgicoDxpreListUciInfquirurgicoDxpre.setIdinfquirurgico(uciInfquirurgico);
                uciInfquirurgicoDxpreListUciInfquirurgicoDxpre = em.merge(uciInfquirurgicoDxpreListUciInfquirurgicoDxpre);
                if (oldIdinfquirurgicoOfUciInfquirurgicoDxpreListUciInfquirurgicoDxpre != null) {
                    oldIdinfquirurgicoOfUciInfquirurgicoDxpreListUciInfquirurgicoDxpre.getUciInfquirurgicoDxpreList().remove(uciInfquirurgicoDxpreListUciInfquirurgicoDxpre);
                    oldIdinfquirurgicoOfUciInfquirurgicoDxpreListUciInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfUciInfquirurgicoDxpreListUciInfquirurgicoDxpre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciInfquirurgico uciInfquirurgico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciInfquirurgico persistentUciInfquirurgico = em.find(UciInfquirurgico.class, uciInfquirurgico.getId());
            List<UciInfquirurgicoCups> uciInfquirurgicoCupsListOld = persistentUciInfquirurgico.getUciInfquirurgicoCupsList();
            List<UciInfquirurgicoCups> uciInfquirurgicoCupsListNew = uciInfquirurgico.getUciInfquirurgicoCupsList();
            List<UciInfquirurgicoDxpost> uciInfquirurgicoDxpostListOld = persistentUciInfquirurgico.getUciInfquirurgicoDxpostList();
            List<UciInfquirurgicoDxpost> uciInfquirurgicoDxpostListNew = uciInfquirurgico.getUciInfquirurgicoDxpostList();
            List<UciInfquirurgicoDxpre> uciInfquirurgicoDxpreListOld = persistentUciInfquirurgico.getUciInfquirurgicoDxpreList();
            List<UciInfquirurgicoDxpre> uciInfquirurgicoDxpreListNew = uciInfquirurgico.getUciInfquirurgicoDxpreList();
            List<String> illegalOrphanMessages = null;
            for (UciInfquirurgicoCups uciInfquirurgicoCupsListOldUciInfquirurgicoCups : uciInfquirurgicoCupsListOld) {
                if (!uciInfquirurgicoCupsListNew.contains(uciInfquirurgicoCupsListOldUciInfquirurgicoCups)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciInfquirurgicoCups " + uciInfquirurgicoCupsListOldUciInfquirurgicoCups + " since its idquirurgico field is not nullable.");
                }
            }
            for (UciInfquirurgicoDxpost uciInfquirurgicoDxpostListOldUciInfquirurgicoDxpost : uciInfquirurgicoDxpostListOld) {
                if (!uciInfquirurgicoDxpostListNew.contains(uciInfquirurgicoDxpostListOldUciInfquirurgicoDxpost)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciInfquirurgicoDxpost " + uciInfquirurgicoDxpostListOldUciInfquirurgicoDxpost + " since its idinfquirurgico field is not nullable.");
                }
            }
            for (UciInfquirurgicoDxpre uciInfquirurgicoDxpreListOldUciInfquirurgicoDxpre : uciInfquirurgicoDxpreListOld) {
                if (!uciInfquirurgicoDxpreListNew.contains(uciInfquirurgicoDxpreListOldUciInfquirurgicoDxpre)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciInfquirurgicoDxpre " + uciInfquirurgicoDxpreListOldUciInfquirurgicoDxpre + " since its idinfquirurgico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciInfquirurgicoCups> attachedUciInfquirurgicoCupsListNew = new ArrayList<UciInfquirurgicoCups>();
            for (UciInfquirurgicoCups uciInfquirurgicoCupsListNewUciInfquirurgicoCupsToAttach : uciInfquirurgicoCupsListNew) {
                uciInfquirurgicoCupsListNewUciInfquirurgicoCupsToAttach = em.getReference(uciInfquirurgicoCupsListNewUciInfquirurgicoCupsToAttach.getClass(), uciInfquirurgicoCupsListNewUciInfquirurgicoCupsToAttach.getId());
                attachedUciInfquirurgicoCupsListNew.add(uciInfquirurgicoCupsListNewUciInfquirurgicoCupsToAttach);
            }
            uciInfquirurgicoCupsListNew = attachedUciInfquirurgicoCupsListNew;
            uciInfquirurgico.setUciInfquirurgicoCupsList(uciInfquirurgicoCupsListNew);
            List<UciInfquirurgicoDxpost> attachedUciInfquirurgicoDxpostListNew = new ArrayList<UciInfquirurgicoDxpost>();
            for (UciInfquirurgicoDxpost uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpostToAttach : uciInfquirurgicoDxpostListNew) {
                uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpostToAttach = em.getReference(uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpostToAttach.getClass(), uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpostToAttach.getId());
                attachedUciInfquirurgicoDxpostListNew.add(uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpostToAttach);
            }
            uciInfquirurgicoDxpostListNew = attachedUciInfquirurgicoDxpostListNew;
            uciInfquirurgico.setUciInfquirurgicoDxpostList(uciInfquirurgicoDxpostListNew);
            List<UciInfquirurgicoDxpre> attachedUciInfquirurgicoDxpreListNew = new ArrayList<UciInfquirurgicoDxpre>();
            for (UciInfquirurgicoDxpre uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpreToAttach : uciInfquirurgicoDxpreListNew) {
                uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpreToAttach = em.getReference(uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpreToAttach.getClass(), uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpreToAttach.getId());
                attachedUciInfquirurgicoDxpreListNew.add(uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpreToAttach);
            }
            uciInfquirurgicoDxpreListNew = attachedUciInfquirurgicoDxpreListNew;
            uciInfquirurgico.setUciInfquirurgicoDxpreList(uciInfquirurgicoDxpreListNew);
            uciInfquirurgico = em.merge(uciInfquirurgico);
            for (UciInfquirurgicoCups uciInfquirurgicoCupsListNewUciInfquirurgicoCups : uciInfquirurgicoCupsListNew) {
                if (!uciInfquirurgicoCupsListOld.contains(uciInfquirurgicoCupsListNewUciInfquirurgicoCups)) {
                    UciInfquirurgico oldIdquirurgicoOfUciInfquirurgicoCupsListNewUciInfquirurgicoCups = uciInfquirurgicoCupsListNewUciInfquirurgicoCups.getIdquirurgico();
                    uciInfquirurgicoCupsListNewUciInfquirurgicoCups.setIdquirurgico(uciInfquirurgico);
                    uciInfquirurgicoCupsListNewUciInfquirurgicoCups = em.merge(uciInfquirurgicoCupsListNewUciInfquirurgicoCups);
                    if (oldIdquirurgicoOfUciInfquirurgicoCupsListNewUciInfquirurgicoCups != null && !oldIdquirurgicoOfUciInfquirurgicoCupsListNewUciInfquirurgicoCups.equals(uciInfquirurgico)) {
                        oldIdquirurgicoOfUciInfquirurgicoCupsListNewUciInfquirurgicoCups.getUciInfquirurgicoCupsList().remove(uciInfquirurgicoCupsListNewUciInfquirurgicoCups);
                        oldIdquirurgicoOfUciInfquirurgicoCupsListNewUciInfquirurgicoCups = em.merge(oldIdquirurgicoOfUciInfquirurgicoCupsListNewUciInfquirurgicoCups);
                    }
                }
            }
            for (UciInfquirurgicoDxpost uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost : uciInfquirurgicoDxpostListNew) {
                if (!uciInfquirurgicoDxpostListOld.contains(uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost)) {
                    UciInfquirurgico oldIdinfquirurgicoOfUciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost = uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost.getIdinfquirurgico();
                    uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost.setIdinfquirurgico(uciInfquirurgico);
                    uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost = em.merge(uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost);
                    if (oldIdinfquirurgicoOfUciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost != null && !oldIdinfquirurgicoOfUciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost.equals(uciInfquirurgico)) {
                        oldIdinfquirurgicoOfUciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost.getUciInfquirurgicoDxpostList().remove(uciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost);
                        oldIdinfquirurgicoOfUciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfUciInfquirurgicoDxpostListNewUciInfquirurgicoDxpost);
                    }
                }
            }
            for (UciInfquirurgicoDxpre uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre : uciInfquirurgicoDxpreListNew) {
                if (!uciInfquirurgicoDxpreListOld.contains(uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre)) {
                    UciInfquirurgico oldIdinfquirurgicoOfUciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre = uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre.getIdinfquirurgico();
                    uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre.setIdinfquirurgico(uciInfquirurgico);
                    uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre = em.merge(uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre);
                    if (oldIdinfquirurgicoOfUciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre != null && !oldIdinfquirurgicoOfUciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre.equals(uciInfquirurgico)) {
                        oldIdinfquirurgicoOfUciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre.getUciInfquirurgicoDxpreList().remove(uciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre);
                        oldIdinfquirurgicoOfUciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfUciInfquirurgicoDxpreListNewUciInfquirurgicoDxpre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciInfquirurgico.getId();
                if (findUciInfquirurgico(id) == null) {
                    throw new NonexistentEntityException("The uciInfquirurgico with id " + id + " no longer exists.");
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
            UciInfquirurgico uciInfquirurgico;
            try {
                uciInfquirurgico = em.getReference(UciInfquirurgico.class, id);
                uciInfquirurgico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciInfquirurgico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciInfquirurgicoCups> uciInfquirurgicoCupsListOrphanCheck = uciInfquirurgico.getUciInfquirurgicoCupsList();
            for (UciInfquirurgicoCups uciInfquirurgicoCupsListOrphanCheckUciInfquirurgicoCups : uciInfquirurgicoCupsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciInfquirurgico (" + uciInfquirurgico + ") cannot be destroyed since the UciInfquirurgicoCups " + uciInfquirurgicoCupsListOrphanCheckUciInfquirurgicoCups + " in its uciInfquirurgicoCupsList field has a non-nullable idquirurgico field.");
            }
            List<UciInfquirurgicoDxpost> uciInfquirurgicoDxpostListOrphanCheck = uciInfquirurgico.getUciInfquirurgicoDxpostList();
            for (UciInfquirurgicoDxpost uciInfquirurgicoDxpostListOrphanCheckUciInfquirurgicoDxpost : uciInfquirurgicoDxpostListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciInfquirurgico (" + uciInfquirurgico + ") cannot be destroyed since the UciInfquirurgicoDxpost " + uciInfquirurgicoDxpostListOrphanCheckUciInfquirurgicoDxpost + " in its uciInfquirurgicoDxpostList field has a non-nullable idinfquirurgico field.");
            }
            List<UciInfquirurgicoDxpre> uciInfquirurgicoDxpreListOrphanCheck = uciInfquirurgico.getUciInfquirurgicoDxpreList();
            for (UciInfquirurgicoDxpre uciInfquirurgicoDxpreListOrphanCheckUciInfquirurgicoDxpre : uciInfquirurgicoDxpreListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciInfquirurgico (" + uciInfquirurgico + ") cannot be destroyed since the UciInfquirurgicoDxpre " + uciInfquirurgicoDxpreListOrphanCheckUciInfquirurgicoDxpre + " in its uciInfquirurgicoDxpreList field has a non-nullable idinfquirurgico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciInfquirurgico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciInfquirurgico> findUciInfquirurgicoEntities() {
        return findUciInfquirurgicoEntities(true, -1, -1);
    }

    public List<UciInfquirurgico> findUciInfquirurgicoEntities(int maxResults, int firstResult) {
        return findUciInfquirurgicoEntities(false, maxResults, firstResult);
    }

    private List<UciInfquirurgico> findUciInfquirurgicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciInfquirurgico.class));
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

    public UciInfquirurgico findUciInfquirurgico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciInfquirurgico.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciInfquirurgicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciInfquirurgico> rt = cq.from(UciInfquirurgico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
