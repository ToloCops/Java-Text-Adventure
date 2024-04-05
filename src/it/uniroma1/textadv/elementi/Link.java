package it.uniroma1.textadv.elementi;

/**
 * 
 * @author lucatolomei
 *
 */
public abstract class Link extends Elemento
{
	private String nomeStanza1;
	private String nomeStanza2;
	private Stanza stanza1;
	private Stanza stanza2;
	
	/**
	 * Costruttore del link
	 * @param nome nome del link
	 * @param descrizione descrizione del link
	 * @param nomeStanza1 nome della stanza di partenza/arrivo
	 * @param nomeStanza2 nome della stanza di partenza/arrivo
	 */
	public Link(String nome, String descrizione, String nomeStanza1, String nomeStanza2) 
	{
		super(nome, descrizione);
		this.nomeStanza1 = nomeStanza1;
		this.nomeStanza2 = nomeStanza2;
	}
	
	/**
	 * Completa il link inserendo i "veri" riferimenti alle stanze
	 * @param stanza1 stanza di partenza/arrivo
	 * @param stanza2 stanza di partenza/arrivo
	 */
	public void completaLink(Stanza stanza1, Stanza stanza2) {
		this.stanza1 = stanza1;
		this.stanza2 = stanza2;
	}
	
	/**
	 * Ritorna la stanza di arrivo, dando quella di partenza
	 * @param partenza stanza di partenza
	 * @return la stanza di arrivo
	 */
	public Stanza getCollegamento(Stanza partenza)
	{
		if (puoPassare())
		{
			if (partenza == stanza1) return stanza2;
			return stanza1;
		}
		else return null;
	}
	
	/**
	 * Ritorna true se il personaggio può passare attraverso il link
	 * @return true
	 */
	public Boolean puoPassare() { return true; }
	
	/**
	 * Ritorna il nome della stanza1
	 * @return il nome di stanza1
	 */
	public String getNomeStanza1() { return nomeStanza1; }
	
	/**
	 * Ritorna il nome della stanza2
	 * @return il nome di stanza2
	 */
	public String getNomeStanza2() { return nomeStanza2; }
	
	/**
	 * Ritorna la stanza2
	 * @return la stanza2
	 */
	public Stanza getStanza2() { return stanza2; } 
}
