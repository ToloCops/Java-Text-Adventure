package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Tesoro extends Oggetto implements Tascabile{
	private static String desc = "il tesoro";
	
	/**
	 * Costruttore del tesoro con solamente il nome
	 * @param nome nome del tesoro
	 */
	public Tesoro(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del tesoro con nome e nome dell'interazione
	 * @param nome nome del tesoro
	 * @param nomeInterazione nome dell'interazione
	 */
	public Tesoro(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Ritorna true se l'oggetto può essere aggiunto all'inventario
	 * @return true
	 */
	@Override
	public boolean isTascabile() { return true; }

}
