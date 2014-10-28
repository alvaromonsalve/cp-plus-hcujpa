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
@Table(name = "config_camas")
@NamedQueries({
    @NamedQuery(name = "ConfigCamas.findAll", query = "SELECT c FROM ConfigCamas c")})
public class ConfigCamas implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfigCamas")
    private List<InfoCamas> infoCamasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numero_cama")
    private Integer numeroCama;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "servicio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConfigServicio servicio;

    public ConfigCamas() {
    }

    public ConfigCamas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroCama() {
        return numeroCama;
    }

    public void setNumeroCama(Integer numeroCama) {
        this.numeroCama = numeroCama;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ConfigServicio getServicio() {
        return servicio;
    }

    public void setServicio(ConfigServicio servicio) {
        this.servicio = servicio;
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
        if (!(object instanceof ConfigCamas)) {
            return false;
        }
        ConfigCamas other = (ConfigCamas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNumeroCama().toString();
    }

    public List<InfoCamas> getInfoCamasList() {
        return infoCamasList;
    }

    public void setInfoCamasList(List<InfoCamas> infoCamasList) {
        this.infoCamasList = infoCamasList;
    }
    
}
