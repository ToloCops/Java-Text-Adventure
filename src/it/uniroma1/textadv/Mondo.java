package it.uniroma1.textadv;

import it.uniroma1.textadv.eccezioni.OggettoGiaInseritoException;
import it.uniroma1.textadv.eccezioni.PersonaggioGiaInseritoException;
import it.uniroma1.textadv.elementi.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 
 * @author lucatolomei
 *
 */
public class Mondo 
{
	static private Mondo mondo;
	
	private String nome;
	private String descrizione;
	private HashSet<Stanza> stanzeMondo;
	private static Giocatore g;
	
	/**
	 * Costruttore del mondo privato
	 * @param nome nome del mondo
	 * @param descrizione descrizione del mondo
	 * @param stanze stanze presenti nel mondo
	 */
	private Mondo(String nome, String descrizione, HashSet<Stanza> stanze) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.stanzeMondo = stanze;
	}
	
	/**
	 * Metodo per far si che il mondo venga istanziato una volta sola (Singleton)
	 * @param nome
	 * @param descrizione
	 * @param stanze
	 * @return l'istanza del mondo
	 */
	public static Mondo getInstance(String nome, String descrizione, HashSet<Stanza> stanze) {
		if (mondo == null) mondo = new Mondo(nome, descrizione, stanze);
		return mondo;
	}
	
	/**
	 * Carica il mondo nel gioco da un file .game
	 * @param file il nome del file da caricare
	 * @return il mondo caricato
	 * @throws PersonaggioGiaInseritoException 
	 * @throws OggettoGiaInseritoException 
	 */
	public static Mondo fromFile(String file) throws OggettoGiaInseritoException, PersonaggioGiaInseritoException {
		HashSet<Oggetto> oggetti = caricaOggetti(file);
		HashSet<Personaggio> personaggi = caricaPersonaggi(file, oggetti);
		HashSet<Link> link = caricaLink(file);
		HashSet<Stanza> stanzeMondo = new HashSet<Stanza>();
		stanzeMondo.addAll(caricaStanze(file, oggetti, personaggi, link));
		//completo i link con gli oggetti Stanza
		for (Link l : link)
		{
			Stanza stanza1 = cercaStanza(l.getNomeStanza1(), stanzeMondo);
			Stanza stanza2 = cercaStanza(l.getNomeStanza2(), stanzeMondo);
			l.completaLink(stanza1, stanza2);
		}
		//completo gli oggetti con le loro interazioni
		for (Oggetto o : oggetti) 
		{
			if (o.getNomeInterazione() != null) 
			{
				Elemento comp = cercaOggetto(o.getNomeInterazione(), oggetti);
				if (comp == null) comp = cercaLink(o.getNomeInterazione(), link);
				if (comp == null) comp = cercaStanza(o.getNomeInterazione(), stanzeMondo);
				if (comp != null) comp.isInterazione = true;
				o.completaOggetto(comp);
				if (o.isInterazioneInStanza())
				{
					for (Stanza s : stanzeMondo)
					{
						if (s.getElementi().contains(o)) s.aggiungiElemento(comp);
					}
				}
			}
		}
		File f = new File(file);
		String nome = "";
		String descrizione = "";
		Stanza stanza = null;
		try
		{
			Scanner in = new Scanner(f);
			while(in.hasNext())
			{
				String line = in.nextLine();
				if (!line.startsWith("[world:")) continue;
				nome = line.split(":")[1];
				descrizione = in.nextLine().split("\t")[1];
				String nomeStanza = in.nextLine().split("\t")[1];
				stanza = cercaStanza(nomeStanza, stanzeMondo);
				String nomeGiocatore = "";
				g = Giocatore.getInstance(nomeGiocatore, stanza);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getInstance(nome, descrizione, stanzeMondo);
	}
	
	/**
	 * Cerca una stanza all'interno dell'insieme passato come parametro
	 * @param nomeStanza stanza da cercare
	 * @param stanze insieme di stanze
	 * @return riferimento alla stanza trovata, se è stata trovata. Altrimenti null
	 */
	private static Stanza cercaStanza(String nomeStanza, HashSet<Stanza> stanze) {
		for (Stanza s : stanze)
		{
			if (s == null) continue;
			if (s.getNome().equals(nomeStanza)) return s;
		}
		return null;
	}
	
	/**
	 * Cerca un link all'interno dell'insieme passato come parametro
	 * @param nome nome del link da cercarre
	 * @param links insieme dei link
	 * @return riferimento al link trovato, se è stato trovato. Altrimenti null
	 */
	private static Link cercaLink(String nome, HashSet<Link> links) {
		for (Link l : links)
			if (l.toString().equals(nome)) return l;
		return null;
	}

	/**
	 * Cerca personaggi all'interno di un insieme passato come parametro
	 * @param nomi nomi dei personaggi da cercare
	 * @param personaggi personaggi caricati
	 * @return insieme dei personaggi trovati
	 */
	private static HashSet<Personaggio> cercaPersonaggi(String[] nomi, HashSet<Personaggio> personaggi) {
		HashSet<Personaggio> personaggiStanza = personaggi.stream().filter(p -> Arrays.asList(nomi).contains(p.toString())).collect(Collectors.toCollection(HashSet::new));
		return personaggiStanza;
	}

	/**
	 * Cerca oggetti all'interno di un insieme passato come parametro
	 * @param nomi nomi degli oggetti da cercare
	 * @param oggetti oggetti caricati
	 * @return insieme degli oggetti trovati
	 */
	private static HashSet<Oggetto> cercaOggetti(String[] nomi, HashSet<Oggetto> oggetti) {
		HashSet<Oggetto> oggettiTrovati = oggetti.stream().filter(o -> Arrays.asList(nomi).contains(o.toString())).collect(Collectors.toCollection(HashSet::new));
		return oggettiTrovati;
	}
	
	/**
	 * Cerca un oggetto interazione all'interno di un insieme passato come parametro
	 * @param nomeInterazione nome dell'oggetto da cercare
	 * @param oggetti oggetti caricati
	 * @return insieme degli oggetti trovati
	 */
	private static Oggetto cercaOggetto(String nomeInterazione, HashSet<Oggetto> oggetti) {
		for (Oggetto o : oggetti)
			if (o.getNome().equals(nomeInterazione)) return o;
		return null;
	}
	
	/**
	 * Carica ciò che è all'interno della sezione [links]
	 * @param file file da analizzare
	 * @return l'insieme dei link caricati
	 */
	private static HashSet<Link> caricaLink(String file) {
		HashSet<Link> links = new HashSet<Link>();
		File f = new File(file);
		try
		{
			Scanner in = new Scanner(f);
			while(!in.nextLine().startsWith("[links]"))
				continue;
			while(in.hasNext())
			{
				String s = in.nextLine();
				if (s.length() == 0) break;
				String[] parameters = getParameters(s);
				try 
				{
					Class<?> c = Class.forName("it.uniroma1.textadv.elementi.links." + parameters[1]);
					Class<? extends Link> link = c.asSubclass(Link.class);
					Constructor<? extends Link> constr = link.getConstructor(String.class, String.class, String.class);
					Link l = constr.newInstance(parameters[0], parameters[2], parameters[3]);
					links.add(l);
				} catch (ClassNotFoundException e) 
				{
					
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			in.close();
		}
		catch (FileNotFoundException e)
		{
			
		}
		return links;
	}
	
	/**
	 * Carica ciò che è all'interno delle sezioni [room]
	 * @param file file da analizzare
	 * @param oggetti insieme degli oggetti caricati
	 * @param personaggi insieme dei personaggi caricati
	 * @param links insieme dei link caricati
	 * @return insieme delle stanze del mondo
	 * @throws OggettoGiaInseritoException 
	 * @throws PersonaggioGiaInseritoException 
	 */
	private static HashSet<Stanza> caricaStanze(String file, HashSet<Oggetto> oggetti, HashSet<Personaggio> personaggi, HashSet<Link> links) throws OggettoGiaInseritoException, PersonaggioGiaInseritoException {
		HashSet<Stanza> stanze = new HashSet<Stanza>();
		File f = new File(file);
		
		try
		{
			Scanner in = new Scanner(f);
			while(in.hasNext())
			{
				String line = in.nextLine();
				if (!line.startsWith("[room:")) continue;
				String nome = line.split(":")[1].replace("]", "");
				Stanza test = cercaStanza(nome, stanze);
				String descrizione = "";
				HashSet<Oggetto> oggettiStanza = new HashSet<Oggetto>();
				HashSet<Personaggio> personaggiStanza = new HashSet<Personaggio>();
				HashMap<Direzione, Link> linkStanza = new HashMap<Direzione, Link>();
				HashMap<Direzione, Stanza> stanzeVicine = new HashMap<Direzione, Stanza>();
				
				while(in.hasNext()) 
				{
					String p = in.nextLine();
					if (p.length() == 0) break;
					String[] parameters = p.split("\t");
					switch(parameters[0]) 
					{
						case "description": descrizione = parameters[1]; break;
						case "objects":
							if (parameters.length < 2) break;
							String[] nomiOggetti = parameters[1].split(",");
							for (int i = 0; i < nomiOggetti.length; i++) 
								nomiOggetti[i] = nomiOggetti[i].strip();
							oggettiStanza = cercaOggetti(nomiOggetti, oggetti);
							for (Oggetto o : oggettiStanza)
							{
								if (o.giaAggiunto) throw new OggettoGiaInseritoException();
								else o.giaAggiunto = true;
							}
							break;
						case "characters":
							if (parameters.length < 2) break;
							String[] nomiPersonaggi = parameters[1].split(",");
							for (int i = 0; i < nomiPersonaggi.length; i++) 
								nomiPersonaggi[i] = nomiPersonaggi[i].strip();
							personaggiStanza = cercaPersonaggi(nomiPersonaggi, personaggi);
							for (Personaggio personaggio : personaggiStanza)
							{
								if (personaggio.giaAggiunto) throw new PersonaggioGiaInseritoException();
								else personaggio.giaAggiunto = true;
							}
							break;
						case "links":
							if (parameters.length < 2) break;
							String[] direzioniStanze = parameters[1].split(",");
							for (int i = 0; i < direzioniStanze.length; i++) 
							{
								Direzione d = Direzione.valueOf(direzioniStanze[i].split(":")[0].strip());
								Link l = cercaLink(direzioniStanze[i].split(":")[1].strip(), links);
								if (l == null) 
								{
									String nomeStanza = direzioniStanze[i].split(":")[1].strip();
									Stanza s = cercaStanza(nomeStanza, stanze);
									if (s == null)
									{
										s = new Stanza(nomeStanza);
										stanze.add(s);
									}
									stanzeVicine.put(d, s);
									continue;
								}
								linkStanza.put(d, l);
							}
							break;
						default: System.out.println("errore"); break;
					}
				}
				Stanza stanza;
				if(test != null)
				{ 
					test.completaStanza(descrizione, oggettiStanza, personaggiStanza, linkStanza, stanzeVicine);
					stanze.add(test);
					continue;
				}
				stanza = new Stanza(nome);
				stanza.completaStanza(descrizione, oggettiStanza, personaggiStanza, linkStanza, stanzeVicine);
				stanze.add(stanza);
			}
			in.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("merd");
		}
		
		return stanze;
	}
	
	/**
	 * Carica ciò che è all'interno della sezione [characters]
	 * @param file file da analizzare
	 * @param oggetti oggetti caricati
	 * @return l'insieme dei personaggi caricati
	 */
	private static HashSet<Personaggio> caricaPersonaggi(String file, HashSet<Oggetto> oggetti) {
		HashSet<Personaggio> personaggi = new HashSet<Personaggio>();
		File f = new File(file);
		
		try
		{
			Scanner in = new Scanner(f);
			while(!in.nextLine().startsWith("[characters]"))
				continue;
			while(in.hasNext())
			{
				String s = in.nextLine();
				if (s.length() == 0) break;
				String[] parameters = getParameters(s);
				try 
				{
					Class<?> c = Class.forName("it.uniroma1.textadv.elementi.personaggi." + parameters[1]);
					Class<? extends Personaggio> pers = c.asSubclass(Personaggio.class);
					Personaggio p = null;
					if (parameters.length > 1) 
					{
						Constructor<? extends Personaggio> constr = pers.getConstructor(String.class, HashSet.class);
						String[] nomiElementi = Arrays.copyOfRange(parameters, 2, parameters.length+1);
						HashSet<Elemento> interazioni = new HashSet<Elemento>();
						interazioni.addAll(cercaOggetti(nomiElementi, oggetti));
						interazioni.addAll(cercaPersonaggi(nomiElementi, personaggi));
						p = constr.newInstance(parameters[0], interazioni);
					}
					else 
					{
						Constructor<? extends Personaggio> constr = pers.getConstructor(String.class);
						p = constr.newInstance(parameters[0]);
					}
					personaggi.add(p);
				} catch (ClassNotFoundException e) 
				{
					
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			in.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("merd");
		}
		return personaggi;
	}
	
	/**
	 * Carica ciò che è all'interno della sezione [objects]
	 * @param file nome del file da analizzare
	 * @return l'insieme degli oggetti caricati
	 */
	private static HashSet<Oggetto> caricaOggetti(String file) {
		HashSet<Oggetto> oggetti = new HashSet<Oggetto>();
		File f = new File(file);
		
		try
		{
			Scanner in = new Scanner(f);
			while(!in.nextLine().startsWith("[objects]"))
				continue;
			while(in.hasNext())
			{
				String s = in.nextLine();
				if (s.length() == 0) break;
				String[] parameters = getParameters(s);
				try
				{
					Class<?> c = Class.forName("it.uniroma1.textadv.elementi.oggetti." + parameters[1]);
					Class<? extends Oggetto> ogg = c.asSubclass(Oggetto.class);
					Oggetto o = null;
					if (parameters.length > 2) 
					{
						Constructor<? extends Oggetto> constr = ogg.getConstructor(String.class, String.class);
						o = constr.newInstance(parameters[0], parameters[2]);
						//System.out.println(o.getClass());
					}
					else 
					{
						Constructor<? extends Oggetto> constr = ogg.getConstructor(String.class);
						o = constr.newInstance(parameters[0]);
						//System.out.println(o.getClass());
					}
					oggetti.add(o);
				} catch(ClassNotFoundException e) 
				{
					System.out.println("Non è stata trovata una classe per l'oggetto" + parameters[1]);
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			in.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("merd");
		}
		return oggetti;
		
	}

	/**
	 * Isola i parametri da una riga
	 * @param s riga da analizzare
	 * @return l'elenco dei parametri
	 */
	private static String[] getParameters(String s) 
	{
		String[] test = s.split(" //");
		String validString = test[0];
		String[] parameters = validString.split("\t");
		return parameters;
	}
	
	/**
	 * Ritorna il riferimento al giocatore
	 * @return il riferimento al giocatore
	 */
	public Giocatore getGiocatore() { return g; }

}
