package com.az.web.util;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil {

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage("successInfo", facesMsg);
    }
    public static boolean isAjaxRequest(){
        /*return FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest();*/
        return PrimeFaces.current().isAjaxRequest();
    }

    public static void hideDialog(String dialog) {
        PrimeFaces.current().executeScript("PF('"+dialog+").hide()");
    }

    public static void showDialog(String dialog) {
        PrimeFaces.current().executeScript("PF('"+dialog+"').show()");

    }
}
