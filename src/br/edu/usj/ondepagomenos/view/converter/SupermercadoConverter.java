package br.edu.usj.ondepagomenos.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.usj.ondepagomenos.entidades.Categoria;
import br.edu.usj.ondepagomenos.entidades.Supermercado;

@FacesConverter(value = "supermercadoConverter")    
public class SupermercadoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Supermercado) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Supermercado) {
        	Supermercado entity= (Supermercado) value;
            if (entity != null && entity instanceof Supermercado && entity.getIdSupermercado() != null) {
                uiComponent.getAttributes().put( entity.getIdSupermercado().toString(), entity);
                return entity.getIdSupermercado().toString();
            }
        }
        return "";
    }
}
