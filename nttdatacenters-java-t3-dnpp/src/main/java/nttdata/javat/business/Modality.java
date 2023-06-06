package nttdata.javat.business;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que tiene las modalidades disponibles, en este caso DAM y DAW
 * @author David
 *
 */
public class Modality {
	private Modality() {}
	/**
	 * 
	 * @return ArrayList con las modalidades
	 */
	public static List<String> all(){
		//ArrayList que contiene las modalidades
		List<String> modalities = new ArrayList<>();
		modalities.add("DAW");
		modalities.add("DAM");
		return modalities;
	}
	
}
