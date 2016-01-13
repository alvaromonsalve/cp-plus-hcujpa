/*
 * To change this template, choose Tools | Templates
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

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "enfu_facts_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfuFactsLiquidos.findAll", query = "SELECT e FROM EnfuFactsLiquidos e"),
    @NamedQuery(name = "EnfuFactsLiquidos.findById", query = "SELECT e FROM EnfuFactsLiquidos e WHERE e.id = :id"),
    @NamedQuery(name = "EnfuFactsLiquidos.findByIdHistoria", query = "SELECT e FROM EnfuFactsLiquidos e WHERE e.idHistoria = :idHistoria"),
    @NamedQuery(name = "EnfuFactsLiquidos.findByFecha", query = "SELECT e FROM EnfuFactsLiquidos e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EnfuFactsLiquidos.findByHora", query = "SELECT e FROM EnfuFactsLiquidos e WHERE e.hora = :hora"),
    @NamedQuery(name = "EnfuFactsLiquidos.findByUsr", query = "SELECT e FROM EnfuFactsLiquidos e WHERE e.usr = :usr"),
    @NamedQuery(name = "EnfuFactsLiquidos.findByEstado", query = "SELECT e FROM EnfuFactsLiquidos e WHERE e.estado = :estado")})
public class EnfuFactsLiquidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InfoHistoriac idHistoria;  
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "usr")
    private Integer usr;
    @Column(name = "estado")
    private Character estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsLiquidos")
    private List<EnfuLiqAdministrados> enfuLiqAdministradosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactsLiquidos")
    private List<EnfuLiqEliminados> enfuLiqEliminadosList;

    public EnfuFactsLiquidos() {
    }

    public EnfuFactsLiquidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InfoHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(InfoHistoriac idHistoria) {
        this.idHistoria = idHistoria;
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

    public List<EnfuLiqAdministrados> getEnfuLiqAdministradosList() {
        return enfuLiqAdministradosList;
    }

    public void setEnfuLiqAdministradosList(List<EnfuLiqAdministrados> enfuLiqAdministradosList) {
        this.enfuLiqAdministradosList = enfuLiqAdministradosList;
    }

    public List<EnfuLiqEliminados> getEnfuLiqEliminadosList() {
        return enfuLiqEliminadosList;
    }

    public void setEnfuLiqEliminadosList(List<EnfuLiqEliminados> enfuLiqEliminadosList) {
        this.enfuLiqEliminadosList = enfuLiqEliminadosList;
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
        if (!(object instanceof EnfuFactsLiquidos)) {
            return false;
        }
        EnfuFactsLiquidos other = (EnfuFactsLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EnfuFactsLiquidos[ id=" + id + " ]";
    }
    
}
