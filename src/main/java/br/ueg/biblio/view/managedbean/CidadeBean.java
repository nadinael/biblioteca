package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.persistence.SuperDAO;
import br.ueg.biblio.model.Cidade;
import br.ueg.biblio.model.Estado;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.enumerated.Sexo;
import br.ueg.biblio.model.enumerated.TUsuario;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.primefaces.context.RequestContext;

@Named(value = "cidadeBean")
@ViewScoped
public class CidadeBean implements Injetavel {

    @Inject
    private SuperDAO dao;

    @Inject
    private RepositoryCore repository;

    @NotNull(message = "Não é possível salvar uma cidade nula.")
    private Cidade cidade;

    private Cidade editandoCidade;

    private List<Cidade> listaCidades;

    private List<Estado> listaEstados;

    private boolean tabelaPronta;

    private boolean carregado;

    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarForm() {

        if (ManagedBeanUtil.isNotPostback()) {
            prepararFormulário();
        } else {

        }

    }

    public void carregarEstadosAjax() {
        if (!isCarregado()) {
            this.listaEstados = repository.listaDeEstados();
            RequestContext reqCtx = RequestContext.getCurrentInstance();
            reqCtx.execute("PF('requisicao').stop()");
            this.carregado = true;
        }

    }

    private void prepararFormulário() {
        this.cidade = new Cidade();
        //this.listaEstados = repository.listaDeEstados();

    }

    public void configurarTabela() {

        if (ManagedBeanUtil.isNotPostback()) {
            System.out.println("não é postback".toUpperCase());
            this.tabelaPronta = false;
            this.editandoCidade = new Cidade();
        } else {
            System.out.println("é postback".toUpperCase());
        }

    }

    public void carregarDados() {
        this.tabelaPronta = true;
        listaCidades = repository.listaDeCidades(); // dao.listar(Cidade.class);
    }

    /* INTERACOES DO SISTEMA */
    public void salvar() {
        if (dao.salvar(cidade)) {
            ManagedBeanUtil.addInfoMessage("Cidade armazenada com sucesso!");
            prepararFormulário();
        } else {
            ManagedBeanUtil.addErrorMessage("Erro ao salvar cidade.");
        }
    }

    /* GETTERS E SETTERS */
    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public Cidade getEditandoCidade() {
        return editandoCidade;
    }

    public void setEditandoCidade(Cidade editandoCidade) {
        this.editandoCidade = editandoCidade;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public boolean isCarregado() {
        return carregado;
    }

}
