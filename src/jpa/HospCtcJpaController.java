/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades_EJB.HospCtc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.HospCtcMedicamento;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospCtcCie10;
import entidades_EJB.HospCtcProcedimientos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Camilo
 */
public class HospCtcJpaController implements Serializable {

    public HospCtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospCtc hospCtc) {
        if (hospCtc.getHospCtcMedicamentoList() == null) {
            hospCtc.setHospCtcMedicamentoList(new ArrayList<HospCtcMedicamento>());
        }
        if (hospCtc.getHospCtcCie10List() == null) {
            hospCtc.setHospCtcCie10List(new ArrayList<HospCtcCie10>());
        }
        if (hospCtc.getHospCtcProcedimientosList() == null) {
            hospCtc.setHospCtcProcedimientosList(new ArrayList<HospCtcProcedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HospCtcMedicamento> attachedHospCtcMedicamentoList = new ArrayList<HospCtcMedicamento>();
            for (HospCtcMedicamento hospCtcMedicamentoListHospCtcMedicamentoToAttach : hospCtc.getHospCtcMedicamentoList()) {
                hospCtcMedicamentoListHospCtcMedicamentoToAttach = em.getReference(hospCtcMedicamentoListHospCtcMedicamentoToAttach.getClass(), hospCtcMedicamentoListHospCtcMedicamentoToAttach.getId());
                attachedHospCtcMedicamentoList.add(hospCtcMedicamentoListHospCtcMedicamentoToAttach);
            }
            hospCtc.setHospCtcMedicamentoList(attachedHospCtcMedicamentoList);
            List<HospCtcCie10> attachedHospCtcCie10List = new ArrayList<HospCtcCie10>();
            for (HospCtcCie10 hospCtcCie10ListHospCtcCie10ToAttach : hospCtc.getHospCtcCie10List()) {
                hospCtcCie10ListHospCtcCie10ToAttach = em.getReference(hospCtcCie10ListHospCtcCie10ToAttach.getClass(), hospCtcCie10ListHospCtcCie10ToAttach.getId());
                attachedHospCtcCie10List.add(hospCtcCie10ListHospCtcCie10ToAttach);
            }
            hospCtc.setHospCtcCie10List(attachedHospCtcCie10List);
            List<HospCtcProcedimientos> attachedHospCtcProcedimientosList = new ArrayList<HospCtcProcedimientos>();
            for (HospCtcProcedimientos hospCtcProcedimientosListHospCtcProcedimientosToAttach : hospCtc.getHospCtcProcedimientosList()) {
                hospCtcProcedimientosListHospCtcProcedimientosToAttach = em.getReference(hospCtcProcedimientosListHospCtcProcedimientosToAttach.getClass(), hospCtcProcedimientosListHospCtcProcedimientosToAttach.getId());
                attachedHospCtcProcedimientosList.add(hospCtcProcedimientosListHospCtcProcedimientosToAttach);
            }
            hospCtc.setHospCtcProcedimientosList(attachedHospCtcProcedimientosList);
            em.persist(hospCtc);
            for (HospCtcMedicamento hospCtcMedicamentoListHospCtcMedicamento : hospCtc.getHospCtcMedicamentoList()) {
                HospCtc oldIdctcOfHospCtcMedicamentoListHospCtcMedicamento = hospCtcMedicamentoListHospCtcMedicamento.getIdctc();
                hospCtcMedicamentoListHospCtcMedicamento.setIdctc(hospCtc);
                hospCtcMedicamentoListHospCtcMedicamento = em.merge(hospCtcMedicamentoListHospCtcMedicamento);
                if (oldIdctcOfHospCtcMedicamentoListHospCtcMedicamento != null) {
                    oldIdctcOfHospCtcMedicamentoListHospCtcMedicamento.getHospCtcMedicamentoList().remove(hospCtcMedicamentoListHospCtcMedicamento);
                    oldIdctcOfHospCtcMedicamentoListHospCtcMedicamento = em.merge(oldIdctcOfHospCtcMedicamentoListHospCtcMedicamento);
                }
            }
            for (HospCtcCie10 hospCtcCie10ListHospCtcCie10 : hospCtc.getHospCtcCie10List()) {
                HospCtc oldIdctcOfHospCtcCie10ListHospCtcCie10 = hospCtcCie10ListHospCtcCie10.getIdctc();
                hospCtcCie10ListHospCtcCie10.setIdctc(hospCtc);
                hospCtcCie10ListHospCtcCie10 = em.merge(hospCtcCie10ListHospCtcCie10);
                if (oldIdctcOfHospCtcCie10ListHospCtcCie10 != null) {
                    oldIdctcOfHospCtcCie10ListHospCtcCie10.getHospCtcCie10List().remove(hospCtcCie10ListHospCtcCie10);
                    oldIdctcOfHospCtcCie10ListHospCtcCie10 = em.merge(oldIdctcOfHospCtcCie10ListHospCtcCie10);
                }
            }
            for (HospCtcProcedimientos hospCtcProcedimientosListHospCtcProcedimientos : hospCtc.getHospCtcProcedimientosList()) {
                HospCtc oldIdctcOfHospCtcProcedimientosListHospCtcProcedimientos = hospCtcProcedimientosListHospCtcProcedimientos.getIdctc();
                hospCtcProcedimientosListHospCtcProcedimientos.setIdctc(hospCtc);
                hospCtcProcedimientosListHospCtcProcedimientos = em.merge(hospCtcProcedimientosListHospCtcProcedimientos);
                if (oldIdctcOfHospCtcProcedimientosListHospCtcProcedimientos != null) {
                    oldIdctcOfHospCtcProcedimientosListHospCtcProcedimientos.getHospCtcProcedimientosList().remove(hospCtcProcedimientosListHospCtcProcedimientos);
                    oldIdctcOfHospCtcProcedimientosListHospCtcProcedimientos = em.merge(oldIdctcOfHospCtcProcedimientosListHospCtcProcedimientos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospCtc hospCtc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCtc persistentHospCtc = em.find(HospCtc.class, hospCtc.getId());
            List<HospCtcMedicamento> hospCtcMedicamentoListOld = persistentHospCtc.getHospCtcMedicamentoList();
            List<HospCtcMedicamento> hospCtcMedicamentoListNew = hospCtc.getHospCtcMedicamentoList();
            List<HospCtcCie10> hospCtcCie10ListOld = persistentHospCtc.getHospCtcCie10List();
            List<HospCtcCie10> hospCtcCie10ListNew = hospCtc.getHospCtcCie10List();
            List<HospCtcProcedimientos> hospCtcProcedimientosListOld = persistentHospCtc.getHospCtcProcedimientosList();
            List<HospCtcProcedimientos> hospCtcProcedimientosListNew = hospCtc.getHospCtcProcedimientosList();
            List<String> illegalOrphanMessages = null;
            for (HospCtcMedicamento hospCtcMedicamentoListOldHospCtcMedicamento : hospCtcMedicamentoListOld) {
                if (!hospCtcMedicamentoListNew.contains(hospCtcMedicamentoListOldHospCtcMedicamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospCtcMedicamento " + hospCtcMedicamentoListOldHospCtcMedicamento + " since its idctc field is not nullable.");
                }
            }
            for (HospCtcCie10 hospCtcCie10ListOldHospCtcCie10 : hospCtcCie10ListOld) {
                if (!hospCtcCie10ListNew.contains(hospCtcCie10ListOldHospCtcCie10)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospCtcCie10 " + hospCtcCie10ListOldHospCtcCie10 + " since its idctc field is not nullable.");
                }
            }
            for (HospCtcProcedimientos hospCtcProcedimientosListOldHospCtcProcedimientos : hospCtcProcedimientosListOld) {
                if (!hospCtcProcedimientosListNew.contains(hospCtcProcedimientosListOldHospCtcProcedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospCtcProcedimientos " + hospCtcProcedimientosListOldHospCtcProcedimientos + " since its idctc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<HospCtcMedicamento> attachedHospCtcMedicamentoListNew = new ArrayList<HospCtcMedicamento>();
            for (HospCtcMedicamento hospCtcMedicamentoListNewHospCtcMedicamentoToAttach : hospCtcMedicamentoListNew) {
                hospCtcMedicamentoListNewHospCtcMedicamentoToAttach = em.getReference(hospCtcMedicamentoListNewHospCtcMedicamentoToAttach.getClass(), hospCtcMedicamentoListNewHospCtcMedicamentoToAttach.getId());
                attachedHospCtcMedicamentoListNew.add(hospCtcMedicamentoListNewHospCtcMedicamentoToAttach);
            }
            hospCtcMedicamentoListNew = attachedHospCtcMedicamentoListNew;
            hospCtc.setHospCtcMedicamentoList(hospCtcMedicamentoListNew);
            List<HospCtcCie10> attachedHospCtcCie10ListNew = new ArrayList<HospCtcCie10>();
            for (HospCtcCie10 hospCtcCie10ListNewHospCtcCie10ToAttach : hospCtcCie10ListNew) {
                hospCtcCie10ListNewHospCtcCie10ToAttach = em.getReference(hospCtcCie10ListNewHospCtcCie10ToAttach.getClass(), hospCtcCie10ListNewHospCtcCie10ToAttach.getId());
                attachedHospCtcCie10ListNew.add(hospCtcCie10ListNewHospCtcCie10ToAttach);
            }
            hospCtcCie10ListNew = attachedHospCtcCie10ListNew;
            hospCtc.setHospCtcCie10List(hospCtcCie10ListNew);
            List<HospCtcProcedimientos> attachedHospCtcProcedimientosListNew = new ArrayList<HospCtcProcedimientos>();
            for (HospCtcProcedimientos hospCtcProcedimientosListNewHospCtcProcedimientosToAttach : hospCtcProcedimientosListNew) {
                hospCtcProcedimientosListNewHospCtcProcedimientosToAttach = em.getReference(hospCtcProcedimientosListNewHospCtcProcedimientosToAttach.getClass(), hospCtcProcedimientosListNewHospCtcProcedimientosToAttach.getId());
                attachedHospCtcProcedimientosListNew.add(hospCtcProcedimientosListNewHospCtcProcedimientosToAttach);
            }
            hospCtcProcedimientosListNew = attachedHospCtcProcedimientosListNew;
            hospCtc.setHospCtcProcedimientosList(hospCtcProcedimientosListNew);
            hospCtc = em.merge(hospCtc);
            for (HospCtcMedicamento hospCtcMedicamentoListNewHospCtcMedicamento : hospCtcMedicamentoListNew) {
                if (!hospCtcMedicamentoListOld.contains(hospCtcMedicamentoListNewHospCtcMedicamento)) {
                    HospCtc oldIdctcOfHospCtcMedicamentoListNewHospCtcMedicamento = hospCtcMedicamentoListNewHospCtcMedicamento.getIdctc();
                    hospCtcMedicamentoListNewHospCtcMedicamento.setIdctc(hospCtc);
                    hospCtcMedicamentoListNewHospCtcMedicamento = em.merge(hospCtcMedicamentoListNewHospCtcMedicamento);
                    if (oldIdctcOfHospCtcMedicamentoListNewHospCtcMedicamento != null && !oldIdctcOfHospCtcMedicamentoListNewHospCtcMedicamento.equals(hospCtc)) {
                        oldIdctcOfHospCtcMedicamentoListNewHospCtcMedicamento.getHospCtcMedicamentoList().remove(hospCtcMedicamentoListNewHospCtcMedicamento);
                        oldIdctcOfHospCtcMedicamentoListNewHospCtcMedicamento = em.merge(oldIdctcOfHospCtcMedicamentoListNewHospCtcMedicamento);
                    }
                }
            }
            for (HospCtcCie10 hospCtcCie10ListNewHospCtcCie10 : hospCtcCie10ListNew) {
                if (!hospCtcCie10ListOld.contains(hospCtcCie10ListNewHospCtcCie10)) {
                    HospCtc oldIdctcOfHospCtcCie10ListNewHospCtcCie10 = hospCtcCie10ListNewHospCtcCie10.getIdctc();
                    hospCtcCie10ListNewHospCtcCie10.setIdctc(hospCtc);
                    hospCtcCie10ListNewHospCtcCie10 = em.merge(hospCtcCie10ListNewHospCtcCie10);
                    if (oldIdctcOfHospCtcCie10ListNewHospCtcCie10 != null && !oldIdctcOfHospCtcCie10ListNewHospCtcCie10.equals(hospCtc)) {
                        oldIdctcOfHospCtcCie10ListNewHospCtcCie10.getHospCtcCie10List().remove(hospCtcCie10ListNewHospCtcCie10);
                        oldIdctcOfHospCtcCie10ListNewHospCtcCie10 = em.merge(oldIdctcOfHospCtcCie10ListNewHospCtcCie10);
                    }
                }
            }
            for (HospCtcProcedimientos hospCtcProcedimientosListNewHospCtcProcedimientos : hospCtcProcedimientosListNew) {
                if (!hospCtcProcedimientosListOld.contains(hospCtcProcedimientosListNewHospCtcProcedimientos)) {
                    HospCtc oldIdctcOfHospCtcProcedimientosListNewHospCtcProcedimientos = hospCtcProcedimientosListNewHospCtcProcedimientos.getIdctc();
                    hospCtcProcedimientosListNewHospCtcProcedimientos.setIdctc(hospCtc);
                    hospCtcProcedimientosListNewHospCtcProcedimientos = em.merge(hospCtcProcedimientosListNewHospCtcProcedimientos);
                    if (oldIdctcOfHospCtcProcedimientosListNewHospCtcProcedimientos != null && !oldIdctcOfHospCtcProcedimientosListNewHospCtcProcedimientos.equals(hospCtc)) {
                        oldIdctcOfHospCtcProcedimientosListNewHospCtcProcedimientos.getHospCtcProcedimientosList().remove(hospCtcProcedimientosListNewHospCtcProcedimientos);
                        oldIdctcOfHospCtcProcedimientosListNewHospCtcProcedimientos = em.merge(oldIdctcOfHospCtcProcedimientosListNewHospCtcProcedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospCtc.getId();
                if (findHospCtc(id) == null) {
                    throw new NonexistentEntityException("The hospCtc with id " + id + " no longer exists.");
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
            HospCtc hospCtc;
            try {
                hospCtc = em.getReference(HospCtc.class, id);
                hospCtc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospCtc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospCtcMedicamento> hospCtcMedicamentoListOrphanCheck = hospCtc.getHospCtcMedicamentoList();
            for (HospCtcMedicamento hospCtcMedicamentoListOrphanCheckHospCtcMedicamento : hospCtcMedicamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospCtc (" + hospCtc + ") cannot be destroyed since the HospCtcMedicamento " + hospCtcMedicamentoListOrphanCheckHospCtcMedicamento + " in its hospCtcMedicamentoList field has a non-nullable idctc field.");
            }
            List<HospCtcCie10> hospCtcCie10ListOrphanCheck = hospCtc.getHospCtcCie10List();
            for (HospCtcCie10 hospCtcCie10ListOrphanCheckHospCtcCie10 : hospCtcCie10ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospCtc (" + hospCtc + ") cannot be destroyed since the HospCtcCie10 " + hospCtcCie10ListOrphanCheckHospCtcCie10 + " in its hospCtcCie10List field has a non-nullable idctc field.");
            }
            List<HospCtcProcedimientos> hospCtcProcedimientosListOrphanCheck = hospCtc.getHospCtcProcedimientosList();
            for (HospCtcProcedimientos hospCtcProcedimientosListOrphanCheckHospCtcProcedimientos : hospCtcProcedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospCtc (" + hospCtc + ") cannot be destroyed since the HospCtcProcedimientos " + hospCtcProcedimientosListOrphanCheckHospCtcProcedimientos + " in its hospCtcProcedimientosList field has a non-nullable idctc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(hospCtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospCtc> findHospCtcEntities() {
        return findHospCtcEntities(true, -1, -1);
    }

    public List<HospCtc> findHospCtcEntities(int maxResults, int firstResult) {
        return findHospCtcEntities(false, maxResults, firstResult);
    }

    private List<HospCtc> findHospCtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospCtc.class));
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

    public HospCtc findHospCtc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospCtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospCtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospCtc> rt = cq.from(HospCtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
