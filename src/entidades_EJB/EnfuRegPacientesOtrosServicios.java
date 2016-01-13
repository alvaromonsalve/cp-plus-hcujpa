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
@Table(name = "enfu_reg_pacientes_otros_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuRegPacientesOtrosServicios.findAll", query = "SELECT e FROM EnfuRegPacientesOtrosServicios e"),
    @NamedQuery(name = "EnfuRegPacientesOtrosServicios.findById", query = "SELECT e FROM EnfuRegPacientesOtrosServicios e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuRegPacientesOtrosServicios.findByServicioTraslado", query = "SELECT e FROM EnfuRegPacientesOtrosServicios e WHERE e.servicioTraslado = :servicioTraslado"),
    @NamedQuery(name = "EnfuRegPacientesOtrosServicios.findByFecha", query = "SELECT e FROM EnfuRegPacientesOtrosServicios e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuRegPacientesOtrosServicios.findByHora", query = "SELECT e FROM EnfuRegPacientesOtrosServicios e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuRegPacientesOtrosServicios.findByEstado", query = "SELECT e FROM EnfuRegPacientesOtrosServicios e WHERE e.estado = :estado")})
public class EnfuRegPacientesOtrosServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "servicio_traslado")
    private int servicioTraslado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "estado")
    private char estado;
    @JoinColumn(name = "id_config_camas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConfigCamas idConfigCamas;

    public EnfuRegPacientesOtrosServicios() {
    }

    public EnfuRegPacientesOtrosServicios(Integer id) {
        this.id = id;
    }

    public EnfuRegPacientesOtrosServicios(Integer id, int servicioTraslado, Date fecha, Date hora, char estado) {
        this.id = id;
        this.servicioTraslado = servicioTraslado;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getServicioTraslado() {
        return servicioTraslado;
    }

    public void setServicioTraslado(int servicioTraslado) {
        this.servicioTraslado = servicioTraslado;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public ConfigCamas getIdConfigCamas() {
        return idConfigCamas;
    }

    public void setIdConfigCamas(ConfigCamas idConfigCamas) {
        this.idConfigCamas = idConfigCamas;
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
        if (!(object instanceof EnfuRegPacientesOtrosServicios)) {
            return false;
        }
        EnfuRegPacientesOtrosServicios other = (EnfuRegPacientesOtrosServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuRegPacientesOtrosServicios[ id=" + id + " ]";
    }
    
}
