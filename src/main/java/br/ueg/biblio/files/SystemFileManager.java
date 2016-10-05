package br.ueg.biblio.files;

import br.ueg.biblio.cfg.SystemCfgModel;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import br.ueg.biblio.util.exceptions.ConfiguracaoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemFileManager implements Injetavel {

    private boolean cfgExistente;

    public SystemFileManager(boolean cfgExistente) {
        this.cfgExistente = cfgExistente;
    }

    public SystemCfgModel obterConfiguracoesModeladas() {
//Aqui eu ajustp se o sistema esta pronto ou não
        if (cfgExistente) {

            String arquivo = FileManager.montartLocalAbosoluto("system");

            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(arquivo));
                SystemCfgModel sistema = new SystemCfgModel();

                sistema.setDatabaseReady(Boolean.parseBoolean(prop.getProperty("sis.cfg.ready")));
                System.out.println("br.ueg.biblio.files.SystemFileManager.obterConfiguracoesModeladas()");
                System.out.println("configuracao do sistema importada com sucesso".toUpperCase());
                return sistema;
            } catch (FileNotFoundException ex) {
                throw new ConfiguracaoException("Arquivo de propriedades nao encontrado." + ex);
            } catch (IOException ex) {
                throw new ConfiguracaoException("Arquivo de propriedades nao encontrado." + ex);
            }

        }
        return null;

    }

}
