package entidades_EJB;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tools.MyDate_IN_ejb;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_historiac")
@NamedQueries({
    @NamedQuery(name = "InfoHistoriac.findAll", query = "SELECT i FROM InfoHistoriac i")})
public class InfoHistoriac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "motivo_consulta")
    private String motivoConsulta;
    @Column(name = "causa_externa")
    private String causaExterna;
    @Column(name = "alergias")
    private String alergias;
    @Column(name = "ingresos_previos")
    private String ingresosPrevios;
    @Column(name = "traumatismos")
    private String traumatismos;
    @Column(name = "tratamientos")
    private String tratamientos;
    @Column(name = "desc_hdd")
    private String descHdd;
    @Column(name = "otros_habitos")
    private String otrosHabitos;
    @Column(name = "situacion_basal")
    private String situacionBasal;
    @Lob
    @Column(name = "ant_familiares")
    private String antFamiliar;
    @Lob
    @Column(name = "enfermedad_actual")
    private String enfermedadActual;
    @Column(name = "tiempo_consulta")
    private Long tiempoConsulta;
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_dato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDato;
    @Column(name = "dm")
    private Boolean dm;
    @Column(name = "hta")
    private Boolean hta;
    @Column(name = "dislipidemia")
    private Boolean dislipidemia;
    @Column(name = "tabaco")
    private Boolean tabaco;
    @Column(name = "alcohol")
    private Boolean alcohol;
    @Column(name = "droga")
    private Boolean droga;
    @Lob
    @Column(name = "hallazgo")
    private String hallazgo;
    @Column(name = "diagnostico")
    private Integer diagnostico;
    @Column(name = "diagnostico2")
    private Integer diagnostico2;
    @Column(name = "diagnostico3")
    private Integer diagnostico3;
    @Column(name = "diagnostico4")
    private Integer diagnostico4;
    @Column(name = "diagnostico5")
    private Integer diagnostico5;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idInfohistoriac")
    private InfoHcExpfisica infoHcExpfisica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInfohistoriac", fetch = FetchType.LAZY)
    private List<InfoPruebasComplement> infoPruebasComplements;
    @JoinColumn(name = "id_infoadmision", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoAdmision idInfoAdmision;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Configdecripcionlogin idConfigdecripcionlogin;
    @Column(name = "nivel_triaje")
    private Integer nivelTriaje;
    @Column(name = "tipo_hc")
    private int tipoHc;
    @Column(name = "destino")
    private String destino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInfoHistoriac", fetch = FetchType.LAZY)
    private List<InfoCamas> infoCamasList;

    public InfoHistoriac() {
    }

    public InfoHistoriac(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(String hallazgo) {
        this.hallazgo = hallazgo;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(String causaExterna) {
        this.causaExterna = causaExterna;
    }

    public Integer getNivelTriaje() {
        return nivelTriaje;
    }

    public void setNivelTriaje(Integer nivelTriaje) {
        this.nivelTriaje = nivelTriaje;
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

    public String getDescHdd() {
        return descHdd;
    }

    public void setDescHdd(String descHdd) {
        this.descHdd = descHdd;
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

    public String getAntFamiliar() {
        return antFamiliar;
    }

    public void setAntFamiliar(String antFamiliar) {
        this.antFamiliar = antFamiliar;
    }

    public String getEnfermedadActual() {
        return enfermedadActual;
    }

    public void setEnfermedadActual(String enfermedadActual) {
        this.enfermedadActual = enfermedadActual;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public InfoAdmision getIdInfoAdmision() {
        return idInfoAdmision;
    }

    public void setIdInfoAdmision(InfoAdmision idInfoAdmision) {
        this.idInfoAdmision = idInfoAdmision;
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
        if (!(object instanceof InfoHistoriac)) {
            return false;
        }
        InfoHistoriac other = (InfoHistoriac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return MyDate_IN_ejb.HHmm.format(getFechaDato());
    }

    public Boolean getDm() {
        return dm;
    }

    public void setDm(Boolean dm) {
        this.dm = dm;
    }

    public Boolean getHta() {
        return hta;
    }

    public void setHta(Boolean hta) {
        this.hta = hta;
    }

    public Boolean getDislipidemia() {
        return dislipidemia;
    }

    public void setDislipidemia(Boolean dislipidemia) {
        this.dislipidemia = dislipidemia;
    }

    public Boolean getTabaco() {
        return tabaco;
    }

    public void setTabaco(Boolean tabaco) {
        this.tabaco = tabaco;
    }

    public Boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Boolean getDroga() {
        return droga;
    }

    public void setDroga(Boolean droga) {
        this.droga = droga;
    }

    public Long getTiempoConsulta() {
        return tiempoConsulta;
    }

    public void setTiempoConsulta(Long tiempoConsulta) {
        this.tiempoConsulta = tiempoConsulta;
    }

    public int getTipoHc() {
        return tipoHc;
    }

    public void setTipoHc(int tipoHc) {
        this.tipoHc = tipoHc;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaDato() {
        return fechaDato;
    }

    public void setFechaDato(Date fechaDato) {
        this.fechaDato = fechaDato;
    }

    public InfoHcExpfisica getInfoHcExpfisica() {
        return infoHcExpfisica;
    }

    public void setInfoHcExpfisica(InfoHcExpfisica infoHcExpfisica) {
        this.infoHcExpfisica = infoHcExpfisica;
    }

    public Integer getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Integer diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Integer getDiagnostico2() {
        return diagnostico2;
    }

    public void setDiagnostico2(Integer diagnostico2) {
        this.diagnostico2 = diagnostico2;
    }

    public Integer getDiagnostico3() {
        return diagnostico3;
    }

    public void setDiagnostico3(Integer diagnostico3) {
        this.diagnostico3 = diagnostico3;
    }

    public Integer getDiagnostico4() {
        return diagnostico4;
    }

    public void setDiagnostico4(Integer diagnostico4) {
        this.diagnostico4 = diagnostico4;
    }

    public Integer getDiagnostico5() {
        return diagnostico5;
    }

    public void setDiagnostico5(Integer diagnostico5) {
        this.diagnostico5 = diagnostico5;
    }

    public List<InfoPruebasComplement> getInfoPruebasComplements() {
        return infoPruebasComplements;
    }

    public void setInfoPruebasComplements(List<InfoPruebasComplement> infoPruebasComplements) {
        this.infoPruebasComplements = infoPruebasComplements;
    }

    public List<InfoCamas> getInfoCamasList() {
        return infoCamasList;
    }

    public void setInfoCamasList(List<InfoCamas> infoCamasList) {
        this.infoCamasList = infoCamasList;
    }

    public Configdecripcionlogin getIdConfigdecripcionlogin() {
        return idConfigdecripcionlogin;
    }

    public void setIdConfigdecripcionlogin(Configdecripcionlogin idConfigdecripcionlogin) {
        this.idConfigdecripcionlogin = idConfigdecripcionlogin;
    }
}
