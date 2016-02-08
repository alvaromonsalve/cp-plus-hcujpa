/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_exp_ginecologica_eco")
public class CeExpGinecologicaEco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fum")
    @Temporal(TemporalType.DATE)
    private Date fum;
    @Column(name = "segura")
    private Character segura;
    @Lob
    @Column(name = "motivo_examen")
    private String motivoExamen;
    @Column(name = "utero")
    private String utero;
    @Column(name = "dl")
    private String dl;
    @Column(name = "dap")
    private String dap;
    @Column(name = "dt")
    private String dt;
    @Lob
    @Column(name = "transv")
    private String transv;
    @Lob
    @Column(name = "endomet")
    private String endomet;
    @Lob
    @Column(name = "anex")
    private String anex;
    @Column(name = "douglas")
    private String douglas;
    @Lob
    @Column(name = "observ")
    private String observ;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoria;
    @Column(name = "semameno")
    private int semameno;
    
    public CeExpGinecologicaEco() {
    }

    public CeExpGinecologicaEco(Integer id) {
        this.id = id;
    }

    public CeExpGinecologicaEco(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFum() {
        return fum;
    }

    public void setFum(Date fum) {
        this.fum = fum;
    }

    public Character getSegura() {
        return segura;
    }

    public void setSegura(Character segura) {
        this.segura = segura;
    }

    public String getMotivoExamen() {
        return motivoExamen;
    }

    public void setMotivoExamen(String motivoExamen) {
        this.motivoExamen = motivoExamen;
    }

    public String getUtero() {
        return utero;
    }

    public void setUtero(String utero) {
        this.utero = utero;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getDap() {
        return dap;
    }

    public void setDap(String dap) {
        this.dap = dap;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getTransv() {
        return transv;
    }

    public void setTransv(String transv) {
        this.transv = transv;
    }

    public String getEndomet() {
        return endomet;
    }

    public void setEndomet(String endomet) {
        this.endomet = endomet;
    }

    public String getAnex() {
        return anex;
    }

    public void setAnex(String anex) {
        this.anex = anex;
    }

    public String getDouglas() {
        return douglas;
    }

    public void setDouglas(String douglas) {
        this.douglas = douglas;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public CeHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(CeHistoriac idHistoria) {
        this.idHistoria = idHistoria;
    }

    public int getSemameno() {
        return semameno;
    }

    public void setSemameno(int semameno) {
        this.semameno = semameno;
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
        if (!(object instanceof CeExpGinecologicaEco)) {
            return false;
        }
        CeExpGinecologicaEco other = (CeExpGinecologicaEco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeExpGinecologicaEco[ id=" + id + " ]";
    }
    
}
