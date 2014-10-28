
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_municipios")
@NamedQueries({
    @NamedQuery(name = "InfoMunicipios.findAll", query = "SELECT i FROM InfoMunicipios i")})
public class InfoMunicipios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @JoinColumn(name = "id_dep", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoDepartamentos idDep;
    
    public InfoMunicipios() {
    }
    
    public InfoMunicipios(Integer id) {
        this.id=id;
    }
    
    public InfoMunicipios(Integer id, String codigo, String nombre){
        this.id=id;
        this.codigo=codigo;
        this.nombre=nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public InfoDepartamentos getIdDep() {
        return idDep;
    }

    public void setIdDep(InfoDepartamentos idDep) {
        this.idDep = idDep;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InfoMunicipios other = (InfoMunicipios) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "entidades.InfoMunicipios[ id="+ id +"]";
    }


    
}
