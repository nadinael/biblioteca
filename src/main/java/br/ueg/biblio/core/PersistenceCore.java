package br.ueg.biblio.core;

import br.ueg.biblio.model.Autor;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.persistence.SuperDAO;
import br.ueg.biblio.model.Simples;
import br.ueg.biblio.model.Titulo;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.enumerated.DisponibilidadeStatus;
import br.ueg.biblio.model.enumerated.StatusCadastro;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;

public class PersistenceCore implements Injetavel {

    @Inject
    private SuperDAO dao;

    @Inject
    private AuthenticationCore aut;

    public void adicionarUsuario(Usuario usuario) {
        usuario.setStatusCadastro(StatusCadastro.ATIVO);
        usuario.setDataCadastro(Calendar.getInstance());
        aut.criptografar(usuario);
        dao.salvar(usuario);
    }

    public boolean persistir(Simples entidade) {
        return dao.salvar(entidade);
    }

    public boolean adicionarEmprestimo(Emprestimo emprestimo) {
        return (dao.atualizar(emprestimo.getCopia())) && (dao.salvar(emprestimo));
    }

    public boolean efetuarDevolucao(Emprestimo emprestimo) {
        return (dao.atualizar(emprestimo.getCopia())) && (dao.atualizar(emprestimo));
    }

    public boolean atualizarEmprestimo(Emprestimo entidade) {
        return dao.atualizar(entidade);
    }

    public boolean adicionarTitulo(Titulo titulo) {
        System.out.println("br.ueg.biblio.core.PersistenceCore.adicionarTitulo()".toUpperCase());
        List<Autor> atts = new ArrayList<>();
        try {
            dao.getEm().getTransaction().begin();

            for (Autor au : titulo.getAutores()) {
                atts.add((Autor) dao.getEm().merge(au));
                //atts.add(dao.getEm().getReference(Autor.class, au.getId()));
            }

            titulo.setAutores(atts);
            dao.getEm().persist(titulo);
            dao.getEm().flush();
            dao.getEm().getTransaction().commit();
            return true;

        } catch (HibernateException ex) {
            System.out.println("FALHA NA TENTATIVA DE SALVAR TITULO " + ex);
            return false;
        }
    }

}
