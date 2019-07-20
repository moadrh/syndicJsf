package controller;

import entity.Appartementhabitant;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.AppartementhabitantFacade;

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

@ManagedBean(name = "appartementhabitantController")
@SessionScoped
public class AppartementhabitantController implements Serializable {

    @EJB
    private service.AppartementhabitantFacade ejbFacade;
    private List<Appartementhabitant> items = null;
    private Appartementhabitant selected;

    public AppartementhabitantController() {
    }

    public Appartementhabitant getSelected() {
        return selected;
    }

    public void setSelected(Appartementhabitant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getAppartementhabitantPK().setHabitant(selected.getHabitant1().getId());
        selected.getAppartementhabitantPK().setAppartement(selected.getAppartement1().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setAppartementhabitantPK(new entity.AppartementhabitantPK());
    }

    private AppartementhabitantFacade getFacade() {
        return ejbFacade;
    }

    public Appartementhabitant prepareCreate() {
        selected = new Appartementhabitant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AppartementhabitantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AppartementhabitantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AppartementhabitantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Appartementhabitant> getItems() {
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

    public List<Appartementhabitant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Appartementhabitant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Appartementhabitant.class)
    public static class AppartementhabitantControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AppartementhabitantController controller = (AppartementhabitantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "appartementhabitantController");
            return controller.getFacade().find(getKey(value));
        }

        entity.AppartementhabitantPK getKey(String value) {
            entity.AppartementhabitantPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.AppartementhabitantPK();
            key.setAppartement(Integer.parseInt(values[0]));
            key.setHabitant(Integer.parseInt(values[1]));
            key.setDatedebut(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(entity.AppartementhabitantPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getAppartement());
            sb.append(SEPARATOR);
            sb.append(value.getHabitant());
            sb.append(SEPARATOR);
            sb.append(value.getDatedebut());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Appartementhabitant) {
                Appartementhabitant o = (Appartementhabitant) object;
                return getStringKey(o.getAppartementhabitantPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Appartementhabitant.class.getName()});
                return null;
            }
        }

    }

}
