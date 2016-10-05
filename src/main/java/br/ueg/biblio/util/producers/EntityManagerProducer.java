package br.ueg.biblio.util.producers;

import br.ueg.biblio.annotations.Configurado;
import br.ueg.biblio.annotations.Localhost;
import br.ueg.biblio.files.DataBaseFileManager;
import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer implements Injetavel {

    private EntityManagerFactory emf;

    public EntityManagerProducer() {
        System.out.println("Iniciando Injeção de Dependência EM".toUpperCase());
        DataBaseModel model = new DataBaseFileManager().obterCfgConexao();
        final Map mapa = model.getConfig();
        emf = Persistence.createEntityManagerFactory("persistenceUnit", mapa);

    }

    @Produces
    @RequestScoped
    @Localhost
    public EntityManager createEntityManager() {
        System.out.println("Criando manager".toUpperCase());
        return emf.createEntityManager();
    }

    public void closeEntityManager(@Localhost @Disposes EntityManager em) {
        System.out.println("Fechando manager".toUpperCase());
        em.close();
    }

    @Produces
    @RequestScoped
    @Configurado
    public EntityManager criarEntityManager() {
        System.out.println("Abrindo Enity Manager @configurado".toUpperCase());
        return emf.createEntityManager();
    }

    public void fecharEntityManager(@Configurado @Disposes EntityManager em) {
        System.out.println("fenchando entity manager @cofigurado".toUpperCase());
        em.close();
    }

}
