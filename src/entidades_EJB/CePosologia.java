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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_posologia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CePosologia.findAll", query = "SELECT c FROM CePosologia c"),
    @NamedQuery(name = "CePosologia.findById", query = "SELECT c FROM CePosologia c WHERE c.id = :id"),
    @NamedQuery(name = "CePosologia.findByDosis", query = "SELECT c FROM CePosologia c WHERE c.dosis = :dosis"),
    @NamedQuery(name = "CePosologia.findByCantidad", query = "SELECT c FROM CePosologia c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "CePosologia.findByDosisU", query = "SELECT c FROM CePosologia c WHERE c.dosisU = :dosisU"),
    @NamedQuery(name = "CePosologia.findByVia", query = "SELECT c FROM CePosologia c WHERE c.via = :via"),
    @NamedQuery(name = "CePosologia.findByUsr", query = "SELECT c FROM CePosologia c WHERE c.usr = :usr"),
    @NamedQuery(name = "CePosologia.findByEstado", query = "SELECT c FROM CePosologia c WHERE c.estado = :estado"),
    @NamedQuery(name = "CePosologia.findByFechaIngresoDatos", query = "SELECT c FROM CePosologia c WHERE c.fechaIngresoDatos = :fechaIngresoDatos")})
public class CePosologia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis")
    private Float dosis;
    @Column(name = "cantidad")
    private Float cantidad;
    @Lob
    @Column(name = "catidad_texto")
    private String catidadTexto;
    @Column(name = "dosis_u")
    private String dosisU;
    @Column(name = "via")
    private String via;
    @Lob
    @Column(name = "administracion")
    private String administracion;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    
    @JoinColumn(name = "id_historiace", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoriace;
    
    @JoinColumn(name = "id_suministro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idSuministro;

    public CePosologia() {
    }

    public CePosologia(Integer id) {
        this.id = id;
    }

    public CePosologia(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getDosis() {
        return dosis;
    }

    public void setDosis(Float dosis) {
        this.dosis = dosis;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getCatidadTexto() {
        return catidadTexto;
    }

    public void setCatidadTexto(String catidadTexto) {
        this.catidadTexto = catidadTexto;
    }

    public String getDosisU() {
        return dosisU;
    }

    public void setDosisU(String dosisU) {
        this.dosisU = dosisU;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }
    
       public SumSuministro getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
    }
    
    public CeHistoriac getIdHistoriace() {
        return idHistoriace;
    }

    public void setIdHistoriace(CeHistoriac idHistoriace) {
        this.idHistoriace = idHistoriace;
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
        if (!(object instanceof CePosologia)) {
            return false;
        }
        CePosologia other = (CePosologia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CePosologia[ id=" + id + " ]";
    }
    
}
