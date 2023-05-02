package com.nttdata.logback;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * 
 * @author David
 *
 */
public class Warrior {
	/**LOGGERS*/
	private static final Logger LOG_DNPP = (Logger) LoggerFactory.getLogger("Warrior");
	
	 //Variables
	private static Scanner s = new Scanner(System.in);
	static Random r = new Random();
	private int lifePoints = 100;
	private int dmgPoints = 0;
	private int critRate = 0;
	private int critDmg = 0;
	private String name = "";
	
	

	//Getters y setters
	/**
	 * 
	 * @return int Almacena numero de puntos de vida
	 */
	public int getLifePoints() {
		return lifePoints;
	}
	/**
	 * Método que asigna los puntos de vida
	 * @param lifePoints
	 */
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	/**
	 * 
	 * @return String Almacena el nombre del Ninja
	 */
	public String getName() {
		return name;
	}
	/**
	 * Método que asigna el nombre
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return int Almacena los puntos de daño
	 */
	public int getDmgPoints() {
		return dmgPoints;
	}
	/**
	 * Método que asigna los puntos de daño
	 * @param dmgPoints
	 */
	public void setDmgPoints(int dmgPoints) {
		this.dmgPoints = dmgPoints;
	}
	/**
	 * 
	 * @return int Almacena la posibilidad de crítico
	 */
	public int getCritRate() {
		return critRate;
	}
	/**
	 * Método que devuelve el valor de la probabilidad de crítico
	 * @param critRate
	 */
	public void setCritRate(int critRate) {
		this.critRate = critRate;
	}
	/**
	 * 
	 * @return int Almacena el valor del daño crítico
	 */
	public int getCritDmg() {
		return critDmg;
	}
	/**
	 * Método que devuelve el valor del daño crítico
	 * @param critDmg
	 */
	public void setCritDmg(int critDmg) {
		this.critDmg = critDmg;
	}

	
	/**
	 * Funcion que hace una muestra del combate, el guerrero siguiente a golpear se elije aleatoriamente
	 * @param b Warrior contricante
	 */
	public static void fight(Warrior a, Warrior b) {
		
		do {
			if (r.nextBoolean()) {
				System.out.println(a.getName()+" hit!");
				b.setLifePoints(b.getLifePoints()-a.hit());
				if(LOG_DNPP.isInfoEnabled()) {
	            	LOG_DNPP.info("Guerrero A golpeo");
	            }
				resumeLifePoints(a, b);
			} else {
				System.out.println(b.getName()+" hit!");
				a.setLifePoints(a.getLifePoints()-b.hit());
				if(LOG_DNPP.isInfoEnabled()) {
	            	LOG_DNPP.info("Guerrero B golpeo");
	            }
				resumeLifePoints(a, b);
			}
		} while (a.getLifePoints()>0&&b.getLifePoints()>0);
		if(a.getLifePoints()<=0) {
			System.out.println(a.getName()+" has lost!");
			System.out.println("Congratulations to "+b.getName()+" !");
		} else{
			System.out.println(b.getName()+" has lost!");
			System.out.println("Congratulations to "+b.getName()+" !");
		}

	}
	/**
	 * 
	 * @return INT almacena el daño hecho en ese golpe, se calcula el critico a partir de la posibilidad del warrior
	 */
	public int hit() {
        int realDmg = getDmgPoints();
        if(LOG_DNPP.isInfoEnabled()) {
        	LOG_DNPP.info("Al inicio de hit el valor de dmgPoints es "+getDmgPoints());
        }
        int randomValue = (r.nextInt(10))+1;
        
        if ( randomValue<= this.critRate) {
            realDmg = getDmgPoints()+(getDmgPoints()*getCritDmg()/10);
            if(LOG_DNPP.isInfoEnabled()) {
            	LOG_DNPP.info("Dio golpe critico");
            }
            
        }
        if(LOG_DNPP.isInfoEnabled()) {
        	LOG_DNPP.info("Al final de hit el valor de realDmg es "+realDmg);
        }
		return realDmg;
	}
	/**
	 * Método que imprime por pantalla el menu de opciones
	 * @param qualitiesPoints
	 */
	public static StringBuilder menu(int qualitiesPoints) {
		StringBuilder menu = new StringBuilder();
		menu.append("Set the qualities of the Ninja! \n");
		menu.append("1. Set Damage Point \n");
		menu.append("2. Set Crit Rate \n");
		menu.append("3. Set Crit Damamge \n");
		menu.append("Remaining skill points: "+qualitiesPoints);
		menu.append("\nChoose an option:");
		return menu;
		
	}
	/**
	 * Método que contiene la eleccion de parametros a mejorar
	 * @param parameter
	 * @param remainingPoints
	 * @return int puntos invertidos en la mejora de cierto criterio
	 */
	public int selectedParameter(int parameter, int remainingPoints) {
		
		switch (parameter) {
		case 1:
			int points = wastedPoint(remainingPoints);
			setDmgPoints(points);
			return points;
		case 2:
			points = wastedPoint(remainingPoints);
			setCritRate(points);
			return points;
		case 3: 
			points = wastedPoint(remainingPoints);
			setCritDmg(points);
			return points;
		default:
			System.out.println("Incorrect option");
			if(LOG_DNPP.isWarnEnabled()) {
            	LOG_DNPP.warn("Eleccion incorrecta en Menu");
            }
			return 0;
		}
		
	}
	/**
	 * Método que recoge los datos para saber cuantos puntos quiere invertir en un habilidad con cierto control
	 */
	public int wastedPoint(int remainingPoints) {
		System.out.println("¿How many points you wish to invest in this skill? (Max 10 per skill)");
		int usedPoints;
		do {
			usedPoints = s.nextInt();
			if(usedPoints>10||usedPoints>remainingPoints) {
				if(LOG_DNPP.isWarnEnabled()) {
					LOG_DNPP.warn("Ha intentado añadir mas puntos de lo restantes a una variable o mas de 10");
				}
				System.out.println("You are trying to add more points than the remaining ones or the maximum, try again.");
			}
		} while (usedPoints>10||usedPoints>remainingPoints);
		return usedPoints;
	}
	/**
	 * Método que imprime por pantalla, un resumen del estado de vida tras cada golpe
	 * @param a
	 * @param b
	 */
	public static void resumeLifePoints(Warrior a, Warrior b) {
		System.out.println(a.getName()+"´s Life Points: "+a.getLifePoints());
		System.out.println(b.getName()+"´s Life Points: "+b.getLifePoints());
	}
	/**
	 * Recogida de datos y asignación por cada Warrior
	 * @param x
	 */
	public static void collectionData(Warrior x) {
		HashMap<Integer,Integer> m = new HashMap<>();
		System.out.print("Choose the name of the next Ninja: ");
		x.setName(s.next());
		int distributedPoints = 12;
		
		while (distributedPoints > 0) {
			m.put(1, x.getDmgPoints());
			m.put(2, x.getCritRate());
			m.put(3, x.getCritDmg());
			System.out.println(Warrior.menu(distributedPoints));
			int selectedOption = s.nextInt();
			if(LOG_DNPP.isInfoEnabled()) {
				LOG_DNPP.info("EL VALOR DE ESTE VALOR SEGUN EL MAP ES: "+m.get(selectedOption));
			}
			if(m.get(selectedOption)!=null) {
				if (m.get(selectedOption)>=10) {
					if(LOG_DNPP.isWarnEnabled()) {
						LOG_DNPP.warn("Ha intentado añadir puntos a una variable con ya 10 puntos");
					}
					System.out.println("This skill already counts with the maximum points");
				} else {
					int lostPoints = x.selectedParameter(selectedOption, distributedPoints);
					distributedPoints -= lostPoints;
				}
			}
			
		}
	}
	
}
