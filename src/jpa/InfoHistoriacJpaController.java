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
import entidades_EJB.InfoHcExpfisica;
import entidades_EJB.Configdecripcionlogin;
import entidades_EJB.InfoPruebasComplement;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.InfoCamas;
import entidades_EJB.InfoHistoriac;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.swing.JOptionPane;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class InfoHistoriacJpaController implements Serializable {

    public InfoHistoriacJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InfoHistoriac infoHistoriac) {
        if (infoHistoriac.getInfoPruebasComplements() == null) {
            infoHistoriac.setInfoPruebasComplements(new ArrayList<InfoPruebasComplement>());
        }
        if (infoHistoriac.getInfoCamasList() == null) {
            infoHistoriac.setInfoCamasList(new ArrayList<InfoCamas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoAdmision idInfoAdmision = infoHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision = em.getReference(idInfoAdmision.getClass(), idInfoAdmision.getId());
                infoHistoriac.setIdInfoAdmision(idInfoAdmision);
            }
            InfoHcExpfisica infoHcExpfisica = infoHistoriac.getInfoHcExpfisica();
            if (infoHcExpfisica != null) {
                infoHcExpfisica = em.getReference(infoHcExpfisica.getClass(), infoHcExpfisica.getId());
                infoHistoriac.setInfoHcExpfisica(infoHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = infoHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin = em.getReference(idConfigdecripcionlogin.getClass(), idConfigdecripcionlogin.getId());
                infoHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionlogin);
            }
            List<InfoPruebasComplement> attachedInfoPruebasComplements = new ArrayList<InfoPruebasComplement>();
            for (InfoPruebasComplement infoPruebasComplementsInfoPruebasComplementToAttach : infoHistoriac.getInfoPruebasComplements()) {
                infoPruebasComplementsInfoPruebasComplementToAttach = em.getReference(infoPruebasComplementsInfoPruebasComplementToAttach.getClass(), infoPruebasComplementsInfoPruebasComplementToAttach.getId());
                attachedInfoPruebasComplements.add(infoPruebasComplementsInfoPruebasComplementToAttach);
            }
            infoHistoriac.setInfoPruebasComplements(attachedInfoPruebasComplements);
            List<InfoCamas> attachedInfoCamasList = new ArrayList<InfoCamas>();
            for (InfoCamas infoCamasListInfoCamasToAttach : infoHistoriac.getInfoCamasList()) {
                infoCamasListInfoCamasToAttach = em.getReference(infoCamasListInfoCamasToAttach.getClass(), infoCamasListInfoCamasToAttach.getId());
                attachedInfoCamasList.add(infoCamasListInfoCamasToAttach);
            }
            infoHistoriac.setInfoCamasList(attachedInfoCamasList);
            em.persist(infoHistoriac);
            if (idInfoAdmision != null) {
                idInfoAdmision.getInfoHistoriacList().add(infoHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            if (infoHcExpfisica != null) {
                InfoHistoriac oldIdInfohistoriacOfInfoHcExpfisica = infoHcExpfisica.getIdInfohistoriac();
                if (oldIdInfohistoriacOfInfoHcExpfisica != null) {
                    oldIdInfohistoriacOfInfoHcExpfisica.setInfoHcExpfisica(null);
                    oldIdInfohistoriacOfInfoHcExpfisica = em.merge(oldIdInfohistoriacOfInfoHcExpfisica);
                }
                infoHcExpfisica.setIdInfohistoriac(infoHistoriac);
                infoHcExpfisica = em.merge(infoHcExpfisica);
            }
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getInfoHistoriac().add(infoHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            for (InfoPruebasComplement infoPruebasComplementsInfoPruebasComplement : infoHistoriac.getInfoPruebasComplements()) {
                InfoHistoriac oldIdInfohistoriacOfInfoPruebasComplementsInfoPruebasComplement = infoPruebasComplementsInfoPruebasComplement.getIdInfohistoriac();
                infoPruebasComplementsInfoPruebasComplement.setIdInfohistoriac(infoHistoriac);
                infoPruebasComplementsInfoPruebasComplement = em.merge(infoPruebasComplementsInfoPruebasComplement);
                if (oldIdInfohistoriacOfInfoPruebasComplementsInfoPruebasComplement != null) {
                    oldIdInfohistoriacOfInfoPruebasComplementsInfoPruebasComplement.getInfoPruebasComplements().remove(infoPruebasComplementsInfoPruebasComplement);
                    oldIdInfohistoriacOfInfoPruebasComplementsInfoPruebasComplement = em.merge(oldIdInfohistoriacOfInfoPruebasComplementsInfoPruebasComplement);
                }
            }
            for (InfoCamas infoCamasListInfoCamas : infoHistoriac.getInfoCamasList()) {
                InfoHistoriac oldIdInfoHistoriacOfInfoCamasListInfoCamas = infoCamasListInfoCamas.getIdInfoHistoriac();
                infoCamasListInfoCamas.setIdInfoHistoriac(infoHistoriac);
                infoCamasListInfoCamas = em.merge(infoCamasListInfoCamas);
                if (oldIdInfoHistoriacOfInfoCamasListInfoCamas != null) {
                    oldIdInfoHistoriacOfInfoCamasListInfoCamas.getInfoCamasList().remove(infoCamasListInfoCamas);
                    oldIdInfoHistoriacOfInfoCamasListInfoCamas = em.merge(oldIdInfoHistoriacOfInfoCamasListInfoCamas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InfoHistoriac infoHistoriac) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoHistoriac persistentInfoHistoriac = em.find(InfoHistoriac.class, infoHistoriac.getId());
            InfoAdmision idInfoAdmisionOld = persistentInfoHistoriac.getIdInfoAdmision();
            InfoAdmision idInfoAdmisionNew = infoHistoriac.getIdInfoAdmision();
            InfoHcExpfisica infoHcExpfisicaOld = persistentInfoHistoriac.getInfoHcExpfisica();
            InfoHcExpfisica infoHcExpfisicaNew = infoHistoriac.getInfoHcExpfisica();
            Configdecripcionlogin idConfigdecripcionloginOld = persistentInfoHistoriac.getIdConfigdecripcionlogin();
            Configdecripcionlogin idConfigdecripcionloginNew = infoHistoriac.getIdConfigdecripcionlogin();
            List<InfoPruebasComplement> infoPruebasComplementsOld = persistentInfoHistoriac.getInfoPruebasComplements();
            List<InfoPruebasComplement> infoPruebasComplementsNew = infoHistoriac.getInfoPruebasComplements();
            List<InfoCamas> infoCamasListOld = persistentInfoHistoriac.getInfoCamasList();
            List<InfoCamas> infoCamasListNew = infoHistoriac.getInfoCamasList();
            List<String> illegalOrphanMessages = null;
            for (InfoPruebasComplement infoPruebasComplementsOldInfoPruebasComplement : infoPruebasComplementsOld) {
                if (!infoPruebasComplementsNew.contains(infoPruebasComplementsOldInfoPruebasComplement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoPruebasComplement " + infoPruebasComplementsOldInfoPruebasComplement + " since its idInfohistoriac field is not nullable.");
                }
            }
            for (InfoCamas infoCamasListOldInfoCamas : infoCamasListOld) {
                if (!infoCamasListNew.contains(infoCamasListOldInfoCamas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InfoCamas " + infoCamasListOldInfoCamas + " since its idInfoHistoriac field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idInfoAdmisionNew != null) {
                idInfoAdmisionNew = em.getReference(idInfoAdmisionNew.getClass(), idInfoAdmisionNew.getId());
                infoHistoriac.setIdInfoAdmision(idInfoAdmisionNew);
            }
            if (infoHcExpfisicaNew != null) {
                infoHcExpfisicaNew = em.getReference(infoHcExpfisicaNew.getClass(), infoHcExpfisicaNew.getId());
                infoHistoriac.setInfoHcExpfisica(infoHcExpfisicaNew);
            }
            if (idConfigdecripcionloginNew != null) {
                idConfigdecripcionloginNew = em.getReference(idConfigdecripcionloginNew.getClass(), idConfigdecripcionloginNew.getId());
                infoHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionloginNew);
            }
            List<InfoPruebasComplement> attachedInfoPruebasComplementsNew = new ArrayList<InfoPruebasComplement>();
            for (InfoPruebasComplement infoPruebasComplementsNewInfoPruebasComplementToAttach : infoPruebasComplementsNew) {
                infoPruebasComplementsNewInfoPruebasComplementToAttach = em.getReference(infoPruebasComplementsNewInfoPruebasComplementToAttach.getClass(), infoPruebasComplementsNewInfoPruebasComplementToAttach.getId());
                attachedInfoPruebasComplementsNew.add(infoPruebasComplementsNewInfoPruebasComplementToAttach);
            }
            infoPruebasComplementsNew = attachedInfoPruebasComplementsNew;
            infoHistoriac.setInfoPruebasComplements(infoPruebasComplementsNew);
            List<InfoCamas> attachedInfoCamasListNew = new ArrayList<InfoCamas>();
            for (InfoCamas infoCamasListNewInfoCamasToAttach : infoCamasListNew) {
                infoCamasListNewInfoCamasToAttach = em.getReference(infoCamasListNewInfoCamasToAttach.getClass(), infoCamasListNewInfoCamasToAttach.getId());
                attachedInfoCamasListNew.add(infoCamasListNewInfoCamasToAttach);
            }
            infoCamasListNew = attachedInfoCamasListNew;
            infoHistoriac.setInfoCamasList(infoCamasListNew);
            infoHistoriac = em.merge(infoHistoriac);
            if (idInfoAdmisionOld != null && !idInfoAdmisionOld.equals(idInfoAdmisionNew)) {
                idInfoAdmisionOld.getInfoHistoriacList().remove(infoHistoriac);
                idInfoAdmisionOld = em.merge(idInfoAdmisionOld);
            }
            if (idInfoAdmisionNew != null && !idInfoAdmisionNew.equals(idInfoAdmisionOld)) {
                idInfoAdmisionNew.getInfoHistoriacList().add(infoHistoriac);
                idInfoAdmisionNew = em.merge(idInfoAdmisionNew);
            }
            if (infoHcExpfisicaOld != null && !infoHcExpfisicaOld.equals(infoHcExpfisicaNew)) {
                infoHcExpfisicaOld.setIdInfohistoriac(null);
                infoHcExpfisicaOld = em.merge(infoHcExpfisicaOld);
            }
            if (infoHcExpfisicaNew != null && !infoHcExpfisicaNew.equals(infoHcExpfisicaOld)) {
                InfoHistoriac oldIdInfohistoriacOfInfoHcExpfisica = infoHcExpfisicaNew.getIdInfohistoriac();
                if (oldIdInfohistoriacOfInfoHcExpfisica != null) {
                    oldIdInfohistoriacOfInfoHcExpfisica.setInfoHcExpfisica(null);
                    oldIdInfohistoriacOfInfoHcExpfisica = em.merge(oldIdInfohistoriacOfInfoHcExpfisica);
                }
                infoHcExpfisicaNew.setIdInfohistoriac(infoHistoriac);
                infoHcExpfisicaNew = em.merge(infoHcExpfisicaNew);
            }
            if (idConfigdecripcionloginOld != null && !idConfigdecripcionloginOld.equals(idConfigdecripcionloginNew)) {
                idConfigdecripcionloginOld.getInfoHistoriac().remove(infoHistoriac);
                idConfigdecripcionloginOld = em.merge(idConfigdecripcionloginOld);
            }
            if (idConfigdecripcionloginNew != null && !idConfigdecripcionloginNew.equals(idConfigdecripcionloginOld)) {
                idConfigdecripcionloginNew.getInfoHistoriac().add(infoHistoriac);
                idConfigdecripcionloginNew = em.merge(idConfigdecripcionloginNew);
            }
            for (InfoPruebasComplement infoPruebasComplementsNewInfoPruebasComplement : infoPruebasComplementsNew) {
                if (!infoPruebasComplementsOld.contains(infoPruebasComplementsNewInfoPruebasComplement)) {
                    InfoHistoriac oldIdInfohistoriacOfInfoPruebasComplementsNewInfoPruebasComplement = infoPruebasComplementsNewInfoPruebasComplement.getIdInfohistoriac();
                    infoPruebasComplementsNewInfoPruebasComplement.setIdInfohistoriac(infoHistoriac);
                    infoPruebasComplementsNewInfoPruebasComplement = em.merge(infoPruebasComplementsNewInfoPruebasComplement);
                    if (oldIdInfohistoriacOfInfoPruebasComplementsNewInfoPruebasComplement != null && !oldIdInfohistoriacOfInfoPruebasComplementsNewInfoPruebasComplement.equals(infoHistoriac)) {
                        oldIdInfohistoriacOfInfoPruebasComplementsNewInfoPruebasComplement.getInfoPruebasComplements().remove(infoPruebasComplementsNewInfoPruebasComplement);
                        oldIdInfohistoriacOfInfoPruebasComplementsNewInfoPruebasComplement = em.merge(oldIdInfohistoriacOfInfoPruebasComplementsNewInfoPruebasComplement);
                    }
                }
            }
            for (InfoCamas infoCamasListNewInfoCamas : infoCamasListNew) {
                if (!infoCamasListOld.contains(infoCamasListNewInfoCamas)) {
                    InfoHistoriac oldIdInfoHistoriacOfInfoCamasListNewInfoCamas = infoCamasListNewInfoCamas.getIdInfoHistoriac();
                    infoCamasListNewInfoCamas.setIdInfoHistoriac(infoHistoriac);
                    infoCamasListNewInfoCamas = em.merge(infoCamasListNewInfoCamas);
                    if (oldIdInfoHistoriacOfInfoCamasListNewInfoCamas != null && !oldIdInfoHistoriacOfInfoCamasListNewInfoCamas.equals(infoHistoriac)) {
                        oldIdInfoHistoriacOfInfoCamasListNewInfoCamas.getInfoCamasList().remove(infoCamasListNewInfoCamas);
                        oldIdInfoHistoriacOfInfoCamasListNewInfoCamas = em.merge(oldIdInfoHistoriacOfInfoCamasListNewInfoCamas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = infoHistoriac.getId();
                if (findInfoHistoriac(id) == null) {
                    throw new NonexistentEntityException("The infoHistoriac with id " + id + " no longer exists.");
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
            InfoHistoriac infoHistoriac;
            try {
                infoHistoriac = em.getReference(InfoHistoriac.class, id);
                infoHistoriac.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infoHistoriac with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<InfoPruebasComplement> infoPruebasComplementsOrphanCheck = infoHistoriac.getInfoPruebasComplements();
            for (InfoPruebasComplement infoPruebasComplementsOrphanCheckInfoPruebasComplement : infoPruebasComplementsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InfoHistoriac (" + infoHistoriac + ") cannot be destroyed since the InfoPruebasComplement " + infoPruebasComplementsOrphanCheckInfoPruebasComplement + " in its infoPruebasComplements field has a non-nullable idInfohistoriac field.");
            }
            List<InfoCamas> infoCamasListOrphanCheck = infoHistoriac.getInfoCamasList();
            for (InfoCamas infoCamasListOrphanCheckInfoCamas : infoCamasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This InfoHistoriac (" + infoHistoriac + ") cannot be destroyed since the InfoCamas " + infoCamasListOrphanCheckInfoCamas + " in its infoCamasList field has a non-nullable idInfoHistoriac field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InfoAdmision idInfoAdmision = infoHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision.getInfoHistoriacList().remove(infoHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            InfoHcExpfisica infoHcExpfisica = infoHistoriac.getInfoHcExpfisica();
            if (infoHcExpfisica != null) {
                infoHcExpfisica.setIdInfohistoriac(null);
                infoHcExpfisica = em.merge(infoHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = infoHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getInfoHistoriac().remove(infoHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            em.remove(infoHistoriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InfoHistoriac> findInfoHistoriacEntities() {
        return findInfoHistoriacEntities(true, -1, -1);
    }

    public List<InfoHistoriac> findInfoHistoriacEntities(int maxResults, int firstResult) {
        return findInfoHistoriacEntities(false, maxResults, firstResult);
    }

    private List<InfoHistoriac> findInfoHistoriacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InfoHistoriac.class));
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

    public InfoHistoriac findInfoHistoriac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InfoHistoriac.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfoHistoriacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InfoHistoriac> rt = cq.from(InfoHistoriac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codifo no Auto-Generado
    public List<InfoHistoriac> findinfoHistoriacs(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM InfoHistoriac i WHERE i.estado = :estado")
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public InfoHistoriac findinfoHistoriac(String numDoc, int estado) {
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM InfoHistoriac i WHERE i.estado <= :estado AND i.idInfoAdmision.idDatosPersonales.numDoc = :doc")
                    .setParameter("estado", estado)
                    .setParameter("doc", numDoc)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
            if (results.isEmpty()) {
                return null;
            } else if (results.size() == 1) {
                return (InfoHistoriac) results.get(0);
            } else {
                JOptionPane.showMessageDialog(null, "Informe al administrador del sistema de este error:\n"
                        + "Es posible que existan varias HC activas.\n", InfoHistoriacJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }

    public List<InfoHistoriac> findHistoriac_() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM InfoHistoriac i WHERE i.tipoHc='0' AND i.estado='1'");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public Object getTipoHC(InfoHistoriac ihc) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT h.tipoHc FROM InfoHistoriac h WHERE h=:idht");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        Q.setParameter("idht", ihc);
        return Q.getSingleResult();
    }

    public List<InfoHistoriac> getHistoriasALL(String ide) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT h FROM InfoHistoriac h WHERE h.idInfoAdmision.idDatosPersonales.numDoc=:document AND h.estado <>'5' AND h.idInfoAdmision.idDatosPersonales.estado='1'");
        Q.setParameter("document", ide);
        return Q.getResultList();
    }

    public List<InfoHistoriac> getUrgencias(InfoAdmision a) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT u FROM InfoHistoriac u WHERE u.idInfoAdmision.id=:adm AND (u.estado='4' OR u.estado='2' OR u.estado='3')");
        Q.setParameter("adm", a.getId());
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
