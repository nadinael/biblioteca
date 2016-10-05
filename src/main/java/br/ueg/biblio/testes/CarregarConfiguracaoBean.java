package br.ueg.biblio.testes;

import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "carregarConfiguracaoBean")
@ViewScoped
public class CarregarConfiguracaoBean implements Serializable {

    public static Properties getProp() throws IOException {

        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath >>" + context.getRealPath("/"));
        System.out.println("ResquestServletPath >>" + context.getRequestServletPath());
        System.out.println("ContextName >>" + context.getContextName());
        System.out.println("Context >>" + context.getContext());
        System.out.println("RequestContextPath >>" + context.getRequestContextPath());
        String caminho = context.getRealPath("/WEB-INF/settings/configuracao.properties");

        // File result = new File(caminho);
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(caminho);
        props.load(file);
        return props;
    }

    public void ler() throws IOException {
        String login; //Variavel que guardará o login do servidor.
        String host; //Variavel que guardará o host do servidor.
        String password; //Variável que guardará o password do usúario.
        System.out.println("************Teste de leitura do arquivo de propriedades************");

        Properties prop = getProp();

        login = prop.getProperty("prop.server.login");
        host = prop.getProperty("prop.server.host");
        password = prop.getProperty("prop.server.password");

        System.out.println("Login = " + login);
        System.out.println("Host = " + host);
        System.out.println("Password = " + password);
    }

}
