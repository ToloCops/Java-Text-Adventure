package it.uniroma1.textadv.elementi;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 * @author lucatolomei
 *
 */
public abstract class Personaggio extends Elemento {
	private HashSet<Elemento> interazioni = new HashSet<Elemento>();
	private HashSet<Elemento> inventario = new HashSet<Elemento>();
	public boolean giaAggiunto = false;
	
	/**
	 * Costruttore del personaggio
	 * @param nome nome del personaggio
	 * @param interazioni interazioni del personaggio
	 */
	public Personaggio(String nome, HashSet<Elemento> interazioni) 
	{
		super(nome, nome);
		this.interazioni = interazioni;
	}
	
	/**
	 * Ritorna l'insieme di interazioni del personaggio
	 * @return l'insieme di interazioni del personaggio
	 */
	public HashSet<Elemento> getInterazioni() { return interazioni; }
	
	/**
	 * Setta le interazioni del personaggio
	 * @param interazioni interazioni da settare
	 */
	public void setInterazioni(HashSet<Elemento> interazioni) { this.interazioni = interazioni; }
	
	/**
	 * Ritorna l'inventario del personaggio
	 * @return Ritorna l'inventario del personaggio
	 */
	public HashSet<Elemento> getInventario() { return inventario; }
	
	/**
	 * Setta l'inventore del personaggio
	 * @param inventario l'inventario da settare
	 */
	public void setInventario(HashSet<Elemento> inventario) { this.inventario = inventario; }
	
	@Override
    public boolean equals(Object obj)
    {
		if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Oggetto o = (Oggetto) obj;
        return (o.getNome().equals(getNome()));
    }
	
	@Override
    public int hashCode()
    {
        return getNome().length();
    }
}
