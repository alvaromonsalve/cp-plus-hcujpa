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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "sum_atc_subgrupoquimico")
@NamedQueries({
    @NamedQuery(name = "SumAtcSubgrupoquimico.findAll", query = "SELECT s FROM SumAtcSubgrupoquimico s")})
public class SumAtcSubgrupoquimico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Short estado;
    @JoinColumn(name = "id_subgrupofarmacologico", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SumAtcSubgrupofarmacologico idSubgrupofarmacologico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubgrupoquimico", fetch = FetchType.LAZY)
    private List<SumAtcPrincipioactivo> sumAtcPrincipioactivoList;

    public SumAtcSubgrupoquimico() {
    }

    public SumAtcSubgrupoquimico(Integer id) {
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

    public SumAtcSubgrupofarmacologico getIdSubgrupofarmacologico() {
        return idSubgrupofarmacologico;
    }

    public void setIdSubgrupofarmacologico(SumAtcSubgrupofarmacologico idSubgrupofarmacologico) {
        this.idSubgrupofarmacologico = idSubgrupofarmacologico;
    }

    public List<SumAtcPrincipioactivo> getSumAtcPrincipioactivoList() {
        return sumAtcPrincipioactivoList;
    }

    public void setSumAtcPrincipioactivoList(List<SumAtcPrincipioactivo> sumAtcPrincipioactivoList) {
        this.sumAtcPrincipioactivoList = sumAtcPrincipioactivoList;
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
        if (!(object instanceof SumAtcSubgrupoquimico)) {
            return false;
        }
        SumAtcSubgrupoquimico other = (SumAtcSubgrupoquimico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SumAtcSubgrupoquimico[ id=" + id + " ]";
    }
    
}
