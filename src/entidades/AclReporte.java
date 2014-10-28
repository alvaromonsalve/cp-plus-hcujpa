/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "acl_reporte")
@NamedQueries({
    @NamedQuery(name = "AclReporte.findAll", query = "SELECT a FROM AclReporte a")})
public class AclReporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_acl_emple", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AclEmpleados idAclEmple;
    @Column(name = "arl")
    private Integer arl;
    @JoinColumn(name = "eps", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfoEntidades infoEntidades;
    @Column(name = "fecha_acl")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcl;
    @Column(name = "tipo_acl")
    private String tipoAcl;
    @Column(name = "parte_cuerpo")
    private String parteCuerpo;
    @Column(name = "tipo_les")
    private String tipoLes;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "radicado")
    private String radicado;
    @Column(name = "autoriza")
    private String autoriza;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "causa")
    private String causa;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;
    @Basic(optional = false)
    @Column(name = "user_data")
    private int userData;

    public AclReporte() {
    }

    public AclReporte(Integer id) {
        this.id = id;
    }

    public AclReporte(Integer id, AclEmpleados idAclEmple, int estado, Date fDigita, int userData) {
        this.id = id;
        this.idAclEmple = idAclEmple;
        this.estado = estado;
        this.fDigita = fDigita;
        this.userData = userData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AclEmpleados getIdAclEmple() {
        return idAclEmple;
    }

    public void setIdAclEmple(AclEmpleados idAclEmple) {
        this.idAclEmple = idAclEmple;
    }

    public Integer getArl() {
        return arl;
    }

    public void setArl(Integer arl) {
        this.arl = arl;
    }

    public InfoEntidades getInfoEntidades() {
        return infoEntidades;
    }

    public void setInfoEntidades(InfoEntidades infoEntidades) {
        this.infoEntidades = infoEntidades;
    }

    public Date getFechaAcl() {
        return fechaAcl;
    }

    public void setFechaAcl(Date fechaAcl) {
        this.fechaAcl = fechaAcl;
    }

    public String getTipoAcl() {
        return tipoAcl;
    }

    public void setTipoAcl(String tipoAcl) {
        this.tipoAcl = tipoAcl;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    public String getTipoLes() {
        return tipoLes;
    }

    public void setTipoLes(String tipoLes) {
        this.tipoLes = tipoLes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public String getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(String autoriza) {
        this.autoriza = autoriza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFDigita() {
        return fDigita;
    }

    public void setFDigita(Date fDigita) {
        this.fDigita = fDigita;
    }

    public int getUserData() {
        return userData;
    }

    public void setUserData(int userData) {
        this.userData = userData;
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
        if (!(object instanceof AclReporte)) {
            return false;
        }
        AclReporte other = (AclReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AclReporte[ id=" + id + " ]";
    }

}
