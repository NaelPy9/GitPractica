package nttdata.javat.game;
/**
 * 
 * @author David
 *
 */
public class Ball {
	/**
	 * Variables
	 */
	private int high = 0;
	private int width = 6;
	/**
	 * Getter high
	 * @return high
	 */
	public int getHigh() {
		return high;
	}
	/**
	 * Setter high
	 * @param high
	 */
	public void setHigh(int high) {
		this.high = high;
	}
	/**
	 * Getter width
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Setter width
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Si concuerda la interacion tanto del bucle general (i) y del bucle del momento(j)
	 * con la posicion de la pelota, devuelve true, sino false;
	 * @param gameHigh
	 * @param gameWidth
	 * @return match
	 */
	public boolean coordinates(int gameHigh, int gameWidth) {
		boolean match = true;
		if(gameHigh == getHigh() && gameWidth == getWidth()) {
			match = true;
		} else {
			match = false;
		}
		return match;
	}
}
