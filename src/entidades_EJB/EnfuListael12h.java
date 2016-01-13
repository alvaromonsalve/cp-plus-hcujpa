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
@Table(name = "enfu_listael12h")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuListael12h.findAll", query = "SELECT e FROM EnfuListael12h e"),
    @NamedQuery(name = "EnfuListael12h.findById", query = "SELECT e FROM EnfuListael12h e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuListael12h.findByIdControles", query = "SELECT e FROM EnfuListael12h e WHERE e.idControles = :idControles"),
    @NamedQuery(name = "EnfuListael12h.findByFecha", query = "SELECT e FROM EnfuListael12h e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuListael12h.findByHora", query = "SELECT e FROM EnfuListael12h e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuListael12h.findByMateriaFecal", query = "SELECT e FROM EnfuListael12h e WHERE e.materiaFecal = :materiaFecal"),
    @NamedQuery(name = "EnfuListael12h.findByOrina", query = "SELECT e FROM EnfuListael12h e WHERE e.orina = :orina"),
    @NamedQuery(name = "EnfuListael12h.findByVomito", query = "SELECT e FROM EnfuListael12h e WHERE e.vomito = :vomito"),
    @NamedQuery(name = "EnfuListael12h.findByDrenajes", query = "SELECT e FROM EnfuListael12h e WHERE e.drenajes = :drenajes"),
    @NamedQuery(name = "EnfuListael12h.findByUsr", query = "SELECT e FROM EnfuListael12h e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuListael12h.findByEstado", query = "SELECT e FROM EnfuListael12h e WHERE e.estado = :estado")})
public class EnfuListael12h implements Serializable {
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
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "materia_fecal")
    private Double materiaFecal;
    @Column(name = "orina")
    private Double orina;
    @Column(name = "vomito")
    private Double vomito;
    @Column(name = "drenajes")
    private Double drenajes;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;

    public EnfuListael12h() {
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

    public Double getMateriaFecal() {
        return materiaFecal;
    }

    public void setMateriaFecal(Double materiaFecal) {
        this.materiaFecal = materiaFecal;
    }

    public Double getOrina() {
        return orina;
    }

    public void setOrina(Double orina) {
        this.orina = orina;
    }

    public Double getVomito() {
        return vomito;
    }

    public void setVomito(Double vomito) {
        this.vomito = vomito;
    }

    public Double getDrenajes() {
        return drenajes;
    }

    public void setDrenajes(Double drenajes) {
        this.drenajes = drenajes;
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
