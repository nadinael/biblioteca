package br.ueg.biblio.util.exceptions;

import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.view.facelets.FaceletException;

public class jsfExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    private List<String> listaString;

    public jsfExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
        this.listaString = new ArrayList<>();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable exception = context.getException();
            boolean handle = false;
            SistemaException sistemaException = getSistemaException(exception);
            DataBaseException dbException = getDBException(exception);
            ConfiguracaoException cfgException = getCfgException(exception);
            FaceletException faces = getFException(exception);

            try {

                if (exception instanceof ViewExpiredException) {
                    handle = true;
                    redirect("/");
                } else if (sistemaException != null) {
                    handle = true;
                    ManagedBeanUtil.addErrorMessage(sistemaException.getMessage());
                } else if (dbException != null) {
                    handle = true;
                    ManagedBeanUtil.addErrorMessage(dbException.getMessage());
                } else if (cfgException != null) {
                    handle = true;
                    ManagedBeanUtil.addErrorMessage(cfgException.getMessage());
                } else if (faces != null) {
                    handle = true;
                    listaString.add(faces.getMessage());
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("lista", listaString);
                    redirect("/view/exception/erro.jsf");
                    //ManagedBeanUtil.addErrorMessage(faces.getMessage());
                } else if (faces != null) {
                    handle = true;
                    listaString.add(faces.getMessage());
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("lista", listaString);
                    redirect("/view/exception/erro.jsf");
                    //ManagedBeanUtil.addErrorMessage(faces.getMessage());
                } else {
                    handle = true;

                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put("lista", listaString);
                    redirect("/view/exception/erro.jsf");
                }
            } finally {
                if (handle) {
                    events.remove();
                }
            }
        }
        getWrapped().handle();
    }

    private void redirect(String page) {
        try {
            String contextPath = ManagedBeanUtil.getExternalContext().getRequestContextPath();
            ManagedBeanUtil.getExternalContext().redirect(contextPath + page);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            throw new FacesException(e);
        }
    }

    private SistemaException getSistemaException(Throwable exception) {
        if (exception instanceof SistemaException) {
            return (SistemaException) exception;
        } else if (exception.getCause() != null) {
            this.listaString.add(exception.getCause().getMessage());
            return getSistemaException(exception.getCause());
        }
        return null;
    }

    private ConfiguracaoException getCfgException(Throwable exception) {
        if (exception instanceof ConfiguracaoException) {
            return (ConfiguracaoException) exception;
        } else if (exception.getCause() != null) {
            this.listaString.add(exception.getCause().getMessage());
            return getCfgException(exception.getCause());
        }
        return null;
    }

    private DataBaseException getDBException(Throwable exception) {
        if (exception instanceof DataBaseException) {
            return (DataBaseException) exception;
        } else if (exception.getCause() != null) {
            this.listaString.add(exception.getCause().getMessage());
            return getDBException(exception.getCause());
        }
        return null;
    }

    private FaceletException getFException(Throwable exception) {
        if (exception instanceof FaceletException) {
            return (FaceletException) exception;
        } else if (exception.getCause() != null) {
            this.listaString.add(exception.getCause().getMessage());
            return getFException(exception.getCause());
        }
        return null;
    }

}
