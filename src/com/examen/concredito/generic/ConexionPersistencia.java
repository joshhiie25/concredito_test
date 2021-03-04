package com.examen.concredito.generic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionPersistencia {
	
	public static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public static SessionFactory getFactory() {
		return factory;
	}

	public static void setFactory(SessionFactory factory) {
		ConexionPersistencia.factory = factory;
	}
	
	

}
