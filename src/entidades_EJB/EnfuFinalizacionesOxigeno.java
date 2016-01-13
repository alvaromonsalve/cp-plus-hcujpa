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
@Table(name = "enfu_finalizaciones_oxigeno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuFinalizacionesOxigeno.findAll", query = "SELECT e FROM EnfuFinalizacionesOxigeno e"),
    @NamedQuery(name = "EnfuFinalizacionesOxigeno.findById", query = "SELECT e FROM EnfuFinalizacionesOxigeno e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuFinalizacionesOxigeno.findByFecha", query = "SELECT e FROM EnfuFinalizacionesOxigeno e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuFinalizacionesOxigeno.findByHoraFin", query = "SELECT e FROM EnfuFinalizacionesOxigeno e WHERE e.horaFin = :horaFin"),
    @NamedQuery(name = "EnfuFinalizacionesOxigeno.findByUsr", query = "SELECT e FROM EnfuFinalizacionesOxigeno e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuFinalizacionesOxigeno.findByEstado", query = "SELECT e FROM EnfuFinalizacionesOxigeno e WHERE e.estado = :estado")})
public class EnfuFinalizacionesOxigeno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_fin")
    private String horaFin;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_aplicacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuAplicacionesOxigeno idAplicacion;

    public EnfuFinalizacionesOxigeno() {
    }

    public EnfuFinalizacionesOxigeno(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
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

    public EnfuAplicacionesOxigeno getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(EnfuAplicacionesOxigeno idAplicacion) {
        this.idAplicacion = idAplicacion;
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
        if (!(object instanceof EnfuFinalizacionesOxigeno)) {
            return false;
        }
        EnfuFinalizacionesOxigeno other = (EnfuFinalizacionesOxigeno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.EnfuFinalizacionesOxigeno[ id=" + id + " ]";
    }
    
}
