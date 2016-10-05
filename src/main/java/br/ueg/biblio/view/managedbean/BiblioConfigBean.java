package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.cfg.BiblioCfg;
import br.ueg.biblio.files.BiblioCfgFileManager;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.JSFMsgs;
import br.ueg.biblio.util.ManagedBeanUtil;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "biblioConfigBean")
@ViewScoped
public class BiblioConfigBean implements Injetavel {

    private BiblioCfg config;
    private boolean editando;
    private String maximo;

    /*METODOS DE CONFIGURACAO*/
    public void configurarFormBiblioCfg() {
        if (ManagedBeanUtil.isNotPostback()) {
            this.config = BiblioCfgFileManager.getBiblioCfg();
            this.editando = false;
        }
    }

    /*METODOS DE ACAO*/
    public void editar() {
        this.editando = true;
        System.out.println("br.ueg.biblio.view.managedbean.BiblioConfigBean.editar()");
    }

    public void salvar() {
        if(config.getValorMaximo()<=(2*config.getTaxaInicial())){
            this.config.setValorMaximo((this.config.getTaxaInicial()+this.config.getTaxaDiaria())*3); 
        }
        BiblioCfgFileManager.atualizarCfgBiblio(config);
        JSFMsgs.pfGrwolInfo("Atualizado com sucesso.");
        this.editando = false;
    }

    /*METODOS GETTERS SETTERS*/
    public BiblioCfg getConfig() {
        return config;
    }

    public void setConfig(BiblioCfg config) {
        this.config = config;
    }

    public boolean isEditando() {
        return editando;
    }
    
}
