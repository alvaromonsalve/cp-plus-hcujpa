/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "hcu_mezclas_medicamentos_desc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findAll", query = "SELECT h FROM HcuMezclasMedicamentosDesc h"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findById", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.id = :id"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByIdHcuMezclasMedicamentos", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.idHcuMezclasMedicamentos = :idHcuMezclasMedicamentos"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByIdSuministro", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.idSuministro = :idSuministro"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByDosisN", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.dosisN = :dosisN"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByDosisU", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.dosisU = :dosisU"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findBySolDiluir", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.solDiluir = :solDiluir"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByEstado", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.estado = :estado"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByUsuario", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HcuMezclasMedicamentosDesc.findByFDigita", query = "SELECT h FROM HcuMezclasMedicamentosDesc h WHERE h.fDigita = :fDigita")})
public class HcuMezclasMedicamentosDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_mezclas_medicamentos",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private HcuMezclasMedicamentos idHcuMezclasMedicamentos;    
    @JoinColumn(name = "id_suministro",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private SumSuministro idSuministro;
    @Basic(optional = false)
    @Column(name = "dosis_n")
    private float dosisN;
    @Basic(optional = false)
    @Column(name = "dosis_u")
    private String dosisU;
    @Basic(optional = false)
    @Column(name = "sol_diluir")
    private boolean solDiluir;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;

    public HcuMezclasMedicamentosDesc() {
    }

    public HcuMezclasMedicamentosDesc(Integer id) {
        this.id = id;
    }

    public HcuMezclasMedicamentosDesc(Integer id, HcuMezclasMedicamentos idHcuMezclasMedicamentos, SumSuministro idSuministro, float dosisN, String dosisU, boolean solDiluir, int usuario, Date fDigita) {
        this.id = id;
        this.idHcuMezclasMedicamentos = idHcuMezclasMedicamentos;
        this.idSuministro = idSuministro;
        this.dosisN = dosisN;
        this.dosisU = dosisU;
        this.solDiluir = solDiluir;
        this.usuario = usuario;
        this.fDigita = fDigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HcuMezclasMedicamentos getIdHcuMezclasMedicamentos() {
        return idHcuMezclasMedicamentos;
    }

    public void setIdHcuMezclasMedicamentos(HcuMezclasMedicamentos idHcuMezclasMedicamentos) {
        this.idHcuMezclasMedicamentos = idHcuMezclasMedicamentos;
    }

    public SumSuministro getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
    }

    public float getDosisN() {
        return dosisN;
    }

    public void setDosisN(float dosisN) {
        this.dosisN = dosisN;
    }

    public String getDosisU() {
        return dosisU;
    }

    public void setDosisU(String dosisU) {
        this.dosisU = dosisU;
    }

    public boolean getSolDiluir() {
        return solDiluir;
    }

    public void setSolDiluir(boolean solDiluir) {
        this.solDiluir = solDiluir;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFDigita() {
        return fDigita;
    }

    public void setFDigita(Date fDigita) {
        this.fDigita = fDigita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HcuMezclasMedicamentosDesc)) {
            return false;
        }
        HcuMezclasMedicamentosDesc other = (HcuMezclasMedicamentosDesc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuMezclasMedicamentosDesc[ id=" + id + " ]";
    }
    
}
