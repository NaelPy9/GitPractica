package com.nttdata.logback;

import java.util.Date;
import java.util.HashMap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

public class LoggerLayout extends LayoutBase<ILoggingEvent> {
	private String prefix;

	/** Imprime nombre del hilo */
	private boolean printThreadName = Boolean.TRUE;

	public String doLayout(ILoggingEvent event) {

		// String Builder 
		final StringBuilder traza = new StringBuilder(128);
		final String backToColor = "\u001B[0m";

		traza.append(new Date(event.getTimeStamp()));
		traza.append(" ");
		traza.append("\u001B[94m");
		traza.append(prefix);
		traza.append(" ");
		
		
		traza.append(coloredLevel(event.getLevel()));
		traza.append(event.getLevel());
		traza.append(backToColor);
		
		if (printThreadName) {
			traza.append("\u001B[97m");
			traza.append(" [");
			traza.append(event.getThreadName());
			traza.append("]");
		}

		traza.append(" ");
		traza.append(backToColor);
		traza.append(event.getLoggerName());
		traza.append(" - ");
		traza.append("\u001B[93m");
		traza.append(event.getFormattedMessage());
		traza.append(backToColor);
		traza.append("\n");

		return traza.toString();
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the printThreadName
	 */
	public boolean isPrintThreadName() {
		return printThreadName;
	}

	/**
	 * @param printThreadName the printThreadName to set
	 */
	public void setPrintThreadName(boolean printThreadName) {
		this.printThreadName = printThreadName;
	}
	/**
	 * 
	 * @param mainLevel Level que signifca el nivel de la traza que se esta pintando
	 * @return String color en codigo ANSI para que se pinte segun la importancia de la traza
	 */
	public String coloredLevel(Level mainLevel){
		HashMap<Level, String> map = new HashMap<>();
		map.put(Level.TRACE, "\u001B[97m");
		map.put(Level.DEBUG, "\u001B[92m");
		map.put(Level.INFO, "\u001B[36m");
		map.put(Level.WARN, "\u001B[93m");
		map.put(Level.ERROR, "\u001B[31m");
		
		return map.get(mainLevel);
	}
}
