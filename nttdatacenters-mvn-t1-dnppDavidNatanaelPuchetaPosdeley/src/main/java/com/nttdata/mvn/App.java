package com.nttdata.mvn;

import java.util.Scanner;

/**
 * 
 * @author David
 *
 */
public class App {
	/**
	 * Metodo principal del programa donde se pone a prueba distintos metodos de la
	 * librería Commons Lang
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean cut;
		Scanner s = new Scanner(System.in);
		int mainOption;
		Menu m = new Menu();
		do {

			m.graphicView();
			mainOption = s.nextInt();
			cut = m.selectedOption(mainOption);

		} while (cut);
		s.close();
	}
}
