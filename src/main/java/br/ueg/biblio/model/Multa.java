package br.ueg.biblio.model;

import br.ueg.biblio.model.enumerated.MultaStatus;
import java.util.Calendar;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "multa")
public class Multa extends Simples {

    @NotNull(message = "Esta multa pertence obrigatoriamente a um empréstimo de título.")
    @OneToOne(targetEntity = Emprestimo.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "emprestimo_id")
    private Emprestimo emprestimo;

    @NotNull(message = "Uma multa pertence a um leitor específico.")
    @ManyToOne(targetEntity = Usuario.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "leitor_id")
    private Usuario leitor;

    @ManyToOne(targetEntity = Usuario.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "mutuario_id")
    private Usuario mutuario;

    /**
     * ***********************************
     */
    @NotNull(message = "Especifique a data.")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_gerada")
    private Calendar dataGerada;

    @NotNull(message = "Um status precisa ser definido.")
    @Enumerated(EnumType.STRING)
    @Column(name = "multa_status", columnDefinition = "char(1)", nullable = false)
    private MultaStatus status;

    @NotNull(message = "Especifique um valor inicial.")
    @Column(name = "valor_inicial", nullable = false)
    private double valorInicial;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = true, name = "data_pagamento")
    private Calendar dataPagamento;

    @Column(name = "valor_final", nullable = true)
    private double valorFinal;

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Usuario getLeitor() {
        return leitor;
    }

    public void setLeitor(Usuario leitor) {
        this.leitor = leitor;
    }

    public Usuario getMutuario() {
        return mutuario;
    }

    public void setMutuario(Usuario mutuario) {
        this.mutuario = mutuario;
    }

    public Calendar getDataGerada() {
        return dataGerada;
    }

    public void setDataGerada(Calendar dataGerada) {
        this.dataGerada = dataGerada;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public MultaStatus getStatus() {
        return status;
    }

    public void setStatus(MultaStatus status) {
        this.status = status;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

}
