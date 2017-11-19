package br.edu.usj.ondepagomenos.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.usj.ondepagomenos.entidades.Perfil;

@FacesConverter(value = "perfilConverter")
public class PerfilConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty() && !value.equals("Selecione")) {
			return Perfil.valueOf(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Perfil) {
			return ((Perfil) value).name();
		}
		return null;
	}
}