/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hosp_evo_interconsulta")
@NamedQueries({
    @NamedQuery(name = "HospEvoInterconsulta.findAll", query = "SELECT h FROM HospEvoInterconsulta h")})
public class HospEvoInterconsulta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hosp_evolucion",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private HospEvolucion idHospEvolucion;
    @JoinColumn(name = "id_static_especialidades",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private StaticEspecialidades idStaticEspecialidades;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @JoinColumn(name = "id_config_cups",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private ConfigCups idConfigCups;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;

    public HospEvoInterconsulta() {
    }

    public HospEvoInterconsulta(Integer id) {
        this.id = id;
    }

    public HospEvoInterconsulta(Integer id, HospEvolucion idHospEvolucion, StaticEspecialidades idStaticEspecialidades, ConfigCups idConfigCups, int idUsuario) {
        this.id = id;
        this.idHospEvolucion = idHospEvolucion;
        this.idStaticEspecialidades = idStaticEspecialidades;
        this.idConfigCups = idConfigCups;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HospEvolucion getIdHospEvolucion() {
        return idHospEvolucion;
    }

    public void setIdHospEvolucion(HospEvolucion idHospEvolucion) {
        this.idHospEvolucion = idHospEvolucion;
    }

    public StaticEspecialidades getIdStaticEspecialidades() {
        return idStaticEspecialidades;
    }

    public void setIdStaticEspecialidades(StaticEspecialidades idStaticEspecialidades) {
        this.idStaticEspecialidades = idStaticEspecialidades;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public ConfigCups getIdConfigCups() {
        return idConfigCups;
    }

    public void setIdConfigCups(ConfigCups idConfigCups) {
        this.idConfigCups = idConfigCups;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof HospEvoInterconsulta)) {
            return false;
        }
        HospEvoInterconsulta other = (HospEvoInterconsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HospEvoInterconsulta[ id=" + id + " ]";
    }

}
