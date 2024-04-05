package it.uniroma1.textadv.elementi;

import java.util.HashSet;

/**
 * 
 * @author lucatolomei
 *
 */
public class Animale extends Personaggio{
	private String verso;

	/**
	 * Costruttore di un animale
	 * @param nome nome dell'animale
	 * @param inventario inventario dell'animale
	 * @param verso verso dell'animale
	 */
	public Animale(String nome, HashSet<Elemento> inventario, String verso) {
		super(nome, inventario);
		this.verso = verso;
	}
	
	/**
	 * Stampa a video il verso dell'animale
	 */
	@Override
	public void emettiVerso() { System.out.println(verso); }

}
