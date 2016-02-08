/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_ctc_medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeCtcMedicamento.findAll", query = "SELECT c FROM CeCtcMedicamento c"),
    @NamedQuery(name = "CeCtcMedicamento.findById", query = "SELECT c FROM CeCtcMedicamento c WHERE c.id = :id"),
    @NamedQuery(name = "CeCtcMedicamento.findByDosisN", query = "SELECT c FROM CeCtcMedicamento c WHERE c.dosisN = :dosisN"),
    @NamedQuery(name = "CeCtcMedicamento.findByVia", query = "SELECT c FROM CeCtcMedicamento c WHERE c.via = :via"),
    @NamedQuery(name = "CeCtcMedicamento.findByDosisU", query = "SELECT c FROM CeCtcMedicamento c WHERE c.dosisU = :dosisU"),
    @NamedQuery(name = "CeCtcMedicamento.findByTipo", query = "SELECT c FROM CeCtcMedicamento c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CeCtcMedicamento.findByCodcum", query = "SELECT c FROM CeCtcMedicamento c WHERE c.codcum = :codcum")})
public class CeCtcMedicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis_n")
    private Float dosisN;
    @Column(name = "via")
    private String via;
    @Column(name = "dosis_u")
    private String dosisU;
    @Lob
    @Column(name = "administracion")
    private String administracion;
    @Lob
    @Column(name = "duraciont")
    private String duraciont;
    @Lob
    @Column(name = "cantidadt")
    private String cantidadt;
    @Lob
    @Column(name = "dosisdiaria")
    private String dosisdiaria;
    @Basic(optional = false)
    @Column(name = "tipo")
    private short tipo;
    @Basic(optional = false)
    @Column(name = "codcum")
    private String codcum;
    @JoinColumn(name = "idctc", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeCtc idctc;
    @JoinColumn(name = "idsuministro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idsuministro;

    public CeCtcMedicamento() {
    }

    public CeCtcMedicamento(Integer id) {
        this.id = id;
    }

    public CeCtcMedicamento(Integer id, short tipo, String codcum) {
        this.id = id;
        this.tipo = tipo;
        this.codcum = codcum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getDosisN() {
        return dosisN;
    }

    public void setDosisN(Float dosisN) {
        this.dosisN = dosisN;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDosisU() {
        return dosisU;
    }

    public void setDosisU(String dosisU) {
        this.dosisU = dosisU;
    }

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }

    public String getDuraciont() {
        return duraciont;
    }

    public void setDuraciont(String duraciont) {
        this.duraciont = duraciont;
    }

    public String getCantidadt() {
        return cantidadt;
    }

    public void setCantidadt(String cantidadt) {
        this.cantidadt = cantidadt;
    }

    public String getDosisdiaria() {
        return dosisdiaria;
    }

    public void setDosisdiaria(String dosisdiaria) {
        this.dosisdiaria = dosisdiaria;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getCodcum() {
        return codcum;
    }

    public void setCodcum(String codcum) {
        this.codcum = codcum;
    }

    public CeCtc getIdctc() {
        return idctc;
    }

    public SumSuministro getIdsuministro() {
        return idsuministro;
    }

    public void setIdsuministro(SumSuministro idsuministro) {
        this.idsuministro = idsuministro;
    }

    
    public void setIdctc(CeCtc idctc) {
        this.idctc = idctc;
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
        if (!(object instanceof CeCtcMedicamento)) {
            return false;
        }
        CeCtcMedicamento other = (CeCtcMedicamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeCtcMedicamento[ id=" + id + " ]";
    }
    
}
