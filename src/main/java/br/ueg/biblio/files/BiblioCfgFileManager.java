package br.ueg.biblio.files;

import br.ueg.biblio.cfg.BiblioCfg;
import br.ueg.biblio.cfg.DataBaseModel;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BiblioCfgFileManager implements Injetavel {

    public static BiblioCfg getBiblioCfg() {
        Properties propriedades = FileManager.getProperties("biblio-config");

        BiblioCfg emConfig = new BiblioCfg();
        emConfig.setPrazo(Integer.parseInt(propriedades.getProperty("biblio.loan.prazo")));
        emConfig.setSabadoUltil(Boolean.valueOf(propriedades.getProperty("biblio.loan.sabado")));
        emConfig.setTaxaDiaria((Double.parseDouble(propriedades.getProperty("biblio.loan.diaria"))));
        emConfig.setTaxaInicial((Double.parseDouble(propriedades.getProperty("biblio.loan.inicial"))));
        emConfig.setValorMaximo((Double.parseDouble(propriedades.getProperty("biblio.loan.maximo"))));

        return emConfig;
    }

    public static boolean atualizarCfgBiblio(BiblioCfg config) {

        String arquivo = FileManager.montartLocalAbosoluto("biblio-config");

        Properties propriedades = new Properties();

        try {
            //carregado arquivo
            File file = new File(arquivo);
            FileInputStream inputStream = new FileInputStream(file);
            propriedades.load(inputStream);
            //arquivo carregado

            propriedades.setProperty("biblio.loan.prazo", String.valueOf(config.getPrazo()));

            propriedades.setProperty("biblio.loan.sabado", String.valueOf(config.isSabadoUltil()));

            propriedades.setProperty("biblio.loan.diaria", String.valueOf(config.getTaxaDiaria()));

            propriedades.setProperty("biblio.loan.inicial", String.valueOf(config.getTaxaInicial()));

            propriedades.setProperty("biblio.loan.maximo", String.valueOf(config.getValorMaximo()));

            //salvando
            FileOutputStream outputStream = new FileOutputStream(file);
            propriedades.store(outputStream, null);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            System.out.println("CONFIGURACOES SALVAS");
            return true;

        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

    }

}
