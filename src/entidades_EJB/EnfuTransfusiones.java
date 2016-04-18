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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "enfu_transfusiones")
@NamedQueries({
    @NamedQuery(name = "EnfuTransfusiones.findAll", query = "SELECT e FROM EnfuTransfusiones e")})
public class EnfuTransfusiones implements Serializable {

    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Basic(optional = false)
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @Basic(optional = false)
    @Column(name = "componente")
    private int componente;
    @Lob
    @Column(name = "sello")
    private String sello;
    @Lob
    @Column(name = "inspeccion")
    private String inspeccion;
    @Lob
    @Column(name = "reacciones")
    private String reacciones;
    @Lob
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Lob
    @Column(name = "tratamiento")
    private String tratamiento;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idsangre", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UrgSangre idsangre;

    public EnfuTransfusiones() {
    }

    public EnfuTransfusiones(Integer id) {
        this.id = id;
    }

    public EnfuTransfusiones(Integer id, Date horainicio, Date horafin, int componente, Date fecha) {
        this.id = id;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.componente = componente;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UrgSangre getIdsangre() {
        return idsangre;
    }

    public void setIdsangre(UrgSangre idsangre) {
        this.idsangre = idsangre;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public int getComponente() {
        return componente;
    }

    public void setComponente(int componente) {
        this.componente = componente;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public String getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(String inspeccion) {
        this.inspeccion = inspeccion;
    }

    public String getReacciones() {
        return reacciones;
    }

    public void setReacciones(String reacciones) {
        this.reacciones = reacciones;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof EnfuTransfusiones)) {
            return false;
        }
        EnfuTransfusiones other = (EnfuTransfusiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.EnfuTransfusiones[ id=" + id + " ]";
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

}
