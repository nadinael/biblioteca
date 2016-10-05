package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Autor;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "autorBean")
@ViewScoped
public class AutorBean implements Serializable {

    @Inject
    private PersistenceCore persistence;

    @Inject
    private RepositoryCore repository;

    private Autor autor;

    private Autor editandoAutor;

    private boolean tabelaPronta;

    private List<Autor> listaAutores;

    public void configurarForm() {
        if (ManagedBeanUtil.isNotPostback()) {
            prepararFormulário();
        } else {
            System.out.println("é postback".toUpperCase());
        }
    }

    private void prepararFormulário() {
        this.autor = new Autor();

    }

    public void configurarTabela() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.editandoAutor = new Autor();
            this.tabelaPronta = false;

        } else {
            System.out.println("é postback".toUpperCase());
        }
    }

    public void carregarDados() {
        this.listaAutores = repository.listaDeAutores();
        this.tabelaPronta = true;
    }

    public void salvar() {

        if (persistence.persistir(getAutor())) {
            prepararFormulário();
            ManagedBeanUtil.addInfoMessage("Autor armazenado com sucesso.");
        } else {
            ManagedBeanUtil.addErrorMessage("Não foi ppossível armazenar informaçõe.");
        }
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Autor getEditandoAutor() {
        return editandoAutor;
    }

    public void setEditandoAutor(Autor editandoAutor) {
        this.editandoAutor = editandoAutor;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }
}
