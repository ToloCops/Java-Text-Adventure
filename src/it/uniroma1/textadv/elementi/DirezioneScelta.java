package it.uniroma1.textadv.elementi;

/**
 * 
 * @author lucatolomei
 *
 */
public class DirezioneScelta extends Elemento{
	private Direzione direzioneScelta;
	
	/**
	 * Costruttore che salva la direzione desiderata dal gioctore
	 * @param nome nome della direzione
	 * @param direzioneScelta direzione desiderata dal giocatore
	 */
	public DirezioneScelta(String nome, Direzione direzioneScelta) {
		super(nome, "");
		this.direzioneScelta = direzioneScelta;
	}
	
	/**
	 * @return la direzione scelta dal giocatore
	 */
	@Override
	public Direzione getDirezione() { return direzioneScelta; }

}
