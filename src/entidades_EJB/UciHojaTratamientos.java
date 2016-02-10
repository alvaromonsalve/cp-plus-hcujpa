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
@Table(name = "uci_hoja_tratamientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciHojaTratamientos.findAll", query = "SELECT h FROM UciHojaTratamientos h"),
    @NamedQuery(name = "UciHojaTratamientos.findById", query = "SELECT h FROM UciHojaTratamientos h WHERE h.id = :id"),
    @NamedQuery(name = "UciHojaTratamientos.findByTipo", query = "SELECT h FROM UciHojaTratamientos h WHERE h.tipo = :tipo"),
    @NamedQuery(name = "UciHojaTratamientos.findByIdentificador", query = "SELECT h FROM UciHojaTratamientos h WHERE h.identificador = :identificador"),
    @NamedQuery(name = "UciHojaTratamientos.findByFecha", query = "SELECT h FROM UciHojaTratamientos h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "UciHojaTratamientos.findByHora", query = "SELECT h FROM UciHojaTratamientos h WHERE h.hora = :hora"),
    @NamedQuery(name = "UciHojaTratamientos.findByDroga", query = "SELECT h FROM UciHojaTratamientos h WHERE h.droga = :droga"),
    @NamedQuery(name = "UciHojaTratamientos.findByDosis", query = "SELECT h FROM UciHojaTratamientos h WHERE h.dosis = :dosis"),
    @NamedQuery(name = "UciHojaTratamientos.findByVia", query = "SELECT h FROM UciHojaTratamientos h WHERE h.via = :via"),
    @NamedQuery(name = "UciHojaTratamientos.findByObservaciones", query = "SELECT h FROM UciHojaTratamientos h WHERE h.observaciones = :observaciones"),
    @NamedQuery(name = "UciHojaTratamientos.findByUsr", query = "SELECT h FROM UciHojaTratamientos h WHERE h.usr = :usr"),
    @NamedQuery(name = "UciHojaTratamientos.findByEstado", query = "SELECT h FROM UciHojaTratamientos h WHERE h.estado = :estado"),
    @NamedQuery(name = "UciHojaTratamientos.findByFechaIngresoDatos", query = "SELECT h FROM UciHojaTratamientos h WHERE h.fechaIngresoDatos = :fechaIngresoDatos")})
public class UciHojaTratamientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "identificador")
    private Integer identificador;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "droga")
    private String droga;
    @Column(name = "dosis")
    private String dosis;
    @Column(name = "via")
    private String via;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciHistoriac idHistoria;
    @JoinColumn(name = "id_suministro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idSuministro;
    
    public UciHojaTratamientos() {
    }

    public UciHojaTratamientos(Integer id) {
        this.id = id;
    }

    public UciHojaTratamientos(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public UciHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(UciHistoriac idHistoria) {
        this.idHistoria = idHistoria;
    }

    public SumSuministro getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
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
        if (!(object instanceof UciHojaTratamientos)) {
            return false;
        }
        UciHojaTratamientos other = (UciHojaTratamientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciHojaTratamientos[ id=" + id + " ]";
    }
    
}
