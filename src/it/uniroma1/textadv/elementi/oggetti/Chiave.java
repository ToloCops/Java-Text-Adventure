package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Apribile;
import it.uniroma1.textadv.interfacce.Distruttibile;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Chiave extends Oggetto implements Tascabile{
	private static String desc = "una chiave";
	
	/**
	 * Costruttore della chiave con solamente il nome
	 * @param nome nome della chiave
	 */
	public Chiave(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore della chiave con nome e nome dell'interazione
	 * @param nome nome della chiave
	 * @param nomeInterazione nome dell'interazione
	 */
	public Chiave(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Prova ad aprire un elemento
	 * @param daAprire elemento da aprire
	 * @return un elemento all'interno dell'elemento aperto, se presente, null altrimenti.
	 */
	@Override
	public Elemento azione(Elemento daAprire) 
	{
		Elemento response = null;
		if (daAprire instanceof Apribile) response = daAprire.apriti(this);
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
