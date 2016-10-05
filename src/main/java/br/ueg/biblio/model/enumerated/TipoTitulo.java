package br.ueg.biblio.model.enumerated;

public enum TipoTitulo {

    LIVRO, REVISTA, JORNAL, AUDIO_VISUAL, J_ELETRONICO, ARTIGO_MONO_TCC, OUTROS;

    public String getDescricao() {
        switch (this) {
            case LIVRO:
                return "LIVRO";
            case REVISTA:
                return "REVISTA";
            case JORNAL:
                return "JORNAL";
            case AUDIO_VISUAL:
                return "MIDIA AUDIO VISUAL";
            case J_ELETRONICO:
                return "JOGO ELETRÔNICO";
            case ARTIGO_MONO_TCC:
                return "ARTS/MONO/TCC";
            case OUTROS:
                return "OUTROS";
            default:
                return "ERRO";
        }
    }

}
