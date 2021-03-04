package com.examen.concredito.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class ServletAudio extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(ServletAudio.class);
	private static final long serialVersionUID = -36871168537423534L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Inicio carga de imagen");
		String rutra = request.getParameter("ruta");
		log.info(rutra);
        File file = new File(rutra);
        response.setHeader("Content-Type", getServletContext().getMimeType(rutra));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + rutra.substring(rutra.lastIndexOf("/")+1, rutra.length()) + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
        log.info("Envio de imagen terminado");
    }

}
