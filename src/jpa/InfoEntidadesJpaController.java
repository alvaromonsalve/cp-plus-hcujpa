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
import entidades_EJB.InfoAdmision;
import entidades_EJB.InfoEntidades;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.InfoPaciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class InfoEntidadesJpaController implements Serializable {

    public InfoEntidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoEntidades infoEntidades) {
        if (infoEntidades.getInfoAdmisionList() == null) {
            infoEntidades.setInfoAdmisionList(new ArrayList<InfoAdmision>());
        }
        if (infoEntidades.getInfoPacienteList() == null) {
            infoEntidades.setInfoPacienteList(new ArrayList<InfoPaciente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InfoAdmision> attachedInfoAdmisionList = new ArrayList<InfoAdmision>();
            for (InfoAdmision infoAdmisionListInfoAdmisionToAttach : infoEntidades.getInfoAdmisionList()) {
                infoAdmisionListInfoAdmisionToAttach = em.getReference(infoAdmisionListInfoAdmisionToAttach.getClass(), infoAdmisionListInfoAdmisionToAttach.getId());
                attachedInfoAdmisionList.add(infoAdmisionListInfoAdmisionToAttach);
            }
            infoEntidades.setInfoAdmisionList(attachedInfoAdmisionList);
            List<InfoPaciente> attachedInfoPacienteList = new ArrayList<InfoPaciente>();
            for (InfoPaciente infoPacienteListInfoPacienteToAttach : infoEntidades.getInfoPacienteList()) {
                infoPacienteListInfoPacienteToAttach = em.getReference(infoPacienteListInfoPacienteToAttach.getClass(), infoPacienteListInfoPacienteToAttach.getId());
                attachedInfoPacienteList.add(infoPacienteListInfoPacienteToAttach);
            }
            infoEntidades.setInfoPacienteList(attachedInfoPacienteList);
            em.persist(infoEntidades);
            for (InfoAdmision infoAdmisionListInfoAdmision : infoEntidades.getInfoAdmisionList()) {
                InfoEntidades oldIdEntidadAdmisionOfInfoAdmisionListInfoAdmision = infoAdmisionListInfoAdmision.getIdEntidadAdmision();
                infoAdmisionListInfoAdmision.setIdEntidadAdmision(infoEntidades);
                infoAdmisionListInfoAdmision = em.merge(infoAdmisionListInfoAdmision);
                if (oldIdEntidadAdmisionOfInfoAdmisionListInfoAdmision != null) {
                    oldIdEntidadAdmisionOfInfoAdmisionListInfoAdmision.getInfoAdmisionList().remove(infoAdmisionListInfoAdmision);
                    oldIdEntidadAdmisionOfInfoAdmisionListInfoAdmision = em.merge(oldIdEntidadAdmisionOfInfoAdmisionListInfoAdmision);
                }
            }
            for (InfoPaciente infoPacienteListInfoPaciente : infoEntidades.getInfoPacienteList()) {
                InfoEntidades oldContratanteOfInfoPacienteListInfoPaciente = infoPacienteListInfoPaciente.getContratante();
                infoPacienteListInfoPaciente.setContratante(infoEntidades);
                infoPacienteListInfoPaciente = em.merge(infoPacienteListInfoPaciente);
                if (oldContratanteOfInfoPacienteListInfoPaciente != null) {
                    oldContratanteOfInfoPacienteListInfoPaciente.getInfoPacienteList().remove(infoPacienteListInfoPaciente);
                    oldContratanteOfInfoPacienteListInfoPaciente = em.merge(oldContratanteOfInfoPacienteListInfoPaciente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoEntidades infoEntidades) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoEntidades persistentInfoEntidades = em.find(InfoEntidades.class, infoEntidades.getId());
            List<InfoAdmision> infoAdmisionListOld = persistentInfoEntidades.getInfoAdmisionList();
            List<InfoAdmision> infoAdmisionListNew = infoEntidades.getInfoAdmisionList();
            List<InfoPaciente> infoPacienteListOld = persistentInfoEntidades.getInfoPacienteList();
            List<InfoPaciente> infoPacienteListNew = infoEntidades.getInfoPacienteList();
            List<String> illegalOrphanMessages = null;
            for (InfoAdmision infoAdmisionListOldInfoAdmision : infoAdmisionListOld) {
                if (!infoAdmisionListNew.contains(infoAdmisionListOldInfoAdmision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoAdmision " + infoAdmisionListOldInfoAdmision + " since its idEntidadAdmision field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InfoAdmision> attachedInfoAdmisionListNew = new ArrayList<InfoAdmision>();
            for (InfoAdmision infoAdmisionListNewInfoAdmisionToAttach : infoAdmisionListNew) {
                infoAdmisionListNewInfoAdmisionToAttach = em.getReference(infoAdmisionListNewInfoAdmisionToAttach.getClass(), infoAdmisionListNewInfoAdmisionToAttach.getId());
                attachedInfoAdmisionListNew.add(infoAdmisionListNewInfoAdmisionToAttach);
            }
            infoAdmisionListNew = attachedInfoAdmisionListNew;
            infoEntidades.setInfoAdmisionList(infoAdmisionListNew);
            List<InfoPaciente> attachedInfoPacienteListNew = new ArrayList<InfoPaciente>();
            for (InfoPaciente infoPacienteListNewInfoPacienteToAttach : infoPacienteListNew) {
                infoPacienteListNewInfoPacienteToAttach = em.getReference(infoPacienteListNewInfoPacienteToAttach.getClass(), infoPacienteListNewInfoPacienteToAttach.getId());
                attachedInfoPacienteListNew.add(infoPacienteListNewInfoPacienteToAttach);
            }
            infoPacienteListNew = attachedInfoPacienteListNew;
            infoEntidades.setInfoPacienteList(infoPacienteListNew);
            infoEntidades = em.merge(infoEntidades);
            for (InfoAdmision infoAdmisionListNewInfoAdmision : infoAdmisionListNew) {
                if (!infoAdmisionListOld.contains(infoAdmisionListNewInfoAdmision)) {
                    InfoEntidades oldIdEntidadAdmisionOfInfoAdmisionListNewInfoAdmision = infoAdmisionListNewInfoAdmision.getIdEntidadAdmision();
                    infoAdmisionListNewInfoAdmision.setIdEntidadAdmision(infoEntidades);
                    infoAdmisionListNewInfoAdmision = em.merge(infoAdmisionListNewInfoAdmision);
                    if (oldIdEntidadAdmisionOfInfoAdmisionListNewInfoAdmision != null && !oldIdEntidadAdmisionOfInfoAdmisionListNewInfoAdmision.equals(infoEntidades)) {
                        oldIdEntidadAdmisionOfInfoAdmisionListNewInfoAdmision.getInfoAdmisionList().remove(infoAdmisionListNewInfoAdmision);
                        oldIdEntidadAdmisionOfInfoAdmisionListNewInfoAdmision = em.merge(oldIdEntidadAdmisionOfInfoAdmisionListNewInfoAdmision);
                    }
                }
            }
            for (InfoPaciente infoPacienteListOldInfoPaciente : infoPacienteListOld) {
                if (!infoPacienteListNew.contains(infoPacienteListOldInfoPaciente)) {
                    infoPacienteListOldInfoPaciente.setContratante(null);
                    infoPacienteListOldInfoPaciente = em.merge(infoPacienteListOldInfoPaciente);
                }
            }
            for (InfoPaciente infoPacienteListNewInfoPaciente : infoPacienteListNew) {
                if (!infoPacienteListOld.contains(infoPacienteListNewInfoPaciente)) {
                    InfoEntidades oldContratanteOfInfoPacienteListNewInfoPaciente = infoPacienteListNewInfoPaciente.getContratante();
                    infoPacienteListNewInfoPaciente.setContratante(infoEntidades);
                    infoPacienteListNewInfoPaciente = em.merge(infoPacienteListNewInfoPaciente);
                    if (oldContratanteOfInfoPacienteListNewInfoPaciente != null && !oldContratanteOfInfoPacienteListNewInfoPaciente.equals(infoEntidades)) {
                        oldContratanteOfInfoPacienteListNewInfoPaciente.getInfoPacienteList().remove(infoPacienteListNewInfoPaciente);
                        oldContratanteOfInfoPacienteListNewInfoPaciente = em.merge(oldContratanteOfInfoPacienteListNewInfoPaciente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoEntidades.getId();
                if (findInfoEntidades(id) == null) {
                    throw new NonexistentEntityException("The infoEntidades with id " + id + " no longer exists.");
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
            InfoEntidades infoEntidades;
            try {
                infoEntidades = em.getReference(InfoEntidades.class, id);
                infoEntidades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoEntidades with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InfoAdmision> infoAdmisionListOrphanCheck = infoEntidades.getInfoAdmisionList();
            for (InfoAdmision infoAdmisionListOrphanCheckInfoAdmision : infoAdmisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InfoEntidades (" + infoEntidades + ") cannot be destroyed since the InfoAdmision " + infoAdmisionListOrphanCheckInfoAdmision + " in its infoAdmisionList field has a non-nullable idEntidadAdmision field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<InfoPaciente> infoPacienteList = infoEntidades.getInfoPacienteList();
            for (InfoPaciente infoPacienteListInfoPaciente : infoPacienteList) {
                infoPacienteListInfoPaciente.setContratante(null);
                infoPacienteListInfoPaciente = em.merge(infoPacienteListInfoPaciente);
            }
            em.remove(infoEntidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoEntidades> findInfoEntidadesEntities() {
        return findInfoEntidadesEntities(true, -1, -1);
    }

    public List<InfoEntidades> findInfoEntidadesEntities(int maxResults, int firstResult) {
        return findInfoEntidadesEntities(false, maxResults, firstResult);
    }

    private List<InfoEntidades> findInfoEntidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoEntidades.class));
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

    public InfoEntidades findInfoEntidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoEntidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoEntidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoEntidades> rt = cq.from(InfoEntidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
