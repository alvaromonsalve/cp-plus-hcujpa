/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UrgIngCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UrgIngCtcCie10;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UrgIngCtcMedicamento;
import entidades_EJB.UrgIngCtcProcedimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UrgIngCtcJpaController implements Serializable {

    public UrgIngCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgIngCtc urgIngCtc) {
        if (urgIngCtc.getUrgIngCtcCie10List() == null) {
            urgIngCtc.setUrgIngCtcCie10List(new ArrayList<UrgIngCtcCie10>());
        }
        if (urgIngCtc.getUrgIngCtcMedicamentoList() == null) {
            urgIngCtc.setUrgIngCtcMedicamentoList(new ArrayList<UrgIngCtcMedicamento>());
        }
        if (urgIngCtc.getUrgIngCtcProcedimientosList() == null) {
            urgIngCtc.setUrgIngCtcProcedimientosList(new ArrayList<UrgIngCtcProcedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UrgIngCtcCie10> attachedUrgIngCtcCie10List = new ArrayList<UrgIngCtcCie10>();
            for (UrgIngCtcCie10 urgIngCtcCie10ListUrgIngCtcCie10ToAttach : urgIngCtc.getUrgIngCtcCie10List()) {
                urgIngCtcCie10ListUrgIngCtcCie10ToAttach = em.getReference(urgIngCtcCie10ListUrgIngCtcCie10ToAttach.getClass(), urgIngCtcCie10ListUrgIngCtcCie10ToAttach.getId());
                attachedUrgIngCtcCie10List.add(urgIngCtcCie10ListUrgIngCtcCie10ToAttach);
            }
            urgIngCtc.setUrgIngCtcCie10List(attachedUrgIngCtcCie10List);
            List<UrgIngCtcMedicamento> attachedUrgIngCtcMedicamentoList = new ArrayList<UrgIngCtcMedicamento>();
            for (UrgIngCtcMedicamento urgIngCtcMedicamentoListUrgIngCtcMedicamentoToAttach : urgIngCtc.getUrgIngCtcMedicamentoList()) {
                urgIngCtcMedicamentoListUrgIngCtcMedicamentoToAttach = em.getReference(urgIngCtcMedicamentoListUrgIngCtcMedicamentoToAttach.getClass(), urgIngCtcMedicamentoListUrgIngCtcMedicamentoToAttach.getId());
                attachedUrgIngCtcMedicamentoList.add(urgIngCtcMedicamentoListUrgIngCtcMedicamentoToAttach);
            }
            urgIngCtc.setUrgIngCtcMedicamentoList(attachedUrgIngCtcMedicamentoList);
            List<UrgIngCtcProcedimientos> attachedUrgIngCtcProcedimientosList = new ArrayList<UrgIngCtcProcedimientos>();
            for (UrgIngCtcProcedimientos urgIngCtcProcedimientosListUrgIngCtcProcedimientosToAttach : urgIngCtc.getUrgIngCtcProcedimientosList()) {
                urgIngCtcProcedimientosListUrgIngCtcProcedimientosToAttach = em.getReference(urgIngCtcProcedimientosListUrgIngCtcProcedimientosToAttach.getClass(), urgIngCtcProcedimientosListUrgIngCtcProcedimientosToAttach.getId());
                attachedUrgIngCtcProcedimientosList.add(urgIngCtcProcedimientosListUrgIngCtcProcedimientosToAttach);
            }
            urgIngCtc.setUrgIngCtcProcedimientosList(attachedUrgIngCtcProcedimientosList);
            em.persist(urgIngCtc);
            for (UrgIngCtcCie10 urgIngCtcCie10ListUrgIngCtcCie10 : urgIngCtc.getUrgIngCtcCie10List()) {
                UrgIngCtc oldIdctcOfUrgIngCtcCie10ListUrgIngCtcCie10 = urgIngCtcCie10ListUrgIngCtcCie10.getIdctc();
                urgIngCtcCie10ListUrgIngCtcCie10.setIdctc(urgIngCtc);
                urgIngCtcCie10ListUrgIngCtcCie10 = em.merge(urgIngCtcCie10ListUrgIngCtcCie10);
                if (oldIdctcOfUrgIngCtcCie10ListUrgIngCtcCie10 != null) {
                    oldIdctcOfUrgIngCtcCie10ListUrgIngCtcCie10.getUrgIngCtcCie10List().remove(urgIngCtcCie10ListUrgIngCtcCie10);
                    oldIdctcOfUrgIngCtcCie10ListUrgIngCtcCie10 = em.merge(oldIdctcOfUrgIngCtcCie10ListUrgIngCtcCie10);
                }
            }
            for (UrgIngCtcMedicamento urgIngCtcMedicamentoListUrgIngCtcMedicamento : urgIngCtc.getUrgIngCtcMedicamentoList()) {
                UrgIngCtc oldIdctcOfUrgIngCtcMedicamentoListUrgIngCtcMedicamento = urgIngCtcMedicamentoListUrgIngCtcMedicamento.getIdctc();
                urgIngCtcMedicamentoListUrgIngCtcMedicamento.setIdctc(urgIngCtc);
                urgIngCtcMedicamentoListUrgIngCtcMedicamento = em.merge(urgIngCtcMedicamentoListUrgIngCtcMedicamento);
                if (oldIdctcOfUrgIngCtcMedicamentoListUrgIngCtcMedicamento != null) {
                    oldIdctcOfUrgIngCtcMedicamentoListUrgIngCtcMedicamento.getUrgIngCtcMedicamentoList().remove(urgIngCtcMedicamentoListUrgIngCtcMedicamento);
                    oldIdctcOfUrgIngCtcMedicamentoListUrgIngCtcMedicamento = em.merge(oldIdctcOfUrgIngCtcMedicamentoListUrgIngCtcMedicamento);
                }
            }
            for (UrgIngCtcProcedimientos urgIngCtcProcedimientosListUrgIngCtcProcedimientos : urgIngCtc.getUrgIngCtcProcedimientosList()) {
                UrgIngCtc oldIdctcOfUrgIngCtcProcedimientosListUrgIngCtcProcedimientos = urgIngCtcProcedimientosListUrgIngCtcProcedimientos.getIdctc();
                urgIngCtcProcedimientosListUrgIngCtcProcedimientos.setIdctc(urgIngCtc);
                urgIngCtcProcedimientosListUrgIngCtcProcedimientos = em.merge(urgIngCtcProcedimientosListUrgIngCtcProcedimientos);
                if (oldIdctcOfUrgIngCtcProcedimientosListUrgIngCtcProcedimientos != null) {
                    oldIdctcOfUrgIngCtcProcedimientosListUrgIngCtcProcedimientos.getUrgIngCtcProcedimientosList().remove(urgIngCtcProcedimientosListUrgIngCtcProcedimientos);
                    oldIdctcOfUrgIngCtcProcedimientosListUrgIngCtcProcedimientos = em.merge(oldIdctcOfUrgIngCtcProcedimientosListUrgIngCtcProcedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgIngCtc urgIngCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgIngCtc persistentUrgIngCtc = em.find(UrgIngCtc.class, urgIngCtc.getId());
            List<UrgIngCtcCie10> urgIngCtcCie10ListOld = persistentUrgIngCtc.getUrgIngCtcCie10List();
            List<UrgIngCtcCie10> urgIngCtcCie10ListNew = urgIngCtc.getUrgIngCtcCie10List();
            List<UrgIngCtcMedicamento> urgIngCtcMedicamentoListOld = persistentUrgIngCtc.getUrgIngCtcMedicamentoList();
            List<UrgIngCtcMedicamento> urgIngCtcMedicamentoListNew = urgIngCtc.getUrgIngCtcMedicamentoList();
            List<UrgIngCtcProcedimientos> urgIngCtcProcedimientosListOld = persistentUrgIngCtc.getUrgIngCtcProcedimientosList();
            List<UrgIngCtcProcedimientos> urgIngCtcProcedimientosListNew = urgIngCtc.getUrgIngCtcProcedimientosList();
            List<String> illegalOrphanMessages = null;
            for (UrgIngCtcCie10 urgIngCtcCie10ListOldUrgIngCtcCie10 : urgIngCtcCie10ListOld) {
                if (!urgIngCtcCie10ListNew.contains(urgIngCtcCie10ListOldUrgIngCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgIngCtcCie10 " + urgIngCtcCie10ListOldUrgIngCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (UrgIngCtcMedicamento urgIngCtcMedicamentoListOldUrgIngCtcMedicamento : urgIngCtcMedicamentoListOld) {
                if (!urgIngCtcMedicamentoListNew.contains(urgIngCtcMedicamentoListOldUrgIngCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgIngCtcMedicamento " + urgIngCtcMedicamentoListOldUrgIngCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            for (UrgIngCtcProcedimientos urgIngCtcProcedimientosListOldUrgIngCtcProcedimientos : urgIngCtcProcedimientosListOld) {
                if (!urgIngCtcProcedimientosListNew.contains(urgIngCtcProcedimientosListOldUrgIngCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgIngCtcProcedimientos " + urgIngCtcProcedimientosListOldUrgIngCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgIngCtcCie10> attachedUrgIngCtcCie10ListNew = new ArrayList<UrgIngCtcCie10>();
            for (UrgIngCtcCie10 urgIngCtcCie10ListNewUrgIngCtcCie10ToAttach : urgIngCtcCie10ListNew) {
                urgIngCtcCie10ListNewUrgIngCtcCie10ToAttach = em.getReference(urgIngCtcCie10ListNewUrgIngCtcCie10ToAttach.getClass(), urgIngCtcCie10ListNewUrgIngCtcCie10ToAttach.getId());
                attachedUrgIngCtcCie10ListNew.add(urgIngCtcCie10ListNewUrgIngCtcCie10ToAttach);
            }
            urgIngCtcCie10ListNew = attachedUrgIngCtcCie10ListNew;
            urgIngCtc.setUrgIngCtcCie10List(urgIngCtcCie10ListNew);
            List<UrgIngCtcMedicamento> attachedUrgIngCtcMedicamentoListNew = new ArrayList<UrgIngCtcMedicamento>();
            for (UrgIngCtcMedicamento urgIngCtcMedicamentoListNewUrgIngCtcMedicamentoToAttach : urgIngCtcMedicamentoListNew) {
                urgIngCtcMedicamentoListNewUrgIngCtcMedicamentoToAttach = em.getReference(urgIngCtcMedicamentoListNewUrgIngCtcMedicamentoToAttach.getClass(), urgIngCtcMedicamentoListNewUrgIngCtcMedicamentoToAttach.getId());
                attachedUrgIngCtcMedicamentoListNew.add(urgIngCtcMedicamentoListNewUrgIngCtcMedicamentoToAttach);
            }
            urgIngCtcMedicamentoListNew = attachedUrgIngCtcMedicamentoListNew;
            urgIngCtc.setUrgIngCtcMedicamentoList(urgIngCtcMedicamentoListNew);
            List<UrgIngCtcProcedimientos> attachedUrgIngCtcProcedimientosListNew = new ArrayList<UrgIngCtcProcedimientos>();
            for (UrgIngCtcProcedimientos urgIngCtcProcedimientosListNewUrgIngCtcProcedimientosToAttach : urgIngCtcProcedimientosListNew) {
                urgIngCtcProcedimientosListNewUrgIngCtcProcedimientosToAttach = em.getReference(urgIngCtcProcedimientosListNewUrgIngCtcProcedimientosToAttach.getClass(), urgIngCtcProcedimientosListNewUrgIngCtcProcedimientosToAttach.getId());
                attachedUrgIngCtcProcedimientosListNew.add(urgIngCtcProcedimientosListNewUrgIngCtcProcedimientosToAttach);
            }
            urgIngCtcProcedimientosListNew = attachedUrgIngCtcProcedimientosListNew;
            urgIngCtc.setUrgIngCtcProcedimientosList(urgIngCtcProcedimientosListNew);
            urgIngCtc = em.merge(urgIngCtc);
            for (UrgIngCtcCie10 urgIngCtcCie10ListNewUrgIngCtcCie10 : urgIngCtcCie10ListNew) {
                if (!urgIngCtcCie10ListOld.contains(urgIngCtcCie10ListNewUrgIngCtcCie10)) {
                    UrgIngCtc oldIdctcOfUrgIngCtcCie10ListNewUrgIngCtcCie10 = urgIngCtcCie10ListNewUrgIngCtcCie10.getIdctc();
                    urgIngCtcCie10ListNewUrgIngCtcCie10.setIdctc(urgIngCtc);
                    urgIngCtcCie10ListNewUrgIngCtcCie10 = em.merge(urgIngCtcCie10ListNewUrgIngCtcCie10);
                    if (oldIdctcOfUrgIngCtcCie10ListNewUrgIngCtcCie10 != null && !oldIdctcOfUrgIngCtcCie10ListNewUrgIngCtcCie10.equals(urgIngCtc)) {
                        oldIdctcOfUrgIngCtcCie10ListNewUrgIngCtcCie10.getUrgIngCtcCie10List().remove(urgIngCtcCie10ListNewUrgIngCtcCie10);
                        oldIdctcOfUrgIngCtcCie10ListNewUrgIngCtcCie10 = em.merge(oldIdctcOfUrgIngCtcCie10ListNewUrgIngCtcCie10);
                    }
                }
            }
            for (UrgIngCtcMedicamento urgIngCtcMedicamentoListNewUrgIngCtcMedicamento : urgIngCtcMedicamentoListNew) {
                if (!urgIngCtcMedicamentoListOld.contains(urgIngCtcMedicamentoListNewUrgIngCtcMedicamento)) {
                    UrgIngCtc oldIdctcOfUrgIngCtcMedicamentoListNewUrgIngCtcMedicamento = urgIngCtcMedicamentoListNewUrgIngCtcMedicamento.getIdctc();
                    urgIngCtcMedicamentoListNewUrgIngCtcMedicamento.setIdctc(urgIngCtc);
                    urgIngCtcMedicamentoListNewUrgIngCtcMedicamento = em.merge(urgIngCtcMedicamentoListNewUrgIngCtcMedicamento);
                    if (oldIdctcOfUrgIngCtcMedicamentoListNewUrgIngCtcMedicamento != null && !oldIdctcOfUrgIngCtcMedicamentoListNewUrgIngCtcMedicamento.equals(urgIngCtc)) {
                        oldIdctcOfUrgIngCtcMedicamentoListNewUrgIngCtcMedicamento.getUrgIngCtcMedicamentoList().remove(urgIngCtcMedicamentoListNewUrgIngCtcMedicamento);
                        oldIdctcOfUrgIngCtcMedicamentoListNewUrgIngCtcMedicamento = em.merge(oldIdctcOfUrgIngCtcMedicamentoListNewUrgIngCtcMedicamento);
                    }
                }
            }
            for (UrgIngCtcProcedimientos urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos : urgIngCtcProcedimientosListNew) {
                if (!urgIngCtcProcedimientosListOld.contains(urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos)) {
                    UrgIngCtc oldIdctcOfUrgIngCtcProcedimientosListNewUrgIngCtcProcedimientos = urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos.getIdctc();
                    urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos.setIdctc(urgIngCtc);
                    urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos = em.merge(urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos);
                    if (oldIdctcOfUrgIngCtcProcedimientosListNewUrgIngCtcProcedimientos != null && !oldIdctcOfUrgIngCtcProcedimientosListNewUrgIngCtcProcedimientos.equals(urgIngCtc)) {
                        oldIdctcOfUrgIngCtcProcedimientosListNewUrgIngCtcProcedimientos.getUrgIngCtcProcedimientosList().remove(urgIngCtcProcedimientosListNewUrgIngCtcProcedimientos);
                        oldIdctcOfUrgIngCtcProcedimientosListNewUrgIngCtcProcedimientos = em.merge(oldIdctcOfUrgIngCtcProcedimientosListNewUrgIngCtcProcedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = urgIngCtc.getId();
                if (findUrgIngCtc(id) == null) {
                    throw new NonexistentEntityException("The urgIngCtc with id " + id + " no longer exists.");
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
            UrgIngCtc urgIngCtc;
            try {
                urgIngCtc = em.getReference(UrgIngCtc.class, id);
                urgIngCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgIngCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UrgIngCtcCie10> urgIngCtcCie10ListOrphanCheck = urgIngCtc.getUrgIngCtcCie10List();
            for (UrgIngCtcCie10 urgIngCtcCie10ListOrphanCheckUrgIngCtcCie10 : urgIngCtcCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgIngCtc (" + urgIngCtc + ") cannot be destroyed since the UrgIngCtcCie10 " + urgIngCtcCie10ListOrphanCheckUrgIngCtcCie10 + " in its urgIngCtcCie10List field has a non-nullable idctc field.");
            }
            List<UrgIngCtcMedicamento> urgIngCtcMedicamentoListOrphanCheck = urgIngCtc.getUrgIngCtcMedicamentoList();
            for (UrgIngCtcMedicamento urgIngCtcMedicamentoListOrphanCheckUrgIngCtcMedicamento : urgIngCtcMedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgIngCtc (" + urgIngCtc + ") cannot be destroyed since the UrgIngCtcMedicamento " + urgIngCtcMedicamentoListOrphanCheckUrgIngCtcMedicamento + " in its urgIngCtcMedicamentoList field has a non-nullable idctc field.");
            }
            List<UrgIngCtcProcedimientos> urgIngCtcProcedimientosListOrphanCheck = urgIngCtc.getUrgIngCtcProcedimientosList();
            for (UrgIngCtcProcedimientos urgIngCtcProcedimientosListOrphanCheckUrgIngCtcProcedimientos : urgIngCtcProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UrgIngCtc (" + urgIngCtc + ") cannot be destroyed since the UrgIngCtcProcedimientos " + urgIngCtcProcedimientosListOrphanCheckUrgIngCtcProcedimientos + " in its urgIngCtcProcedimientosList field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(urgIngCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgIngCtc> findUrgIngCtcEntities() {
        return findUrgIngCtcEntities(true, -1, -1);
    }

    public List<UrgIngCtc> findUrgIngCtcEntities(int maxResults, int firstResult) {
        return findUrgIngCtcEntities(false, maxResults, firstResult);
    }

    private List<UrgIngCtc> findUrgIngCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgIngCtc.class));
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

    public UrgIngCtc findUrgIngCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgIngCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgIngCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgIngCtc> rt = cq.from(UrgIngCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
