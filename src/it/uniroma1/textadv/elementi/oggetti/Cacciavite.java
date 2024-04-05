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
public class Cacciavite extends Oggetto implements Tascabile{
	private static String desc = "un cacciavite";
	
	/**
	 * Costruttore del cacciavite con solo il nome
	 * @param nome nome del cacciavite
	 */
	public Cacciavite(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del cacciavite con il nome e il nome dell'interazione
	 * @param nome nome del cacciavite
	 * @param nomeInterazione nome dell'interazione
	 */
	public Cacciavite(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Ritorna true se l'oggetto può essere aggiunto all'inventario
	 * @return true
	 */
	@Override
	public boolean isTascabile() { return true; }
	
	/**
	 * Prova a svitare un elemento
	 * @param daSvitare elemento da svitare
	 * @return null
	 */
	@Override
	public Elemento azione(Elemento daSvitare) 
	{
		Elemento response = null;
		if (daSvitare instanceof Vite) daSvitare.svitati(this);
		else { System.out.println("Mi dispiace, ma non posso compiere questo tipo di azione"); }
		return response;
	}

}
