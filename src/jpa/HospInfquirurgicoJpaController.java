/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import entidades_EJB.HospInfquirurgico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospInfquirurgicoCups;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospInfquirurgicoDxpost;
import entidades_EJB.HospInfquirurgicoDxpre;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author IdlhDeveloper
 */
public class HospInfquirurgicoJpaController implements Serializable {

    public HospInfquirurgicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospInfquirurgico hospInfquirurgico) {
        if (hospInfquirurgico.getHospInfquirurgicoCupsList() == null) {
            hospInfquirurgico.setHospInfquirurgicoCupsList(new ArrayList<HospInfquirurgicoCups>());
        }
        if (hospInfquirurgico.getHospInfquirurgicoDxpostList() == null) {
            hospInfquirurgico.setHospInfquirurgicoDxpostList(new ArrayList<HospInfquirurgicoDxpost>());
        }
        if (hospInfquirurgico.getHospInfquirurgicoDxpreList() == null) {
            hospInfquirurgico.setHospInfquirurgicoDxpreList(new ArrayList<HospInfquirurgicoDxpre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospInfquirurgicoCups> attachedHospInfquirurgicoCupsList = new ArrayList<HospInfquirurgicoCups>();
            for (HospInfquirurgicoCups hospInfquirurgicoCupsListHospInfquirurgicoCupsToAttach : hospInfquirurgico.getHospInfquirurgicoCupsList()) {
                hospInfquirurgicoCupsListHospInfquirurgicoCupsToAttach = em.getReference(hospInfquirurgicoCupsListHospInfquirurgicoCupsToAttach.getClass(), hospInfquirurgicoCupsListHospInfquirurgicoCupsToAttach.getId());
                attachedHospInfquirurgicoCupsList.add(hospInfquirurgicoCupsListHospInfquirurgicoCupsToAttach);
            }
            hospInfquirurgico.setHospInfquirurgicoCupsList(attachedHospInfquirurgicoCupsList);
            List<HospInfquirurgicoDxpost> attachedHospInfquirurgicoDxpostList = new ArrayList<HospInfquirurgicoDxpost>();
            for (HospInfquirurgicoDxpost hospInfquirurgicoDxpostListHospInfquirurgicoDxpostToAttach : hospInfquirurgico.getHospInfquirurgicoDxpostList()) {
                hospInfquirurgicoDxpostListHospInfquirurgicoDxpostToAttach = em.getReference(hospInfquirurgicoDxpostListHospInfquirurgicoDxpostToAttach.getClass(), hospInfquirurgicoDxpostListHospInfquirurgicoDxpostToAttach.getId());
                attachedHospInfquirurgicoDxpostList.add(hospInfquirurgicoDxpostListHospInfquirurgicoDxpostToAttach);
            }
            hospInfquirurgico.setHospInfquirurgicoDxpostList(attachedHospInfquirurgicoDxpostList);
            List<HospInfquirurgicoDxpre> attachedHospInfquirurgicoDxpreList = new ArrayList<HospInfquirurgicoDxpre>();
            for (HospInfquirurgicoDxpre hospInfquirurgicoDxpreListHospInfquirurgicoDxpreToAttach : hospInfquirurgico.getHospInfquirurgicoDxpreList()) {
                hospInfquirurgicoDxpreListHospInfquirurgicoDxpreToAttach = em.getReference(hospInfquirurgicoDxpreListHospInfquirurgicoDxpreToAttach.getClass(), hospInfquirurgicoDxpreListHospInfquirurgicoDxpreToAttach.getId());
                attachedHospInfquirurgicoDxpreList.add(hospInfquirurgicoDxpreListHospInfquirurgicoDxpreToAttach);
            }
            hospInfquirurgico.setHospInfquirurgicoDxpreList(attachedHospInfquirurgicoDxpreList);
            em.persist(hospInfquirurgico);
            for (HospInfquirurgicoCups hospInfquirurgicoCupsListHospInfquirurgicoCups : hospInfquirurgico.getHospInfquirurgicoCupsList()) {
                HospInfquirurgico oldIdquirurgicoOfHospInfquirurgicoCupsListHospInfquirurgicoCups = hospInfquirurgicoCupsListHospInfquirurgicoCups.getIdquirurgico();
                hospInfquirurgicoCupsListHospInfquirurgicoCups.setIdquirurgico(hospInfquirurgico);
                hospInfquirurgicoCupsListHospInfquirurgicoCups = em.merge(hospInfquirurgicoCupsListHospInfquirurgicoCups);
                if (oldIdquirurgicoOfHospInfquirurgicoCupsListHospInfquirurgicoCups != null) {
                    oldIdquirurgicoOfHospInfquirurgicoCupsListHospInfquirurgicoCups.getHospInfquirurgicoCupsList().remove(hospInfquirurgicoCupsListHospInfquirurgicoCups);
                    oldIdquirurgicoOfHospInfquirurgicoCupsListHospInfquirurgicoCups = em.merge(oldIdquirurgicoOfHospInfquirurgicoCupsListHospInfquirurgicoCups);
                }
            }
            for (HospInfquirurgicoDxpost hospInfquirurgicoDxpostListHospInfquirurgicoDxpost : hospInfquirurgico.getHospInfquirurgicoDxpostList()) {
                HospInfquirurgico oldIdinfquirurgicoOfHospInfquirurgicoDxpostListHospInfquirurgicoDxpost = hospInfquirurgicoDxpostListHospInfquirurgicoDxpost.getIdinfquirurgico();
                hospInfquirurgicoDxpostListHospInfquirurgicoDxpost.setIdinfquirurgico(hospInfquirurgico);
                hospInfquirurgicoDxpostListHospInfquirurgicoDxpost = em.merge(hospInfquirurgicoDxpostListHospInfquirurgicoDxpost);
                if (oldIdinfquirurgicoOfHospInfquirurgicoDxpostListHospInfquirurgicoDxpost != null) {
                    oldIdinfquirurgicoOfHospInfquirurgicoDxpostListHospInfquirurgicoDxpost.getHospInfquirurgicoDxpostList().remove(hospInfquirurgicoDxpostListHospInfquirurgicoDxpost);
                    oldIdinfquirurgicoOfHospInfquirurgicoDxpostListHospInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfHospInfquirurgicoDxpostListHospInfquirurgicoDxpost);
                }
            }
            for (HospInfquirurgicoDxpre hospInfquirurgicoDxpreListHospInfquirurgicoDxpre : hospInfquirurgico.getHospInfquirurgicoDxpreList()) {
                HospInfquirurgico oldIdinfquirurgicoOfHospInfquirurgicoDxpreListHospInfquirurgicoDxpre = hospInfquirurgicoDxpreListHospInfquirurgicoDxpre.getIdinfquirurgico();
                hospInfquirurgicoDxpreListHospInfquirurgicoDxpre.setIdinfquirurgico(hospInfquirurgico);
                hospInfquirurgicoDxpreListHospInfquirurgicoDxpre = em.merge(hospInfquirurgicoDxpreListHospInfquirurgicoDxpre);
                if (oldIdinfquirurgicoOfHospInfquirurgicoDxpreListHospInfquirurgicoDxpre != null) {
                    oldIdinfquirurgicoOfHospInfquirurgicoDxpreListHospInfquirurgicoDxpre.getHospInfquirurgicoDxpreList().remove(hospInfquirurgicoDxpreListHospInfquirurgicoDxpre);
                    oldIdinfquirurgicoOfHospInfquirurgicoDxpreListHospInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfHospInfquirurgicoDxpreListHospInfquirurgicoDxpre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospInfquirurgico hospInfquirurgico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospInfquirurgico persistentHospInfquirurgico = em.find(HospInfquirurgico.class, hospInfquirurgico.getId());
            List<HospInfquirurgicoCups> hospInfquirurgicoCupsListOld = persistentHospInfquirurgico.getHospInfquirurgicoCupsList();
            List<HospInfquirurgicoCups> hospInfquirurgicoCupsListNew = hospInfquirurgico.getHospInfquirurgicoCupsList();
            List<HospInfquirurgicoDxpost> hospInfquirurgicoDxpostListOld = persistentHospInfquirurgico.getHospInfquirurgicoDxpostList();
            List<HospInfquirurgicoDxpost> hospInfquirurgicoDxpostListNew = hospInfquirurgico.getHospInfquirurgicoDxpostList();
            List<HospInfquirurgicoDxpre> hospInfquirurgicoDxpreListOld = persistentHospInfquirurgico.getHospInfquirurgicoDxpreList();
            List<HospInfquirurgicoDxpre> hospInfquirurgicoDxpreListNew = hospInfquirurgico.getHospInfquirurgicoDxpreList();
            List<String> illegalOrphanMessages = null;
            for (HospInfquirurgicoCups hospInfquirurgicoCupsListOldHospInfquirurgicoCups : hospInfquirurgicoCupsListOld) {
                if (!hospInfquirurgicoCupsListNew.contains(hospInfquirurgicoCupsListOldHospInfquirurgicoCups)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospInfquirurgicoCups " + hospInfquirurgicoCupsListOldHospInfquirurgicoCups + " since its idquirurgico field is not nullable.");
                }
            }
            for (HospInfquirurgicoDxpost hospInfquirurgicoDxpostListOldHospInfquirurgicoDxpost : hospInfquirurgicoDxpostListOld) {
                if (!hospInfquirurgicoDxpostListNew.contains(hospInfquirurgicoDxpostListOldHospInfquirurgicoDxpost)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospInfquirurgicoDxpost " + hospInfquirurgicoDxpostListOldHospInfquirurgicoDxpost + " since its idinfquirurgico field is not nullable.");
                }
            }
            for (HospInfquirurgicoDxpre hospInfquirurgicoDxpreListOldHospInfquirurgicoDxpre : hospInfquirurgicoDxpreListOld) {
                if (!hospInfquirurgicoDxpreListNew.contains(hospInfquirurgicoDxpreListOldHospInfquirurgicoDxpre)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospInfquirurgicoDxpre " + hospInfquirurgicoDxpreListOldHospInfquirurgicoDxpre + " since its idinfquirurgico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospInfquirurgicoCups> attachedHospInfquirurgicoCupsListNew = new ArrayList<HospInfquirurgicoCups>();
            for (HospInfquirurgicoCups hospInfquirurgicoCupsListNewHospInfquirurgicoCupsToAttach : hospInfquirurgicoCupsListNew) {
                hospInfquirurgicoCupsListNewHospInfquirurgicoCupsToAttach = em.getReference(hospInfquirurgicoCupsListNewHospInfquirurgicoCupsToAttach.getClass(), hospInfquirurgicoCupsListNewHospInfquirurgicoCupsToAttach.getId());
                attachedHospInfquirurgicoCupsListNew.add(hospInfquirurgicoCupsListNewHospInfquirurgicoCupsToAttach);
            }
            hospInfquirurgicoCupsListNew = attachedHospInfquirurgicoCupsListNew;
            hospInfquirurgico.setHospInfquirurgicoCupsList(hospInfquirurgicoCupsListNew);
            List<HospInfquirurgicoDxpost> attachedHospInfquirurgicoDxpostListNew = new ArrayList<HospInfquirurgicoDxpost>();
            for (HospInfquirurgicoDxpost hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpostToAttach : hospInfquirurgicoDxpostListNew) {
                hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpostToAttach = em.getReference(hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpostToAttach.getClass(), hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpostToAttach.getId());
                attachedHospInfquirurgicoDxpostListNew.add(hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpostToAttach);
            }
            hospInfquirurgicoDxpostListNew = attachedHospInfquirurgicoDxpostListNew;
            hospInfquirurgico.setHospInfquirurgicoDxpostList(hospInfquirurgicoDxpostListNew);
            List<HospInfquirurgicoDxpre> attachedHospInfquirurgicoDxpreListNew = new ArrayList<HospInfquirurgicoDxpre>();
            for (HospInfquirurgicoDxpre hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpreToAttach : hospInfquirurgicoDxpreListNew) {
                hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpreToAttach = em.getReference(hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpreToAttach.getClass(), hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpreToAttach.getId());
                attachedHospInfquirurgicoDxpreListNew.add(hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpreToAttach);
            }
            hospInfquirurgicoDxpreListNew = attachedHospInfquirurgicoDxpreListNew;
            hospInfquirurgico.setHospInfquirurgicoDxpreList(hospInfquirurgicoDxpreListNew);
            hospInfquirurgico = em.merge(hospInfquirurgico);
            for (HospInfquirurgicoCups hospInfquirurgicoCupsListNewHospInfquirurgicoCups : hospInfquirurgicoCupsListNew) {
                if (!hospInfquirurgicoCupsListOld.contains(hospInfquirurgicoCupsListNewHospInfquirurgicoCups)) {
                    HospInfquirurgico oldIdquirurgicoOfHospInfquirurgicoCupsListNewHospInfquirurgicoCups = hospInfquirurgicoCupsListNewHospInfquirurgicoCups.getIdquirurgico();
                    hospInfquirurgicoCupsListNewHospInfquirurgicoCups.setIdquirurgico(hospInfquirurgico);
                    hospInfquirurgicoCupsListNewHospInfquirurgicoCups = em.merge(hospInfquirurgicoCupsListNewHospInfquirurgicoCups);
                    if (oldIdquirurgicoOfHospInfquirurgicoCupsListNewHospInfquirurgicoCups != null && !oldIdquirurgicoOfHospInfquirurgicoCupsListNewHospInfquirurgicoCups.equals(hospInfquirurgico)) {
                        oldIdquirurgicoOfHospInfquirurgicoCupsListNewHospInfquirurgicoCups.getHospInfquirurgicoCupsList().remove(hospInfquirurgicoCupsListNewHospInfquirurgicoCups);
                        oldIdquirurgicoOfHospInfquirurgicoCupsListNewHospInfquirurgicoCups = em.merge(oldIdquirurgicoOfHospInfquirurgicoCupsListNewHospInfquirurgicoCups);
                    }
                }
            }
            for (HospInfquirurgicoDxpost hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost : hospInfquirurgicoDxpostListNew) {
                if (!hospInfquirurgicoDxpostListOld.contains(hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost)) {
                    HospInfquirurgico oldIdinfquirurgicoOfHospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost = hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost.getIdinfquirurgico();
                    hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost.setIdinfquirurgico(hospInfquirurgico);
                    hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost = em.merge(hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost);
                    if (oldIdinfquirurgicoOfHospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost != null && !oldIdinfquirurgicoOfHospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost.equals(hospInfquirurgico)) {
                        oldIdinfquirurgicoOfHospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost.getHospInfquirurgicoDxpostList().remove(hospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost);
                        oldIdinfquirurgicoOfHospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost = em.merge(oldIdinfquirurgicoOfHospInfquirurgicoDxpostListNewHospInfquirurgicoDxpost);
                    }
                }
            }
            for (HospInfquirurgicoDxpre hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre : hospInfquirurgicoDxpreListNew) {
                if (!hospInfquirurgicoDxpreListOld.contains(hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre)) {
                    HospInfquirurgico oldIdinfquirurgicoOfHospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre = hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre.getIdinfquirurgico();
                    hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre.setIdinfquirurgico(hospInfquirurgico);
                    hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre = em.merge(hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre);
                    if (oldIdinfquirurgicoOfHospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre != null && !oldIdinfquirurgicoOfHospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre.equals(hospInfquirurgico)) {
                        oldIdinfquirurgicoOfHospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre.getHospInfquirurgicoDxpreList().remove(hospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre);
                        oldIdinfquirurgicoOfHospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre = em.merge(oldIdinfquirurgicoOfHospInfquirurgicoDxpreListNewHospInfquirurgicoDxpre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospInfquirurgico.getId();
                if (findHospInfquirurgico(id) == null) {
                    throw new NonexistentEntityException("The hospInfquirurgico with id " + id + " no longer exists.");
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
            HospInfquirurgico hospInfquirurgico;
            try {
                hospInfquirurgico = em.getReference(HospInfquirurgico.class, id);
                hospInfquirurgico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospInfquirurgico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospInfquirurgicoCups> hospInfquirurgicoCupsListOrphanCheck = hospInfquirurgico.getHospInfquirurgicoCupsList();
            for (HospInfquirurgicoCups hospInfquirurgicoCupsListOrphanCheckHospInfquirurgicoCups : hospInfquirurgicoCupsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospInfquirurgico (" + hospInfquirurgico + ") cannot be destroyed since the HospInfquirurgicoCups " + hospInfquirurgicoCupsListOrphanCheckHospInfquirurgicoCups + " in its hospInfquirurgicoCupsList field has a non-nullable idquirurgico field.");
            }
            List<HospInfquirurgicoDxpost> hospInfquirurgicoDxpostListOrphanCheck = hospInfquirurgico.getHospInfquirurgicoDxpostList();
            for (HospInfquirurgicoDxpost hospInfquirurgicoDxpostListOrphanCheckHospInfquirurgicoDxpost : hospInfquirurgicoDxpostListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospInfquirurgico (" + hospInfquirurgico + ") cannot be destroyed since the HospInfquirurgicoDxpost " + hospInfquirurgicoDxpostListOrphanCheckHospInfquirurgicoDxpost + " in its hospInfquirurgicoDxpostList field has a non-nullable idinfquirurgico field.");
            }
            List<HospInfquirurgicoDxpre> hospInfquirurgicoDxpreListOrphanCheck = hospInfquirurgico.getHospInfquirurgicoDxpreList();
            for (HospInfquirurgicoDxpre hospInfquirurgicoDxpreListOrphanCheckHospInfquirurgicoDxpre : hospInfquirurgicoDxpreListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospInfquirurgico (" + hospInfquirurgico + ") cannot be destroyed since the HospInfquirurgicoDxpre " + hospInfquirurgicoDxpreListOrphanCheckHospInfquirurgicoDxpre + " in its hospInfquirurgicoDxpreList field has a non-nullable idinfquirurgico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hospInfquirurgico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospInfquirurgico> findHospInfquirurgicoEntities() {
        return findHospInfquirurgicoEntities(true, -1, -1);
    }

    public List<HospInfquirurgico> findHospInfquirurgicoEntities(int maxResults, int firstResult) {
        return findHospInfquirurgicoEntities(false, maxResults, firstResult);
    }

    private List<HospInfquirurgico> findHospInfquirurgicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospInfquirurgico.class));
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

    public HospInfquirurgico findHospInfquirurgico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospInfquirurgico.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospInfquirurgicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospInfquirurgico> rt = cq.from(HospInfquirurgico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
