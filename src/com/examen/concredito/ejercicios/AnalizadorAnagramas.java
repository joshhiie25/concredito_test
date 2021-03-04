package com.examen.concredito.ejercicios;

import java.util.Arrays;

/**
 * 
 * Está clase es una analizador de anagramas, determina si dos cadenas son
 * anagramas
 * 
 * @author jrios
 *
 */
public class AnalizadorAnagramas {

	/**
	 * Este método ordena los caracteres de una cadena y obtiene la cadena entrante
	 * ordenada.
	 * 
	 * @param String
	 * @return String
	 */
	public static String ordenarCadena(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return "";
		}
		cadena = cadena.toLowerCase();
		cadena = eliminarCaracteresGenerales(cadena);
		cadena = eliminarCaracteresEspeciales(cadena);
		char[] listaCaracteres = cadena.toCharArray();
		Arrays.sort(listaCaracteres);
		String resultado = String.valueOf(listaCaracteres);
		System.out.println(resultado);
		return resultado;
	}

	/**
	 * Este método borra los caracteres especiales de una cadena de texto y la
	 * regresa
	 * 
	 * @param String
	 * @return String
	 */
	private static String eliminarCaracteresEspeciales(String cadena) {
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String resultado = cadena;
		for (int i = 0; i < original.length(); i++) {
			resultado = resultado.replace(original.charAt(i), ascii.charAt(i));
		}
		return resultado;
	}

	/**
	 * Este método borra los caracteres generales de una cadena de texto y la
	 * regresa
	 * 
	 * @param cadena
	 * @return
	 */
	private static String eliminarCaracteresGenerales(String cadena) {
		String result = cadena.replaceAll("\\p{Punct}", "");
		result = result.replaceAll("[^\\dA-Za-z]", "");
		return result;
	}

	/**
	 * Verifica si es posible que las dos cadenas sean un anagrama
	 * 
	 * @param String
	 * @param String
	 * @return Boolean, si es true es un anagrama, caso contrario no es un anagrama
	 */
	public static boolean verificar(String cadena1, String cadena2) {
		return cadena1.contains(cadena2);
	}
}
