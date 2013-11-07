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

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "hcu_evo_mezclas_medicamentos_desc")
@NamedQueries({
    @NamedQuery(name = "HcuEvoMezclasMedicamentosDesc.findAll", query = "SELECT h FROM HcuEvoMezclasMedicamentosDesc h")})
public class HcuEvoMezclasMedicamentosDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_hcu_evo_mezclas_medicamentos",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentos;
    @JoinColumn(name = "id_suministro",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SumSuministro idSuministro;
    @Basic(optional = false)
    @Column(name = "dosis_n")
    private float dosisN;
    @Basic(optional = false)
    @Column(name = "dosis_u")
    private String dosisU;
    @Basic(optional = false)
    @Column(name = "sol_diluir")
    private boolean solDiluir;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @Column(name = "f_digita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDigita;

    public HcuEvoMezclasMedicamentosDesc() {
    }

    public HcuEvoMezclasMedicamentosDesc(Integer id) {
        this.id = id;
    }

    public HcuEvoMezclasMedicamentosDesc(Integer id, HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentos, SumSuministro  idSuministro, float dosisN, String dosisU, boolean solDiluir, int estado, int usuario, Date fDigita) {
        this.id = id;
        this.idHcuEvoMezclasMedicamentos = idHcuEvoMezclasMedicamentos;
        this.idSuministro = idSuministro;
        this.dosisN = dosisN;
        this.dosisU = dosisU;
        this.solDiluir = solDiluir;
        this.estado = estado;
        this.usuario = usuario;
        this.fDigita = fDigita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HcuEvoMezclasMedicamentos getIdHcuEvoMezclasMedicamentos() {
        return idHcuEvoMezclasMedicamentos;
    }

    public void setIdHcuEvoMezclasMedicamentos(HcuEvoMezclasMedicamentos idHcuEvoMezclasMedicamentos) {
        this.idHcuEvoMezclasMedicamentos = idHcuEvoMezclasMedicamentos;
    }

    public SumSuministro  getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(SumSuministro idSuministro) {
        this.idSuministro = idSuministro;
    }

    public float getDosisN() {
        return dosisN;
    }

    public void setDosisN(float dosisN) {
        this.dosisN = dosisN;
    }

    public String getDosisU() {
        return dosisU;
    }

    public void setDosisU(String dosisU) {
        this.dosisU = dosisU;
    }

    public boolean getSolDiluir() {
        return solDiluir;
    }

    public void setSolDiluir(boolean solDiluir) {
        this.solDiluir = solDiluir;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        if (!(object instanceof HcuEvoMezclasMedicamentosDesc)) {
            return false;
        }
        HcuEvoMezclasMedicamentosDesc other = (HcuEvoMezclasMedicamentosDesc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HcuEvoMezclasMedicamentosDesc[ id=" + id + " ]";
    }
    
}
