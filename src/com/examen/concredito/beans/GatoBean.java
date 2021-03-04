package com.examen.concredito.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.examen.concredito.entidades.IndiceEntity;
import com.examen.concredito.entidades.TicTacToeEntity;
import com.examen.concredito.generic.GenericBean;

/**
 * 
 * @author jrios
 *
 */
@Named
@ViewScoped
public class GatoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 759390112432985319L;
	private static final Logger log = Logger.getLogger(GatoBean.class);

	private List<IndiceEntity> listaIndices;
	private TicTacToeEntity ticTacToeEntity;
	private boolean jugador;
	private boolean ganador;

	@PostConstruct
	public void init() {
		reiniciarVariables();
	}

	/**
	 * Esté metédo ocupa la posicion seleccionada con una imagen representativa, a
	 * su vez determina si 1
	 * 
	 * @param obj
	 */
	public void asignarPosicion(TicTacToeEntity obj) {
		obj.setAsignado(true);
		obj.setMarca(isJugador() ? "O" : "X");
		obj.setUrl(isJugador() ? "circulo.png" : "cruz.png");
		setJugador(!isJugador());
		determinarGanador();
	}

	/**
	 * Determina si ya se agotaron todos los movimientos de la partida
	 * 
	 * @return boolean
	 */
	private boolean determinarJuegoTerminado() {
		boolean resultado = true;
		for (IndiceEntity indice : getListaIndices()) {
			for (TicTacToeEntity ticTacToeEntity : indice.getListaPosiciones()) {
				if (!ticTacToeEntity.isAsignado()) {
					resultado = false;
					break;
				}
			}
		}
		return resultado;
	}

	/**
	 * Hace un escaneo en el arregle en todas las combinaciones posibles para
	 * determinar si ya hay un ganador
	 */
	private void determinarGanador() {
		List<String> lineas = new ArrayList<>();
		for (IndiceEntity indice : getListaIndices()) {
			for (TicTacToeEntity ticTacToeEntity : indice.getListaPosiciones()) {
				if (ticTacToeEntity.isAsignado()) {
					lineas.add(ticTacToeEntity.getMarca());
				} else {
					lineas.add("");
				}
			}
		}

		String ganador = "";
		for (int contador = 0; contador < 8; contador++) {
			String linea = null;

			switch (contador) {
			case 0:
				linea = lineas.get(0) + lineas.get(1) + lineas.get(2); // fila 1
				break;
			case 1:
				linea = lineas.get(3) + lineas.get(4) + lineas.get(5); // fila 2
				break;
			case 2:
				linea = lineas.get(6) + lineas.get(7) + lineas.get(8); // fila 3
				break;
			case 3:
				linea = lineas.get(0) + lineas.get(3) + lineas.get(6); // columna 1
				break;
			case 4:
				linea = lineas.get(1) + lineas.get(4) + lineas.get(7); // columna 2
				break;
			case 5:
				linea = lineas.get(2) + lineas.get(5) + lineas.get(8); // columna 3
				break;
			case 6:
				linea = lineas.get(0) + lineas.get(4) + lineas.get(8); // fila cruzada 1
				break;
			case 7:
				linea = lineas.get(2) + lineas.get(4) + lineas.get(6); // fila cruzada 2
				break;
			}

			if (linea.equals("XXX")) {
				ganador = "X";
			}

			if (linea.equals("OOO")) {
				ganador = "O";
			}
		}

		if (ganador.equals("X")) {
			agregarMensaje("El jugador X ganó!", "info");
			setGanador(true);
		} else if (ganador.equals("O")) {
			agregarMensaje("El jugador O ganó!", "info");
			setGanador(true);
		} else {
			if (determinarJuegoTerminado()) {
				agregarMensaje("Fue un empate", "warn");
			}
		}
	}

	/*
	 * Inicializa las variables del juego
	 */
	public void reiniciarVariables() {
		setJugador(false);
		setGanador(false);
		setListaIndices(new ArrayList<>());

		int contador = 0;
		for (; contador < 3; contador++) {
			List<TicTacToeEntity> listaPosiciones = new ArrayList<>();
			IndiceEntity indiceEntity = new IndiceEntity();
			indiceEntity.setIndice("" + contador);

			int contadorpos = 0;
			for (; contadorpos < 3; contadorpos++) {
				TicTacToeEntity tictactoe = new TicTacToeEntity();
				tictactoe.setAsignado(false);
				tictactoe.setMarca("");
				tictactoe.setUrl("unnamed.png");
				listaPosiciones.add(tictactoe);
			}
			indiceEntity.setListaPosiciones(listaPosiciones);
			getListaIndices().add(indiceEntity);
		}
	}

	public List<IndiceEntity> getListaIndices() {
		return listaIndices;
	}

	public void setListaIndices(List<IndiceEntity> listaIndices) {
		this.listaIndices = listaIndices;
	}

	public TicTacToeEntity getTicTacToeEntity() {
		return ticTacToeEntity;
	}

	public void setTicTacToeEntity(TicTacToeEntity ticTacToeEntity) {
		this.ticTacToeEntity = ticTacToeEntity;
	}

	public boolean isJugador() {
		return jugador;
	}

	public void setJugador(boolean jugador) {
		this.jugador = jugador;
	}

	public boolean isGanador() {
		return ganador;
	}

	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}

}
