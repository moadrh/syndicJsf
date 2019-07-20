package entity;

import entity.Employe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Profil.class)
public class Profil_ { 

    public static volatile SingularAttribute<Profil, String> code;
    public static volatile ListAttribute<Profil, Employe> employeList;
    public static volatile SingularAttribute<Profil, String> libelle;
    public static volatile SingularAttribute<Profil, Integer> id;

}