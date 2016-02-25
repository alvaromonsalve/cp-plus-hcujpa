/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "config_procedimientos")
public class ConfigProcedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "config1")
    private Integer config1;
    @Column(name = "config2")
    private Integer config2;
    @Column(name = "config3")
    private Integer config3;
    @Column(name = "config4")
    private Integer config4;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @JoinColumn(name = "id_cups", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConfigCups idCups;

    public ConfigProcedimientos() {
    }

    public ConfigProcedimientos(Integer id) {
        this.id = id;
    }

    public ConfigProcedimientos(Integer id, Date fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfig1() {
        return config1;
    }

    public void setConfig1(Integer config1) {
        this.config1 = config1;
    }

    public Integer getConfig2() {
        return config2;
    }

    public void setConfig2(Integer config2) {
        this.config2 = config2;
    }

    public Integer getConfig3() {
        return config3;
    }

    public void setConfig3(Integer config3) {
        this.config3 = config3;
    }

    public Integer getConfig4() {
        return config4;
    }

    public void setConfig4(Integer config4) {
        this.config4 = config4;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public ConfigCups getIdCups() {
        return idCups;
    }

    public void setIdCups(ConfigCups idCups) {
        this.idCups = idCups;
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
        if (!(object instanceof ConfigProcedimientos)) {
            return false;
        }
        ConfigProcedimientos other = (ConfigProcedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.ConfigProcedimientos[ id=" + id + " ]";
    }
    
}
