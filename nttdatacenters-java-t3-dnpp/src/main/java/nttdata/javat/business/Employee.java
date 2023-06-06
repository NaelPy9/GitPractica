package nttdata.javat.business;
/**
 * Clase Employee que extiende de la clase abstracta Person, añade dos atributos,
 * con sus getters y setters e implementa showDetails de person.
 * @author David
 *
 */
public class Employee extends Person {
	//Atributos especiales de la clase Employee
	private String category;
	private String project;
	
	//Getters y Setters
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	//Constructor
	public Employee(String typePerson, String name, String dni, String category, String project) {
		super(typePerson, name, dni);
		this.category = category;
		this.project = project;

	}
	/**
	 * To String que llama al metodo showDetails
	 */
	public String toString() {
		StringBuilder details = new StringBuilder();
		details.append(showDetails());

		return details.toString();
	}
	/**
	 * Metodo que sobreescribe al metodo showDetails de Person añadiendole
	 * los atributos especificos
	 */
	@Override
	public StringBuilder showDetails() {
		StringBuilder details = new StringBuilder();
		details.append("Employee");
		details.append(Constants.COLUMNS_SPACE);
		details.append(getName());
		details.append(Constants.COLUMNS_SPACE);
		details.append(getDni());
		details.append(Constants.COLUMNS_SPACE);
		details.append(getCategory());
		details.append(Constants.COLUMNS_SPACE);
		details.append(getProject());
		details.append("\n");
		return details;
	}
	

}
