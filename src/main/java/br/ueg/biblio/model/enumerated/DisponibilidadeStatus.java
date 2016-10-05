package br.ueg.biblio.model.enumerated;

public enum DisponibilidadeStatus {

    P, O, D, E;

    public String getDescricao() {
        switch (this) {
            case D:
                return "DISPONÍVEL";
            case E:
                return "EMPRESTADO";
            case O:
                return "DOADO";
            case P:
                return "DESCONHECIDO";
            default:
                return "ERRO";
        }
    }

}
