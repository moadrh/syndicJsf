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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dell
 */
@Embeddable
public class AppartementhabitantPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "appartement")
    private int appartement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habitant")
    private int habitant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;

    public AppartementhabitantPK() {
    }

    public AppartementhabitantPK(int appartement, int habitant, Date datedebut) {
        this.appartement = appartement;
        this.habitant = habitant;
        this.datedebut = datedebut;
    }

    public int getAppartement() {
        return appartement;
    }

    public void setAppartement(int appartement) {
        this.appartement = appartement;
    }

    public int getHabitant() {
        return habitant;
    }

    public void setHabitant(int habitant) {
        this.habitant = habitant;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) appartement;
        hash += (int) habitant;
        hash += (datedebut != null ? datedebut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppartementhabitantPK)) {
            return false;
        }
        AppartementhabitantPK other = (AppartementhabitantPK) object;
        if (this.appartement != other.appartement) {
            return false;
        }
        if (this.habitant != other.habitant) {
            return false;
        }
        if ((this.datedebut == null && other.datedebut != null) || (this.datedebut != null && !this.datedebut.equals(other.datedebut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AppartementhabitantPK[ appartement=" + appartement + ", habitant=" + habitant + ", datedebut=" + datedebut + " ]";
    }
    
}
