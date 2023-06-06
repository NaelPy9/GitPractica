package nttdata.javat;

import java.util.InputMismatchException;
import java.util.Scanner;

import nttdata.javat.business.*;

/**
 * 
 * @author David
 *
 */
public class T3MainDnpp {
	/** Scanner destinado a recoger String */
	public static final Scanner sc = new Scanner(System.in);
	/** Scanenr destinado a recoger int */
	public static final Scanner s = new Scanner(System.in);
	/**
	 * Método principal de la clase que se consumiran el resto de metodos
	 * @param args
	 */
	public static void main(String[] args) {
		/** Booleano usado para salir del bucle que ejecuta el menu */
		boolean exit = Boolean.FALSE;
		/** int usado para almacenar la opcion escogida en el menu */
		int mainOption = 0;
		do {
			mainOption = recopileOption();
			//Switch con todas las opciones del menu
			switch (mainOption) {
			case 1:
				//Ingresar persona
				recopilateData();
				break;

			case 2:
				//Eliminar persona
				System.out.print("Introduce el DNI de la persona que desea eliminar del sistema: ");
				String key = sc.nextLine();
				ManagementServiceImpl.delete(key);
				break;

			case 3:
				//Mostrar base de datos
				ManagementServiceImpl.showAll();
				
				
				break;
			case 4:
				System.out.print("Introduzca el tipo del persona por el que desea filtrar: ");
				String typePerson = sc.nextLine();
				if (typePerson.equals("E") || typePerson.equals("S")) {
					ManagementServiceImpl.showSpecified(typePerson);
				} else {
					System.out.println("No existe ese tipo de persona");
				}
				break;
			default:
				//Se sale del programa con cualquier opcion no recogida en el menu
				System.out.println("Gracias por usar esta aplicacion!");
				exit = Boolean.TRUE;
			}
		} while (!exit);

		s.close();
		sc.close();
	}
	/**
	 * Método que imprime por pantalla el menu
	 */
	public static void options() {
		System.out.println("Introduzca la opcion que desee: ");
		System.out.println("1. Agregar persona.");
		System.out.println("2. Borrar persona.");
		System.out.println("3. Ver todas las personas en el Sistema.");
		System.out.println("4. Ver las personas filtradas por tipo.");
		System.out.println("Salir (cualquier otra numero)");
		System.out.print("Opcion: ");
	}
	/**
	 * Verifica el scanner y controla la excepcion de InputMisMatch
	 * @return int con la opcion elegida 
	 */
	public static int recopileOption() {
		boolean numValid;
		int mainOption = 0;
		do {
			mainOption = 0;
			//Si introduce algo distinto a un numero, salta excepcion, se controla y muestra un mensaje de error
			try {
				options();
				mainOption = s.nextInt();
				numValid = Boolean.TRUE;
			} catch (InputMismatchException e) {
				System.err.println("Error: Debes ingresar un número.");
				numValid = Boolean.FALSE;
				refreshScanner(s);
			}
		} while (!numValid);
		return mainOption;
	}
	/**
	 * Recoje toda la imformación necesaria para ingresara a una persona
	 * en el sistema
	 */
	public static void recopilateData() {
		//Variables donde se almacenaran los datos de las personas
		String typePerson = "";
		String name = "";
		String dni = "";
		String added1 = "";
		String added2 = "";
		boolean exit2;

		do {
			//Si introduce erroneanmente los datos y salta la excepcion se muestra un mensaje de error
			try {
				typePerson = validateScanner(sc);
				name = validateAllString("Nombre de la persona: ", sc);
				dni = dniField();
				
				//Depende del tipo de persona que es, le pregunta por unos u otros datos
				if (typePerson.equals("E")) {
					added1 = validateAllString("Nombre de la categoria a la que pertenece: ", sc);
					added2 = validateAllString("Nombre del proyecto en el que trabaja: ", sc);

				} else {
					added1 = validateAllString("Nombre del centro al que pertenece: ", sc);
					//Comprueba que solo pueda introducir modalidades existentes, DAW O DAM
					added2 = modalityField();

				}
				exit2 = true;
			} catch (OptionInvalidateException e) {
				System.out.println("Error: " + e.getMessage());
				exit2 = false;

			}
		} while (!exit2);
		
		/**
		 * Una vez comprobado, llama la función que almacena las personas en el HashMap
		 */
		ManagementServiceImpl.register(typePerson, name, dni, added1, added2);
	}
	/**
	 * Metodo que comprueba que el tipo de persona que se introduzca es
	 * E o S, sino tira un excepcion personalizada
	 * @param scanner
	 * @return String con S o E para determinar el tipo de persona que es
	 * @throws OptionInvalidateException
	 */
	public static String validateScanner(Scanner scanner) throws OptionInvalidateException {
		System.out.print("¿Que tipo de persona quiere agregar al sistema?(E/S): ");
		String option = scanner.nextLine();

		if (!option.equals("E") && !option.equals("S")) {
			throw new OptionInvalidateException("La opción ingresada es inválida.");
		}

		return option;
	}
	/**
	 * Metodo para limpiar o reiniciar el buffer de un scanner
	 * @param scanner
	 */
	public static void refreshScanner(Scanner scanner) {
		scanner.nextLine();
	}
	/**
	 * En el caso que se introduzca irroneamente un dato y salte una excepcion, se controla y salta mensaje de error
	 * @param messeage
	 * @param scanner
	 * @return
	 */
	public static String validateAllString(String messeage, Scanner scanner) {
		boolean exit;
		String string = "";
		do {
			try {
				System.out.print(messeage);
				string = scanner.nextLine();
				exit = Boolean.TRUE;
			} catch (InputMismatchException e) {
				System.err.println("Error: Debes ingresar una cadena de caracteres");
				exit = Boolean.FALSE;
				refreshScanner(scanner);
			}
		} while (!exit);
		return string;
	}
	/**
	 * Comprueba si la modalidad introducida es DAM o DAW
	 * @param initialModality
	 * @return
	 */
	public static boolean validateModality(String initialModality) {
		return Modality.all().contains(initialModality);

	}
	/**
	 * Comprueba que el DNI introducido no tenga mas de 9 caracteres
	 * @param dni
	 * @return Boolean
	 */
	public static boolean validateID(String dni) {
		if (dni.length()!=9) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	/**
	 * En el caso que introduzca un modalidad que no exista
	 * este muestra un mensaje de error y vuelve a pedirlo
	 * @return String modalidad ya comprobada
	 */
	public static String modalityField() {
		String added2 = "";
		do {
			added2 = validateAllString("Nombre de la modalidad a la que pertenece (DAW O DAM): ", sc);
			if (!validateModality(added2)) {
				System.out.println("Ha introducido una modalidad inexistente en el sistema");
			}
		} while (!validateModality(added2));
		return added2;
	}
	/**
	 * En el caso que introduzca un dni con mas longitud de la apropiada
	 * este muestra un mensaje de error y vuelve a pedirlo
	 * @return
	 */
	public static String dniField() {
		String dni = "";
		do {
			dni = validateAllString("DNI de la persona: ", sc);
			if (!validateID(dni)) {
				System.out.println("EL DNI debe tener una longitud de 9 dígitos");
			}
		} while (!validateID(dni));
		return dni;
	}
}
