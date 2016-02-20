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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "datos_usuarios")
@NamedQueries({
    @NamedQuery(name = "DatosUsuarios.findAll", query = "SELECT d FROM DatosUsuarios d")})
public class DatosUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "identificacion")
    private String identificacion;
    @Basic(optional = false)
    @Lob
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Lob
    @Column(name = "nombrecompleto")
    private String nombrecompleto;
    @Basic(optional = false)
    @Lob
    @Column(name = "servicio")
    private String servicio;
    @Basic(optional = false)
    @Lob
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Lob
    @Column(name = "fechainhabilitacion")
    private String fechainhabilitacion;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;

    public DatosUsuarios() {
    }

    public DatosUsuarios(Integer id) {
        this.id = id;
    }

    public DatosUsuarios(Integer id, String identificacion, String usuario, String nombrecompleto, String servicio, String cargo, Date fechacreacion, int estado) {
        this.id = id;
        this.identificacion = identificacion;
        this.usuario = usuario;
        this.nombrecompleto = nombrecompleto;
        this.servicio = servicio;
        this.cargo = cargo;
        this.fechacreacion = fechacreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechainhabilitacion() {
        return fechainhabilitacion;
    }

    public void setFechainhabilitacion(String fechainhabilitacion) {
        this.fechainhabilitacion = fechainhabilitacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
        if (!(object instanceof DatosUsuarios)) {
            return false;
        }
        DatosUsuarios other = (DatosUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.DatosUsuarios[ id=" + id + " ]";
    }

}
