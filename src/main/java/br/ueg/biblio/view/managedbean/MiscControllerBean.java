package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "miscControllerBean")
@ViewScoped
public class MiscControllerBean implements Injetavel {

    private String erro;
    
    private List<String> listaString;

    public void configurarPagina() {
        this.listaString = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        this.listaString = (List<String>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("lista");
    }

    public List<String> getListaString() {
        return listaString;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

}
