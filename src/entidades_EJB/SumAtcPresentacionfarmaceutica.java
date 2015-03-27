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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "sum_atc_presentacionfarmaceutica")
@NamedQueries({
    @NamedQuery(name = "SumAtcPresentacionfarmaceutica.findAll", query = "SELECT s FROM SumAtcPresentacionfarmaceutica s")})
public class SumAtcPresentacionfarmaceutica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Administracion")
    private String administracion;
    @Basic(optional = false)
    @Column(name = "Dosis")
    private String dosis;
    @Column(name = "Estado")
    private Short estado;

    public SumAtcPresentacionfarmaceutica() {
    }

    public SumAtcPresentacionfarmaceutica(Integer id) {
        this.id = id;
    }

    public SumAtcPresentacionfarmaceutica(Integer id, String administracion, String dosis) {
        this.id = id;
        this.administracion = administracion;
        this.dosis = dosis;
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

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
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
        if (!(object instanceof SumAtcPresentacionfarmaceutica)) {
            return false;
        }
        SumAtcPresentacionfarmaceutica other = (SumAtcPresentacionfarmaceutica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SumAtcPresentacionfarmaceutica[ id=" + id + " ]";
    }
    
}
