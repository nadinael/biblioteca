package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.accreditation.SessionTools;
import br.ueg.biblio.annotations.Padrao;
import br.ueg.biblio.core.Sistema;
import br.ueg.biblio.files.DataBaseFileManager;
import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Faces;

/**
 *
 * @author nadinael
 */
@Named(value = "sistemaBean")
@SessionScoped
public class SistemaBean implements Injetavel {

    @Padrao
    private Sistema sistema;

    private Usuario usuario;

    private boolean loginStatus;

    private static final long serialVersionUID = 1343403461;

    /*METODOS DE CONFIGURAÇÕES*/
    public SistemaBean() {

    }

    @Inject
    public SistemaBean(Sistema sistema) {
        // System.out.println("br.ueg.biblio.view.managedbean.SistemaBean.<init>()");
        //System.out.println("injetando sistema)".toUpperCase());

        validarLogin();
        this.sistema = sistema;
        if (ManagedBeanUtil.isNotPostback()) {
            sistema.inicializarSistema();
        }

        if (!loginStatus && ManagedBeanUtil.isNotPostback()) {
            usuario = new Usuario();
        }

        //validarConexao();
    }

    public boolean validarConexao() {
        DataBaseFileManager fileManager = new DataBaseFileManager();
        DataBaseModel base = fileManager.obterCfgConexao();
        if (fileManager.conexaoPronta(base)) {
            System.out.println("Conexao bem sucedida");
            ManagedBeanUtil.addGrwolInfo("Conexao bem sucedida.");
            return true;
        } else {
            System.out.println("Erro ao tentar conectar, mude as configurações.");
            ManagedBeanUtil.addErrorMessage("Erro ao tentar conectar, mude as configurações.");
            return false;
        }
    }

    public void validarLogin() {
        this.loginStatus = SessionTools.isExisteUserLogado();
        if (!loginStatus) {
            this.usuario = new Usuario();
        }

        /*HttpSession sessao = ManagedBeanUtil.getSession();
        this.loginStatus = false;
        if ((sessao != null) && sessao.getAttribute("usuario_logado") != null) {
            this.loginStatus = true;
        } else {
            this.usuario = new Usuario();
        }*/
    }

    public boolean isPermitido() {

        return false;
    }

    /* METODOS DE ACAO */
    public void efetuaLogout() throws IOException {
        sistema.sair();
        this.usuario = new Usuario();
        validarLogin();
        Faces.redirect(Faces.getRequestBaseURL());

    }

    public void efetuarLogin() {
        this.usuario = sistema.efetuarLogin(usuario);
        if (usuario == null) {
            usuario = new Usuario();
            ManagedBeanUtil.addGrwolError("Nome de usuário ou senha incorretos.");
        } else if (usuario.getId() != null) {
            //this.loginStatus = true;
            ManagedBeanUtil.addGrwolInfo("Login efetuado com sucesso.");
        }
        validarLogin();
    }


    /* GETTERS E SETTERS */
    public Sistema getSistema() {
        return sistema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogado() {
        return loginStatus;
    }

}
/*METODOS DE CONFIGURAÇÕES*/
 /* METODOS DE ACAO */
 /* GETTERS E SETTERS */
