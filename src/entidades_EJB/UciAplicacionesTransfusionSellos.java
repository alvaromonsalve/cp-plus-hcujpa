/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "uci_aplicaciones_transfusion_sellos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciAplicacionesTransfusionSellos.findAll", query = "SELECT u FROM UciAplicacionesTransfusionSellos u"),
    @NamedQuery(name = "UciAplicacionesTransfusionSellos.findById", query = "SELECT u FROM UciAplicacionesTransfusionSellos u WHERE u.id = :id"),
    @NamedQuery(name = "UciAplicacionesTransfusionSellos.findBySello", query = "SELECT u FROM UciAplicacionesTransfusionSellos u WHERE u.sello = :sello"),
    @NamedQuery(name = "UciAplicacionesTransfusionSellos.findByUsr", query = "SELECT u FROM UciAplicacionesTransfusionSellos u WHERE u.usr = :usr"),
    @NamedQuery(name = "UciAplicacionesTransfusionSellos.findByEstado", query = "SELECT u FROM UciAplicacionesTransfusionSellos u WHERE u.estado = :estado"),
    @NamedQuery(name = "UciAplicacionesTransfusionSellos.findByFechaIngresoDatos", query = "SELECT u FROM UciAplicacionesTransfusionSellos u WHERE u.fechaIngresoDatos = :fechaIngresoDatos")})
public class UciAplicacionesTransfusionSellos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sello")
    private String sello;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_aplicacion_transfusion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciAplicacionesTransfusion idAplicacionTransfusion;

    public UciAplicacionesTransfusionSellos() {
    }

    public UciAplicacionesTransfusionSellos(Integer id) {
        this.id = id;
    }

    public UciAplicacionesTransfusionSellos(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public UciAplicacionesTransfusion getIdAplicacionTransfusion() {
        return idAplicacionTransfusion;
    }

    public void setIdAplicacionTransfusion(UciAplicacionesTransfusion idAplicacionTransfusion) {
        this.idAplicacionTransfusion = idAplicacionTransfusion;
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
        if (!(object instanceof UciAplicacionesTransfusionSellos)) {
            return false;
        }
        UciAplicacionesTransfusionSellos other = (UciAplicacionesTransfusionSellos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UciAplicacionesTransfusionSellos[ id=" + id + " ]";
    }
    
}
