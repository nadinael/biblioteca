package br.ueg.biblio.tarefas;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import static br.ueg.biblio.util.ManagedBeanUtil.getRequest;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.omnifaces.util.Faces;

@WebServlet(urlPatterns = {"/task"})
public class ExecutorTarefas extends HttpServlet implements Injetavel {

    private static final long serialVersionUID = 1L;

    @Configurado
    private ManagerTask tarefa;

    public ExecutorTarefas() {
    }

    @Inject
    public ExecutorTarefas(ManagerTask tar) {
        System.out.println("INICIANDO AGENDADOR DE TAREFAS.");
        this.tarefa = tar;
    }

}
