package br.ueg.biblio.view.conversor;

import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.model.Estado;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "meu-teste")
public class ConverterPadrao implements Converter, Serializable {

    private RepositoryCore loRep;

    public ConverterPadrao() {

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Estado retorno = null;

        if (value != null) {
            loRep = new RepositoryCore();
            System.out.println("br.ueg.biblio.view.conversor.CategoriaConverter.getAsObject()");
            System.out.println("Metodo GetAsObject - Ativado".toUpperCase());
            System.out.println("valor recebido como parametro: ".toUpperCase() + " >_ " + value);
            Long id = new Long(value);
            retorno = loRep.EstadoPorId(id);
        }
        System.out.println("RETORNO - Ativado".toUpperCase() + retorno.getNome());
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("".toUpperCase());
        System.out.println("Metodo GetAsString - Ativado".toUpperCase());
        System.out.println("valor recebido como parametro: ".toUpperCase() + " >_ " + value);
        if (value != null) {
            return ((Estado) value).getId().toString();
        }

        return "";
    }

}
