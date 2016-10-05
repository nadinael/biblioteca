package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Cidade;
import br.ueg.biblio.model.Estado;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.enumerated.Sexo;
import br.ueg.biblio.model.enumerated.TUsuario;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.primefaces.context.RequestContext;

/**
 * Manage Bean será responsável por valições básicas, como valores nulos. Regras
 * de negócio é responsabilidade do serviço
 *
 * @author nadinael
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Injetavel {

    private String teste;
    private String msg;

    @Inject
    private RepositoryCore repository;

    @Inject
    private PersistenceCore persistence;

    @NotNull(message = "Selecione um estado.")
    private Estado estado;

    @NotNull(message = "Usuário não pode ser nulo!")
    private Usuario usuario;

    private Usuario editandoUsuario;

    private boolean tabelaPronta;
    private boolean carregado;

    private List<Usuario> listaUsuarios;
    private List<Cidade> listaCidades;
    private List<Estado> listaEstados;

    /*METODOS DE CONFIGURAÇÕES*/
    public void confirgurarForm() {

        if (ManagedBeanUtil.isNotPostback()) {
            prepararFormulário();
        }
    }

    private void prepararFormulário() {
        usuario = new Usuario();
        //listaEstados = repository.listaDeEstados();
        usuario.setTipo(TUsuario.A);
        usuario.setSexo(Sexo.F);
        this.carregado = false;
    }

    public void carregarEstadosAjax() {
        if (!isCarregado()) {
            this.listaEstados = repository.listaDeEstados();
            RequestContext reqCtx = RequestContext.getCurrentInstance();
            reqCtx.execute("PF('requisicao').stop()");
            this.carregado = true;
        }

    }

    public void carregarDados() {
        this.listaUsuarios = repository.listaDeUsuarios();
        this.tabelaPronta = true;
    }

    public void configurarTabela() {

        if (ManagedBeanUtil.isNotPostback()) {
            this.editandoUsuario = new Usuario();
            this.tabelaPronta = false;
        }

    }

    public void carregarCidades() {
        this.listaCidades = repository.cidadesPorEstado(this.estado);
    }

    /* METODOS DE ACAO */
    public void salvar() {
        if (!repository.cpfDuplicado(usuario.getCpf()) && !repository.loginEstaCadastrado(usuario.getUsername())) {
            persistence.adicionarUsuario(usuario);
            ManagedBeanUtil.guardado();
            prepararFormulário();
        } else if (repository.cpfDuplicado(usuario.getCpf())) {
            ManagedBeanUtil.addErrorMessage("CPF Duplicado");
        } else if (repository.loginEstaCadastrado(usuario.getUsername())) {
            ManagedBeanUtil.addErrorMessage("Este Nome de Usuário ja foi cadastrado, tente outro!");
        }

    }

    public void verificarCPF() {
        if (repository.cpfDuplicado(usuario.getCpf())) {
            ManagedBeanUtil.addInfoMessage("CPF ja está cadastrado.");
        }
    }

    public void verificarUsername() {
        if (repository.loginEstaCadastrado(usuario.getUsername())) {
            ManagedBeanUtil.addInfoMessage("Este nome de usuário ja está sendo usado.");
        }
    }


    /* GETTERS E SETTERS */
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void testar() {
        if (repository.cpfDuplicado(teste)) {
            msg = "cpf duplicado".toUpperCase();
        } else {
            msg = "cpf válido".toUpperCase();
            System.out.println(msg + " - FINAL");
        }

    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Usuario getEditandoUsuario() {
        return editandoUsuario;
    }

    public void setEditandoUsuario(Usuario editandoUsuario) {
        this.editandoUsuario = editandoUsuario;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public void setTabelaPronta(boolean tabelaPronta) {
        this.tabelaPronta = tabelaPronta;
    }

    public boolean isCarregado() {
        return carregado;
    }

}
