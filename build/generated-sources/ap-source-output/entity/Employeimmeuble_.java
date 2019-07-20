package entity;

import entity.Employe;
import entity.EmployeimmeublePK;
import entity.Immeuble;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-06T13:04:55")
@StaticMetamodel(Employeimmeuble.class)
public class Employeimmeuble_ { 

    public static volatile SingularAttribute<Employeimmeuble, EmployeimmeublePK> employeimmeublePK;
    public static volatile SingularAttribute<Employeimmeuble, Employe> employe1;
    public static volatile SingularAttribute<Employeimmeuble, Date> datefin;
    public static volatile SingularAttribute<Employeimmeuble, Immeuble> immeuble1;

}