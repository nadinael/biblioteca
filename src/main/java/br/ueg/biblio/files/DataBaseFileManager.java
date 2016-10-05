package br.ueg.biblio.files;

import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.model.enumerated.TipoDB;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import javax.faces.context.ExternalContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import org.primefaces.model.UploadedFile;

public class DataBaseFileManager implements Injetavel {

    private final String pastaSettings = "/WEB-INF/settings";

    private static int BUFFER_SIZE = 6124;
    private UploadedFile file;

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

    /*livro*/
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

    public void CriarPersistence() throws IOException {

        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath >>" + context.getRealPath("/"));
        System.out.println("ResquestServletPath >>" + context.getRequestServletPath());
        System.out.println("ContextName >>" + context.getContextName());
        System.out.println("Context >>" + context.getContext());
        System.out.println("RequestContextPath >>" + context.getRequestContextPath());

        String caminho = context.getRealPath("/WEB-INF/arquivos") + "//" + "novo.txt";

        File result = new File(caminho);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            fileOutputStream.flush();
            fileOutputStream.close();
            ManagedBeanUtil.addInfoMessage(" enviado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            ManagedBeanUtil.addErrorMessage("Arquivos nao enviados");
        }

        /*

        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath >>" + context.getRealPath("/"));

        String caminho = context.getRealPath("/WEB-INF/settings/database.txt");
        File f = new File("database.txt");
        f.createNewFile();
        
        
        //FileWriter arq = new FileWriter(new File("/WEB-INF/settings/database.txt") );

        /*l
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(caminho);
        props.load(file);

       ogin = propriedades.getProperty("propriedades.server.login");
        host = propriedades.getProperty("propriedades.server.host");
        password = propriedades.getProperty("propriedades.server.password"); ]
                 String Dialect;
    String url;
    String jdbcDriver;
    String password;
    String username;

        
        props.setProperty("dialect", ""); */
    }

