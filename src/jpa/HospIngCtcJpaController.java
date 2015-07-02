/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospIngCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospIngCtcProcedimientos;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospIngCtcCie10;
import entidades_EJB.HospIngCtcMedicamento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospIngCtcJpaController implements Serializable {

    public HospIngCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospIngCtc hospIngCtc) {
        if (hospIngCtc.getHospIngCtcProcedimientosList() == null) {
            hospIngCtc.setHospIngCtcProcedimientosList(new ArrayList<HospIngCtcProcedimientos>());
        }
        if (hospIngCtc.getHospIngCtcCie10List() == null) {
            hospIngCtc.setHospIngCtcCie10List(new ArrayList<HospIngCtcCie10>());
        }
        if (hospIngCtc.getHospIngCtcMedicamentoList() == null) {
            hospIngCtc.setHospIngCtcMedicamentoList(new ArrayList<HospIngCtcMedicamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospIngCtcProcedimientos> attachedHospIngCtcProcedimientosList = new ArrayList<HospIngCtcProcedimientos>();
            for (HospIngCtcProcedimientos hospIngCtcProcedimientosListHospIngCtcProcedimientosToAttach : hospIngCtc.getHospIngCtcProcedimientosList()) {
                hospIngCtcProcedimientosListHospIngCtcProcedimientosToAttach = em.getReference(hospIngCtcProcedimientosListHospIngCtcProcedimientosToAttach.getClass(), hospIngCtcProcedimientosListHospIngCtcProcedimientosToAttach.getId());
                attachedHospIngCtcProcedimientosList.add(hospIngCtcProcedimientosListHospIngCtcProcedimientosToAttach);
            }
            hospIngCtc.setHospIngCtcProcedimientosList(attachedHospIngCtcProcedimientosList);
            List<HospIngCtcCie10> attachedHospIngCtcCie10List = new ArrayList<HospIngCtcCie10>();
            for (HospIngCtcCie10 hospIngCtcCie10ListHospIngCtcCie10ToAttach : hospIngCtc.getHospIngCtcCie10List()) {
                hospIngCtcCie10ListHospIngCtcCie10ToAttach = em.getReference(hospIngCtcCie10ListHospIngCtcCie10ToAttach.getClass(), hospIngCtcCie10ListHospIngCtcCie10ToAttach.getId());
                attachedHospIngCtcCie10List.add(hospIngCtcCie10ListHospIngCtcCie10ToAttach);
            }
            hospIngCtc.setHospIngCtcCie10List(attachedHospIngCtcCie10List);
            List<HospIngCtcMedicamento> attachedHospIngCtcMedicamentoList = new ArrayList<HospIngCtcMedicamento>();
            for (HospIngCtcMedicamento hospIngCtcMedicamentoListHospIngCtcMedicamentoToAttach : hospIngCtc.getHospIngCtcMedicamentoList()) {
                hospIngCtcMedicamentoListHospIngCtcMedicamentoToAttach = em.getReference(hospIngCtcMedicamentoListHospIngCtcMedicamentoToAttach.getClass(), hospIngCtcMedicamentoListHospIngCtcMedicamentoToAttach.getId());
                attachedHospIngCtcMedicamentoList.add(hospIngCtcMedicamentoListHospIngCtcMedicamentoToAttach);
            }
            hospIngCtc.setHospIngCtcMedicamentoList(attachedHospIngCtcMedicamentoList);
            em.persist(hospIngCtc);
            for (HospIngCtcProcedimientos hospIngCtcProcedimientosListHospIngCtcProcedimientos : hospIngCtc.getHospIngCtcProcedimientosList()) {
                HospIngCtc oldIdctcOfHospIngCtcProcedimientosListHospIngCtcProcedimientos = hospIngCtcProcedimientosListHospIngCtcProcedimientos.getIdctc();
                hospIngCtcProcedimientosListHospIngCtcProcedimientos.setIdctc(hospIngCtc);
                hospIngCtcProcedimientosListHospIngCtcProcedimientos = em.merge(hospIngCtcProcedimientosListHospIngCtcProcedimientos);
                if (oldIdctcOfHospIngCtcProcedimientosListHospIngCtcProcedimientos != null) {
                    oldIdctcOfHospIngCtcProcedimientosListHospIngCtcProcedimientos.getHospIngCtcProcedimientosList().remove(hospIngCtcProcedimientosListHospIngCtcProcedimientos);
                    oldIdctcOfHospIngCtcProcedimientosListHospIngCtcProcedimientos = em.merge(oldIdctcOfHospIngCtcProcedimientosListHospIngCtcProcedimientos);
                }
            }
            for (HospIngCtcCie10 hospIngCtcCie10ListHospIngCtcCie10 : hospIngCtc.getHospIngCtcCie10List()) {
                HospIngCtc oldIdctcOfHospIngCtcCie10ListHospIngCtcCie10 = hospIngCtcCie10ListHospIngCtcCie10.getIdctc();
                hospIngCtcCie10ListHospIngCtcCie10.setIdctc(hospIngCtc);
                hospIngCtcCie10ListHospIngCtcCie10 = em.merge(hospIngCtcCie10ListHospIngCtcCie10);
                if (oldIdctcOfHospIngCtcCie10ListHospIngCtcCie10 != null) {
                    oldIdctcOfHospIngCtcCie10ListHospIngCtcCie10.getHospIngCtcCie10List().remove(hospIngCtcCie10ListHospIngCtcCie10);
                    oldIdctcOfHospIngCtcCie10ListHospIngCtcCie10 = em.merge(oldIdctcOfHospIngCtcCie10ListHospIngCtcCie10);
                }
            }
            for (HospIngCtcMedicamento hospIngCtcMedicamentoListHospIngCtcMedicamento : hospIngCtc.getHospIngCtcMedicamentoList()) {
                HospIngCtc oldIdctcOfHospIngCtcMedicamentoListHospIngCtcMedicamento = hospIngCtcMedicamentoListHospIngCtcMedicamento.getIdctc();
                hospIngCtcMedicamentoListHospIngCtcMedicamento.setIdctc(hospIngCtc);
                hospIngCtcMedicamentoListHospIngCtcMedicamento = em.merge(hospIngCtcMedicamentoListHospIngCtcMedicamento);
                if (oldIdctcOfHospIngCtcMedicamentoListHospIngCtcMedicamento != null) {
                    oldIdctcOfHospIngCtcMedicamentoListHospIngCtcMedicamento.getHospIngCtcMedicamentoList().remove(hospIngCtcMedicamentoListHospIngCtcMedicamento);
                    oldIdctcOfHospIngCtcMedicamentoListHospIngCtcMedicamento = em.merge(oldIdctcOfHospIngCtcMedicamentoListHospIngCtcMedicamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospIngCtc hospIngCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospIngCtc persistentHospIngCtc = em.find(HospIngCtc.class, hospIngCtc.getId());
            List<HospIngCtcProcedimientos> hospIngCtcProcedimientosListOld = persistentHospIngCtc.getHospIngCtcProcedimientosList();
            List<HospIngCtcProcedimientos> hospIngCtcProcedimientosListNew = hospIngCtc.getHospIngCtcProcedimientosList();
            List<HospIngCtcCie10> hospIngCtcCie10ListOld = persistentHospIngCtc.getHospIngCtcCie10List();
            List<HospIngCtcCie10> hospIngCtcCie10ListNew = hospIngCtc.getHospIngCtcCie10List();
            List<HospIngCtcMedicamento> hospIngCtcMedicamentoListOld = persistentHospIngCtc.getHospIngCtcMedicamentoList();
            List<HospIngCtcMedicamento> hospIngCtcMedicamentoListNew = hospIngCtc.getHospIngCtcMedicamentoList();
            List<String> illegalOrphanMessages = null;
            for (HospIngCtcProcedimientos hospIngCtcProcedimientosListOldHospIngCtcProcedimientos : hospIngCtcProcedimientosListOld) {
                if (!hospIngCtcProcedimientosListNew.contains(hospIngCtcProcedimientosListOldHospIngCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospIngCtcProcedimientos " + hospIngCtcProcedimientosListOldHospIngCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            for (HospIngCtcCie10 hospIngCtcCie10ListOldHospIngCtcCie10 : hospIngCtcCie10ListOld) {
                if (!hospIngCtcCie10ListNew.contains(hospIngCtcCie10ListOldHospIngCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospIngCtcCie10 " + hospIngCtcCie10ListOldHospIngCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (HospIngCtcMedicamento hospIngCtcMedicamentoListOldHospIngCtcMedicamento : hospIngCtcMedicamentoListOld) {
                if (!hospIngCtcMedicamentoListNew.contains(hospIngCtcMedicamentoListOldHospIngCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospIngCtcMedicamento " + hospIngCtcMedicamentoListOldHospIngCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospIngCtcProcedimientos> attachedHospIngCtcProcedimientosListNew = new ArrayList<HospIngCtcProcedimientos>();
            for (HospIngCtcProcedimientos hospIngCtcProcedimientosListNewHospIngCtcProcedimientosToAttach : hospIngCtcProcedimientosListNew) {
                hospIngCtcProcedimientosListNewHospIngCtcProcedimientosToAttach = em.getReference(hospIngCtcProcedimientosListNewHospIngCtcProcedimientosToAttach.getClass(), hospIngCtcProcedimientosListNewHospIngCtcProcedimientosToAttach.getId());
                attachedHospIngCtcProcedimientosListNew.add(hospIngCtcProcedimientosListNewHospIngCtcProcedimientosToAttach);
            }
            hospIngCtcProcedimientosListNew = attachedHospIngCtcProcedimientosListNew;
            hospIngCtc.setHospIngCtcProcedimientosList(hospIngCtcProcedimientosListNew);
            List<HospIngCtcCie10> attachedHospIngCtcCie10ListNew = new ArrayList<HospIngCtcCie10>();
            for (HospIngCtcCie10 hospIngCtcCie10ListNewHospIngCtcCie10ToAttach : hospIngCtcCie10ListNew) {
                hospIngCtcCie10ListNewHospIngCtcCie10ToAttach = em.getReference(hospIngCtcCie10ListNewHospIngCtcCie10ToAttach.getClass(), hospIngCtcCie10ListNewHospIngCtcCie10ToAttach.getId());
                attachedHospIngCtcCie10ListNew.add(hospIngCtcCie10ListNewHospIngCtcCie10ToAttach);
            }
            hospIngCtcCie10ListNew = attachedHospIngCtcCie10ListNew;
            hospIngCtc.setHospIngCtcCie10List(hospIngCtcCie10ListNew);
            List<HospIngCtcMedicamento> attachedHospIngCtcMedicamentoListNew = new ArrayList<HospIngCtcMedicamento>();
            for (HospIngCtcMedicamento hospIngCtcMedicamentoListNewHospIngCtcMedicamentoToAttach : hospIngCtcMedicamentoListNew) {
                hospIngCtcMedicamentoListNewHospIngCtcMedicamentoToAttach = em.getReference(hospIngCtcMedicamentoListNewHospIngCtcMedicamentoToAttach.getClass(), hospIngCtcMedicamentoListNewHospIngCtcMedicamentoToAttach.getId());
                attachedHospIngCtcMedicamentoListNew.add(hospIngCtcMedicamentoListNewHospIngCtcMedicamentoToAttach);
            }
            hospIngCtcMedicamentoListNew = attachedHospIngCtcMedicamentoListNew;
            hospIngCtc.setHospIngCtcMedicamentoList(hospIngCtcMedicamentoListNew);
            hospIngCtc = em.merge(hospIngCtc);
            for (HospIngCtcProcedimientos hospIngCtcProcedimientosListNewHospIngCtcProcedimientos : hospIngCtcProcedimientosListNew) {
                if (!hospIngCtcProcedimientosListOld.contains(hospIngCtcProcedimientosListNewHospIngCtcProcedimientos)) {
                    HospIngCtc oldIdctcOfHospIngCtcProcedimientosListNewHospIngCtcProcedimientos = hospIngCtcProcedimientosListNewHospIngCtcProcedimientos.getIdctc();
                    hospIngCtcProcedimientosListNewHospIngCtcProcedimientos.setIdctc(hospIngCtc);
                    hospIngCtcProcedimientosListNewHospIngCtcProcedimientos = em.merge(hospIngCtcProcedimientosListNewHospIngCtcProcedimientos);
                    if (oldIdctcOfHospIngCtcProcedimientosListNewHospIngCtcProcedimientos != null && !oldIdctcOfHospIngCtcProcedimientosListNewHospIngCtcProcedimientos.equals(hospIngCtc)) {
                        oldIdctcOfHospIngCtcProcedimientosListNewHospIngCtcProcedimientos.getHospIngCtcProcedimientosList().remove(hospIngCtcProcedimientosListNewHospIngCtcProcedimientos);
                        oldIdctcOfHospIngCtcProcedimientosListNewHospIngCtcProcedimientos = em.merge(oldIdctcOfHospIngCtcProcedimientosListNewHospIngCtcProcedimientos);
                    }
                }
            }
            for (HospIngCtcCie10 hospIngCtcCie10ListNewHospIngCtcCie10 : hospIngCtcCie10ListNew) {
                if (!hospIngCtcCie10ListOld.contains(hospIngCtcCie10ListNewHospIngCtcCie10)) {
                    HospIngCtc oldIdctcOfHospIngCtcCie10ListNewHospIngCtcCie10 = hospIngCtcCie10ListNewHospIngCtcCie10.getIdctc();
                    hospIngCtcCie10ListNewHospIngCtcCie10.setIdctc(hospIngCtc);
                    hospIngCtcCie10ListNewHospIngCtcCie10 = em.merge(hospIngCtcCie10ListNewHospIngCtcCie10);
                    if (oldIdctcOfHospIngCtcCie10ListNewHospIngCtcCie10 != null && !oldIdctcOfHospIngCtcCie10ListNewHospIngCtcCie10.equals(hospIngCtc)) {
                        oldIdctcOfHospIngCtcCie10ListNewHospIngCtcCie10.getHospIngCtcCie10List().remove(hospIngCtcCie10ListNewHospIngCtcCie10);
                        oldIdctcOfHospIngCtcCie10ListNewHospIngCtcCie10 = em.merge(oldIdctcOfHospIngCtcCie10ListNewHospIngCtcCie10);
                    }
                }
            }
            for (HospIngCtcMedicamento hospIngCtcMedicamentoListNewHospIngCtcMedicamento : hospIngCtcMedicamentoListNew) {
                if (!hospIngCtcMedicamentoListOld.contains(hospIngCtcMedicamentoListNewHospIngCtcMedicamento)) {
                    HospIngCtc oldIdctcOfHospIngCtcMedicamentoListNewHospIngCtcMedicamento = hospIngCtcMedicamentoListNewHospIngCtcMedicamento.getIdctc();
                    hospIngCtcMedicamentoListNewHospIngCtcMedicamento.setIdctc(hospIngCtc);
                    hospIngCtcMedicamentoListNewHospIngCtcMedicamento = em.merge(hospIngCtcMedicamentoListNewHospIngCtcMedicamento);
                    if (oldIdctcOfHospIngCtcMedicamentoListNewHospIngCtcMedicamento != null && !oldIdctcOfHospIngCtcMedicamentoListNewHospIngCtcMedicamento.equals(hospIngCtc)) {
                        oldIdctcOfHospIngCtcMedicamentoListNewHospIngCtcMedicamento.getHospIngCtcMedicamentoList().remove(hospIngCtcMedicamentoListNewHospIngCtcMedicamento);
                        oldIdctcOfHospIngCtcMedicamentoListNewHospIngCtcMedicamento = em.merge(oldIdctcOfHospIngCtcMedicamentoListNewHospIngCtcMedicamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospIngCtc.getId();
                if (findHospIngCtc(id) == null) {
                    throw new NonexistentEntityException("The hospIngCtc with id " + id + " no longer exists.");
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
            HospIngCtc hospIngCtc;
            try {
                hospIngCtc = em.getReference(HospIngCtc.class, id);
                hospIngCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospIngCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospIngCtcProcedimientos> hospIngCtcProcedimientosListOrphanCheck = hospIngCtc.getHospIngCtcProcedimientosList();
            for (HospIngCtcProcedimientos hospIngCtcProcedimientosListOrphanCheckHospIngCtcProcedimientos : hospIngCtcProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospIngCtc (" + hospIngCtc + ") cannot be destroyed since the HospIngCtcProcedimientos " + hospIngCtcProcedimientosListOrphanCheckHospIngCtcProcedimientos + " in its hospIngCtcProcedimientosList field has a non-nullable idctc field.");
            }
            List<HospIngCtcCie10> hospIngCtcCie10ListOrphanCheck = hospIngCtc.getHospIngCtcCie10List();
            for (HospIngCtcCie10 hospIngCtcCie10ListOrphanCheckHospIngCtcCie10 : hospIngCtcCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospIngCtc (" + hospIngCtc + ") cannot be destroyed since the HospIngCtcCie10 " + hospIngCtcCie10ListOrphanCheckHospIngCtcCie10 + " in its hospIngCtcCie10List field has a non-nullable idctc field.");
            }
            List<HospIngCtcMedicamento> hospIngCtcMedicamentoListOrphanCheck = hospIngCtc.getHospIngCtcMedicamentoList();
            for (HospIngCtcMedicamento hospIngCtcMedicamentoListOrphanCheckHospIngCtcMedicamento : hospIngCtcMedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospIngCtc (" + hospIngCtc + ") cannot be destroyed since the HospIngCtcMedicamento " + hospIngCtcMedicamentoListOrphanCheckHospIngCtcMedicamento + " in its hospIngCtcMedicamentoList field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hospIngCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospIngCtc> findHospIngCtcEntities() {
        return findHospIngCtcEntities(true, -1, -1);
    }

    public List<HospIngCtc> findHospIngCtcEntities(int maxResults, int firstResult) {
        return findHospIngCtcEntities(false, maxResults, firstResult);
    }

    private List<HospIngCtc> findHospIngCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospIngCtc.class));
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

    public HospIngCtc findHospIngCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospIngCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospIngCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospIngCtc> rt = cq.from(HospIngCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
