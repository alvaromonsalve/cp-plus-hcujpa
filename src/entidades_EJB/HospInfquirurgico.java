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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "hosp_infquirurgico")
@NamedQueries({
    @NamedQuery(name = "HospInfquirurgico.findAll", query = "SELECT h FROM HospInfquirurgico h")})
public class HospInfquirurgico implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquirurgico", fetch = FetchType.LAZY)
    private List<HospInfquirurgicoSoat2> hospInfquirurgicoSoat2List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinforme", fetch = FetchType.LAZY)
    private List<HospInfquirurgicoSoat1> hospInfquirurgicoSoat1List;

    @Basic(optional = false)
    @Column(name = "fechadigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadigita;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tiporegistro")
    private int tiporegistro;
    @Basic(optional = false)
    @Column(name = "idregistro")
    private int idregistro;
    @Basic(optional = false)
    @Column(name = "idcirujano")
    private int idcirujano;
    @Lob
    @Column(name = "anestesiologo")
    private String anestesiologo;
    @Lob
    @Column(name = "instrumentador")
    private String instrumentador;
    @Lob
    @Column(name = "ayudante")
    private String ayudante;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "horainicial")
    @Temporal(TemporalType.TIME)
    private Date horainicial;
    @Basic(optional = false)
    @Column(name = "horafinal")
    @Temporal(TemporalType.TIME)
    private Date horafinal;
    @Lob
    @Column(name = "tipoanestesia")
    private String tipoanestesia;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquirurgico", fetch = FetchType.LAZY)
    private List<HospInfquirurgicoCups> hospInfquirurgicoCupsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinfquirurgico", fetch = FetchType.LAZY)
    private List<HospInfquirurgicoDxpost> hospInfquirurgicoDxpostList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinfquirurgico", fetch = FetchType.LAZY)
    private List<HospInfquirurgicoDxpre> hospInfquirurgicoDxpreList;

    public HospInfquirurgico() {
    }

    public HospInfquirurgico(Integer id) {
        this.id = id;
    }

    public HospInfquirurgico(Integer id, int tiporegistro, int idregistro, int idcirujano, Date fecha, Date horainicial, Date horafinal, String descripcion, int estado) {
        this.id = id;
        this.tiporegistro = tiporegistro;
        this.idregistro = idregistro;
        this.idcirujano = idcirujano;
        this.fecha = fecha;
        this.horainicial = horainicial;
        this.horafinal = horafinal;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTiporegistro() {
        return tiporegistro;
    }

    public void setTiporegistro(int tiporegistro) {
        this.tiporegistro = tiporegistro;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public int getIdcirujano() {
        return idcirujano;
    }

    public void setIdcirujano(int idcirujano) {
        this.idcirujano = idcirujano;
    }

    public String getAnestesiologo() {
        return anestesiologo;
    }

    public void setAnestesiologo(String anestesiologo) {
        this.anestesiologo = anestesiologo;
    }

    public String getInstrumentador() {
        return instrumentador;
    }

    public void setInstrumentador(String instrumentador) {
        this.instrumentador = instrumentador;
    }

    public String getAyudante() {
        return ayudante;
    }

    public void setAyudante(String ayudante) {
        this.ayudante = ayudante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorainicial() {
        return horainicial;
    }

    public void setHorainicial(Date horainicial) {
        this.horainicial = horainicial;
    }

    public Date getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(Date horafinal) {
        this.horafinal = horafinal;
    }

    public String getTipoanestesia() {
        return tipoanestesia;
    }

    public void setTipoanestesia(String tipoanestesia) {
        this.tipoanestesia = tipoanestesia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<HospInfquirurgicoCups> getHospInfquirurgicoCupsList() {
        return hospInfquirurgicoCupsList;
    }

    public void setHospInfquirurgicoCupsList(List<HospInfquirurgicoCups> hospInfquirurgicoCupsList) {
        this.hospInfquirurgicoCupsList = hospInfquirurgicoCupsList;
    }

    public List<HospInfquirurgicoDxpost> getHospInfquirurgicoDxpostList() {
        return hospInfquirurgicoDxpostList;
    }

    public void setHospInfquirurgicoDxpostList(List<HospInfquirurgicoDxpost> hospInfquirurgicoDxpostList) {
        this.hospInfquirurgicoDxpostList = hospInfquirurgicoDxpostList;
    }

    public List<HospInfquirurgicoDxpre> getHospInfquirurgicoDxpreList() {
        return hospInfquirurgicoDxpreList;
    }

    public void setHospInfquirurgicoDxpreList(List<HospInfquirurgicoDxpre> hospInfquirurgicoDxpreList) {
        this.hospInfquirurgicoDxpreList = hospInfquirurgicoDxpreList;
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
        if (!(object instanceof HospInfquirurgico)) {
            return false;
        }
        HospInfquirurgico other = (HospInfquirurgico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.HospInfquirurgico[ id=" + id + " ]";
    }

    public Date getFechadigita() {
        return fechadigita;
    }

    public void setFechadigita(Date fechadigita) {
        this.fechadigita = fechadigita;
    }

    public List<HospInfquirurgicoSoat2> getHospInfquirurgicoSoat2List() {
        return hospInfquirurgicoSoat2List;
    }

    public void setHospInfquirurgicoSoat2List(List<HospInfquirurgicoSoat2> hospInfquirurgicoSoat2List) {
        this.hospInfquirurgicoSoat2List = hospInfquirurgicoSoat2List;
    }

    public List<HospInfquirurgicoSoat1> getHospInfquirurgicoSoat1List() {
        return hospInfquirurgicoSoat1List;
    }

    public void setHospInfquirurgicoSoat1List(List<HospInfquirurgicoSoat1> hospInfquirurgicoSoat1List) {
        this.hospInfquirurgicoSoat1List = hospInfquirurgicoSoat1List;
    }

}
