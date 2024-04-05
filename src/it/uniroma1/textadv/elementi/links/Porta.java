package it.uniroma1.textadv.elementi.links;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Link;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Apribile;

public class Porta extends Link implements Apribile{
	private static String desc = "una porta";
	private Boolean aperta = false;

	/**
	 * Costruttore della porta
	 * @param nome nome della porta
	 * @param stanza1 nome della stanza di partenza/arrivo
	 * @param stanza2 nome della stanza di partenza/arrivo
	 */
	public Porta(String nome, String stanza1, String stanza2) {
		super(nome, desc, stanza1, stanza2);
	}
	
	/**
	 * Ritorna true se il giocatore può passare
	 * @return aperta
	 */
	@Override
	public Boolean puoPassare() 
	{
		if (aperta) return aperta;
		else
			System.out.println("La porta è bloccata.");
		return aperta;
	}
	
	/**
	 * Prova ad aprire la porta
	 * @return null 
	 */
	@Override
	public Elemento apriti()
	{
		if (isInterazione) System.out.println("La porta non si apre");
		else
		{
			aperta = true;
			System.out.println("La porta si è aperta");
		}
		return null;
	}
	
	/**
	 * Prova ad aprire la porta con uno strumento
	 * @param conCosa strumento con cui aprire la porta
	 * @return null
	 */
	public Elemento apriti(Elemento conCosa)
	{
		conCosa.setInterazioneVisibile(true);
		if (conCosa.getInterazione().getNome().equals(getNome()))
		{
			aperta = true;
			System.out.println("La porta si è aperta");
		}
		else System.out.println("Accidenti, l'elemento che ho selto non è adatto per aprire questa porta.");
		conCosa.setInterazioneVisibile(false);
		return null;
	}
}
