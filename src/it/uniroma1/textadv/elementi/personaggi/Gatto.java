package it.uniroma1.textadv.elementi.personaggi;

import java.util.HashSet;

import it.uniroma1.textadv.elementi.Animale;
import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.elementi.Personaggio;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Gatto extends Animale implements Tascabile {
	private static String verso = "Miao!";
	
	/**
	 * Costruttore del gatto
	 * @param nome nome del gatto
	 * @param interazioni insieme di interazioni del gatto
	 */
	public Gatto(String nome, HashSet<Elemento> interazioni) { super(nome, interazioni, verso); }
	
	/**
	 * Ritorna true se l'oggetto può essere aggiunto all'inventario
	 * @return true
	 */
	@Override
	public boolean isTascabile() { return true; }

}