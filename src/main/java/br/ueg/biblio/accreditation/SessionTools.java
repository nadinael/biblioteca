package br.ueg.biblio.accreditation;

import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Faces;

public class SessionTools implements Injetavel {

    public static Usuario getUsuario() {
        return (Usuario) Faces.getSessionAttribute("usuario_logado");
    }

    public static boolean isExisteUserLogado() {
        return (Usuario) Faces.getSessionAttribute("usuario_logado") != null;
    }

}
