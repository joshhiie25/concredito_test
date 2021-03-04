package com.examen.concredito.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;

import com.examen.concredito.ejercicios.AnalizadorAnagramas;
import com.examen.concredito.generic.GenericBean;

/**
 * 
 * @author jrios
 *
 */
@Named
@ViewScoped
public class AnagramaBean extends GenericBean implements Serializable {

	private static final Logger log = Logger.getLogger(AnagramaBean.class);

	private static final long serialVersionUID = 7718744645619479222L;

	private static final String SI_ANAGRAMA = "Las cadenas son anagramas";
	private static final String NO_ANAGRAMA = "Las cadenas no son anagramas";

	private String cadena1;
	private String cadena2;

	@PostConstruct
	public void init() {
		setCadena1("");
		setCadena2("");
	}

	/**
	 * Esté método se encarga de analizar las cadenas entrantes y verificar si estas
	 * corresponden a un anagrama
	 */
	public void analizarCadenas() {
		if (verificarCampos()) {
			return;
		}

		String cadena1 = AnalizadorAnagramas.ordenarCadena(this.cadena1);
		String cadena2 = AnalizadorAnagramas.ordenarCadena(this.cadena2);
		boolean resultado = AnalizadorAnagramas.verificar(cadena1, cadena2);
		mensaje = resultado ? SI_ANAGRAMA : NO_ANAGRAMA;
		agregarMensaje(mensaje, "info");
		log.info(mensaje);
	}

	/***
	 * Esté método verifica los campos del formulario sean llenados correctamente.
	 * 
	 * @return boolean
	 */
	private boolean verificarCampos() {
		boolean resultado = false;
		if (this.cadena1 == null || this.cadena1.isEmpty()) {
			mensaje = "Ingrese el texto 1";
			agregarMensaje(mensaje, "warn");
			resultado = true;
		}

		if (this.cadena2 == null || this.cadena2.isEmpty()) {
			mensaje = "Ingrese el texto 2";
			agregarMensaje(mensaje, "warn");
			resultado = true;
		}

		return resultado;
	}

	public String getCadena1() {
		return cadena1;
	}

	public void setCadena1(String cadena1) {
		this.cadena1 = cadena1;
	}

	public String getCadena2() {
		return cadena2;
	}

	public void setCadena2(String cadena2) {
		this.cadena2 = cadena2;
	}

}
