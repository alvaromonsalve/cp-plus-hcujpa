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
@Table(name = "uce_hoja_tratamientos2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospHojaTratamientos2.findAll", query = "SELECT h FROM HospHojaTratamientos2 h"),
    @NamedQuery(name = "HospHojaTratamientos2.findById", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.id = :id"),
    @NamedQuery(name = "HospHojaTratamientos2.findByIdIngOEvo", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.idIngOEvo = :idIngOEvo"),
    @NamedQuery(name = "HospHojaTratamientos2.findByTipo", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.tipo = :tipo"),
    @NamedQuery(name = "HospHojaTratamientos2.findByIdentificador", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.identificador = :identificador"),
    @NamedQuery(name = "HospHojaTratamientos2.findByFecha", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HospHojaTratamientos2.findByHora", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.hora = :hora"),
    @NamedQuery(name = "HospHojaTratamientos2.findByProcedimiento", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.procedimiento = :procedimiento"),
    @NamedQuery(name = "HospHojaTratamientos2.findByEstado", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.estado = :estado"),
    @NamedQuery(name = "HospHojaTratamientos2.findByUsr", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.usr = :usr"),
    @NamedQuery(name = "HospHojaTratamientos2.findByFechaIngresoDatos", query = "SELECT h FROM HospHojaTratamientos2 h WHERE h.fechaIngresoDatos = :fechaIngresoDatos")})
public class UceHojaTratamientos2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_ing_o_evo")
    private int idIngOEvo;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "identificador")
    private Integer identificador;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "procedimiento")
    private String procedimiento;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UceHistoriac idHistoria;
    public UceHojaTratamientos2() {
    }

    public UceHojaTratamientos2(Integer id) {
        this.id = id;
    }

    public UceHojaTratamientos2(Integer id, int idIngOEvo, Date fechaIngresoDatos) {
        this.id = id;
        this.idIngOEvo = idIngOEvo;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdIngOEvo() {
        return idIngOEvo;
    }

    public void setIdIngOEvo(int idIngOEvo) {
        this.idIngOEvo = idIngOEvo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public UceHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(UceHistoriac idHistoria) {
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
        if (!(object instanceof UceHojaTratamientos2)) {
            return false;
        }
        UceHojaTratamientos2 other = (UceHojaTratamientos2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospHojaTratamientos2[ id=" + id + " ]";
    }
    
}
