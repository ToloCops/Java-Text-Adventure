package it.uniroma1.textadv.elementi;

import java.util.HashSet;

/**
 * 
 * @author lucatolomei
 *
 */
public abstract class Elemento{
	private String nome;
	private String descrizione;
	private Boolean visibileInStanza = true;
	public Boolean isInterazione = false;
	public Boolean giaAggiunto = false;
	
	/**
	 * Costruttore dell'elemento base del gioco
	 * @param nome il nome dell'elemento
	 * @param descrizione descrizione dell'elemento
	 */
	public Elemento(String nome, String descrizione) { this.nome = nome; this.descrizione = descrizione; }
	
	/**
	 * Ritorna il nome dell'elemento
	 * @return il nome dell'elemento
	 */
	public String getNome() { return nome; }
	
	/**
	 * Ritorna la descrizione dell'elemento
	 * @return la descrizione dell'elemento
	 */
	public String getDescrizione() { return descrizione; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public Elemento apriti() { System.out.println("Azione non consentita."); return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param conCosa elemento con cui aprire l'elemento
	 * @return null
	 */
	public Elemento apriti(Elemento conCosa) { System.out.println("Azione non consentita."); return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param l'elemento su cui compiere l'azione
	 * @return null
	 */
	public Elemento azione(Elemento e) { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public String getStato() { return null;}
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param luogo stanza in cui si trova il giocatore
	 * @return null
	 */
	public Stanza getCollegamento(Stanza luogo) { System.out.println("Azione non consentita."); return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public Stanza getStanza() { System.out.println("Azione non consentita."); return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public Elemento getInterazione() { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public HashSet<Elemento> getInterazioni() { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public String getNomeInterazione() { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return false
	 */
	public boolean isInterazioneInStanza() { return false; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public Direzione getDirezione() { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param cosa elemento da scambiare
	 * @return null
	 */
	public HashSet<Elemento> scambia(Elemento cosa) { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 */
	public void emettiVerso() {}
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return null
	 */
	public Elemento rompiti() { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param strumento l'elemento con cui rompere l'oggetto
	 * @return null
	 */
	public Elemento rompiti(Elemento strumento) { return null; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param conCosa l'elemento con cui svitare l'oggetto
	 */
	public void svitati(Elemento conCosa) {}
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param conCosa l'elemento con cui riempire l'oggetto
	 */
	public void riempiti(Elemento conCosa) {}
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 */
	public void eliminaInterazione() {}
	
	/**
	 * Setta il parametro visibileInStanza
	 * @param b valore da assegnare a visibileInStanza
	 */
	public void setVisibileInStanza(Boolean b) { visibileInStanza = b; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @param b valore da assegnare a interazioneVisibile
	 */
	public void setInterazioneVisibile(Boolean b) {}
	
	/**
	 * Setta la descrizione dell'elemento
	 * @param descrizione descrizione da settare
	 */
	public void setDescrizione(String descrizione) { this.descrizione = descrizione;}
	
	/**
	 * Ritorna il parametro visibileInStanza
	 * @return il parametro visibileInStanza
	 */
	public boolean isVisibileInStanza() { return visibileInStanza; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return false
	 */
	public boolean isTascabile() { return false; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return false
	 */
	public boolean isAcceso() { return false; }
	
	/**
	 * Metodo destinato alle classi ereditarie, per ora non fa nulla.
	 * @return false
	 */
	public boolean isOccupato() { return false; }
	
	/**
	 * @return il nome dell'elemento
	 */
	@Override
	public String toString() {
		return nome;
	}
}
