package nttdata.javat.business;
/**
 * Clase abstracta Person con los atributos de nombre, dni y tipo de persona
 * de la que extenderean Employee y Student
 * @author David
 *
 */
public abstract class Person {
	//Atributos de la clase
	private String name;
	private String dni;
	private String typePerson;
	//Getters y Setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//Constructor
	protected Person(String typePerson, String name, String dni) {
		this.typePerson = typePerson;
		this.name = name;
		this.dni = dni;
	}
	/**
	 * Metodo que devuelve en detalle todos los atributos
	 * @return StringBuilder
	 */
	public StringBuilder showDetails() {
		StringBuilder details = new StringBuilder();
		details.append("Nombre: ");
		details.append(getName());
		details.append(", DNI: ");
		details.append(getDni());

		return details;
	}

}
