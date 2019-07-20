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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "employeimmeuble")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeimmeuble.findAll", query = "SELECT e FROM Employeimmeuble e"),
    @NamedQuery(name = "Employeimmeuble.findByEmploye", query = "SELECT e FROM Employeimmeuble e WHERE e.employeimmeublePK.employe = :employe"),
    @NamedQuery(name = "Employeimmeuble.findByImmeuble", query = "SELECT e FROM Employeimmeuble e WHERE e.employeimmeublePK.immeuble = :immeuble"),
    @NamedQuery(name = "Employeimmeuble.findByDatedebut", query = "SELECT e FROM Employeimmeuble e WHERE e.employeimmeublePK.datedebut = :datedebut"),
    @NamedQuery(name = "Employeimmeuble.findByDatefin", query = "SELECT e FROM Employeimmeuble e WHERE e.datefin = :datefin")})
public class Employeimmeuble implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeimmeublePK employeimmeublePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @JoinColumn(name = "employe", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employe employe1;
    @JoinColumn(name = "immeuble", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Immeuble immeuble1;

    public Employeimmeuble() {
    }

    public Employeimmeuble(EmployeimmeublePK employeimmeublePK) {
        this.employeimmeublePK = employeimmeublePK;
    }

    public Employeimmeuble(EmployeimmeublePK employeimmeublePK, Date datefin) {
        this.employeimmeublePK = employeimmeublePK;
        this.datefin = datefin;
    }

    public Employeimmeuble(int employe, int immeuble, Date datedebut) {
        this.employeimmeublePK = new EmployeimmeublePK(employe, immeuble, datedebut);
    }

    public EmployeimmeublePK getEmployeimmeublePK() {
        return employeimmeublePK;
    }

    public void setEmployeimmeublePK(EmployeimmeublePK employeimmeublePK) {
        this.employeimmeublePK = employeimmeublePK;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Employe getEmploye1() {
        return employe1;
    }

    public void setEmploye1(Employe employe1) {
        this.employe1 = employe1;
    }

    public Immeuble getImmeuble1() {
        return immeuble1;
    }

    public void setImmeuble1(Immeuble immeuble1) {
        this.immeuble1 = immeuble1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeimmeublePK != null ? employeimmeublePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeimmeuble)) {
            return false;
        }
        Employeimmeuble other = (Employeimmeuble) object;
        if ((this.employeimmeublePK == null && other.employeimmeublePK != null) || (this.employeimmeublePK != null && !this.employeimmeublePK.equals(other.employeimmeublePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Employeimmeuble[ employeimmeublePK=" + employeimmeublePK + " ]";
    }
    
}
