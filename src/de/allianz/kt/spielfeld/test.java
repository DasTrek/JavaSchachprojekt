package de.allianz.kt.spielfeld;

import de.allianz.kt.gui.View;
import de.allianz.kt.spielablauf.Spiel;

public class test
{
	public static void main(String[] args) throws InvalidKoordinatenException
	{
		Bord board = new Bord();
		// Spiel spielen = new Spiel();
		// spielen.spielSpielen("e2","e4");
		//// spielen.spielSpielen("d2", "d4");
		//// spielen.spielSpielen("d4", "d5");
		View view = new View(board);
		view.init();

		
	}
}
