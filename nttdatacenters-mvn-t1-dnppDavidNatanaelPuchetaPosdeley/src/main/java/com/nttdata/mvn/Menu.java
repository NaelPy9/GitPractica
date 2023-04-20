package com.nttdata.mvn;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;



/**
 * 
 * @author David
 *
 */
public class Menu {
	/**
	 * Menu grafico
	 */
	public void graphicView() {
		System.out.println("Descubre ciertas utilidades de la libreria Commons Lang");
		System.out.println("1. Sin espacios en blanco");
		System.out.println("2. Abrevia la frase");
		System.out.println("3. ¿Contiene este caracter o frase?");
		System.out.println("4. ¿Contiene unicamente numeros?");
		System.out.print("Elige opción: ");
	}

	/**
	 * Switch que ayuda a la eleccion de opciones
	 * 
	 * @param option
	 */
	public boolean selectedOption(int option) {
		switch (option) {
		case 1:
			String noWhiteSpace = noWhiteSpaces();
			System.out.println(noWhiteSpace);
			return true;

		case 2:
			String abbreviation = abbreviation();
			System.out.println(abbreviation);
			return true;

		case 3:
			if (contains()) {
				System.out.println("Si contiene el caracter o frase introducido");
			} else {
				System.out.println("No contiene el caracter o frase introducido");
			}
			return true;

		case 4:
			if (isNumeric()) {
				System.out.println("Solo contiene numeros");
			} else {
				System.out.println("No contiene solo numeros");
			}
			return true;

		default:
			System.out.println("Opcion no valida");
			return false;
		}
	}

	/**
	 * Un scanner para recoger la frase
	 * 
	 * @return sentence
	 */
	public String getSentence() {
		System.out.println("Introduzca la frase que quiera utilizar: ");
		//No se cierra el scanner debido que interfiere en el scanner de la clase main
		Scanner s = new Scanner(System.in);
		return s.nextLine();

	}

	/**
	 * Metodo que abrevia una frase y pide lo pertinente para funcionar
	 * 
	 * @return String abreviado
	 */
	public String abbreviation() {
		int numCharacters;
		do {
			Scanner s = new Scanner(System.in);
			System.out.println("¿A partir de cuantos caracteres quiere abreviar?");
			numCharacters = s.nextInt();
			if (numCharacters < 4) {
				System.out.println("La cantidad de caracteres minima para abreviar son 4");
			}
		} while (numCharacters < 4);

		String sentence = getSentence();
		return StringUtils.abbreviate(sentence, numCharacters);
	}

	/**
	 * Metodo que elimina espacios en una oracion
	 * 
	 * @return String sin espacios en blanco
	 */
	public String noWhiteSpaces() {
		String sentence = getSentence();
		return StringUtils.deleteWhitespace(sentence);
	}

	/**
	 * Metodo que devuelve si una frase contiene un character o no
	 * 
	 * @return Booleano que indica si contiene o no ese String o Char
	 */
	public boolean contains() {
		Scanner s = new Scanner(System.in);
		System.out.println("¿Que caracter o frase desea buscar?");
		String subSentence = s.nextLine();
		String sentence = getSentence();
		return StringUtils.contains(sentence, subSentence);

	}

	/**
	 * Metodo que devuelve si una frase es formada por numeros o no
	 * 
	 * @return Boolean que indica si es numerico el String
	 */
	public boolean isNumeric() {
		return StringUtils.isNumeric(getSentence());
	}

}
