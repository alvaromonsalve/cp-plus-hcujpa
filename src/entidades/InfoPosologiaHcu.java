/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_posologia_hcu")
@NamedQueries({
    @NamedQuery(name = "InfoPosologiaHcu.findAll", query = "SELECT i FROM InfoPosologiaHcu i")})
public class InfoPosologiaHcu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_suministro", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SumSuministro idSuministro;
    @JoinColumn(name="id_historiac",referencedColumnName="id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfoHistoriac idHistoriac;
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
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "fdigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdigita;

    public InfoPosologiaHcu() {
    }

    public InfoPosologiaHcu(Integer id) {
        this.id = id;
    }

    public InfoPosologiaHcu(Integer id, SumSuministro idSuministro, InfoHistoriac idHistoriac, int usuario, Date fdigita) {
        this.id = id;
        this.idSuministro = idSuministro;
        this.idHistoriac = idHistoriac;
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

    public InfoHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(InfoHistoriac idHistoriac) {
        this.idHistoriac = idHistoriac;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }
    
    public Float getDosisN() {
        return dosisN;
    }

    public void setDosisN(Float dosisN) {
        this.dosisN = dosisN;
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
        if (!(object instanceof InfoPosologiaHcu)) {
            return false;
        }
        InfoPosologiaHcu other = (InfoPosologiaHcu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIdSuministro().getIdPricipioactivo().getNombre();
    }
    
}
