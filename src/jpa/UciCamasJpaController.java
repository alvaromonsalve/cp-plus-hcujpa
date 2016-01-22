package jpa;

import entidades_EJB.ConfigCamas;
import entidades_EJB.UciCamas;
import entidades_EJB.UciHistoriac;
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
public class UciCamasJpaController implements Serializable {

    public UciCamasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciCamas uciCamas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfigCamas idConfigCamas = uciCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas = em.getReference(idConfigCamas.getClass(), idConfigCamas.getId());
                uciCamas.setIdConfigCamas(idConfigCamas);
            }
            UciHistoriac idUciHistoriac = uciCamas.getIdUciHistoriac();
            if (idUciHistoriac != null) {
                idUciHistoriac = em.getReference(idUciHistoriac.getClass(), idUciHistoriac.getId());
                uciCamas.setIdUciHistoriac(idUciHistoriac);
            }
            em.persist(uciCamas);
            if (idConfigCamas != null) {
                idConfigCamas.getUciCamasList().add(uciCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            if (idUciHistoriac != null) {
                idUciHistoriac.getUciCamasList().add(uciCamas);
                idUciHistoriac = em.merge(idUciHistoriac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciCamas uciCamas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciCamas persistentUciCamas = em.find(UciCamas.class, uciCamas.getId());
            ConfigCamas idConfigCamasOld = persistentUciCamas.getIdConfigCamas();
            ConfigCamas idConfigCamasNew = uciCamas.getIdConfigCamas();
            UciHistoriac idUciHistoriacOld = persistentUciCamas.getIdUciHistoriac();
            UciHistoriac idUciHistoriacNew = uciCamas.getIdUciHistoriac();
            if (idConfigCamasNew != null) {
                idConfigCamasNew = em.getReference(idConfigCamasNew.getClass(), idConfigCamasNew.getId());
                uciCamas.setIdConfigCamas(idConfigCamasNew);
            }
            if (idUciHistoriacNew != null) {
                idUciHistoriacNew = em.getReference(idUciHistoriacNew.getClass(), idUciHistoriacNew.getId());
                uciCamas.setIdUciHistoriac(idUciHistoriacNew);
            }
            uciCamas = em.merge(uciCamas);
            if (idConfigCamasOld != null && !idConfigCamasOld.equals(idConfigCamasNew)) {
                idConfigCamasOld.getUciCamasList().remove(uciCamas);
                idConfigCamasOld = em.merge(idConfigCamasOld);
            }
            if (idConfigCamasNew != null && !idConfigCamasNew.equals(idConfigCamasOld)) {
                idConfigCamasNew.getUciCamasList().add(uciCamas);
                idConfigCamasNew = em.merge(idConfigCamasNew);
            }
            if (idUciHistoriacOld != null && !idUciHistoriacOld.equals(idUciHistoriacNew)) {
                idUciHistoriacOld.getUciCamasList().remove(uciCamas);
                idUciHistoriacOld = em.merge(idUciHistoriacOld);
            }
            if (idUciHistoriacNew != null && !idUciHistoriacNew.equals(idUciHistoriacOld)) {
                idUciHistoriacNew.getUciCamasList().add(uciCamas);
                idUciHistoriacNew = em.merge(idUciHistoriacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciCamas.getId();
                if (findUciCamas(id) == null) {
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
            UciCamas uciCamas;
            try {
                uciCamas = em.getReference(UciCamas.class, id);
                uciCamas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciCamas with id " + id + " no longer exists.", enfe);
            }
            ConfigCamas idConfigCamas = uciCamas.getIdConfigCamas();
            if (idConfigCamas != null) {
                idConfigCamas.getUciCamasList().remove(uciCamas);
                idConfigCamas = em.merge(idConfigCamas);
            }
            UciHistoriac idUciHistoriac = uciCamas.getIdUciHistoriac();
            if (idUciHistoriac != null) {
                idUciHistoriac.getUciCamasList().remove(uciCamas);
                idUciHistoriac = em.merge(idUciHistoriac);
            }
            em.remove(uciCamas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciCamas> findUciCamasEntities() {
        return findUciCamasEntities(true, -1, -1);
    }

    public List<UciCamas> findUciCamasEntities(int maxResults, int firstResult) {
        return findUciCamasEntities(false, maxResults, firstResult);
    }

    private List<UciCamas> findUciCamasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciCamas.class));
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

    public UciCamas findUciCamas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciCamas.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciCamasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciCamas> rt = cq.from(UciCamas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codigo no Auto-Generado
    public List<UciCamas> findUciCamasEntities(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM UciCamas i WHERE i.estado = :estado")
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public UciCamas findCamasHcu(UciHistoriac ih) {
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM UciCamas i WHERE i.idUciHistoriac = :ih AND i.estado = 1")
                    .setParameter("ih", ih)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
            if (results.isEmpty()) {
                return null;
            } else if (results.size() == 1) {
                return (UciCamas) results.get(0);
            } else {
                JOptionPane.showMessageDialog(null, "Hosprme al administrador del sistema de este error:\n"
                        + "Es posible que existan varias camas asignadas a esta historia clinica activas.\n", HospCamasJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }

    public Object countCamas(UciHistoriac h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT COUNT(i.id) FROM UciCamas i WHERE i.idUciHistoriac.id=:ht AND i.estado='1'");
        Q.setParameter("ht", h.getId());
        return Q.getSingleResult();
    }

    public Object countCamaOcupada(UciHistoriac h) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT COUNT(i.id) FROM UciCamas i WHERE i.idUciHistoriac.id=:ht AND i.estado='2'");
        Q.setParameter("ht", h.getId());
        return Q.getSingleResult();
    }

    public Object ValidarCama(UciHistoriac h, ConfigCamas c) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT COUNT(i.id) FROM UciCamas i WHERE i.idUciHistoriac.id=:ht AND i.idConfigCamas.id=:cc AND i.estado='1'");
        Q.setParameter("ht", h.getId());
        Q.setParameter("cc", c.getId());
        return Q.getSingleResult();
    }

    public List<UciCamas> findInfocamas_() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM UciCamas i WHERE i.estado='1'");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }
}