    public void gerarProperties() throws IOException {

        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath || " + context.getRealPath("/"));

        String caminho = context.getRealPath("/WEB-INF/settings") + "//" + "confiig-default";

        Properties props = new Properties();
        try {
            FileInputStream arquivo = new FileInputStream(caminho);
            props.load(arquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado");
        }

        //File result = new File(caminho);
        // FileWriter writer = new FileWriter(result);
        System.out.println("R: " + props);

        /*
        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath >>" + context.getRealPath("/"));

        String caminho = context.getRealPath("/WEB-INF/arquivos") + "//" + "config.properties";

        File result = new File(caminho);
        

        try {
            FileOutputStream outputStream = new FileOutputStream(result);
            outputStream.flush();
            outputStream.close();
            ManagedBeanUtil.addInfoMessage(" enviado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            ManagedBeanUtil.addErrorMessage("Arquivos nao enviados");
        }

        

        ExternalContext context = ManagedBeanUtil.getExternalContext();
        System.out.println("RealPath >>" + context.getRealPath("/"));

        String caminho = context.getRealPath("/WEB-INF/settings/database.txt");
        File f = new File("database.txt");
        f.createNewFile();
        
        
        //FileWriter arq = new FileWriter(new File("/WEB-INF/settings/database.txt") );

        /*l
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(caminho);
        props.load(file);

       ogin = propriedades.getProperty("propriedades.server.login");
        host = propriedades.getProperty("propriedades.server.host");
        password = propriedades.getProperty("propriedades.server.password"); ]
                 String Dialect;
    String url;
    String jdbcDriver;
    String password;
    String username;

        
        props.setProperty("dialect", "");
        
        public void obterArquivo() {
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
String arquivo = context.getRealPath("/WEB-INF/sua-pasta") + "//" +
"seu-arquivo.properties";

try {
Properties propriedades = new Properties();
FileInputStream inputStream = new FileInputStream(arquivo);
propriedades.load(inputStream);

System.out.println(propriedades.getProperty("jdbc.user"));
System.out.println(propriedades.getProperty("jdbc.password"));
System.out.println(propriedades.getProperty("jdbc.url"));
System.out.println(propriedades.getProperty("jdbc.drivert"));

inputStream.close();
    } catch (FileNotFoundException ex) {
           //sua msg
        } catch (IOException ex) {
                           //sua msg
        }
    }public void obterArquivo() {
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
String arquivo = context.getRealPath("/WEB-INF/sua-pasta") + "//" +
"seu-arquivo.properties";

try {
Properties propriedades = new Properties();
FileInputStream inputStream = new FileInputStream(arquivo);
propriedades.load(inputStream);

System.out.println(propriedades.getProperty("jdbc.user"));
System.out.println(propriedades.getProperty("jdbc.password"));
System.out.println(propriedades.getProperty("jdbc.url"));
System.out.println(propriedades.getProperty("jdbc.drivert"));

inputStream.close();
    } catch (FileNotFoundException ex) {
           //sua msg
        } catch (IOException ex) {
                           //sua msg
        }
    }
         */
    }

    public boolean validarProperties(String arquivo) {
        if (arquivo != null) {
            arquivo = montartLocalAbosoluto(arquivo);

            System.out.println(arquivo);

            try {
                FileInputStream fileLoaded = new FileInputStream(arquivo);

                System.out.println("Arquivo encontrado <> metodo verificar retorna true");
                System.out.println("fim do metodo, arquivo encontrado.");
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo nao encontrado " + e);
                return false;
            }
        }
        return false;
    }

    public void gerarPropriedadesPadrao() {
        if (!validarProperties("config-default")) {

            System.out.println("gerando propriedades".toUpperCase());
            String arquivo = montartLocalAbosoluto("config-default");

            System.out.println(arquivo);

            File conf = new File(arquivo);

            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(conf);

                outputStream.flush();

                Properties prop = new Properties();
                FileInputStream inputStream = new FileInputStream(arquivo);
                prop.load(inputStream);

                DataBaseModel db = new DataBaseModel();
                db.ajustarCfgPadrao();

                prop.setProperty("db.att.dialect", db.getDialect());
                prop.setProperty("db.att.jdbc-driver", db.getJdbcDriver());
                prop.setProperty("db.att.username", db.getUsername());
                prop.setProperty("db.att.password", db.getPassword());

                prop.setProperty("db.att.database", db.getNomeBanco());
                prop.setProperty("db.att.ip", db.getIp());
                prop.setProperty("db.att.port", db.getPorta());

                prop.setProperty("db.att.url", db.getUrl());

                prop.store(outputStream, null);
                outputStream.flush();
                outputStream.close();
                ManagedBeanUtil.addInfoMessage("Arquivo de propriedades padrão criado com sucesso.");
            } catch (FileNotFoundException ex) {
                ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não criado. \nDetalhes: " + ex);
            } catch (IOException ex) {
                ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não criado. \nDetalhes: " + ex);
            }

        }

    }

    public DataBaseModel obterConexaoPadrao() {

        System.out.println("Obtendo conexao padrao");
        if (validarProperties("config-default")) {

            String arquivo = montartLocalAbosoluto("config-default");

            try {

                Properties prop = new Properties();
                FileInputStream inputStream = new FileInputStream(arquivo);
                prop.load(inputStream);

                DataBaseModel db = new DataBaseModel();

                db.setUsername(prop.getProperty("db.att.username"));

                db.setPassword(prop.getProperty("db.att.password"));

                db.setNomeBanco(prop.getProperty("db.att.database"));

                db.setIp(prop.getProperty("db.att.ip"));

                db.setPorta(prop.getProperty("db.att.port"));

                db.setUrlPersonalizada(prop.getProperty("db.att.url"));

                inputStream.close();
                System.out.println("Arquivo de propriedades padrão recuperado com sucesso.");
                return db;
            } catch (FileNotFoundException ex) {
                ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
                return null;
            } catch (IOException ex) {
                ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
                return null;
            }

        }
        return null;

    }

