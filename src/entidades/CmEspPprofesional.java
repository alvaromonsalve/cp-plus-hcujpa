/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Administrador
 */
@Entity
@Table(name = "cm_esp_pprofesional")
@NamedQueries({
    @NamedQuery(name = "CmEspPprofesional.findAll", query = "SELECT c FROM CmEspPprofesional c")})
public class CmEspPprofesional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_profesional", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmProfesionales idProfesional;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StaticEspecialidades idEspecialidad;

    public CmEspPprofesional() {
    }

    public CmEspPprofesional(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public CmProfesionales getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(CmProfesionales idProfesional) {
        this.idProfesional = idProfesional;
    }

    public StaticEspecialidades getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(StaticEspecialidades idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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
        if (!(object instanceof CmEspPprofesional)) {
            return false;
        }
        CmEspPprofesional other = (CmEspPprofesional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CmEspPprofesional[ id=" + id + " ]";
    }
    
}
