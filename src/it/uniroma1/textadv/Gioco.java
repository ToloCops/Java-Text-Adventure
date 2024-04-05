package it.uniroma1.textadv;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import it.uniroma1.textadv.elementi.Direzione;
import it.uniroma1.textadv.elementi.DirezioneScelta;
import it.uniroma1.textadv.elementi.Elemento;
import it.uniroma1.textadv.elementi.Oggetto;
import it.uniroma1.textadv.elementi.Personaggio;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.nio.file.Path;

/**
 * 
 * @author lucatolomei
 *
 */
public class Gioco 
{
	public DirezioneScelta nord = new DirezioneScelta("Nord", Direzione.N);
	public DirezioneScelta sud = new DirezioneScelta("Sud", Direzione.S);
	public DirezioneScelta est = new DirezioneScelta("Est", Direzione.E);
	public DirezioneScelta ovest = new DirezioneScelta("Ovest", Direzione.O);
	
	public HashSet<Elemento> direzioni = new HashSet<Elemento>(Arrays.asList(nord, sud, est, ovest));
	

	/**
	 * Fa partire il gioco nella modalità classica
	 * @param mondo il mondo in cui giocare
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void play(Mondo mondo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		while(true) 
		{
			if (mondo.getGiocatore().getInventario().toString().contains("tesoro")) break;
			Scanner s = new Scanner(System.in);
			String comando = s.nextLine();
			if (comando.equals("")) continue;
			if (comando.equals("stop")) { break; }
			processaComando(comando, mondo);
			if (mondo.getGiocatore().haVinto()) break;
		}
		System.out.println("Hai vinto!");
	}
	
	/**
	 * Fa partire il gioco in modalità fast forward
	 * @param mondo il mondo in cui giocare
	 * @param path il path del file da cui prendere l'elenco dei comandi
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void play(Mondo mondo, Path path) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		File file = path.toFile();
		
		try
		{
			Scanner in = new Scanner(file);
			while(in.hasNext())
			{
				String comando = in.nextLine();
				if (comando.equals("")) continue;
				if (comando.equals("stop")) break;
				processaComando(comando, mondo);
				if (mondo.getGiocatore().haVinto()) break;
			}
			in.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("merda");
		}
		System.out.println("Hai vinto!");
	}
	
	/**
	 * Processa il comando trovando prima l'azione da compiere e, successivamente, gli argomenti da passare al giocatore, se ce ne sono
	 * @param comando la riga di comando da processare
	 * @param mondo il mondo in cui si gioca
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void processaComando(String comando, Mondo mondo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
	{
		comando = comando.split("//")[0].replace("\t", "");
		System.out.println("-"+comando);
		String[] partiComando = comando.split("//")[0].split(" ");
		ArrayList<Method> azioni = new ArrayList<Method>();
		for (Method m : mondo.getGiocatore().getClass().getDeclaredMethods())
		{
			if (!Modifier.isPublic(m.getModifiers())) continue;
			String nomeMetodo = m.getName();
			if (nomeMetodo.startsWith(partiComando[0])) azioni.add(m);
		}
		if (azioni.size() != 0)
		{
			ArrayList<Elemento> argomenti = trovaArgomenti(comando, mondo);
			//System.out.println(argomenti);
			ArrayList<Elemento> argomentiNonDuplicati = new ArrayList<Elemento>();
			for (Elemento e : argomenti) 
			{
				if (!argomentiNonDuplicati.contains(e)) argomentiNonDuplicati.add(e);
			}
			Object[] param = {argomentiNonDuplicati};
			azioni.get(0).invoke(mondo.getGiocatore(), param);
		}
		else System.out.println("Non ho capito, prova a riformulare la frase");
	}
	
	
	/**
	 * Trova gli argomenti in base a ciò che circonda il giocatore e a cosa ha quest'ultimo nel proprio inventario
	 * @param comando il comando da esaminare
	 * @param mondo il mondo in cui ci si trova
	 * @return gli argomenti da passare al giocatore
	 */
	private ArrayList<Elemento> trovaArgomenti(String comando, Mondo mondo) {
		ArrayList<Elemento> argomenti = new ArrayList<Elemento>();
		String[] partiComando = comando.split("//")[0].split(" ");
		HashSet<Elemento> elementi = mondo.getGiocatore().getLuogo().getElementi();
		elementi.addAll(mondo.getGiocatore().getInventario());
		//processo di riconoscimento delle parole nel comando
		comando = String.join(" ", Arrays.copyOfRange(partiComando, 1, partiComando.length));
		String comandoOriginale = comando;
		ArrayList<Elemento> test = cercaElementi(comando, elementi);
		if (test != null) argomenti.addAll(test);
		String comando2 = comando;
		while (!comando2.equals(""))
		{
			comando = comando2;
			comando2 = String.join(" ", Arrays.copyOfRange(comando.split(" "), 1, comando.split(" ").length));
			if (comando.equals(comando2)) break;
			ArrayList<Elemento> nuoviElementi = cercaElementi(comando2, elementi);
			if (nuoviElementi != null) argomenti.addAll(nuoviElementi);
		}
		return argomenti;
	}
	
	/**
	 * In base agli elementi presenti, tramite una ricorsione sulla riga di comando, vengono isolati gli argomenti da passaare al giocatore
	 * @param comando il comando da esaminare
	 * @param elementi gli elementi che circondano il giocatore o che sono nel suo inventario
	 * @return gli argomenti isolati
	 */
	public ArrayList<Elemento> cercaElementi(String comando, HashSet<Elemento> elementi)
	{
		ArrayList<Elemento> argomenti = new ArrayList<Elemento>();
		Elemento e = null;
		e = cercaElemento(comando, elementi);
		if (e == null)
		{
			String comando2 = String.join(" ", Arrays.copyOfRange(comando.split(" "), 0, comando.split(" ").length-1));
			if (comando2.equals("")) return null;
			else
			{
				ArrayList<Elemento> test = cercaElementi(comando2, elementi);
				if (test != null) e = test.get(0);
			}
		}
		if (e == null) return null;
		else
		{
			argomenti.add(e);
			return argomenti;
		}
	}
	
	/**
	 * Verifica la presenza di un elemento all'interno dell'insieme di questi ultimi
	 * @param nomeElemento l'elemento da verificare
	 * @param elementi insieme di elementi presenti
	 * @return
	 */
	public Elemento cercaElemento(String nomeElemento, HashSet<Elemento> elementi) 
	{
		for (Elemento e : elementi)
		{
			if (e == null) continue;
			if (e.getNome().toLowerCase().equals(nomeElemento.toLowerCase())) return e;
		}
		for (Elemento d : direzioni)
		{
			if (nomeElemento.toLowerCase().startsWith(d.toString().toLowerCase().substring(0, 1)))
			{
				if (nomeElemento.length() == 1 || nomeElemento.toLowerCase().equals(d.toString().toLowerCase())) return d;
			}
		}
		return null;
	}

}
