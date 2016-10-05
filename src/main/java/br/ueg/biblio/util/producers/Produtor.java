package br.ueg.biblio.util.producers;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.annotations.Tarefas;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.loan.GeradorMulta;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.tarefas.ManagerTask;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class Produtor implements Injetavel {

    @Produces
    @Configurado
    @RequestScoped
    public GeradorMulta injetarMulta() {
        System.out.println("br.ueg.biblio.util.producers.Produtor.injetarMulta()".toUpperCase());
        return new GeradorMulta();
    }

    @Produces
    @Configurado
    @RequestScoped
    public ManagerTask injetarTarefa() {
        System.out.println("br.ueg.biblio.util.producers.Produtor.injetarTarefa()".toUpperCase());
        return new ManagerTask();
    }

}
