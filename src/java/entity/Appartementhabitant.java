/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "appartementhabitant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appartementhabitant.findAll", query = "SELECT a FROM Appartementhabitant a"),
    @NamedQuery(name = "Appartementhabitant.findByAppartement", query = "SELECT a FROM Appartementhabitant a WHERE a.appartementhabitantPK.appartement = :appartement"),
    @NamedQuery(name = "Appartementhabitant.findByHabitant", query = "SELECT a FROM Appartementhabitant a WHERE a.appartementhabitantPK.habitant = :habitant"),
    @NamedQuery(name = "Appartementhabitant.findByDatedebut", query = "SELECT a FROM Appartementhabitant a WHERE a.appartementhabitantPK.datedebut = :datedebut"),
    @NamedQuery(name = "Appartementhabitant.findByDatefin", query = "SELECT a FROM Appartementhabitant a WHERE a.datefin = :datefin"),
    @NamedQuery(name = "Appartementhabitant.findByEtat", query = "SELECT a FROM Appartementhabitant a WHERE a.etat = :etat")})
public class Appartementhabitant implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AppartementhabitantPK appartementhabitantPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "etat")
    private String etat;
    @JoinColumn(name = "appartement", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Appartement appartement1;
    @JoinColumn(name = "habitant", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Habitant habitant1;

    public Appartementhabitant() {
    }

    public Appartementhabitant(AppartementhabitantPK appartementhabitantPK) {
        this.appartementhabitantPK = appartementhabitantPK;
    }

    public Appartementhabitant(AppartementhabitantPK appartementhabitantPK, Date datefin, String etat) {
        this.appartementhabitantPK = appartementhabitantPK;
        this.datefin = datefin;
        this.etat = etat;
    }

    public Appartementhabitant(int appartement, int habitant, Date datedebut) {
        this.appartementhabitantPK = new AppartementhabitantPK(appartement, habitant, datedebut);
    }

    public AppartementhabitantPK getAppartementhabitantPK() {
        return appartementhabitantPK;
    }

    public void setAppartementhabitantPK(AppartementhabitantPK appartementhabitantPK) {
        this.appartementhabitantPK = appartementhabitantPK;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Appartement getAppartement1() {
        return appartement1;
    }

    public void setAppartement1(Appartement appartement1) {
        this.appartement1 = appartement1;
    }

    public Habitant getHabitant1() {
        return habitant1;
    }

    public void setHabitant1(Habitant habitant1) {
        this.habitant1 = habitant1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appartementhabitantPK != null ? appartementhabitantPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appartementhabitant)) {
            return false;
        }
        Appartementhabitant other = (Appartementhabitant) object;
        if ((this.appartementhabitantPK == null && other.appartementhabitantPK != null) || (this.appartementhabitantPK != null && !this.appartementhabitantPK.equals(other.appartementhabitantPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Appartementhabitant[ appartementhabitantPK=" + appartementhabitantPK + " ]";
    }
    
}
