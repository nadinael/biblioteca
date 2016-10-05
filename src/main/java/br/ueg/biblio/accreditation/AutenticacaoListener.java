package br.ueg.biblio.accreditation;

import br.ueg.biblio.model.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.omnifaces.util.Faces;

public class AutenticacaoListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        // System.out.println("DEPOIS DA FASE." + event.getPhaseId());
        String paginalAtuaString = Faces.getViewId();
        boolean paginaPublic = ControlePagina.isPublicPage(paginalAtuaString); //paginalAtuaString.contains("index.xhtml");
        Usuario usuarioLogado = (Usuario) Faces.getSessionAttribute("usuario_logado");

        if (!paginaPublic) {
            if (usuarioLogado == null) {
                // Faces.redirect(Faces.getRequestBaseURL() + "/view/exception/exception.xhtml?msg=O Sistema nao consegue se conectar com o banco. Configure agora mesmo.");

                try {
                    Faces.redirect(Faces.getRequestBaseURL() + "/view/exception/exception.xhtml?msg=Voce nao esta logado no sistema.");
                } catch (IOException ex) {
                    //      System.out.println("ERRO");
                    Faces.navigate("/view/exception/exception.xhtml?msg=?msg=Voce nao esta logado no sistema..");
                }

            } else if (usuarioLogado instanceof Usuario && ControlePagina.validarAcesso(usuarioLogado, paginalAtuaString)) {
                //    System.out.println("USUARIO LOGADO: " + usuarioLogado.getTipo().getDescricao());
            } else {
                try {
                    Faces.redirect(Faces.getRequestBaseURL() + "/view/exception/exception.xhtml?msg=Voce nao tem permissao.");
                } catch (IOException ex) {
                    //      System.out.println("ERRO");
                    Faces.navigate("/view/exception/exception.xhtml?msg=Voce nao tem permissao.");
                }
            }
        }

        //   System.out.println("PAGINA AGORA: " + paginalAtuaString);
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // System.out.println("ANTES DA FASE." + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
