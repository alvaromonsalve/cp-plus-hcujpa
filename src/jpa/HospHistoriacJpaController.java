package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades_EJB.InfoAdmision;
import entidades_EJB.HospHcExpfisica;
import entidades_EJB.Configdecripcionlogin;
import entidades_EJB.HospPruebasComplement;
import java.util.ArrayList;
import java.util.List;
import entidades_EJB.HospCamas;
import entidades_EJB.HospHistoriac;
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
public class HospHistoriacJpaController implements Serializable {

    public HospHistoriacJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HospHistoriac hospHistoriac) {
        if (hospHistoriac.getHospPruebasComplements() == null) {
            hospHistoriac.setHospPruebasComplements(new ArrayList<HospPruebasComplement>());
        }
        if (hospHistoriac.getHospCamasList() == null) {
            hospHistoriac.setHospCamasList(new ArrayList<HospCamas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoAdmision idInfoAdmision = hospHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision = em.getReference(idInfoAdmision.getClass(), idInfoAdmision.getId());
                hospHistoriac.setIdInfoAdmision(idInfoAdmision);
            }
            HospHcExpfisica hospHcExpfisica = hospHistoriac.getHospHcExpfisica();
            if (hospHcExpfisica != null) {
                hospHcExpfisica = em.getReference(hospHcExpfisica.getClass(), hospHcExpfisica.getId());
                hospHistoriac.setHospHcExpfisica(hospHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = hospHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin = em.getReference(idConfigdecripcionlogin.getClass(), idConfigdecripcionlogin.getId());
                hospHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionlogin);
            }
            List<HospPruebasComplement> attachedHospPruebasComplements = new ArrayList<HospPruebasComplement>();
            for (HospPruebasComplement hospPruebasComplementsHospPruebasComplementToAttach : hospHistoriac.getHospPruebasComplements()) {
                hospPruebasComplementsHospPruebasComplementToAttach = em.getReference(hospPruebasComplementsHospPruebasComplementToAttach.getClass(), hospPruebasComplementsHospPruebasComplementToAttach.getId());
                attachedHospPruebasComplements.add(hospPruebasComplementsHospPruebasComplementToAttach);
            }
            hospHistoriac.setHospPruebasComplements(attachedHospPruebasComplements);
            List<HospCamas> attachedHospCamasList = new ArrayList<HospCamas>();
            for (HospCamas hospCamasListHospCamasToAttach : hospHistoriac.getHospCamasList()) {
                hospCamasListHospCamasToAttach = em.getReference(hospCamasListHospCamasToAttach.getClass(), hospCamasListHospCamasToAttach.getId());
                attachedHospCamasList.add(hospCamasListHospCamasToAttach);
            }
            hospHistoriac.setHospCamasList(attachedHospCamasList);
            em.persist(hospHistoriac);
            if (idInfoAdmision != null) {
                idInfoAdmision.getHospHistoriacList().add(hospHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            if (hospHcExpfisica != null) {
                HospHistoriac oldIdHosphistoriacOfHospHcExpfisica = hospHcExpfisica.getIdHosphistoriac();
                if (oldIdHosphistoriacOfHospHcExpfisica != null) {
                    oldIdHosphistoriacOfHospHcExpfisica.setHospHcExpfisica(null);
                    oldIdHosphistoriacOfHospHcExpfisica = em.merge(oldIdHosphistoriacOfHospHcExpfisica);
                }
                hospHcExpfisica.setIdHosphistoriac(hospHistoriac);
                hospHcExpfisica = em.merge(hospHcExpfisica);
            }
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getHospHistoriac().add(hospHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            for (HospPruebasComplement hospPruebasComplementsHospPruebasComplement : hospHistoriac.getHospPruebasComplements()) {
                HospHistoriac oldIdHosphistoriacOfHospPruebasComplementsHospPruebasComplement = hospPruebasComplementsHospPruebasComplement.getIdHosphistoriac();
                hospPruebasComplementsHospPruebasComplement.setIdHosphistoriac(hospHistoriac);
                hospPruebasComplementsHospPruebasComplement = em.merge(hospPruebasComplementsHospPruebasComplement);
                if (oldIdHosphistoriacOfHospPruebasComplementsHospPruebasComplement != null) {
                    oldIdHosphistoriacOfHospPruebasComplementsHospPruebasComplement.getHospPruebasComplements().remove(hospPruebasComplementsHospPruebasComplement);
                    oldIdHosphistoriacOfHospPruebasComplementsHospPruebasComplement = em.merge(oldIdHosphistoriacOfHospPruebasComplementsHospPruebasComplement);
                }
            }
            for (HospCamas hospCamasListHospCamas : hospHistoriac.getHospCamasList()) {
                HospHistoriac oldIdHospHistoriacOfHospCamasListHospCamas = hospCamasListHospCamas.getIdHospHistoriac();
                hospCamasListHospCamas.setIdHospHistoriac(hospHistoriac);
                hospCamasListHospCamas = em.merge(hospCamasListHospCamas);
                if (oldIdHospHistoriacOfHospCamasListHospCamas != null) {
                    oldIdHospHistoriacOfHospCamasListHospCamas.getHospCamasList().remove(hospCamasListHospCamas);
                    oldIdHospHistoriacOfHospCamasListHospCamas = em.merge(oldIdHospHistoriacOfHospCamasListHospCamas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HospHistoriac hospHistoriac) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HospHistoriac persistentHospHistoriac = em.find(HospHistoriac.class, hospHistoriac.getId());
            InfoAdmision idInfoAdmisionOld = persistentHospHistoriac.getIdInfoAdmision();
            InfoAdmision idInfoAdmisionNew = hospHistoriac.getIdInfoAdmision();
            HospHcExpfisica hospHcExpfisicaOld = persistentHospHistoriac.getHospHcExpfisica();
            HospHcExpfisica hospHcExpfisicaNew = hospHistoriac.getHospHcExpfisica();
            Configdecripcionlogin idConfigdecripcionloginOld = persistentHospHistoriac.getIdConfigdecripcionlogin();
            Configdecripcionlogin idConfigdecripcionloginNew = hospHistoriac.getIdConfigdecripcionlogin();
            List<HospPruebasComplement> hospPruebasComplementsOld = persistentHospHistoriac.getHospPruebasComplements();
            List<HospPruebasComplement> hospPruebasComplementsNew = hospHistoriac.getHospPruebasComplements();
            List<HospCamas> hospCamasListOld = persistentHospHistoriac.getHospCamasList();
            List<HospCamas> hospCamasListNew = hospHistoriac.getHospCamasList();
            List<String> illegalOrphanMessages = null;
            for (HospPruebasComplement hospPruebasComplementsOldHospPruebasComplement : hospPruebasComplementsOld) {
                if (!hospPruebasComplementsNew.contains(hospPruebasComplementsOldHospPruebasComplement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospPruebasComplement " + hospPruebasComplementsOldHospPruebasComplement + " since its idHosphistoriac field is not nullable.");
                }
            }
            for (HospCamas hospCamasListOldHospCamas : hospCamasListOld) {
                if (!hospCamasListNew.contains(hospCamasListOldHospCamas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HospCamas " + hospCamasListOldHospCamas + " since its idHospHistoriac field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idInfoAdmisionNew != null) {
                idInfoAdmisionNew = em.getReference(idInfoAdmisionNew.getClass(), idInfoAdmisionNew.getId());
                hospHistoriac.setIdInfoAdmision(idInfoAdmisionNew);
            }
            if (hospHcExpfisicaNew != null) {
                hospHcExpfisicaNew = em.getReference(hospHcExpfisicaNew.getClass(), hospHcExpfisicaNew.getId());
                hospHistoriac.setHospHcExpfisica(hospHcExpfisicaNew);
            }
            if (idConfigdecripcionloginNew != null) {
                idConfigdecripcionloginNew = em.getReference(idConfigdecripcionloginNew.getClass(), idConfigdecripcionloginNew.getId());
                hospHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionloginNew);
            }
            List<HospPruebasComplement> attachedHospPruebasComplementsNew = new ArrayList<HospPruebasComplement>();
            for (HospPruebasComplement hospPruebasComplementsNewHospPruebasComplementToAttach : hospPruebasComplementsNew) {
                hospPruebasComplementsNewHospPruebasComplementToAttach = em.getReference(hospPruebasComplementsNewHospPruebasComplementToAttach.getClass(), hospPruebasComplementsNewHospPruebasComplementToAttach.getId());
                attachedHospPruebasComplementsNew.add(hospPruebasComplementsNewHospPruebasComplementToAttach);
            }
            hospPruebasComplementsNew = attachedHospPruebasComplementsNew;
            hospHistoriac.setHospPruebasComplements(hospPruebasComplementsNew);
            List<HospCamas> attachedHospCamasListNew = new ArrayList<HospCamas>();
            for (HospCamas hospCamasListNewHospCamasToAttach : hospCamasListNew) {
                hospCamasListNewHospCamasToAttach = em.getReference(hospCamasListNewHospCamasToAttach.getClass(), hospCamasListNewHospCamasToAttach.getId());
                attachedHospCamasListNew.add(hospCamasListNewHospCamasToAttach);
            }
            hospCamasListNew = attachedHospCamasListNew;
            hospHistoriac.setHospCamasList(hospCamasListNew);
            hospHistoriac = em.merge(hospHistoriac);
            if (idInfoAdmisionOld != null && !idInfoAdmisionOld.equals(idInfoAdmisionNew)) {
                idInfoAdmisionOld.getHospHistoriacList().remove(hospHistoriac);
                idInfoAdmisionOld = em.merge(idInfoAdmisionOld);
            }
            if (idInfoAdmisionNew != null && !idInfoAdmisionNew.equals(idInfoAdmisionOld)) {
                idInfoAdmisionNew.getHospHistoriacList().add(hospHistoriac);
                idInfoAdmisionNew = em.merge(idInfoAdmisionNew);
            }
            if (hospHcExpfisicaOld != null && !hospHcExpfisicaOld.equals(hospHcExpfisicaNew)) {
                hospHcExpfisicaOld.setIdHosphistoriac(null);
                hospHcExpfisicaOld = em.merge(hospHcExpfisicaOld);
            }
            if (hospHcExpfisicaNew != null && !hospHcExpfisicaNew.equals(hospHcExpfisicaOld)) {
                HospHistoriac oldIdHosphistoriacOfHospHcExpfisica = hospHcExpfisicaNew.getIdHosphistoriac();
                if (oldIdHosphistoriacOfHospHcExpfisica != null) {
                    oldIdHosphistoriacOfHospHcExpfisica.setHospHcExpfisica(null);
                    oldIdHosphistoriacOfHospHcExpfisica = em.merge(oldIdHosphistoriacOfHospHcExpfisica);
                }
                hospHcExpfisicaNew.setIdHosphistoriac(hospHistoriac);
                hospHcExpfisicaNew = em.merge(hospHcExpfisicaNew);
            }
            if (idConfigdecripcionloginOld != null && !idConfigdecripcionloginOld.equals(idConfigdecripcionloginNew)) {
                idConfigdecripcionloginOld.getHospHistoriac().remove(hospHistoriac);
                idConfigdecripcionloginOld = em.merge(idConfigdecripcionloginOld);
            }
            if (idConfigdecripcionloginNew != null && !idConfigdecripcionloginNew.equals(idConfigdecripcionloginOld)) {
                idConfigdecripcionloginNew.getHospHistoriac().add(hospHistoriac);
                idConfigdecripcionloginNew = em.merge(idConfigdecripcionloginNew);
            }
            for (HospPruebasComplement hospPruebasComplementsNewHospPruebasComplement : hospPruebasComplementsNew) {
                if (!hospPruebasComplementsOld.contains(hospPruebasComplementsNewHospPruebasComplement)) {
                    HospHistoriac oldIdHosphistoriacOfHospPruebasComplementsNewHospPruebasComplement = hospPruebasComplementsNewHospPruebasComplement.getIdHosphistoriac();
                    hospPruebasComplementsNewHospPruebasComplement.setIdHosphistoriac(hospHistoriac);
                    hospPruebasComplementsNewHospPruebasComplement = em.merge(hospPruebasComplementsNewHospPruebasComplement);
                    if (oldIdHosphistoriacOfHospPruebasComplementsNewHospPruebasComplement != null && !oldIdHosphistoriacOfHospPruebasComplementsNewHospPruebasComplement.equals(hospHistoriac)) {
                        oldIdHosphistoriacOfHospPruebasComplementsNewHospPruebasComplement.getHospPruebasComplements().remove(hospPruebasComplementsNewHospPruebasComplement);
                        oldIdHosphistoriacOfHospPruebasComplementsNewHospPruebasComplement = em.merge(oldIdHosphistoriacOfHospPruebasComplementsNewHospPruebasComplement);
                    }
                }
            }
            for (HospCamas hospCamasListNewHospCamas : hospCamasListNew) {
                if (!hospCamasListOld.contains(hospCamasListNewHospCamas)) {
                    HospHistoriac oldIdHospHistoriacOfHospCamasListNewHospCamas = hospCamasListNewHospCamas.getIdHospHistoriac();
                    hospCamasListNewHospCamas.setIdHospHistoriac(hospHistoriac);
                    hospCamasListNewHospCamas = em.merge(hospCamasListNewHospCamas);
                    if (oldIdHospHistoriacOfHospCamasListNewHospCamas != null && !oldIdHospHistoriacOfHospCamasListNewHospCamas.equals(hospHistoriac)) {
                        oldIdHospHistoriacOfHospCamasListNewHospCamas.getHospCamasList().remove(hospCamasListNewHospCamas);
                        oldIdHospHistoriacOfHospCamasListNewHospCamas = em.merge(oldIdHospHistoriacOfHospCamasListNewHospCamas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hospHistoriac.getId();
                if (findHospHistoriac(id) == null) {
                    throw new NonexistentEntityException("The hospHistoriac with id " + id + " no longer exists.");
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
            HospHistoriac hospHistoriac;
            try {
                hospHistoriac = em.getReference(HospHistoriac.class, id);
                hospHistoriac.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospHistoriac with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HospPruebasComplement> hospPruebasComplementsOrphanCheck = hospHistoriac.getHospPruebasComplements();
            for (HospPruebasComplement hospPruebasComplementsOrphanCheckHospPruebasComplement : hospPruebasComplementsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospHistoriac (" + hospHistoriac + ") cannot be destroyed since the HospPruebasComplement " + hospPruebasComplementsOrphanCheckHospPruebasComplement + " in its hospPruebasComplements field has a non-nullable idHosphistoriac field.");
            }
            List<HospCamas> hospCamasListOrphanCheck = hospHistoriac.getHospCamasList();
            for (HospCamas hospCamasListOrphanCheckHospCamas : hospCamasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HospHistoriac (" + hospHistoriac + ") cannot be destroyed since the HospCamas " + hospCamasListOrphanCheckHospCamas + " in its hospCamasList field has a non-nullable idHospHistoriac field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InfoAdmision idInfoAdmision = hospHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision.getHospHistoriacList().remove(hospHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            HospHcExpfisica hospHcExpfisica = hospHistoriac.getHospHcExpfisica();
            if (hospHcExpfisica != null) {
                hospHcExpfisica.setIdHosphistoriac(null);
                hospHcExpfisica = em.merge(hospHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = hospHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getHospHistoriac().remove(hospHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            em.remove(hospHistoriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HospHistoriac> findHospHistoriacEntities() {
        return findHospHistoriacEntities(true, -1, -1);
    }

    public List<HospHistoriac> findHospHistoriacEntities(int maxResults, int firstResult) {
        return findHospHistoriacEntities(false, maxResults, firstResult);
    }

    private List<HospHistoriac> findHospHistoriacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HospHistoriac.class));
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

    public HospHistoriac findHospHistoriac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HospHistoriac.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospHistoriacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HospHistoriac> rt = cq.from(HospHistoriac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codifo no Auto-Generado
    public List<HospHistoriac> findhospHistoriacs(int estado) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM HospHistoriac i WHERE i.estado = :estado")
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public HospHistoriac findhospHistoriac(String numDoc, int estado) {
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM HospHistoriac i WHERE i.estado <= :estado AND i.idInfoAdmision.idDatosPersonales.numDoc = :doc")
                    .setParameter("estado", estado)
                    .setParameter("doc", numDoc)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
            if (results.isEmpty()) {
                return null;
            } else if (results.size() == 1) {
                return (HospHistoriac) results.get(0);
            } else {
                JOptionPane.showMessageDialog(null, "Informe al administrador del sistema de este error:\n"
                        + "Es posible que existan varias HC activas.\n", HospHistoriacJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }

    public List<HospHistoriac> findHistoriac_() {
        EntityManager em = getEntityManager();
        Query Q = null;
        try {
            Q = em.createQuery("SELECT i FROM HospHistoriac i WHERE i.tipoHc='0' AND i.estado='1'");
            Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Q.getResultList();
    }

    public Object getTipoHC(HospHistoriac ihc) {
        Query Q = null;
        EntityManager em = getEntityManager();
        Q = em.createQuery("SELECT h.tipoHc FROM HospHistoriac h WHERE h=:idht");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        Q.setParameter("idht", ihc);
        return Q.getSingleResult();
    }

    public List<HospHistoriac> getHistoriasALL(String ide) {
        EntityManager em = getEntityManager();
        Query Q = null;
        Q = em.createQuery("SELECT h FROM HospHistoriac h WHERE h.idInfoAdmision.idDatosPersonales.numDoc=:document AND h.estado <>'5' AND h.idInfoAdmision.idDatosPersonales.estado='1'");
        Q.setParameter("document", ide);
        return Q.getResultList();
    }
}
