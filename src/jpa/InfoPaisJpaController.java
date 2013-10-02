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
import entidades.InfoDepartamentos;
import entidades.InfoPais;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class InfoPaisJpaController implements Serializable {

    public InfoPaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoPais infoPais) {
        if (infoPais.getInfoDepartamentosList() == null) {
            infoPais.setInfoDepartamentosList(new ArrayList<InfoDepartamentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InfoDepartamentos> attachedInfoDepartamentosList = new ArrayList<InfoDepartamentos>();
            for (InfoDepartamentos infoDepartamentosListInfoDepartamentosToAttach : infoPais.getInfoDepartamentosList()) {
                infoDepartamentosListInfoDepartamentosToAttach = em.getReference(infoDepartamentosListInfoDepartamentosToAttach.getClass(), infoDepartamentosListInfoDepartamentosToAttach.getId());
                attachedInfoDepartamentosList.add(infoDepartamentosListInfoDepartamentosToAttach);
            }
            infoPais.setInfoDepartamentosList(attachedInfoDepartamentosList);
            em.persist(infoPais);
            for (InfoDepartamentos infoDepartamentosListInfoDepartamentos : infoPais.getInfoDepartamentosList()) {
                InfoPais oldInfoPaisOfInfoDepartamentosListInfoDepartamentos = infoDepartamentosListInfoDepartamentos.getInfoPais();
                infoDepartamentosListInfoDepartamentos.setInfoPais(infoPais);
                infoDepartamentosListInfoDepartamentos = em.merge(infoDepartamentosListInfoDepartamentos);
                if (oldInfoPaisOfInfoDepartamentosListInfoDepartamentos != null) {
                    oldInfoPaisOfInfoDepartamentosListInfoDepartamentos.getInfoDepartamentosList().remove(infoDepartamentosListInfoDepartamentos);
                    oldInfoPaisOfInfoDepartamentosListInfoDepartamentos = em.merge(oldInfoPaisOfInfoDepartamentosListInfoDepartamentos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoPais infoPais) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoPais persistentInfoPais = em.find(InfoPais.class, infoPais.getId());
            List<InfoDepartamentos> infoDepartamentosListOld = persistentInfoPais.getInfoDepartamentosList();
            List<InfoDepartamentos> infoDepartamentosListNew = infoPais.getInfoDepartamentosList();
            List<String> illegalOrphanMessages = null;
            for (InfoDepartamentos infoDepartamentosListOldInfoDepartamentos : infoDepartamentosListOld) {
                if (!infoDepartamentosListNew.contains(infoDepartamentosListOldInfoDepartamentos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoDepartamentos " + infoDepartamentosListOldInfoDepartamentos + " since its infoPais field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InfoDepartamentos> attachedInfoDepartamentosListNew = new ArrayList<InfoDepartamentos>();
            for (InfoDepartamentos infoDepartamentosListNewInfoDepartamentosToAttach : infoDepartamentosListNew) {
                infoDepartamentosListNewInfoDepartamentosToAttach = em.getReference(infoDepartamentosListNewInfoDepartamentosToAttach.getClass(), infoDepartamentosListNewInfoDepartamentosToAttach.getId());
                attachedInfoDepartamentosListNew.add(infoDepartamentosListNewInfoDepartamentosToAttach);
            }
            infoDepartamentosListNew = attachedInfoDepartamentosListNew;
            infoPais.setInfoDepartamentosList(infoDepartamentosListNew);
            infoPais = em.merge(infoPais);
            for (InfoDepartamentos infoDepartamentosListNewInfoDepartamentos : infoDepartamentosListNew) {
                if (!infoDepartamentosListOld.contains(infoDepartamentosListNewInfoDepartamentos)) {
                    InfoPais oldInfoPaisOfInfoDepartamentosListNewInfoDepartamentos = infoDepartamentosListNewInfoDepartamentos.getInfoPais();
                    infoDepartamentosListNewInfoDepartamentos.setInfoPais(infoPais);
                    infoDepartamentosListNewInfoDepartamentos = em.merge(infoDepartamentosListNewInfoDepartamentos);
                    if (oldInfoPaisOfInfoDepartamentosListNewInfoDepartamentos != null && !oldInfoPaisOfInfoDepartamentosListNewInfoDepartamentos.equals(infoPais)) {
                        oldInfoPaisOfInfoDepartamentosListNewInfoDepartamentos.getInfoDepartamentosList().remove(infoDepartamentosListNewInfoDepartamentos);
                        oldInfoPaisOfInfoDepartamentosListNewInfoDepartamentos = em.merge(oldInfoPaisOfInfoDepartamentosListNewInfoDepartamentos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoPais.getId();
                if (findInfoPais(id) == null) {
                    throw new NonexistentEntityException("The infoPais with id " + id + " no longer exists.");
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
            InfoPais infoPais;
            try {
                infoPais = em.getReference(InfoPais.class, id);
                infoPais.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoPais with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InfoDepartamentos> infoDepartamentosListOrphanCheck = infoPais.getInfoDepartamentosList();
            for (InfoDepartamentos infoDepartamentosListOrphanCheckInfoDepartamentos : infoDepartamentosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InfoPais (" + infoPais + ") cannot be destroyed since the InfoDepartamentos " + infoDepartamentosListOrphanCheckInfoDepartamentos + " in its infoDepartamentosList field has a non-nullable infoPais field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(infoPais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoPais> findInfoPaisEntities() {
        return findInfoPaisEntities(true, -1, -1);
    }

    public List<InfoPais> findInfoPaisEntities(int maxResults, int firstResult) {
        return findInfoPaisEntities(false, maxResults, firstResult);
    }

    private List<InfoPais> findInfoPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoPais.class));
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

    public InfoPais findInfoPais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoPais.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoPais> rt = cq.from(InfoPais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
