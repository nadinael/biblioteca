package br.ueg.biblio.persistence;

import br.ueg.biblio.util.EMUtil;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Inicializar extends HttpServlet {

    private static final long serialVersionUID = -8777892581202322531L;

    private EntityManager em;

    public Inicializar() {

        System.out.println("INICIALIZAR ATIVADO!");

        System.out.println("VERIFICANDO A EXISTENCIA DO BANCO DE DADOS!");
        /*
        SHOW tables FROM banco_de_dados;
        List f = entityManager.createNativeQuery("select nome, dataNascimento from funcionario where matricula=1").getResultList();
         */

        em = EMUtil.getNovoManager();

        List lista = em.createNativeQuery("SHOW tables FROM biblio").getResultList();
        boolean v = false;
        for (int x = 0; x < lista.size(); x++) {
            System.out.println("TAB: " + lista.get(x).toString()); //FUNCIONA
            String tabela = lista.get(x).toString();
            if (tabela.contains("pais")) {
                SQLUtil.popularTabelaVazia(em, tabela, insertPais());
            }

        }
        for (int x = 0; x < lista.size(); x++) {
            System.out.println("TAB: " + lista.get(x).toString()); //FUNCIONA
            String tabela = lista.get(x).toString();

            if (tabela.contains("estado")) {
                SQLUtil.popularTabelaVazia(em, tabela, insertUfs());
            }

        }

        for (int x = 0; x < lista.size(); x++) {
            System.out.println("TAB: " + lista.get(x).toString()); //FUNCIONA
            String tabela = lista.get(x).toString();

            if (tabela.contains("cidade")) {
                SQLUtil.popularTabelaVazia(em, tabela, insertCidade());
            }

        }

        em.close();

    }

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
            throws ServletException, IOException {
        super.service(arg0, arg1);
        arg1.getOutputStream().print("Iniciado");
    }

    private String insertPais() {
        return "INSERT INTO `pais` (`id`, `nome`, `sigla`) VALUES (1, 'Brasil', 'BR');";
    }

    private String insertUfs() {
        String sql = "INSERT INTO `estado` (`id`, `nome`, `uf`, `pais_id`) VALUES\n"
                + "(1, 'Acre', 'AC', 1),\n"
                + "(2, 'Alagoas', 'AL', 1),\n"
                + "(3, 'Amazonas', 'AM', 1),\n"
                + "(4, 'Amapá', 'AP', 1),\n"
                + "(5, 'Bahia', 'BA', 1),\n"
                + "(6, 'Ceará', 'CE', 1),\n"
                + "(7, 'Distrito Federal', 'DF', 1),\n"
                + "(8, 'Espírito Santo', 'ES', 1),\n"
                + "(9, 'Goiás', 'GO', 1),\n"
                + "(10, 'Maranhão', 'MA', 1),\n"
                + "(11, 'Minas Gerais', 'MG', 1),\n"
                + "(12, 'Mato Grosso do Sul', 'MS', 1),\n"
                + "(13, 'Mato Grosso', 'MT', 1),\n"
                + "(14, 'Pará', 'PA', 1),\n"
                + "(15, 'Paraíba', 'PB', 1),\n"
                + "(16, 'Pernambuco', 'PE', 1),\n"
                + "(17, 'Piauí', 'PI', 1),\n"
                + "(18, 'Paraná', 'PR', 1),\n"
                + "(19, 'Rio de Janeiro', 'RJ', 1),\n"
                + "(20, 'Rio Grande do Norte', 'RN', 1),\n"
                + "(21, 'Rondônia', 'RO', 1),\n"
                + "(22, 'Roraima', 'RR', 1),\n"
                + "(23, 'Rio Grande do Sul', 'RS', 1),\n"
                + "(24, 'Santa Catarina', 'SC', 1),\n"
                + "(25, 'Sergipe', 'SE', 1),\n"
                + "(26, 'São Paulo', 'SP', 1),\n"
                + "(27, 'Tocantins', 'TO', 1);";
        return sql;
    }

    private String insertCidade() {
        String sql = "INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES\n"
                + "(894, 'Alvorada do Norte', 9),\n"
                + "(1109, 'Simolândia', 9),\n"
                + "(1110, 'Sítio d`Abadia', 9),\n"
                + "(1074, 'Posse', 9),\n"
                + "(5270, 'São Paulo', 26),\n"
                + "(3658, 'Rio de Janeiro', 19),\n"
                + "(958, 'Damianópolis', 9),\n"
                + "(1019, 'Mambaí', 9),\n"
                + "(977, 'Goiânia', 9),\n"
                + "(882, 'Brasília', 7),\n"
                + "(2878, 'Curitiba', 18)";
        return sql;
    }
}
