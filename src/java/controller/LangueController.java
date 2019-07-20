/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.ejb.Local;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author mohss
 */
@ManagedBean(name = "lng")
@RequestScoped
public class LangueController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currentLocale = Locale.ENGLISH.toString();

    private static Map<String, Locale> availableLocales;

    static {
        availableLocales = new LinkedHashMap<String, Locale>();
        availableLocales.put("France", Locale.FRENCH);
        availableLocales.put("English", Locale.ENGLISH);

    }

    public Map<String, Locale> getAvailableLocales() {
        return availableLocales;
    }

    public LangueController() {
    }

    public void setCurrentLocale(String newLocale) {
        this.currentLocale = newLocale;
        System.out.println("########## currentLocale " + this.currentLocale);
        System.out.println("########## newLocale " + newLocale);
        for (Map.Entry<String, Locale> entry : availableLocales.entrySet()) {
            if (entry.getValue().toString().equals(newLocale)) {
                System.out.println("dddddddd " + entry.getValue());
                FacesContext.getCurrentInstance().getViewRoot().setLocale(entry.getValue());
            }
        }
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

}
