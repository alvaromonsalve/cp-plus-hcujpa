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
@Table(name = "uci_anexo3")
@NamedQueries({
    @NamedQuery(name = "UciAnexo3.findAll", query = "SELECT u FROM UciAnexo3 u")})
public class UciAnexo3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "cama")
    private String cama;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private int prioridad;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fechadigita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadigita;
    @JoinColumn(name = "idevolucion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UciEvolucion idUciEvolucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<UciAnexo3Procedimientos> uciAnexo3ProcedimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanexo")
    private List<UciAnexo3Cie10> uciAnexo3Cie10List;

    public UciAnexo3() {
    }

    public UciAnexo3(Integer id) {
        this.id = id;
    }

    public UciAnexo3(Integer id, String justificacion, int prioridad, int estado, Date fechadigita) {
        this.id = id;
        this.justificacion = justificacion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechadigita = fechadigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechadigita() {
        return fechadigita;
    }

    public void setFechadigita(Date fechadigita) {
        this.fechadigita = fechadigita;
    }

    public UciEvolucion getIdUciEvolucion() {
        return idUciEvolucion;
    }

    public void setIdUciEvolucion(UciEvolucion idUciEvolucion) {
        this.idUciEvolucion = idUciEvolucion;
    }

    public List<UciAnexo3Procedimientos> getUciAnexo3ProcedimientosList() {
        return uciAnexo3ProcedimientosList;
    }

    public void setUciAnexo3ProcedimientosList(List<UciAnexo3Procedimientos> uciAnexo3ProcedimientosList) {
        this.uciAnexo3ProcedimientosList = uciAnexo3ProcedimientosList;
    }

    public List<UciAnexo3Cie10> getUciAnexo3Cie10List() {
        return uciAnexo3Cie10List;
    }

    public void setUciAnexo3Cie10List(List<UciAnexo3Cie10> uciAnexo3Cie10List) {
        this.uciAnexo3Cie10List = uciAnexo3Cie10List;
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
        if (!(object instanceof UciAnexo3)) {
            return false;
        }
        UciAnexo3 other = (UciAnexo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.UciAnexo3[ id=" + id + " ]";
    }
    
}
