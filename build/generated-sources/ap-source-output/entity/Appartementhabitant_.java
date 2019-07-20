package entity;

import entity.Appartement;
import entity.AppartementhabitantPK;
import entity.Habitant;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Appartementhabitant.class)
public class Appartementhabitant_ { 

    public static volatile SingularAttribute<Appartementhabitant, Habitant> habitant1;
    public static volatile SingularAttribute<Appartementhabitant, AppartementhabitantPK> appartementhabitantPK;
    public static volatile SingularAttribute<Appartementhabitant, Date> datefin;
    public static volatile SingularAttribute<Appartementhabitant, String> etat;
    public static volatile SingularAttribute<Appartementhabitant, Appartement> appartement1;

}