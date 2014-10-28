/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_pruebas_complement")
@NamedQueries({
    @NamedQuery(name = "InfoPruebasComplement.findAll", query = "SELECT i FROM InfoPruebasComplement i")})
public class InfoPruebasComplement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "estado")
    private Short estado;
    @JoinColumn(name="id_info_historiac",referencedColumnName="id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfoHistoriac idInfohistoriac;

    public InfoHistoriac getIdInfohistoriac() {
        return idInfohistoriac;
    }

    public void setIdInfohistoriac(InfoHistoriac idInfohistoriac) {
        this.idInfohistoriac = idInfohistoriac;
    }

    public InfoPruebasComplement() {
    }

    public InfoPruebasComplement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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
        if (!(object instanceof InfoPruebasComplement)) {
            return false;
        }
        InfoPruebasComplement other = (InfoPruebasComplement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoPruebasComplement[ id=" + id + " ]";
    }
    
}
