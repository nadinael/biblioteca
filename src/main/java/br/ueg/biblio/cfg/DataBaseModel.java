package br.ueg.biblio.cfg;

import br.ueg.biblio.files.DataBaseFileManager;
import br.ueg.biblio.model.enumerated.TipoDB;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.HashMap;
import java.util.Map;

public class DataBaseModel implements Injetavel {

    private TipoDB tipoDoBanco;

    private String dialect;
    private String jdbcDriver;

    private String password;
    private String username;

    private String nomeBanco;
    private String porta;
    private String ip;
    private String url;

    private boolean padraoExiste;

    public DataBaseModel() {

    }

    public void ajustarCfgPadrao() {
        this.dialect = "org.hibernate.dialect.MySQL5InnoDBDialect";
        this.jdbcDriver = "com.mysql.jdbc.Driver";
        this.setPassword("");
        this.setUsername("root");
        this.setNomeBanco("biblio");
        this.setPorta("3306");
        this.setIp("127.0.0.1");

        System.out.println("URL GERADA: " + this.getUrl());
    }

    public void setUrlPersonalizada(String url) {
        this.url = url;
    }

    public String getUrlPersonalizada() {
        return this.url;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {

        return "jdbc:mysql://" + this.ip + ":" + this.porta + "/" + this.nomeBanco;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPadraoExiste() {
        return new DataBaseFileManager().validarProperties("config-default");
    }

    public Map getMap() {
        Map propriedades = new HashMap();
        propriedades.put("javax.persistence.jdbc.url", this.getUrl());
        propriedades.put("javax.persistence.jdbc.user", this.username);
        propriedades.put("javax.persistence.jdbc.password", this.password);
        //propriedades.put("hibernate.hbm2ddl.auto", "create-drop");

        return propriedades;
    }

    public TipoDB getTipoDoBanco() {
        return tipoDoBanco;
    }

    public void setTipoDoBanco(TipoDB tipoDoBanco) {
        this.tipoDoBanco = tipoDoBanco;
    }

    public Map getConfig() {

        Map propriedades = new HashMap();
        propriedades.put("javax.persistence.jdbc.url", this.getUrl());
        propriedades.put("javax.persistence.jdbc.user", this.getUsername());
        propriedades.put("javax.persistence.jdbc.password", this.getPassword());
        propriedades.put("hibernate.dialect", this.getDialect());
        propriedades.put("hibernate.connection.driver_class", this.getJdbcDriver());
        //propriedades.put("hibernate.hbm2ddl.auto", "create");

        /*HIB*/
        propriedades.put("connection.autoReconnect", "true");
        propriedades.put("connection.autoReconnectForPools", "true");

        return propriedades;
    }

}
