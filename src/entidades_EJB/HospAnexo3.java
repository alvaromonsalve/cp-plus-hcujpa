package entidades_EJB;

import java.io.Serializable;
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
 * @author Juan Camilo
 */
@Entity
@Table(name = "hosp_anexo3")
@NamedQueries({
    @NamedQuery(name = "HospAnexo3.findAll", query = "SELECT h FROM HospAnexo3 h")})
public class HospAnexo3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "cama")
    private String cama;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private int prioridad;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fechadigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadigita;
    @JoinColumn(name = "idevolucion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospEvolucion idHospEvolucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<HospAnexo3Procedimientos> hospAnexo3ProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<HospAnexo3Cie10> hospAnexo3Cie10List;

    public HospAnexo3() {
    }

    public HospAnexo3(Integer id) {
        this.id = id;
    }

    public HospAnexo3(Integer id, String justificacion, int estado, Date fechadigita, int prioridad) {
        this.id = id;
        this.justificacion = justificacion;
        this.estado = estado;
        this.fechadigita = fechadigita;
        this.prioridad = prioridad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechadigita() {
        return fechadigita;
    }

    public void setFechadigita(Date fechadigita) {
        this.fechadigita = fechadigita;
    }

    public HospEvolucion getIdHospEvolucion() {
        return idHospEvolucion;
    }

    public void setIdHospEvolucion(HospEvolucion idHospEvolucion) {
        this.idHospEvolucion = idHospEvolucion;
    }

    public List<HospAnexo3Procedimientos> getHospAnexo3ProcedimientosList() {
        return hospAnexo3ProcedimientosList;
    }

    public void setHospAnexo3ProcedimientosList(List<HospAnexo3Procedimientos> hospAnexo3ProcedimientosList) {
        this.hospAnexo3ProcedimientosList = hospAnexo3ProcedimientosList;
    }

    public List<HospAnexo3Cie10> getHospAnexo3Cie10List() {
        return hospAnexo3Cie10List;
    }

    public void setHospAnexo3Cie10List(List<HospAnexo3Cie10> hospAnexo3Cie10List) {
        this.hospAnexo3Cie10List = hospAnexo3Cie10List;
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
        if (!(object instanceof HospAnexo3)) {
            return false;
        }
        HospAnexo3 other = (HospAnexo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.HospAnexo3[ id=" + id + " ]";
    }

}
