package de.allianz.figuren;

import de.allianz.kt.bewegung.Bewegungskoordinate;
import de.allianz.kt.spielablauf.Figur;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Dame extends Figur
{

	public Dame(boolean white) throws InvalidKoordinatenException
	{
		super(white);
		
		moves.add(new Bewegungskoordinate(1,1,7));
		moves.add(new Bewegungskoordinate(-1,1,7));
		moves.add(new Bewegungskoordinate(1,-1,7));
		moves.add(new Bewegungskoordinate(-1,-1,7));
		moves.add(new Bewegungskoordinate(1,0,7));
		moves.add(new Bewegungskoordinate(0,1,7));
		moves.add(new Bewegungskoordinate(-1,0,7));
		moves.add(new Bewegungskoordinate(0,-1,7));

	}

}
