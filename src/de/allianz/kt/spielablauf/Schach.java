package de.allianz.kt.spielablauf;

import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Schach
{
	public static void main(String[]args) throws InvalidKoordinatenException
	{
		Spiel spiel1 = new Spiel();
		spiel1.playGame();
	}
}
