package br.ueg.biblio.util;

import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class JSFMsgs implements Injetavel {

    public static void errorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public static void infoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public static void abriDialog(String titulo) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog(titulo, options, null);
    }

    public static void guardado() {
        infoMessage("Informações guardadas com sucesso.");
    }

    public static void atualizado() {
        infoMessage("Informações autualizadas com sucesso.");
    }

    public static void erroSalvar() {
        errorMessage("Ocorreu um erro ao tentar guardar informações.");
    }

    public static void pfGrwolInfo(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMAÇÂO", texto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("chaveLogin", message);
    }

    public static void pfGrwolError(String texto) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", texto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("chaveLogin", message);
    }

}
