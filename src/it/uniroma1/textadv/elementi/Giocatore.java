package it.uniroma1.textadv.elementi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import it.uniroma1.textadv.Mondo;
import it.uniroma1.textadv.elementi.oggetti.Cacciavite;
import it.uniroma1.textadv.elementi.oggetti.Camino;
import it.uniroma1.textadv.elementi.oggetti.Chiave;
import it.uniroma1.textadv.elementi.oggetti.Scrivania;
import it.uniroma1.textadv.elementi.oggetti.Soldi;
import it.uniroma1.textadv.elementi.oggetti.Tesoro;
import it.uniroma1.textadv.elementi.oggetti.Tronchesi;
import it.uniroma1.textadv.elementi.personaggi.Guardiano;
import it.uniroma1.textadv.elementi.personaggi.Venditore;
import it.uniroma1.textadv.interfacce.Apribile;
import it.uniroma1.textadv.interfacce.Distruttibile;

/**
 * 
 * @author lucatolomei
 *
 */
public class Giocatore extends Personaggio
{
	static private Giocatore giocatore;
	private Stanza luogo;
	private boolean haVinto = false;
	
	/**
	 * Costruttore privato del giocatore
	 * @param nome nome del giocatore
	 * @param luogo stanza in cui si trova il giocatore
	 */
	private Giocatore(String nome, Stanza luogo) 
		{ 
			//super(nome, new HashSet<Oggetto>());
			super(nome, new HashSet<Elemento>());
			this.luogo = luogo;
		}
	
