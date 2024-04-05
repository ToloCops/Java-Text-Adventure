package it.uniroma1.textadv.elementi.links;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Link;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.elementi.oggetti.Cacciavite;
import it.uniroma1.textadv.interfacce.Apribile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Botola extends Link implements Apribile{
	private static String desc = "una botola, c'è una vite sopra di essa.";
	private Boolean aperta = false;

	/**
	 * Costruttore della botola
	 * @param nome nome della botola
	 * @param stanza1 nome della stanza di partenza/arrivo
	 * @param stanza2 nome della stanza di partenza/arrivo
	 */
	public Botola(String nome, String stanza1, String stanza2) {
		super(nome, desc, stanza1, stanza2);
	}
	
	/**
	 * Ritorna true se il giocatore può passare
	 * @return ritorna aperta
	 */
	@Override
	public Boolean puoPassare() 
	{
		if (aperta) return aperta;
		else
			System.out.println("La botola è chiusa.");
		return aperta;
	}
	
	/**
	 * Prova ad aprire la botola
	 * @return null
	 */
	@Override
	public Elemento apriti()
	{
		System.out.println("La botola non si apre");
		return null;
	}
	
	/**
	 * Prova ad aprire la botola con uno strumento
	 * @param strumento con cui aprire la botola
	 * @return null
	 */
	public Elemento apriti(Elemento conCosa)
	{
		if (conCosa instanceof Cacciavite)
		{
			aperta = true;
			setDescrizione("una botola, la vite non la blocca più.");
		}
		return null;
	}
}
