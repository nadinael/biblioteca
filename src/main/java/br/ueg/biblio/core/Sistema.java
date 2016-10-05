package br.ueg.biblio.core;

import br.ueg.biblio.annotations.Padrao;
import br.ueg.biblio.files.FileManager;
import br.ueg.biblio.cfg.SystemCfgModel;
import br.ueg.biblio.files.SystemFileManager;
import br.ueg.biblio.accreditation.ControlePagina;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

public class Sistema implements Injetavel {

    private SystemCfgModel sysModel;

    private SystemFileManager sysManager;

    @Inject
    private RepositoryCore repository;

    @Inject
    private AuthenticationCore aut;

    public Sistema() {
    }

    public void inicializarSistema() {
        System.out.println("br.ueg.biblio.core.Sistema.inicializarSistema()".toUpperCase());
        /*boolean validado = FileManager.validarProperties("system");
        if (validado) {
            this.sysManager = new SystemFileManager(validado);
            this.sysModel = this.sysManager.obterConfiguracoesModeladas();
            if (!sysModel.isDatabaseReady()) {
                
            } else {
                System.out.println("Banco de dados pronto;".toUpperCase());
            }
        } */
    }

    public void sair() {
        HttpSession sessao = ManagedBeanUtil.getSession();
        sessao.invalidate();
    }

    public Usuario efetuarLogin(@NotNull Usuario usuario) {
        System.out.println("sistema > efetuar login".toUpperCase());
        System.out.println("sistema > " + usuario.getUsername() + "".toUpperCase());
        if (aut.autenticar(usuario)) {
            Usuario user = repository.encontrarUserPorLogin(usuario.getUsername());
            List<String> permissoes = ControlePagina.carregarPermissoes(user);
            HttpSession sessao = ManagedBeanUtil.getSession();

            sessao.setAttribute("usuario_logado", user);
            sessao.setAttribute("paginas_permitidas", permissoes);

            sessao.setAttribute("permissoes", "permissoes");
            return user;
        }
        return null;
        /* if (usuario.getUsername().equals("admin") && usuario.getSenha().equals("admin")) {
            HttpSession sessao = ManagedBeanUtil.getSession();
            sessao.setAttribute("usuario", this.login);
            ManagedBeanUtil.addGrwolInfo("Login efetuado com sucesso!");
            System.out.println("ENTRANDO NO SISTEMA");
            this.login = null;
            this.senha = null;
            this.loginStatus = true;
        } else if (login == null || senha == null) {

            this.loginStatus = false;
            this.login = null;
            this.senha = null;
        } else {

            this.loginStatus = false;
            this.login = null;
            this.senha = null;
        } */

    }

    public SystemCfgModel getSysModel() {
        return sysModel;
    }

    public void setSysModel(SystemCfgModel sysModel) {
        this.sysModel = sysModel;
    }

    private SystemFileManager getSysManager() {
        return sysManager;
    }

    private void setSysManager(SystemFileManager sysManager) {
        this.sysManager = sysManager;
    }

}
