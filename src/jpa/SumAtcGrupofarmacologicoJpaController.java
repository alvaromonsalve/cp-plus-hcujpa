/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.SumAtcGrupofarmacologico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SumAtcSistemasorganicos;
import entidades.SumAtcSubgrupofarmacologico;
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
public class SumAtcGrupofarmacologicoJpaController implements Serializable {

    public SumAtcGrupofarmacologicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcGrupofarmacologico sumAtcGrupofarmacologico) {
        if (sumAtcGrupofarmacologico.getSumAtcSubgrupofarmacologicoList() == null) {
            sumAtcGrupofarmacologico.setSumAtcSubgrupofarmacologicoList(new ArrayList<SumAtcSubgrupofarmacologico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcSistemasorganicos idSistemaorganico = sumAtcGrupofarmacologico.getIdSistemaorganico();
            if (idSistemaorganico != null) {
                idSistemaorganico = em.getReference(idSistemaorganico.getClass(), idSistemaorganico.getId());
                sumAtcGrupofarmacologico.setIdSistemaorganico(idSistemaorganico);
            }
            List<SumAtcSubgrupofarmacologico> attachedSumAtcSubgrupofarmacologicoList = new ArrayList<SumAtcSubgrupofarmacologico>();
            for (SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologicoToAttach : sumAtcGrupofarmacologico.getSumAtcSubgrupofarmacologicoList()) {
                sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologicoToAttach = em.getReference(sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologicoToAttach.getClass(), sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologicoToAttach.getId());
                attachedSumAtcSubgrupofarmacologicoList.add(sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologicoToAttach);
            }
            sumAtcGrupofarmacologico.setSumAtcSubgrupofarmacologicoList(attachedSumAtcSubgrupofarmacologicoList);
            em.persist(sumAtcGrupofarmacologico);
            if (idSistemaorganico != null) {
                idSistemaorganico.getSumAtcGrupofarmacologicoList().add(sumAtcGrupofarmacologico);
                idSistemaorganico = em.merge(idSistemaorganico);
            }
            for (SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico : sumAtcGrupofarmacologico.getSumAtcSubgrupofarmacologicoList()) {
                SumAtcGrupofarmacologico oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico = sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico.getIdGrupofarmacologico();
                sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico.setIdGrupofarmacologico(sumAtcGrupofarmacologico);
                sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico = em.merge(sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico);
                if (oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico != null) {
                    oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico.getSumAtcSubgrupofarmacologicoList().remove(sumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico);
                    oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico = em.merge(oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListSumAtcSubgrupofarmacologico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcGrupofarmacologico sumAtcGrupofarmacologico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcGrupofarmacologico persistentSumAtcGrupofarmacologico = em.find(SumAtcGrupofarmacologico.class, sumAtcGrupofarmacologico.getId());
            SumAtcSistemasorganicos idSistemaorganicoOld = persistentSumAtcGrupofarmacologico.getIdSistemaorganico();
            SumAtcSistemasorganicos idSistemaorganicoNew = sumAtcGrupofarmacologico.getIdSistemaorganico();
            List<SumAtcSubgrupofarmacologico> sumAtcSubgrupofarmacologicoListOld = persistentSumAtcGrupofarmacologico.getSumAtcSubgrupofarmacologicoList();
            List<SumAtcSubgrupofarmacologico> sumAtcSubgrupofarmacologicoListNew = sumAtcGrupofarmacologico.getSumAtcSubgrupofarmacologicoList();
            List<String> illegalOrphanMessages = null;
            for (SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologicoListOldSumAtcSubgrupofarmacologico : sumAtcSubgrupofarmacologicoListOld) {
                if (!sumAtcSubgrupofarmacologicoListNew.contains(sumAtcSubgrupofarmacologicoListOldSumAtcSubgrupofarmacologico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SumAtcSubgrupofarmacologico " + sumAtcSubgrupofarmacologicoListOldSumAtcSubgrupofarmacologico + " since its idGrupofarmacologico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idSistemaorganicoNew != null) {
                idSistemaorganicoNew = em.getReference(idSistemaorganicoNew.getClass(), idSistemaorganicoNew.getId());
                sumAtcGrupofarmacologico.setIdSistemaorganico(idSistemaorganicoNew);
            }
            List<SumAtcSubgrupofarmacologico> attachedSumAtcSubgrupofarmacologicoListNew = new ArrayList<SumAtcSubgrupofarmacologico>();
            for (SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologicoToAttach : sumAtcSubgrupofarmacologicoListNew) {
                sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologicoToAttach = em.getReference(sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologicoToAttach.getClass(), sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologicoToAttach.getId());
                attachedSumAtcSubgrupofarmacologicoListNew.add(sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologicoToAttach);
            }
            sumAtcSubgrupofarmacologicoListNew = attachedSumAtcSubgrupofarmacologicoListNew;
            sumAtcGrupofarmacologico.setSumAtcSubgrupofarmacologicoList(sumAtcSubgrupofarmacologicoListNew);
            sumAtcGrupofarmacologico = em.merge(sumAtcGrupofarmacologico);
            if (idSistemaorganicoOld != null && !idSistemaorganicoOld.equals(idSistemaorganicoNew)) {
                idSistemaorganicoOld.getSumAtcGrupofarmacologicoList().remove(sumAtcGrupofarmacologico);
                idSistemaorganicoOld = em.merge(idSistemaorganicoOld);
            }
            if (idSistemaorganicoNew != null && !idSistemaorganicoNew.equals(idSistemaorganicoOld)) {
                idSistemaorganicoNew.getSumAtcGrupofarmacologicoList().add(sumAtcGrupofarmacologico);
                idSistemaorganicoNew = em.merge(idSistemaorganicoNew);
            }
            for (SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico : sumAtcSubgrupofarmacologicoListNew) {
                if (!sumAtcSubgrupofarmacologicoListOld.contains(sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico)) {
                    SumAtcGrupofarmacologico oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico = sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico.getIdGrupofarmacologico();
                    sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico.setIdGrupofarmacologico(sumAtcGrupofarmacologico);
                    sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico = em.merge(sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico);
                    if (oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico != null && !oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico.equals(sumAtcGrupofarmacologico)) {
                        oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico.getSumAtcSubgrupofarmacologicoList().remove(sumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico);
                        oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico = em.merge(oldIdGrupofarmacologicoOfSumAtcSubgrupofarmacologicoListNewSumAtcSubgrupofarmacologico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcGrupofarmacologico.getId();
                if (findSumAtcGrupofarmacologico(id) == null) {
                    throw new NonexistentEntityException("The sumAtcGrupofarmacologico with id " + id + " no longer exists.");
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
            SumAtcGrupofarmacologico sumAtcGrupofarmacologico;
            try {
                sumAtcGrupofarmacologico = em.getReference(SumAtcGrupofarmacologico.class, id);
                sumAtcGrupofarmacologico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcGrupofarmacologico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SumAtcSubgrupofarmacologico> sumAtcSubgrupofarmacologicoListOrphanCheck = sumAtcGrupofarmacologico.getSumAtcSubgrupofarmacologicoList();
            for (SumAtcSubgrupofarmacologico sumAtcSubgrupofarmacologicoListOrphanCheckSumAtcSubgrupofarmacologico : sumAtcSubgrupofarmacologicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SumAtcGrupofarmacologico (" + sumAtcGrupofarmacologico + ") cannot be destroyed since the SumAtcSubgrupofarmacologico " + sumAtcSubgrupofarmacologicoListOrphanCheckSumAtcSubgrupofarmacologico + " in its sumAtcSubgrupofarmacologicoList field has a non-nullable idGrupofarmacologico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SumAtcSistemasorganicos idSistemaorganico = sumAtcGrupofarmacologico.getIdSistemaorganico();
            if (idSistemaorganico != null) {
                idSistemaorganico.getSumAtcGrupofarmacologicoList().remove(sumAtcGrupofarmacologico);
                idSistemaorganico = em.merge(idSistemaorganico);
            }
            em.remove(sumAtcGrupofarmacologico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcGrupofarmacologico> findSumAtcGrupofarmacologicoEntities() {
        return findSumAtcGrupofarmacologicoEntities(true, -1, -1);
    }

    public List<SumAtcGrupofarmacologico> findSumAtcGrupofarmacologicoEntities(int maxResults, int firstResult) {
        return findSumAtcGrupofarmacologicoEntities(false, maxResults, firstResult);
    }

    private List<SumAtcGrupofarmacologico> findSumAtcGrupofarmacologicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcGrupofarmacologico.class));
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

    public SumAtcGrupofarmacologico findSumAtcGrupofarmacologico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcGrupofarmacologico.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcGrupofarmacologicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcGrupofarmacologico> rt = cq.from(SumAtcGrupofarmacologico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
