package com.nttdata.mvn;

import org.apache.maven.shared.utils.StringUtils;

/**
 * 
 * @author David
 *
 */
public class App 
{
	/**
	 * Metodo principal del programa donde se pone a prueba
	 * distintos metodos de la librería Commons Lang
	 * @param args
	 */
    public static void main( String[] args )
    {
    	/**
    	 * El metodo capitalise de la libreria Commons lang importada devuelve el String
    	 * pasado por parametro pero con la primera letra en mayuscula
    	 */
        String str = "hola me llamo David aunque me puedes llamar Nael";
        System.out.println(StringUtils.capitalise(str));
        
        /**
         * El metodo deleteWhitespace retira todos los espacios en blanco de un String
         */
        String noWhiteSpaces = (StringUtils.deleteWhitespace(str));
        System.out.println(noWhiteSpaces);        
        
        /**
         * El metodo abbreviate reduce la cantidad de caracteres en un String a la cantidad
         * del entero pasado por parametro menos 3 caracteres que seran los 3 puntos suspensivos
         */
        String abbreviation = StringUtils.abbreviate(str, 22);
        System.out.println(abbreviation);
        
    }
}
