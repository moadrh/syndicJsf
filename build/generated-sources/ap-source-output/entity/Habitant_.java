package entity;

import entity.Appartementhabitant;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Habitant.class)
public class Habitant_ { 

    public static volatile ListAttribute<Habitant, Appartementhabitant> appartementhabitantList;
    public static volatile SingularAttribute<Habitant, String> cin;
    public static volatile SingularAttribute<Habitant, String> telephone;
    public static volatile SingularAttribute<Habitant, Integer> id;
    public static volatile SingularAttribute<Habitant, String> nom;
    public static volatile SingularAttribute<Habitant, String> prenom;

}