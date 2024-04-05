package it.uniroma1.textadv.elementi.personaggi;

import java.util.HashSet;

import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.elementi.Personaggio;
import it.uniroma1.textadv.elementi.oggetti.Soldi;

/**
 * 
 * @author lucatolomei
 *
 */
public class Guardiano extends Personaggio
{
	private boolean occupato = false;
	
	/**
	 * Costruttore del guardiano
	 * @param nome nome del guardiano
	 * @param interazioni insieme di interazioni del guardiano
	 */
	public Guardiano(String nome, HashSet<Elemento> interazioni) { super(nome, interazioni); setInterazioneVisibile(true); }
	
	/**
	 * Ritorna true se il guardiano è occupato con il gatto
	 * @return occupato
	 */
	@Override
	public boolean isOccupato() { return occupato; }
	
	/**
	 * Il guardiano accetta il gatto e diventa occupato
	 * @return null
	 */
	@Override
	public HashSet<Elemento> scambia(Elemento e)
	{
		if (e instanceof Gatto)
		{
			for (Elemento interazione : getInterazioni())
			{
				if (interazione == e)
				{
					occupato = true;
					getInventario().add(e);
					System.out.println("Il guardiano sembra molto contento e ora è distratto ad accarezzare il gatto: è il mio momento!");
				}
			}
		}
		else System.out.println("Il guardiano non sembra gradire.");
		return null;
	}

}
