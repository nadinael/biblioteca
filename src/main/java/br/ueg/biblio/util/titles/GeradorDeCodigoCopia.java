package br.ueg.biblio.util.titles;

import br.ueg.biblio.model.Exemplar;
import java.util.List;

public class GeradorDeCodigoCopia {

    private static final String PREFIXO = "COPY-00000";

    public static String gerar(List<Exemplar> copias) {
        /*StringBuilder codigo = new StringBuilder();
        String ad;
        if (titulo.getNome() == null) {
            System.out.println("MULO");
            titulo.setNome("O NULO");
        }
        
        2 primeiras letras do nome 
        3 primeiras da classe de conhecimento;
        3 primeiros do tipo da obra;
        dataatual
        quantidade em estoque
        

        ad = titulo.getNome().replaceAll(" ", "");
        ad = ad.toUpperCase();
        codigo.append(ad.substring(0, 2));

        ad = titulo.getAreaCon().getDescricao().replaceAll(" ", "");
        ad = ad.toUpperCase();
        codigo.append(ad.substring(0, 3));

        ad = titulo.getTipoDoTitulo().getDescricao().replaceAll(" ", "");
        ad = ad.toUpperCase();
        codigo.append(ad.substring(0, 3));

        LocalDate hj = LocalDate.now();
        ad = hj.toString().replaceAll("-", "");
        codigo.append(ad);
        System.out.println("PEGO DO TIULO: " + codigo);

        //return codigo.toString(); */
        if (copias == null) {
            return PREFIXO + "1";
        }

        if (copias.isEmpty()) {
            return PREFIXO + "1";
        }

        if (copias.size() <= 0) {
            return PREFIXO + "1";
        }

        int n = copias.size() + 1;

        return PREFIXO + n;

    }

}
