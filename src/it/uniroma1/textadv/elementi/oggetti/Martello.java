package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Distruttibile;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Martello extends Oggetto implements Tascabile{
	private static String desc = "un martello";
	
	/**
	 * Costruttore del martello con solamente il nome
	 * @param nome nome del martello
	 */
	public Martello(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del martello con nome e nome dell'interazione
	 * @param nome nome del martello
	 * @param nomeInterazione nome dell'interazione
	 */
	public Martello(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Prova a rompere un elemento
	 * @param daRompere elemento da rompere
	 * @return un elemento all'interno dell'elemento rotto, se presente, null altrimenti.
	 */
	@Override
	public Elemento azione(Elemento daRompere) 
	{
		Elemento response = null;
		if (daRompere instanceof Distruttibile) response = daRompere.rompiti(this);
		else { System.out.println("Mi dispiace, ma non posso compiere questo tipo di azione"); }
		return response;
	}
	
	/**
	 * Ritorna true se l'oggetto può essere aggiunto all'inventario
	 * @return true
	 */
	@Override
	public boolean isTascabile() { return true; }

}
