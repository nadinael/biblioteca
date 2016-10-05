package br.ueg.biblio.testes;

import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named(value = "uploadBean")
@ViewScoped
public class UploadBean implements Serializable {

    private static int BUFFER_SIZE = 6124;
    private UploadedFile file;

    public void handleFileUpload() {

        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath >>" + context.getRealPath("/"));
        System.out.println("ResquestServletPath >>" + context.getRequestServletPath());
        System.out.println("ContextName >>" + context.getContextName());
        System.out.println("Context >>" + context.getContext());
        System.out.println("RequestContextPath >>" + context.getRequestContextPath());

        String caminho = context.getRealPath("/WEB-INF/arquivos") + "//" + file.getFileName();

        File result = new File(caminho);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = file.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            ManagedBeanUtil.addInfoMessage(file.getFileName() + " foi enviado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            ManagedBeanUtil.addErrorMessage("Arquivos nao enviados");
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
