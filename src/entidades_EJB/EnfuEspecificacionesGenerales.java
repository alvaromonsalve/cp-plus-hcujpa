/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "enfu_especificaciones_generales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuEspecificacionesGenerales.findAll", query = "SELECT e FROM EnfuEspecificacionesGenerales e"),
    @NamedQuery(name = "EnfuEspecificacionesGenerales.findById", query = "SELECT e FROM EnfuEspecificacionesGenerales e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuEspecificacionesGenerales.findByEstadoP", query = "SELECT e FROM EnfuEspecificacionesGenerales e WHERE e.estadoP = :estadoP"),
    @NamedQuery(name = "EnfuEspecificacionesGenerales.findByOrientado", query = "SELECT e FROM EnfuEspecificacionesGenerales e WHERE e.orientado = :orientado"),
    @NamedQuery(name = "EnfuEspecificacionesGenerales.findByEstado", query = "SELECT e FROM EnfuEspecificacionesGenerales e WHERE e.estado = :estado")})
public class EnfuEspecificacionesGenerales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado_p")
    private Character estadoP;
    @Column(name = "orientado")
    private Character orientado;
    @Lob
    @Column(name = "otros_estados")
    private String otrosEstados;
    @Lob
    @Column(name = "exploracion")
    private String exploracion;
    @Lob
    @Column(name = "comentario_paciente")
    private String comentarioPaciente;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_facts_notas", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnfuFactsNotas idFactsNotas;

    public EnfuEspecificacionesGenerales() {
    }

    public EnfuEspecificacionesGenerales(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getEstadoP() {
        return estadoP;
    }

    public void setEstadoP(Character estadoP) {
        this.estadoP = estadoP;
    }

    public Character getOrientado() {
        return orientado;
    }

    public void setOrientado(Character orientado) {
        this.orientado = orientado;
    }

    public String getOtrosEstados() {
        return otrosEstados;
    }

    public void setOtrosEstados(String otrosEstados) {
        this.otrosEstados = otrosEstados;
    }

    public String getExploracion() {
        return exploracion;
    }

    public void setExploracion(String exploracion) {
        this.exploracion = exploracion;
    }

    public String getComentarioPaciente() {
        return comentarioPaciente;
    }

    public void setComentarioPaciente(String comentarioPaciente) {
        this.comentarioPaciente = comentarioPaciente;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public EnfuFactsNotas getIdFactsNotas() {
        return idFactsNotas;
    }

    public void setIdFactsNotas(EnfuFactsNotas idFactsNotas) {
        this.idFactsNotas = idFactsNotas;
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
        if (!(object instanceof EnfuEspecificacionesGenerales)) {
            return false;
        }
        EnfuEspecificacionesGenerales other = (EnfuEspecificacionesGenerales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuEspecificacionesGenerales[ id=" + id + " ]";
    }
    
}
