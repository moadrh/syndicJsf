package entity;

import entity.Appartementhabitant;
import entity.Etage;
import entity.Immeuble;
import entity.Revenue;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Appartement.class)
public class Appartement_ { 

    public static volatile ListAttribute<Appartement, Revenue> revenueList;
    public static volatile ListAttribute<Appartement, Appartementhabitant> appartementhabitantList;
    public static volatile SingularAttribute<Appartement, Etage> etageId;
    public static volatile SingularAttribute<Appartement, String> num;
    public static volatile SingularAttribute<Appartement, Integer> id;
    public static volatile SingularAttribute<Appartement, Immeuble> immeubleId;
    public static volatile SingularAttribute<Appartement, Double> dimension;

}