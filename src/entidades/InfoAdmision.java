
package entidades;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_Admision")
@NamedQueries({
    @NamedQuery(name = "InfoAdmision.findAll", query = "SELECT i FROM InfoAdmision i")})
public class InfoAdmision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_servicio_destino")
    private int idServicioDestino;
    @Basic(optional = false)
    @Column(name = "id_IPS")
    private int idIPS;
    @Column(name = "id_anexo")
    private Integer idAnexo;
    @Column(name = "edad")
    private String edad;
    @Column(name = "fecha_afiliacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAfiliacion;
    @Column(name = "n_semanas_cotizadas")
    private Integer nSemanasCotizadas;
    @Column(name = "num_afiliacion_poliza")
    private String numAfiliacionPoliza;
    @Column(name = "nivel_economico")
    private Integer nivelEconomico;
    @Column(name = "estadoIngreso")
    private Character estadoIngreso;
    @Column(name = "causaExterna")
    private String causaExterna;
    @Column(name = "mediodeingreso")
    private String mediodeingreso;
    @Column(name = "id_entidad")
    private Integer idEntidad;
    @Column(name = "documentacion")
    private String documentacion;
    @Lob
    @Column(name = "subjetividadPaciente")
    private String subjetividadPaciente;
    @Lob
    @Column(name = "subjetividadAdmisionista")
    private String subjetividadAdmisionista;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @Column(name = "hora_ingreso")
    private Time horaIngreso;
    @Basic(optional = false)
    @Column(name = "info_atencion")
    private boolean infoAtencion;
    @Column(name = "estado")
    private int estado;
    @Column(name = "Fecha_IngresoDatos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_entidad_admision", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoEntidades idEntidadAdmision;
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoPaciente idDatosPersonales;
    @OneToMany(cascade= CascadeType.ALL,mappedBy="idInfoAdmision", fetch = FetchType.LAZY)
    private List<InfoHistoriac> infoHistoriacList;

    public List<InfoHistoriac> getInfoHistoriacList() {
        return infoHistoriacList;
    }

    public void setInfoHistoriacList(List<InfoHistoriac> infoHistoriacList) {
        this.infoHistoriacList = infoHistoriacList;
    }
    @OneToMany(cascade= CascadeType.ALL,mappedBy="idInfoAdmision", fetch = FetchType.LAZY)
    private List<HospHistoriac> hospHistoriacList;

    public List<HospHistoriac> getHospHistoriacList() {
        return hospHistoriacList;
    }

    public void setHospHistoriacList(List<HospHistoriac> HospHistoriacList) {
        this.hospHistoriacList = HospHistoriacList;
    }
    @OneToMany(cascade= CascadeType.ALL,mappedBy="idInfoAdmision", fetch = FetchType.LAZY)
    private List<UciHistoriac> uciHistoriacList;

    public List<UciHistoriac> getUciHistoriacList() {
        return uciHistoriacList;
    }
    

    public void setUciHistoriacList(List<UciHistoriac> uciHistoriacList) {
        this.uciHistoriacList = uciHistoriacList;
    }
    @OneToMany(cascade= CascadeType.ALL,mappedBy="idInfoAdmision", fetch = FetchType.LAZY)
    private List<UceHistoriac> uceHistoriacList;

    public List<UceHistoriac> getUceHistoriacList() {
        return uceHistoriacList;
    }

    public void setUceHistoriacList(List<UceHistoriac> uceHistoriacList) {
        this.uceHistoriacList = uceHistoriacList;
    }


    public InfoAdmision(Integer id) {
        this.id = id;
    }

    public InfoAdmision(Integer id, int idServicioDestino, int idIPS, int idEntidad, Date fechaIngreso, Time horaIngreso, boolean infoAtencion) {
        this.id = id;
        this.idServicioDestino = idServicioDestino;
        this.idIPS = idIPS;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.infoAtencion = infoAtencion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdServicioDestino() {
        return idServicioDestino;
    }

    public void setIdServicioDestino(int idServicioDestino) {
        this.idServicioDestino = idServicioDestino;
    }

    public int getIdIPS() {
        return idIPS;
    }

    public void setIdIPS(int idIPS) {
        this.idIPS = idIPS;
    }

    public Integer getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Integer getNSemanasCotizadas() {
        return nSemanasCotizadas;
    }

    public void setNSemanasCotizadas(Integer nSemanasCotizadas) {
        this.nSemanasCotizadas = nSemanasCotizadas;
    }

    public String getNumAfiliacionPoliza() {
        return numAfiliacionPoliza;
    }

    public void setNumAfiliacionPoliza(String numAfiliacionPoliza) {
        this.numAfiliacionPoliza = numAfiliacionPoliza;
    }

    public Integer getNivelEconomico() {
        return nivelEconomico;
    }

    public void setNivelEconomico(Integer nivelEconomico) {
        this.nivelEconomico = nivelEconomico;
    }

    public Character getEstadoIngreso() {
        return estadoIngreso;
    }

    public void setEstadoIngreso(Character estadoIngreso) {
        this.estadoIngreso = estadoIngreso;
    }

    public String getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(String causaExterna) {
        this.causaExterna = causaExterna;
    }

    public String getMediodeingreso() {
        return mediodeingreso;
    }

    public void setMediodeingreso(String mediodeingreso) {
        this.mediodeingreso = mediodeingreso;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getSubjetividadPaciente() {
        return subjetividadPaciente;
    }

    public void setSubjetividadPaciente(String subjetividadPaciente) {
        this.subjetividadPaciente = subjetividadPaciente;
    }

    public String getSubjetividadAdmisionista() {
        return subjetividadAdmisionista;
    }

    public void setSubjetividadAdmisionista(String subjetividadAdmisionista) {
        this.subjetividadAdmisionista = subjetividadAdmisionista;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public boolean getInfoAtencion() {
        return infoAtencion;
    }

    public void setInfoAtencion(boolean infoAtencion) {
        this.infoAtencion = infoAtencion;
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

    public InfoEntidades getIdEntidadAdmision() {
        return idEntidadAdmision;
    }

    public void setIdEntidadAdmision(InfoEntidades idEntidadAdmision) {
        this.idEntidadAdmision = idEntidadAdmision;
    }

    public Integer getIdEntidad() {
       return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public InfoPaciente getIdDatosPersonales() {
        return idDatosPersonales;
    }

    public void setIdDatosPersonales(InfoPaciente idDatosPersonales) {
        this.idDatosPersonales = idDatosPersonales;
    }

    public InfoAdmision() {
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
        if (!(object instanceof InfoAdmision)) {
            return false;
        }
        InfoAdmision other = (InfoAdmision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {        
        return "entidades.InfoAdmision[ id=" + id + " ]";
        
    }

    }
