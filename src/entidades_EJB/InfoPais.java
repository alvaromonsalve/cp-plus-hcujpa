
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
@Table(name = "info_Pais")
@NamedQueries({
    @NamedQuery(name = "InfoPais.findAll", query = "SELECT i FROM InfoPais i")})
public class InfoPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "codigo_dian")
    private String codigoDian;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infoPais", fetch = FetchType.LAZY)
    private List<InfoDepartamentos> infoDepartamentosList;

    public InfoPais() {
    }

    public InfoPais(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoDian() {
        return codigoDian;
    }

    public void setCodigoDian(String codigoDian) {
        this.codigoDian = codigoDian;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<InfoDepartamentos> getInfoDepartamentosList() {
        return infoDepartamentosList;
    }

    public void setInfoDepartamentosList(List<InfoDepartamentos> infoDepartamentosList) {
        this.infoDepartamentosList = infoDepartamentosList;
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
        if (!(object instanceof InfoPais)) {
            return false;
        }
        InfoPais other = (InfoPais) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoPais[ id=" + id + " ]";
    }

}
