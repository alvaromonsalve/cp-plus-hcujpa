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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "seg_servicios")
@NamedQueries({
    @NamedQuery(name = "SegServicios.findAll", query = "SELECT s FROM SegServicios s")})
public class SegServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "servicio")
    private String servicio;
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private int idServicio;
    @Basic(optional = false)
    @Column(name = "id_static_especialidad")
    private int idStaticEspecialidad;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "user_mod")
    private int userMod;
    @Basic(optional = false)
    @Column(name = "fecha_dato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDato;
    @Basic(optional = false)
    @Column(name = "id_info_admision")
    private int idInfoAdmision;

    public SegServicios() {
    }

    public SegServicios(Integer id) {
        this.id = id;
    }

    public SegServicios(Integer id, int idServicio, int idStaticEspecialidad, int tipo, int estado, int userMod, Date fechaDato, int idInfoAdmision) {
        this.id = id;
        this.idServicio = idServicio;
        this.idStaticEspecialidad = idStaticEspecialidad;
        this.tipo = tipo;
        this.estado = estado;
        this.userMod = userMod;
        this.fechaDato = fechaDato;
        this.idInfoAdmision = idInfoAdmision;
    }

    public int getIdInfoAdmision() {
        return idInfoAdmision;
    }

    public void setIdInfoAdmision(int idInfoAdmision) {
        this.idInfoAdmision = idInfoAdmision;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdStaticEspecialidad() {
        return idStaticEspecialidad;
    }

    public void setIdStaticEspecialidad(int idStaticEspecialidad) {
        this.idStaticEspecialidad = idStaticEspecialidad;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getUserMod() {
        return userMod;
    }

    public void setUserMod(int userMod) {
        this.userMod = userMod;
    }

    public Date getFechaDato() {
        return fechaDato;
    }

    public void setFechaDato(Date fechaDato) {
        this.fechaDato = fechaDato;
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
        if (!(object instanceof SegServicios)) {
            return false;
        }
        SegServicios other = (SegServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.SegServicios[ id=" + id + " ]";
    }
    
}
