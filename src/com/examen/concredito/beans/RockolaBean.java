package com.examen.concredito.beans;

import java.io.File;
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

	private String SERVLET = "http://ec2-18-188-64-193.us-east-2.compute.amazonaws.com:8080/concredito-evaluacion/servlet/ServletAudio?ruta=";
	private String CARPETA_MUSICA = "/opt/musica/";

	@PostConstruct
	public void init() {
		setPlay(false);
		setListaReproduccion(new ArrayList<>());

		File file = new File(CARPETA_MUSICA);
		File[] listaMusica = file.listFiles();
		for (File archivo : listaMusica) {
			CancionEntity cancionEntity = new CancionEntity();
			cancionEntity.setTitulo(archivo.getName());
			cancionEntity.setRuta(obtenerRutaImagenServlet(archivo.getPath()));
			getListaReproduccion().add(cancionEntity);
		}
	}

	/***
	 * 
	 * @param url
	 * @return
	 */
	public String obtenerRutaImagenServlet(String url) {
		if (url == null) {
			return "";
		}
		String rutaAudio = construirURL(url);
		String rutaCompleta = SERVLET + rutaAudio;

		return rutaCompleta;
	}

	/***
	 * Sustituye las \\ por / en una URL
	 * 
	 * @param rutaImagen
	 * @return
	 */
	public String construirURL(String rutaImagen) {
		char[] arreglo = rutaImagen.toCharArray();
		String url = "";
		for (char letra : arreglo) {
			if (letra == 92) {
				url += "/";
			} else {
				url += letra;
			}
		}
		return url;
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
