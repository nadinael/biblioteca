package br.ueg.biblio.model.enumerated;

public enum TipoDB {

    M, P;

    public String getDescricao() {
        switch (this) {
            case M:
                return "MySSQL";
            case P:
                return "Postgres";
            default:
                return "ERRO";
        }
    }

}