    public DataBaseModel obterCfgConexao() {
        System.out.println("br.ueg.biblio.files.DataBaseFileManager.obterCfgConexao()".toUpperCase());
        if (validarProperties("configuracao")) {

            String arquivo = montartLocalAbosoluto("configuracao");

            try {

                Properties propriedades = new Properties();
                FileInputStream inputStream = new FileInputStream(arquivo);
                propriedades.load(inputStream);
                DataBaseModel banco = new DataBaseModel();
                Enumeration<?> e;

                switch (propriedades.getProperty("key.chave")) {
                    case "P":
                        banco.setTipoDoBanco(TipoDB.P);
                        System.out.println("postgre está ajustado como padrao");
                        e = propriedades.propertyNames();
                        //      System.out.println("Você ADMIN".toUpperCase());
                        while (e.hasMoreElements()) {
                            String key = (String) e.nextElement();
                            //         System.out.println("  --  " + key + "  --  ");
                            if (key.startsWith("postgre")) {
                                //String uri = propriedades.getProperty(key);
                                //          System.out.println("adicionando a permissao: " + uri);

                                banco.setUsername(propriedades.getProperty("postgre.login"));

                                banco.setPassword(propriedades.getProperty("postgre.password"));

                                banco.setNomeBanco(propriedades.getProperty("postgre.database"));

                                banco.setIp(propriedades.getProperty("postgre.ip"));

                                banco.setPorta(propriedades.getProperty("postgre.porta"));

                                banco.setUrlPersonalizada(propriedades.getProperty("postgre.url"));

                                banco.setJdbcDriver(propriedades.getProperty("postgre.driver"));

                                banco.setDialect(propriedades.getProperty("postgre.dialect"));

                            }
                        }

                        break;
                    case "M":
                        banco.setTipoDoBanco(TipoDB.M);
                        System.out.println("Mysql está ajustado como padrao");
                        e = propriedades.propertyNames();
                        //      System.out.println("Você ADMIN".toUpperCase());
                        while (e.hasMoreElements()) {
                            String key = (String) e.nextElement();
                            //         System.out.println("  --  " + key + "  --  ");
                            if (key.startsWith("mysql")) {
                                //String uri = propriedades.getProperty(key);
                                //          System.out.println("adicionando a permissao: " + uri);

                                banco.setUsername(propriedades.getProperty("mysql.login"));

                                banco.setPassword(propriedades.getProperty("mysql.password"));

                                banco.setNomeBanco(propriedades.getProperty("mysql.database"));

                                banco.setIp(propriedades.getProperty("mysql.ip"));

                                banco.setPorta(propriedades.getProperty("mysql.porta"));

                                banco.setUrlPersonalizada(propriedades.getProperty("mysql.url"));

                                banco.setJdbcDriver(propriedades.getProperty("mysql.driver"));

                                banco.setDialect(propriedades.getProperty("mysql.dialect"));

                            }
                        }

                        break;
                    default:
                        banco.setTipoDoBanco(TipoDB.M);
                        System.out.println("Mysql está ajustado como padrao");
                        e = propriedades.propertyNames();
                        //      System.out.println("Você ADMIN".toUpperCase());
                        while (e.hasMoreElements()) {
                            String key = (String) e.nextElement();
                            //         System.out.println("  --  " + key + "  --  ");
                            if (key.startsWith("mysql")) {
                                //  String uri = propriedades.getProperty(key);
                                //          System.out.println("adicionando a permissao: " + uri);

                                banco.setUsername(propriedades.getProperty("mysql.login"));

                                banco.setPassword(propriedades.getProperty("mysql.password"));

                                banco.setNomeBanco(propriedades.getProperty("mysql.database"));

                                banco.setIp(propriedades.getProperty("mysql.ip"));

                                banco.setPorta(propriedades.getProperty("mysql.porta"));

                                banco.setUrlPersonalizada(propriedades.getProperty("mysql.url"));

                                banco.setJdbcDriver(propriedades.getProperty("mysql.driver"));

                                banco.setDialect(propriedades.getProperty("mysql.dialect"));

                            }
                        }
                        break;
                }

                inputStream.close();
                System.out.println("Arquivo de propriedades padrão recuperado com sucesso.");
                return banco;
            } catch (FileNotFoundException ex) {
                ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
                return null;
            } catch (IOException ex) {
                ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
                return null;
            }

        }
        return null;

    }

