package br.ueg.biblio.core;

import br.ueg.biblio.model.Titulo;
import br.ueg.biblio.model.interfaces.Injetavel;
import java.util.Calendar;

public class ControllerCore implements Injetavel {

    public static void ajustandoNovaObra(Titulo titulo) {
        if (titulo.getEdicao() <= 0) {
            titulo.setEdicao(1);
        }
        titulo.setDataCadastro(Calendar.getInstance());
    }

    public static void adicionarCopia() {
    }

}
