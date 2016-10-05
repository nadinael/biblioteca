package br.ueg.biblio.view.conversor;

import br.ueg.biblio.model.Cidade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter, Serializable {

    public CidadeConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null) {
            return (Cidade) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Cidade est = (Cidade) value;
            component.getAttributes().put(est.getId().toString(), est);
            return est.getId().toString();
        }
        return "";
    }

}
