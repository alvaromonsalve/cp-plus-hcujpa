/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monsalve
 */
@Entity
@Table(name = "info_ant_personales")
@NamedQueries({
    @NamedQuery(name = "InfoAntPersonales.findAll", query = "SELECT i FROM InfoAntPersonales i")})
public class InfoAntPersonales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name="id_paciente")
    private int idPaciente;
    @Column(name = "alergias")
    private String alergias;
    @Column(name = "ingresos_previos")
    private String ingresosPrevios;
    @Column(name = "traumatismos")
    private String traumatismos;
    @Column(name = "tratamientos")
    private String tratamientos;
    @Column(name = "dm")
    private Boolean dm;
    @Column(name = "hta")
    private Boolean hta;
    @Column(name = "dislipidemia")
    private Boolean dislipidemia;
    @Column(name = "desc_hdd")
    private String descHdd;
    @Column(name = "tabaco")
    private Boolean tabaco;
    @Column(name = "alcohol")
    private Boolean alcohol;
    @Column(name = "droga")
    private Boolean droga;
    @Column(name = "otros_habitos")
    private String otrosHabitos;
    @Column(name = "situacion_basal")
    private String situacionBasal;
    @Column(name = "ant_familiares")
    private String antFamiliares;

    public InfoAntPersonales() {
    }

    public InfoAntPersonales(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getIngresosPrevios() {
        return ingresosPrevios;
    }

    public void setIngresosPrevios(String ingresosPrevios) {
        this.ingresosPrevios = ingresosPrevios;
    }

    public String getTraumatismos() {
        return traumatismos;
    }

    public void setTraumatismos(String traumatismos) {
        this.traumatismos = traumatismos;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    public Boolean getDm() {
        return dm;
    }

    public void setDm(Boolean dm) {
        this.dm = dm;
    }

    public Boolean getHta() {
        return hta;
    }

    public void setHta(Boolean hta) {
        this.hta = hta;
    }

    public Boolean getDislipidemia() {
        return dislipidemia;
    }

    public void setDislipidemia(Boolean dislipidemia) {
        this.dislipidemia = dislipidemia;
    }

    public String getDescHdd() {
        return descHdd;
    }

    public void setDescHdd(String descHdd) {
        this.descHdd = descHdd;
    }

    public Boolean getTabaco() {
        return tabaco;
    }

    public void setTabaco(Boolean tabaco) {
        this.tabaco = tabaco;
    }

    public Boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Boolean getDroga() {
        return droga;
    }

    public void setDroga(Boolean droga) {
        this.droga = droga;
    }

    public String getOtrosHabitos() {
        return otrosHabitos;
    }

    public void setOtrosHabitos(String otrosHabitos) {
        this.otrosHabitos = otrosHabitos;
    }

    public String getSituacionBasal() {
        return situacionBasal;
    }

    public void setSituacionBasal(String situacionBasal) {
        this.situacionBasal = situacionBasal;
    }

    public String getAntFamiliares() {
        return antFamiliares;
    }

    public void setAntFamiliares(String antFamiliares) {
        this.antFamiliares = antFamiliares;
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
        if (!(object instanceof InfoAntPersonales)) {
            return false;
        }
        InfoAntPersonales other = (InfoAntPersonales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InfoAntPersonales[ id=" + id + " ]";
    }
    
}
