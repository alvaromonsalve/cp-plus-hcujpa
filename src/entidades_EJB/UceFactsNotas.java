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
@Table(name = "uce_facts_notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UceFactsNotas.findAll", query = "SELECT u FROM UceFactsNotas u"),
    @NamedQuery(name = "UceFactsNotas.findById", query = "SELECT u FROM UceFactsNotas u WHERE u.id = :id"),
    @NamedQuery(name = "UceFactsNotas.findByFecha", query = "SELECT u FROM UceFactsNotas u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UceFactsNotas.findByHora", query = "SELECT u FROM UceFactsNotas u WHERE u.hora = :hora"),
    @NamedQuery(name = "UceFactsNotas.findByUsuario", query = "SELECT u FROM UceFactsNotas u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UceFactsNotas.findByEstado", query = "SELECT u FROM UceFactsNotas u WHERE u.estado = :estado"),
    @NamedQuery(name = "UceFactsNotas.findByFechaIngresoDatos", query = "SELECT u FROM UceFactsNotas u WHERE u.fechaIngresoDatos = :fechaIngresoDatos")})
public class UceFactsNotas implements Serializable {
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
    private UceHistoriac idHistoriac;

    public UceFactsNotas() {
    }

    public UceFactsNotas(Integer id) {
        this.id = id;
    }

    public UceFactsNotas(Integer id, Date fechaIngresoDatos) {
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

    public UceHistoriac getIdHistoriac() {
        return idHistoriac;
    }

    public void setIdHistoriac(UceHistoriac idHistoriac) {
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
        if (!(object instanceof UceFactsNotas)) {
            return false;
        }
        UceFactsNotas other = (UceFactsNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Rep.UceFactsNotas[ id=" + id + " ]";
    }
    
}
