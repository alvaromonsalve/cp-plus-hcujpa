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
@Table(name = "uce_facts_liquidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UceFactsLiquidos.findAll", query = "SELECT u FROM UceFactsLiquidos u"),
    @NamedQuery(name = "UceFactsLiquidos.findById", query = "SELECT u FROM UceFactsLiquidos u WHERE u.id = :id"),
    @NamedQuery(name = "UceFactsLiquidos.findByFecha", query = "SELECT u FROM UceFactsLiquidos u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UceFactsLiquidos.findByHora", query = "SELECT u FROM UceFactsLiquidos u WHERE u.hora = :hora"),
    @NamedQuery(name = "UceFactsLiquidos.findByUsr", query = "SELECT u FROM UceFactsLiquidos u WHERE u.usr = :usr"),
    @NamedQuery(name = "UceFactsLiquidos.findByEstado", query = "SELECT u FROM UceFactsLiquidos u WHERE u.estado = :estado")})
public class UceFactsLiquidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @JoinColumn(name = "id_historia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UceHistoriac idHistoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idControlO")
    private List<UceLiquidos> uceLiquidosList;

    public UceFactsLiquidos() {
    }

    public UceFactsLiquidos(Integer id) {
        this.id = id;
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

    public UceHistoriac getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(UceHistoriac idHistoria) {
        this.idHistoria = idHistoria;
    }

    @XmlTransient
    public List<UceLiquidos> getUceLiquidosList() {
        return uceLiquidosList;
    }

    public void setUceLiquidosList(List<UceLiquidos> uceLiquidosList) {
        this.uceLiquidosList = uceLiquidosList;
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
        if (!(object instanceof UceFactsLiquidos)) {
            return false;
        }
        UceFactsLiquidos other = (UceFactsLiquidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UceFactsLiquidos[ id=" + id + " ]";
    }
    
}
