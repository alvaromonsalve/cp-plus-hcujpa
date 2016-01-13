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
@Table(name = "enfu_tratamientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuTratamientos.findAll", query = "SELECT e FROM EnfuTratamientos e"),
    @NamedQuery(name = "EnfuTratamientos.findById", query = "SELECT e FROM EnfuTratamientos e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuTratamientos.findByDroga", query = "SELECT e FROM EnfuTratamientos e WHERE e.droga = :droga"),
    @NamedQuery(name = "EnfuTratamientos.findByDosis", query = "SELECT e FROM EnfuTratamientos e WHERE e.dosis = :dosis"),
    @NamedQuery(name = "EnfuTratamientos.findByFecha", query = "SELECT e FROM EnfuTratamientos e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuTratamientos.findByHora", query = "SELECT e FROM EnfuTratamientos e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuTratamientos.findByTipo", query = "SELECT e FROM EnfuTratamientos e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "EnfuTratamientos.findByEstado", query = "SELECT e FROM EnfuTratamientos e WHERE e.estado = :estado"),
    @NamedQuery(name = "EnfuTratamientos.findByUsuario", query = "SELECT e FROM EnfuTratamientos e WHERE e.usuario = :usuario"),
    @NamedQuery(name = "EnfuTratamientos.findByFechaIngresoDatos", query = "SELECT e FROM EnfuTratamientos e WHERE e.fechaIngresoDatos = :fechaIngresoDatos")})
public class EnfuTratamientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "droga")
    private String droga;
    @Basic(optional = false)
    @Column(name = "dosis")
    private String dosis;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Lob
    @Column(name = "nota")
    private String nota;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idHistoria;
    
    public EnfuTratamientos() {
    }

    public EnfuTratamientos(Integer id) {
        this.id = id;
    }

    public EnfuTratamientos(Integer id, String droga, String dosis, Date fecha, Date hora, int tipo, int estado, int usuario, Date fechaIngresoDatos) {
        this.id = id;
        this.droga = droga;
        this.dosis = dosis;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.estado = estado;
        this.usuario = usuario;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDroga() {
        return droga;
    }

    public void setDroga(String droga) {
        this.droga = droga;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public InfoHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(InfoHistoriac idHistoria) {
        this.idHistoria = idHistoria;
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
        if (!(object instanceof EnfuTratamientos)) {
            return false;
        }
        EnfuTratamientos other = (EnfuTratamientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuTratamientos[ id=" + id + " ]";
    }
    
}
