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
@Table(name = "habitant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitant.findAll", query = "SELECT h FROM Habitant h"),
    @NamedQuery(name = "Habitant.findById", query = "SELECT h FROM Habitant h WHERE h.id = :id"),
    @NamedQuery(name = "Habitant.findByCin", query = "SELECT h FROM Habitant h WHERE h.cin = :cin"),
    @NamedQuery(name = "Habitant.findByNom", query = "SELECT h FROM Habitant h WHERE h.nom = :nom"),
    @NamedQuery(name = "Habitant.findByPrenom", query = "SELECT h FROM Habitant h WHERE h.prenom = :prenom"),
    @NamedQuery(name = "Habitant.findByTelephone", query = "SELECT h FROM Habitant h WHERE h.telephone = :telephone")})
public class Habitant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cin")
    private String cin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitant1", fetch = FetchType.EAGER)
    private List<Appartementhabitant> appartementhabitantList;

    public Habitant() {
    }

    public Habitant(Integer id) {
        this.id = id;
    }

    public Habitant(Integer id, String cin, String nom, String prenom, String telephone) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
        if (!(object instanceof Habitant)) {
            return false;
        }
        Habitant other = (Habitant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom+" "+prenom;
    }
    
}
