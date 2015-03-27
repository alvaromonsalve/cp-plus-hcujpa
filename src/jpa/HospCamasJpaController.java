package jpa;

import entidades_EJB.ConfigCamas;
import entidades_EJB.HospCamas;
import entidades_EJB.HospHistoriac;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class HospCamasJpaController implements Serializable {

    public HospCamasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospCamas hospCamas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCamas idConfigCamas = hospCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas = em.getReference(idConfigCamas.getClass(), idConfigCamas.getId());
                hospCamas.setIdConfigCamas(idConfigCamas);
            }
            HospHistoriac idHospHistoriac = hospCamas.getIdHospHistoriac();
            if (idHospHistoriac != null) {
                idHospHistoriac = em.getReference(idHospHistoriac.getClass(), idHospHistoriac.getId());
                hospCamas.setIdHospHistoriac(idHospHistoriac);
            }
            em.persist(hospCamas);
            if (idConfigCamas != null) {
                idConfigCamas.getHospCamasList().add(hospCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            if (idHospHistoriac != null) {
                idHospHistoriac.getHospCamasList().add(hospCamas);
                idHospHistoriac = em.merge(idHospHistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospCamas hospCamas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCamas persistentHospCamas = em.find(HospCamas.class, hospCamas.getId());
            ConfigCamas idConfigCamasOld = persistentHospCamas.getIdConfigCamas();
            ConfigCamas idConfigCamasNew = hospCamas.getIdConfigCamas();
            HospHistoriac idHospHistoriacOld = persistentHospCamas.getIdHospHistoriac();
            HospHistoriac idHospHistoriacNew = hospCamas.getIdHospHistoriac();
            if (idConfigCamasNew != null) {
                idConfigCamasNew = em.getReference(idConfigCamasNew.getClass(), idConfigCamasNew.getId());
                hospCamas.setIdConfigCamas(idConfigCamasNew);
            }
            if (idHospHistoriacNew != null) {
                idHospHistoriacNew = em.getReference(idHospHistoriacNew.getClass(), idHospHistoriacNew.getId());
                hospCamas.setIdHospHistoriac(idHospHistoriacNew);
            }
            hospCamas = em.merge(hospCamas);
            if (idConfigCamasOld != null && !idConfigCamasOld.equals(idConfigCamasNew)) {
                idConfigCamasOld.getHospCamasList().remove(hospCamas);
                idConfigCamasOld = em.merge(idConfigCamasOld);
            }
            if (idConfigCamasNew != null && !idConfigCamasNew.equals(idConfigCamasOld)) {
                idConfigCamasNew.getHospCamasList().add(hospCamas);
                idConfigCamasNew = em.merge(idConfigCamasNew);
            }
            if (idHospHistoriacOld != null && !idHospHistoriacOld.equals(idHospHistoriacNew)) {
                idHospHistoriacOld.getHospCamasList().remove(hospCamas);
                idHospHistoriacOld = em.merge(idHospHistoriacOld);
            }
            if (idHospHistoriacNew != null && !idHospHistoriacNew.equals(idHospHistoriacOld)) {
                idHospHistoriacNew.getHospCamasList().add(hospCamas);
                idHospHistoriacNew = em.merge(idHospHistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospCamas.getId();
                if (findHospCamas(id) == null) {
                    throw new NonexistentEntityException("The hospCamas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospCamas hospCamas;
            try {
                hospCamas = em.getReference(HospCamas.class, id);
                hospCamas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospCamas with id " + id + " no longer exists.", enfe);
            }
            ConfigCamas idConfigCamas = hospCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas.getHospCamasList().remove(hospCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            HospHistoriac idHospHistoriac = hospCamas.getIdHospHistoriac();
            if (idHospHistoriac != null) {
                idHospHistoriac.getHospCamasList().remove(hospCamas);
                idHospHistoriac = em.merge(idHospHistoriac);
            }
            em.remove(hospCamas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospCamas> findHospCamasEntities() {
        return findHospCamasEntities(true, -1, -1);
    }

    public List<HospCamas> findHospCamasEntities(int maxResults, int firstResult) {
        return findHospCamasEntities(false, maxResults, firstResult);
    }

    private List<HospCamas> findHospCamasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospCamas.class));
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

    public HospCamas findHospCamas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospCamas.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospCamasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospCamas> rt = cq.from(HospCamas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-Generado
    public List<HospCamas> findHospCamasEntities(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM HospCamas i WHERE i.estado = :estado" )
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public HospCamas findCamasHcu(HospHistoriac ih){
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM HospCamas i WHERE i.idHospHistoriac = :ih AND i.estado = 1")
            .setParameter("ih", ih)
            .setHint("javax.persistence.cache.storeMode", "REFRESH")
            .getResultList();
            if (results.isEmpty()) return null;
            else if (results.size() == 1) return (HospCamas) results.get(0);
            else {
                JOptionPane.showMessageDialog(null, "Hosprme al administrador del sistema de este error:\n"
                        + "Es posible que existan varias camas asignadas a esta historia clinica activas.\n", HospCamasJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }

}
