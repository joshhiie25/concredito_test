package com.examen.concredito.servicios;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.examen.concredito.control.CalificacionControl;
import com.examen.concredito.entidades.CalificacionEntity;
import com.examen.concredito.generic.ConexionPersistencia;

/***
 * 
 * @author jrios
 *
 */
@Named
@RequestScoped
public class CalificacionService implements Serializable {
	private static final long serialVersionUID = 3710729231790880511L;
	protected final CalificacionControl calificacionControl = new CalificacionControl(ConexionPersistencia.getFactory());

	/**
	 * Obtiene un comprobante
	 * 
	 * @param cotizacion
	 * @return
	 * @throws Exception
	 */
	public List<CalificacionEntity> obtenerTodos(Integer idcurso) throws Exception {
		return calificacionControl.obtenerTodos(idcurso);
	}
}