    public boolean atualizarCfgDB(DataBaseModel banco) {

        String arquivo = montartLocalAbosoluto("configuracao");

        Properties propriedades = new Properties();

        try {
            //carregado arquivo
            File file = new File(arquivo);
            FileInputStream inputStream = new FileInputStream(file);
            propriedades.load(inputStream);
            //arquivo carregado

            switch (banco.getTipoDoBanco()) {
                case P:
                    propriedades.setProperty("postgre.password", banco.getPassword());

                    propriedades.setProperty("postgre.login", banco.getUsername());

                    propriedades.setProperty("postgre.database", banco.getNomeBanco());

                    propriedades.setProperty("postgre.ip", banco.getIp());

                    propriedades.setProperty("postgre.porta", banco.getPorta());

                    propriedades.setProperty("postgre.url", banco.getUrl());

                    propriedades.setProperty("postgre.driver", banco.getJdbcDriver());

                    propriedades.setProperty("postgre.dialect", banco.getDialect());

                    break;

                case M:
                    propriedades.setProperty("mysql.password", banco.getPassword());

                    propriedades.setProperty("mysql.login", banco.getUsername());

                    propriedades.setProperty("mysql.database", banco.getNomeBanco());

                    propriedades.setProperty("mysql.ip", banco.getIp());

                    propriedades.setProperty("mysql.porta", banco.getPorta());

                    propriedades.setProperty("mysql.url", banco.getUrl());

                    propriedades.setProperty("mysql.driver", banco.getJdbcDriver());

                    propriedades.setProperty("mysql.dialect", banco.getDialect());
                    break;
                default:
                    propriedades.setProperty("mysql.password", banco.getPassword());

                    propriedades.setProperty("mysql.login", banco.getUsername());

                    propriedades.setProperty("mysql.database", banco.getNomeBanco());

                    propriedades.setProperty("mysql.ip", banco.getIp());

                    propriedades.setProperty("mysql.porta", banco.getPorta());

                    propriedades.setProperty("postgre.url", banco.getUrl());

                    propriedades.setProperty("mysql.driver", banco.getJdbcDriver());

                    propriedades.setProperty("mysql.dialect", banco.getDialect());
                    break;
            }
            //salvando
            FileOutputStream outputStream = new FileOutputStream(file);
            propriedades.store(outputStream, null);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            System.out.println("CONFIGURACOES DO BANCO FORAM SALVAS");
            return true;

        } catch (FileNotFoundException ex) {
            ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
            return false;
        } catch (IOException ex) {
            ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
            return false;
        }

    }

    public boolean conexaoPronta(DataBaseModel model) {
        System.out.println("br.ueg.biblio.files.DataBaseFileManager.conexaoPronta()".toUpperCase());
        EntityManagerFactory emf;
        final Map mapa = model.getConfig();
        try {
            System.out.println("br.ueg.biblio.files.DataBaseFileManager.conexaoPronta()PRECREATE");
            emf = Persistence.createEntityManagerFactory("persistenceUnit", mapa);
            System.out.println("br.ueg.biblio.files.DataBaseFileManager.conexaoPronta()INSTANT");
            emf.createEntityManager();
            emf.close();
            System.out.println("br.ueg.biblio.files.DataBaseFileManager.conexaoPronta()CREATE");
            return true;
        } catch (PersistenceException pe) {
            System.out.println("EXECPTION: " + pe);
            return false;
        }

    }

    private String montartLocalAbosoluto(String document) {
        if (document != null) {
            ExternalContext context = ManagedBeanUtil.getExternalContext();
            String caminho = context.getRealPath(pastaSettings);

            StringBuilder diretorio = new StringBuilder();
            diretorio.append(caminho).append("/");
            diretorio.append(document);
            diretorio.append(".properties");
            document = diretorio.toString();
            return document;
        }
        return null;
    }

