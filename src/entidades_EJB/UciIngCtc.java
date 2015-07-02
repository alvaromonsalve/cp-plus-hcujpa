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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "uci_ing_ctc")
@NamedQueries({
    @NamedQuery(name = "UciIngCtc.findAll", query = "SELECT u FROM UciIngCtc u")})
public class UciIngCtc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "alternativapos")
    private int alternativapos;
    @Basic(optional = false)
    @Column(name = "respuestaclinica")
    private int respuestaclinica;
    @Lob
    @Column(name = "cualmejoria")
    private String cualmejoria;
    @Basic(optional = false)
    @Column(name = "agotopos")
    private int agotopos;
    @Lob
    @Column(name = "indicacionesnopos")
    private String indicacionesnopos;
    @Column(name = "tiemporespuesta")
    private String tiemporespuesta;
    @Lob
    @Column(name = "efectossecundarios")
    private String efectossecundarios;
    @Basic(optional = false)
    @Column(name = "rinminente")
    private int rinminente;
    @Lob
    @Column(name = "explicacionnopos")
    private String explicacionnopos;
    @Basic(optional = false)
    @Column(name = "idusuario")
    private int idusuario;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fechagenerado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechagenerado;
    @JoinColumn(name = "idhistoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciHistoriac idUciHistoriac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idctc")
    private List<UciIngCtcMedicamento> uciIngCtcMedicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idctc")
    private List<UciIngCtcProcedimientos> uciIngCtcProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idctc")
    private List<UciIngCtcCie10> uciIngCtcCie10List;

    public UciIngCtc() {
    }

    public UciIngCtc(Integer id) {
        this.id = id;
    }

    public UciIngCtc(Integer id, int alternativapos, int respuestaclinica, int agotopos, int rinminente, int idusuario, int estado, Date fechagenerado) {
        this.id = id;
        this.alternativapos = alternativapos;
        this.respuestaclinica = respuestaclinica;
        this.agotopos = agotopos;
        this.rinminente = rinminente;
        this.idusuario = idusuario;
        this.estado = estado;
        this.fechagenerado = fechagenerado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UciHistoriac getIdUciHistoriac() {
        return idUciHistoriac;
    }

    public void setIdUciHistoriac(UciHistoriac idUciHistoriac) {
        this.idUciHistoriac = idUciHistoriac;
    }

    public int getAlternativapos() {
        return alternativapos;
    }

    public void setAlternativapos(int alternativapos) {
        this.alternativapos = alternativapos;
    }

    public int getRespuestaclinica() {
        return respuestaclinica;
    }

    public void setRespuestaclinica(int respuestaclinica) {
        this.respuestaclinica = respuestaclinica;
    }

    public String getCualmejoria() {
        return cualmejoria;
    }

    public void setCualmejoria(String cualmejoria) {
        this.cualmejoria = cualmejoria;
    }

    public int getAgotopos() {
        return agotopos;
    }

    public void setAgotopos(int agotopos) {
        this.agotopos = agotopos;
    }

    public String getIndicacionesnopos() {
        return indicacionesnopos;
    }

    public void setIndicacionesnopos(String indicacionesnopos) {
        this.indicacionesnopos = indicacionesnopos;
    }

    public String getTiemporespuesta() {
        return tiemporespuesta;
    }

    public void setTiemporespuesta(String tiemporespuesta) {
        this.tiemporespuesta = tiemporespuesta;
    }

    public String getEfectossecundarios() {
        return efectossecundarios;
    }

    public void setEfectossecundarios(String efectossecundarios) {
        this.efectossecundarios = efectossecundarios;
    }

    public int getRinminente() {
        return rinminente;
    }

    public void setRinminente(int rinminente) {
        this.rinminente = rinminente;
    }

    public String getExplicacionnopos() {
        return explicacionnopos;
    }

    public void setExplicacionnopos(String explicacionnopos) {
        this.explicacionnopos = explicacionnopos;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechagenerado() {
        return fechagenerado;
    }

    public void setFechagenerado(Date fechagenerado) {
        this.fechagenerado = fechagenerado;
    }

    public List<UciIngCtcMedicamento> getUciIngCtcMedicamentoList() {
        return uciIngCtcMedicamentoList;
    }

    public void setUciIngCtcMedicamentoList(List<UciIngCtcMedicamento> uciIngCtcMedicamentoList) {
        this.uciIngCtcMedicamentoList = uciIngCtcMedicamentoList;
    }

    public List<UciIngCtcProcedimientos> getUciIngCtcProcedimientosList() {
        return uciIngCtcProcedimientosList;
    }

    public void setUciIngCtcProcedimientosList(List<UciIngCtcProcedimientos> uciIngCtcProcedimientosList) {
        this.uciIngCtcProcedimientosList = uciIngCtcProcedimientosList;
    }

    public List<UciIngCtcCie10> getUciIngCtcCie10List() {
        return uciIngCtcCie10List;
    }

    public void setUciIngCtcCie10List(List<UciIngCtcCie10> uciIngCtcCie10List) {
        this.uciIngCtcCie10List = uciIngCtcCie10List;
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
        if (!(object instanceof UciIngCtc)) {
            return false;
        }
        UciIngCtc other = (UciIngCtc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciIngCtc[ id=" + id + " ]";
    }
    
}
