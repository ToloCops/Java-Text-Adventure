package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Riempibile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Pozzo extends Oggetto implements Riempibile{
	private static String desc = "un pozzo vuoto";
	private Boolean vuoto = true;
	
	/**
	 * Costruttore del pozzo con solamente il nome
	 * @param nome nome del pozzo
	 */
	public Pozzo(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del pozzo con nome e nome dell'interazione
	 * @param nome nome del pozzo
	 * @param nomeInterazione nome dell'interazione
	 */
	public Pozzo(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Prova a riempire il pozzo con uno strumento
	 * @param conCosa con cosa riempire il pozzo
	 */
	public void riempiti(Elemento conCosa)
	{
		if (conCosa instanceof Secchio)
		{
			vuoto = false;
			System.out.println("il pozzo si è riempito d'acqua");
			setDescrizione("un pozzo pieno d'acqua");
		}
	}

}
