/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "enfu_facts_notas")

public class EnfuFactsNotas implements Serializable {
    @Column(name =     "hora")
    private String hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private Collection<EnfuMedSuministrados> enfuMedSuministradosCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private Collection<EnfuGlasgow> enfuGlasgowCollection;
    @JoinColumn(name = "id_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idHistoriac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private Collection<EnfuEspecificacionesGenerales> enfuEspecificacionesGeneralesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private Collection<EnfuSignosVitalesDet> enfuSignosVitalesDetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private Collection<EnfuSignosVitalesMat> enfuSignosVitalesMatCollection;
    @Column(name = "tipo")
    private Integer tipo;
        
    public EnfuFactsNotas() {
    }

    public EnfuFactsNotas(Integer id) {
        this.id = id;
    }

    public EnfuFactsNotas(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    @XmlTransient
    public Collection<EnfuGlasgow> getEnfuGlasgowCollection() {
        return enfuGlasgowCollection;
    }

    public void setEnfuGlasgowCollection(Collection<EnfuGlasgow> enfuGlasgowCollection) {
        this.enfuGlasgowCollection = enfuGlasgowCollection;
    }

    public InfoHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(InfoHistoriac idHistoriac) {
        this.idHistoriac = idHistoriac;
    }

    @XmlTransient
    public Collection<EnfuEspecificacionesGenerales> getEnfuEspecificacionesGeneralesCollection() {
        return enfuEspecificacionesGeneralesCollection;
    }

    public void setEnfuEspecificacionesGeneralesCollection(Collection<EnfuEspecificacionesGenerales> enfuEspecificacionesGeneralesCollection) {
        this.enfuEspecificacionesGeneralesCollection = enfuEspecificacionesGeneralesCollection;
    }

    @XmlTransient
    public Collection<EnfuSignosVitalesDet> getEnfuSignosVitalesDetCollection() {
        return enfuSignosVitalesDetCollection;
    }

    public void setEnfuSignosVitalesDetCollection(Collection<EnfuSignosVitalesDet> enfuSignosVitalesDetCollection) {
        this.enfuSignosVitalesDetCollection = enfuSignosVitalesDetCollection;
    }

    @XmlTransient
    public Collection<EnfuSignosVitalesMat> getEnfuSignosVitalesMatCollection() {
        return enfuSignosVitalesMatCollection;
    }

    public void setEnfuSignosVitalesMatCollection(Collection<EnfuSignosVitalesMat> enfuSignosVitalesMatCollection) {
        this.enfuSignosVitalesMatCollection = enfuSignosVitalesMatCollection;
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
        if (!(object instanceof EnfuFactsNotas)) {
            return false;
        }
        EnfuFactsNotas other = (EnfuFactsNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.EnfuFactsNotas[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<EnfuMedSuministrados> getEnfuMedSuministradosCollection() {
        return enfuMedSuministradosCollection;
    }

    public void setEnfuMedSuministradosCollection(Collection<EnfuMedSuministrados> enfuMedSuministradosCollection) {
        this.enfuMedSuministradosCollection = enfuMedSuministradosCollection;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
