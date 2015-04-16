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

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "hosp_control_diableticos")
@NamedQueries({
    @NamedQuery(name = "HospControlDiableticosH.findAll", query = "SELECT h FROM HospControlDiableticosH h")})
public class HospControlDiableticosH implements Serializable {
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
    private HospHistoriac idHospHistoriac;

    public HospHistoriac getIdHospHistoriac() {
        return idHospHistoriac;
    }

    public void setIdHospHistoriac(HospHistoriac idHospHistoriac) {
        this.idHospHistoriac = idHospHistoriac;
    }

    public HospControlDiableticosH() {
    }

    public HospControlDiableticosH(Integer id) {
        this.id = id;
    }

    public HospControlDiableticosH(Integer id, Date fechaIngresoDato) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospControlDiableticosH)) {
            return false;
        }
        HospControlDiableticosH other = (HospControlDiableticosH) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.HospControlDiableticosH[ id=" + id + " ]";
    }
    
}
