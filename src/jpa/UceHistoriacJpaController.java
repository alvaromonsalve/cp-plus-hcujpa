
package jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.InfoAdmision;
import entidades.UceHcExpfisica;
import entidades.Configdecripcionlogin;
import entidades.UcePruebasComplement;
import java.util.ArrayList;
import java.util.List;
import entidades.UceCamas;
import entidades.UceHistoriac;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Alvaro Monsalve
 */
public class UceHistoriacJpaController implements Serializable {

    public UceHistoriacJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UceHistoriac uceHistoriac) {
        if (uceHistoriac.getUcePruebasComplements() == null) {
            uceHistoriac.setUcePruebasComplements(new ArrayList<UcePruebasComplement>());
        }
        if (uceHistoriac.getUceCamasList() == null) {
            uceHistoriac.setUceCamasList(new ArrayList<UceCamas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InfoAdmision idInfoAdmision = uceHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision = em.getReference(idInfoAdmision.getClass(), idInfoAdmision.getId());
                uceHistoriac.setIdInfoAdmision(idInfoAdmision);
            }
            UceHcExpfisica uciHcExpfisica = uceHistoriac.getUceHcExpfisica();
            if (uciHcExpfisica != null) {
                uciHcExpfisica = em.getReference(uciHcExpfisica.getClass(), uciHcExpfisica.getId());
                uceHistoriac.setUceHcExpfisica(uciHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = uceHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin = em.getReference(idConfigdecripcionlogin.getClass(), idConfigdecripcionlogin.getId());
                uceHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionlogin);
            }
            List<UcePruebasComplement> attachedUcePruebasComplements = new ArrayList<UcePruebasComplement>();
            for (UcePruebasComplement uciPruebasComplementsUcePruebasComplementToAttach : uceHistoriac.getUcePruebasComplements()) {
                uciPruebasComplementsUcePruebasComplementToAttach = em.getReference(uciPruebasComplementsUcePruebasComplementToAttach.getClass(), uciPruebasComplementsUcePruebasComplementToAttach.getId());
                attachedUcePruebasComplements.add(uciPruebasComplementsUcePruebasComplementToAttach);
            }
            uceHistoriac.setUcePruebasComplements(attachedUcePruebasComplements);
            List<UceCamas> attachedUceCamasList = new ArrayList<UceCamas>();
            for (UceCamas uciCamasListuciCamasToAttach : uceHistoriac.getUceCamasList()) {
                uciCamasListuciCamasToAttach = em.getReference(uciCamasListuciCamasToAttach.getClass(), uciCamasListuciCamasToAttach.getId());
                attachedUceCamasList.add(uciCamasListuciCamasToAttach);
            }
            uceHistoriac.setUceCamasList(attachedUceCamasList);
            em.persist(uceHistoriac);
            if (idInfoAdmision != null) {
                idInfoAdmision.getUceHistoriacList().add(uceHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            if (uciHcExpfisica != null) {
                UceHistoriac oldIdUcehistoriacOfUceHcExpfisica = uciHcExpfisica.getIdUcehistoriac();
                if (oldIdUcehistoriacOfUceHcExpfisica != null) {
                    oldIdUcehistoriacOfUceHcExpfisica.setUceHcExpfisica(null);
                    oldIdUcehistoriacOfUceHcExpfisica = em.merge(oldIdUcehistoriacOfUceHcExpfisica);
                }
                uciHcExpfisica.setIdUcehistoriac(uceHistoriac);
                uciHcExpfisica = em.merge(uciHcExpfisica);
            }
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getUceHistoriac().add(uceHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            for (UcePruebasComplement ucePruebasComplementsUcePruebasComplement : uceHistoriac.getUcePruebasComplements()) {
                UceHistoriac oldIdUcehistoriacOfUcePruebasComplementsUcePruebasComplement = ucePruebasComplementsUcePruebasComplement.getIdUcehistoriac();
                ucePruebasComplementsUcePruebasComplement.setIdHosphistoriac(uceHistoriac);
                ucePruebasComplementsUcePruebasComplement = em.merge(ucePruebasComplementsUcePruebasComplement);
                if (oldIdUcehistoriacOfUcePruebasComplementsUcePruebasComplement != null) {
                    oldIdUcehistoriacOfUcePruebasComplementsUcePruebasComplement.getUcePruebasComplements().remove(ucePruebasComplementsUcePruebasComplement);
                    oldIdUcehistoriacOfUcePruebasComplementsUcePruebasComplement = em.merge(oldIdUcehistoriacOfUcePruebasComplementsUcePruebasComplement);
                }
            }
            for (UceCamas uceCamasListUceCamas : uceHistoriac.getUceCamasList()) {
                UceHistoriac oldIdUceHistoriacOfUceCamasListUceCamas = uceCamasListUceCamas.getIdUceHistoriac();
                uceCamasListUceCamas.setIdUceHistoriac(uceHistoriac);
                uceCamasListUceCamas = em.merge(uceCamasListUceCamas);
                if (oldIdUceHistoriacOfUceCamasListUceCamas != null) {
                    oldIdUceHistoriacOfUceCamasListUceCamas.getUceCamasList().remove(uceCamasListUceCamas);
                    oldIdUceHistoriacOfUceCamasListUceCamas = em.merge(oldIdUceHistoriacOfUceCamasListUceCamas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UceHistoriac uceHistoriac) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UceHistoriac persistentUceHistoriac = em.find(UceHistoriac.class, uceHistoriac.getId());
            InfoAdmision idInfoAdmisionOld = persistentUceHistoriac.getIdInfoAdmision();
            InfoAdmision idInfoAdmisionNew = uceHistoriac.getIdInfoAdmision();
            UceHcExpfisica uceHcExpfisicaOld = persistentUceHistoriac.getUceHcExpfisica();
            UceHcExpfisica uceHcExpfisicaNew = uceHistoriac.getUceHcExpfisica();
            Configdecripcionlogin idConfigdecripcionloginOld = persistentUceHistoriac.getIdConfigdecripcionlogin();
            Configdecripcionlogin idConfigdecripcionloginNew = uceHistoriac.getIdConfigdecripcionlogin();
            List<UcePruebasComplement> ucePruebasComplementsOld = persistentUceHistoriac.getUcePruebasComplements();
            List<UcePruebasComplement> ucePruebasComplementsNew = uceHistoriac.getUcePruebasComplements();
            List<UceCamas> uceCamasListOld = persistentUceHistoriac.getUceCamasList();
            List<UceCamas> uceCamasListNew = uceHistoriac.getUceCamasList();
            List<String> illegalOrphanMessages = null;
            for (UcePruebasComplement ucePruebasComplementsOldUcePruebasComplement : ucePruebasComplementsOld) {
                if (!ucePruebasComplementsNew.contains(ucePruebasComplementsOldUcePruebasComplement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UcePruebasComplement " + ucePruebasComplementsOldUcePruebasComplement + " since its idUcehistoriac field is not nullable.");
                }
            }
            for (UceCamas uceCamasListOldUceCamas : uceCamasListOld) {
                if (!uceCamasListNew.contains(uceCamasListOldUceCamas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UceCamas " + uceCamasListOldUceCamas + " since its idUceHistoriac field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idInfoAdmisionNew != null) {
                idInfoAdmisionNew = em.getReference(idInfoAdmisionNew.getClass(), idInfoAdmisionNew.getId());
                uceHistoriac.setIdInfoAdmision(idInfoAdmisionNew);
            }
            if (uceHcExpfisicaNew != null) {
                uceHcExpfisicaNew = em.getReference(uceHcExpfisicaNew.getClass(), uceHcExpfisicaNew.getId());
                uceHistoriac.setUceHcExpfisica(uceHcExpfisicaNew);
            }
            if (idConfigdecripcionloginNew != null) {
                idConfigdecripcionloginNew = em.getReference(idConfigdecripcionloginNew.getClass(), idConfigdecripcionloginNew.getId());
                uceHistoriac.setIdConfigdecripcionlogin(idConfigdecripcionloginNew);
            }
            List<UcePruebasComplement> attachedUcePruebasComplementsNew = new ArrayList<UcePruebasComplement>();
            for (UcePruebasComplement ucePruebasComplementsNewUcePruebasComplementToAttach : ucePruebasComplementsNew) {
                ucePruebasComplementsNewUcePruebasComplementToAttach = em.getReference(ucePruebasComplementsNewUcePruebasComplementToAttach.getClass(), ucePruebasComplementsNewUcePruebasComplementToAttach.getId());
                attachedUcePruebasComplementsNew.add(ucePruebasComplementsNewUcePruebasComplementToAttach);
            }
            ucePruebasComplementsNew = attachedUcePruebasComplementsNew;
            uceHistoriac.setUcePruebasComplements(ucePruebasComplementsNew);
            List<UceCamas> attachedUceCamasListNew = new ArrayList<UceCamas>();
            for (UceCamas uceCamasListNewUceCamasToAttach : uceCamasListNew) {
                uceCamasListNewUceCamasToAttach = em.getReference(uceCamasListNewUceCamasToAttach.getClass(), uceCamasListNewUceCamasToAttach.getId());
                attachedUceCamasListNew.add(uceCamasListNewUceCamasToAttach);
            }
            uceCamasListNew = attachedUceCamasListNew;
            uceHistoriac.setUceCamasList(uceCamasListNew);
            uceHistoriac = em.merge(uceHistoriac);
            if (idInfoAdmisionOld != null && !idInfoAdmisionOld.equals(idInfoAdmisionNew)) {
                idInfoAdmisionOld.getUceHistoriacList().remove(uceHistoriac);
                idInfoAdmisionOld = em.merge(idInfoAdmisionOld);
            }
            if (idInfoAdmisionNew != null && !idInfoAdmisionNew.equals(idInfoAdmisionOld)) {
                idInfoAdmisionNew.getUceHistoriacList().add(uceHistoriac);
                idInfoAdmisionNew = em.merge(idInfoAdmisionNew);
            }
            if (uceHcExpfisicaOld != null && !uceHcExpfisicaOld.equals(uceHcExpfisicaNew)) {
                uceHcExpfisicaOld.setIdUcehistoriac(null);
                uceHcExpfisicaOld = em.merge(uceHcExpfisicaOld);
            }
            if (uceHcExpfisicaNew != null && !uceHcExpfisicaNew.equals(uceHcExpfisicaOld)) {
                UceHistoriac oldIdUcehistoriacOfUceHcExpfisica = uceHcExpfisicaNew.getIdUcehistoriac();
                if (oldIdUcehistoriacOfUceHcExpfisica != null) {
                    oldIdUcehistoriacOfUceHcExpfisica.setUceHcExpfisica(null);
                    oldIdUcehistoriacOfUceHcExpfisica = em.merge(oldIdUcehistoriacOfUceHcExpfisica);
                }
                uceHcExpfisicaNew.setIdUcehistoriac(uceHistoriac);
                uceHcExpfisicaNew = em.merge(uceHcExpfisicaNew);
            }
            if (idConfigdecripcionloginOld != null && !idConfigdecripcionloginOld.equals(idConfigdecripcionloginNew)) {
                idConfigdecripcionloginOld.getUceHistoriac().remove(uceHistoriac);
                idConfigdecripcionloginOld = em.merge(idConfigdecripcionloginOld);
            }
            if (idConfigdecripcionloginNew != null && !idConfigdecripcionloginNew.equals(idConfigdecripcionloginOld)) {
                idConfigdecripcionloginNew.getUceHistoriac().add(uceHistoriac);
                idConfigdecripcionloginNew = em.merge(idConfigdecripcionloginNew);
            }
            for (UcePruebasComplement ucePruebasComplementsNewUcePruebasComplement : ucePruebasComplementsNew) {
                if (!ucePruebasComplementsOld.contains(ucePruebasComplementsNewUcePruebasComplement)) {
                    UceHistoriac oldIdUcehistoriacOfUcePruebasComplementsNewUcePruebasComplement = ucePruebasComplementsNewUcePruebasComplement.getIdUcehistoriac();
                    ucePruebasComplementsNewUcePruebasComplement.setIdHosphistoriac(uceHistoriac);
                    ucePruebasComplementsNewUcePruebasComplement = em.merge(ucePruebasComplementsNewUcePruebasComplement);
                    if (oldIdUcehistoriacOfUcePruebasComplementsNewUcePruebasComplement != null && !oldIdUcehistoriacOfUcePruebasComplementsNewUcePruebasComplement.equals(uceHistoriac)) {
                        oldIdUcehistoriacOfUcePruebasComplementsNewUcePruebasComplement.getUcePruebasComplements().remove(ucePruebasComplementsNewUcePruebasComplement);
                        oldIdUcehistoriacOfUcePruebasComplementsNewUcePruebasComplement = em.merge(oldIdUcehistoriacOfUcePruebasComplementsNewUcePruebasComplement);
                    }
                }
            }
            for (UceCamas uceCamasListNewUceCamas : uceCamasListNew) {
                if (!uceCamasListOld.contains(uceCamasListNewUceCamas)) {
                    UceHistoriac oldIdUceHistoriacOfUceCamasListNewUceCamas = uceCamasListNewUceCamas.getIdUceHistoriac();
                    uceCamasListNewUceCamas.setIdUceHistoriac(uceHistoriac);
                    uceCamasListNewUceCamas = em.merge(uceCamasListNewUceCamas);
                    if (oldIdUceHistoriacOfUceCamasListNewUceCamas != null && !oldIdUceHistoriacOfUceCamasListNewUceCamas.equals(uceHistoriac)) {
                        oldIdUceHistoriacOfUceCamasListNewUceCamas.getUceCamasList().remove(uceCamasListNewUceCamas);
                        oldIdUceHistoriacOfUceCamasListNewUceCamas = em.merge(oldIdUceHistoriacOfUceCamasListNewUceCamas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uceHistoriac.getId();
                if (findUceHistoriac(id) == null) {
                    throw new NonexistentEntityException("The uceHistoriac with id " + id + " no longer exists.");
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
            UceHistoriac uceHistoriac;
            try {
                uceHistoriac = em.getReference(UceHistoriac.class, id);
                uceHistoriac.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uceHistoriac with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UcePruebasComplement> ucePruebasComplementsOrphanCheck = uceHistoriac.getUcePruebasComplements();
            for (UcePruebasComplement ucePruebasComplementsOrphanCheckUcePruebasComplement : ucePruebasComplementsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceHistoriac (" + uceHistoriac + ") cannot be destroyed since the UcePruebasComplement " + ucePruebasComplementsOrphanCheckUcePruebasComplement + " in its ucePruebasComplements field has a non-nullable idUcehistoriac field.");
            }
            List<UceCamas> uceCamasListOrphanCheck = uceHistoriac.getUceCamasList();
            for (UceCamas uceCamasListOrphanCheckUceCamas : uceCamasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UceHistoriac (" + uceHistoriac + ") cannot be destroyed since the UceCamas " + uceCamasListOrphanCheckUceCamas + " in its uceCamasList field has a non-nullable idUceHistoriac field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            InfoAdmision idInfoAdmision = uceHistoriac.getIdInfoAdmision();
            if (idInfoAdmision != null) {
                idInfoAdmision.getUceHistoriacList().remove(uceHistoriac);
                idInfoAdmision = em.merge(idInfoAdmision);
            }
            UceHcExpfisica ucrHcExpfisica = uceHistoriac.getUceHcExpfisica();
            if (ucrHcExpfisica != null) {
                ucrHcExpfisica.setIdUcehistoriac(null);
                ucrHcExpfisica = em.merge(ucrHcExpfisica);
            }
            Configdecripcionlogin idConfigdecripcionlogin = uceHistoriac.getIdConfigdecripcionlogin();
            if (idConfigdecripcionlogin != null) {
                idConfigdecripcionlogin.getUceHistoriac().remove(uceHistoriac);
                idConfigdecripcionlogin = em.merge(idConfigdecripcionlogin);
            }
            em.remove(uceHistoriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UceHistoriac> findUceHistoriacEntities() {
        return findUceHistoriacEntities(true, -1, -1);
    }

    public List<UceHistoriac> findUceHistoriacEntities(int maxResults, int firstResult) {
        return findUceHistoriacEntities(false, maxResults, firstResult);
    }

    private List<UceHistoriac> findUceHistoriacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UceHistoriac.class));
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

    public UceHistoriac findUceHistoriac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UceHistoriac.class, id);
        } finally {
            em.close();
        }
    }

    public int getUceHistoriacCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UceHistoriac> rt = cq.from(UceHistoriac.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //Codifo no Auto-Generado
    public List<UceHistoriac> finduciHistoriacs(int estado){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT i FROM UceHistoriac i WHERE i.estado = :estado")
                    .setParameter("estado", estado)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public UceHistoriac finduceHistoriac(String numDoc, int estado) {
        EntityManager em = getEntityManager();
        try {
            List results = em.createQuery("SELECT i FROM UceHistoriac i WHERE i.estado <= :estado AND i.idInfoAdmision.idDatosPersonales.numDoc = :doc")
                    .setParameter("estado", estado)
                    .setParameter("doc", numDoc)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();
            if (results.isEmpty()) {
                return null;
            } else if (results.size() == 1) {
                return (UceHistoriac) results.get(0);
            } else {
                JOptionPane.showMessageDialog(null, "Informe al administrador del sistema de este error:\n"
                        + "Es posible que existan varias HC activas.\n", HospHistoriacJpaController.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
        } finally {
            em.close();
        }
    }
}
