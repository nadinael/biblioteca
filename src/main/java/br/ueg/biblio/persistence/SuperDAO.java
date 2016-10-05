package br.ueg.biblio.persistence;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.model.Simples;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.List;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import org.hibernate.HibernateException;

/**
 *
 * SuperDAO é uma classe responsavel pela manipulação e transações simples
 * (CRUD) de entidades no banco de dados, sendo que a unica responsabilidade
 * dessas entidades é a heraça da classe EntidadeSimples abstrata
 *
 * @author nadinael
 *
 */
public class SuperDAO implements Injetavel {

    @Inject
    @Configurado
    private EntityManager em;
//EntityManager em = EMUtil.getManager();

    public boolean salvar(Simples entidade) {
        try {
            getEm().getTransaction().begin();
            getEm().persist(entidade);
            getEm().getTransaction().commit();
            return true;

        } catch (HibernateException ex) {
            System.out.println("FALHA NA TENTATIVA DE SALVAR ENTIDADE " + ex);
            return false;
        }

    }

    public boolean atualizar(Simples entidade) {
        try {
            getEm().getTransaction().begin();
            getEm().merge(entidade);
            getEm().getTransaction().commit();
            return true;

        } catch (HibernateException ex) {
            System.out.println("FALHA NA TENTATIVA DE SALVAR ENTIDADE " + ex);
            return false;
        }
    }

    public void excluir(Simples entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <T extends Simples> List<T> listar(Class<T> entidade) {
        if (getEm() != null) {
            System.out.println("Iniciando query".toUpperCase());
            return getEm().createQuery(montarQuery(entidade)).getResultList();
        }
        return null;
    }

    private String montarQuery(Class<? extends Simples> entidade) {
        StringBuilder sb = new StringBuilder();
        sb.append("select e from ");
        sb.append(entidade.getSimpleName());
        sb.append(" as e ");
        return sb.toString();
    }

    public EntityManager getEm() {
        return em;
    }

}
