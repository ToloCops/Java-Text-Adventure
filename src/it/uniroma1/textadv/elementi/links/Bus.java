package it.uniroma1.textadv.elementi.links;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Link;
import it.uniroma1.textadv.elementi.Oggetto;

public class Bus extends Link {
	private static String desc = "un bus";

	/**
	 * Costruttore del bus
	 * @param nome nome del bus
	 * @param stanza1 nome della stanza di partenza/arrivo
	 * @param stanza2 nome della stanza di partenza/arrivo
	 */
	public Bus(String nome, String stanza1, String stanza2) {
		super(nome, desc, stanza1, stanza2);
	}
}
