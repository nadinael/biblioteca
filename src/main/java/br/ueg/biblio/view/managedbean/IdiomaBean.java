package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Autor;
import br.ueg.biblio.model.Idioma;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import javax.inject.Inject;

@Named(value = "idiomaBean")
@ViewScoped
public class IdiomaBean implements Injetavel {

    @Inject
    private PersistenceCore persistence;

    @Inject
    private RepositoryCore repository;

    private Idioma idioma;

    private Idioma editandoIdioma;

    private List<Idioma> listaIdiomas;

    private boolean tabelaPronta;

    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarForm() {

        if (ManagedBeanUtil.isNotPostback()) {
            System.out.println("não é postback".toUpperCase());
            prepararFormulário();

        } else {
            System.out.println("é postback".toUpperCase());
        }
    }

    private void prepararFormulário() {
        this.setIdioma(new Idioma());
    }

    public void configurarTabela() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.editandoIdioma = new Idioma();
            this.tabelaPronta = false;
        } else {
            System.out.println("é postback".toUpperCase());
        }

    }

    public void carregarDados() {
        this.listaIdiomas = repository.listaDeIdiomas();
        this.tabelaPronta = true;
    }

    /* METODOS DE ACAO */
    public void salvar() {
        if (persistence.persistir(getIdioma())) {
            prepararFormulário();
            ManagedBeanUtil.addInfoMessage("Idioma armazenado com sucesso.");
        } else {
            ManagedBeanUtil.addErrorMessage("Não foi ppossível armazenar informaçõe.");
        }
    }

    /* GETTERS E SETTERS */
    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public Idioma getEditandoIdioma() {
        return editandoIdioma;
    }

    public void setEditandoIdioma(Idioma editandoIdioma) {
        this.editandoIdioma = editandoIdioma;
    }

}