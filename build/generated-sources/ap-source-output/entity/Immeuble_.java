package entity;

import entity.Appartement;
import entity.Depense;
import entity.Employeimmeuble;
import entity.Etage;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Immeuble.class)
public class Immeuble_ { 

    public static volatile ListAttribute<Immeuble, Employeimmeuble> employeimmeubleList;
    public static volatile ListAttribute<Immeuble, Appartement> appartementList;
    public static volatile ListAttribute<Immeuble, Etage> etageList;
    public static volatile SingularAttribute<Immeuble, Integer> num;
    public static volatile SingularAttribute<Immeuble, String> adresse;
    public static volatile SingularAttribute<Immeuble, Integer> id;
    public static volatile ListAttribute<Immeuble, Depense> depenseList;
    public static volatile SingularAttribute<Immeuble, String> nom;

}