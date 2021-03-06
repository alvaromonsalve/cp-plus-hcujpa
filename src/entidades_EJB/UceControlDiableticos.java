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
@Table(name = "uce_control_diableticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UceControlDiableticos.findAll", query = "SELECT u FROM UceControlDiableticos u"),
    @NamedQuery(name = "UceControlDiableticos.findById", query = "SELECT u FROM UceControlDiableticos u WHERE u.id = :id"),
    @NamedQuery(name = "UceControlDiableticos.findByFecha", query = "SELECT u FROM UceControlDiableticos u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UceControlDiableticos.findByHora", query = "SELECT u FROM UceControlDiableticos u WHERE u.hora = :hora"),
    @NamedQuery(name = "UceControlDiableticos.findByDestrostix", query = "SELECT u FROM UceControlDiableticos u WHERE u.destrostix = :destrostix"),
    @NamedQuery(name = "UceControlDiableticos.findByMultistix", query = "SELECT u FROM UceControlDiableticos u WHERE u.multistix = :multistix"),
    @NamedQuery(name = "UceControlDiableticos.findByInsulina", query = "SELECT u FROM UceControlDiableticos u WHERE u.insulina = :insulina"),
    @NamedQuery(name = "UceControlDiableticos.findByCantidad", query = "SELECT u FROM UceControlDiableticos u WHERE u.cantidad = :cantidad"),
    @NamedQuery(name = "UceControlDiableticos.findByVia", query = "SELECT u FROM UceControlDiableticos u WHERE u.via = :via"),
    @NamedQuery(name = "UceControlDiableticos.findByUsuario", query = "SELECT u FROM UceControlDiableticos u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UceControlDiableticos.findByEstado", query = "SELECT u FROM UceControlDiableticos u WHERE u.estado = :estado"),
    @NamedQuery(name = "UceControlDiableticos.findByFechaIngresoDato", query = "SELECT u FROM UceControlDiableticos u WHERE u.fechaIngresoDato = :fechaIngresoDato")})
public class UceControlDiableticos implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "destrostix")
    private Float destrostix;
    @Column(name = "multistix")
    private Float multistix;
    @Column(name = "insulina")
    private Character insulina;
    @Column(name = "cantidad")
    private Float cantidad;
    @Column(name = "via")
    private Character via;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_dato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDato;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UceHistoriac idHistoria;

    public UceControlDiableticos() {
    }

    public UceControlDiableticos(Integer id) {
        this.id = id;
    }

    public UceControlDiableticos(Integer id, Date fechaIngresoDato) {
        this.id = id;
        this.fechaIngresoDato = fechaIngresoDato;
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

    public Float getDestrostix() {
        return destrostix;
    }

    public void setDestrostix(Float destrostix) {
        this.destrostix = destrostix;
    }

    public Float getMultistix() {
        return multistix;
    }

    public void setMultistix(Float multistix) {
        this.multistix = multistix;
    }

    public Character getInsulina() {
        return insulina;
    }

    public void setInsulina(Character insulina) {
        this.insulina = insulina;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Character getVia() {
        return via;
    }

    public void setVia(Character via) {
        this.via = via;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDato() {
        return fechaIngresoDato;
    }

    public void setFechaIngresoDato(Date fechaIngresoDato) {
        this.fechaIngresoDato = fechaIngresoDato;
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
        if (!(object instanceof UceControlDiableticos)) {
            return false;
        }
        UceControlDiableticos other = (UceControlDiableticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UceControlDiableticos[ id=" + id + " ]";
    }
    
}
