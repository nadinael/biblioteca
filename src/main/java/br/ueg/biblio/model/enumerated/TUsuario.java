package br.ueg.biblio.model.enumerated;

public enum TUsuario {

    A, B, L, S;//s(sistema)

    public String getDescricao() {
        switch (this) {
            case A:
                return "ADMINISTRADOR";
            case B:
                return "BIBLIOTECARIO";
            case L:
                return "LEITOR";
            case S:
                return "SISTEMA";
            default:
                return "ERRO";
        }

    }

}
