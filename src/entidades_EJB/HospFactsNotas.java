/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
 * @author JUDMEZ
 */
@Entity
@Table(name = "hosp_facts_notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospFactsNotas.findAll", query = "SELECT h FROM HospFactsNotas h"),
    @NamedQuery(name = "HospFactsNotas.findById", query = "SELECT h FROM HospFactsNotas h WHERE h.id = :id"),
    @NamedQuery(name = "HospFactsNotas.findByFecha", query = "SELECT h FROM HospFactsNotas h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HospFactsNotas.findByHora", query = "SELECT h FROM HospFactsNotas h WHERE h.hora = :hora"),
    @NamedQuery(name = "HospFactsNotas.findByUsuario", query = "SELECT h FROM HospFactsNotas h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HospFactsNotas.findByEstado", query = "SELECT h FROM HospFactsNotas h WHERE h.estado = :estado"),
    @NamedQuery(name = "HospFactsNotas.findByFechaIngresoDatos", query = "SELECT h FROM HospFactsNotas h WHERE h.fechaIngresoDatos = :fechaIngresoDatos")})
public class HospFactsNotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    private String hora;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private List<HospEspecificacionesGenerales> hospEspecificacionesGeneralesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private List<HospMedSuministrados> hospMedSuministradosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private List<HospSignosVitalesDet> hospSignosVitalesDetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsNotas")
    private List<HospGlasgow> hospGlasgowList;
    @JoinColumn(name = "id_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospHistoriac idHistoriac;

    public HospFactsNotas() {
    }

    public HospFactsNotas(Integer id) {
        this.id = id;
    }

    public HospFactsNotas(Integer id, Date fechaIngresoDatos) {
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    @XmlTransient
    public List<HospEspecificacionesGenerales> getHospEspecificacionesGeneralesList() {
        return hospEspecificacionesGeneralesList;
    }

    public void setHospEspecificacionesGeneralesList(List<HospEspecificacionesGenerales> hospEspecificacionesGeneralesList) {
        this.hospEspecificacionesGeneralesList = hospEspecificacionesGeneralesList;
    }

    @XmlTransient
    public List<HospMedSuministrados> getHospMedSuministradosList() {
        return hospMedSuministradosList;
    }

    public void setHospMedSuministradosList(List<HospMedSuministrados> hospMedSuministradosList) {
        this.hospMedSuministradosList = hospMedSuministradosList;
    }

    @XmlTransient
    public List<HospSignosVitalesDet> getHospSignosVitalesDetList() {
        return hospSignosVitalesDetList;
    }

    public void setHospSignosVitalesDetList(List<HospSignosVitalesDet> hospSignosVitalesDetList) {
        this.hospSignosVitalesDetList = hospSignosVitalesDetList;
    }

    @XmlTransient
    public List<HospGlasgow> getHospGlasgowList() {
        return hospGlasgowList;
    }

    public void setHospGlasgowList(List<HospGlasgow> hospGlasgowList) {
        this.hospGlasgowList = hospGlasgowList;
    }

    public HospHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(HospHistoriac idHistoriac) {
        this.idHistoriac = idHistoriac;
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
        if (!(object instanceof HospFactsNotas)) {
            return false;
        }
        HospFactsNotas other = (HospFactsNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntidadesHosp.HospFactsNotas[ id=" + id + " ]";
    }
    
}
