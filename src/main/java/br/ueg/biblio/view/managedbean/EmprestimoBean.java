package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.BiblioCore;
import br.ueg.biblio.core.ControleDataDeEmprestimo;
import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.Exemplar;
import br.ueg.biblio.model.Titulo;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.enumerated.DisponibilidadeStatus;
import br.ueg.biblio.model.enumerated.EmprestimoStatus;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.JSFMsgs;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.omnifaces.util.Faces;

@Named(value = "emprestimoBean")
@ViewScoped
public class EmprestimoBean implements Injetavel {

    /*GERENCIADO POR AÇÕES*/
    private boolean selecionado;
    private boolean tabelaPronta;
    private Usuario leitor;
    private Usuario usuario;
    private Emprestimo emprestimo;
    private Emprestimo editandoEmprestimo; //private Titulo titulo;
    private Exemplar copia;
    private Date inicio;
    private Date fim;


    /*GERENCIADO POR CDI*/
    private List<Usuario> listaUsuarios;
    private List<Titulo> listaTitulos;
    private List<Emprestimo> listaEmprestimo;

    @Inject
    private RepositoryCore repository;

    @Inject
    private PersistenceCore persistence;

    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarTabela() {
    }

    public boolean isCanDevolve() {
        if (this.editandoEmprestimo != null) {
            if ((this.editandoEmprestimo.getStatus() == EmprestimoStatus.D)
                    || this.editandoEmprestimo.getStatus() == EmprestimoStatus.C) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void configurarPagina() {
        if (ManagedBeanUtil.isNotPostback()) {
            prepararForm();
            this.usuario = recuperarUsuario();
            this.tabelaPronta = false;
            if (this.usuario == null) {
                try {
                    Faces.redirect(Faces.getRequestBaseURL());
                } catch (IOException ex) {
                    System.out.println("erro ao redirecionar.".toUpperCase());
                }

            }
        }
    }

    public void prepararForm() {
        this.selecionado = false;
        recuperarUsuario();
        this.leitor = new Usuario();
        this.emprestimo = new Emprestimo();
        this.tabelaPronta = false;
        gerarDateDev();
    }

    public void gerarDateDev() {
        this.emprestimo.setDataEmprestimo(Calendar.getInstance());
        this.emprestimo.setDataEstimadaDev(ControleDataDeEmprestimo.gerarDevolucao(emprestimo));
        this.setInicio(ControleDataDeEmprestimo.conCalendarParaDate(emprestimo.getDataEmprestimo()));
        this.setFim(ControleDataDeEmprestimo.conCalendarParaDate(emprestimo.getDataEstimadaDev()));

    }

    private Usuario recuperarUsuario() {
        return (Usuario) Faces.getSession().getAttribute("usuario_logado");
    }

    public void carregarLeitores() {
        this.listaUsuarios = repository.listaDeUsuarios();
    }

    public void carregarTitulos() {
        this.listaTitulos = repository.listaDeTitulos();
    }

    public void carregarEmprestimos() {
        this.listaEmprestimo = repository.listaDeEmprestimos();
        this.tabelaPronta = true;
    }


    /* METODOS DE ACAO */
    public void efetuarDevolucao() {
        System.out.println("br.ueg.biblio.view.managedbean.EmprestimoBean.efetuarDevolucao()");
        if ((this.editandoEmprestimo.getLeitor() == null)
                || (this.editandoEmprestimo.getId() == null)
                || (this.editandoEmprestimo.getCopia() == null)) {
            JSFMsgs.errorMessage("Verifque se os dados foram preenchidos corretamente.");
        } else {
            this.editandoEmprestimo.setMutuario(this.usuario);
            this.editandoEmprestimo = BiblioCore.devolverCopia(editandoEmprestimo);
            if (persistence.efetuarDevolucao(emprestimo)) {
                JSFMsgs.pfGrwolInfo("Atualizado");
                prepararForm();
            } else {
                JSFMsgs.erroSalvar();
            }
        }
    }

    public void efetuarEmprestimo() {
        if ((this.emprestimo.getLeitor() == null)
                || (this.emprestimo.getCopia() == null)
                || (this.emprestimo.getCopia().getDispStatus() != DisponibilidadeStatus.D)) {
            JSFMsgs.errorMessage("Verifique se os dados foram preenchidos corretamente.");
        } else {
            this.emprestimo = BiblioCore.novoEmprestimo(emprestimo);
            this.emprestimo.setLocador(this.usuario);
            if (persistence.adicionarEmprestimo(emprestimo)) {
                this.tabelaPronta = false;
                JSFMsgs.guardado();

                prepararForm();
            } else {
                JSFMsgs.erroSalvar();
            }
        }
    }

    public void adicionaLeitor(Usuario leitor) {
        this.emprestimo.setLeitor(leitor);
    }

    public void selecionaTitulo(Titulo titulo) {
        this.emprestimo.setTitulo(titulo);
    }

    public void adicionaCopia(Exemplar copia) {
        this.emprestimo.setCopia(copia);
    }

    public void selecionarEmprestimo() {
        System.out.println("EMPRÉSTIMO SELECIONADO");
        this.selecionado = true;
    }

    /* GETTERS E SETTERS */
    public Usuario getLeitor() {
        return leitor;
    }

    public void setLeitor(Usuario leitor) {
        this.leitor = leitor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public boolean isTabelaPronta() {
        return tabelaPronta;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Titulo> getListaTitulos() {
        return listaTitulos;
    }

    public Exemplar getCopia() {
        return copia;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public List<Emprestimo> getListaEmprestimo() {
        return listaEmprestimo;
    }

    public Emprestimo getEditandoEmprestimo() {
        return editandoEmprestimo;
    }

    public void setEditandoEmprestimo(Emprestimo editandoEmprestimo) {
        System.out.println("br.ueg.biblio.view.managedbean.EmprestimoBean.setEditandoEmprestimo()".toUpperCase());
        System.out.println(editandoEmprestimo.getCopia().getCodigo());
        this.editandoEmprestimo = editandoEmprestimo;
    }

}
