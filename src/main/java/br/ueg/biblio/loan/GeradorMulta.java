package br.ueg.biblio.loan;

import br.ueg.biblio.cfg.BiblioCfg;
import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.core.ControleDataDeEmprestimo;
import br.ueg.biblio.files.BiblioCfgFileManager;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.Multa;
import br.ueg.biblio.model.enumerated.EmprestimoStatus;
import br.ueg.biblio.model.enumerated.MultaStatus;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.EMUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

public class GeradorMulta implements Injetavel {

    private static final long serialVersionUID = 1L;

    private LocalDateTime ldt;
    private List<Emprestimo> listaDeEmprestimos;
    private List<Multa> listaDeMultas;
    private EntityManager manager;
    private BiblioCfg emprestimoCfg;
    private Period periodo;

    public void verificarEmprestimos(DataBaseModel base, BiblioCfg cfg) {
        this.listaDeEmprestimos = obterListaLoan(base);
        emprestimoCfg = cfg;
        manager = EMUtil.createEntityManagerFromModel(base);
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
                multa.setValorInicial(emprestimoCfg.getTaxaInicial());
                multa.setValorFinal(emprestimoCfg.getTaxaInicial());
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

        System.out.println("FIM - br.ueg.biblio.loan.GeradorMulta.verificarEmprestimo()".toUpperCase());
    }

    public void verificarMultas(DataBaseModel base) {

        this.listaDeMultas = obterListaMultas(base);
        emprestimoCfg = BiblioCfgFileManager.getBiblioCfg();

        for (Multa multa : listaDeMultas) {
            LocalDate agora = LocalDate.now();
            LocalDate data = ControleDataDeEmprestimo.conCalendarParaLocalDate(multa.getDataGerada());

            if (agora.isAfter(data)) {
                periodo = Period.between(data, agora);

                System.out.println("DIAS QUE SE PASSARAM DESDE QUE A MULTA FOI GERADA: " + periodo.getDays());
            }
        }

        System.out.println("br.ueg.biblio.loan.GeradorMulta.verificarMultas()");
    }

    private List<Emprestimo> obterListaLoan(DataBaseModel base) {
        manager = EMUtil.createEntityManagerFromModel(base);
        List<Emprestimo> lista = manager.createQuery("select e from Emprestimo e", Emprestimo.class).getResultList();
        EMUtil.closeEntityManager(manager);
        return lista;
    }

    private List<Multa> obterListaMultas(DataBaseModel base) {
        manager = EMUtil.createEntityManagerFromModel(base);
        List<Multa> lista = manager.createQuery("select e from Multa e", Multa.class).getResultList();
        EMUtil.closeEntityManager(manager);
        return lista;
    }

    private void registrarUpdate(Emprestimo emp) {
        manager = EMUtil.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(emp);
        manager.flush();
        manager.getTransaction().commit();
        EMUtil.closeEntityManager(manager);
    }

    private void gerarMulta(Emprestimo emp) {
        manager = EMUtil.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(emp);
        manager.flush();
        manager.getTransaction().commit();
        EMUtil.closeEntityManager(manager);
    }

}
