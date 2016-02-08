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
@Table(name = "ce_medidas_generales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeMedidasGenerales.findAll", query = "SELECT c FROM CeMedidasGenerales c"),
    @NamedQuery(name = "CeMedidasGenerales.findById", query = "SELECT c FROM CeMedidasGenerales c WHERE c.id = :id"),
    @NamedQuery(name = "CeMedidasGenerales.findByUsr", query = "SELECT c FROM CeMedidasGenerales c WHERE c.usr = :usr"),
    @NamedQuery(name = "CeMedidasGenerales.findByEstado", query = "SELECT c FROM CeMedidasGenerales c WHERE c.estado = :estado"),
    @NamedQuery(name = "CeMedidasGenerales.findByFechaIngresoDatos", query = "SELECT c FROM CeMedidasGenerales c WHERE c.fechaIngresoDatos = :fechaIngresoDatos")})
public class CeMedidasGenerales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "medidageneral")
    private String medidageneral;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_historice", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistorice;
    
    public CeMedidasGenerales() {
    }

    public CeMedidasGenerales(Integer id) {
        this.id = id;
    }

    public CeMedidasGenerales(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedidageneral() {
        return medidageneral;
    }

    public void setMedidageneral(String medidageneral) {
        this.medidageneral = medidageneral;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        public CeHistoriac getIdHistorice() {
        return idHistorice;
    }

    public void setIdHistorice(CeHistoriac idHistorice) {
        this.idHistorice = idHistorice;
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
        if (!(object instanceof CeMedidasGenerales)) {
            return false;
        }
        CeMedidasGenerales other = (CeMedidasGenerales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeMedidasGenerales[ id=" + id + " ]";
    }
    
}
