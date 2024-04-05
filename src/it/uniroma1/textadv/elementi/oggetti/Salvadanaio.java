package it.uniroma1.textadv.elementi.oggetti;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.interfacce.Distruttibile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Salvadanaio extends Oggetto implements Distruttibile{
	private static String desc = "un salvadanaio";
	private Boolean rotto = false;
	
	/**
	 * Costruttore del salvadanaio con solamente il nome
	 * @param nome nome del salvadanaio
	 */
	public Salvadanaio(String nome) { super(nome, desc); }
	
	/**
	 * Costruttore del salvadanaio con nome e nome dell'interazione
	 * @param nome nome del salvadanaio
	 * @param nomeInterazione nome dell'interazione
	 */
	public Salvadanaio(String nome, String nomeInterazione) { super(nome, desc, nomeInterazione); }
	
	/**
	 * Prova a rompere il salvadanaio
	 * @return null
	 */
	@Override
	public Elemento rompiti() 
	{
		System.out.println("Non si rompe, è necessario un oggetto per romperlo.");
		return null;
	}
	
	/**
	 * Prova a rompere il salvadanaio con uno strumento
	 * @param strumento strumento con cui rompere il salvadanaio
	 * @return l'interazione contenuta nel salvadanaio se rotto
	 */
	public Elemento rompiti(Elemento strumento)
	{
		if (strumento instanceof Martello)
		{
			rotto = true;
			System.out.println("Il salvadanaio si è rotto");
			setDescrizione("un salvadanaio rotto");
			setInterazioneVisibile(true);
			Elemento i = getInterazione();
			eliminaInterazione();
			return i;
		}
		return null;
	}
}
