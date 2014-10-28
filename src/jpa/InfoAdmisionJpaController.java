/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entidades.InfoAdmision;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.InfoEntidades;
import entidades.InfoPaciente;
import entidades.InfoHistoriac;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author Administrador
 */
public class InfoAdmisionJpaController implements Serializable {

    public InfoAdmisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoAdmision infoAdmision) {
        if (infoAdmision.getInfoHistoriacList() == null) {
            infoAdmision.setInfoHistoriacList(new ArrayList<InfoHistoriac>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoEntidades idEntidadAdmision = infoAdmision.getIdEntidadAdmision();
            if (idEntidadAdmision != null) {
                idEntidadAdmision = em.getReference(idEntidadAdmision.getClass(), idEntidadAdmision.getId());
                infoAdmision.setIdEntidadAdmision(idEntidadAdmision);
            }
            InfoPaciente idDatosPersonales = infoAdmision.getIdDatosPersonales();
            if (idDatosPersonales != null) {
                idDatosPersonales = em.getReference(idDatosPersonales.getClass(), idDatosPersonales.getId());
                infoAdmision.setIdDatosPersonales(idDatosPersonales);
            }
            List<InfoHistoriac> attachedInfoHistoriacList = new ArrayList<InfoHistoriac>();
            for (InfoHistoriac infoHistoriacListInfoHistoriacToAttach : infoAdmision.getInfoHistoriacList()) {
                infoHistoriacListInfoHistoriacToAttach = em.getReference(infoHistoriacListInfoHistoriacToAttach.getClass(), infoHistoriacListInfoHistoriacToAttach.getId());
                attachedInfoHistoriacList.add(infoHistoriacListInfoHistoriacToAttach);
            }
            infoAdmision.setInfoHistoriacList(attachedInfoHistoriacList);
            em.persist(infoAdmision);
            if (idEntidadAdmision != null) {
                idEntidadAdmision.getInfoAdmisionList().add(infoAdmision);
                idEntidadAdmision = em.merge(idEntidadAdmision);
            }
            if (idDatosPersonales != null) {
                idDatosPersonales.getInfoAdmisionList().add(infoAdmision);
                idDatosPersonales = em.merge(idDatosPersonales);
            }
            for (InfoHistoriac infoHistoriacListInfoHistoriac : infoAdmision.getInfoHistoriacList()) {
                InfoAdmision oldIdInfoAdmisionOfInfoHistoriacListInfoHistoriac = infoHistoriacListInfoHistoriac.getIdInfoAdmision();
                infoHistoriacListInfoHistoriac.setIdInfoAdmision(infoAdmision);
                infoHistoriacListInfoHistoriac = em.merge(infoHistoriacListInfoHistoriac);
                if (oldIdInfoAdmisionOfInfoHistoriacListInfoHistoriac != null) {
                    oldIdInfoAdmisionOfInfoHistoriacListInfoHistoriac.getInfoHistoriacList().remove(infoHistoriacListInfoHistoriac);
                    oldIdInfoAdmisionOfInfoHistoriacListInfoHistoriac = em.merge(oldIdInfoAdmisionOfInfoHistoriacListInfoHistoriac);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoAdmision infoAdmision) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoAdmision persistentInfoAdmision = em.find(InfoAdmision.class, infoAdmision.getId());
            InfoEntidades idEntidadAdmisionOld = persistentInfoAdmision.getIdEntidadAdmision();
            InfoEntidades idEntidadAdmisionNew = infoAdmision.getIdEntidadAdmision();
            InfoPaciente idDatosPersonalesOld = persistentInfoAdmision.getIdDatosPersonales();
            InfoPaciente idDatosPersonalesNew = infoAdmision.getIdDatosPersonales();
            List<InfoHistoriac> infoHistoriacListOld = persistentInfoAdmision.getInfoHistoriacList();
            List<InfoHistoriac> infoHistoriacListNew = infoAdmision.getInfoHistoriacList();
            List<String> illegalOrphanMessages = null;
            for (InfoHistoriac infoHistoriacListOldInfoHistoriac : infoHistoriacListOld) {
                if (!infoHistoriacListNew.contains(infoHistoriacListOldInfoHistoriac)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoHistoriac " + infoHistoriacListOldInfoHistoriac + " since its idInfoAdmision field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEntidadAdmisionNew != null) {
                idEntidadAdmisionNew = em.getReference(idEntidadAdmisionNew.getClass(), idEntidadAdmisionNew.getId());
                infoAdmision.setIdEntidadAdmision(idEntidadAdmisionNew);
            }
            if (idDatosPersonalesNew != null) {
                idDatosPersonalesNew = em.getReference(idDatosPersonalesNew.getClass(), idDatosPersonalesNew.getId());
                infoAdmision.setIdDatosPersonales(idDatosPersonalesNew);
            }
            List<InfoHistoriac> attachedInfoHistoriacListNew = new ArrayList<InfoHistoriac>();
            for (InfoHistoriac infoHistoriacListNewInfoHistoriacToAttach : infoHistoriacListNew) {
                infoHistoriacListNewInfoHistoriacToAttach = em.getReference(infoHistoriacListNewInfoHistoriacToAttach.getClass(), infoHistoriacListNewInfoHistoriacToAttach.getId());
                attachedInfoHistoriacListNew.add(infoHistoriacListNewInfoHistoriacToAttach);
            }
            infoHistoriacListNew = attachedInfoHistoriacListNew;
            infoAdmision.setInfoHistoriacList(infoHistoriacListNew);
            infoAdmision = em.merge(infoAdmision);
            if (idEntidadAdmisionOld != null && !idEntidadAdmisionOld.equals(idEntidadAdmisionNew)) {
                idEntidadAdmisionOld.getInfoAdmisionList().remove(infoAdmision);
                idEntidadAdmisionOld = em.merge(idEntidadAdmisionOld);
            }
            if (idEntidadAdmisionNew != null && !idEntidadAdmisionNew.equals(idEntidadAdmisionOld)) {
                idEntidadAdmisionNew.getInfoAdmisionList().add(infoAdmision);
                idEntidadAdmisionNew = em.merge(idEntidadAdmisionNew);
            }
            if (idDatosPersonalesOld != null && !idDatosPersonalesOld.equals(idDatosPersonalesNew)) {
                idDatosPersonalesOld.getInfoAdmisionList().remove(infoAdmision);
                idDatosPersonalesOld = em.merge(idDatosPersonalesOld);
            }
            if (idDatosPersonalesNew != null && !idDatosPersonalesNew.equals(idDatosPersonalesOld)) {
                idDatosPersonalesNew.getInfoAdmisionList().add(infoAdmision);
                idDatosPersonalesNew = em.merge(idDatosPersonalesNew);
            }
            for (InfoHistoriac infoHistoriacListNewInfoHistoriac : infoHistoriacListNew) {
                if (!infoHistoriacListOld.contains(infoHistoriacListNewInfoHistoriac)) {
                    InfoAdmision oldIdInfoAdmisionOfInfoHistoriacListNewInfoHistoriac = infoHistoriacListNewInfoHistoriac.getIdInfoAdmision();
                    infoHistoriacListNewInfoHistoriac.setIdInfoAdmision(infoAdmision);
                    infoHistoriacListNewInfoHistoriac = em.merge(infoHistoriacListNewInfoHistoriac);
                    if (oldIdInfoAdmisionOfInfoHistoriacListNewInfoHistoriac != null && !oldIdInfoAdmisionOfInfoHistoriacListNewInfoHistoriac.equals(infoAdmision)) {
                        oldIdInfoAdmisionOfInfoHistoriacListNewInfoHistoriac.getInfoHistoriacList().remove(infoHistoriacListNewInfoHistoriac);
                        oldIdInfoAdmisionOfInfoHistoriacListNewInfoHistoriac = em.merge(oldIdInfoAdmisionOfInfoHistoriacListNewInfoHistoriac);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoAdmision.getId();
                if (findInfoAdmision(id) == null) {
                    throw new NonexistentEntityException("The infoAdmision with id " + id + " no longer exists.");
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
            InfoAdmision infoAdmision;
            try {
                infoAdmision = em.getReference(InfoAdmision.class, id);
                infoAdmision.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoAdmision with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InfoHistoriac> infoHistoriacListOrphanCheck = infoAdmision.getInfoHistoriacList();
            for (InfoHistoriac infoHistoriacListOrphanCheckInfoHistoriac : infoHistoriacListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InfoAdmision (" + infoAdmision + ") cannot be destroyed since the InfoHistoriac " + infoHistoriacListOrphanCheckInfoHistoriac + " in its infoHistoriacList field has a non-nullable idInfoAdmision field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InfoEntidades idEntidadAdmision = infoAdmision.getIdEntidadAdmision();
            if (idEntidadAdmision != null) {
                idEntidadAdmision.getInfoAdmisionList().remove(infoAdmision);
                idEntidadAdmision = em.merge(idEntidadAdmision);
            }
            InfoPaciente idDatosPersonales = infoAdmision.getIdDatosPersonales();
            if (idDatosPersonales != null) {
                idDatosPersonales.getInfoAdmisionList().remove(infoAdmision);
                idDatosPersonales = em.merge(idDatosPersonales);
            }
            em.remove(infoAdmision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoAdmision> findInfoAdmisionEntities() {
        return findInfoAdmisionEntities(true, -1, -1);
    }

    public List<InfoAdmision> findInfoAdmisionEntities(int maxResults, int firstResult) {
        return findInfoAdmisionEntities(false, maxResults, firstResult);
    }

    private List<InfoAdmision> findInfoAdmisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoAdmision.class));
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

    public InfoAdmision findInfoAdmision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoAdmision.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoAdmisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoAdmision> rt = cq.from(InfoAdmision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        //Codigo no Auto-generado
    
   public List<InfoAdmision> findInfoAdmisionActivado(int estado){
        EntityManager em = getEntityManager();        
        try {
            return em.createQuery("SELECT i FROM InfoAdmision i WHERE i.estado = :estado")
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
   }
    
}

