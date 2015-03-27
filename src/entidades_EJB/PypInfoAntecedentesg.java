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
@Table(name = "pyp_info_antecedentesg")
@NamedQueries({
    @NamedQuery(name = "PypInfoAntecedentesg.findAll", query = "SELECT p FROM PypInfoAntecedentesg p")})
public class PypInfoAntecedentesg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idpaciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoPaciente infoPaciente;
    @Column(name = "FUM")
    @Temporal(TemporalType.DATE)
    private Date fum;
    @Column(name = "Ciclos")
    private String ciclos;
    @Column(name = "gestas")
    private String gestas;
    @Column(name = "partos")
    private String partos;
    @Column(name = "abortos")
    private String abortos;
    @Column(name = "cesareas")
    private String cesareas;
    @Column(name = "vaginales")
    private String vaginales;
    @Column(name = "FUP")
    @Temporal(TemporalType.DATE)
    private Date fup;
    @Column(name = "menarquia")
    private String menarquia;
    @Column(name = "edadinicio")
    private String edadinicio;
    @Column(name = "vidasexualac")
    private Short vidasexualac;
    @Column(name = "relacionescon")
    private Short relacionescon;
    @Column(name = "parejaestable")
    private Short parejaestable;
    @Column(name = "usopreservativo")
    private Short usopreservativo;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public PypInfoAntecedentesg() {
    }

    public PypInfoAntecedentesg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InfoPaciente getInfoPaciente() {
        return infoPaciente;
    }

    public void setInfoPaciente(InfoPaciente infoPaciente) {
        this.infoPaciente = infoPaciente;
    }

    public Date getFum() {
        return fum;
    }

    public void setFum(Date fum) {
        this.fum = fum;
    }

    public String getCiclos() {
        return ciclos;
    }

    public void setCiclos(String ciclos) {
        this.ciclos = ciclos;
    }

    public String getGestas() {
        return gestas;
    }

    public void setGestas(String gestas) {
        this.gestas = gestas;
    }

    public String getPartos() {
        return partos;
    }

    public void setPartos(String partos) {
        this.partos = partos;
    }

    public String getAbortos() {
        return abortos;
    }

    public void setAbortos(String abortos) {
        this.abortos = abortos;
    }

    public String getCesareas() {
        return cesareas;
    }

    public void setCesareas(String cesareas) {
        this.cesareas = cesareas;
    }

    public String getVaginales() {
        return vaginales;
    }

    public void setVaginales(String vaginales) {
        this.vaginales = vaginales;
    }

    public Date getFup() {
        return fup;
    }

    public void setFup(Date fup) {
        this.fup = fup;
    }

    public String getMenarquia() {
        return menarquia;
    }

    public void setMenarquia(String menarquia) {
        this.menarquia = menarquia;
    }

    public String getEdadinicio() {
        return edadinicio;
    }

    public void setEdadinicio(String edadinicio) {
        this.edadinicio = edadinicio;
    }

    public Short getVidasexualac() {
        return vidasexualac;
    }

    public void setVidasexualac(Short vidasexualac) {
        this.vidasexualac = vidasexualac;
    }

    public Short getRelacionescon() {
        return relacionescon;
    }

    public void setRelacionescon(Short relacionescon) {
        this.relacionescon = relacionescon;
    }

    public Short getParejaestable() {
        return parejaestable;
    }

    public void setParejaestable(Short parejaestable) {
        this.parejaestable = parejaestable;
    }

    public Short getUsopreservativo() {
        return usopreservativo;
    }

    public void setUsopreservativo(Short usopreservativo) {
        this.usopreservativo = usopreservativo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof PypInfoAntecedentesg)) {
            return false;
        }
        PypInfoAntecedentesg other = (PypInfoAntecedentesg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PypInfoAntecedentesg[ id=" + id + " ]";
    }

}
