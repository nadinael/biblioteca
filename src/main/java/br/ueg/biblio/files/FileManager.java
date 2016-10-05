package br.ueg.biblio.files;

import br.ueg.biblio.model.interfaces.Injetavel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Faces;

public class FileManager implements Injetavel {

    private static final String PASTA = "/WEB-INF/settings";

    public static String montartLocalAbosoluto(String document) {
        if (document != null) {
            System.out.println("br.ueg.biblio.files.FileManager.montartLocalAbosoluto()");

            /*System.out.println(": " + Faces.getForwardRequestURI());
            System.out.println(": " + Faces.getImplInfo());
            System.out.println(": " + Faces.getMapping());
            System.out.println(": " + Faces.getRealPath(document));
            System.out.println(": " + Faces.getRemoteAddr());
            System.out.println(": " + Faces.getRequestBaseURL());
            System.out.println(": " + Faces.getRequestContextPath());
            System.out.println(": " + Faces.getRequestDomainURL());
            System.out.println(": " + Faces.getRequestHostname());
            System.out.println(": " + Faces.getRequestPathInfo());
            System.out.println(": " + Faces.getRequestServletPath());
            System.out.println(": " + Faces.getRequestURI());
            System.out.println(": " + Faces.getServerInfo());
            System.out.println(": " + Faces.getContext().getCurrentPhaseId());

            System.out.println(": " + Faces.getELContext().toString());
            System.out.println(": " + Faces.getExternalContext().getRequestServletPath());
            System.out.println(": " + Faces.getExternalContext().getRealPath(PASTA));
            System.out.println(": " + Faces.getExternalContext().getRequestContextPath());

            System.out.println(": " + Faces.getFaceletContext().getFacesContext().getExternalContext().getApplicationContextPath());
            System.out.println(": " + Faces.getFaceletContext().getFacesContext().getExternalContext().getRealPath(PASTA));

            System.out.println(": " + Faces.getResponse());
            System.out.println(": " + Faces.getRequest());

            System.out.println(": " + Faces.getForwardRequestQueryString());*/
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath(PASTA);
            StringBuilder diretorio = new StringBuilder();
            diretorio.append(caminho).append("/");
            diretorio.append(document);
            diretorio.append(".properties");
            document = diretorio.toString();
            return document;
        }
        return null;
    }

    public static Properties getProperties(String arquivo) {
        arquivo = montartLocalAbosoluto(arquivo);
        FileInputStream inputStream;
        Properties propriedades = new Properties();

        try {
            inputStream = new FileInputStream(arquivo);
            propriedades.load(inputStream);
            inputStream.close();
            return propriedades;
        } catch (IOException ex) {
            return null;
        }

    }

    public static boolean validarProperties(String arquivo) {
        if (arquivo != null) {
            arquivo = montartLocalAbosoluto(arquivo);
            System.out.println("br.ueg.biblio.files.FileManager.validarProperties()");
            System.out.println(arquivo);

            try {
                FileInputStream fileLoaded = new FileInputStream(arquivo);
                System.out.println("Arquivo encontrado <> metodo verificar retorna true");
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo nao encontrado " + e);
                return false;

            }
        }
        return false;
    }

}
