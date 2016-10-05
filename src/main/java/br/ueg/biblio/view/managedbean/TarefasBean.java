package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.files.DataBaseFileManager;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.tarefas.ManagerTask;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.omnifaces.util.Faces;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.CronScheduleBuilder.cronSchedule;

@Named(value = "tarefasBean")
@ApplicationScoped
public class TarefasBean implements Injetavel {

    private static final long serialVersionUID = 1L;

    private boolean ativado;

    public TarefasBean() {
        System.out.println("INICIANDO TAREFAS BEAM");
        //verificarEmprestimos();
    }

    public boolean isAtivado() {
        return ativado;
    }

    @PostConstruct
    public void executarTarefas() {
        iniciarQuartz();
    }

    private void iniciarQuartz() {
        System.out.println("br.ueg.biblio.view.managedbean.TarefasBean.iniciarQuartz()".toUpperCase());
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            Scheduler scheduler = sf.getScheduler();
            JobDetail job = JobBuilder.newJob(ManagerTask.class).withIdentity("tarefa1", "agendado").build();

            String ur = Faces.getRequestBaseURL();
            job.getJobDataMap().put("app", ur);

            job.getJobDataMap().put("res", Faces.getResponse());

            Date runTime = DateBuilder.evenMinuteDate(new Date());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "agendado")
                    .withSchedule(cronSchedule("0/20 * * * * ?")).build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (SchedulerException ex) {
            System.out.println("SE" + ex);
        }

    }

    public void verificarConexão() {
        DataBaseFileManager fileManager = new DataBaseFileManager();
        DataBaseModel base = fileManager.obterCfgConexao();
        if (fileManager.conexaoPronta(base)) {
            System.out.println("Conexao bem sucedida");
            // ManagedBeanUtil.addGrwolInfo("Conexao bem sucedida.");
        } else {
            System.out.println("Erro ao tentar conectar, mude as configurações.");
            try {
                // ManagedBeanUtil.addErrorMessage("Erro ao tentar conectar, mude as configurações.");
                // Faces.navigate("/view/exception/exception.xhtml");
                Faces.redirect(Faces.getRequestBaseURL() + "/view/exception/exception.xhtml?msg=O Sistema nao consegue se conectar com o banco. Configure agora mesmo.");
            } catch (IOException ex) {
                System.out.println("" + ex);
            }
        }
    }

}
