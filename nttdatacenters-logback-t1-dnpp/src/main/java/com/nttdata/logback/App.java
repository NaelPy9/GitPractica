package com.nttdata.logback;

import java.util.Scanner;


/**
 * 
 * @author David
 *
 */
public class App {
	static Scanner s = new Scanner(System.in);
	/**
	 * Metodo principal de la clase
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Warrior a = new Warrior();
		Warrior b = new Warrior();
		
		System.out.println("Welcome to Ninja Fight!");
		
		Warrior.collectionData(a);	
		Warrior.collectionData(b);
		
		Warrior.fight(a, b);
		
		Warrior.overload();

		s.close();

	}
}
