package it.uniroma1.textadv.elementi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import it.uniroma1.textadv.elementi.oggetti.Chiave;

/**
 * 
 * @author lucatolomei
 *
 */
public class Stanza extends Elemento
{
	private HashSet<Oggetto> oggetti;
	private HashSet<Personaggio> personaggi;
	private HashMap<Direzione, Link> links;
	private HashMap<Direzione, Stanza> stanzeVicine;
	
	/**
	 * Costruttore della stanza
	 * @param nome nome della stanza
	 */
	public Stanza(String nome) { super(nome, ""); }
	
	/**
	 * Completa la stanza aggiungendo i dettagli
	 * @param descrizione descrizione della stanza
	 * @param oggetti gli oggetti presenti nella stanza
	 * @param personaggi i personaggi presenti nella stanza
	 * @param links i link presenti nella stanza
	 * @param stanzeVicine le stanze vicine
	 */
	public void completaStanza(String descrizione, HashSet<Oggetto> oggetti, HashSet<Personaggio> personaggi, HashMap<Direzione, Link> links, HashMap<Direzione, Stanza> stanzeVicine) 
	{
		setDescrizione(descrizione);
		this.oggetti = oggetti;
		this.personaggi = personaggi;
		this.links = links;
		this.stanzeVicine = stanzeVicine;
	}
	
	/**
	 * Ritorna tutti gli elementi in stanza
	 * @return tutti gli elementi in stanza
	 */
	public HashSet<Elemento> getElementi() {
		HashSet<Elemento> elementi = new HashSet<Elemento>();
		for (Oggetto o : oggetti) elementi.add(o);
		for (Personaggio p : personaggi) elementi.add(p);
		for (Direzione d : Direzione.values())
		{
			elementi.add(links.get(d));
			elementi.add(stanzeVicine.get(d));
		}
		return elementi;
	}
	
	/**
	 * Descrive la stanza elencando gli elementi presenti
	 */
	public void descriviStanza() 
	{
		System.out.println("Vedo " + getDescrizione());
		HashSet<Elemento> elementiPresenti = getElementi();
		if (elementiPresenti.size() != 0)
		{
			int i = 0;
			Iterator iter = elementiPresenti.iterator();
			while (iter.hasNext()) 
			{
				Elemento e = (Elemento) iter.next();
				if (e == null)
				{
					iter.remove();
					continue;
				}
				if (!e.isVisibileInStanza()) iter.remove();
			}
			if (elementiPresenti.size() == 0) System.out.println("Non c'è nulla qui.");
			else
			{
				System.out.print("C'è: ");
				for (Elemento e : elementiPresenti)
				{
					if (e instanceof Link || e instanceof Stanza || e instanceof Chiave) System.out.print(e);
					else System.out.print(e.getDescrizione());
					Direzione dir = null;
					if (links.containsValue(e))
					{
						Stream<Direzione> dirStream = links.entrySet().stream().filter(entry -> e.equals(entry.getValue())).map(Map.Entry::getKey);
						dir = dirStream.findFirst().get();
					}
					if (stanzeVicine.containsValue(e))
					{
						Stream<Direzione> dirStream = stanzeVicine.entrySet().stream().filter(entry -> e.equals(entry.getValue())).map(Map.Entry::getKey);
						dir = dirStream.findFirst().get();
					}
					if (dir != null) System.out.print(" a " + dir);
					i++;
					if (elementiPresenti.size() == i) break;
					if (elementiPresenti.size() - 1 == i) System.out.print(" e ");
					else System.out.print(", ");
				}
				System.out.print(".\n");
			}
		}
		else { System.out.println("Non c'è nulla qui."); }
	}
	
	/**
	 * Aggiunge un elemento alla stanza
	 * @param e elemento da aggiungere
	 */
	public void aggiungiElemento(Elemento e) {
		if (e instanceof Oggetto) oggetti.add((Oggetto) e);
	}
	
	/**
	 * Elimina un elemento dalla stanza
	 * @param e elemento da eliminare
	 */
	public void eliminaElemento(Elemento e) {
		if (e instanceof Oggetto) oggetti.remove((Oggetto) e);
	}
	
	/**
	 * Ritorna il link corrispondente alla direzione
	 * @param d direzione scelta
	 * @return il link corrispondente alla direzione
	 */
	public Link getLink(Direzione d) { return links.get(d); }
	
	/**
	 * Ritorna la stanza corrispondente alla direzione
	 * @param d direzione scelta
	 * @return la stanza corrispondente alla direzione
	 */
	public Stanza getStanzaVicina(Direzione d) { return stanzeVicine.get(d); }
	
	/**
	 * Ritorna la stanza
	 * @return la stanza
	 */
	public Stanza getStanza() { return this; }

 }
