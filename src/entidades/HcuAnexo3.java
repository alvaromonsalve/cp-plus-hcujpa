/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "hcu_anexo3")
@NamedQueries({
    @NamedQuery(name = "HcuAnexo3.findAll", query = "SELECT h FROM HcuAnexo3 h")})
public class HcuAnexo3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name="id_info_historiac")
    private Integer idInfoHistoriac;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "nombre_medico")
    private String nombreMedico;
    @Column(name="idusuario")
    private int idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHcuAnexo3")
    private List<HcuAnexo3Det> hcuAnexo3DetList;
    @Column(name = "estado")
    private Integer estado;

    public HcuAnexo3() {
    }

    public HcuAnexo3(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdInfoHistoriac() {
        return idInfoHistoriac;
    }

    public void setIdInfoHistoriac(Integer idInfoHistoriac) {
        this.idInfoHistoriac = idInfoHistoriac;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }
    
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public List<HcuAnexo3Det> getHcuAnexo3DetList() {
        return hcuAnexo3DetList;
    }

    public void setHcuAnexo3DetList(List<HcuAnexo3Det> hcuAnexo3DetList) {
        this.hcuAnexo3DetList = hcuAnexo3DetList;
    }
    
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
        if (!(object instanceof HcuAnexo3)) {
            return false;
        }
        HcuAnexo3 other = (HcuAnexo3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuAnexo3[ id=" + id + " ]";
    }
    
}
