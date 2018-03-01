package de.allianz.kt.bewegung;

import de.allianz.kt.spielablauf.Figur;

public class Bewegung extends Figur
{
	public Bewegung(boolean white)
	{
		super(white);
	}

	int startzeile;
	int startspalte;
	int endzeile;
	int endspalte;
	
	public void startposition(int zeile, int spalte)
	{
		this.startzeile = zeile;
		this.startspalte = spalte;
	}
	
	public void endposition(int zeile, int spalte)
	{
		this.endzeile = zeile;
		this.endspalte = spalte;
	}

}
