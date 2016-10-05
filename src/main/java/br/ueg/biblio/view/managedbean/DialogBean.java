package br.ueg.biblio.view.managedbean;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named(value = "dialogBean")
@ViewScoped
public class DialogBean implements Serializable {

    private String valor;

    public void saindoDialog() {
    }

    public void abrir() {

        RequestContext.getCurrentInstance().execute("meuDialogo.show();return false;");
        System.err.println("Abrindo");
    }

    public String getValor() {
        return valor;
    }

    public void fechando() {
        RequestContext.getCurrentInstance().closeDialog(valor);
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
