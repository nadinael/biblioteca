package br.ueg.biblio.util.producers;

import br.ueg.biblio.annotations.DAOPadrao;
import br.ueg.biblio.persistence.SuperDAO;
import br.ueg.biblio.model.interfaces.Injetavel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class DAOProducer implements Injetavel {

    @Produces
    @RequestScoped
    @DAOPadrao
    public SuperDAO superDAO() {
        return new SuperDAO();
    }

}
