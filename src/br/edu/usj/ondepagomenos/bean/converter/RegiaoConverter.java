package br.edu.usj.ondepagomenos.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.entidades.Regiao;

@FacesConverter(value = "regiaoConverter")    
public class RegiaoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Regiao) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Regiao) {
        	Regiao entity= (Regiao) value;
            if (entity != null && entity instanceof Regiao && entity.getIdRegiao() != null) {
                uiComponent.getAttributes().put( entity.getIdRegiao().toString(), entity);
                return entity.getIdRegiao().toString();
            }
        }
        return "";
    }
}
