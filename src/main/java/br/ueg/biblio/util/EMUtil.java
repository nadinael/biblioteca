package br.ueg.biblio.util;

import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.files.DataBaseFileManager;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.net.URL;
import java.util.Map;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.omnifaces.util.Faces;

public class EMUtil implements Injetavel {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static EntityManagerFactory factory;

    public EMUtil() {
        DataBaseModel model = new DataBaseFileManager().obterCfgConexao();
        final Map mapa = model.getConfig();
        factory = Persistence.createEntityManagerFactory("persistenceUnit", mapa);
    }

    public static EntityManager createEntityManagerFromModel(DataBaseModel model) {
        final Map mapa = model.getConfig();
        factory = Persistence.createEntityManagerFactory("persistenceUnit", mapa);
        return factory.createEntityManager();
    }

    public static EntityManager createEntityManager() {
        DataBaseFileManager db = new DataBaseFileManager();
        //String caminho = null;
        DataBaseModel model = db.obterCfgConexao();
        // System.out.println("caminho".toUpperCase() + caminho);
        //model = db.obterCfgConexaoServ(caminho);
        final Map mapa = model.getConfig();
        factory = Persistence.createEntityManagerFactory("persistenceUnit", mapa);
        return factory.createEntityManager();
    }

    public static void closeEntityManager(EntityManager em) {
        em.close();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public static EntityManager getManager() {
        EMUtil.emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EMUtil.em = emf.createEntityManager();
        return em;
    }

    public static EntityManager getNovoManager() {
        DataBaseModel model = new DataBaseModel();
        model.ajustarCfgPadrao();
        final Map mapa = model.getMap();
        emf = Persistence.createEntityManagerFactory("persistenceUnit", mapa);
        return emf.createEntityManager();
    }

}
