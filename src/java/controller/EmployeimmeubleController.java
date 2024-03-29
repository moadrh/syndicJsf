package controller;

import entity.Employeimmeuble;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.EmployeimmeubleFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "employeimmeubleController")
@SessionScoped
public class EmployeimmeubleController implements Serializable {

    @EJB
    private service.EmployeimmeubleFacade ejbFacade;
    private List<Employeimmeuble> items = null;
    private Employeimmeuble selected;

    public EmployeimmeubleController() {
    }

    public Employeimmeuble getSelected() {
        return selected;
    }

    public void setSelected(Employeimmeuble selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getEmployeimmeublePK().setImmeuble(selected.getImmeuble1().getId());
        selected.getEmployeimmeublePK().setEmploye(selected.getEmploye1().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setEmployeimmeublePK(new entity.EmployeimmeublePK());
    }

    private EmployeimmeubleFacade getFacade() {
        return ejbFacade;
    }

    public Employeimmeuble prepareCreate() {
        selected = new Employeimmeuble();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmployeimmeubleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmployeimmeubleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmployeimmeubleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Employeimmeuble> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Employeimmeuble> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Employeimmeuble> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Employeimmeuble.class)
    public static class EmployeimmeubleControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmployeimmeubleController controller = (EmployeimmeubleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "employeimmeubleController");
            return controller.getFacade().find(getKey(value));
        }

        entity.EmployeimmeublePK getKey(String value) {
            entity.EmployeimmeublePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.EmployeimmeublePK();
            key.setEmploye(Integer.parseInt(values[0]));
            key.setImmeuble(Integer.parseInt(values[1]));
            key.setDatedebut(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(entity.EmployeimmeublePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getEmploye());
            sb.append(SEPARATOR);
            sb.append(value.getImmeuble());
            sb.append(SEPARATOR);
            sb.append(value.getDatedebut());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Employeimmeuble) {
                Employeimmeuble o = (Employeimmeuble) object;
                return getStringKey(o.getEmployeimmeublePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Employeimmeuble.class.getName()});
                return null;
            }
        }

    }

}
