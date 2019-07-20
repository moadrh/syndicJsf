package controller;

import entity.Revenue;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import java.io.IOException;
import service.RevenueFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@ManagedBean(name = "revenueController")
@SessionScoped
public class RevenueController implements Serializable {

    @EJB
    private service.RevenueFacade ejbFacade;
    private List<Revenue> items = null;
    private Revenue selected;

    public RevenueController() {
    }

    public Revenue getSelected() {
        return selected;
    }

    public void setSelected(Revenue selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RevenueFacade getFacade() {
        return ejbFacade;
    }

    public Revenue prepareCreate() {
        selected = new Revenue();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() throws JRException , IOException  {

        JasperReport jr = JasperCompileManager.compileReport("C:\\NetBeansProjects\\syndicJsf\\web\\resources\\reports\\revenueReport.jrxml");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("immeuble", selected.getAppartementId().getImmeubleId().toString());
        parameters.put("nom", selected.getAppartementId().getAppartementhabitantList().get(0).getHabitant1().getNom());
        parameters.put("prenom", selected.getAppartementId().getAppartementhabitantList().get(0).getHabitant1().getPrenom());
        parameters.put("appartement", selected.getAppartementId().getNum()+"");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        parameters.put("date", sdf.format(selected.getDate()));
        parameters.put("periode", selected.getPeriodeId().getNom());
        parameters.put("montant", selected.getMontant()+" DH");

        JRDataSource dataSource = new JREmptyDataSource();
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("content-disposition", "attachement;filename=" + jr.getName()+".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, stream);
        FacesContext.getCurrentInstance().responseComplete();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RevenueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RevenueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RevenueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Revenue> getItems() {
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

    public List<Revenue> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Revenue> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Revenue.class)
    public static class RevenueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RevenueController controller = (RevenueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "revenueController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Revenue) {
                Revenue o = (Revenue) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Revenue.class.getName()});
                return null;
            }
        }

    }

}
