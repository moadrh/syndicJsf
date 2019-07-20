/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Depense;
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
public class DepenseFacade extends AbstractFacade<Depense> {
    @PersistenceContext(unitName = "syndicJsfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepenseFacade() {
        super(Depense.class);
    }
    public List<Object []> depenseByCategorie(Immeuble imm){
        List<Object []> depenses = null;
        
        depenses = em.createNamedQuery("Depense.findByCategorie").setParameter("imm", imm).getResultList();
        return depenses;
    }
    
}
