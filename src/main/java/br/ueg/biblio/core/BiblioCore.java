package br.ueg.biblio.core;

import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.enumerated.DisponibilidadeStatus;
import br.ueg.biblio.model.enumerated.EmprestimoStatus;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.time.LocalDateTime;

public class BiblioCore implements Injetavel {

    public static Emprestimo novoEmprestimo(Emprestimo emprestimo) {
        emprestimo.setStatus(EmprestimoStatus.P);
        emprestimo.getCopia().setDispStatus(DisponibilidadeStatus.E);
        return emprestimo;
    }

    public static Emprestimo devolverCopia(Emprestimo emprestimo) {
        System.out.println("br.ueg.biblio.core.BiblioCore.devolverCopia()");
        emprestimo.setStatus(EmprestimoStatus.D);
        emprestimo.getCopia().setDispStatus(DisponibilidadeStatus.D);
        emprestimo.setDataDevolucao(ControleDataDeEmprestimo.conLocalDateTimeParaCalendar(LocalDateTime.now()));
        return emprestimo;
    }

    /*GETTERS E SETTERS*/
}
