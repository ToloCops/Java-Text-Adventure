package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Soldi extends Oggetto implements Tascabile{
	private static String desc = "dei soldi";
	
	/**
	 * Costruttore dei soldi con solamente il nome
	 * @param nome nome dei soldi
	 */
	public Soldi(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore dei soldi con nome e nome dell'interazione
	 * @param nome nome dei soldi
	 * @param nomeInterazione nome dell'interazione
	 */
	public Soldi(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Ritorna true se l'oggetto può essere aggiunto all'inventario
	 * @return true
	 */
	@Override
	public boolean isTascabile() { return true; }

}