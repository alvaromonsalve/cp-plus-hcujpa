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
@Table(name = "uci_aplicaciones_transfusion_signos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findAll", query = "SELECT u FROM UciAplicacionesTransfusionSignos u"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findById", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.id = :id"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByMin", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.min = :min"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByTa", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.ta = :ta"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByT", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.t = :t"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByP", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.p = :p"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByR", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.r = :r"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByUsr", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.usr = :usr"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByEstado", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.estado = :estado"),
    @NamedQuery(name = "UciAplicacionesTransfusionSignos.findByFechaIngresoDatos", query = "SELECT u FROM UciAplicacionesTransfusionSignos u WHERE u.fechaIngresoDatos = :fechaIngresoDatos")})
public class UciAplicacionesTransfusionSignos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "min")
    private Integer min;
    @Column(name = "ta")
    private String ta;
    @Column(name = "t")
    private String t;
    @Column(name = "p")
    private String p;
    @Column(name = "r")
    private String r;
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

    public UciAplicacionesTransfusionSignos() {
    }

    public UciAplicacionesTransfusionSignos(Integer id) {
        this.id = id;
    }

    public UciAplicacionesTransfusionSignos(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
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
        if (!(object instanceof UciAplicacionesTransfusionSignos)) {
            return false;
        }
        UciAplicacionesTransfusionSignos other = (UciAplicacionesTransfusionSignos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UciAplicacionesTransfusionSignos[ id=" + id + " ]";
    }
    
}
