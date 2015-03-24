package entidades;

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
@Table(name = "uce_evo_egreso")
@NamedQueries({
    @NamedQuery(name = "UceEvoEgreso.findAll", query = "SELECT h FROM UceEvoEgreso h")})
public class UceEvoEgreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_evolucion", referencedColumnName = "id")
    @ManyToOne
    private UceEvolucion idUceEvolucion;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "destino")
    private Integer destino;//incapacidad_init
    @Column(name = "incapacidad")
    private Integer incapacidad;
    @Column(name = "incapacidad_init")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date incapacidadInit;
    @Column(name = "incapacidad_end")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date incapacidadEnd;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;

    public UceEvoEgreso() {
    }

    public UceEvoEgreso(Integer id) {
        this.id = id;
    }

    public UceEvoEgreso(Integer id, UceEvolucion idUceEvolucion, int usuario, Date fDigita) {
        this.id = id;
        this.idUceEvolucion = idUceEvolucion;
        this.usuario = usuario;
        this.fDigita = fDigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UceEvolucion getIdUceEvolucion() {
        return idUceEvolucion;
    }

    public void setIdUceEvolucion(UceEvolucion idUceEvolucion) {
        this.idUceEvolucion = idUceEvolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public Integer getIncapacidad() {
        return incapacidad;
    }

    public void setIncapacidad(Integer incapacidad) {
        this.incapacidad = incapacidad;
    }

    public Date getIncapacidadInit() {
        return incapacidadInit;
    }

    public void setIncapacidadInit(Date incapacidadInit) {
        this.incapacidadInit = incapacidadInit;
    }

    public Date getIncapacidadEnd() {
        return incapacidadEnd;
    }

    public void setIncapacidadEnd(Date incapacidadEnd) {
        this.incapacidadEnd = incapacidadEnd;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFDigita() {
        return fDigita;
    }

    public void setFDigita(Date fDigita) {
        this.fDigita = fDigita;
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
        if (!(object instanceof UceEvoEgreso)) {
            return false;
        }
        UceEvoEgreso other = (UceEvoEgreso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UceEvoEgreso[ id=" + id + " ]";
    }

}
