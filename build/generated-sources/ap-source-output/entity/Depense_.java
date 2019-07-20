package entity;

import entity.Categorie;
import entity.Immeuble;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Depense.class)
public class Depense_ { 

    public static volatile SingularAttribute<Depense, Date> date;
    public static volatile SingularAttribute<Depense, Categorie> categorieId;
    public static volatile SingularAttribute<Depense, String> montant;
    public static volatile SingularAttribute<Depense, Integer> id;
    public static volatile SingularAttribute<Depense, Immeuble> immeubleId;

}