package entity;

import entity.Appartement;
import entity.Periode;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Revenue.class)
public class Revenue_ { 

    public static volatile SingularAttribute<Revenue, Date> date;
    public static volatile SingularAttribute<Revenue, Appartement> appartementId;
    public static volatile SingularAttribute<Revenue, Double> montant;
    public static volatile SingularAttribute<Revenue, Periode> periodeId;
    public static volatile SingularAttribute<Revenue, Integer> id;

}