package br.ueg.biblio.model.enumerated;

public enum EmprestimoStatus {
    D, P, M, C;

    public String getDescricao() {
        switch (this) {
            case D:
                return "DEVOLVIDO";
            case P:
                return "PENDENTE";
            case M:
                return "MULTADO";
            case C:
                return "CANCELADO";
            default:
                return "ERRO";
        }
    }

}
