package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.cfg.BiblioCfg;
import br.ueg.biblio.core.ControleDataDeEmprestimo;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.files.BiblioCfgFileManager;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.Multa;
import br.ueg.biblio.model.enumerated.EmprestimoStatus;
import br.ueg.biblio.model.enumerated.MultaStatus;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Named(value = "agendaBean")
@RequestScoped
public class AgendaBean implements Injetavel {

    private LocalDateTime ldt;

    private List<Multa> listaDeMultas;

    private BiblioCfg biblioCfg;
    private Period periodo;
    private List<Emprestimo> listaDeEmprestimos;

    @Inject
    private RepositoryCore repository;

    @Inject
    @Configurado
    private EntityManager manager;

    public AgendaBean() {
        System.out.println("BEAN POR REQUISICAO");
        this.biblioCfg = BiblioCfgFileManager.getBiblioCfg();
    }

    public void requisitar() {
        System.out.println("br.ueg.biblio.view.managedbean.AgendaBean.requisitar()".toUpperCase());
        verificarEmprestimos();
    }

    private void verificarEmprestimos() {
        listaDeEmprestimos = repository.listaDeEmprestimos();

        manager.getTransaction().begin();
        for (Emprestimo loan : listaDeEmprestimos) {
            ldt = LocalDateTime.now();
            LocalDateTime dataE = ControleDataDeEmprestimo.conCalendarParaLocalDateTime(loan.getDataEstimadaDev());

            if ((ldt.isAfter(dataE)) && (loan.getStatus() == EmprestimoStatus.P)) {
                System.out.println("GERAR MULTA PARA EMPRESTIMO " + loan.getId());
                System.out.println("GERAR MULTA NA DATA  " + loan.getDataEmprestimo().getTime());
                System.out.println("TITULO " + loan.getTitulo().getNome());
                System.out.println("GERAR MULTA COPIA " + loan.getCopia().getCodigo());

                loan.setStatus(EmprestimoStatus.M);

                Multa multa = new Multa();
                multa.setValorInicial(biblioCfg.getTaxaInicial());
                multa.setValorFinal(biblioCfg.getTaxaInicial());
                multa.setDataGerada(Calendar.getInstance());
                multa.setStatus(MultaStatus.P);
                multa.setEmprestimo(loan);
                multa.setLeitor(loan.getLeitor());

                manager.merge(loan);
                manager.persist(multa);
            }
        }
        manager.flush();
        manager.getTransaction().commit();
    }

}
