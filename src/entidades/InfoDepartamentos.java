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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_departamentos")
@NamedQueries({
    @NamedQuery(name = "InfoDepartamentos.findAll", query = "SELECT i FROM InfoDepartamentos i")})
public class InfoDepartamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;   


    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    

    @JoinColumn(name = "id_pais", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private InfoPais infoPais;

    public InfoDepartamentos() {
    }
    
    public InfoDepartamentos(Integer id) {
    this.id = id;
    }
    
    public InfoDepartamentos(Integer id, String nombre) {
        this.id=id;
        this.nombre=nombre;
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

    public InfoPais getInfoPais() {
        return infoPais;
    }

    public void setInfoPais(InfoPais infoPais) {
        this.infoPais = infoPais;
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
        final InfoDepartamentos other = (InfoDepartamentos) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoDepartamentos[ id=" + id + " ]";
    }
    
}
