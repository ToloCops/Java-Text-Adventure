package it.uniroma1.textadv.elementi;

/**
 * 
 * @author lucatolomei
 *
 */
public enum Direzione{
	N("Nord"),
	S("Sud"),
	O("Ovest"),
	E("Est");
	
	private String nome;
	
	Direzione(String nome) { this.nome = nome; }
	
	@Override
	public String toString() { return nome; }
}
