/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "sum_atc_sistemasorganicos")
@NamedQueries({
    @NamedQuery(name = "SumAtcSistemasorganicos.findAll", query = "SELECT s FROM SumAtcSistemasorganicos s")})
public class SumAtcSistemasorganicos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSistemaorganico")
    private List<SumAtcGrupofarmacologico> sumAtcGrupofarmacologicoList;

    public SumAtcSistemasorganicos() {
    }

    public SumAtcSistemasorganicos(Integer id) {
        this.id = id;
    }

    public SumAtcSistemasorganicos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public List<SumAtcGrupofarmacologico> getSumAtcGrupofarmacologicoList() {
        return sumAtcGrupofarmacologicoList;
    }

    public void setSumAtcGrupofarmacologicoList(List<SumAtcGrupofarmacologico> sumAtcGrupofarmacologicoList) {
        this.sumAtcGrupofarmacologicoList = sumAtcGrupofarmacologicoList;
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
        if (!(object instanceof SumAtcSistemasorganicos)) {
            return false;
        }
        SumAtcSistemasorganicos other = (SumAtcSistemasorganicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SumAtcSistemasorganicos[ id=" + id + " ]";
    }
    
}
