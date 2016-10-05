package br.ueg.biblio.model.enumerated;

public enum MultaStatus {

    P, C, L;

    public String getDescricao() {
        switch (this) {
            case L:
                return "LIQUIDADA";
            case P:
                return "PENDENTE";
            case C:
                return "CANCELADA";
            default:
                return "ERRO";
        }
    }

}
