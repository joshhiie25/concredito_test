package com.examen.concredito.converters;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.examen.concredito.entidades.CursoEntity;
import com.examen.concredito.servicios.CursoService;

/***
 * 
 * @author jrios
 *
 */
@Named
@FacesConverter(value = "cursoConverter", managed = true)
public class cursoConverter implements Converter<Object> {

	@Inject
	private CursoService cursoService = new CursoService();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		CursoEntity resultado = null;
		if (value != null && value.trim().length() > 0) {
			Integer idcurso = Integer.parseInt(value);
			List<CursoEntity> listaCursos;
			try {
				listaCursos = cursoService.obtenerTodos();
			} catch (Exception e) {
				return null;
			}
			for (CursoEntity curso : listaCursos) {
				if (curso.getIdcurso().equals(idcurso)) {
					resultado = curso;
					break;
				}
			}
		}
		return resultado;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			CursoEntity factura = (CursoEntity) object;
			return String.valueOf(factura.getIdcurso());
		} else {
			return "";
		}
	}

}
