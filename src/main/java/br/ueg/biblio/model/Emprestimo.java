package br.ueg.biblio.model;

import br.ueg.biblio.model.enumerated.EmprestimoStatus;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emprestimo")
public class Emprestimo extends Simples {

    @Transient
    private Multa multa;

    @Transient
    private int diasAdicionais;

    @NotNull(message = "Usuário alocador não pode ser nulo.")
    @ManyToOne(targetEntity = Usuario.class, optional = false,
            fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "locador_id")
    private Usuario locador;

    @ManyToOne(targetEntity = Usuario.class, optional = true,
            fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "mutuario_id")
    private Usuario mutuario;

    @NotNull(message = "Um leitor deve ser selecionado.")
    @ManyToOne(targetEntity = Usuario.class, optional = false,
            fetch = FetchType.EAGER)//@JoinColumn(nullable = false, name = "leitor_id", unique = false)
    private Usuario leitor;

    @NotNull(message = "Uma cópia deve ser adicionada.")
    @ManyToOne(targetEntity = Exemplar.class, optional = false,
            fetch = FetchType.EAGER)//@JoinColumn(nullable = false, name = "exemplar_id", unique = false)
    private Exemplar copia;

    @NotNull(message = "Um título deve ser adicionada.")
    @ManyToOne(targetEntity = Titulo.class, optional = false,
            fetch = FetchType.EAGER)//@JoinColumn(nullable = false, name = "exemplar_id", unique = false)
    private Titulo titulo;

    /*----------------------------------------------------------------------*/
    @NotNull(message = "O Status do empréstimo não pode ser nulo.")
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "char(1)", nullable = false)
    private EmprestimoStatus status;

    @NotNull(message = "A data do empréstimo deve ser informada.")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_emprestimo")
    private Calendar dataEmprestimo;

    @NotNull(message = "A data prevista de entrega deve ser calculada.")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_estimada")
    private Calendar dataEstimadaDev;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = true, name = "data_devolucao")
    private Calendar dataDevolucao;

    public Usuario getLeitor() {
        return leitor;
    }

    public void setLeitor(Usuario leitor) {
        System.out.println("set leitor: " + leitor.getNome());
        this.leitor = leitor;
    }

    public Exemplar getCopia() {
        return copia;
    }

    public void setCopia(Exemplar copia) {
        this.copia = copia;
    }

    public EmprestimoStatus getStatus() {
        return status;
    }

    public void setStatus(EmprestimoStatus status) {
        this.status = status;
    }

    public Calendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Calendar getDataEstimadaDev() {
        return dataEstimadaDev;
    }

    public void setDataEstimadaDev(Calendar dataEstimadaDev) {
        this.dataEstimadaDev = dataEstimadaDev;
    }

    public Calendar getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public int getDiasAdicionais() {
        return diasAdicionais;
    }

    public void setDiasAdicionais(int diasAdicionais) {
        this.diasAdicionais = diasAdicionais;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Usuario getLocador() {
        return locador;
    }

    public void setLocador(Usuario locador) {
        this.locador = locador;
    }

    public Usuario getMutuario() {
        return mutuario;
    }

    public void setMutuario(Usuario mutuario) {
        this.mutuario = mutuario;
    }

}
