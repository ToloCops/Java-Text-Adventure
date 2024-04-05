package it.uniroma1.textadv.elementi;

/**
 * 
 * @author lucatolomei
 *
 */
public abstract class Oggetto extends Elemento{
	private String nomeInterazione;
	private Elemento interazione;
	private Boolean interazioneVisibile = false;
	
	/**
	 * Costruttore dell'oggetto senza interazione
	 * @param nome nome dell'oggetto
	 * @param descrizione descrizione dell'oggetto
	 */
	public Oggetto(String nome, String descrizione) { super(nome, descrizione);}
	
	/**
	 * Costruttore dell'oggetto con interazione
	 * @param nome nome dell'oggetto
	 * @param descrizione descrizione dell'oggetto
	 * @param nomeInterazione nome dell'interazione dell'oggetto
	 */
	public Oggetto(String nome, String descrizione, String nomeInterazione) { super(nome, descrizione); this.nomeInterazione = nomeInterazione;}
	
	/**
	 * Completa l'oggetto inserendo i "veri" riferimenti alle interazioni
	 * @param interazione interazione da inserire
	 */
	public void completaOggetto(Elemento interazione) { this.interazione = interazione; }
	
	/**
	 * Elimina l'interazione dell'oggetto
	 */
	@Override
	public void eliminaInterazione() { interazione = null; }
	
	/**
	 * Ritorna il nome dell'interazione
	 * @return il nome dell'interazione
	 */
	@Override
	public String getNomeInterazione() { return nomeInterazione; }
	
	/**
	 * Ritorna l'interazione dell'oggetto, se visibile
	 * @return l'interazione dell'oggetto
	 */
	@Override
	public Elemento getInterazione() 
	{
		if (interazioneVisibile)
		{
			return interazione;
		}
		return null;
	}
	
	/**
	 * Setta la variabile per la visibilità dell'interazione
	 */
	public void setInterazioneVisibile(Boolean b) { interazioneVisibile = b; }
	
	/**
	 * Ritorna la variabile per la visibilità dell'interazione
	 * @return la variabile per la visibilità dell'interazione
	 */
	public Boolean getInterazioneVisibile() { return interazioneVisibile; }
	
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
