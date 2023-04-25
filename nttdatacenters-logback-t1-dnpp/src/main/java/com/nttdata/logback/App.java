package com.nttdata.logback;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * 
 * @author David
 *
 */
public class App {
	/** LOOGER Root */
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(App.class);
	/** LOOGER de un desarrollador en concreto */
	private static final Logger LOG_DNPP = (Logger) LoggerFactory.getLogger("com.nttdata.logback.App.dnpp");
	
	/**
	 * Metodo principal de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		//Variables
		int numero = 10;
		
		//
		if (numero > 10) {
			LOG.debug("El numero es mayor a 10");
			LOG_DNPP.debug("Po mira si que es mayor que 10");
			
		} else {
			LOG.debug("El numero es menor a 10");
			LOG_DNPP.debug("Po mira no lo era");
		}
	}
}
