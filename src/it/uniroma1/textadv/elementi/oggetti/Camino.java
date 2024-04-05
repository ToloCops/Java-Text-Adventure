package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Riempibile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Camino extends Oggetto implements Riempibile{
	private boolean acceso = true;
	private static String desc = "un camino acceso";
	
	/**
	 * Costruttore del camino con solo il nome
	 * @param nome nome del camino
	 */
	public Camino(String nome) { super(nome, desc); setInterazioneVisibile(true); }
	
	/**
	 * Costruttore del camino con il nome e il nome dell'interazione
	 * @param nome nome del camino
	 * @param nomeInterazione nome dell'interazione
	 */
	public Camino(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); setInterazioneVisibile(true); }

	/**
	 * Ritorna true se l'interazione si trova all'interno della stanza
	 * @return true
	 */
	@Override
	public boolean isInterazioneInStanza() { return true; }
	
	/**
	 * Ritorna true se il camino è acceso
	 * @return acceso
	 */
	@Override
	public boolean isAcceso() { return acceso; }
	
	/**
	 * Prova a "riempire"(spegnere) il camino con uno strumento
	 * @param conCosa con cosa spegnere il camino
	 */
	public void riempiti(Elemento conCosa)
	{
		if (conCosa instanceof Secchio)
		{
			System.out.println("Il camino si è spento");
			acceso = false;
			setDescrizione("un camino spento");
		}
	}
	
}
