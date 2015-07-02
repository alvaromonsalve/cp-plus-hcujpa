/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UceIngCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UceIngCtcMedicamento;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UceIngCtcCie10;
import entidades_EJB.UceIngCtcProcedimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UceIngCtcJpaController implements Serializable {

    public UceIngCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceIngCtc uceIngCtc) {
        if (uceIngCtc.getUceIngCtcMedicamentoList() == null) {
            uceIngCtc.setUceIngCtcMedicamentoList(new ArrayList<UceIngCtcMedicamento>());
        }
        if (uceIngCtc.getUceIngCtcCie10List() == null) {
            uceIngCtc.setUceIngCtcCie10List(new ArrayList<UceIngCtcCie10>());
        }
        if (uceIngCtc.getUceIngCtcProcedimientosList() == null) {
            uceIngCtc.setUceIngCtcProcedimientosList(new ArrayList<UceIngCtcProcedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UceIngCtcMedicamento> attachedUceIngCtcMedicamentoList = new ArrayList<UceIngCtcMedicamento>();
            for (UceIngCtcMedicamento uceIngCtcMedicamentoListUceIngCtcMedicamentoToAttach : uceIngCtc.getUceIngCtcMedicamentoList()) {
                uceIngCtcMedicamentoListUceIngCtcMedicamentoToAttach = em.getReference(uceIngCtcMedicamentoListUceIngCtcMedicamentoToAttach.getClass(), uceIngCtcMedicamentoListUceIngCtcMedicamentoToAttach.getId());
                attachedUceIngCtcMedicamentoList.add(uceIngCtcMedicamentoListUceIngCtcMedicamentoToAttach);
            }
            uceIngCtc.setUceIngCtcMedicamentoList(attachedUceIngCtcMedicamentoList);
            List<UceIngCtcCie10> attachedUceIngCtcCie10List = new ArrayList<UceIngCtcCie10>();
            for (UceIngCtcCie10 uceIngCtcCie10ListUceIngCtcCie10ToAttach : uceIngCtc.getUceIngCtcCie10List()) {
                uceIngCtcCie10ListUceIngCtcCie10ToAttach = em.getReference(uceIngCtcCie10ListUceIngCtcCie10ToAttach.getClass(), uceIngCtcCie10ListUceIngCtcCie10ToAttach.getId());
                attachedUceIngCtcCie10List.add(uceIngCtcCie10ListUceIngCtcCie10ToAttach);
            }
            uceIngCtc.setUceIngCtcCie10List(attachedUceIngCtcCie10List);
            List<UceIngCtcProcedimientos> attachedUceIngCtcProcedimientosList = new ArrayList<UceIngCtcProcedimientos>();
            for (UceIngCtcProcedimientos uceIngCtcProcedimientosListUceIngCtcProcedimientosToAttach : uceIngCtc.getUceIngCtcProcedimientosList()) {
                uceIngCtcProcedimientosListUceIngCtcProcedimientosToAttach = em.getReference(uceIngCtcProcedimientosListUceIngCtcProcedimientosToAttach.getClass(), uceIngCtcProcedimientosListUceIngCtcProcedimientosToAttach.getId());
                attachedUceIngCtcProcedimientosList.add(uceIngCtcProcedimientosListUceIngCtcProcedimientosToAttach);
            }
            uceIngCtc.setUceIngCtcProcedimientosList(attachedUceIngCtcProcedimientosList);
            em.persist(uceIngCtc);
            for (UceIngCtcMedicamento uceIngCtcMedicamentoListUceIngCtcMedicamento : uceIngCtc.getUceIngCtcMedicamentoList()) {
                UceIngCtc oldIdctcOfUceIngCtcMedicamentoListUceIngCtcMedicamento = uceIngCtcMedicamentoListUceIngCtcMedicamento.getIdctc();
                uceIngCtcMedicamentoListUceIngCtcMedicamento.setIdctc(uceIngCtc);
                uceIngCtcMedicamentoListUceIngCtcMedicamento = em.merge(uceIngCtcMedicamentoListUceIngCtcMedicamento);
                if (oldIdctcOfUceIngCtcMedicamentoListUceIngCtcMedicamento != null) {
                    oldIdctcOfUceIngCtcMedicamentoListUceIngCtcMedicamento.getUceIngCtcMedicamentoList().remove(uceIngCtcMedicamentoListUceIngCtcMedicamento);
                    oldIdctcOfUceIngCtcMedicamentoListUceIngCtcMedicamento = em.merge(oldIdctcOfUceIngCtcMedicamentoListUceIngCtcMedicamento);
                }
            }
            for (UceIngCtcCie10 uceIngCtcCie10ListUceIngCtcCie10 : uceIngCtc.getUceIngCtcCie10List()) {
                UceIngCtc oldIdctcOfUceIngCtcCie10ListUceIngCtcCie10 = uceIngCtcCie10ListUceIngCtcCie10.getIdctc();
                uceIngCtcCie10ListUceIngCtcCie10.setIdctc(uceIngCtc);
                uceIngCtcCie10ListUceIngCtcCie10 = em.merge(uceIngCtcCie10ListUceIngCtcCie10);
                if (oldIdctcOfUceIngCtcCie10ListUceIngCtcCie10 != null) {
                    oldIdctcOfUceIngCtcCie10ListUceIngCtcCie10.getUceIngCtcCie10List().remove(uceIngCtcCie10ListUceIngCtcCie10);
                    oldIdctcOfUceIngCtcCie10ListUceIngCtcCie10 = em.merge(oldIdctcOfUceIngCtcCie10ListUceIngCtcCie10);
                }
            }
            for (UceIngCtcProcedimientos uceIngCtcProcedimientosListUceIngCtcProcedimientos : uceIngCtc.getUceIngCtcProcedimientosList()) {
                UceIngCtc oldIdctcOfUceIngCtcProcedimientosListUceIngCtcProcedimientos = uceIngCtcProcedimientosListUceIngCtcProcedimientos.getIdctc();
                uceIngCtcProcedimientosListUceIngCtcProcedimientos.setIdctc(uceIngCtc);
                uceIngCtcProcedimientosListUceIngCtcProcedimientos = em.merge(uceIngCtcProcedimientosListUceIngCtcProcedimientos);
                if (oldIdctcOfUceIngCtcProcedimientosListUceIngCtcProcedimientos != null) {
                    oldIdctcOfUceIngCtcProcedimientosListUceIngCtcProcedimientos.getUceIngCtcProcedimientosList().remove(uceIngCtcProcedimientosListUceIngCtcProcedimientos);
                    oldIdctcOfUceIngCtcProcedimientosListUceIngCtcProcedimientos = em.merge(oldIdctcOfUceIngCtcProcedimientosListUceIngCtcProcedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceIngCtc uceIngCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceIngCtc persistentUceIngCtc = em.find(UceIngCtc.class, uceIngCtc.getId());
            List<UceIngCtcMedicamento> uceIngCtcMedicamentoListOld = persistentUceIngCtc.getUceIngCtcMedicamentoList();
            List<UceIngCtcMedicamento> uceIngCtcMedicamentoListNew = uceIngCtc.getUceIngCtcMedicamentoList();
            List<UceIngCtcCie10> uceIngCtcCie10ListOld = persistentUceIngCtc.getUceIngCtcCie10List();
            List<UceIngCtcCie10> uceIngCtcCie10ListNew = uceIngCtc.getUceIngCtcCie10List();
            List<UceIngCtcProcedimientos> uceIngCtcProcedimientosListOld = persistentUceIngCtc.getUceIngCtcProcedimientosList();
            List<UceIngCtcProcedimientos> uceIngCtcProcedimientosListNew = uceIngCtc.getUceIngCtcProcedimientosList();
            List<String> illegalOrphanMessages = null;
            for (UceIngCtcMedicamento uceIngCtcMedicamentoListOldUceIngCtcMedicamento : uceIngCtcMedicamentoListOld) {
                if (!uceIngCtcMedicamentoListNew.contains(uceIngCtcMedicamentoListOldUceIngCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceIngCtcMedicamento " + uceIngCtcMedicamentoListOldUceIngCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            for (UceIngCtcCie10 uceIngCtcCie10ListOldUceIngCtcCie10 : uceIngCtcCie10ListOld) {
                if (!uceIngCtcCie10ListNew.contains(uceIngCtcCie10ListOldUceIngCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceIngCtcCie10 " + uceIngCtcCie10ListOldUceIngCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (UceIngCtcProcedimientos uceIngCtcProcedimientosListOldUceIngCtcProcedimientos : uceIngCtcProcedimientosListOld) {
                if (!uceIngCtcProcedimientosListNew.contains(uceIngCtcProcedimientosListOldUceIngCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceIngCtcProcedimientos " + uceIngCtcProcedimientosListOldUceIngCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UceIngCtcMedicamento> attachedUceIngCtcMedicamentoListNew = new ArrayList<UceIngCtcMedicamento>();
            for (UceIngCtcMedicamento uceIngCtcMedicamentoListNewUceIngCtcMedicamentoToAttach : uceIngCtcMedicamentoListNew) {
                uceIngCtcMedicamentoListNewUceIngCtcMedicamentoToAttach = em.getReference(uceIngCtcMedicamentoListNewUceIngCtcMedicamentoToAttach.getClass(), uceIngCtcMedicamentoListNewUceIngCtcMedicamentoToAttach.getId());
                attachedUceIngCtcMedicamentoListNew.add(uceIngCtcMedicamentoListNewUceIngCtcMedicamentoToAttach);
            }
            uceIngCtcMedicamentoListNew = attachedUceIngCtcMedicamentoListNew;
            uceIngCtc.setUceIngCtcMedicamentoList(uceIngCtcMedicamentoListNew);
            List<UceIngCtcCie10> attachedUceIngCtcCie10ListNew = new ArrayList<UceIngCtcCie10>();
            for (UceIngCtcCie10 uceIngCtcCie10ListNewUceIngCtcCie10ToAttach : uceIngCtcCie10ListNew) {
                uceIngCtcCie10ListNewUceIngCtcCie10ToAttach = em.getReference(uceIngCtcCie10ListNewUceIngCtcCie10ToAttach.getClass(), uceIngCtcCie10ListNewUceIngCtcCie10ToAttach.getId());
                attachedUceIngCtcCie10ListNew.add(uceIngCtcCie10ListNewUceIngCtcCie10ToAttach);
            }
            uceIngCtcCie10ListNew = attachedUceIngCtcCie10ListNew;
            uceIngCtc.setUceIngCtcCie10List(uceIngCtcCie10ListNew);
            List<UceIngCtcProcedimientos> attachedUceIngCtcProcedimientosListNew = new ArrayList<UceIngCtcProcedimientos>();
            for (UceIngCtcProcedimientos uceIngCtcProcedimientosListNewUceIngCtcProcedimientosToAttach : uceIngCtcProcedimientosListNew) {
                uceIngCtcProcedimientosListNewUceIngCtcProcedimientosToAttach = em.getReference(uceIngCtcProcedimientosListNewUceIngCtcProcedimientosToAttach.getClass(), uceIngCtcProcedimientosListNewUceIngCtcProcedimientosToAttach.getId());
                attachedUceIngCtcProcedimientosListNew.add(uceIngCtcProcedimientosListNewUceIngCtcProcedimientosToAttach);
            }
            uceIngCtcProcedimientosListNew = attachedUceIngCtcProcedimientosListNew;
            uceIngCtc.setUceIngCtcProcedimientosList(uceIngCtcProcedimientosListNew);
            uceIngCtc = em.merge(uceIngCtc);
            for (UceIngCtcMedicamento uceIngCtcMedicamentoListNewUceIngCtcMedicamento : uceIngCtcMedicamentoListNew) {
                if (!uceIngCtcMedicamentoListOld.contains(uceIngCtcMedicamentoListNewUceIngCtcMedicamento)) {
                    UceIngCtc oldIdctcOfUceIngCtcMedicamentoListNewUceIngCtcMedicamento = uceIngCtcMedicamentoListNewUceIngCtcMedicamento.getIdctc();
                    uceIngCtcMedicamentoListNewUceIngCtcMedicamento.setIdctc(uceIngCtc);
                    uceIngCtcMedicamentoListNewUceIngCtcMedicamento = em.merge(uceIngCtcMedicamentoListNewUceIngCtcMedicamento);
                    if (oldIdctcOfUceIngCtcMedicamentoListNewUceIngCtcMedicamento != null && !oldIdctcOfUceIngCtcMedicamentoListNewUceIngCtcMedicamento.equals(uceIngCtc)) {
                        oldIdctcOfUceIngCtcMedicamentoListNewUceIngCtcMedicamento.getUceIngCtcMedicamentoList().remove(uceIngCtcMedicamentoListNewUceIngCtcMedicamento);
                        oldIdctcOfUceIngCtcMedicamentoListNewUceIngCtcMedicamento = em.merge(oldIdctcOfUceIngCtcMedicamentoListNewUceIngCtcMedicamento);
                    }
                }
            }
            for (UceIngCtcCie10 uceIngCtcCie10ListNewUceIngCtcCie10 : uceIngCtcCie10ListNew) {
                if (!uceIngCtcCie10ListOld.contains(uceIngCtcCie10ListNewUceIngCtcCie10)) {
                    UceIngCtc oldIdctcOfUceIngCtcCie10ListNewUceIngCtcCie10 = uceIngCtcCie10ListNewUceIngCtcCie10.getIdctc();
                    uceIngCtcCie10ListNewUceIngCtcCie10.setIdctc(uceIngCtc);
                    uceIngCtcCie10ListNewUceIngCtcCie10 = em.merge(uceIngCtcCie10ListNewUceIngCtcCie10);
                    if (oldIdctcOfUceIngCtcCie10ListNewUceIngCtcCie10 != null && !oldIdctcOfUceIngCtcCie10ListNewUceIngCtcCie10.equals(uceIngCtc)) {
                        oldIdctcOfUceIngCtcCie10ListNewUceIngCtcCie10.getUceIngCtcCie10List().remove(uceIngCtcCie10ListNewUceIngCtcCie10);
                        oldIdctcOfUceIngCtcCie10ListNewUceIngCtcCie10 = em.merge(oldIdctcOfUceIngCtcCie10ListNewUceIngCtcCie10);
                    }
                }
            }
            for (UceIngCtcProcedimientos uceIngCtcProcedimientosListNewUceIngCtcProcedimientos : uceIngCtcProcedimientosListNew) {
                if (!uceIngCtcProcedimientosListOld.contains(uceIngCtcProcedimientosListNewUceIngCtcProcedimientos)) {
                    UceIngCtc oldIdctcOfUceIngCtcProcedimientosListNewUceIngCtcProcedimientos = uceIngCtcProcedimientosListNewUceIngCtcProcedimientos.getIdctc();
                    uceIngCtcProcedimientosListNewUceIngCtcProcedimientos.setIdctc(uceIngCtc);
                    uceIngCtcProcedimientosListNewUceIngCtcProcedimientos = em.merge(uceIngCtcProcedimientosListNewUceIngCtcProcedimientos);
                    if (oldIdctcOfUceIngCtcProcedimientosListNewUceIngCtcProcedimientos != null && !oldIdctcOfUceIngCtcProcedimientosListNewUceIngCtcProcedimientos.equals(uceIngCtc)) {
                        oldIdctcOfUceIngCtcProcedimientosListNewUceIngCtcProcedimientos.getUceIngCtcProcedimientosList().remove(uceIngCtcProcedimientosListNewUceIngCtcProcedimientos);
                        oldIdctcOfUceIngCtcProcedimientosListNewUceIngCtcProcedimientos = em.merge(oldIdctcOfUceIngCtcProcedimientosListNewUceIngCtcProcedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceIngCtc.getId();
                if (findUceIngCtc(id) == null) {
                    throw new NonexistentEntityException("The uceIngCtc with id " + id + " no longer exists.");
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
            UceIngCtc uceIngCtc;
            try {
                uceIngCtc = em.getReference(UceIngCtc.class, id);
                uceIngCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceIngCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UceIngCtcMedicamento> uceIngCtcMedicamentoListOrphanCheck = uceIngCtc.getUceIngCtcMedicamentoList();
            for (UceIngCtcMedicamento uceIngCtcMedicamentoListOrphanCheckUceIngCtcMedicamento : uceIngCtcMedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceIngCtc (" + uceIngCtc + ") cannot be destroyed since the UceIngCtcMedicamento " + uceIngCtcMedicamentoListOrphanCheckUceIngCtcMedicamento + " in its uceIngCtcMedicamentoList field has a non-nullable idctc field.");
            }
            List<UceIngCtcCie10> uceIngCtcCie10ListOrphanCheck = uceIngCtc.getUceIngCtcCie10List();
            for (UceIngCtcCie10 uceIngCtcCie10ListOrphanCheckUceIngCtcCie10 : uceIngCtcCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceIngCtc (" + uceIngCtc + ") cannot be destroyed since the UceIngCtcCie10 " + uceIngCtcCie10ListOrphanCheckUceIngCtcCie10 + " in its uceIngCtcCie10List field has a non-nullable idctc field.");
            }
            List<UceIngCtcProcedimientos> uceIngCtcProcedimientosListOrphanCheck = uceIngCtc.getUceIngCtcProcedimientosList();
            for (UceIngCtcProcedimientos uceIngCtcProcedimientosListOrphanCheckUceIngCtcProcedimientos : uceIngCtcProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceIngCtc (" + uceIngCtc + ") cannot be destroyed since the UceIngCtcProcedimientos " + uceIngCtcProcedimientosListOrphanCheckUceIngCtcProcedimientos + " in its uceIngCtcProcedimientosList field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uceIngCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceIngCtc> findUceIngCtcEntities() {
        return findUceIngCtcEntities(true, -1, -1);
    }

    public List<UceIngCtc> findUceIngCtcEntities(int maxResults, int firstResult) {
        return findUceIngCtcEntities(false, maxResults, firstResult);
    }

    private List<UceIngCtc> findUceIngCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceIngCtc.class));
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

    public UceIngCtc findUceIngCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceIngCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceIngCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceIngCtc> rt = cq.from(UceIngCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
