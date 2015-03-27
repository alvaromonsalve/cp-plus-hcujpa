/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
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

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hcu_anexo3_det")
@NamedQueries({
    @NamedQuery(name = "HcuAnexo3Det.findAll", query = "SELECT h FROM HcuAnexo3Det h")})
public class HcuAnexo3Det implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cantidad")
    private Short cantidad;
    @JoinColumn(name = "codigo_cup", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConfigCups codigoCup;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @JoinColumn(name = "id_hcu_anexo3", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HcuAnexo3 idHcuAnexo3;

    public HcuAnexo3Det() {
    }

    public HcuAnexo3Det(Integer id) {
        this.id = id;
    }

    public HcuAnexo3Det(Integer id, ConfigCups codigoCup) {
        this.id = id;
        this.codigoCup = codigoCup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public ConfigCups getCodigoCup() {
        return codigoCup;
    }

    public void setCodigoCup(ConfigCups codigoCup) {
        this.codigoCup = codigoCup;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HcuAnexo3 getIdHcuAnexo3() {
        return idHcuAnexo3;
    }

    public void setIdHcuAnexo3(HcuAnexo3 idHcuAnexo3) {
        this.idHcuAnexo3 = idHcuAnexo3;
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
        if (!(object instanceof HcuAnexo3Det)) {
            return false;
        }
        HcuAnexo3Det other = (HcuAnexo3Det) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuAnexo3Det[ id=" + id + " ]";
    }
    
}
