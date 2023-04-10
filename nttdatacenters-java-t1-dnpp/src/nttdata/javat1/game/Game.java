package nttdata.javat1.game;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author David
 *
 */
public class Game {
	/**
	 * Metodo principal de la clase Game, este contiene el lanzamiento del juego
	 */
	public void launchAndStart() {
		
		/**
		 * Variables
		 */
		Scanner s = new Scanner(System.in);
		int width = 15;
		int length = 15;
		boolean [][] objects = mapChoice();
		
		for(int k = 0; k<1; k++) {
			Ball p = new Ball();
		
			/**
			 * Booleanos que nos permite y ayuda a finalizar el do-while
			 */
			boolean gameWin = false;
			boolean gameOver = false;
			//Ayuda a actualizar la posicion de la pelota
			int counter=0;
			/**
			 * Ejecuta el mapa del juego junto a la pelota simulada en su interior
			 */
			do {
				p.setHigh(counter+1);
				counter +=1;
				for(int i = 0; i<length; i++) {
					/**
					 * Según en que iteración del array, tomara distintas opciones
					 */
					switch (i) { 
						case 0:
							System.out.println("┌─────────────┐");
							break;
						case 12: 
							gameOver = dashesLines(width, i, gameOver, objects, p);
							break;
						case 13: 
							gameWin = lastLine(width, i, gameWin, objects, p);
							break;
							
						case 14:
							System.out.println("└─────────────┘");
							break;
						default:
							commonLines(objects, p, i);
							break;
					}
				}
				/**
				 * Interrumpe el while durante 750 milisegundos
				 */
				slowDown();
				//
			} while (!gameWin && !gameOver);
			/**
			 * En el caso que la pelota toque choque contra los guiones
			 * será "propulsada" para arriba y el juego se reiniciara (se devuelve k a 0)
			 */
			if(gameOver) {
				k--;
			}
			/**
			 * Se escribe por pantalla que la partida ha acabado, se termina el for
			 * y muestra por pantalla los puntos conseguidos
			 */
			if(gameWin) {
				System.out.println("Game Over!");
			}
		
		}
		
		System.out.println("Ha conseguido "+Counter.totalPoints()+" puntos!! Enhorabuena");
		s.close();
	}
	/**
	 * Comprueba si la posicion de enfrente a la pelota hay algun obstaculo (true)
	 * en ese caso, de manera aleatoria tomara el camino de la izquierda o de la derecha
	 * salvo que se encuentre al lado de una pared, que tomara el camino libre
	 * @param objetos
	 * @param p
	 */
	public void collisions(boolean[][] objetos, Ball p) {
		Random r = new Random();
		int currentLength = p.getHigh();
		int currentWidth = p.getWidth();
		int direction = r.nextInt(2);
		if(objetos[currentLength][currentWidth]) {
			
			Counter.addPoints(10);
			if(currentWidth== 0 ) {
				p.setWidth(p.getWidth()+1);
			} else if(currentWidth ==12) {
				p.setWidth(p.getWidth()-1);
			}else if(direction == 0) {
				p.setWidth(p.getWidth()+1);
			}else if(direction == 1) {
				p.setWidth(p.getWidth()-1);
			}
			
		}
	}
	/**
	 * Dibuja las lineas comunes del mapa, las que no estan predefinidos
	 * y las que no actualizan valores de variables
	 * @param objetos
	 * @param p
	 * @param i
	 */
	public void commonLines(boolean[][] objetcs, Ball p, int i) {
		int width = 15;
		StringBuilder line11 = new StringBuilder();
		line11.append("│");
		for(int j = 0; j<width-1; j++) {
			if(p.coordinates(i,j)) {
				line11.append("◍");
			} else if(j==width-2){
				line11.append("│");
			} else if(objetcs[i][j]){
				line11.append("*");
				
			}else{
				line11.append(" ");
			}
		}
		collisions(objetcs, p);
		System.out.println(line11.toString());
	}
	/**
	 * Rellena el array con un patron básico
	 * @param length
	 * @param width
	 * @param objects
	 */
	public void standardMap(int length, int width, boolean[][] objects) {
		boolean value = true;
		for(int i = 0; i < length-3; i++) {
		    for(int j = 0; j < width; j++) {
		        objects[i][j] = value;
		        value = !value;
		    }
		    if(width % 2 == 0) {
		        value = !value;
		    }
		}
	}
	/**
	 * Rellena el array aleatoriamente siguiendo los siguientes criterios:
	 * 	Cantidad de maximos true (obstaculo) por fila
	 * 	Si habia un onstaculo al lado
	 * @param objects
	 * @param maxTruePorFila
	 */
	public static void automaticRefill(boolean[][] objects, int maxTruePerRow) {
	    Random random = new Random();
	    int rows = objects.length;
	    int columns = objects[0].length;
	    
	    for (int i = 0; i < rows-3; i++) {
	        int counterTrue = 0;
	        boolean lastValue = false;
	        for (int j = 0; j < columns; j++) {
	            // Comprobar si se ha alcanzado el máximo de true por fila
	            if (counterTrue >= maxTruePerRow) {
	                objects[i][j] = false;
	            } else {
	                // Comprobar si el valor anterior era true
	                if (lastValue) {
	                    objects[i][j] = false;
	                } else {
	                    objects[i][j] = random.nextBoolean();
	                    if (objects[i][j]) {
	                        counterTrue++;
	                    }
	                }
	                lastValue = objects[i][j];
	            }
	        }
	    }
	}
	/**
	 * Nos ayuda a elegir el tipo de mapa que queremos para el juego y devuelve
	 * el array con la distribucion de los obstaculos que estaran en el mapa
	 * @return objetos
	 */
	public boolean[][] mapChoice(){
		int width = 15;
		int length = 15;
		boolean[][] objects = new boolean[length][width];
		Scanner s = new Scanner(System.in);
		int chosenOption = 0;
		
		do {
			
			System.out.println("¿Como quiere que sea el tablero?");
			System.out.println("1. Estandar");
			System.out.println("2. Aleatorio");
			System.out.print("Opcion: ");
			chosenOption = s.nextInt();
			
			if (chosenOption == 1) {
				standardMap(length, width, objects);
				
			} else if(chosenOption == 2) {
				int maxTruePerRow = 7;
				automaticRefill(objects, maxTruePerRow);
			} else {
				System.out.println("Opcion no valida, vuelva a intentarlo");
			}
			
		} while (chosenOption!=1 && chosenOption!=2);
		s.close();
		return objects;
	}
	/**
	 * Dibuja la ultima linea que puede dibujar la pelota en el case que caiga
	 * en el agujero y actualiza el valor de la variable gameWin
	 * @param width
	 * @param i
	 * @param gameWin
	 * @param objetcs
	 * @param p
	 * @return gameWin
	 */
	public boolean lastLine(int width, int i, boolean gameWin, boolean[][] objetcs, Ball p) {
		StringBuilder line13 = new StringBuilder();
		line13.append("│");
		for(int j = 0; j<width-1; j++) {
			if(p.coordinates(i,j)) {
				line13.append("◍");
				gameWin = true;
				
			} else if(j==width-2){
				line13.append("│");
			} else if(objetcs[i][j]){
				line13.append("*");
				
			}else{
				line13.append(" ");
			}
		}
		
		System.out.println(line13.toString());
		return gameWin;
	}
	/**
	 * Dibuja la linea que determina si ganas o pierdes y actualiza el valor 
	 * de la variable encargada de la continuacion del bucle
	 * @param width
	 * @param i
	 * @param gameOver
	 * @param objects
	 * @param p
	 * @return gameOver
	 */
	public boolean dashesLines(int width, int i, boolean gameOver, boolean[][] objects, Ball p) {
		StringBuilder line12 = new StringBuilder();
		line12.append("├");
		for(int j = 0; j<width-1; j++) {
			if(p.coordinates(i,j)) {
				line12.append("o");
				if(j!=5 && j!=6 && j!=7) {
					gameOver = true;
				}
			} else if(j==width-2){
				line12.append("┤");
			} else if(j==5 || j==6 || j==7){
				line12.append(" ");
				
			}else{
				line12.append("─");
			}
		}
		collisions(objects, p);
		System.out.println(line12.toString());
		return gameOver;
	}
	/**
	 * Funcion que retrasa los bucles
	 */
	public void slowDown() {
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}