package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.cfg.BiblioCfg;
import br.ueg.biblio.files.DataBaseFileManager;
import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.files.BiblioCfgFileManager;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.hibernate.engine.spi.Managed;

@Named(value = "configuracoesBean")
@ViewScoped
public class ConfiguracoesBean implements Injetavel {

    private BiblioCfg bicfg;
    //-------
    private String teste;

    private String password;

    private boolean aprovado;
    private boolean editando;

    private DataBaseModel dtBase;
    private DataBaseModel padrao;

    private DataBaseFileManager fileManager;

    /*METODOS DE CONFIGURACAO*/
    public ConfiguracoesBean() {
        System.out.println("br.ueg.biblio.view.managedbean.ConfiguracoesBean.<init>()".toUpperCase());
    }

    public void configurarFormDB() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.editando = false;
            this.aprovado = false;
            System.out.println("br.ueg.biblio.view.managedbean.ConfiguracoesBean.configurarFormDB()");
            this.fileManager = new DataBaseFileManager();
            this.dtBase = fileManager.obterCfgConexao();

            this.padrao = fileManager.obterConexaoPadrao();
            if (this.dtBase == null) {
                ManagedBeanUtil.addErrorMessage("Exceção ao tentar recuperar configurações de conexão.");
            }

            if ((this.password != null) && (this.password.equals("lagrima"))) {
                this.aprovado = true;
                ManagedBeanUtil.addGrwolInfo("palava chave correta".toUpperCase());
            }
        }
    }

    /*METODOS DE ACAO*/
    public void verificarConexao() {
        System.out.println("br.ueg.biblio.view.managedbean.ConfiguracoesBean.verificarConexao()");
        if (fileManager.conexaoPronta(this.dtBase) == true) {
            ManagedBeanUtil.addGrwolInfo("Conexao bem sucedida.");
        } else {
            ManagedBeanUtil.addErrorMessage("Erro ao tentar conectar, mude as configurações.");
        }
    }

    public void updateCfgDB() {
        if (this.editando) {
            fileManager.atualizarCfgDB(dtBase);
            this.editando = false;
            return;
        }

        if (!this.editando) {
            this.editando = true;
            return;
        }

    }

    /*METODOS GETTERS SETTERS*/
    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public DataBaseModel getDtBase() {
        return dtBase;
    }

    public void setDtBase(DataBaseModel dtBase) {
        this.dtBase = dtBase;
    }

    public DataBaseModel getPadrao() {
        return padrao;
    }

    public void setPadrao(DataBaseModel padrao) {
        this.padrao = padrao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public boolean isEditando() {
        return editando;
    }

    public BiblioCfg getBicfg() {
        return bicfg;
    }

    public void setBicfg(BiblioCfg bicfg) {
        this.bicfg = bicfg;
    }

}
