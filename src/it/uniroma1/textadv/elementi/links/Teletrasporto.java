package it.uniroma1.textadv.elementi.links;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Link;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.elementi.oggetti.Cacciavite;
import it.uniroma1.textadv.elementi.oggetti.Chiave;
import it.uniroma1.textadv.interfacce.Apribile;

public class Teletrasporto extends Link implements Apribile{
	private static String desc = "un teletrasporto, è chiuso a chiave";
	private boolean aperto = false;

	/**
	 * Costruttore del teletrasporto
	 * @param nome nome del teletrasporto
	 * @param stanza1 nome della stanza di partenza/arrivo
	 * @param stanza2 nome della stanza di partenza/arrivo
	 */
	public Teletrasporto(String nome, String stanza1, String stanza2) {
		super(nome, desc, stanza1, stanza2);
	}
	
	/**
	 * Ritorna true se il giocatore può passare
	 * @return aperto
	 */
	@Override
	public Boolean puoPassare() 
	{
		if (aperto) return aperto;
		else
			System.out.println("Non posso usare il teletrasporto, è chiuso a chiave.");
		return aperto;
	}
	
	/**
	 * Prova ad aprire il teletrasporto
	 * @return null 
	 */
	@Override
	public Elemento apriti()
	{
		System.out.println("Il teletrasporto non si apre.");
		return null;
	}
	
	/**
	 * Prova ad aprire il teletrasporto con uno strumento
	 * @param conCosa strumento con cui aprire il teletrasporto
	 * @return la stanza di arrivo
	 */
	public Elemento apriti(Elemento conCosa)
	{
		Elemento response = null;
		conCosa.setInterazioneVisibile(true);
		if (conCosa instanceof Chiave && conCosa.getInterazione() == this)
		{
			conCosa.setInterazioneVisibile(false);
			aperto = true;
			setDescrizione("un teletrasporto, posso usarlo ora.");
			response = getStanza2();
		}
		return response;
	}
}
