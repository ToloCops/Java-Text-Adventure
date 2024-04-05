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
public class Venditore extends Personaggio
{
	/**
	 * Costruttore del venditore
	 * @param nome nome del venditore
	 * @param interazioni insieme di interazioni del venditore
	 */
	public Venditore(String nome, HashSet<Elemento> interazioni) { super(nome, interazioni); }
	
	/**
	 * Il venditore accetta i soldi e da ciò che vende al personaggio
	 * @return ciò che vende se è stato pagato
	 */
	@Override
	public HashSet<Elemento> scambia(Elemento e)
	{
		if (e instanceof Soldi)
		{
			HashSet<Elemento> oggetti = getInterazioni();
			setInterazioni(new HashSet<Elemento>());
			getInventario().add(e);
			return oggetti;
		}
		System.out.println("Il venditore non sembra gradire.");
		return null;
	}

}
