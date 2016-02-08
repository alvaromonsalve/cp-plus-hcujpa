package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.InfoAdmision;
import entidades_EJB.UciHcExpfisica;
import entidades_EJB.Configdecripcionlogin;
import entidades_EJB.UciPruebasComplement;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.UciCamas;
import entidades_EJB.UciHistoriac;
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
public class UciHistoriacJpaController implements Serializable {

    public UciHistoriacJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UciHistoriac uciHistoriac) {
        if (uciHistoriac.getUciPruebasComplements() == null) {
            uciHistoriac.setUciPruebasComplements(new ArrayList<UciPruebasComplement>());
        }
        if (uciHistoriac.getUciCamasList() == null) {
            uciHistoriac.setUciCamasList(new ArrayList<UciCamas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoAdmision idInfoAdmision = uciHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision = em.getReference(idInfoAdmision.getClass(), idInfoAdmision.getId());
                uciHistoriac.setIdInfoAdmision(idInfoAdmision);
            }
            UciHcExpfisica uciHcExpfisica = uciHistoriac.getUciHcExpfisica();
            if (uciHcExpfisica != null) {
                uciHcExpfisica = em.getReference(uciHcExpfisica.getClass(), uciHcExpfisica.getId());
                uciHistoriac.setUciHcExpfisica(uciHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = uciHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin = em.getReference(idConfigdecripcionlogin.getClass(), idConfigdecripcionlogin.getId());
                uciHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionlogin);
            }
            List<UciPruebasComplement> attachedUciPruebasComplements = new ArrayList<UciPruebasComplement>();
            for (UciPruebasComplement uciPruebasComplementsUciPruebasComplementToAttach : uciHistoriac.getUciPruebasComplements()) {
                uciPruebasComplementsUciPruebasComplementToAttach = em.getReference(uciPruebasComplementsUciPruebasComplementToAttach.getClass(), uciPruebasComplementsUciPruebasComplementToAttach.getId());
                attachedUciPruebasComplements.add(uciPruebasComplementsUciPruebasComplementToAttach);
            }
            uciHistoriac.setUciPruebasComplements(attachedUciPruebasComplements);
            List<UciCamas> attachedUciCamasList = new ArrayList<UciCamas>();
            for (UciCamas uciCamasListuciCamasToAttach : uciHistoriac.getUciCamasList()) {
                uciCamasListuciCamasToAttach = em.getReference(uciCamasListuciCamasToAttach.getClass(), uciCamasListuciCamasToAttach.getId());
                attachedUciCamasList.add(uciCamasListuciCamasToAttach);
            }
            uciHistoriac.setUciCamasList(attachedUciCamasList);
            em.persist(uciHistoriac);
            if (idInfoAdmision != null) {
                idInfoAdmision.getUciHistoriacList().add(uciHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            if (uciHcExpfisica != null) {
                UciHistoriac oldIdUcihistoriacOfUciHcExpfisica = uciHcExpfisica.getIdUcihistoriac();
                if (oldIdUcihistoriacOfUciHcExpfisica != null) {
                    oldIdUcihistoriacOfUciHcExpfisica.setUciHcExpfisica(null);
                    oldIdUcihistoriacOfUciHcExpfisica = em.merge(oldIdUcihistoriacOfUciHcExpfisica);
                }
                uciHcExpfisica.setIdUcihistoriac(uciHistoriac);
                uciHcExpfisica = em.merge(uciHcExpfisica);
            }
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getUciHistoriac().add(uciHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            for (UciPruebasComplement uciPruebasComplementsUciPruebasComplement : uciHistoriac.getUciPruebasComplements()) {
                UciHistoriac oldIdUcihistoriacOfUciPruebasComplementsUciPruebasComplement = uciPruebasComplementsUciPruebasComplement.getIdUcihistoriac();
                uciPruebasComplementsUciPruebasComplement.setIdHosphistoriac(uciHistoriac);
                uciPruebasComplementsUciPruebasComplement = em.merge(uciPruebasComplementsUciPruebasComplement);
                if (oldIdUcihistoriacOfUciPruebasComplementsUciPruebasComplement != null) {
                    oldIdUcihistoriacOfUciPruebasComplementsUciPruebasComplement.getUciPruebasComplements().remove(uciPruebasComplementsUciPruebasComplement);
                    oldIdUcihistoriacOfUciPruebasComplementsUciPruebasComplement = em.merge(oldIdUcihistoriacOfUciPruebasComplementsUciPruebasComplement);
                }
            }
            for (UciCamas uciCamasListUciCamas : uciHistoriac.getUciCamasList()) {
                UciHistoriac oldIdUciHistoriacOfUciCamasListUciCamas = uciCamasListUciCamas.getIdUciHistoriac();
                uciCamasListUciCamas.setIdUciHistoriac(uciHistoriac);
                uciCamasListUciCamas = em.merge(uciCamasListUciCamas);
                if (oldIdUciHistoriacOfUciCamasListUciCamas != null) {
                    oldIdUciHistoriacOfUciCamasListUciCamas.getUciCamasList().remove(uciCamasListUciCamas);
                    oldIdUciHistoriacOfUciCamasListUciCamas = em.merge(oldIdUciHistoriacOfUciCamasListUciCamas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UciHistoriac uciHistoriac) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UciHistoriac persistentUciHistoriac = em.find(UciHistoriac.class, uciHistoriac.getId());
            InfoAdmision idInfoAdmisionOld = persistentUciHistoriac.getIdInfoAdmision();
            InfoAdmision idInfoAdmisionNew = uciHistoriac.getIdInfoAdmision();
            UciHcExpfisica uciHcExpfisicaOld = persistentUciHistoriac.getUciHcExpfisica();
            UciHcExpfisica uciHcExpfisicaNew = uciHistoriac.getUciHcExpfisica();
            Configdecripcionlogin idConfigdecripcionloginOld = persistentUciHistoriac.getIdConfigdecripcionlogin();
            Configdecripcionlogin idConfigdecripcionloginNew = uciHistoriac.getIdConfigdecripcionlogin();
            List<UciPruebasComplement> uciPruebasComplementsOld = persistentUciHistoriac.getUciPruebasComplements();
            List<UciPruebasComplement> uciPruebasComplementsNew = uciHistoriac.getUciPruebasComplements();
            List<UciCamas> uciCamasListOld = persistentUciHistoriac.getUciCamasList();
            List<UciCamas> uciCamasListNew = uciHistoriac.getUciCamasList();
            List<String> illegalOrphanMessages = null;
            for (UciPruebasComplement uciPruebasComplementsOldUciPruebasComplement : uciPruebasComplementsOld) {
                if (!uciPruebasComplementsNew.contains(uciPruebasComplementsOldUciPruebasComplement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciPruebasComplement " + uciPruebasComplementsOldUciPruebasComplement + " since its idUcihistoriac field is not nullable.");
                }
            }
            for (UciCamas uciCamasListOldUciCamas : uciCamasListOld) {
                if (!uciCamasListNew.contains(uciCamasListOldUciCamas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UciCamas " + uciCamasListOldUciCamas + " since its idUciHistoriac field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idInfoAdmisionNew != null) {
                idInfoAdmisionNew = em.getReference(idInfoAdmisionNew.getClass(), idInfoAdmisionNew.getId());
                uciHistoriac.setIdInfoAdmision(idInfoAdmisionNew);
            }
            if (uciHcExpfisicaNew != null) {
                uciHcExpfisicaNew = em.getReference(uciHcExpfisicaNew.getClass(), uciHcExpfisicaNew.getId());
                uciHistoriac.setUciHcExpfisica(uciHcExpfisicaNew);
            }
            if (idConfigdecripcionloginNew != null) {
                idConfigdecripcionloginNew = em.getReference(idConfigdecripcionloginNew.getClass(), idConfigdecripcionloginNew.getId());
                uciHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionloginNew);
            }
            List<UciPruebasComplement> attachedUciPruebasComplementsNew = new ArrayList<UciPruebasComplement>();
            for (UciPruebasComplement uciPruebasComplementsNewUciPruebasComplementToAttach : uciPruebasComplementsNew) {
                uciPruebasComplementsNewUciPruebasComplementToAttach = em.getReference(uciPruebasComplementsNewUciPruebasComplementToAttach.getClass(), uciPruebasComplementsNewUciPruebasComplementToAttach.getId());
                attachedUciPruebasComplementsNew.add(uciPruebasComplementsNewUciPruebasComplementToAttach);
            }
            uciPruebasComplementsNew = attachedUciPruebasComplementsNew;
            uciHistoriac.setUciPruebasComplements(uciPruebasComplementsNew);
            List<UciCamas> attachedUciCamasListNew = new ArrayList<UciCamas>();
            for (UciCamas uciCamasListNewUciCamasToAttach : uciCamasListNew) {
                uciCamasListNewUciCamasToAttach = em.getReference(uciCamasListNewUciCamasToAttach.getClass(), uciCamasListNewUciCamasToAttach.getId());
                attachedUciCamasListNew.add(uciCamasListNewUciCamasToAttach);
            }
            uciCamasListNew = attachedUciCamasListNew;
            uciHistoriac.setUciCamasList(uciCamasListNew);
            uciHistoriac = em.merge(uciHistoriac);
            if (idInfoAdmisionOld != null && !idInfoAdmisionOld.equals(idInfoAdmisionNew)) {
                idInfoAdmisionOld.getUciHistoriacList().remove(uciHistoriac);
                idInfoAdmisionOld = em.merge(idInfoAdmisionOld);
            }
            if (idInfoAdmisionNew != null && !idInfoAdmisionNew.equals(idInfoAdmisionOld)) {
                idInfoAdmisionNew.getUciHistoriacList().add(uciHistoriac);
                idInfoAdmisionNew = em.merge(idInfoAdmisionNew);
            }
            if (uciHcExpfisicaOld != null && !uciHcExpfisicaOld.equals(uciHcExpfisicaNew)) {
                uciHcExpfisicaOld.setIdUcihistoriac(null);
                uciHcExpfisicaOld = em.merge(uciHcExpfisicaOld);
            }
            if (uciHcExpfisicaNew != null && !uciHcExpfisicaNew.equals(uciHcExpfisicaOld)) {
                UciHistoriac oldIdUcihistoriacOfUciHcExpfisica = uciHcExpfisicaNew.getIdUcihistoriac();
                if (oldIdUcihistoriacOfUciHcExpfisica != null) {
                    oldIdUcihistoriacOfUciHcExpfisica.setUciHcExpfisica(null);
                    oldIdUcihistoriacOfUciHcExpfisica = em.merge(oldIdUcihistoriacOfUciHcExpfisica);
                }
                uciHcExpfisicaNew.setIdUcihistoriac(uciHistoriac);
                uciHcExpfisicaNew = em.merge(uciHcExpfisicaNew);
            }
            if (idConfigdecripcionloginOld != null && !idConfigdecripcionloginOld.equals(idConfigdecripcionloginNew)) {
                idConfigdecripcionloginOld.getUciHistoriac().remove(uciHistoriac);
                idConfigdecripcionloginOld = em.merge(idConfigdecripcionloginOld);
            }
            if (idConfigdecripcionloginNew != null && !idConfigdecripcionloginNew.equals(idConfigdecripcionloginOld)) {
                idConfigdecripcionloginNew.getUciHistoriac().add(uciHistoriac);
                idConfigdecripcionloginNew = em.merge(idConfigdecripcionloginNew);
            }
            for (UciPruebasComplement uciPruebasComplementsNewUciPruebasComplement : uciPruebasComplementsNew) {
                if (!uciPruebasComplementsOld.contains(uciPruebasComplementsNewUciPruebasComplement)) {
                    UciHistoriac oldIdUcihistoriacOfUciPruebasComplementsNewUciPruebasComplement = uciPruebasComplementsNewUciPruebasComplement.getIdUcihistoriac();
                    uciPruebasComplementsNewUciPruebasComplement.setIdHosphistoriac(uciHistoriac);
                    uciPruebasComplementsNewUciPruebasComplement = em.merge(uciPruebasComplementsNewUciPruebasComplement);
                    if (oldIdUcihistoriacOfUciPruebasComplementsNewUciPruebasComplement != null && !oldIdUcihistoriacOfUciPruebasComplementsNewUciPruebasComplement.equals(uciHistoriac)) {
                        oldIdUcihistoriacOfUciPruebasComplementsNewUciPruebasComplement.getUciPruebasComplements().remove(uciPruebasComplementsNewUciPruebasComplement);
                        oldIdUcihistoriacOfUciPruebasComplementsNewUciPruebasComplement = em.merge(oldIdUcihistoriacOfUciPruebasComplementsNewUciPruebasComplement);
                    }
                }
            }
            for (UciCamas uciCamasListNewUciCamas : uciCamasListNew) {
                if (!uciCamasListOld.contains(uciCamasListNewUciCamas)) {
                    UciHistoriac oldIdUciHistoriacOfUciCamasListNewUciCamas = uciCamasListNewUciCamas.getIdUciHistoriac();
                    uciCamasListNewUciCamas.setIdUciHistoriac(uciHistoriac);
                    uciCamasListNewUciCamas = em.merge(uciCamasListNewUciCamas);
                    if (oldIdUciHistoriacOfUciCamasListNewUciCamas != null && !oldIdUciHistoriacOfUciCamasListNewUciCamas.equals(uciHistoriac)) {
                        oldIdUciHistoriacOfUciCamasListNewUciCamas.getUciCamasList().remove(uciCamasListNewUciCamas);
                        oldIdUciHistoriacOfUciCamasListNewUciCamas = em.merge(oldIdUciHistoriacOfUciCamasListNewUciCamas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uciHistoriac.getId();
                if (findUciHistoriac(id) == null) {
                    throw new NonexistentEntityException("The uciHistoriac with id " + id + " no longer exists.");
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
            UciHistoriac uciHistoriac;
            try {
                uciHistoriac = em.getReference(UciHistoriac.class, id);
                uciHistoriac.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uciHistoriac with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UciPruebasComplement> uciPruebasComplementsOrphanCheck = uciHistoriac.getUciPruebasComplements();
            for (UciPruebasComplement uciPruebasComplementsOrphanCheckUciPruebasComplement : uciPruebasComplementsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciHistoriac (" + uciHistoriac + ") cannot be destroyed since the UciPruebasComplement " + uciPruebasComplementsOrphanCheckUciPruebasComplement + " in its uciPruebasComplements field has a non-nullable idUcihistoriac field.");
            }
            List<UciCamas> uciCamasListOrphanCheck = uciHistoriac.getUciCamasList();
            for (UciCamas uciCamasListOrphanCheckUciCamas : uciCamasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UciHistoriac (" + uciHistoriac + ") cannot be destroyed since the UciCamas " + uciCamasListOrphanCheckUciCamas + " in its uciCamasList field has a non-nullable idUciHistoriac field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InfoAdmision idInfoAdmision = uciHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision.getUciHistoriacList().remove(uciHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            UciHcExpfisica uciHcExpfisica = uciHistoriac.getUciHcExpfisica();
            if (uciHcExpfisica != null) {
                uciHcExpfisica.setIdUcihistoriac(null);
                uciHcExpfisica = em.merge(uciHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = uciHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getUciHistoriac().remove(uciHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            em.remove(uciHistoriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UciHistoriac> findUciHistoriacEntities() {
        return findUciHistoriacEntities(true, -1, -1);
    }

    public List<UciHistoriac> findUciHistoriacEntities(int maxResults, int firstResult) {
        return findUciHistoriacEntities(false, maxResults, firstResult);
    }

    private List<UciHistoriac> findUciHistoriacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UciHistoriac.class));
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

    public UciHistoriac findUciHistoriac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UciHistoriac.class, id);
        } finally {
            em.close();
        }
    }

    public int getUciHistoriacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UciHistoriac> rt = cq.from(UciHistoriac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codifo no Auto-Generado
    public List<UciHistoriac> finduciHistoriacs(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM UciHistoriac i WHERE i.estado = :estado")
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public UciHistoriac finduciHistoriac(String numDoc, int estado) {
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM UciHistoriac i WHERE i.estado <= :estado AND i.idInfoAdmision.idDatosPersonales.numDoc = :doc")
                    .setParameter("estado", estado)
                    .setParameter("doc", numDoc)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
            if (results.isEmpty()) {
                return null;
            } else if (results.size() == 1) {
                return (UciHistoriac) results.get(0);
            } else {
                JOptionPane.showMessageDialog(null, "Informe al administrador del sistema de este error:\n"
                        + "Es posible que existan varias HC activas.\n", HospHistoriacJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }

    public List<UciHistoriac> findHistoriac_() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM UciHistoriac i WHERE i.tipoHc='0' AND i.estado='1'");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public Object getTipoHC(UciHistoriac ihc) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT h.tipoHc FROM UciHistoriac h WHERE h=:idht");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        Q.setParameter("idht", ihc);
        return Q.getSingleResult();
    }

    public List<UciHistoriac> getHistoriasALL(String ide) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT h FROM UciHistoriac h WHERE h.idInfoAdmision.idDatosPersonales.numDoc=:document AND h.estado <>'5' AND h.idInfoAdmision.idDatosPersonales.estado='1'");
        Q.setParameter("document", ide);
        return Q.getResultList();
    }

    public List<UciHistoriac> getUCI(InfoAdmision a) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT h FROM UciHistoriac h WHERE h.idInfoAdmision=:adm AND (h.estado='4' OR h.estado='2' OR h.estado='3')");
        Q.setParameter("adm", a);
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
