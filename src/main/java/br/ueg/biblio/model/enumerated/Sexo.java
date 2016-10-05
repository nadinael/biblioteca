package br.ueg.biblio.model.enumerated;

public enum Sexo {

    M, F;

    public String getDescricao() {
        switch (this) {
            case M:
                return "MASCULINO";
            case F:
                return "FEMININO";
            default:
                return "";
        }
    }
    
}