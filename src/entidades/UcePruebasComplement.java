
package entidades;

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
@Table(name = "uce_pruebas_complement")
@NamedQueries({
    @NamedQuery(name = "UcePruebasComplement.findAll", query = "SELECT i FROM UcePruebasComplement i")})
public class UcePruebasComplement implements Serializable {
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
    @ManyToOne(optional = false)
    private UceHistoriac idUcehistoriac;

    public UceHistoriac getIdUcehistoriac() {
        return idUcehistoriac;
    }

    public void setIdHosphistoriac(UceHistoriac idUcehistoriac) {
        this.idUcehistoriac = idUcehistoriac;
    }

    public UcePruebasComplement() {
    }

    public UcePruebasComplement(Integer id) {
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
        if (!(object instanceof UcePruebasComplement)) {
            return false;
        }
        UcePruebasComplement other = (UcePruebasComplement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UcePruebasComplement[ id=" + id + " ]";
    }

}
