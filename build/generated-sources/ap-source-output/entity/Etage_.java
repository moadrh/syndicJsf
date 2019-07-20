package entity;

import entity.Appartement;
import entity.Immeuble;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Etage.class)
public class Etage_ { 

    public static volatile ListAttribute<Etage, Appartement> appartementList;
    public static volatile SingularAttribute<Etage, Integer> num;
    public static volatile SingularAttribute<Etage, Integer> id;
    public static volatile SingularAttribute<Etage, Immeuble> immeubleId;

}