package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.model.Cidade;
import br.ueg.biblio.model.Editora;
import br.ueg.biblio.model.Estado;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import org.primefaces.context.RequestContext;

@Named(value = "editoraBean")
@ViewScoped
public class EditoraBean implements Injetavel {

    @Inject
    private RepositoryCore repository;

    @Inject
    private PersistenceCore persistence;

    @NotNull(message = "Selecione um estado.")
    private Estado estado;

    private List<Cidade> listaCidades;
    private List<Estado> listaEstados;
    private List<Editora> listaEditoras;
    private Editora editora;
    private Editora editandoEditora;

    private boolean tabelaPronta;
    private boolean carregado;


    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarForm() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.carregado = false;
            prepararFormulário();
        } else {
            System.out.println("é postback".toUpperCase());
        }
    }

    public void configurarTabela() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.editandoEditora = new Editora();
            this.tabelaPronta = false;
        }
    }

    public void carregarDados() {
        this.listaEditoras = repository.listaDeEditoras();
        this.tabelaPronta = true;

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
        if (ManagedBeanUtil.isNotPostback()) {
        }
        // this.listaEstados = repository.listaDeEstados();
        this.editora = new Editora();

    }

    public void carregarCidadesPorEstado() {
        this.listaCidades = repository.cidadesPorEstado(this.estado);
    }


    /* METODOS DE ACAO */
    public void salvar() {

        if (persistence.persistir(editora)) {
            prepararFormulário();
            ManagedBeanUtil.addInfoMessage("Editora armazenado com sucesso.");
        } else {
            ManagedBeanUtil.addErrorMessage("Não foi ppossível armazenar informaçõe.");
        }
    }

    /* GETTERS E SETTERS */
    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Editora> getListaEditoras() {
        return listaEditoras;
    }

    public Editora getEditandoEditora() {
        return editandoEditora;
    }

    public void setEditandoEditora(Editora editandoEditora) {
        this.editandoEditora = editandoEditora;
    }

    public boolean isCarregado() {
        return this.carregado;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

}
