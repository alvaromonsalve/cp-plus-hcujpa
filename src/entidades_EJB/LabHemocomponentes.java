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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "lab_hemocomponentes")
@NamedQueries({
    @NamedQuery(name = "LabHemocomponentes.findAll", query = "SELECT l FROM LabHemocomponentes l")})
public class LabHemocomponentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "globulosromas")
    private int globulosromas;
    @Basic(optional = false)
    @Column(name = "globulosromenos")
    private int globulosromenos;
    @Basic(optional = false)
    @Column(name = "globulosramas")
    private int globulosramas;
    @Basic(optional = false)
    @Column(name = "globulosramenos")
    private int globulosramenos;
    @Basic(optional = false)
    @Column(name = "globulosrbmas")
    private int globulosrbmas;
    @Basic(optional = false)
    @Column(name = "globulosrbmenos")
    private int globulosrbmenos;
    @Basic(optional = false)
    @Column(name = "globulosrabmas")
    private int globulosrabmas;
    @Basic(optional = false)
    @Column(name = "globulosrabmenos")
    private int globulosrabmenos;
    @Basic(optional = false)
    @Column(name = "plasmaomas")
    private int plasmaomas;
    @Basic(optional = false)
    @Column(name = "plasmaomenos")
    private int plasmaomenos;
    @Basic(optional = false)
    @Column(name = "plasmaamas")
    private int plasmaamas;
    @Basic(optional = false)
    @Column(name = "plasmaamenos")
    private int plasmaamenos;
    @Basic(optional = false)
    @Column(name = "plasmabmas")
    private int plasmabmas;
    @Basic(optional = false)
    @Column(name = "plasmabmenos")
    private int plasmabmenos;
    @Basic(optional = false)
    @Column(name = "plasmaabmas")
    private int plasmaabmas;
    @Basic(optional = false)
    @Column(name = "plasmaabmenos")
    private int plasmaabmenos;
    @Basic(optional = false)
    @Column(name = "crioomas")
    private int crioomas;
    @Basic(optional = false)
    @Column(name = "crioomenos")
    private int crioomenos;
    @Basic(optional = false)
    @Column(name = "crioamas")
    private int crioamas;
    @Basic(optional = false)
    @Column(name = "crioamenos")
    private int crioamenos;
    @Basic(optional = false)
    @Column(name = "criobmas")
    private int criobmas;
    @Basic(optional = false)
    @Column(name = "criobmenos")
    private int criobmenos;
    @Basic(optional = false)
    @Column(name = "crioabmas")
    private int crioabmas;
    @Basic(optional = false)
    @Column(name = "crioabmenos")
    private int crioabmenos;
    @Basic(optional = false)
    @Column(name = "paqueteurggreomas")
    private int paqueteurggreomas;
    @Basic(optional = false)
    @Column(name = "paqueteurggreomenos")
    private int paqueteurggreomenos;
    @Basic(optional = false)
    @Column(name = "paqueteurggreamas")
    private int paqueteurggreamas;
    @Basic(optional = false)
    @Column(name = "paqueteurggreamenos")
    private int paqueteurggreamenos;
    @Basic(optional = false)
    @Column(name = "paqueteurggrebmas")
    private int paqueteurggrebmas;
    @Basic(optional = false)
    @Column(name = "paqueteurggrebmenos")
    private int paqueteurggrebmenos;
    @Basic(optional = false)
    @Column(name = "paqueteurggreabmas")
    private int paqueteurggreabmas;
    @Basic(optional = false)
    @Column(name = "paqueteurggreabmenos")
    private int paqueteurggreabmenos;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcomas")
    private int paqueteurgpfcomas;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcomenos")
    private int paqueteurgpfcomenos;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcamas")
    private int paqueteurgpfcamas;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcamenos")
    private int paqueteurgpfcamenos;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcbmas")
    private int paqueteurgpfcbmas;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcbmenos")
    private int paqueteurgpfcbmenos;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcabmas")
    private int paqueteurgpfcabmas;
    @Basic(optional = false)
    @Column(name = "paqueteurgpfcabmenos")
    private int paqueteurgpfcabmenos;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;

    public LabHemocomponentes() {
    }

    public LabHemocomponentes(Integer id) {
        this.id = id;
    }

    public LabHemocomponentes(Integer id, Date fecha, int globulosromas, int globulosromenos, int globulosramas, int globulosramenos, int globulosrbmas, int globulosrbmenos, int globulosrabmas, int globulosrabmenos, int plasmaomas, int plasmaomenos, int plasmaamas, int plasmaamenos, int plasmabmas, int plasmabmenos, int plasmaabmas, int plasmaabmenos, int crioomas, int crioomenos, int crioamas, int crioamenos, int criobmas, int criobmenos, int crioabmas, int crioabmenos, int paqueteurggreomas, int paqueteurggreomenos, int paqueteurggreamas, int paqueteurggreamenos, int paqueteurggrebmas, int paqueteurggrebmenos, int paqueteurggreabmas, int paqueteurggreabmenos, int paqueteurgpfcomas, int paqueteurgpfcomenos, int paqueteurgpfcamas, int paqueteurgpfcamenos, int paqueteurgpfcbmas, int paqueteurgpfcbmenos, int paqueteurgpfcabmas, int paqueteurgpfcabmenos, int estado) {
        this.id = id;
        this.fecha = fecha;
        this.globulosromas = globulosromas;
        this.globulosromenos = globulosromenos;
        this.globulosramas = globulosramas;
        this.globulosramenos = globulosramenos;
        this.globulosrbmas = globulosrbmas;
        this.globulosrbmenos = globulosrbmenos;
        this.globulosrabmas = globulosrabmas;
        this.globulosrabmenos = globulosrabmenos;
        this.plasmaomas = plasmaomas;
        this.plasmaomenos = plasmaomenos;
        this.plasmaamas = plasmaamas;
        this.plasmaamenos = plasmaamenos;
        this.plasmabmas = plasmabmas;
        this.plasmabmenos = plasmabmenos;
        this.plasmaabmas = plasmaabmas;
        this.plasmaabmenos = plasmaabmenos;
        this.crioomas = crioomas;
        this.crioomenos = crioomenos;
        this.crioamas = crioamas;
        this.crioamenos = crioamenos;
        this.criobmas = criobmas;
        this.criobmenos = criobmenos;
        this.crioabmas = crioabmas;
        this.crioabmenos = crioabmenos;
        this.paqueteurggreomas = paqueteurggreomas;
        this.paqueteurggreomenos = paqueteurggreomenos;
        this.paqueteurggreamas = paqueteurggreamas;
        this.paqueteurggreamenos = paqueteurggreamenos;
        this.paqueteurggrebmas = paqueteurggrebmas;
        this.paqueteurggrebmenos = paqueteurggrebmenos;
        this.paqueteurggreabmas = paqueteurggreabmas;
        this.paqueteurggreabmenos = paqueteurggreabmenos;
        this.paqueteurgpfcomas = paqueteurgpfcomas;
        this.paqueteurgpfcomenos = paqueteurgpfcomenos;
        this.paqueteurgpfcamas = paqueteurgpfcamas;
        this.paqueteurgpfcamenos = paqueteurgpfcamenos;
        this.paqueteurgpfcbmas = paqueteurgpfcbmas;
        this.paqueteurgpfcbmenos = paqueteurgpfcbmenos;
        this.paqueteurgpfcabmas = paqueteurgpfcabmas;
        this.paqueteurgpfcabmenos = paqueteurgpfcabmenos;
        this.estado = estado;
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

    public int getGlobulosromas() {
        return globulosromas;
    }

    public void setGlobulosromas(int globulosromas) {
        this.globulosromas = globulosromas;
    }

    public int getGlobulosromenos() {
        return globulosromenos;
    }

    public void setGlobulosromenos(int globulosromenos) {
        this.globulosromenos = globulosromenos;
    }

    public int getGlobulosramas() {
        return globulosramas;
    }

    public void setGlobulosramas(int globulosramas) {
        this.globulosramas = globulosramas;
    }

    public int getGlobulosramenos() {
        return globulosramenos;
    }

    public void setGlobulosramenos(int globulosramenos) {
        this.globulosramenos = globulosramenos;
    }

    public int getGlobulosrbmas() {
        return globulosrbmas;
    }

    public void setGlobulosrbmas(int globulosrbmas) {
        this.globulosrbmas = globulosrbmas;
    }

    public int getGlobulosrbmenos() {
        return globulosrbmenos;
    }

    public void setGlobulosrbmenos(int globulosrbmenos) {
        this.globulosrbmenos = globulosrbmenos;
    }

    public int getGlobulosrabmas() {
        return globulosrabmas;
    }

    public void setGlobulosrabmas(int globulosrabmas) {
        this.globulosrabmas = globulosrabmas;
    }

    public int getGlobulosrabmenos() {
        return globulosrabmenos;
    }

    public void setGlobulosrabmenos(int globulosrabmenos) {
        this.globulosrabmenos = globulosrabmenos;
    }

    public int getPlasmaomas() {
        return plasmaomas;
    }

    public void setPlasmaomas(int plasmaomas) {
        this.plasmaomas = plasmaomas;
    }

    public int getPlasmaomenos() {
        return plasmaomenos;
    }

    public void setPlasmaomenos(int plasmaomenos) {
        this.plasmaomenos = plasmaomenos;
    }

    public int getPlasmaamas() {
        return plasmaamas;
    }

    public void setPlasmaamas(int plasmaamas) {
        this.plasmaamas = plasmaamas;
    }

    public int getPlasmaamenos() {
        return plasmaamenos;
    }

    public void setPlasmaamenos(int plasmaamenos) {
        this.plasmaamenos = plasmaamenos;
    }

    public int getPlasmabmas() {
        return plasmabmas;
    }

    public void setPlasmabmas(int plasmabmas) {
        this.plasmabmas = plasmabmas;
    }

    public int getPlasmabmenos() {
        return plasmabmenos;
    }

    public void setPlasmabmenos(int plasmabmenos) {
        this.plasmabmenos = plasmabmenos;
    }

    public int getPlasmaabmas() {
        return plasmaabmas;
    }

    public void setPlasmaabmas(int plasmaabmas) {
        this.plasmaabmas = plasmaabmas;
    }

    public int getPlasmaabmenos() {
        return plasmaabmenos;
    }

    public void setPlasmaabmenos(int plasmaabmenos) {
        this.plasmaabmenos = plasmaabmenos;
    }

    public int getCrioomas() {
        return crioomas;
    }

    public void setCrioomas(int crioomas) {
        this.crioomas = crioomas;
    }

    public int getCrioomenos() {
        return crioomenos;
    }

    public void setCrioomenos(int crioomenos) {
        this.crioomenos = crioomenos;
    }

    public int getCrioamas() {
        return crioamas;
    }

    public void setCrioamas(int crioamas) {
        this.crioamas = crioamas;
    }

    public int getCrioamenos() {
        return crioamenos;
    }

    public void setCrioamenos(int crioamenos) {
        this.crioamenos = crioamenos;
    }

    public int getCriobmas() {
        return criobmas;
    }

    public void setCriobmas(int criobmas) {
        this.criobmas = criobmas;
    }

    public int getCriobmenos() {
        return criobmenos;
    }

    public void setCriobmenos(int criobmenos) {
        this.criobmenos = criobmenos;
    }

    public int getCrioabmas() {
        return crioabmas;
    }

    public void setCrioabmas(int crioabmas) {
        this.crioabmas = crioabmas;
    }

    public int getCrioabmenos() {
        return crioabmenos;
    }

    public void setCrioabmenos(int crioabmenos) {
        this.crioabmenos = crioabmenos;
    }

    public int getPaqueteurggreomas() {
        return paqueteurggreomas;
    }

    public void setPaqueteurggreomas(int paqueteurggreomas) {
        this.paqueteurggreomas = paqueteurggreomas;
    }

    public int getPaqueteurggreomenos() {
        return paqueteurggreomenos;
    }

    public void setPaqueteurggreomenos(int paqueteurggreomenos) {
        this.paqueteurggreomenos = paqueteurggreomenos;
    }

    public int getPaqueteurggreamas() {
        return paqueteurggreamas;
    }

    public void setPaqueteurggreamas(int paqueteurggreamas) {
        this.paqueteurggreamas = paqueteurggreamas;
    }

    public int getPaqueteurggreamenos() {
        return paqueteurggreamenos;
    }

    public void setPaqueteurggreamenos(int paqueteurggreamenos) {
        this.paqueteurggreamenos = paqueteurggreamenos;
    }

    public int getPaqueteurggrebmas() {
        return paqueteurggrebmas;
    }

    public void setPaqueteurggrebmas(int paqueteurggrebmas) {
        this.paqueteurggrebmas = paqueteurggrebmas;
    }

    public int getPaqueteurggrebmenos() {
        return paqueteurggrebmenos;
    }

    public void setPaqueteurggrebmenos(int paqueteurggrebmenos) {
        this.paqueteurggrebmenos = paqueteurggrebmenos;
    }

    public int getPaqueteurggreabmas() {
        return paqueteurggreabmas;
    }

    public void setPaqueteurggreabmas(int paqueteurggreabmas) {
        this.paqueteurggreabmas = paqueteurggreabmas;
    }

    public int getPaqueteurggreabmenos() {
        return paqueteurggreabmenos;
    }

    public void setPaqueteurggreabmenos(int paqueteurggreabmenos) {
        this.paqueteurggreabmenos = paqueteurggreabmenos;
    }

    public int getPaqueteurgpfcomas() {
        return paqueteurgpfcomas;
    }

    public void setPaqueteurgpfcomas(int paqueteurgpfcomas) {
        this.paqueteurgpfcomas = paqueteurgpfcomas;
    }

    public int getPaqueteurgpfcomenos() {
        return paqueteurgpfcomenos;
    }

    public void setPaqueteurgpfcomenos(int paqueteurgpfcomenos) {
        this.paqueteurgpfcomenos = paqueteurgpfcomenos;
    }

    public int getPaqueteurgpfcamas() {
        return paqueteurgpfcamas;
    }

    public void setPaqueteurgpfcamas(int paqueteurgpfcamas) {
        this.paqueteurgpfcamas = paqueteurgpfcamas;
    }

    public int getPaqueteurgpfcamenos() {
        return paqueteurgpfcamenos;
    }

    public void setPaqueteurgpfcamenos(int paqueteurgpfcamenos) {
        this.paqueteurgpfcamenos = paqueteurgpfcamenos;
    }

    public int getPaqueteurgpfcbmas() {
        return paqueteurgpfcbmas;
    }

    public void setPaqueteurgpfcbmas(int paqueteurgpfcbmas) {
        this.paqueteurgpfcbmas = paqueteurgpfcbmas;
    }

    public int getPaqueteurgpfcbmenos() {
        return paqueteurgpfcbmenos;
    }

    public void setPaqueteurgpfcbmenos(int paqueteurgpfcbmenos) {
        this.paqueteurgpfcbmenos = paqueteurgpfcbmenos;
    }

    public int getPaqueteurgpfcabmas() {
        return paqueteurgpfcabmas;
    }

    public void setPaqueteurgpfcabmas(int paqueteurgpfcabmas) {
        this.paqueteurgpfcabmas = paqueteurgpfcabmas;
    }

    public int getPaqueteurgpfcabmenos() {
        return paqueteurgpfcabmenos;
    }

    public void setPaqueteurgpfcabmenos(int paqueteurgpfcabmenos) {
        this.paqueteurgpfcabmenos = paqueteurgpfcabmenos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        if (!(object instanceof LabHemocomponentes)) {
            return false;
        }
        LabHemocomponentes other = (LabHemocomponentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_EJB.LabHemocomponentes[ id=" + id + " ]";
    }

}
