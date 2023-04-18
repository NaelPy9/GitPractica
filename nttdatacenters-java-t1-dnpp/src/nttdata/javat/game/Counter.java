package nttdata.javat.game;

/**
 * 
 * @author David
 *
 */
public class Counter {

	private static int points;

	/**
	 * Suma la cantidad de puntos pasada por parametro
	 * 
	 * @param cantidadPuntos
	 */
	public static void addPoints(int numberPoints) {
		points += numberPoints;
	}

	/**
	 * Devuelve la cantidad total de puntos
	 * 
	 * @return puntos
	 */
	public static int totalPoints() {

		return points;
	}
}
