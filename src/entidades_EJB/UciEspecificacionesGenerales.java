/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author JUDMEZ
 */
@Entity
@Table(name = "uci_especificaciones_generales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospEspecificacionesGenerales.findAll", query = "SELECT h FROM HospEspecificacionesGenerales h"),
    @NamedQuery(name = "HospEspecificacionesGenerales.findById", query = "SELECT h FROM HospEspecificacionesGenerales h WHERE h.id = :id"),
    @NamedQuery(name = "HospEspecificacionesGenerales.findByEstadoP", query = "SELECT h FROM HospEspecificacionesGenerales h WHERE h.estadoP = :estadoP"),
    @NamedQuery(name = "HospEspecificacionesGenerales.findByOrientado", query = "SELECT h FROM HospEspecificacionesGenerales h WHERE h.orientado = :orientado"),
    @NamedQuery(name = "HospEspecificacionesGenerales.findByEstado", query = "SELECT h FROM HospEspecificacionesGenerales h WHERE h.estado = :estado")})
public class UciEspecificacionesGenerales implements Serializable {
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
    private UciFactsNotas idFactsNotas;

    public UciEspecificacionesGenerales() {
    }

    public UciEspecificacionesGenerales(Integer id) {
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

    public UciFactsNotas getIdFactsNotas() {
        return idFactsNotas;
    }

    public void setIdFactsNotas(UciFactsNotas idFactsNotas) {
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
        if (!(object instanceof UciEspecificacionesGenerales)) {
            return false;
        }
        UciEspecificacionesGenerales other = (UciEspecificacionesGenerales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospEspecificacionesGenerales[ id=" + id + " ]";
    }
    
}
