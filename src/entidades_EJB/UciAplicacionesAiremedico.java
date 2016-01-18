/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "uci_aplicaciones_airemedico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciAplicacionesAiremedico.findAll", query = "SELECT u FROM UciAplicacionesAiremedico u"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findById", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.id = :id"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByFecha", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByHoraInicio", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.horaInicio = :horaInicio"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByMedico", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.medico = :medico"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findBySisO2", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.sisO2 = :sisO2"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByFiO2", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.fiO2 = :fiO2"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByLts", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.lts = :lts"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByUsr", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.usr = :usr"),
    @NamedQuery(name = "UciAplicacionesAiremedico.findByEstado", query = "SELECT u FROM UciAplicacionesAiremedico u WHERE u.estado = :estado")})
public class UciAplicacionesAiremedico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_inicio")
    private String horaInicio;
    @Basic(optional = false)
    @Column(name = "medico")
    private int medico;
    @Column(name = "sis_O2")
    private Integer sisO2;
    @Column(name = "fiO2")
    private Integer fiO2;
    @Column(name = "lts")
    private Integer lts;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciHistoriac idHistoriac;

    public UciAplicacionesAiremedico() {
    }

    public UciAplicacionesAiremedico(Integer id) {
        this.id = id;
    }

    public UciAplicacionesAiremedico(Integer id, int medico) {
        this.id = id;
        this.medico = medico;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public Integer getSisO2() {
        return sisO2;
    }

    public void setSisO2(Integer sisO2) {
        this.sisO2 = sisO2;
    }

    public Integer getFiO2() {
        return fiO2;
    }

    public void setFiO2(Integer fiO2) {
        this.fiO2 = fiO2;
    }

    public Integer getLts() {
        return lts;
    }

    public void setLts(Integer lts) {
        this.lts = lts;
    }

    public Integer getUsr() {
        return usr;
    }

    public void setUsr(Integer usr) {
        this.usr = usr;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public UciHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(UciHistoriac idHistoriac) {
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
        if (!(object instanceof UciAplicacionesAiremedico)) {
            return false;
        }
        UciAplicacionesAiremedico other = (UciAplicacionesAiremedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UciAplicacionesAiremedico[ id=" + id + " ]";
    }
    
}
