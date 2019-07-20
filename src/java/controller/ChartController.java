/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Appartement;
import entity.Immeuble;
import entity.Periode;
import entity.Revenue;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import service.PeriodeFacade;

/**
 *
 * @author dell
 */
@ManagedBean(name = "chartController")
@RequestScoped
public class ChartController {

    /**
     * Creates a new instance of ChartController
     */
    private Immeuble immeuble;
    private int annee2;
    private PeriodeFacade pf;
    private PeriodeController pc;
    List<Periode> items = null;

    public ChartController() {
        items = new ArrayList<>();
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    public List<Periode> getPeriodes() {
        if (items == null) {
            items = getPf().findAll();
            System.out.println(" 7777 " + pc.getItems());
        }
        return items;
    }

    public void setPeriodes(List<Periode> items) {
        this.items = items;
    }

    public int getAnnee2() {
        return annee2;
    }

    public void setAnnee2(int annee2) {
        this.annee2 = annee2;
    }

    public PeriodeFacade getPf() {
        return pf;
    }

    public void setPf(PeriodeFacade pf) {
        this.pf = pf;
    }

    public PeriodeController getPc() {
        return pc;
    }

    public void setPc(PeriodeController pc) {
        this.pc = pc;
    }

    public List<String> listeAnnees() {
        List<String> annees = new ArrayList<>();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > 2010; i--) {
            annees.add(i + "");
        }
        return annees;
    }

    public ChartModel revenueByImmeubleAndAnneeChart() {
        CartesianChartModel model = new CartesianChartModel();
        double s1 = 0, s2 = 0, s3 = 0, s4 = 0, s5 = 0, s6 = 0, s7 = 0, s8 = 0, s9 = 0, s10 = 0, s11 = 0, s12 = 0;
        ChartSeries revs = new ChartSeries();
        revs.setLabel("Revenues");

        String[] periodes = new String[12];
        double[] re = new double[12];
        periodes[0] = "jjj";
        periodes[1] = "kkk";
        periodes[2] = "jj";
        periodes[3] = "jddjj";
        periodes[4] = "jjjff";
        periodes[5] = "jjjcc";
        periodes[6] = "jjjdd";
        periodes[7] = "jjj";
        periodes[8] = "kkk";
        periodes[9] = "jj";
        periodes[10] = "jddjj";
        periodes[11] = "jjjff";
        int j = 0;
//        List<Periode> pers = new ArrayList<>();
//        pers = getPf().findAll();
//        System.out.println("####### " + pers);
//        for (Periode p : items) {
//            System.out.println("####### " + p.getNom());
//            periodes[j] = p.getNom();
//            j++;
//        }
        if (immeuble != null) {
            System.out.println("imme  "+immeuble.getNom());
            for (Appartement a : immeuble.getAppartementList()) {
                for (Revenue r : a.getRevenueList()) {
                    System.out.println("reer "+r.getMontant() );
                    int year1 = r.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
                    System.out.println("annee "+annee2 +" year "+year1);
                    if (year1 == annee2) {
                        System.out.println(" rr "+annee2);
                        if (r.getPeriodeId().getNom().equals("janvier")) {
                            s1 += r.getMontant();
                            System.out.println("s1 "+s1);
                        } else if (r.getPeriodeId().getNom().equals("fevrier")) {
                            s2 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("mars")) {
                            s3 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("avril")) {
                            s4 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("mai")) {
                            s5 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("juin")) {
                            s6 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("juillet")) {
                            s7 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("aout")) {
                            s8 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("septembre")) {
                            s9 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("octobre")) {
                            s10 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("novembre")) {
                            s11 += r.getMontant();
                        } else if (r.getPeriodeId().getNom().equals("decembre")) {
                            s12 += r.getMontant();
                        }
                    }

                }
            }
            re[0] = s1;
            re[1] = s2;
            re[2] = s3;
            re[3] = s4;
            re[4] = s5;
            re[5] = s6;
            re[6] = s7;
            re[7] = s8;
            re[8] = s9;
            re[9] = s10;
            re[10] = s11;
            re[11] = s12;

            for (int i = 0; i < 12; i++) {
                System.out.println("---- " + periodes[i] + " - --- " + re[i]);
                revs.set(periodes[i], re[i]);
            }
            model.addSeries(revs);

        }

        return model;
    }
}
