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
@Table(name = "enfu_hoja_tratamientos2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuHojaTratamientos2.findAll", query = "SELECT e FROM EnfuHojaTratamientos2 e"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findById", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByIdIngOEvo", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.idIngOEvo = :idIngOEvo"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByTipo", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByIdentificador", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.identificador = :identificador"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByFecha", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByHora", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByProcedimiento", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.procedimiento = :procedimiento"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByEstado", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.estado = :estado"),
    @NamedQuery(name = "EnfuHojaTratamientos2.findByFechaIngresoDatos", query = "SELECT e FROM EnfuHojaTratamientos2 e WHERE e.fechaIngresoDatos = :fechaIngresoDatos")})
public class EnfuHojaTratamientos2 implements Serializable {
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
    @Column(name="usr")
    private int usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idHistoria;
    
    public EnfuHojaTratamientos2() {
    }

    public EnfuHojaTratamientos2(Integer id) {
        this.id = id;
    }

    public EnfuHojaTratamientos2(Integer id, int idIngOEvo, Date fechaIngresoDatos) {
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

    public int getUsr() {
        return usr;
    }

    public void setUsr(int usr) {
        this.usr = usr;
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
        if (!(object instanceof EnfuHojaTratamientos2)) {
            return false;
        }
        EnfuHojaTratamientos2 other = (EnfuHojaTratamientos2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuHojaTratamientos2[ id=" + id + " ]";
    }
    
}
