package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Apribile;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Tronchesi extends Oggetto implements Tascabile{
	private static String desc = "delle tronchesi";
	
	/**
	 * Costruttore delle tronchesi con solamente il nome
	 * @param nome nome delle tronchesi
	 */
	public Tronchesi(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore delle tronchesi con nome e nome dell'interazione
	 * @param nome nome delle tronchesi
	 * @param nomeInterazione nome dell'interazione
	 */
	public Tronchesi(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
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
	 * @return false
	 */
	@Override
	public boolean isTascabile() 
	{
		System.out.println("Devi pagarlo prima.");
		return false;
	}

}
