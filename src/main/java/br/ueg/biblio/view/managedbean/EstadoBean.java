package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.persistence.SuperDAO;
import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Cidade;
import br.ueg.biblio.model.Estado;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "estadoBean")
@ViewScoped
public class EstadoBean implements Serializable {

    private Estado editandoEstado;

    private Estado estado;

    private List<Estado> listaEstados;

    private boolean tabelaPronta;

    @Inject
    private RepositoryCore repository;

    @Inject
    private PersistenceCore persistence;

    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarTabela() {

        if (ManagedBeanUtil.isNotPostback()) {
            System.out.println("não é postback".toUpperCase());
            this.editandoEstado = new Estado();
            this.tabelaPronta = false;
        } else {
            System.out.println("é postback".toUpperCase());
        }
    }

    public void configurarForm() {
        if (ManagedBeanUtil.isNotPostback()) {
            System.out.println("não é postback".toUpperCase());
            this.estado = new Estado();

        } else {
            System.out.println("é postback".toUpperCase());
        }

    }

    public void carregarDados() {
        this.tabelaPronta = true;
        this.listaEstados = repository.listaDeEstados();

    }

    /* METODOS DE ACAO */
    public void atualizar(Estado estado) {

    }

    public void salvar(Estado estado) {
        if (persistence.persistir(estado)) {
            ManagedBeanUtil.addInfoMessage("Informações armazenadas com sucesso.");
            this.estado = new Estado();
        } else {
            ManagedBeanUtil.addErrorMessage("Não foi possível salvar informações");
        }
    }

    /* GETTERS E SETTERS */
    public Estado getEditandoEstado() {
        return editandoEstado;
    }

    public void setEditandoEstado(Estado editandoEstado) {
        this.editandoEstado = editandoEstado;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}