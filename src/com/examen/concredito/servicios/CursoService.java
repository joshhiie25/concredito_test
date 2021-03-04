package com.examen.concredito.servicios;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.examen.concredito.control.CursoControl;
import com.examen.concredito.entidades.CursoEntity;
import com.examen.concredito.generic.ConexionPersistencia;

/***
 * 
 * @author jrios
 *
 */
@Named
@RequestScoped
public class CursoService implements Serializable {
	private static final long serialVersionUID = 940611005702656397L;
	protected final CursoControl cursoControl = new CursoControl(ConexionPersistencia.factory);

	/**
	 * Obtiene un comprobante
	 * 
	 * @param cotizacion
	 * @return
	 * @throws Exception
	 */
	public List<CursoEntity> obtenerTodos() throws Exception {
		return cursoControl.obtenerTodos();
	}
}
