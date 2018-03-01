package de.allianz.kt.spielablauf;

public  class Spieler
{
	String eingabe;
	boolean isWhite;
	String Farbe;

	public Spieler(String eingabe, boolean isWhite)
	{
		this.eingabe = eingabe;
		this.isWhite = isWhite;
	}

	@Override
	public String toString()
	{

		if(isWhite == true)
		{
			Farbe = "Weiﬂ";
		}
		else
		{
			Farbe = "Schwarz";
		}
		return eingabe + "  " +  Farbe;
	}
	
//	public abstract void doTurn();
}
