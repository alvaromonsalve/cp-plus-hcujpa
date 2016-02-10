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
import javax.persistence.Lob;
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
@Table(name = "ce_historiac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeHistoriac.findAll", query = "SELECT c FROM CeHistoriac c"),
    @NamedQuery(name = "CeHistoriac.findById", query = "SELECT c FROM CeHistoriac c WHERE c.id = :id"),
    @NamedQuery(name = "CeHistoriac.findByPrimerVez", query = "SELECT c FROM CeHistoriac c WHERE c.primerVez = :primerVez"),
    @NamedQuery(name = "CeHistoriac.findByCausaExterna", query = "SELECT c FROM CeHistoriac c WHERE c.causaExterna = :causaExterna"),
    @NamedQuery(name = "CeHistoriac.findByRtaVerbal", query = "SELECT c FROM CeHistoriac c WHERE c.rtaVerbal = :rtaVerbal"),
    @NamedQuery(name = "CeHistoriac.findByRtaMotora", query = "SELECT c FROM CeHistoriac c WHERE c.rtaMotora = :rtaMotora"),
    @NamedQuery(name = "CeHistoriac.findByRtaOcular", query = "SELECT c FROM CeHistoriac c WHERE c.rtaOcular = :rtaOcular"),
    @NamedQuery(name = "CeHistoriac.findByHta", query = "SELECT c FROM CeHistoriac c WHERE c.hta = :hta"),
    @NamedQuery(name = "CeHistoriac.findByDm", query = "SELECT c FROM CeHistoriac c WHERE c.dm = :dm"),
    @NamedQuery(name = "CeHistoriac.findByDis", query = "SELECT c FROM CeHistoriac c WHERE c.dis = :dis"),
    @NamedQuery(name = "CeHistoriac.findByTabaco", query = "SELECT c FROM CeHistoriac c WHERE c.tabaco = :tabaco"),
    @NamedQuery(name = "CeHistoriac.findByAlcohol", query = "SELECT c FROM CeHistoriac c WHERE c.alcohol = :alcohol"),
    @NamedQuery(name = "CeHistoriac.findByDrogas", query = "SELECT c FROM CeHistoriac c WHERE c.drogas = :drogas"),
    @NamedQuery(name = "CeHistoriac.findByTiempoConsulta", query = "SELECT c FROM CeHistoriac c WHERE c.tiempoConsulta = :tiempoConsulta"),
    @NamedQuery(name = "CeHistoriac.findByDx1", query = "SELECT c FROM CeHistoriac c WHERE c.dx1 = :dx1"),
    @NamedQuery(name = "CeHistoriac.findByDx2", query = "SELECT c FROM CeHistoriac c WHERE c.dx2 = :dx2"),
    @NamedQuery(name = "CeHistoriac.findByDx3", query = "SELECT c FROM CeHistoriac c WHERE c.dx3 = :dx3"),
    @NamedQuery(name = "CeHistoriac.findByDx4", query = "SELECT c FROM CeHistoriac c WHERE c.dx4 = :dx4"),
    @NamedQuery(name = "CeHistoriac.findByDx5", query = "SELECT c FROM CeHistoriac c WHERE c.dx5 = :dx5"),
    @NamedQuery(name = "CeHistoriac.findByEstado", query = "SELECT c FROM CeHistoriac c WHERE c.estado = :estado"),
    @NamedQuery(name = "CeHistoriac.findByUsr", query = "SELECT c FROM CeHistoriac c WHERE c.usr = :usr"),
    @NamedQuery(name = "CeHistoriac.findByFechaIngresoDatos", query = "SELECT c FROM CeHistoriac c WHERE c.fechaIngresoDatos = :fechaIngresoDatos")})
