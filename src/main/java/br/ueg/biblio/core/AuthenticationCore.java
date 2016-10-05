package br.ueg.biblio.core;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.interfaces.Injetavel;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthenticationCore implements Injetavel {

    public AuthenticationCore() {
        System.out.println("Novo objeto para validacao");
    }

    @Inject
    private RepositoryCore repository;

    @Inject
    @Configurado
    private EntityManager manager;

    public void criptografar(@NotNull Usuario user) {
        user.setSenha(DigestUtils.sha256Hex(DigestUtils.sha256(user.getSenha())));
        System.out.println("criptografado com sucesso: > " + user.getSenha() + "".toUpperCase());
    }

    public boolean setLogin(String username, String senha) {

        boolean logar = false;
        /*

        Usuario user = new UsuarioDAO().getUsuarioPorLogin(username);

        if (user != null && user.getLogin() != null && user.getSenha() != null) {
            String cripto = Criptografia.criptografar(senha);
            if (cripto.equals(user.getSenha())) {
                logar = true;
            }
        } else {
            logar = false;
        }
         */
 /*String res = DigestUtils.sha256Hex(DigestUtils.sha256(senha));
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SENHA", res);
        context.addMessage("chaveLogin", message);*/
        return logar;
    }

    public boolean autenticar(@NotNull Usuario user) {

        if (repository.loginEstaCadastrado(user.getUsername())) {
            Usuario u = repository.encontrarUserPorLogin(user.getUsername());
            String cripto = DigestUtils.sha256Hex(DigestUtils.sha256(user.getSenha()));
            if (u.getSenha().equals(cripto)) {
                return true;
            }
        }

        return false;

    }

}
