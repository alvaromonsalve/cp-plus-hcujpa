/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "sum_atc_laboratorio")
@NamedQueries({
    @NamedQuery(name = "SumAtcLaboratorio.findAll", query = "SELECT s FROM SumAtcLaboratorio s")})
public class SumAtcLaboratorio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "Estado")
    private Short estado;
    @OneToMany(cascade= CascadeType.ALL,mappedBy="idLaboratorio", fetch = FetchType.LAZY)
    private List<SumSuministro> SumSuministroList;

    public List<SumSuministro> getSumSuministroList() {
        return SumSuministroList;
    }

    public void setSumSuministroList(List<SumSuministro> SumSuministroList) {
        this.SumSuministroList = SumSuministroList;
    }

    public SumAtcLaboratorio() {
    }

    public SumAtcLaboratorio(Integer id) {
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
        if (!(object instanceof SumAtcLaboratorio)) {
            return false;
        }
        SumAtcLaboratorio other = (SumAtcLaboratorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SumAtcLaboratorio[ id=" + id + " ]";
    }
    
}
