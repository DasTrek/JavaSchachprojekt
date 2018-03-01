package de.allianz.kt.bewegung;

import de.allianz.kt.spielfeld.InvalidKoordinatenException;
import de.allianz.kt.spielfeld.Koordinaten;

public class Bewegungskoordinate extends Koordinaten
{
	private int offset;
	
	public int getOffset()
	{
		return offset;
	}


	public Bewegungskoordinate(int spalte, int zeile, int offset) throws InvalidKoordinatenException
	{
		super(spalte, zeile);
		this.offset = offset;
		if (spalte > 2 || spalte < -2 || zeile > 2 || zeile < -2)
		{
			throw new InvalidKoordinatenException("Falsche Eingabe");

		} else
		{
			
		}

	}

}
