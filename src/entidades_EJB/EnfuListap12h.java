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
import javax.persistence.Id;
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
@Table(name = "enfu_listap12h")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuListap12h.findAll", query = "SELECT e FROM EnfuListap12h e"),
    @NamedQuery(name = "EnfuListap12h.findById", query = "SELECT e FROM EnfuListap12h e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuListap12h.findByIdControles", query = "SELECT e FROM EnfuListap12h e WHERE e.idControles = :idControles"),
    @NamedQuery(name = "EnfuListap12h.findByFecha", query = "SELECT e FROM EnfuListap12h e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuListap12h.findByHora", query = "SELECT e FROM EnfuListap12h e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuListap12h.findByCantAdm", query = "SELECT e FROM EnfuListap12h e WHERE e.cantAdm = :cantAdm"),
    @NamedQuery(name = "EnfuListap12h.findBySonda", query = "SELECT e FROM EnfuListap12h e WHERE e.sonda = :sonda"),
    @NamedQuery(name = "EnfuListap12h.findByOral", query = "SELECT e FROM EnfuListap12h e WHERE e.oral = :oral"),
    @NamedQuery(name = "EnfuListap12h.findByParenteral", query = "SELECT e FROM EnfuListap12h e WHERE e.parenteral = :parenteral"),
    @NamedQuery(name = "EnfuListap12h.findByUsr", query = "SELECT e FROM EnfuListap12h e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuListap12h.findByEstado", query = "SELECT e FROM EnfuListap12h e WHERE e.estado = :estado")})
public class EnfuListap12h implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id")
    @Id
    private int id;
    @Basic(optional = false)
    @Column(name = "id_controles")
    private int idControles;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cant_adm")
    private Double cantAdm;
    @Column(name = "sonda")
    private Double sonda;
    @Column(name = "oral")
    private Double oral;
    @Column(name = "parenteral")
    private Double parenteral;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;

    public EnfuListap12h() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdControles() {
        return idControles;
    }

    public void setIdControles(int idControles) {
        this.idControles = idControles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Double getCantAdm() {
        return cantAdm;
    }

    public void setCantAdm(Double cantAdm) {
        this.cantAdm = cantAdm;
    }

    public Double getSonda() {
        return sonda;
    }

    public void setSonda(Double sonda) {
        this.sonda = sonda;
    }

    public Double getOral() {
        return oral;
    }

    public void setOral(Double oral) {
        this.oral = oral;
    }

    public Double getParenteral() {
        return parenteral;
    }

    public void setParenteral(Double parenteral) {
        this.parenteral = parenteral;
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
    
}