    public DataBaseModel obterCfgConexaoServ(String arquivo) {
        System.out.println("br.ueg.biblio.files.DataBaseFileManager.obterCfgConexaoServ()".toUpperCase());
        System.out.println("-- 0" + arquivo);

        try {

            Properties propriedades = new Properties();
            FileInputStream inputStream = new FileInputStream(arquivo);
            propriedades.load(inputStream);
            DataBaseModel banco = new DataBaseModel();
            Enumeration<?> e;

            switch (propriedades.getProperty("key.chave")) {
                case "P":
                    banco.setTipoDoBanco(TipoDB.P);
                    System.out.println("postgre está ajustado como padrao");
                    e = propriedades.propertyNames();
                    //      System.out.println("Você ADMIN".toUpperCase());
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        //         System.out.println("  --  " + key + "  --  ");
                        if (key.startsWith("postgre")) {
                            //String uri = propriedades.getProperty(key);
                            //          System.out.println("adicionando a permissao: " + uri);

                            banco.setUsername(propriedades.getProperty("postgre.login"));

                            banco.setPassword(propriedades.getProperty("postgre.password"));

                            banco.setNomeBanco(propriedades.getProperty("postgre.database"));

                            banco.setIp(propriedades.getProperty("postgre.ip"));

                            banco.setPorta(propriedades.getProperty("postgre.porta"));

                            banco.setUrlPersonalizada(propriedades.getProperty("postgre.url"));

                            banco.setJdbcDriver(propriedades.getProperty("postgre.driver"));

                            banco.setDialect(propriedades.getProperty("postgre.dialect"));

                        }
                    }

                    break;
                case "M":
                    banco.setTipoDoBanco(TipoDB.M);
                    System.out.println("Mysql está ajustado como padrao");
                    e = propriedades.propertyNames();
                    //      System.out.println("Você ADMIN".toUpperCase());
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        //         System.out.println("  --  " + key + "  --  ");
                        if (key.startsWith("mysql")) {
                            //String uri = propriedades.getProperty(key);
                            //          System.out.println("adicionando a permissao: " + uri);

                            banco.setUsername(propriedades.getProperty("mysql.login"));

                            banco.setPassword(propriedades.getProperty("mysql.password"));

                            banco.setNomeBanco(propriedades.getProperty("mysql.database"));

                            banco.setIp(propriedades.getProperty("mysql.ip"));

                            banco.setPorta(propriedades.getProperty("mysql.porta"));

                            banco.setUrlPersonalizada(propriedades.getProperty("mysql.url"));

                            banco.setJdbcDriver(propriedades.getProperty("mysql.driver"));

                            banco.setDialect(propriedades.getProperty("mysql.dialect"));

                        }
                    }

                    break;
                default:
                    banco.setTipoDoBanco(TipoDB.M);
                    System.out.println("Mysql está ajustado como padrao");
                    e = propriedades.propertyNames();
                    //      System.out.println("Você ADMIN".toUpperCase());
                    while (e.hasMoreElements()) {
                        String key = (String) e.nextElement();
                        //         System.out.println("  --  " + key + "  --  ");
                        if (key.startsWith("mysql")) {
                            //  String uri = propriedades.getProperty(key);
                            //          System.out.println("adicionando a permissao: " + uri);

                            banco.setUsername(propriedades.getProperty("mysql.login"));

                            banco.setPassword(propriedades.getProperty("mysql.password"));

                            banco.setNomeBanco(propriedades.getProperty("mysql.database"));

                            banco.setIp(propriedades.getProperty("mysql.ip"));

                            banco.setPorta(propriedades.getProperty("mysql.porta"));

                            banco.setUrlPersonalizada(propriedades.getProperty("mysql.url"));

                            banco.setJdbcDriver(propriedades.getProperty("mysql.driver"));

                            banco.setDialect(propriedades.getProperty("mysql.dialect"));

                        }
                    }
                    break;
            }

            inputStream.close();
            System.out.println("Arquivo de propriedades padrão recuperado com sucesso.");
            return banco;
        } catch (FileNotFoundException ex) {
            ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
            return null;
        } catch (IOException ex) {
            ManagedBeanUtil.addErrorMessage("Arquivo de propriedade não encontrado. \nDetalhes: " + ex);
            return null;
        }

    }

    private String montartLocalAbosoluto2(String document) {
        if (document != null) {
            ExternalContext context = ManagedBeanUtil.getExternalContext();
            String caminho = context.getRealPath(pastaSettings);

            StringBuilder diretorio = new StringBuilder();
            diretorio.append(caminho).append("/");
            diretorio.append(document);
            diretorio.append(".properties");
            document = diretorio.toString();
            return document;
        }
        return null;
    }

}
