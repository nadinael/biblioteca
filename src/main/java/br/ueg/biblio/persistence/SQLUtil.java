package br.ueg.biblio.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.persistence.EntityManager;

public class SQLUtil {

    private static Connection conexao = null;

    public static Connection getConnection(EntityManager em) {
        Connection con = null;
        if (em != null) {
            System.out.println(" : INICIANDO CONEXAO COMPROPRIEDADES DE PERSISTENCE.XML");
            String url = em.getEntityManagerFactory().getProperties().get("hibernate.connection.url").toString();
            String user = em.getEntityManagerFactory().getProperties().get("hibernate.connection.username").toString();
            String password = em.getEntityManagerFactory().getProperties().get("hibernate.connection.password").toString();
            System.out.println(" : INFORMACOES RECUPERADAS!");

            try {
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                System.out.println(" : ERRO AO TENTAR CRIAR CONEXAO: \n!" + ex);
            }
        }

        return con;

    }

    private static void conectar(EntityManager em) {
        if (em != null) {
            System.out.println(" : INICIANDO CONEXAO COM PROPRIEDADES DE PERSISTENCE.XML");
            String url = "jdbc:mysql://127.0.0.1:3306/biblio";// em.getEntityManagerFactory().getProperties().get("hibernate.connection.url").toString();
            String user = "root";//em.getEntityManagerFactory().getProperties().get("hibernate.connection.username").toString();
            System.out.println(" : INFORMACOES RECUPERADAS!");

            try {
                SQLUtil.conexao = DriverManager.getConnection(url, user, "");
            } catch (SQLException ex) {
                System.out.println(" : ERRO AO TENTAR CRIAR CONEXAO: \n!" + ex);
            }
        }

    }

    public static boolean executarSQL(EntityManager em, String sql) {
        try {
            conectar(em);
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(" : ERRO AO TENTAR EXECUTAR SQL: \n!" + ex);
            return false;
        }

    }

    public static boolean popularTabelaVazia(EntityManager em, String tabela, String sql) {
        String select = "select COUNT(*) quantidade from " + tabela.toLowerCase();
        try {
            conectar(em);
            PreparedStatement stmt = conexao.prepareStatement(select);
            stmt.execute();
            ResultSet res = stmt.getResultSet();
            int total = 5;
            while (res.next()) {
                total = res.getInt("quantidade");
            }
            System.out.println("QUANTIDADE DE REGISTROS NA TABELA " + tabela + " : " + total);
            if (total <= 0) {
                System.out.println("EXECUTANDO CADASTROS NA TABELA: " + tabela);
                stmt = conexao.prepareStatement(sql);
                stmt.execute();
                System.out.println("CADASTROS EFETUADOS");
            }
            stmt.close();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(" : ERRO AO TENTAR EXECUTAR SQL: \n!" + ex);
            return false;
        }

    }

}
