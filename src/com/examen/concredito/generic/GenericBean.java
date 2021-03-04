package com.examen.concredito.generic;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GenericBean {
	protected String mensaje = "";
	
	public void agregarMensaje(String mensaje, String tipo) {
		FacesMessage facesMessage = new FacesMessage(mensaje);
		switch (tipo) {
		case "info":
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			break;
		case "warn":
			facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
			break;
		case "error":
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			break;
		}
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
