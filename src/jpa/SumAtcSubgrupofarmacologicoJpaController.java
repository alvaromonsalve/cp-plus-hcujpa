/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.SumAtcGrupofarmacologico;
import entidades_EJB.SumAtcSubgrupofarmacologico;
import entidades_EJB.SumAtcSubgrupoquimico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class SumAtcSubgrupofarmacologicoJpaController implements Serializable {

    public SumAtcSubgrupofarmacologicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologico) {
        if (sumAtcSubgrupofarmacologico.getSumAtcSubgrupoquimicoList() == null) {
            sumAtcSubgrupofarmacologico.setSumAtcSubgrupoquimicoList(new ArrayList<SumAtcSubgrupoquimico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcGrupofarmacologico idGrupofarmacologico = sumAtcSubgrupofarmacologico.getIdGrupofarmacologico();
            if (idGrupofarmacologico != null) {
                idGrupofarmacologico = em.getReference(idGrupofarmacologico.getClass(), idGrupofarmacologico.getId());
                sumAtcSubgrupofarmacologico.setIdGrupofarmacologico(idGrupofarmacologico);
            }
            List<SumAtcSubgrupoquimico> attachedSumAtcSubgrupoquimicoList = new ArrayList<SumAtcSubgrupoquimico>();
            for (SumAtcSubgrupoquimico sumAtcSubgrupoquimicoListSumAtcSubgrupoquimicoToAttach : sumAtcSubgrupofarmacologico.getSumAtcSubgrupoquimicoList()) {
                sumAtcSubgrupoquimicoListSumAtcSubgrupoquimicoToAttach = em.getReference(sumAtcSubgrupoquimicoListSumAtcSubgrupoquimicoToAttach.getClass(), sumAtcSubgrupoquimicoListSumAtcSubgrupoquimicoToAttach.getId());
                attachedSumAtcSubgrupoquimicoList.add(sumAtcSubgrupoquimicoListSumAtcSubgrupoquimicoToAttach);
            }
            sumAtcSubgrupofarmacologico.setSumAtcSubgrupoquimicoList(attachedSumAtcSubgrupoquimicoList);
            em.persist(sumAtcSubgrupofarmacologico);
            if (idGrupofarmacologico != null) {
                idGrupofarmacologico.getSumAtcSubgrupofarmacologicoList().add(sumAtcSubgrupofarmacologico);
                idGrupofarmacologico = em.merge(idGrupofarmacologico);
            }
            for (SumAtcSubgrupoquimico sumAtcSubgrupoquimicoListSumAtcSubgrupoquimico : sumAtcSubgrupofarmacologico.getSumAtcSubgrupoquimicoList()) {
                SumAtcSubgrupofarmacologico oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListSumAtcSubgrupoquimico = sumAtcSubgrupoquimicoListSumAtcSubgrupoquimico.getIdSubgrupofarmacologico();
                sumAtcSubgrupoquimicoListSumAtcSubgrupoquimico.setIdSubgrupofarmacologico(sumAtcSubgrupofarmacologico);
                sumAtcSubgrupoquimicoListSumAtcSubgrupoquimico = em.merge(sumAtcSubgrupoquimicoListSumAtcSubgrupoquimico);
                if (oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListSumAtcSubgrupoquimico != null) {
                    oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListSumAtcSubgrupoquimico.getSumAtcSubgrupoquimicoList().remove(sumAtcSubgrupoquimicoListSumAtcSubgrupoquimico);
                    oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListSumAtcSubgrupoquimico = em.merge(oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListSumAtcSubgrupoquimico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcSubgrupofarmacologico persistentSumAtcSubgrupofarmacologico = em.find(SumAtcSubgrupofarmacologico.class, sumAtcSubgrupofarmacologico.getId());
            SumAtcGrupofarmacologico idGrupofarmacologicoOld = persistentSumAtcSubgrupofarmacologico.getIdGrupofarmacologico();
            SumAtcGrupofarmacologico idGrupofarmacologicoNew = sumAtcSubgrupofarmacologico.getIdGrupofarmacologico();
            List<SumAtcSubgrupoquimico> sumAtcSubgrupoquimicoListOld = persistentSumAtcSubgrupofarmacologico.getSumAtcSubgrupoquimicoList();
            List<SumAtcSubgrupoquimico> sumAtcSubgrupoquimicoListNew = sumAtcSubgrupofarmacologico.getSumAtcSubgrupoquimicoList();
            List<String> illegalOrphanMessages = null;
            for (SumAtcSubgrupoquimico sumAtcSubgrupoquimicoListOldSumAtcSubgrupoquimico : sumAtcSubgrupoquimicoListOld) {
                if (!sumAtcSubgrupoquimicoListNew.contains(sumAtcSubgrupoquimicoListOldSumAtcSubgrupoquimico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SumAtcSubgrupoquimico " + sumAtcSubgrupoquimicoListOldSumAtcSubgrupoquimico + " since its idSubgrupofarmacologico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idGrupofarmacologicoNew != null) {
                idGrupofarmacologicoNew = em.getReference(idGrupofarmacologicoNew.getClass(), idGrupofarmacologicoNew.getId());
                sumAtcSubgrupofarmacologico.setIdGrupofarmacologico(idGrupofarmacologicoNew);
            }
            List<SumAtcSubgrupoquimico> attachedSumAtcSubgrupoquimicoListNew = new ArrayList<SumAtcSubgrupoquimico>();
            for (SumAtcSubgrupoquimico sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimicoToAttach : sumAtcSubgrupoquimicoListNew) {
                sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimicoToAttach = em.getReference(sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimicoToAttach.getClass(), sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimicoToAttach.getId());
                attachedSumAtcSubgrupoquimicoListNew.add(sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimicoToAttach);
            }
            sumAtcSubgrupoquimicoListNew = attachedSumAtcSubgrupoquimicoListNew;
            sumAtcSubgrupofarmacologico.setSumAtcSubgrupoquimicoList(sumAtcSubgrupoquimicoListNew);
            sumAtcSubgrupofarmacologico = em.merge(sumAtcSubgrupofarmacologico);
            if (idGrupofarmacologicoOld != null && !idGrupofarmacologicoOld.equals(idGrupofarmacologicoNew)) {
                idGrupofarmacologicoOld.getSumAtcSubgrupofarmacologicoList().remove(sumAtcSubgrupofarmacologico);
                idGrupofarmacologicoOld = em.merge(idGrupofarmacologicoOld);
            }
            if (idGrupofarmacologicoNew != null && !idGrupofarmacologicoNew.equals(idGrupofarmacologicoOld)) {
                idGrupofarmacologicoNew.getSumAtcSubgrupofarmacologicoList().add(sumAtcSubgrupofarmacologico);
                idGrupofarmacologicoNew = em.merge(idGrupofarmacologicoNew);
            }
            for (SumAtcSubgrupoquimico sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico : sumAtcSubgrupoquimicoListNew) {
                if (!sumAtcSubgrupoquimicoListOld.contains(sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico)) {
                    SumAtcSubgrupofarmacologico oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico = sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico.getIdSubgrupofarmacologico();
                    sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico.setIdSubgrupofarmacologico(sumAtcSubgrupofarmacologico);
                    sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico = em.merge(sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico);
                    if (oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico != null && !oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico.equals(sumAtcSubgrupofarmacologico)) {
                        oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico.getSumAtcSubgrupoquimicoList().remove(sumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico);
                        oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico = em.merge(oldIdSubgrupofarmacologicoOfSumAtcSubgrupoquimicoListNewSumAtcSubgrupoquimico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcSubgrupofarmacologico.getId();
                if (findSumAtcSubgrupofarmacologico(id) == null) {
                    throw new NonexistentEntityException("The sumAtcSubgrupofarmacologico with id " + id + " no longer exists.");
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
            SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologico;
            try {
                sumAtcSubgrupofarmacologico = em.getReference(SumAtcSubgrupofarmacologico.class, id);
                sumAtcSubgrupofarmacologico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcSubgrupofarmacologico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SumAtcSubgrupoquimico> sumAtcSubgrupoquimicoListOrphanCheck = sumAtcSubgrupofarmacologico.getSumAtcSubgrupoquimicoList();
            for (SumAtcSubgrupoquimico sumAtcSubgrupoquimicoListOrphanCheckSumAtcSubgrupoquimico : sumAtcSubgrupoquimicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SumAtcSubgrupofarmacologico (" + sumAtcSubgrupofarmacologico + ") cannot be destroyed since the SumAtcSubgrupoquimico " + sumAtcSubgrupoquimicoListOrphanCheckSumAtcSubgrupoquimico + " in its sumAtcSubgrupoquimicoList field has a non-nullable idSubgrupofarmacologico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SumAtcGrupofarmacologico idGrupofarmacologico = sumAtcSubgrupofarmacologico.getIdGrupofarmacologico();
            if (idGrupofarmacologico != null) {
                idGrupofarmacologico.getSumAtcSubgrupofarmacologicoList().remove(sumAtcSubgrupofarmacologico);
                idGrupofarmacologico = em.merge(idGrupofarmacologico);
            }
            em.remove(sumAtcSubgrupofarmacologico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcSubgrupofarmacologico> findSumAtcSubgrupofarmacologicoEntities() {
        return findSumAtcSubgrupofarmacologicoEntities(true, -1, -1);
    }

    public List<SumAtcSubgrupofarmacologico> findSumAtcSubgrupofarmacologicoEntities(int maxResults, int firstResult) {
        return findSumAtcSubgrupofarmacologicoEntities(false, maxResults, firstResult);
    }

    private List<SumAtcSubgrupofarmacologico> findSumAtcSubgrupofarmacologicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcSubgrupofarmacologico.class));
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

    public SumAtcSubgrupofarmacologico findSumAtcSubgrupofarmacologico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcSubgrupofarmacologico.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcSubgrupofarmacologicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcSubgrupofarmacologico> rt = cq.from(SumAtcSubgrupofarmacologico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
