/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.util.SessionUtils;
import entity.Employe;
import entity.Immeuble;
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
public class EmployeFacade extends AbstractFacade<Employe> {
    @PersistenceContext(unitName = "syndicJsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeFacade() {
        super(Employe.class);
    }
     public List<Immeuble> findImmeublesByEmploye(Employe e) {
        List<Immeuble> immeubles = null;
        if (e != null) {
            immeubles = em.createNamedQuery("Employeimmeuble.findByEmployeId").setParameter("employeId", e.getId()).getResultList();
            System.out.println("###########" + immeubles.get(0));
        }
        return immeubles;
    }
    public Employe findEmployeByEmail(String email){
        Employe e = null;
        try {
            if(!email.isEmpty()){
                e = (Employe) em.createNamedQuery("Employe.findByEmail").setParameter("email", email).getSingleResult();
            }
        } catch (Exception ee) {
        }
        return e;
    }
    public List<Immeuble> findImmeublesByEmploye() {
       HttpSession session = SessionUtils.getSession();
        Employe user = (Employe) session.getAttribute("user");
        return em.createQuery("SELECT e.immeuble1 FROM Employeimmeuble e WHERE e.employe1.id = "+ user.getId())
                .getResultList();
    }
    
}
