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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "etage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etage.findAll", query = "SELECT e FROM Etage e"),
    @NamedQuery(name = "Etage.findById", query = "SELECT e FROM Etage e WHERE e.id = :id"),
    @NamedQuery(name = "Etage.findByNum", query = "SELECT e FROM Etage e WHERE e.num = :num")})
public class Etage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num")
    private int num;
    @JoinColumn(name = "immeuble_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Immeuble immeubleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etageId", fetch = FetchType.EAGER)
    private List<Appartement> appartementList;

    public Etage() {
    }

    public Etage(Integer id) {
        this.id = id;
    }

    public Etage(Integer id, int num) {
        this.id = id;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Immeuble getImmeubleId() {
        return immeubleId;
    }

    public void setImmeubleId(Immeuble immeubleId) {
        this.immeubleId = immeubleId;
    }

    @XmlTransient
    public List<Appartement> getAppartementList() {
        return appartementList;
    }

    public void setAppartementList(List<Appartement> appartementList) {
        this.appartementList = appartementList;
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
        if (!(object instanceof Etage)) {
            return false;
        }
        Etage other = (Etage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return num+"";
    }
    
}
