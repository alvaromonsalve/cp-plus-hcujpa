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
@Table(name = "gen_curaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenCuraciones.findAll", query = "SELECT g FROM GenCuraciones g"),
    @NamedQuery(name = "GenCuraciones.findById", query = "SELECT g FROM GenCuraciones g WHERE g.id = :id"),
    @NamedQuery(name = "GenCuraciones.findByFecha", query = "SELECT g FROM GenCuraciones g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "GenCuraciones.findByHora", query = "SELECT g FROM GenCuraciones g WHERE g.hora = :hora"),
    @NamedQuery(name = "GenCuraciones.findByServicio", query = "SELECT g FROM GenCuraciones g WHERE g.servicio = :servicio"),
    @NamedQuery(name = "GenCuraciones.findByUsr", query = "SELECT g FROM GenCuraciones g WHERE g.usr = :usr"),
    @NamedQuery(name = "GenCuraciones.findByEstado", query = "SELECT g FROM GenCuraciones g WHERE g.estado = :estado")})
public class GenCuraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "servicio")
    private String servicio;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoPaciente idPaciente;

    public GenCuraciones() {
    }

    public GenCuraciones(Integer id) {
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
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

    public InfoPaciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(InfoPaciente idPaciente) {
        this.idPaciente = idPaciente;
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
        if (!(object instanceof GenCuraciones)) {
            return false;
        }
        GenCuraciones other = (GenCuraciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_AR.GenCuraciones[ id=" + id + " ]";
    }

}
