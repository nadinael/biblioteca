package br.ueg.biblio.model.enumerated;

import java.util.List;

public enum ClassesConhecimentoHumano {

    GENERALIDADES,
    FILOSOFIA,
    RELIGIAO,
    CIENCIAS_SOCIAIS,
    LINGUAS,
    CIENCIAS_PURAS,
    CIENCIAS_APLICADAS,
    ARTES,
    LITERATURA,
    HISTORIA_GEOGRAFIA;

    public String getDescricao() {
        switch (this) {
            case GENERALIDADES:
                return "GENERALIDADES";
            case FILOSOFIA:
                return "FILOSOFIA";
            case RELIGIAO:
                return "RELIGIAO";
            case CIENCIAS_SOCIAIS:
                return "CIÊNCIAS SOCIAIS";
            case LINGUAS:
                return "LÍGUAS";
            case CIENCIAS_PURAS:
                return "CIÊCIAS PURAS";
            case CIENCIAS_APLICADAS:
                return "CIÊNCIAS APLICADAS";
            case ARTES:
                return "ARTES";
            case LITERATURA:
                return "LITERATURA";
            case HISTORIA_GEOGRAFIA:
                return "HIST/GEO";
            default:
                return "DESCONHECIDO";
        }
    }

}
