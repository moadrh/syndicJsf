/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import  entity.Employe;
import controller.util.SessionUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import service.EmployeFacade;

/**
 *
 * @author 4g
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable{
    @EJB
    private  EmployeFacade ejbFacade;
    private Employe employe;
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        employe = new Employe();
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    
    public String validateLogin(){
        String res = "";
        Employe e = ejbFacade.findEmployeByEmail(employe.getEmail());
        RequestContext.getCurrentInstance().update("growl");
        if(e == null){
            FacesContext.getCurrentInstance().addMessage(
            null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Incorrect Email",
                            "Please enter correct Email"
                    ));
            return "login";
        }else{
            if(e.getPassword().equals(employe.getPassword())){
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("user", e);
//                return "index";
                return "index?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage(
            null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Incorrect Password",
                            "Please enter correct Password"
                    ));
            }
        }
        
        return res;
    }
    
    
    public String logOut(){
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/faces/login";
    }
    
    public void permission() throws IOException{
        HttpSession session = SessionUtils.getSession();
        if(session.getAttribute("user") == null){
            System.out.println("this user has no permission");
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("/syndicJsf/faces/login.xhtml?faces-redirect=true");
        }else {
            System.out.println("The session is still alive");
        }
    }
    
}
