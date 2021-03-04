package com.examen.concredito.beans;

import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.examen.concredito.entidades.CalificacionEntity;
import com.examen.concredito.entidades.CursoEntity;
import com.examen.concredito.generic.GenericBean;
import com.examen.concredito.servicios.CalificacionService;
import com.examen.concredito.servicios.CursoService;

/**
 * 
 * @author jrios
 *
 */
@Named
@ViewScoped
public class CalificacionesBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = 8211679699803156156L;
	private static final Logger log = Logger.getLogger(CalificacionesBean.class);

	private List<CursoEntity> listaCursos;
	private List<CalificacionEntity> listaCalificaciones;

	private CursoEntity cursoEntity;

	@Inject
	private CalificacionService calificacionService;

	@Inject
	private CursoService cursoService;

	@PostConstruct
	public void init() {
		obtenerCursos();
		obtenerCalificaciones();
	}

	/**
	 * Inicializa la lista de cursos llamando al cursoServicio
	 */
	private void obtenerCursos() {
		try {
			setListaCursos(cursoService.obtenerTodos());
		} catch (Exception e) {
			log.error(e);
			mensaje = "No se pudieron obtener los cursos";
			agregarMensaje(mensaje, "error");
		}
	}

	/**
	 * Obtiene las 10 mejores calificaciones del curso
	 */
	public void obtenerCalificaciones() {
		Integer idcurso = (cursoEntity == null) ? 1 : cursoEntity.getIdcurso();
		try {
			setListaCalificaciones(calificacionService.obtenerTodos(idcurso));
		} catch (Exception e) {
			log.error(e);
			mensaje = "No se pudieron obtener las calificaciones del curso";
			agregarMensaje(mensaje, "error");
		}
	}

	public List<CalificacionEntity> getListaCalificaciones() {
		return listaCalificaciones;
	}

	public void setListaCalificaciones(List<CalificacionEntity> listaCalificaciones) {
		this.listaCalificaciones = listaCalificaciones;
	}

	public List<CursoEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursoEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public CursoEntity getCursoEntity() {
		return cursoEntity;
	}

	public void setCursoEntity(CursoEntity cursoEntity) {
		this.cursoEntity = cursoEntity;
	}
}
