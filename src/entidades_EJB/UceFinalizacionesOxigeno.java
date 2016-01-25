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
 * @author JUDMEZ
 */
@Entity
@Table(name = "uce_finalizaciones_oxigeno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospFinalizacionesOxigeno.findAll", query = "SELECT h FROM HospFinalizacionesOxigeno h"),
    @NamedQuery(name = "HospFinalizacionesOxigeno.findById", query = "SELECT h FROM HospFinalizacionesOxigeno h WHERE h.id = :id"),
    @NamedQuery(name = "HospFinalizacionesOxigeno.findByFecha", query = "SELECT h FROM HospFinalizacionesOxigeno h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HospFinalizacionesOxigeno.findByHoraFin", query = "SELECT h FROM HospFinalizacionesOxigeno h WHERE h.horaFin = :horaFin"),
    @NamedQuery(name = "HospFinalizacionesOxigeno.findByUsr", query = "SELECT h FROM HospFinalizacionesOxigeno h WHERE h.usr = :usr"),
    @NamedQuery(name = "HospFinalizacionesOxigeno.findByEstado", query = "SELECT h FROM HospFinalizacionesOxigeno h WHERE h.estado = :estado")})
public class UceFinalizacionesOxigeno implements Serializable {
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
    private UceAplicacionesOxigeno idAplicacion;

    public UceFinalizacionesOxigeno() {
    }

    public UceFinalizacionesOxigeno(Integer id) {
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

    public UceAplicacionesOxigeno getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(UceAplicacionesOxigeno idAplicacion) {
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
        if (!(object instanceof UceFinalizacionesOxigeno)) {
            return false;
        }
        UceFinalizacionesOxigeno other = (UceFinalizacionesOxigeno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospFinalizacionesOxigeno[ id=" + id + " ]";
    }
    
}
