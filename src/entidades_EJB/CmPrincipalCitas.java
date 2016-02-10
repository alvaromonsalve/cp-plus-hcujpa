/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "cm_principal_citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmPrincipalCitas.findAll", query = "SELECT c FROM CmPrincipalCitas c"),
    @NamedQuery(name = "CmPrincipalCitas.findById", query = "SELECT c FROM CmPrincipalCitas c WHERE c.id = :id"),
    @NamedQuery(name = "CmPrincipalCitas.findByFechaCita", query = "SELECT c FROM CmPrincipalCitas c WHERE c.fechaCita = :fechaCita"),
    @NamedQuery(name = "CmPrincipalCitas.findByHoraCita", query = "SELECT c FROM CmPrincipalCitas c WHERE c.horaCita = :horaCita"),
    @NamedQuery(name = "CmPrincipalCitas.findByTipoSolicitud", query = "SELECT c FROM CmPrincipalCitas c WHERE c.tipoSolicitud = :tipoSolicitud"),
    @NamedQuery(name = "CmPrincipalCitas.findByPrimeraVez", query = "SELECT c FROM CmPrincipalCitas c WHERE c.primeraVez = :primeraVez"),
    @NamedQuery(name = "CmPrincipalCitas.findByAutorizacion", query = "SELECT c FROM CmPrincipalCitas c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmPrincipalCitas.findByEstado", query = "SELECT c FROM CmPrincipalCitas c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmPrincipalCitas.findByMat", query = "SELECT c FROM CmPrincipalCitas c WHERE c.mat = :mat"),
    @NamedQuery(name = "CmPrincipalCitas.findByFechaDigitacion", query = "SELECT c FROM CmPrincipalCitas c WHERE c.fechaDigitacion = :fechaDigitacion")})
public class CmPrincipalCitas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_cita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;
    @Column(name = "hora_cita")
    @Temporal(TemporalType.TIME)
    private Date horaCita;
    @Column(name = "tipo_solicitud")
    private Character tipoSolicitud;
    @Column(name = "primera_vez")
    private Character primeraVez;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "autorizacion")
    private String autorizacion;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "mat")
    private Character mat;
    @Basic(optional = false)
    @Column(name = "fecha_digitacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDigitacion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoPaciente idPaciente;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CmEspPprofesional idEspecialidad;
    @JoinColumn(name = "id_entidad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoEntidades idEntidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCita")
    private List<CeHistoriac> ceHistoriacList;

    public CmPrincipalCitas() {
    }

    public CmPrincipalCitas(Integer id) {
        this.id = id;
    }

    public CmPrincipalCitas(Integer id, Character mat, Date fechaDigitacion) {
        this.id = id;
        this.mat = mat;
        this.fechaDigitacion = fechaDigitacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Date horaCita) {
        this.horaCita = horaCita;
    }

    public Character getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(Character tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Character getPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(Character primeraVez) {
        this.primeraVez = primeraVez;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Character getMat() {
        return mat;
    }

    public void setMat(Character mat) {
        this.mat = mat;
    }

    public Date getFechaDigitacion() {
        return fechaDigitacion;
    }

    public void setFechaDigitacion(Date fechaDigitacion) {
        this.fechaDigitacion = fechaDigitacion;
    }

    public InfoPaciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(InfoPaciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public CmEspPprofesional getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(CmEspPprofesional idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public InfoEntidades getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(InfoEntidades idEntidad) {
        this.idEntidad = idEntidad;
    }

    @XmlTransient
    public List<CeHistoriac> getCeHistoriacList() {
        return ceHistoriacList;
    }

    public void setCeHistoriacList(List<CeHistoriac> ceHistoriacList) {
        this.ceHistoriacList = ceHistoriacList;
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
        if (!(object instanceof CmPrincipalCitas)) {
            return false;
        }
        CmPrincipalCitas other = (CmPrincipalCitas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.CmPrincipalCitas[ id=" + id + " ]";
    }
    
}
