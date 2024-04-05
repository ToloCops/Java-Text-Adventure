package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Distruttibile;
import it.uniroma1.textadv.interfacce.Riempibile;
import it.uniroma1.textadv.interfacce.Tascabile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Secchio extends Oggetto implements Tascabile{
	private boolean pieno = true;
	private static String desc = "un secchio pieno d'acqua";
	
	/**
	 * Costruttore del secchio con solamente il nome
	 * @param nome nome del secchio
	 */
	public Secchio(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del secchio con nome e nome dell'interazione
	 * @param nome nome del secchio
	 * @param nomeInterazione nome dell'interazione
	 */
	public Secchio(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Prova a riempire un elemento
	 * @param daRiempire elemento da riempire
	 * @return null
	 */
	@Override
	public Elemento azione(Elemento daRiempire) 
	{
		if (!pieno) return null;
		Elemento response = null;
		if (daRiempire instanceof Riempibile)
		{
			daRiempire.riempiti(this);
			setDescrizione("un secchio");
		}
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