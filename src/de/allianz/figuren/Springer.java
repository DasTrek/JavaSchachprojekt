package de.allianz.figuren;

import de.allianz.kt.bewegung.Bewegungskoordinate;
import de.allianz.kt.spielablauf.Figur;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Springer extends Figur
{

	public Springer(boolean white) throws InvalidKoordinatenException
	{
		super(white);
		moves.add(new Bewegungskoordinate(2,1,1));
		moves.add(new Bewegungskoordinate(1,2,1));
		moves.add(new Bewegungskoordinate(2,-1,1));
		moves.add(new Bewegungskoordinate(1,-2,1));
		moves.add(new Bewegungskoordinate(-1,-2,1));
		moves.add(new Bewegungskoordinate(-2,-1,1));
		moves.add(new Bewegungskoordinate(-2,1,1));
		moves.add(new Bewegungskoordinate(-1,2,1));
	}

}
