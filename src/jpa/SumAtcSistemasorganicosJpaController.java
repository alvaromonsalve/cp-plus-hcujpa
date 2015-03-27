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
import entidades_EJB.SumAtcSistemasorganicos;
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
public class SumAtcSistemasorganicosJpaController implements Serializable {

    public SumAtcSistemasorganicosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SumAtcSistemasorganicos sumAtcSistemasorganicos) {
        if (sumAtcSistemasorganicos.getSumAtcGrupofarmacologicoList() == null) {
            sumAtcSistemasorganicos.setSumAtcGrupofarmacologicoList(new ArrayList<SumAtcGrupofarmacologico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SumAtcGrupofarmacologico> attachedSumAtcGrupofarmacologicoList = new ArrayList<SumAtcGrupofarmacologico>();
            for (SumAtcGrupofarmacologico sumAtcGrupofarmacologicoListSumAtcGrupofarmacologicoToAttach : sumAtcSistemasorganicos.getSumAtcGrupofarmacologicoList()) {
                sumAtcGrupofarmacologicoListSumAtcGrupofarmacologicoToAttach = em.getReference(sumAtcGrupofarmacologicoListSumAtcGrupofarmacologicoToAttach.getClass(), sumAtcGrupofarmacologicoListSumAtcGrupofarmacologicoToAttach.getId());
                attachedSumAtcGrupofarmacologicoList.add(sumAtcGrupofarmacologicoListSumAtcGrupofarmacologicoToAttach);
            }
            sumAtcSistemasorganicos.setSumAtcGrupofarmacologicoList(attachedSumAtcGrupofarmacologicoList);
            em.persist(sumAtcSistemasorganicos);
            for (SumAtcGrupofarmacologico sumAtcGrupofarmacologicoListSumAtcGrupofarmacologico : sumAtcSistemasorganicos.getSumAtcGrupofarmacologicoList()) {
                SumAtcSistemasorganicos oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListSumAtcGrupofarmacologico = sumAtcGrupofarmacologicoListSumAtcGrupofarmacologico.getIdSistemaorganico();
                sumAtcGrupofarmacologicoListSumAtcGrupofarmacologico.setIdSistemaorganico(sumAtcSistemasorganicos);
                sumAtcGrupofarmacologicoListSumAtcGrupofarmacologico = em.merge(sumAtcGrupofarmacologicoListSumAtcGrupofarmacologico);
                if (oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListSumAtcGrupofarmacologico != null) {
                    oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListSumAtcGrupofarmacologico.getSumAtcGrupofarmacologicoList().remove(sumAtcGrupofarmacologicoListSumAtcGrupofarmacologico);
                    oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListSumAtcGrupofarmacologico = em.merge(oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListSumAtcGrupofarmacologico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SumAtcSistemasorganicos sumAtcSistemasorganicos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SumAtcSistemasorganicos persistentSumAtcSistemasorganicos = em.find(SumAtcSistemasorganicos.class, sumAtcSistemasorganicos.getId());
            List<SumAtcGrupofarmacologico> sumAtcGrupofarmacologicoListOld = persistentSumAtcSistemasorganicos.getSumAtcGrupofarmacologicoList();
            List<SumAtcGrupofarmacologico> sumAtcGrupofarmacologicoListNew = sumAtcSistemasorganicos.getSumAtcGrupofarmacologicoList();
            List<String> illegalOrphanMessages = null;
            for (SumAtcGrupofarmacologico sumAtcGrupofarmacologicoListOldSumAtcGrupofarmacologico : sumAtcGrupofarmacologicoListOld) {
                if (!sumAtcGrupofarmacologicoListNew.contains(sumAtcGrupofarmacologicoListOldSumAtcGrupofarmacologico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SumAtcGrupofarmacologico " + sumAtcGrupofarmacologicoListOldSumAtcGrupofarmacologico + " since its idSistemaorganico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SumAtcGrupofarmacologico> attachedSumAtcGrupofarmacologicoListNew = new ArrayList<SumAtcGrupofarmacologico>();
            for (SumAtcGrupofarmacologico sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologicoToAttach : sumAtcGrupofarmacologicoListNew) {
                sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologicoToAttach = em.getReference(sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologicoToAttach.getClass(), sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologicoToAttach.getId());
                attachedSumAtcGrupofarmacologicoListNew.add(sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologicoToAttach);
            }
            sumAtcGrupofarmacologicoListNew = attachedSumAtcGrupofarmacologicoListNew;
            sumAtcSistemasorganicos.setSumAtcGrupofarmacologicoList(sumAtcGrupofarmacologicoListNew);
            sumAtcSistemasorganicos = em.merge(sumAtcSistemasorganicos);
            for (SumAtcGrupofarmacologico sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico : sumAtcGrupofarmacologicoListNew) {
                if (!sumAtcGrupofarmacologicoListOld.contains(sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico)) {
                    SumAtcSistemasorganicos oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico = sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico.getIdSistemaorganico();
                    sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico.setIdSistemaorganico(sumAtcSistemasorganicos);
                    sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico = em.merge(sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico);
                    if (oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico != null && !oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico.equals(sumAtcSistemasorganicos)) {
                        oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico.getSumAtcGrupofarmacologicoList().remove(sumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico);
                        oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico = em.merge(oldIdSistemaorganicoOfSumAtcGrupofarmacologicoListNewSumAtcGrupofarmacologico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sumAtcSistemasorganicos.getId();
                if (findSumAtcSistemasorganicos(id) == null) {
                    throw new NonexistentEntityException("The sumAtcSistemasorganicos with id " + id + " no longer exists.");
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
            SumAtcSistemasorganicos sumAtcSistemasorganicos;
            try {
                sumAtcSistemasorganicos = em.getReference(SumAtcSistemasorganicos.class, id);
                sumAtcSistemasorganicos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sumAtcSistemasorganicos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SumAtcGrupofarmacologico> sumAtcGrupofarmacologicoListOrphanCheck = sumAtcSistemasorganicos.getSumAtcGrupofarmacologicoList();
            for (SumAtcGrupofarmacologico sumAtcGrupofarmacologicoListOrphanCheckSumAtcGrupofarmacologico : sumAtcGrupofarmacologicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SumAtcSistemasorganicos (" + sumAtcSistemasorganicos + ") cannot be destroyed since the SumAtcGrupofarmacologico " + sumAtcGrupofarmacologicoListOrphanCheckSumAtcGrupofarmacologico + " in its sumAtcGrupofarmacologicoList field has a non-nullable idSistemaorganico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sumAtcSistemasorganicos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SumAtcSistemasorganicos> findSumAtcSistemasorganicosEntities() {
        return findSumAtcSistemasorganicosEntities(true, -1, -1);
    }

    public List<SumAtcSistemasorganicos> findSumAtcSistemasorganicosEntities(int maxResults, int firstResult) {
        return findSumAtcSistemasorganicosEntities(false, maxResults, firstResult);
    }

    private List<SumAtcSistemasorganicos> findSumAtcSistemasorganicosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SumAtcSistemasorganicos.class));
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

    public SumAtcSistemasorganicos findSumAtcSistemasorganicos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SumAtcSistemasorganicos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSumAtcSistemasorganicosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SumAtcSistemasorganicos> rt = cq.from(SumAtcSistemasorganicos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
