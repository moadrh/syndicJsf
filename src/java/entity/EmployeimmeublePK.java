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
public class EmployeimmeublePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "employe")
    private int employe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "immeuble")
    private int immeuble;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;

    public EmployeimmeublePK() {
    }

    public EmployeimmeublePK(int employe, int immeuble, Date datedebut) {
        this.employe = employe;
        this.immeuble = immeuble;
        this.datedebut = datedebut;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(int immeuble) {
        this.immeuble = immeuble;
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
        hash += (int) employe;
        hash += (int) immeuble;
        hash += (datedebut != null ? datedebut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeimmeublePK)) {
            return false;
        }
        EmployeimmeublePK other = (EmployeimmeublePK) object;
        if (this.employe != other.employe) {
            return false;
        }
        if (this.immeuble != other.immeuble) {
            return false;
        }
        if ((this.datedebut == null && other.datedebut != null) || (this.datedebut != null && !this.datedebut.equals(other.datedebut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmployeimmeublePK[ employe=" + employe + ", immeuble=" + immeuble + ", datedebut=" + datedebut + " ]";
    }
    
}
