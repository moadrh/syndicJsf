/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Employe;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import service.EmployeFacade;
import service.ProfilFacade;

/**
 *
 * @author dell
 */
@ManagedBean(name = "inscriptionController")
@RequestScoped
public class InscriptionController {

    /**
     * Creates a new instance of InscriptionController
     */
    @EJB
    private service.EmployeFacade ejbFacade;
    private List<Employe> items = null;
    private Employe selected;
    @EJB
    private ProfilFacade pf;
    
    public InscriptionController() {
        selected = new Employe();
    }

    public Employe getSelected() {
        return selected;
    }

    public void setSelected(Employe selected) {
        this.selected = selected;
    }

    public ProfilFacade getPf() {
        return pf;
    }

    public void setPf(ProfilFacade pf) {
        this.pf = pf;
    }

    private EmployeFacade getFacade() {
        return ejbFacade;
    }

    public String create() {
         
        if (selected != null) {
            selected.setProfilId(pf.find(2));
            System.out.println("$$$$$$$$$$$$$$$$$$$"+selected.getNom());
            ejbFacade.create(selected);
        }
        return "login?faces-redirect=true";
  
    }

    public List<Employe> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

}
