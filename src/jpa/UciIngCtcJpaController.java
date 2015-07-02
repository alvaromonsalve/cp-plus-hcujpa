/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciIngCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciIngCtcMedicamento;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciIngCtcProcedimientos;
import entidades_EJB.UciIngCtcCie10;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciIngCtcJpaController implements Serializable {

    public UciIngCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciIngCtc uciIngCtc) {
        if (uciIngCtc.getUciIngCtcMedicamentoList() == null) {
            uciIngCtc.setUciIngCtcMedicamentoList(new ArrayList<UciIngCtcMedicamento>());
        }
        if (uciIngCtc.getUciIngCtcProcedimientosList() == null) {
            uciIngCtc.setUciIngCtcProcedimientosList(new ArrayList<UciIngCtcProcedimientos>());
        }
        if (uciIngCtc.getUciIngCtcCie10List() == null) {
            uciIngCtc.setUciIngCtcCie10List(new ArrayList<UciIngCtcCie10>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UciIngCtcMedicamento> attachedUciIngCtcMedicamentoList = new ArrayList<UciIngCtcMedicamento>();
            for (UciIngCtcMedicamento uciIngCtcMedicamentoListUciIngCtcMedicamentoToAttach : uciIngCtc.getUciIngCtcMedicamentoList()) {
                uciIngCtcMedicamentoListUciIngCtcMedicamentoToAttach = em.getReference(uciIngCtcMedicamentoListUciIngCtcMedicamentoToAttach.getClass(), uciIngCtcMedicamentoListUciIngCtcMedicamentoToAttach.getId());
                attachedUciIngCtcMedicamentoList.add(uciIngCtcMedicamentoListUciIngCtcMedicamentoToAttach);
            }
            uciIngCtc.setUciIngCtcMedicamentoList(attachedUciIngCtcMedicamentoList);
            List<UciIngCtcProcedimientos> attachedUciIngCtcProcedimientosList = new ArrayList<UciIngCtcProcedimientos>();
            for (UciIngCtcProcedimientos uciIngCtcProcedimientosListUciIngCtcProcedimientosToAttach : uciIngCtc.getUciIngCtcProcedimientosList()) {
                uciIngCtcProcedimientosListUciIngCtcProcedimientosToAttach = em.getReference(uciIngCtcProcedimientosListUciIngCtcProcedimientosToAttach.getClass(), uciIngCtcProcedimientosListUciIngCtcProcedimientosToAttach.getId());
                attachedUciIngCtcProcedimientosList.add(uciIngCtcProcedimientosListUciIngCtcProcedimientosToAttach);
            }
            uciIngCtc.setUciIngCtcProcedimientosList(attachedUciIngCtcProcedimientosList);
            List<UciIngCtcCie10> attachedUciIngCtcCie10List = new ArrayList<UciIngCtcCie10>();
            for (UciIngCtcCie10 uciIngCtcCie10ListUciIngCtcCie10ToAttach : uciIngCtc.getUciIngCtcCie10List()) {
                uciIngCtcCie10ListUciIngCtcCie10ToAttach = em.getReference(uciIngCtcCie10ListUciIngCtcCie10ToAttach.getClass(), uciIngCtcCie10ListUciIngCtcCie10ToAttach.getId());
                attachedUciIngCtcCie10List.add(uciIngCtcCie10ListUciIngCtcCie10ToAttach);
            }
            uciIngCtc.setUciIngCtcCie10List(attachedUciIngCtcCie10List);
            em.persist(uciIngCtc);
            for (UciIngCtcMedicamento uciIngCtcMedicamentoListUciIngCtcMedicamento : uciIngCtc.getUciIngCtcMedicamentoList()) {
                UciIngCtc oldIdctcOfUciIngCtcMedicamentoListUciIngCtcMedicamento = uciIngCtcMedicamentoListUciIngCtcMedicamento.getIdctc();
                uciIngCtcMedicamentoListUciIngCtcMedicamento.setIdctc(uciIngCtc);
                uciIngCtcMedicamentoListUciIngCtcMedicamento = em.merge(uciIngCtcMedicamentoListUciIngCtcMedicamento);
                if (oldIdctcOfUciIngCtcMedicamentoListUciIngCtcMedicamento != null) {
                    oldIdctcOfUciIngCtcMedicamentoListUciIngCtcMedicamento.getUciIngCtcMedicamentoList().remove(uciIngCtcMedicamentoListUciIngCtcMedicamento);
                    oldIdctcOfUciIngCtcMedicamentoListUciIngCtcMedicamento = em.merge(oldIdctcOfUciIngCtcMedicamentoListUciIngCtcMedicamento);
                }
            }
            for (UciIngCtcProcedimientos uciIngCtcProcedimientosListUciIngCtcProcedimientos : uciIngCtc.getUciIngCtcProcedimientosList()) {
                UciIngCtc oldIdctcOfUciIngCtcProcedimientosListUciIngCtcProcedimientos = uciIngCtcProcedimientosListUciIngCtcProcedimientos.getIdctc();
                uciIngCtcProcedimientosListUciIngCtcProcedimientos.setIdctc(uciIngCtc);
                uciIngCtcProcedimientosListUciIngCtcProcedimientos = em.merge(uciIngCtcProcedimientosListUciIngCtcProcedimientos);
                if (oldIdctcOfUciIngCtcProcedimientosListUciIngCtcProcedimientos != null) {
                    oldIdctcOfUciIngCtcProcedimientosListUciIngCtcProcedimientos.getUciIngCtcProcedimientosList().remove(uciIngCtcProcedimientosListUciIngCtcProcedimientos);
                    oldIdctcOfUciIngCtcProcedimientosListUciIngCtcProcedimientos = em.merge(oldIdctcOfUciIngCtcProcedimientosListUciIngCtcProcedimientos);
                }
            }
            for (UciIngCtcCie10 uciIngCtcCie10ListUciIngCtcCie10 : uciIngCtc.getUciIngCtcCie10List()) {
                UciIngCtc oldIdctcOfUciIngCtcCie10ListUciIngCtcCie10 = uciIngCtcCie10ListUciIngCtcCie10.getIdctc();
                uciIngCtcCie10ListUciIngCtcCie10.setIdctc(uciIngCtc);
                uciIngCtcCie10ListUciIngCtcCie10 = em.merge(uciIngCtcCie10ListUciIngCtcCie10);
                if (oldIdctcOfUciIngCtcCie10ListUciIngCtcCie10 != null) {
                    oldIdctcOfUciIngCtcCie10ListUciIngCtcCie10.getUciIngCtcCie10List().remove(uciIngCtcCie10ListUciIngCtcCie10);
                    oldIdctcOfUciIngCtcCie10ListUciIngCtcCie10 = em.merge(oldIdctcOfUciIngCtcCie10ListUciIngCtcCie10);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciIngCtc uciIngCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciIngCtc persistentUciIngCtc = em.find(UciIngCtc.class, uciIngCtc.getId());
            List<UciIngCtcMedicamento> uciIngCtcMedicamentoListOld = persistentUciIngCtc.getUciIngCtcMedicamentoList();
            List<UciIngCtcMedicamento> uciIngCtcMedicamentoListNew = uciIngCtc.getUciIngCtcMedicamentoList();
            List<UciIngCtcProcedimientos> uciIngCtcProcedimientosListOld = persistentUciIngCtc.getUciIngCtcProcedimientosList();
            List<UciIngCtcProcedimientos> uciIngCtcProcedimientosListNew = uciIngCtc.getUciIngCtcProcedimientosList();
            List<UciIngCtcCie10> uciIngCtcCie10ListOld = persistentUciIngCtc.getUciIngCtcCie10List();
            List<UciIngCtcCie10> uciIngCtcCie10ListNew = uciIngCtc.getUciIngCtcCie10List();
            List<String> illegalOrphanMessages = null;
            for (UciIngCtcMedicamento uciIngCtcMedicamentoListOldUciIngCtcMedicamento : uciIngCtcMedicamentoListOld) {
                if (!uciIngCtcMedicamentoListNew.contains(uciIngCtcMedicamentoListOldUciIngCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciIngCtcMedicamento " + uciIngCtcMedicamentoListOldUciIngCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            for (UciIngCtcProcedimientos uciIngCtcProcedimientosListOldUciIngCtcProcedimientos : uciIngCtcProcedimientosListOld) {
                if (!uciIngCtcProcedimientosListNew.contains(uciIngCtcProcedimientosListOldUciIngCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciIngCtcProcedimientos " + uciIngCtcProcedimientosListOldUciIngCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            for (UciIngCtcCie10 uciIngCtcCie10ListOldUciIngCtcCie10 : uciIngCtcCie10ListOld) {
                if (!uciIngCtcCie10ListNew.contains(uciIngCtcCie10ListOldUciIngCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciIngCtcCie10 " + uciIngCtcCie10ListOldUciIngCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UciIngCtcMedicamento> attachedUciIngCtcMedicamentoListNew = new ArrayList<UciIngCtcMedicamento>();
            for (UciIngCtcMedicamento uciIngCtcMedicamentoListNewUciIngCtcMedicamentoToAttach : uciIngCtcMedicamentoListNew) {
                uciIngCtcMedicamentoListNewUciIngCtcMedicamentoToAttach = em.getReference(uciIngCtcMedicamentoListNewUciIngCtcMedicamentoToAttach.getClass(), uciIngCtcMedicamentoListNewUciIngCtcMedicamentoToAttach.getId());
                attachedUciIngCtcMedicamentoListNew.add(uciIngCtcMedicamentoListNewUciIngCtcMedicamentoToAttach);
            }
            uciIngCtcMedicamentoListNew = attachedUciIngCtcMedicamentoListNew;
            uciIngCtc.setUciIngCtcMedicamentoList(uciIngCtcMedicamentoListNew);
            List<UciIngCtcProcedimientos> attachedUciIngCtcProcedimientosListNew = new ArrayList<UciIngCtcProcedimientos>();
            for (UciIngCtcProcedimientos uciIngCtcProcedimientosListNewUciIngCtcProcedimientosToAttach : uciIngCtcProcedimientosListNew) {
                uciIngCtcProcedimientosListNewUciIngCtcProcedimientosToAttach = em.getReference(uciIngCtcProcedimientosListNewUciIngCtcProcedimientosToAttach.getClass(), uciIngCtcProcedimientosListNewUciIngCtcProcedimientosToAttach.getId());
                attachedUciIngCtcProcedimientosListNew.add(uciIngCtcProcedimientosListNewUciIngCtcProcedimientosToAttach);
            }
            uciIngCtcProcedimientosListNew = attachedUciIngCtcProcedimientosListNew;
            uciIngCtc.setUciIngCtcProcedimientosList(uciIngCtcProcedimientosListNew);
            List<UciIngCtcCie10> attachedUciIngCtcCie10ListNew = new ArrayList<UciIngCtcCie10>();
            for (UciIngCtcCie10 uciIngCtcCie10ListNewUciIngCtcCie10ToAttach : uciIngCtcCie10ListNew) {
                uciIngCtcCie10ListNewUciIngCtcCie10ToAttach = em.getReference(uciIngCtcCie10ListNewUciIngCtcCie10ToAttach.getClass(), uciIngCtcCie10ListNewUciIngCtcCie10ToAttach.getId());
                attachedUciIngCtcCie10ListNew.add(uciIngCtcCie10ListNewUciIngCtcCie10ToAttach);
            }
            uciIngCtcCie10ListNew = attachedUciIngCtcCie10ListNew;
            uciIngCtc.setUciIngCtcCie10List(uciIngCtcCie10ListNew);
            uciIngCtc = em.merge(uciIngCtc);
            for (UciIngCtcMedicamento uciIngCtcMedicamentoListNewUciIngCtcMedicamento : uciIngCtcMedicamentoListNew) {
                if (!uciIngCtcMedicamentoListOld.contains(uciIngCtcMedicamentoListNewUciIngCtcMedicamento)) {
                    UciIngCtc oldIdctcOfUciIngCtcMedicamentoListNewUciIngCtcMedicamento = uciIngCtcMedicamentoListNewUciIngCtcMedicamento.getIdctc();
                    uciIngCtcMedicamentoListNewUciIngCtcMedicamento.setIdctc(uciIngCtc);
                    uciIngCtcMedicamentoListNewUciIngCtcMedicamento = em.merge(uciIngCtcMedicamentoListNewUciIngCtcMedicamento);
                    if (oldIdctcOfUciIngCtcMedicamentoListNewUciIngCtcMedicamento != null && !oldIdctcOfUciIngCtcMedicamentoListNewUciIngCtcMedicamento.equals(uciIngCtc)) {
                        oldIdctcOfUciIngCtcMedicamentoListNewUciIngCtcMedicamento.getUciIngCtcMedicamentoList().remove(uciIngCtcMedicamentoListNewUciIngCtcMedicamento);
                        oldIdctcOfUciIngCtcMedicamentoListNewUciIngCtcMedicamento = em.merge(oldIdctcOfUciIngCtcMedicamentoListNewUciIngCtcMedicamento);
                    }
                }
            }
            for (UciIngCtcProcedimientos uciIngCtcProcedimientosListNewUciIngCtcProcedimientos : uciIngCtcProcedimientosListNew) {
                if (!uciIngCtcProcedimientosListOld.contains(uciIngCtcProcedimientosListNewUciIngCtcProcedimientos)) {
                    UciIngCtc oldIdctcOfUciIngCtcProcedimientosListNewUciIngCtcProcedimientos = uciIngCtcProcedimientosListNewUciIngCtcProcedimientos.getIdctc();
                    uciIngCtcProcedimientosListNewUciIngCtcProcedimientos.setIdctc(uciIngCtc);
                    uciIngCtcProcedimientosListNewUciIngCtcProcedimientos = em.merge(uciIngCtcProcedimientosListNewUciIngCtcProcedimientos);
                    if (oldIdctcOfUciIngCtcProcedimientosListNewUciIngCtcProcedimientos != null && !oldIdctcOfUciIngCtcProcedimientosListNewUciIngCtcProcedimientos.equals(uciIngCtc)) {
                        oldIdctcOfUciIngCtcProcedimientosListNewUciIngCtcProcedimientos.getUciIngCtcProcedimientosList().remove(uciIngCtcProcedimientosListNewUciIngCtcProcedimientos);
                        oldIdctcOfUciIngCtcProcedimientosListNewUciIngCtcProcedimientos = em.merge(oldIdctcOfUciIngCtcProcedimientosListNewUciIngCtcProcedimientos);
                    }
                }
            }
            for (UciIngCtcCie10 uciIngCtcCie10ListNewUciIngCtcCie10 : uciIngCtcCie10ListNew) {
                if (!uciIngCtcCie10ListOld.contains(uciIngCtcCie10ListNewUciIngCtcCie10)) {
                    UciIngCtc oldIdctcOfUciIngCtcCie10ListNewUciIngCtcCie10 = uciIngCtcCie10ListNewUciIngCtcCie10.getIdctc();
                    uciIngCtcCie10ListNewUciIngCtcCie10.setIdctc(uciIngCtc);
                    uciIngCtcCie10ListNewUciIngCtcCie10 = em.merge(uciIngCtcCie10ListNewUciIngCtcCie10);
                    if (oldIdctcOfUciIngCtcCie10ListNewUciIngCtcCie10 != null && !oldIdctcOfUciIngCtcCie10ListNewUciIngCtcCie10.equals(uciIngCtc)) {
                        oldIdctcOfUciIngCtcCie10ListNewUciIngCtcCie10.getUciIngCtcCie10List().remove(uciIngCtcCie10ListNewUciIngCtcCie10);
                        oldIdctcOfUciIngCtcCie10ListNewUciIngCtcCie10 = em.merge(oldIdctcOfUciIngCtcCie10ListNewUciIngCtcCie10);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciIngCtc.getId();
                if (findUciIngCtc(id) == null) {
                    throw new NonexistentEntityException("The uciIngCtc with id " + id + " no longer exists.");
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
            UciIngCtc uciIngCtc;
            try {
                uciIngCtc = em.getReference(UciIngCtc.class, id);
                uciIngCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciIngCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciIngCtcMedicamento> uciIngCtcMedicamentoListOrphanCheck = uciIngCtc.getUciIngCtcMedicamentoList();
            for (UciIngCtcMedicamento uciIngCtcMedicamentoListOrphanCheckUciIngCtcMedicamento : uciIngCtcMedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciIngCtc (" + uciIngCtc + ") cannot be destroyed since the UciIngCtcMedicamento " + uciIngCtcMedicamentoListOrphanCheckUciIngCtcMedicamento + " in its uciIngCtcMedicamentoList field has a non-nullable idctc field.");
            }
            List<UciIngCtcProcedimientos> uciIngCtcProcedimientosListOrphanCheck = uciIngCtc.getUciIngCtcProcedimientosList();
            for (UciIngCtcProcedimientos uciIngCtcProcedimientosListOrphanCheckUciIngCtcProcedimientos : uciIngCtcProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciIngCtc (" + uciIngCtc + ") cannot be destroyed since the UciIngCtcProcedimientos " + uciIngCtcProcedimientosListOrphanCheckUciIngCtcProcedimientos + " in its uciIngCtcProcedimientosList field has a non-nullable idctc field.");
            }
            List<UciIngCtcCie10> uciIngCtcCie10ListOrphanCheck = uciIngCtc.getUciIngCtcCie10List();
            for (UciIngCtcCie10 uciIngCtcCie10ListOrphanCheckUciIngCtcCie10 : uciIngCtcCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciIngCtc (" + uciIngCtc + ") cannot be destroyed since the UciIngCtcCie10 " + uciIngCtcCie10ListOrphanCheckUciIngCtcCie10 + " in its uciIngCtcCie10List field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciIngCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciIngCtc> findUciIngCtcEntities() {
        return findUciIngCtcEntities(true, -1, -1);
    }

    public List<UciIngCtc> findUciIngCtcEntities(int maxResults, int firstResult) {
        return findUciIngCtcEntities(false, maxResults, firstResult);
    }

    private List<UciIngCtc> findUciIngCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciIngCtc.class));
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

    public UciIngCtc findUciIngCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciIngCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciIngCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciIngCtc> rt = cq.from(UciIngCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
