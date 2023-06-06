package nttdata.javat.business;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que implementa la interfaz ManagmentServiceI
 * @author David
 *
 */
public class ManagementServiceImpl implements ManagementServiceI {
	/**
	 * Constructor privado
	 */
	private ManagementServiceImpl() {
		
	}
	/**
	 * HashMap donde se guardaran todos las Personas
	 */
	static HashMap<String, Person> peopleSystem = new HashMap<>();
	/**
	 * HashMap donde se guardaran todos los Estudiantes
	 */
	static HashMap<String, Student> studentSystem = new HashMap<>();
	/**
	 * HashMap donde se guardaran todos los Empleados
	 */
	static HashMap<String, Employee> employeeSystem = new HashMap<>();
	//Getters y Setters
	public static Map<String, Person> getPeopleSystem() {
		return peopleSystem;
	}
	
	public static void setPeopleSystem(Map<String, Person> peopleSystem) {
		ManagementServiceImpl.peopleSystem = (HashMap<String, Person>) peopleSystem;
	}
	
	public static Map<String, Student> getStudentSystem() {
		return studentSystem;
	}

	public static void setStudentSystem(Map<String, Student> studentSystem) {
		ManagementServiceImpl.studentSystem = (HashMap<String, Student>) studentSystem;
	}

	public static Map<String, Employee> getEmployeeSystem() {
		return employeeSystem;
	}

	public static void setEmployeeSystem(Map<String, Employee> employeeSystem) {
		ManagementServiceImpl.employeeSystem = (HashMap<String, Employee>) employeeSystem;
	}
	/**
	 * Implementacion del metodo register, recibe los datos y según el tipo de persona
	 * llama a un constructor u otro.
	 * @param typePerson
	 * @param name
	 * @param dni
	 * @param add1
	 * @param add2
	 */
	public static void register(String typePerson, String name, String dni, String add1, String add2) {
		if (peopleSystem.containsKey(dni)) {
			System.err.println("Ese DNI ya se encuentra en el sistema");
		} else if (typePerson.equals("E")) {
			peopleSystem.put(dni, new Employee(typePerson, name, dni, add1, add2));
			employeeSystem.put(dni, new Employee(typePerson, name, dni, add1, add2));
		} else if (typePerson.equals("S")) {
			peopleSystem.put(dni, new Student(typePerson, name, dni, add1, add2));
			studentSystem.put(dni, new Student(typePerson, name, dni, add1, add2));
		}
		
	}
	/**
	 * Implementacion del metodo delete, pide por parametro una key
	 * del hashmap (DNI), si este key existe en el hashmap, lo elimina
	 * si no muestra un mensaje de error
	 * @param key
	 */
	public static void delete(String key) {
		if (ManagementServiceImpl.getPeopleSystem().containsKey(key)) {
			ManagementServiceImpl.getPeopleSystem().remove(key);
			System.out.println("Persona eliminada con exito");
		} else {
			System.err.println("Ese DNI no está registrado en el sistema");
		}
	}
	/**
	 * Implementacion del metodo showAll, usa el metodo entrySet
	 * de HashMap para mostrar los datos almacenados
	 */
	public static void showAll() {
		peopleSystem.forEach((k,v) -> System.out.println(v));
		
	}
	/**
	 * Implementacion del método showSpecified, muestra por tablas
	 * las personas almacenadas distinguiendolas por tipo de persona.
	 * @param typePerson
	 */
	public static void showSpecified(String typePerson) {
		if (peopleSystem.isEmpty()) {
			System.out.println("El sistema aun no tiene ninguna persona registrada");
		}else if (typePerson.equals("S")) {
			
			System.out.println(Constants.TOP_STUDENT);
		    System.out.println("│    DNI    │   NOMBRE   │    CENTRO    │  MODALIDAD   │");
		    System.out.println(Constants.MID_STUDENT);
		    for (Map.Entry<String, Student> entry : studentSystem.entrySet()) {
	            Student person = entry.getValue();
	            System.out.printf("│ %-9s │ %-10.10s │ %-12.12s │ %-13.13s│%n",person.getDni(), person.getName(), person.getCenter(),person.getModality());

	        }
	        System.out.println(Constants.BOTTOM_STUDENT);
		    
		}else {
			System.out.println(Constants.TOP_EMPLOYEE);
		    System.out.println("│    DNI    │   NOMBRE   │    CATEGORIA    │  PROYECTO   │");
		    System.out.println(Constants.MID_EMPLOYEE);
		    for (Map.Entry<String, Employee> entry : employeeSystem.entrySet()) {
	            Employee person = entry.getValue();
	            System.out.printf("│ %-9s │ %-10.10s │ %-15.15s │ %-12.12s│%n",person.getDni(), person.getName(), person.getCategory(),person.getProject());

	        }
	        System.out.println(Constants.BOTTOM_EMPLOYEE);
		}
		
	}

}
