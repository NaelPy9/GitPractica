package nttdata.javat.business;
/**
 * Clase Student que extiende de la clase abstracta Person, añade dos atributos,
 * con sus getters y setters e implementa showDetails de person.
 * @author David
 *
 */
public class Student extends Person {
	//Atributos especiales de la clase Student
	private String center;
	private String modality;

	//Getters y Setters
	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	//Constructor
	public Student(String typePerson, String name, String dni, String center, String modality) {
		super(typePerson, name, dni);
		this.center = center;
		this.modality = modality;
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
		details.append("Student");
		details.append(Constants.COLUMNS_SPACE);
		details.append(getName());
		details.append(Constants.COLUMNS_SPACE);
		details.append(getDni());
		details.append(Constants.COLUMNS_SPACE);
		details.append(getCenter());
		details.append(Constants.COLUMNS_SPACE);
		details.append(getModality());
		details.append("\n");
		return details;
	}

}
