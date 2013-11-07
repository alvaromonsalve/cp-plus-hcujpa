/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

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
 * @author Administrador
 */
@Entity
@Table(name = "hcu_evo_posologia")
@NamedQueries({
    @NamedQuery(name = "HcuEvoPosologia.findAll", query = "SELECT h FROM HcuEvoPosologia h")})
public class HcuEvoPosologia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_suministro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idSuministro;
    @JoinColumn(name = "id_hcu_evolucion",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HcuEvolucion idHcuEvolucion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis_n")
    private Float dosisN;
    @Column(name = "cantidad")
    private Float cantidad;
    @Lob
    @Column(name = "cantidad_texto")
    private String cantidadTexto;
    @Column(name = "dosis_u")
    private String dosisU;
    @Column(name = "via")
    private String via;
    @Lob
    @Column(name = "administracion")
    private String administracion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "fdigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdigita;

    public HcuEvoPosologia() {
    }

    public HcuEvoPosologia(Integer id) {
        this.id = id;
    }

    public HcuEvoPosologia(Integer id, SumSuministro idSuministro, HcuEvolucion idHcuEvolucion, int usuario, Date fdigita) {
        this.id = id;
        this.idSuministro = idSuministro;
        this.idHcuEvolucion = idHcuEvolucion;
        this.usuario = usuario;
        this.fdigita = fdigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SumSuministro getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
    }

    public HcuEvolucion getIdHcuEvolucion() {
        return idHcuEvolucion;
    }

    public void setIdHcuEvolucion(HcuEvolucion idHcuEvolucion) {
        this.idHcuEvolucion = idHcuEvolucion;
    }

    public Float getDosisN() {
        return dosisN;
    }

    public void setDosisN(Float dosisN) {
        this.dosisN = dosisN;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidadTexto() {
        return cantidadTexto;
    }

    public void setCantidadTexto(String cantidadTexto) {
        this.cantidadTexto = cantidadTexto;
    }

    public String getDosisU() {
        return dosisU;
    }

    public void setDosisU(String dosisU) {
        this.dosisU = dosisU;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
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

    public Date getFdigita() {
        return fdigita;
    }

    public void setFdigita(Date fdigita) {
        this.fdigita = fdigita;
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
        if (!(object instanceof HcuEvoPosologia)) {
            return false;
        }
        HcuEvoPosologia other = (HcuEvoPosologia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuEvoPosologia[ id=" + id + " ]";
    }
    
}
