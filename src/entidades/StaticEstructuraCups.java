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
@Table(name = "static_estructura_cups")
@NamedQueries({
    @NamedQuery(name = "StaticEstructuraCups.findAll", query = "SELECT s FROM StaticEstructuraCups s")})
public class StaticEstructuraCups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "capitulo")
    private String capitulo;
    @Column(name = "des_capitulo")
    private String desCapitulo;
    @Column(name = "seccion")
    private String seccion;
    @Column(name = "desc_seccion")
    private String descSeccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstructuraCups")
    private List<ConfigCups> configCupsList;

    public StaticEstructuraCups() {
    }

    public StaticEstructuraCups(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public String getDesCapitulo() {
        return desCapitulo;
    }

    public void setDesCapitulo(String desCapitulo) {
        this.desCapitulo = desCapitulo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getDescSeccion() {
        return descSeccion;
    }

    public void setDescSeccion(String descSeccion) {
        this.descSeccion = descSeccion;
    }

    public List<ConfigCups> getConfigCupsList() {
        return configCupsList;
    }

    public void setConfigCupsList(List<ConfigCups> configCupsList) {
        this.configCupsList = configCupsList;
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
        if (!(object instanceof StaticEstructuraCups)) {
            return false;
        }
        StaticEstructuraCups other = (StaticEstructuraCups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDesCapitulo();
    }
    
}
