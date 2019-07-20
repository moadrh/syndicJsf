package entity;

import entity.Employeimmeuble;
import entity.Profil;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile ListAttribute<Employe, Employeimmeuble> employeimmeubleList;
    public static volatile SingularAttribute<Employe, String> password;
    public static volatile SingularAttribute<Employe, Profil> profilId;
    public static volatile SingularAttribute<Employe, String> telephone;
    public static volatile SingularAttribute<Employe, Integer> id;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile SingularAttribute<Employe, String> email;

}