package com.examen.concredito.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.examen.concredito.entidades.CancionEntity;
import com.examen.concredito.generic.GenericBean;

/**
 * 
 * @author jrios
 *
 */
@Named
@ViewScoped
public class RockolaBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 759390112432985319L;
	private static final Logger log = Logger.getLogger(RockolaBean.class);
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private List<CancionEntity> listaReproduccion;
	private CancionEntity cancion;
	private boolean play;

	@PostConstruct
	public void init() {
		setPlay(false);
		setListaReproduccion(new ArrayList<>());

		CancionEntity cancion0 = new CancionEntity();
		cancion0.setAutor("León Larregui");
		cancion0.setTitulo("Brillas");
		cancion0.setRuta("../resources/musica/brillas.mp3");
		cancion0.setFecha(SDF.format(new Date()));
		getListaReproduccion().add(cancion0);
		
		CancionEntity cancion1 = new CancionEntity();
		cancion1.setAutor("Mariya Takeuchi");
		cancion1.setTitulo("Mariya Takeuchi 竹内 まりや Plastic Love");
		cancion1.setRuta("../resources/musica/Plastic Love.mp3");
		cancion1.setFecha(SDF.format(new Date()));
		getListaReproduccion().add(cancion1);

	}

	public void reproducir(ActionEvent event) {
		if (isPlay()) {
			setPlay(false);
			setCancion(new CancionEntity());
		} else {
			setPlay(true);
		}
	}

	public List<CancionEntity> getListaReproduccion() {
		return listaReproduccion;
	}

	public void setListaReproduccion(List<CancionEntity> listaReproduccion) {
		this.listaReproduccion = listaReproduccion;
	}

	public CancionEntity getCancion() {
		return cancion;
	}

	public void setCancion(CancionEntity cancion) {
		this.cancion = cancion;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

}
