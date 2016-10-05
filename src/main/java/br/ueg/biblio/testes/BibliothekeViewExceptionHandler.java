package br.ueg.biblio.testes;

import br.ueg.biblio.util.exceptions.SistemaException;
import java.io.Serializable;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "biblioException")
@ViewScoped
public class BibliothekeViewExceptionHandler implements Serializable {

    public void throwNullPointerException() {
        throw new NullPointerException("A NullPointerException!");
    }

    public void throwWrappedIllegalStateException() {
        Throwable t = new IllegalStateException("A wrapped IllegalStateException!");
        throw new FacesException(t);
    }

    public void throwViewExpiredException() {
        throw new ViewExpiredException("A ViewExpiredException!",
                FacesContext.getCurrentInstance().getViewRoot().getViewId());
    }

    public void salvar() {
        throw new SistemaException("Teste de Exceção");
    }
}
