package br.ueg.biblio.core;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.persistence.SuperDAO;
import br.ueg.biblio.model.Autor;
import br.ueg.biblio.model.Cidade;
import br.ueg.biblio.model.Editora;
import br.ueg.biblio.model.Emprestimo;
import br.ueg.biblio.model.Estado;
import br.ueg.biblio.model.Idioma;
import br.ueg.biblio.model.Marca;
import br.ueg.biblio.model.Multa;
import br.ueg.biblio.model.Titulo;
import br.ueg.biblio.model.Usuario;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class RepositoryCore implements Injetavel {

    @Inject
    private SuperDAO dao;

    @Inject
    @Configurado
    private EntityManager manager;

    /*LISTAS*/
    public List<Cidade> cidadesPorEstado(Estado estado) {
        return manager.createQuery("select e from Cidade e where e.estado = :est", Cidade.class)
                .setParameter("est", estado).getResultList();
    }

    public Estado EstadoPorId(Long id) {
        //manager = EMUtil.getManager();
        System.out.println("CIDADE CONVERTIDA: " + manager.find(Estado.class, id).getNome());
        return manager.find(Estado.class, id);
    }

    public Cidade cidadePorId(Long id) {
        //manager = EMUtil.getManager();
        System.out.println("CIDADE CONVERTIDA: " + manager.find(Cidade.class, id).getNome());
        return manager.find(Cidade.class, id);
    }

    public boolean cpfDuplicado(String fis) {

        Usuario u;
        Query query = manager.createQuery("from Usuario e where e.cpf = :fis", Usuario.class);
        query.setParameter("fis", fis);

        try {
            u = (Usuario) query.getSingleResult();
            return u.getCpf().equals(fis);
        } catch (NoResultException ex) {
            System.out.println(">>>> NADA ENCONTRADO " + ex);
            return false;
        }

    }

    public boolean loginEstaCadastrado(String login) {
        Usuario u;
        Query query = manager.createQuery("from Usuario e where e.username = :login", Usuario.class);
        query.setParameter("login", login);

        try {
            u = (Usuario) query.getSingleResult();
            System.out.println("CADASTRADO");
            return u.getUsername().equals(login);
        } catch (NoResultException ex) {
            System.out.println(">>>> NADA ENCONTRADO " + ex);
            return false;
        }

    }

    public Usuario encontrarUserPorLogin(String login) {
        Usuario u;
        Query query = manager.createQuery("from Usuario e where e.username = :login", Usuario.class);
        query.setParameter("login", login);

        try {
            u = (Usuario) query.getSingleResult();
            System.out.println("Encontrado.");
            System.out.println("U: " + u.getNome());
            System.out.println("U: " + u.getUsername());
            System.out.println("U: " + u.getSenha());
            System.out.println("U: " + u.getSenha());
            System.out.println("U: " + u.getEndereco());
            return u;
        } catch (NoResultException ex) {
            System.out.println(">>>> NADA ENCONTRADO " + ex);
            return null;
        }

    }

    public List<Cidade> listaDeCidades() {
        return dao.listar(Cidade.class);
    }

    public List<Estado> listaDeEstados() {
        return dao.listar(Estado.class);
    }

    public List<Usuario> listaDeUsuarios() {
        return dao.listar(Usuario.class);
    }

    public List<Editora> listaDeEditoras() {
        return dao.listar(Editora.class);
    }

    public List<Idioma> listaDeIdiomas() {
        return dao.listar(Idioma.class);
    }

    /*
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	} */
    public List<Autor> listaDeAutores() {
        return dao.listar(Autor.class);
    }

    public List<Marca> listaDeMarcas() {
        return dao.listar(Marca.class);
    }

    public List<Titulo> listaDeTitulos() {
        return dao.listar(Titulo.class);
    }

    public List<Emprestimo> listaDeEmprestimos() {
        return dao.listar(Emprestimo.class);
    }

    public List<Multa> listaDeMultas() {
        return dao.listar(Multa.class);
    }

    public List<Emprestimo> listaDeEmprestimosPendentes() {
        //Deve ser implementado
        /*List<Emprestimo> u = new ArrayList<>();
        Query query = manager.createQuery("from Usuario e where e.username = :login", Usuario.class);
        query.setParameter("login", login);

        try {

            return u;
        } catch (NoResultException ex) {
            System.out.println(">>>> NADA ENCONTRADO " + ex);
            return null;
        } */
        return null;
    }


    /*
            public List<User> readAll() {
                //avoiding LazyInitializationException join fetch
                String hql = “select u from User u join fetch u.type”;
                Query query = getCurrentSession().createQuery(hql);
                return query.list();
        }
     */
}
