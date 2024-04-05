package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;

/**
 * 
 * @author lucatolomei
 *
 */
public class Vite extends Oggetto {
	private static String desc = "una vite";
	
	/**
	 * Costruttore della vite con solamente il nome
	 * @param nome nome del martello
	 */
	public Vite(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore della vite con nome e nome dell'interazione
	 * @param nome nome della vite
	 * @param nomeInterazione nome dell'interazione
	 */
	public Vite(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Prova a svitare la vite con uno strumento
	 * @param conCosa con cosa svitare la vite
	 */
	public void svitati(Elemento conCosa) {
		if (conCosa instanceof Cacciavite)
		{
			System.out.println("La vite si è svitata");
			setInterazioneVisibile(true);
			Elemento inter = getInterazione();
			inter.apriti(conCosa);
		}
	}

}
