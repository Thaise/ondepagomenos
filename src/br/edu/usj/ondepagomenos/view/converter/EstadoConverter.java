package br.edu.usj.ondepagomenos.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.usj.ondepagomenos.entidades.Estado;

@FacesConverter(value = "estadoConverter")    
public class EstadoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Estado) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Estado) {
        	Estado entity= (Estado) value;
            if (entity != null && entity instanceof Estado && entity.getIdEstado() != null) {
                uiComponent.getAttributes().put( entity.getIdEstado().toString(), entity);
                return entity.getIdEstado().toString();
            }
        }
        return "";
    }
}
