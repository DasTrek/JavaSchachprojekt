package de.allianz.figuren;

import de.allianz.kt.bewegung.Bewegungskoordinate;
import de.allianz.kt.spielablauf.Figur;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Bauer extends Figur
{

	public Bauer(boolean white, boolean ersterBauernZug) throws InvalidKoordinatenException
	{
		super(white);
		
		
		if (white == true)
		{

			moves.add(new Bewegungskoordinate(0, 1, 1));
			moves.add(new Bewegungskoordinate(-1, 1, 1));
			moves.add(new Bewegungskoordinate(1, 1, 1));
			moves.add(new Bewegungskoordinate(0, 1, 2));

		} else
		{

			moves.add(new Bewegungskoordinate(0, -1, 1));
			moves.add(new Bewegungskoordinate(-1, -1, 1));
			moves.add(new Bewegungskoordinate(1, -1, 1));
			moves.add(new Bewegungskoordinate(0, -1, 2));

		}
		
		
		

	}

}
