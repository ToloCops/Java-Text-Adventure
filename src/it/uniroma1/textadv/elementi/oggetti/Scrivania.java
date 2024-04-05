package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Apribile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Scrivania extends Oggetto implements Apribile{
	private static String desc = "una scrivania";
	private Boolean aperta = false;
	
	/**
	 * Costruttore della scrivania con solamente il nome
	 * @param nome nome della scrivania
	 */
	public Scrivania(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore della scrivania con nome e nome dell'interazione
	 * @param nome nome della scrivania
	 * @param nomeInterazione nome dell'interazione
	 */
	public Scrivania(String nome, String interaction) { super(nome, desc, interaction); }
	
	/**
	 * Ritorna lo stato dell'oggetto, specificando se la scrivania è aperta o chiusa e cosa contiene all'interno
	 * @return lo stato dell'oggetto
	 */
	@Override
	public String getStato() 
	{
		String stato = "è chiusa.";
		if (aperta) 
		{
			if (getInterazione() != null) stato = "è aperta. Contiene: " + getInterazione() + "."; 
			else stato = "è aperta. Non contiene nulla.";
		}
		return stato;
	}

	/**
	 * Apre la scrivania e ritorna cosa ha al proprio interno
	 * @return cosa ha al proprio interno
	 */
	@Override
	public Elemento apriti() {
		aperta = true;
		System.out.println("La scrivania si è aperta");
		setInterazioneVisibile(true);
		Elemento interazione = getInterazione();
		if (interazione != null)
		{ 
			interazione.setVisibileInStanza(false);
			return interazione;
		}
		return interazione;
	}

}
