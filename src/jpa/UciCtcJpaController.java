/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.UciCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.UciCtcCie10;
import java.util.HashSet;
import java.util.Set;
import entidades_EJB.UciCtcMedicamento;
import entidades_EJB.UciCtcProcedimientos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class UciCtcJpaController implements Serializable {

    public UciCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciCtc uciCtc) {
        if (uciCtc.getUciCtcCie10Set() == null) {
            uciCtc.setUciCtcCie10Set(new HashSet<UciCtcCie10>());
        }
        if (uciCtc.getUciCtcMedicamentoSet() == null) {
            uciCtc.setUciCtcMedicamentoSet(new HashSet<UciCtcMedicamento>());
        }
        if (uciCtc.getUciCtcProcedimientosSet() == null) {
            uciCtc.setUciCtcProcedimientosSet(new HashSet<UciCtcProcedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<UciCtcCie10> attachedUciCtcCie10Set = new HashSet<UciCtcCie10>();
            for (UciCtcCie10 uciCtcCie10SetUciCtcCie10ToAttach : uciCtc.getUciCtcCie10Set()) {
                uciCtcCie10SetUciCtcCie10ToAttach = em.getReference(uciCtcCie10SetUciCtcCie10ToAttach.getClass(), uciCtcCie10SetUciCtcCie10ToAttach.getId());
                attachedUciCtcCie10Set.add(uciCtcCie10SetUciCtcCie10ToAttach);
            }
            uciCtc.setUciCtcCie10Set(attachedUciCtcCie10Set);
            Set<UciCtcMedicamento> attachedUciCtcMedicamentoSet = new HashSet<UciCtcMedicamento>();
            for (UciCtcMedicamento uciCtcMedicamentoSetUciCtcMedicamentoToAttach : uciCtc.getUciCtcMedicamentoSet()) {
                uciCtcMedicamentoSetUciCtcMedicamentoToAttach = em.getReference(uciCtcMedicamentoSetUciCtcMedicamentoToAttach.getClass(), uciCtcMedicamentoSetUciCtcMedicamentoToAttach.getId());
                attachedUciCtcMedicamentoSet.add(uciCtcMedicamentoSetUciCtcMedicamentoToAttach);
            }
            uciCtc.setUciCtcMedicamentoSet(attachedUciCtcMedicamentoSet);
            Set<UciCtcProcedimientos> attachedUciCtcProcedimientosSet = new HashSet<UciCtcProcedimientos>();
            for (UciCtcProcedimientos uciCtcProcedimientosSetUciCtcProcedimientosToAttach : uciCtc.getUciCtcProcedimientosSet()) {
                uciCtcProcedimientosSetUciCtcProcedimientosToAttach = em.getReference(uciCtcProcedimientosSetUciCtcProcedimientosToAttach.getClass(), uciCtcProcedimientosSetUciCtcProcedimientosToAttach.getId());
                attachedUciCtcProcedimientosSet.add(uciCtcProcedimientosSetUciCtcProcedimientosToAttach);
            }
            uciCtc.setUciCtcProcedimientosSet(attachedUciCtcProcedimientosSet);
            em.persist(uciCtc);
            for (UciCtcCie10 uciCtcCie10SetUciCtcCie10 : uciCtc.getUciCtcCie10Set()) {
                UciCtc oldIdctcOfUciCtcCie10SetUciCtcCie10 = uciCtcCie10SetUciCtcCie10.getIdctc();
                uciCtcCie10SetUciCtcCie10.setIdctc(uciCtc);
                uciCtcCie10SetUciCtcCie10 = em.merge(uciCtcCie10SetUciCtcCie10);
                if (oldIdctcOfUciCtcCie10SetUciCtcCie10 != null) {
                    oldIdctcOfUciCtcCie10SetUciCtcCie10.getUciCtcCie10Set().remove(uciCtcCie10SetUciCtcCie10);
                    oldIdctcOfUciCtcCie10SetUciCtcCie10 = em.merge(oldIdctcOfUciCtcCie10SetUciCtcCie10);
                }
            }
            for (UciCtcMedicamento uciCtcMedicamentoSetUciCtcMedicamento : uciCtc.getUciCtcMedicamentoSet()) {
                UciCtc oldIdctcOfUciCtcMedicamentoSetUciCtcMedicamento = uciCtcMedicamentoSetUciCtcMedicamento.getIdctc();
                uciCtcMedicamentoSetUciCtcMedicamento.setIdctc(uciCtc);
                uciCtcMedicamentoSetUciCtcMedicamento = em.merge(uciCtcMedicamentoSetUciCtcMedicamento);
                if (oldIdctcOfUciCtcMedicamentoSetUciCtcMedicamento != null) {
                    oldIdctcOfUciCtcMedicamentoSetUciCtcMedicamento.getUciCtcMedicamentoSet().remove(uciCtcMedicamentoSetUciCtcMedicamento);
                    oldIdctcOfUciCtcMedicamentoSetUciCtcMedicamento = em.merge(oldIdctcOfUciCtcMedicamentoSetUciCtcMedicamento);
                }
            }
            for (UciCtcProcedimientos uciCtcProcedimientosSetUciCtcProcedimientos : uciCtc.getUciCtcProcedimientosSet()) {
                UciCtc oldIdctcOfUciCtcProcedimientosSetUciCtcProcedimientos = uciCtcProcedimientosSetUciCtcProcedimientos.getIdctc();
                uciCtcProcedimientosSetUciCtcProcedimientos.setIdctc(uciCtc);
                uciCtcProcedimientosSetUciCtcProcedimientos = em.merge(uciCtcProcedimientosSetUciCtcProcedimientos);
                if (oldIdctcOfUciCtcProcedimientosSetUciCtcProcedimientos != null) {
                    oldIdctcOfUciCtcProcedimientosSetUciCtcProcedimientos.getUciCtcProcedimientosSet().remove(uciCtcProcedimientosSetUciCtcProcedimientos);
                    oldIdctcOfUciCtcProcedimientosSetUciCtcProcedimientos = em.merge(oldIdctcOfUciCtcProcedimientosSetUciCtcProcedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciCtc uciCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCtc persistentUciCtc = em.find(UciCtc.class, uciCtc.getId());
            Set<UciCtcCie10> uciCtcCie10SetOld = persistentUciCtc.getUciCtcCie10Set();
            Set<UciCtcCie10> uciCtcCie10SetNew = uciCtc.getUciCtcCie10Set();
            Set<UciCtcMedicamento> uciCtcMedicamentoSetOld = persistentUciCtc.getUciCtcMedicamentoSet();
            Set<UciCtcMedicamento> uciCtcMedicamentoSetNew = uciCtc.getUciCtcMedicamentoSet();
            Set<UciCtcProcedimientos> uciCtcProcedimientosSetOld = persistentUciCtc.getUciCtcProcedimientosSet();
            Set<UciCtcProcedimientos> uciCtcProcedimientosSetNew = uciCtc.getUciCtcProcedimientosSet();
            List<String> illegalOrphanMessages = null;
            for (UciCtcCie10 uciCtcCie10SetOldUciCtcCie10 : uciCtcCie10SetOld) {
                if (!uciCtcCie10SetNew.contains(uciCtcCie10SetOldUciCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciCtcCie10 " + uciCtcCie10SetOldUciCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (UciCtcMedicamento uciCtcMedicamentoSetOldUciCtcMedicamento : uciCtcMedicamentoSetOld) {
                if (!uciCtcMedicamentoSetNew.contains(uciCtcMedicamentoSetOldUciCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciCtcMedicamento " + uciCtcMedicamentoSetOldUciCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            for (UciCtcProcedimientos uciCtcProcedimientosSetOldUciCtcProcedimientos : uciCtcProcedimientosSetOld) {
                if (!uciCtcProcedimientosSetNew.contains(uciCtcProcedimientosSetOldUciCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciCtcProcedimientos " + uciCtcProcedimientosSetOldUciCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<UciCtcCie10> attachedUciCtcCie10SetNew = new HashSet<UciCtcCie10>();
            for (UciCtcCie10 uciCtcCie10SetNewUciCtcCie10ToAttach : uciCtcCie10SetNew) {
                uciCtcCie10SetNewUciCtcCie10ToAttach = em.getReference(uciCtcCie10SetNewUciCtcCie10ToAttach.getClass(), uciCtcCie10SetNewUciCtcCie10ToAttach.getId());
                attachedUciCtcCie10SetNew.add(uciCtcCie10SetNewUciCtcCie10ToAttach);
            }
            uciCtcCie10SetNew = attachedUciCtcCie10SetNew;
            uciCtc.setUciCtcCie10Set(uciCtcCie10SetNew);
            Set<UciCtcMedicamento> attachedUciCtcMedicamentoSetNew = new HashSet<UciCtcMedicamento>();
            for (UciCtcMedicamento uciCtcMedicamentoSetNewUciCtcMedicamentoToAttach : uciCtcMedicamentoSetNew) {
                uciCtcMedicamentoSetNewUciCtcMedicamentoToAttach = em.getReference(uciCtcMedicamentoSetNewUciCtcMedicamentoToAttach.getClass(), uciCtcMedicamentoSetNewUciCtcMedicamentoToAttach.getId());
                attachedUciCtcMedicamentoSetNew.add(uciCtcMedicamentoSetNewUciCtcMedicamentoToAttach);
            }
            uciCtcMedicamentoSetNew = attachedUciCtcMedicamentoSetNew;
            uciCtc.setUciCtcMedicamentoSet(uciCtcMedicamentoSetNew);
            Set<UciCtcProcedimientos> attachedUciCtcProcedimientosSetNew = new HashSet<UciCtcProcedimientos>();
            for (UciCtcProcedimientos uciCtcProcedimientosSetNewUciCtcProcedimientosToAttach : uciCtcProcedimientosSetNew) {
                uciCtcProcedimientosSetNewUciCtcProcedimientosToAttach = em.getReference(uciCtcProcedimientosSetNewUciCtcProcedimientosToAttach.getClass(), uciCtcProcedimientosSetNewUciCtcProcedimientosToAttach.getId());
                attachedUciCtcProcedimientosSetNew.add(uciCtcProcedimientosSetNewUciCtcProcedimientosToAttach);
            }
            uciCtcProcedimientosSetNew = attachedUciCtcProcedimientosSetNew;
            uciCtc.setUciCtcProcedimientosSet(uciCtcProcedimientosSetNew);
            uciCtc = em.merge(uciCtc);
            for (UciCtcCie10 uciCtcCie10SetNewUciCtcCie10 : uciCtcCie10SetNew) {
                if (!uciCtcCie10SetOld.contains(uciCtcCie10SetNewUciCtcCie10)) {
                    UciCtc oldIdctcOfUciCtcCie10SetNewUciCtcCie10 = uciCtcCie10SetNewUciCtcCie10.getIdctc();
                    uciCtcCie10SetNewUciCtcCie10.setIdctc(uciCtc);
                    uciCtcCie10SetNewUciCtcCie10 = em.merge(uciCtcCie10SetNewUciCtcCie10);
                    if (oldIdctcOfUciCtcCie10SetNewUciCtcCie10 != null && !oldIdctcOfUciCtcCie10SetNewUciCtcCie10.equals(uciCtc)) {
                        oldIdctcOfUciCtcCie10SetNewUciCtcCie10.getUciCtcCie10Set().remove(uciCtcCie10SetNewUciCtcCie10);
                        oldIdctcOfUciCtcCie10SetNewUciCtcCie10 = em.merge(oldIdctcOfUciCtcCie10SetNewUciCtcCie10);
                    }
                }
            }
            for (UciCtcMedicamento uciCtcMedicamentoSetNewUciCtcMedicamento : uciCtcMedicamentoSetNew) {
                if (!uciCtcMedicamentoSetOld.contains(uciCtcMedicamentoSetNewUciCtcMedicamento)) {
                    UciCtc oldIdctcOfUciCtcMedicamentoSetNewUciCtcMedicamento = uciCtcMedicamentoSetNewUciCtcMedicamento.getIdctc();
                    uciCtcMedicamentoSetNewUciCtcMedicamento.setIdctc(uciCtc);
                    uciCtcMedicamentoSetNewUciCtcMedicamento = em.merge(uciCtcMedicamentoSetNewUciCtcMedicamento);
                    if (oldIdctcOfUciCtcMedicamentoSetNewUciCtcMedicamento != null && !oldIdctcOfUciCtcMedicamentoSetNewUciCtcMedicamento.equals(uciCtc)) {
                        oldIdctcOfUciCtcMedicamentoSetNewUciCtcMedicamento.getUciCtcMedicamentoSet().remove(uciCtcMedicamentoSetNewUciCtcMedicamento);
                        oldIdctcOfUciCtcMedicamentoSetNewUciCtcMedicamento = em.merge(oldIdctcOfUciCtcMedicamentoSetNewUciCtcMedicamento);
                    }
                }
            }
            for (UciCtcProcedimientos uciCtcProcedimientosSetNewUciCtcProcedimientos : uciCtcProcedimientosSetNew) {
                if (!uciCtcProcedimientosSetOld.contains(uciCtcProcedimientosSetNewUciCtcProcedimientos)) {
                    UciCtc oldIdctcOfUciCtcProcedimientosSetNewUciCtcProcedimientos = uciCtcProcedimientosSetNewUciCtcProcedimientos.getIdctc();
                    uciCtcProcedimientosSetNewUciCtcProcedimientos.setIdctc(uciCtc);
                    uciCtcProcedimientosSetNewUciCtcProcedimientos = em.merge(uciCtcProcedimientosSetNewUciCtcProcedimientos);
                    if (oldIdctcOfUciCtcProcedimientosSetNewUciCtcProcedimientos != null && !oldIdctcOfUciCtcProcedimientosSetNewUciCtcProcedimientos.equals(uciCtc)) {
                        oldIdctcOfUciCtcProcedimientosSetNewUciCtcProcedimientos.getUciCtcProcedimientosSet().remove(uciCtcProcedimientosSetNewUciCtcProcedimientos);
                        oldIdctcOfUciCtcProcedimientosSetNewUciCtcProcedimientos = em.merge(oldIdctcOfUciCtcProcedimientosSetNewUciCtcProcedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciCtc.getId();
                if (findUciCtc(id) == null) {
                    throw new NonexistentEntityException("The uciCtc with id " + id + " no longer exists.");
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
            UciCtc uciCtc;
            try {
                uciCtc = em.getReference(UciCtc.class, id);
                uciCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<UciCtcCie10> uciCtcCie10SetOrphanCheck = uciCtc.getUciCtcCie10Set();
            for (UciCtcCie10 uciCtcCie10SetOrphanCheckUciCtcCie10 : uciCtcCie10SetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciCtc (" + uciCtc + ") cannot be destroyed since the UciCtcCie10 " + uciCtcCie10SetOrphanCheckUciCtcCie10 + " in its uciCtcCie10Set field has a non-nullable idctc field.");
            }
            Set<UciCtcMedicamento> uciCtcMedicamentoSetOrphanCheck = uciCtc.getUciCtcMedicamentoSet();
            for (UciCtcMedicamento uciCtcMedicamentoSetOrphanCheckUciCtcMedicamento : uciCtcMedicamentoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciCtc (" + uciCtc + ") cannot be destroyed since the UciCtcMedicamento " + uciCtcMedicamentoSetOrphanCheckUciCtcMedicamento + " in its uciCtcMedicamentoSet field has a non-nullable idctc field.");
            }
            Set<UciCtcProcedimientos> uciCtcProcedimientosSetOrphanCheck = uciCtc.getUciCtcProcedimientosSet();
            for (UciCtcProcedimientos uciCtcProcedimientosSetOrphanCheckUciCtcProcedimientos : uciCtcProcedimientosSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciCtc (" + uciCtc + ") cannot be destroyed since the UciCtcProcedimientos " + uciCtcProcedimientosSetOrphanCheckUciCtcProcedimientos + " in its uciCtcProcedimientosSet field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(uciCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciCtc> findUciCtcEntities() {
        return findUciCtcEntities(true, -1, -1);
    }

    public List<UciCtc> findUciCtcEntities(int maxResults, int firstResult) {
        return findUciCtcEntities(false, maxResults, firstResult);
    }

    private List<UciCtc> findUciCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciCtc.class));
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

    public UciCtc findUciCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciCtc> rt = cq.from(UciCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
