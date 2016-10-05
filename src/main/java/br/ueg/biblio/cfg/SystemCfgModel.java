package br.ueg.biblio.cfg;

import br.ueg.biblio.files.SystemFileManager;
import br.ueg.biblio.model.interfaces.Injetavel;

public class SystemCfgModel implements Injetavel {

    private boolean databaseReady;

    private SystemFileManager manager;

    public SystemCfgModel() {
    }

    public boolean isDatabaseReady() {
        return databaseReady;
    }

    public void setDatabaseReady(boolean databaseReady) {
        this.databaseReady = databaseReady;
    }

}
