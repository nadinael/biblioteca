package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Idioma;
import br.ueg.biblio.model.Marca;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "tagBean")
@ViewScoped
public class TagBean implements Injetavel {

    @Inject
    private PersistenceCore persistence;

    @Inject
    private RepositoryCore repository;

    private boolean tabelaPronta;

    private boolean estaSelecionado;

    private Marca marca;

    private Marca editandoMarca;

    private List<Marca> listaMarcas;

    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarForm() {
        if (ManagedBeanUtil.isNotPostback()) {
            prepararFormulário();
            this.editandoMarca = new Marca();
            this.tabelaPronta = false;
            this.estaSelecionado = false;

        }
    }

    private void prepararFormulário() {
        this.marca = new Marca();
    }

    public void configurarTabela() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.editandoMarca = new Marca();
            this.tabelaPronta = false;
        }

    }

    public void carregarDados() {
        this.listaMarcas = repository.listaDeMarcas();
        this.tabelaPronta = true;
    }

    /* METODOS DE ACAO */
    public void salvar() {
        if (persistence.persistir(marca)) {
            prepararFormulário();
            carregarDados();
            ManagedBeanUtil.guardado();
        } else {
            ManagedBeanUtil.erroSalvar();
        }
    }

    public void editar() {
        //this.marca = ma;
        // System.out.println("<> : " + this.editandoMarca.getDescricao());
        //System.out.println("<> : " + this.marca.getDescricao());
        System.out.println("editando");
    }

    /* GETTERS E SETTERS */
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Marca getEditandoMarca() {
        return editandoMarca;
    }

    public void setEditandoMarca(Marca editandoMarca) {
        System.out.println("editando");
        this.editandoMarca = editandoMarca;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public void setTabelaPronta(boolean tabelaPronta) {
        this.tabelaPronta = tabelaPronta;
    }

    public List<Marca> getListaMarcas() {
        return listaMarcas;
    }

    public void linhaSelecionada() {
        System.out.println("Linha selecionada");
        this.estaSelecionado = true;
    }

    public void linhaNaoSelecionada() {
        System.out.println("Linha NOT selecionada");
    }

    public boolean isEstaSelecionado() {
        return estaSelecionado;
    }

}
