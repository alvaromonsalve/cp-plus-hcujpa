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
import javax.persistence.Lob;
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
@Table(name = "config_cups")
@NamedQueries({
    @NamedQuery(name = "ConfigCups.findAll", query = "SELECT c FROM ConfigCups c")})
public class ConfigCups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "codigo")
    private String codigo;
    @Lob
    @Column(name = "de_subcategoria")
    private String deSubcategoria;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "nivel_pos")
    private Integer nivelPos;
    @Column(name = "mapipos")
    private Boolean mapipos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCups", fetch = FetchType.LAZY)
    private List<ConfigSoat2> configSoat2List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCups", fetch = FetchType.LAZY)
    private List<ConfigSoat1> configSoat1List;
    @JoinColumn(name = "id_estructura_cups", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StaticEstructuraCups idEstructuraCups;
    @Column(name="estado_urg")
    private int estadoUrg; 
    @Column(name="estado")
    private int estado;

    public ConfigCups() {
    }

    public ConfigCups(Integer id) {
        this.id = id;
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

    public String getDeSubcategoria() {
        return deSubcategoria;
    }

    public void setDeSubcategoria(String deSubcategoria) {
        this.deSubcategoria = deSubcategoria;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNivelPos() {
        return nivelPos;
    }

    public void setNivelPos(Integer nivelPos) {
        this.nivelPos = nivelPos;
    }

    public Boolean getMapipos() {
        return mapipos;
    }

    public void setMapipos(Boolean mapipos) {
        this.mapipos = mapipos;
    }

    public List<ConfigSoat2> getConfigSoat2List() {
        return configSoat2List;
    }

    public void setConfigSoat2List(List<ConfigSoat2> configSoat2List) {
        this.configSoat2List = configSoat2List;
    }

    public List<ConfigSoat1> getConfigSoat1List() {
        return configSoat1List;
    }

    public void setConfigSoat1List(List<ConfigSoat1> configSoat1List) {
        this.configSoat1List = configSoat1List;
    }

    public StaticEstructuraCups getIdEstructuraCups() {
        return idEstructuraCups;
    }

    public void setIdEstructuraCups(StaticEstructuraCups idEstructuraCups) {
        this.idEstructuraCups = idEstructuraCups;
    }
    
    public int getEstadoUrg() {
        return estadoUrg;
    }

    public void setEstadoUrg(int estadoUrg) {
        this.estadoUrg = estadoUrg;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        if (!(object instanceof ConfigCups)) {
            return false;
        }
        ConfigCups other = (ConfigCups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ConfigCups[ id=" + id + " ]";
    }
    
}
