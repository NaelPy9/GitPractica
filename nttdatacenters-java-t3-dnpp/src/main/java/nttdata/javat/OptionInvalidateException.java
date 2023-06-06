package nttdata.javat;
/**
 * 
 * @author David
 *
 */
public class OptionInvalidateException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Excepcion personalizada
	 * @param mensaje
	 */
	public OptionInvalidateException(String mensaje) {
        super(mensaje);
    }
}