	/**
	 * Metodo per creare il giocatore, una volta soltanto (Singleton pattern)
	 * @param nome nome del giocatore
	 * @param luogo stanza in cui si trova il giocatore
	 * @return l'istanza del giocatore creato
	 */
	public static Giocatore getInstance(String nome, Stanza luogo) {
		if (nome == "") nome = "Zak"; 
		if (giocatore == null) giocatore = new Giocatore(nome, luogo);
		return giocatore;
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "guarda", che possono riguardare un elemento della stanza o la stanza stessa
	 * @param elementi gli argomenti isolati dal comando
	 */
	public void guarda(ArrayList<Elemento> elementi) 
	{
		switch(elementi.size()) 
		{
			case 0 :
				luogo.descriviStanza();
				break;
			case 1 : guarda(elementi.get(0)); break;
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "guarda", descrive l'elemento scelto
	 * @param e l'elemento da descrivere
	 */
	private void guarda(Elemento e) 
	{
		String response = "Vedo " + e.getDescrizione();
		if (e.getStato() != null) response += ", " + e.getStato();
		System.out.println(response);
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "apri", che possono riguardare uno o più elementi
	 * @param elementi gli argomenti isolati dal comando
	 */
	public void apri(ArrayList<Elemento> elementi) 
	{
		switch(elementi.size()) 
		{
			case 0 :
				System.out.println("Azione non riconosciuta, prova a riformulare la frase");
				break;
			case 1 : apri(elementi.get(0)); break;
			case 2 : apri(elementi.get(0), elementi.get(1)); break;
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "apri", prova ad aprire un elemento
	 * @param e elemento da aprire
	 */
	private void apri(Elemento e) 
	{
		Elemento response = null;
		if (e instanceof Apribile)
		{
			response = e.apriti();
		}
		else System.out.println("Azione non consentita.");
		if (response != null) luogo.aggiungiElemento(response);
	}
	
	/**
	 * Metodo privato per le azioni di tipo "apri", prova ad aprire un elemento utilizzandone un altro
	 * @param cosa elemento da aprire
	 * @param conCosa strumento con cui aprire l'elemento
	 */
	private void apri(Elemento cosa, Elemento conCosa)
	{
		Elemento response = null;
		if (cosa instanceof Apribile)
		{
			response = cosa.apriti(conCosa);
			getInventario().remove(conCosa);
		}
		if (response != null) luogo.aggiungiElemento(response);
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "prendi", che possono riguardare uno o più elementi
	 * @param elementi gli argomenti isolati dal comando
	 */
	public void prendi(ArrayList<Elemento> elementi) 
	{
		switch(elementi.size())
		{
			case 0:
				System.out.println("Azione non riconosciuta, prova a riformulare la frase");
				break;
			case 1:
				prendi(elementi.get(0));
				break;
			case 2:
				prendi(elementi.get(0), elementi.get(1));
				break;
			case 3:
				prendi(elementi.get(0), elementi.get(2));
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "prendi", prova a prendere un elemento
	 * @param cosa elemento da prendere
	 */
	private void prendi(Elemento cosa)
	{
		if (cosa instanceof Link) vai(cosa);
		if (cosa.isTascabile())
		{
			Boolean stop = false;
			for (Elemento e : luogo.getElementi())
			{
				if (e instanceof Camino && cosa == e.getInterazione() && e.isAcceso())
				{
					System.out.println("Non posso prenderla, è nel camino acceso");
					stop = true;
				}
				else if (e instanceof Guardiano && !e.isOccupato())
				{
					for (Elemento interazione : e.getInterazioni())
					{
						if (interazione == cosa)
						{
							stop = true; 
							System.out.println("Il guardiano del tesoro si è arrabbiato, non mi permette di prendere il tesoro.");
						}
					}
				}
			}
			if (!stop)
			{
				getInventario().add(cosa);
				luogo.eliminaElemento(cosa);
				if (cosa.toString().equals("tesoro")) haVinto = true;
				System.out.println(cosa + " è ora nell'inventario.");
			}
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "prendi", prova a prendere un elemento all'interno di un altro
	 * @param cosa elemento da prendere
	 * @param daCosa elemento contenente cosa prendere
	 */
	private void prendi(Elemento cosa, Elemento daCosa) {
		if (daCosa.getInterazione() == cosa)
		{
			prendi(cosa);
			daCosa.eliminaInterazione();
		}
		else prendi(cosa);
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "vai", che possono riguardare un solo elemento
	 * @param elementi gli argomenti isolati dal comando
	 */
	public void vai(ArrayList<Elemento> elementi)
	{
		switch (elementi.size())
		{
			case 0:
				System.out.println("Non ho capito, prova a riformulare la frase.");
				break;
			case 1:
				if (elementi.get(0) instanceof DirezioneScelta) vai(elementi.get(0).getDirezione());
				else vai(elementi.get(0));
				break;
			case 2:
				System.out.println("Non ho capito, prova a riformulare la frase.");
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "vai", prova a far andare il giocatore in una direzione
	 * @param d direzione scelta
	 */
	private void vai(Direzione d) 
	{
		Link link = luogo.getLink(d);
		Stanza stanza = null;
		if (link != null)
		{
			Stanza destinazione = link.getCollegamento(luogo);
			if (destinazione != null)
			{
				luogo = destinazione;
				System.out.println("Ora sono in: " + luogo);
			}
			else System.out.println("Non è possibile andare in quella direzione.");
		}
		else
		{
			stanza = luogo.getStanzaVicina(d);
			if (stanza != null)
			{
				luogo = stanza;
				System.out.println("Ora sono in: " + luogo);
			}
			else System.out.println("Non c'è nulla in quella direzione.");
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "vai", prova a far andare il giocatore verso una destinazione
	 * @param e destinazione
	 */
	private void vai(Elemento e) 
	{
		if (e instanceof Link)
		{
			Stanza destinazione = e.getCollegamento(luogo);
			if (destinazione != null)
			{
				luogo = destinazione;
				System.out.println("Ora sono in: " + luogo);
			}
			else System.out.println("Non è possibile andare in quella direzione.");
		}
		else if (e instanceof Stanza)
		{
			Stanza stanza = e.getStanza();
			if (stanza != null)
			{
				luogo = stanza;
				System.out.println("Ora sono in: " + luogo);
			}
			else System.out.println("Non c'è nulla in quella direzione.");
		}
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "entra", che possono riguardare un solo elemento
	 * @param elementi argomenti isolati dal comando
	 */
	public void entra(ArrayList<Elemento> elementi)
	{
		switch (elementi.size())
		{
			case 0:
				System.out.println("Non ho capito, prova a riformulare la frase.");
				break;
			case 1:
				if (elementi.get(0) instanceof DirezioneScelta) vai(elementi.get(0).getDirezione());
				else vai(elementi.get(0));
				break;
			case 2:
				System.out.println("Non ho capito, prova a riformulare la frase.");
		}
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "accarezza", che possono riguardare un solo elemento
	 * @param elementi argomenti isolati dal comando
	 */
	public void accarezza(ArrayList<Elemento> elementi) 
	{
		if (elementi.size() != 1) System.out.println("Non ho capito, prova a riformulare la frase.");
		else
		{
			if (elementi.get(0) instanceof Animale) elementi.get(0).emettiVerso();
			else System.out.println("Azione non consentita.");
		}
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "rompi", che possono riguardare uno o due elementi
	 * @param elementi argomenti isolati dal comando
	 */
	public void rompi(ArrayList<Elemento> elementi) 
	{
		switch (elementi.size())
		{
			case 0:
				System.out.println("Non ho capito, prova a riformulare la frase.");
				break;
			case 1:
				rompi(elementi.get(0));
				break;
			case 2:
				rompi(elementi.get(0), elementi.get(1));
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "rompi", prova a rompere un elemento
	 * @param e elemento da rompere
	 */
	private void rompi(Elemento e)
	{
		Elemento response = null;
		if (e instanceof Distruttibile) e.rompiti();
		else System.out.println("Azione non consentita.");
		if (response != null) luogo.aggiungiElemento(response);
	}
	
	/**
	 * Metodo privato per le azioni di tipo "rompi", prova a rompere un elemento utilizzandone un altro
	 * @param e elemento da rompere
	 * @param strumento strumento da utilizzare per rompere l'elemento
	 */
	private void rompi(Elemento e, Elemento strumento)
	{
		Elemento response = null;
		if (e instanceof Distruttibile) response = e.rompiti(strumento);
		else System.out.println("Azione non consentita.");
		if (response != null) luogo.aggiungiElemento(response);
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "usa", che possono riguardare uno o due elementi
	 * @param elementi argomenti isolati dal comando
	 */
	public void usa(ArrayList<Elemento> elementi)
	{
		switch(elementi.size())
		{
			case 0 : 
				System.out.println("Non ho capito, prova a riformulare la frase.");
				break;
			case 1 :
				usa(elementi.get(0));
				break;
			case 2 :
				usa(elementi.get(0), elementi.get(1));
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "usa", prova a usare un elemento
	 * @param elemento elemento da usare
	 */
	private void usa(Elemento elemento)
	{
		if (elemento instanceof Link) vai(elemento);
		else System.out.println("Non ho capito, prova a riformulare la frase.");
	}
	
	/**
	 * Metodo privato per le azioni di tipo "usa", prova a usare un elemento su un altro
	 * @param strumento elemento da utilizzare
	 * @param destinazione elemento su cui utilizzare lo strumento
	 */
	private void usa(Elemento strumento, Elemento destinazione)
	{
		Elemento response = null;
		response = strumento.azione(destinazione);
		if (strumento instanceof Chiave) getInventario().remove(strumento);
		if (response != null)
		{
			if (response instanceof Stanza) vai(response);
			else luogo.aggiungiElemento(response);
		}
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "dai", che possono riguardare uno o due elementi
	 * @param elementi argomenti isolati dal comando
	 */
	public void dai(ArrayList<Elemento> elementi) 
	{
		switch(elementi.size())
		{
			case 0 : 
				System.out.println("Non ho capito, prova a riformulare la frase.");
				break;
			case 1 :
				System.out.println("Non ho capito, prova a riformulare la frase.");
				break;
			case 2 :
				dai(elementi.get(0), elementi.get(1));
				break;
			case 3 :
				dai(elementi.get(0), elementi.get(1));
		}
	}
	
	/**
	 * Metodo privato per le azioni di tipo "dai", prova a dare un elemento a un personaggio
	 * @param cosa cosa dare
	 * @param aChi a chi dare l'elemento
	 */
	private void dai(Elemento cosa, Elemento aChi) 
	{
		if (aChi instanceof Venditore || aChi instanceof Guardiano) 
		{ 
			HashSet<Elemento> nuoveCose = aChi.scambia(cosa);
			if (nuoveCose != null)
			{
				getInventario().addAll(nuoveCose);
				for (Elemento e : nuoveCose)
					if (luogo.getElementi().contains(e)) luogo.eliminaElemento(e);
				getInventario().remove(cosa);
				String response = aChi.getNome() + " mi ha dato: ";
				int i = 1;
				for (Elemento e : nuoveCose)
				{
					response += e.getDescrizione();
					if (i == nuoveCose.size()) response += ".";
					else if (i == nuoveCose.size()-1) response += " e ";
					else response += ", ";
					i++;
				}
				System.out.println(response);
			}
			else getInventario().remove(cosa);
		}
		else System.out.println("Ancora da definire");
	}
	
	/**
	 * Metodo pubblico per le azioni di tipo "parla", che può riguardare un solo elemento
	 * @param elementi argomenti isolati dal comando
	 */
	public void parla(ArrayList<Elemento> elementi)
	{
		if (elementi.size() != 1) System.out.println("Non ho capito, prova a riformulare la frase.");
		else
		{
			if (elementi.get(0) instanceof Personaggio) elementi.get(0).emettiVerso();
			else System.out.println("Azione non consentita.");
		}
	}

	/**
	 * Metodo pubblico che riporta l'inventario del giocatore
	 * @param elementi argomenti isolati dal comando
	 */
	public void inventario(ArrayList<Elemento> elementi)
	{
		String inventario = "Nella mia borsa ho: ";
		int i = 0;
		for (Elemento e : getInventario())
		{
			i++;
			inventario += e.getDescrizione();
			if (i == getInventario().size()) inventario += ".";
			else if (i == getInventario().size()-1)
				inventario += " e ";
			else inventario += ", ";
		}
		System.out.println(inventario);
	}
	
	/**
	 * Ritorna se il giocatore ha vinto
	 * @return haVinto
	 */
	public boolean haVinto() { return haVinto; }

	/**
	 * Ritorna la stanza in cui si trova il giocatore
	 * @return luogo
	 */
	public Stanza getLuogo() { return luogo; }

}
