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
@Table(name = "uci_facts_notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UciFactsNotas.findAll", query = "SELECT u FROM UciFactsNotas u"),
    @NamedQuery(name = "UciFactsNotas.findById", query = "SELECT u FROM UciFactsNotas u WHERE u.id = :id"),
    @NamedQuery(name = "UciFactsNotas.findByFecha", query = "SELECT u FROM UciFactsNotas u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UciFactsNotas.findByHora", query = "SELECT u FROM UciFactsNotas u WHERE u.hora = :hora"),
    @NamedQuery(name = "UciFactsNotas.findByUsuario", query = "SELECT u FROM UciFactsNotas u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UciFactsNotas.findByEstado", query = "SELECT u FROM UciFactsNotas u WHERE u.estado = :estado"),
    @NamedQuery(name = "UciFactsNotas.findByFechaIngresoDatos", query = "SELECT u FROM UciFactsNotas u WHERE u.fechaIngresoDatos = :fechaIngresoDatos")})
public class UciFactsNotas implements Serializable {
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
    private String hora;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoDatos;
    @JoinColumn(name = "id_historiac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciHistoriac idHistoriac;

    public UciFactsNotas() {
    }

    public UciFactsNotas(Integer id) {
        this.id = id;
    }

    public UciFactsNotas(Integer id, Date fechaIngresoDatos) {
        this.id = id;
        this.fechaIngresoDatos = fechaIngresoDatos;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaIngresoDatos() {
        return fechaIngresoDatos;
    }

    public void setFechaIngresoDatos(Date fechaIngresoDatos) {
        this.fechaIngresoDatos = fechaIngresoDatos;
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
        if (!(object instanceof UciFactsNotas)) {
            return false;
        }
        UciFactsNotas other = (UciFactsNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UciFactsNotas[ id=" + id + " ]";
    }
    
}
