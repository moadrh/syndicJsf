/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "appartement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appartement.findAll", query = "SELECT a FROM Appartement a"),
    @NamedQuery(name = "Appartement.findById", query = "SELECT a FROM Appartement a WHERE a.id = :id"),
    @NamedQuery(name = "Appartement.findByNum", query = "SELECT a FROM Appartement a WHERE a.num = :num"),
    @NamedQuery(name = "Appartement.findByDimension", query = "SELECT a FROM Appartement a WHERE a.dimension = :dimension")})
public class Appartement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "num")
    private String num;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension")
    private double dimension;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appartementId", fetch = FetchType.EAGER)
    private List<Revenue> revenueList;
    @JoinColumn(name = "etage_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Etage etageId;
    @JoinColumn(name = "immeuble_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Immeuble immeubleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appartement1", fetch = FetchType.EAGER)
    private List<Appartementhabitant> appartementhabitantList;

    public Appartement() {
    }

    public Appartement(Integer id) {
        this.id = id;
    }

    public Appartement(Integer id, String num, double dimension) {
        this.id = id;
        this.num = num;
        this.dimension = dimension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    @XmlTransient
    public List<Revenue> getRevenueList() {
        return revenueList;
    }

    public void setRevenueList(List<Revenue> revenueList) {
        this.revenueList = revenueList;
    }

    public Etage getEtageId() {
        return etageId;
    }

    public void setEtageId(Etage etageId) {
        this.etageId = etageId;
    }

    public Immeuble getImmeubleId() {
        return immeubleId;
    }

    public void setImmeubleId(Immeuble immeubleId) {
        this.immeubleId = immeubleId;
    }

    @XmlTransient
    public List<Appartementhabitant> getAppartementhabitantList() {
        return appartementhabitantList;
    }

    public void setAppartementhabitantList(List<Appartementhabitant> appartementhabitantList) {
        this.appartementhabitantList = appartementhabitantList;
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
        if (!(object instanceof Appartement)) {
            return false;
        }
        Appartement other = (Appartement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return num;
    }
    
}
