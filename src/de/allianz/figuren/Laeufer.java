package de.allianz.figuren;

import de.allianz.kt.bewegung.Bewegungskoordinate;
import de.allianz.kt.spielablauf.Figur;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Laeufer extends Figur
{

	public Laeufer(boolean white) throws InvalidKoordinatenException
	{
		super(white);
		moves.add(new Bewegungskoordinate(1,1,7));
		moves.add(new Bewegungskoordinate(-1,1,7));
		moves.add(new Bewegungskoordinate(1,-1,7));
		moves.add(new Bewegungskoordinate(-1,-1,7));

	}

}
