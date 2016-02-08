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
import entidades_EJB.InfoEntidades;
import entidades_EJB.InfoAdmision;
import entidades_EJB.InfoPaciente;
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
public class InfoPacienteJpaController implements Serializable {

    public InfoPacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoPaciente infoPaciente) {
        if (infoPaciente.getInfoAdmisionList() == null) {
            infoPaciente.setInfoAdmisionList(new ArrayList<InfoAdmision>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoEntidades contratante = infoPaciente.getContratante();
            if (contratante != null) {
                contratante = em.getReference(contratante.getClass(), contratante.getId());
                infoPaciente.setContratante(contratante);
            }
            List<InfoAdmision> attachedInfoAdmisionList = new ArrayList<InfoAdmision>();
            for (InfoAdmision infoAdmisionListInfoAdmisionToAttach : infoPaciente.getInfoAdmisionList()) {
                infoAdmisionListInfoAdmisionToAttach = em.getReference(infoAdmisionListInfoAdmisionToAttach.getClass(), infoAdmisionListInfoAdmisionToAttach.getId());
                attachedInfoAdmisionList.add(infoAdmisionListInfoAdmisionToAttach);
            }
            infoPaciente.setInfoAdmisionList(attachedInfoAdmisionList);
            em.persist(infoPaciente);
            if (contratante != null) {
                contratante.getInfoPacienteList().add(infoPaciente);
                contratante = em.merge(contratante);
            }
            for (InfoAdmision infoAdmisionListInfoAdmision : infoPaciente.getInfoAdmisionList()) {
                InfoPaciente oldIdDatosPersonalesOfInfoAdmisionListInfoAdmision = infoAdmisionListInfoAdmision.getIdDatosPersonales();
                infoAdmisionListInfoAdmision.setIdDatosPersonales(infoPaciente);
                infoAdmisionListInfoAdmision = em.merge(infoAdmisionListInfoAdmision);
                if (oldIdDatosPersonalesOfInfoAdmisionListInfoAdmision != null) {
                    oldIdDatosPersonalesOfInfoAdmisionListInfoAdmision.getInfoAdmisionList().remove(infoAdmisionListInfoAdmision);
                    oldIdDatosPersonalesOfInfoAdmisionListInfoAdmision = em.merge(oldIdDatosPersonalesOfInfoAdmisionListInfoAdmision);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoPaciente infoPaciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoPaciente persistentInfoPaciente = em.find(InfoPaciente.class, infoPaciente.getId());
            InfoEntidades contratanteOld = persistentInfoPaciente.getContratante();
            InfoEntidades contratanteNew = infoPaciente.getContratante();
            List<InfoAdmision> infoAdmisionListOld = persistentInfoPaciente.getInfoAdmisionList();
            List<InfoAdmision> infoAdmisionListNew = infoPaciente.getInfoAdmisionList();
            List<String> illegalOrphanMessages = null;
            for (InfoAdmision infoAdmisionListOldInfoAdmision : infoAdmisionListOld) {
                if (!infoAdmisionListNew.contains(infoAdmisionListOldInfoAdmision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoAdmision " + infoAdmisionListOldInfoAdmision + " since its idDatosPersonales field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contratanteNew != null) {
                contratanteNew = em.getReference(contratanteNew.getClass(), contratanteNew.getId());
                infoPaciente.setContratante(contratanteNew);
            }
            List<InfoAdmision> attachedInfoAdmisionListNew = new ArrayList<InfoAdmision>();
            for (InfoAdmision infoAdmisionListNewInfoAdmisionToAttach : infoAdmisionListNew) {
                infoAdmisionListNewInfoAdmisionToAttach = em.getReference(infoAdmisionListNewInfoAdmisionToAttach.getClass(), infoAdmisionListNewInfoAdmisionToAttach.getId());
                attachedInfoAdmisionListNew.add(infoAdmisionListNewInfoAdmisionToAttach);
            }
            infoAdmisionListNew = attachedInfoAdmisionListNew;
            infoPaciente.setInfoAdmisionList(infoAdmisionListNew);
            infoPaciente = em.merge(infoPaciente);
            if (contratanteOld != null && !contratanteOld.equals(contratanteNew)) {
                contratanteOld.getInfoPacienteList().remove(infoPaciente);
                contratanteOld = em.merge(contratanteOld);
            }
            if (contratanteNew != null && !contratanteNew.equals(contratanteOld)) {
                contratanteNew.getInfoPacienteList().add(infoPaciente);
                contratanteNew = em.merge(contratanteNew);
            }
            for (InfoAdmision infoAdmisionListNewInfoAdmision : infoAdmisionListNew) {
                if (!infoAdmisionListOld.contains(infoAdmisionListNewInfoAdmision)) {
                    InfoPaciente oldIdDatosPersonalesOfInfoAdmisionListNewInfoAdmision = infoAdmisionListNewInfoAdmision.getIdDatosPersonales();
                    infoAdmisionListNewInfoAdmision.setIdDatosPersonales(infoPaciente);
                    infoAdmisionListNewInfoAdmision = em.merge(infoAdmisionListNewInfoAdmision);
                    if (oldIdDatosPersonalesOfInfoAdmisionListNewInfoAdmision != null && !oldIdDatosPersonalesOfInfoAdmisionListNewInfoAdmision.equals(infoPaciente)) {
                        oldIdDatosPersonalesOfInfoAdmisionListNewInfoAdmision.getInfoAdmisionList().remove(infoAdmisionListNewInfoAdmision);
                        oldIdDatosPersonalesOfInfoAdmisionListNewInfoAdmision = em.merge(oldIdDatosPersonalesOfInfoAdmisionListNewInfoAdmision);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoPaciente.getId();
                if (findInfoPaciente(id) == null) {
                    throw new NonexistentEntityException("The infoPaciente with id " + id + " no longer exists.");
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
            InfoPaciente infoPaciente;
            try {
                infoPaciente = em.getReference(InfoPaciente.class, id);
                infoPaciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoPaciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InfoAdmision> infoAdmisionListOrphanCheck = infoPaciente.getInfoAdmisionList();
            for (InfoAdmision infoAdmisionListOrphanCheckInfoAdmision : infoAdmisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InfoPaciente (" + infoPaciente + ") cannot be destroyed since the InfoAdmision " + infoAdmisionListOrphanCheckInfoAdmision + " in its infoAdmisionList field has a non-nullable idDatosPersonales field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InfoEntidades contratante = infoPaciente.getContratante();
            if (contratante != null) {
                contratante.getInfoPacienteList().remove(infoPaciente);
                contratante = em.merge(contratante);
            }
            em.remove(infoPaciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoPaciente> findInfoPacienteEntities() {
        return findInfoPacienteEntities(true, -1, -1);
    }

    public List<InfoPaciente> findInfoPacienteEntities(int maxResults, int firstResult) {
        return findInfoPacienteEntities(false, maxResults, firstResult);
    }

    private List<InfoPaciente> findInfoPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoPaciente.class));
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

    public InfoPaciente findInfoPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoPaciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoPaciente> rt = cq.from(InfoPaciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public InfoPaciente getEntidadPaciente(String id) {
        Query q = null;
        InfoPaciente pa = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT p FROM InfoPaciente p WHERE p.numDoc=:doc AND p.estado='1'");
        q.setParameter("doc", id);
        List<InfoPaciente> getEnt = q.getResultList();
        if (!getEnt.isEmpty()) {
            pa = getEnt.get(0);
        }
        return pa;
    }

    public InfoPaciente getEntidadPacienteSalCoop(String id) {//id 78 saludcoop
        Query q = null;
        InfoPaciente pa = null;
        EntityManager em = getEntityManager();
        q = em.createQuery("SELECT p FROM InfoPaciente p WHERE p.numDoc=:doc AND p.estado='1' "
                + "AND (p.contratante.id='78' OR p.contratante.id='117' OR p.contratante.id='124' OR p.contratante.id='5' OR p.contratante.id='141')");
        q.setParameter("doc", id);
        List<InfoPaciente> getEnt = q.getResultList();
        if (!getEnt.isEmpty()) {
            pa = getEnt.get(0);
        }
        return pa;
    }
}
