
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
@Table(name = "hosp_pruebas_complement")
@NamedQueries({
    @NamedQuery(name = "HospPruebasComplement.findAll", query = "SELECT i FROM HospPruebasComplement i")})
public class HospPruebasComplement implements Serializable {
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
    private HospHistoriac idHosphistoriac;

    public HospHistoriac getIdHosphistoriac() {
        return idHosphistoriac;
    }

    public void setIdHosphistoriac(HospHistoriac idHosphistoriac) {
        this.idHosphistoriac = idHosphistoriac;
    }

    public HospPruebasComplement() {
    }

    public HospPruebasComplement(Integer id) {
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
        if (!(object instanceof HospPruebasComplement)) {
            return false;
        }
        HospPruebasComplement other = (HospPruebasComplement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HospPruebasComplement[ id=" + id + " ]";
    }

}
