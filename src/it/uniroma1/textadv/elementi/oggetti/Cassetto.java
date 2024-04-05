package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Apribile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Cassetto extends Oggetto implements Apribile {
	private static String desc = "un cassetto";
	private Boolean aperta = false;
	
	/**
	 * Costruttore del cassetto con solo il nome
	 * @param nome nome del cassetto
	 */
	public Cassetto(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del cassetto con il nome e il nome dell'interazione
	 * @param nome nome del cassetto
	 * @param nomeInterazione nome dell'interazione
	 */
	public Cassetto(String nome, String nomeInterazione) 
	{
		super(nome, desc, nomeInterazione);
	}
	
	/**
	 * Ritorna lo stato dell'oggetto, specificando se il cassetto è aperto o chiuso e cosa contiene all'interno
	 * @return lo stato dell'oggetto
	 */
	@Override
	public String getStato() 
	{
		String stato = "è chiuso.";
		if (aperta) 
		{
			if (getInterazione() != null) stato = "è aperto. Contiene: " + getInterazione() + "."; 
			else stato = "è aperto. Non contiene nulla.";
		}
		return stato;
	}
	
	/**
	 * Apre il cassetto e ritorna cosa ha al proprio interno
	 * @return cosa ha al proprio interno
	 */
	@Override
	public Elemento apriti() {
		aperta = true;
		System.out.println("Il cassetto si è aperto");
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
