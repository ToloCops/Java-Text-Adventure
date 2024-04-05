package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Apribile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Armadio extends Oggetto implements Apribile{
	private static String desc = "un armadio luccicante";
	private boolean aperto = false;
	
	/**
	 * Costruttore dell'armadio con solamente il nome
	 * @param nome nome dell'armadio
	 */
	public Armadio(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore dell'armadio con nome e nome dell'interazione
	 * @param nome nome dell'armadio
	 * @param nomeInterazione nome dell'interazione
	 */
	public Armadio(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Ritorna lo stato dell'oggetto, specificando se l'armadio è aperto o chiuso e cosa contiene all'interno
	 * @return lo stato dell'oggetto
	 */
	@Override
	public String getStato() 
	{
		String stato = "è chiuso da un lucchetto.";
		if (aperto) 
		{
			if (getInterazione() != null) stato = "è aperto. Contiene " + getInterazione() + "."; 
			else stato = "è aperto. Non contiene nulla.";
		}
		return stato;
	}
	
	/**
	 * Prova ad aprire l'armadio
	 * @return null 
	 */
	@Override
	public Elemento apriti()
	{
		System.out.println("L'armadio non si apre");
		return null;
	}
	
	/**
	 * Prova ad aprire l'armadio con uno strumento
	 * @param conCosa strumento con cui aprire l'armadio
	 * @return cosa contiene l'armadio
	 */
	public Elemento apriti(Elemento conCosa)
	{
		conCosa.setInterazioneVisibile(true);
		if (conCosa.getInterazione().getNome().equals(getNome()))
		{
			System.out.println("L'armadio si è aperto");
			aperto = true;
			setInterazioneVisibile(true);
			Elemento interazione = getInterazione();
			if (interazione != null) interazione.setVisibileInStanza(true);
			return interazione;
		}
		else System.out.println("Accidenti, l'elemento che ho scelto non è adatto per aprire l'armadio.");
		conCosa.setInterazioneVisibile(false);
		return null;
	}

}
