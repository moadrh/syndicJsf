/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Immeuble;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dell
 */
@Stateless
public class ImmeubleFacade extends AbstractFacade<Immeuble> {
    @PersistenceContext(unitName = "syndicJsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImmeubleFacade() {
        super(Immeuble.class);
    }
     public  List<Object[]> exporter(Immeuble immeuble){
        List<Object[]> list = null;
        System.out.println("##########");
        list = em.createQuery("SELECT ah.appartement1.num, a.dimension, ah.habitant1.nom, ah.habitant1.prenom FROM Immeuble i, Appartement a, Appartementhabitant ah WHERE i.id = a.immeubleId.id AND a.id = ah.appartement1.id AND i.id=:imm").setParameter("imm", immeuble.getId()).getResultList();
        System.out.println("#### MSt "+list);
        return list;
        
        
    }
}
