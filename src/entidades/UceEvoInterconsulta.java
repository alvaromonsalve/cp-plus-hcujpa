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
@Table(name = "uce_evo_interconsulta")
@NamedQueries({
    @NamedQuery(name = "UceEvoInterconsulta.findAll", query = "SELECT h FROM UceEvoInterconsulta h")})
public class UceEvoInterconsulta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_evolucion",referencedColumnName = "id")
    @ManyToOne(optional=false)
    private UceEvolucion idUceEvolucion;
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

    public UceEvoInterconsulta() {
    }

    public UceEvoInterconsulta(Integer id) {
        this.id = id;
    }

    public UceEvoInterconsulta(Integer id, UceEvolucion idUceEvolucion, StaticEspecialidades idStaticEspecialidades, ConfigCups idConfigCups, int idUsuario) {
        this.id = id;
        this.idUceEvolucion = idUceEvolucion;
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

    public UceEvolucion getIdUceEvolucion() {
        return idUceEvolucion;
    }

    public void setIdUceEvolucion(UceEvolucion idUceEvolucion) {
        this.idUceEvolucion = idUceEvolucion;
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
        if (!(object instanceof UceEvoInterconsulta)) {
            return false;
        }
        UceEvoInterconsulta other = (UceEvoInterconsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UceEvoInterconsulta[ id=" + id + " ]";
    }

}
