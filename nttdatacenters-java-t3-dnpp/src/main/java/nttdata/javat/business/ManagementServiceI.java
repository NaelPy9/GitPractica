package nttdata.javat.business;

import java.util.Scanner;
/**
 * Interfaz que contiene los métodos que se deben implementar: registed, delete y showAll
 * @author David
 *
 */
public interface ManagementServiceI {
	/**
	 * Metodo para registar personas
	 * @param typePerson
	 * @param name
	 * @param DNI
	 * @param add1
	 * @param add2
	 */
	public static void register(String typePerson, String name, String dni, String add1, String add2) {
	}
	/**
	 * Método para borrar personas
	 * @param s
	 */
	public static void delete(Scanner s) {
	}
	/**
	 * Metodo para mostrar por pantalla las personas registradas
	 */
	public static void showAll() {
	}
	/**
	 * Método que muestra por tablas las personas
	 * distinguiendo si son Employee o Student
	 */
	public static void showSpecified() {
		
	}

}
