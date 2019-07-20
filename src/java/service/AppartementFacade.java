/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.util.SessionUtils;
import entity.Appartement;
import entity.Employe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell
 */
@Stateless
public class AppartementFacade extends AbstractFacade<Appartement> {
    @PersistenceContext(unitName = "syndicJsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppartementFacade() {
        super(Appartement.class);
    }
     public List<Appartement> findAppartementsByEmploye(){
        List<Appartement> appartements = null;
       HttpSession session = SessionUtils.getSession();
        Employe user = (Employe) session.getAttribute("user");
        return em.createQuery("SELECT A FROM Appartement A , Employeimmeuble EI, Immeuble I WHERE EI.immeuble1.id = I.id AND A.immeubleId.id = I.id AND  EI.employe1.id = "+ user.getId())
                .getResultList();
    }
}