public class CeHistoriac implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHistoria")
    private List<CeCtc> ceCtcList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "primer_vez")
    private Character primerVez;
    @Column(name = "causa_externa")
    private String causaExterna;
    @Lob
    @Column(name = "motivo_consulta")
    private String motivoConsulta;
    @Lob
    @Column(name = "enfermedad_actual")
    private String enfermedadActual;
    @Column(name = "rta_verbal")
    private Short rtaVerbal;
    @Column(name = "rta_motora")
    private Short rtaMotora;
    @Column(name = "rta_ocular")
    private Short rtaOcular;
    @Lob
    @Column(name = "alergias")
    private String alergias;
    @Lob
    @Column(name = "ingresos_previos")
    private String ingresosPrevios;
    @Lob
    @Column(name = "traumatismos")
    private String traumatismos;
    @Lob
    @Column(name = "tratamientos")
    private String tratamientos;
    @Column(name = "hta")
    private Short hta;
    @Column(name = "dm")
    private Short dm;
    @Column(name = "dis")
    private Short dis;
    @Lob
    @Column(name = "desc_hdd")
    private String descHdd;
    @Column(name = "tabaco")
    private Short tabaco;
    @Column(name = "alcohol")
    private Short alcohol;
    @Column(name = "drogas")
    private Short drogas;
    @Lob
    @Column(name = "otros_habitos")
    private String otrosHabitos;
    @Lob
    @Column(name = "situacion_basal")
    private String situacionBasal;
    @Lob
    @Column(name = "ant_familiares")
    private String antFamiliares;
    @Column(name = "tiempo_consulta")
    @Temporal(TemporalType.TIME)
    private Date tiempoConsulta;
    @Lob
    @Column(name = "analisis")
    private String analisis;
    @Column(name = "dx1")
    private Integer dx1;
    @Column(name = "dx2")
    private Integer dx2;
    @Column(name = "dx3")
    private Integer dx3;
    @Column(name = "dx4")
    private Integer dx4;
    @Column(name = "dx5")
    private Integer dx5;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "usr")
    private Integer usr;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_cita", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CmPrincipalCitas idCita;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHistoria")
    private List<CeAnexo3> ceAnexo3List;

    public CeHistoriac() {
    }

    public CeHistoriac(Integer id) {
        this.id = id;
    }

    public CeHistoriac(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getPrimerVez() {
        return primerVez;
    }

    public void setPrimerVez(Character primerVez) {
        this.primerVez = primerVez;
    }

    public String getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(String causaExterna) {
        this.causaExterna = causaExterna;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getEnfermedadActual() {
        return enfermedadActual;
    }

    public void setEnfermedadActual(String enfermedadActual) {
        this.enfermedadActual = enfermedadActual;
    }

    public Short getRtaVerbal() {
        return rtaVerbal;
    }

    public void setRtaVerbal(Short rtaVerbal) {
        this.rtaVerbal = rtaVerbal;
    }

    public Short getRtaMotora() {
        return rtaMotora;
    }

    public void setRtaMotora(Short rtaMotora) {
        this.rtaMotora = rtaMotora;
    }

    public Short getRtaOcular() {
        return rtaOcular;
    }

    public void setRtaOcular(Short rtaOcular) {
        this.rtaOcular = rtaOcular;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getIngresosPrevios() {
        return ingresosPrevios;
    }

    public void setIngresosPrevios(String ingresosPrevios) {
        this.ingresosPrevios = ingresosPrevios;
    }

    public String getTraumatismos() {
        return traumatismos;
    }

    public void setTraumatismos(String traumatismos) {
        this.traumatismos = traumatismos;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    public Short getHta() {
        return hta;
    }

    public void setHta(Short hta) {
        this.hta = hta;
    }

    public Short getDm() {
        return dm;
    }

    public void setDm(Short dm) {
        this.dm = dm;
    }

    public Short getDis() {
        return dis;
    }

    public void setDis(Short dis) {
        this.dis = dis;
    }

    public String getDescHdd() {
        return descHdd;
    }

    public void setDescHdd(String descHdd) {
        this.descHdd = descHdd;
    }

    public Short getTabaco() {
        return tabaco;
    }

    public void setTabaco(Short tabaco) {
        this.tabaco = tabaco;
    }

    public Short getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Short alcohol) {
        this.alcohol = alcohol;
    }

    public Short getDrogas() {
        return drogas;
    }

    public void setDrogas(Short drogas) {
        this.drogas = drogas;
    }

    public String getOtrosHabitos() {
        return otrosHabitos;
    }

    public void setOtrosHabitos(String otrosHabitos) {
        this.otrosHabitos = otrosHabitos;
    }

    public String getSituacionBasal() {
        return situacionBasal;
    }

    public void setSituacionBasal(String situacionBasal) {
        this.situacionBasal = situacionBasal;
    }

    public String getAntFamiliares() {
        return antFamiliares;
    }

    public void setAntFamiliares(String antFamiliares) {
        this.antFamiliares = antFamiliares;
    }

    public Date getTiempoConsulta() {
        return tiempoConsulta;
    }

    public void setTiempoConsulta(Date tiempoConsulta) {
        this.tiempoConsulta = tiempoConsulta;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public Integer getDx1() {
        return dx1;
    }

    public void setDx1(Integer dx1) {
        this.dx1 = dx1;
    }

    public Integer getDx2() {
        return dx2;
    }

    public void setDx2(Integer dx2) {
        this.dx2 = dx2;
    }

    public Integer getDx3() {
        return dx3;
    }

    public void setDx3(Integer dx3) {
        this.dx3 = dx3;
    }

    public Integer getDx4() {
        return dx4;
    }

    public void setDx4(Integer dx4) {
        this.dx4 = dx4;
    }

    public Integer getDx5() {
        return dx5;
    }

    public void setDx5(Integer dx5) {
        this.dx5 = dx5;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public CmPrincipalCitas getIdCita() {
        return idCita;
    }

    public void setIdCita(CmPrincipalCitas idCita) {
        this.idCita = idCita;
    }

    @XmlTransient
    public List<CeAnexo3> getCeAnexo3List() {
        return ceAnexo3List;
    }

    public void setCeAnexo3List(List<CeAnexo3> ceAnexo3List) {
        this.ceAnexo3List = ceAnexo3List;
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
        if (!(object instanceof CeHistoriac)) {
            return false;
        }
        CeHistoriac other = (CeHistoriac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.CeHistoriac[ id=" + id + " ]";
    }

    @XmlTransient
    public List<CeCtc> getCeCtcList() {
        return ceCtcList;
    }

    public void setCeCtcList(List<CeCtc> ceCtcList) {
        this.ceCtcList = ceCtcList;
    }
    
}
