/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades_EJB;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IdlhDeveloper
 */
@Entity
@Table(name = "ce_exp_fisica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeExpFisica.findAll", query = "SELECT c FROM CeExpFisica c"),
    @NamedQuery(name = "CeExpFisica.findById", query = "SELECT c FROM CeExpFisica c WHERE c.id = :id"),
    @NamedQuery(name = "CeExpFisica.findByT", query = "SELECT c FROM CeExpFisica c WHERE c.t = :t"),
    @NamedQuery(name = "CeExpFisica.findByPeso", query = "SELECT c FROM CeExpFisica c WHERE c.peso = :peso"),
    @NamedQuery(name = "CeExpFisica.findByTalla", query = "SELECT c FROM CeExpFisica c WHERE c.talla = :talla"),
    @NamedQuery(name = "CeExpFisica.findByFr", query = "SELECT c FROM CeExpFisica c WHERE c.fr = :fr"),
    @NamedQuery(name = "CeExpFisica.findByFc", query = "SELECT c FROM CeExpFisica c WHERE c.fc = :fc"),
    @NamedQuery(name = "CeExpFisica.findByTas", query = "SELECT c FROM CeExpFisica c WHERE c.tas = :tas"),
    @NamedQuery(name = "CeExpFisica.findByTad", query = "SELECT c FROM CeExpFisica c WHERE c.tad = :tad"),
    @NamedQuery(name = "CeExpFisica.findByTam", query = "SELECT c FROM CeExpFisica c WHERE c.tam = :tam"),
    @NamedQuery(name = "CeExpFisica.findByImc", query = "SELECT c FROM CeExpFisica c WHERE c.imc = :imc"),
    @NamedQuery(name = "CeExpFisica.findByEstado", query = "SELECT c FROM CeExpFisica c WHERE c.estado = :estado")})
public class CeExpFisica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "t")
    private String t;
    @Column(name = "peso")
    private String peso;
    @Column(name = "talla")
    private String talla;
    @Column(name = "fr")
    private String fr;
    @Column(name = "fc")
    private String fc;
    @Column(name = "tas")
    private String tas;
    @Column(name = "tad")
    private String tad;
    @Column(name = "tam")
    private String tam;
   
    @Column(name ="imc")
     private String imc;
    
    @Lob
    @Column(name = "aspectogeneral")
    private String aspectogeneral;
    @Lob
    @Column(name = "cara")
    private String cara;
    @Lob
    @Column(name = "cardio")
    private String cardio;
    @Lob
    @Column(name = "resp")
    private String resp;
    @Lob
    @Column(name = "gastro")
    private String gastro;
    @Lob
    @Column(name = "genito")
    private String genito;
    @Lob
    @Column(name = "piel")
    private String piel;
    @Lob
    @Column(name = "endo")
    private String endo;
    @Lob
    @Column(name = "hemato")
    private String hemato;
    @Column(name = "estado")
    private Character estado;

    @JoinColumn(name = "id_historiace", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CeHistoriac idHistoriace;
     
    public CeExpFisica() {
    }

    public CeExpFisica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getTas() {
        return tas;
    }

    public void setTas(String tas) {
        this.tas = tas;
    }

    public String getTad() {
        return tad;
    }

    public void setTad(String tad) {
        this.tad = tad;
    }

    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }

    public String getAspectogeneral() {
        return aspectogeneral;
    }

    public void setAspectogeneral(String aspectogeneral) {
        this.aspectogeneral = aspectogeneral;
    }

    public String getCara() {
        return cara;
    }

    public void setCara(String cara) {
        this.cara = cara;
    }

    public String getCardio() {
        return cardio;
    }

    public void setCardio(String cardio) {
        this.cardio = cardio;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getGastro() {
        return gastro;
    }

    public void setGastro(String gastro) {
        this.gastro = gastro;
    }

    public String getGenito() {
        return genito;
    }

    public void setGenito(String genito) {
        this.genito = genito;
    }

    public String getPiel() {
        return piel;
    }

    public void setPiel(String piel) {
        this.piel = piel;
    }

    public String getEndo() {
        return endo;
    }

    public void setEndo(String endo) {
        this.endo = endo;
    }

    public String getHemato() {
        return hemato;
    }

    public void setHemato(String hemato) {
        this.hemato = hemato;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

     public CeHistoriac getIdHistoriace() {
        return idHistoriace;
    }

    public void setIdHistoriace(CeHistoriac idHistoriace) {
        this.idHistoriace = idHistoriace;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
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
        if (!(object instanceof CeExpFisica)) {
            return false;
        }
        CeExpFisica other = (CeExpFisica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE_Entidades.CeExpFisica[ id=" + id + " ]";
    }
    
}
