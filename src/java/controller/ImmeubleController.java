package controller;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import entity.Immeuble;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.PeriodeController;
import controller.util.SessionUtils;
import entity.Appartement;
import entity.Depense;
import entity.Employe;
import entity.Periode;
import entity.Revenue;
import service.ImmeubleFacade;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import service.DepenseFacade;
import service.EmployeFacade;
import service.PeriodeFacade;

@ManagedBean(name = "immeubleController")
@SessionScoped
public class ImmeubleController implements Serializable {

    @EJB
    private service.ImmeubleFacade ejbFacade;
    private List<Immeuble> items = null;
    private Immeuble selected;
    @EJB
    private EmployeFacade ef;
    private int annee;
    private int annee2;
    @EJB
    private PeriodeFacade pf;
    private List<Periode> pers = null;
    private PieChartModel pieModel;
    @EJB
    private DepenseFacade df;

    public ImmeubleController() {
        pieModel = new PieChartModel();

    }

    public Immeuble getSelected() {
        return selected;
    }

    public void setSelected(Immeuble selected) {
        this.selected = selected;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public int getAnnee2() {
        return annee2;
    }

    public void setAnnee2(int annee2) {
        this.annee2 = annee2;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImmeubleFacade getFacade() {
        return ejbFacade;
    }

    public Immeuble prepareCreate() {
        selected = new Immeuble();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImmeubleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImmeubleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImmeubleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Immeuble> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Immeuble> getItemsByEmploye() {
        if (items == null) {
            HttpSession session = SessionUtils.getSession();
            Employe e1 = (Employe) session.getAttribute("user");
            if (e1.getProfilId().getLibelle().equals("admin")) {
                items = getFacade().findAll();
            } else {
                items = ef.findImmeublesByEmploye();
            }
        }

        System.out.println(" #### Immeubles " + items);
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

    public List<Immeuble> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Immeuble> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Immeuble.class)
    public static class ImmeubleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImmeubleController controller = (ImmeubleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "immeubleController");
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
            if (object instanceof Immeuble) {
                Immeuble o = (Immeuble) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Immeuble.class.getName()});
                return null;
            }
        }

    }

    public List<String> listeAnnees() {
        List<String> annees = new ArrayList<>();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > 2010; i--) {
            annees.add(i + "");
        }
        return annees;
    }

    public String[][] revenueByAppartement() {
        String[][] mList = new String[1][2];
        if (selected != null) {
            mList = new String[selected.getAppartementList().size()][14];
            int i = 0;
            double somme = 0;
            for (Appartement a : selected.getAppartementList()) {
                List<Revenue> revList = a.getRevenueList();
                List<Revenue> revenues = new ArrayList<>();
                for (Revenue r : revList) {
                    int year = r.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
                    System.out.println("sss " + year);
                    if (year == annee) {
                        revenues.add(r);

                    }
                }
                System.out.println("-------> " + revenues);
                mList[i][0] = a.getNum();

                for (Revenue rl : revenues) {
                    somme += rl.getMontant();
                    if (rl.getPeriodeId().getNom().equals("janvier")) {
                        mList[i][1] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("fevrier")) {
                        mList[i][2] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("mars")) {
                        mList[i][3] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("avril")) {
                        mList[i][4] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("mai")) {
                        mList[i][5] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("juin")) {
                        mList[i][6] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("juillet")) {
                        mList[i][7] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("aout")) {
                        mList[i][8] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("septembre")) {
                        mList[i][9] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("octobre")) {
                        mList[i][10] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("novembre")) {
                        mList[i][11] = String.valueOf(rl.getMontant());
                    } else if (rl.getPeriodeId().getNom().equals("decembre")) {
                        mList[i][12] = String.valueOf(rl.getMontant());
                    }
                    mList[i][13] = String.valueOf(somme);
                }

                i++;
                somme = 0;
            }

        }
        return mList;

    }

    public ChartModel revenueByImmeubleAndAnneesChart() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries revs = new ChartSeries();
        revs.setLabel("Revenues par immeuble et annee");
        String[] periodes = new String[12];
        double[] revenues = new double[12];
        int i = 0;
        for (Periode p : pf.findAll()) {
            periodes[i] = p.getNom();
            i++;
        }
        if (selected != null) {
            for (Appartement a : selected.getAppartementList()) {
                for (Revenue r : a.getRevenueList()) {
                    int year1 = r.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
                    if (year1 == annee2) {
                        for (int j = 0; j < 12; j++) {

                            if (r.getPeriodeId().getNom().equals(periodes[j])) {
                                revenues[j] += r.getMontant();
                            }
                        }
                    }
                }

            }
            for (int k = 0; k < 12; k++) {
                revs.set(periodes[k], revenues[k]);
            }
            model.addSeries(revs);
        }
        return model;
    }

    @PostConstruct
    public void init() {
        depensesByCategorie();
    }

    public void refresh() {
        depensesByCategorie();
    }

    public void depensesByCategorie() {
//        System.out.println("##########");
        if (selected == null) {
            selected = ejbFacade.find(1);
        } else {
            System.out.println("#h#h#h#" + selected);

        }
        List<Object[]> depenses = null;
//        pieModel.set("ggg", 25);
//        pieModel.set("jjjj", 50);

        if (selected != null) {
            System.out.println("hhh " + selected.getNom());
            depenses = df.depenseByCategorie(selected);
            for (Object dep[] : depenses) {
                System.out.println("  " + dep[0] + "jjj" + dep[1]);
                pieModel.set((String) dep[0], (double) dep[1]);
            }
            pieModel.setTitle("depenses ");
            pieModel.setLegendPosition("w");
            pieModel.setShowDataLabels(true);
            pieModel.setShadow(false);
        }
    }

    public List<Object[]> exporter() {
        List<Object[]> listes = null;
        if (selected != null) {
            listes = ejbFacade.exporter(selected);
            for (Object[] ob : listes) {
                System.out.println("ee## " + ob[1]);
            }
        }

        return listes;
    }

}
