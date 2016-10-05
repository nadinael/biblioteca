package br.ueg.biblio.util;

import br.ueg.biblio.model.interfaces.Injetavel;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

public class ManagedBeanUtil implements Injetavel {

    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public static boolean isNotPostback() {
        return !isPostback();
    }

    public static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public static void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static void abriDialog(String titulo) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog(titulo, options, null);
    }

    /*METODOS PRIVADOS*/
    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static void getExternal() {
        
        URL location = ManagedBeanUtil.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        System.out.println(location.getPath());

    }

    public static void guardado() {
        addInfoMessage("Informações guardadas com sucesso.");
    }

    public static void atualizado() {
        addInfoMessage("Informações autualizadas com sucesso.");
    }

    public static void erroSalvar() {
        addErrorMessage("Ocorreu um erro ao tentar guardar informações.");
    }

    public static void addGrwolInfo(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMAÇÂO", texto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("chaveLogin", message);
    }

    public static void addGrwolError(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", texto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("chaveLogin", message);
    }

}
