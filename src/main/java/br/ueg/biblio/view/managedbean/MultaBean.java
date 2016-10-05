package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Multa;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "multaBean")
@ViewScoped
public class MultaBean implements Injetavel {

    private boolean tabelaPronta;

    private Multa multa;

    private List<Multa> listaDeMultas;

    @Inject
    private RepositoryCore repository;

    @Inject
    private PersistenceCore persistence;

    /*METODOS DE CONFIGURAÇÕES*/
    public void prepararPage() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.tabelaPronta = false;
            this.multa = new Multa();
        }
    }

    public void carregarMultas() {
        this.listaDeMultas = repository.listaDeMultas();
        this.tabelaPronta = true;
    }

    /* METODOS DE ACAO */
    public void quitarMulta() {
    }

    /* GETTERS E SETTERS */
    public List<Multa> getListaDeMultas() {
        return listaDeMultas;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
}
/*METODOS DE CONFIGURAÇÕES*/
 /* METODOS DE ACAO */
 /* GETTERS E SETTERS */
