package it.uniroma1.textadv.elementi.personaggi;

import java.util.HashSet;

import it.uniroma1.textadv.elementi.Animale;
import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;

/**
 * 
 * @author lucatolomei
 *
 */
public class Cane extends Animale {
	private static String verso = "Bau!";
	
	/**
	 * Costruttore del cane
	 * @param nome nome del cane
	 * @param interazioni insieme di interazioni del cane
	 */
	public Cane(String nome, HashSet<Elemento> interazioni) { super(nome, interazioni, verso); }

}
