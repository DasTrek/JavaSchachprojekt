package de.allianz.kt.spielablauf;

import java.util.List;

import de.allianz.kt.gui.View;
import de.allianz.kt.spielfeld.Bord;
import de.allianz.kt.spielfeld.BrettKoordinaten;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Spiel
{
	Bord board;
	boolean firsttime = true;
	View view;

	public Spiel() throws InvalidKoordinatenException
	{
		super();
		board = new Bord();
		System.out.println(board.brett());

	}

	public void playGame() throws InvalidKoordinatenException
	{
		board = new Bord();
		Spieler spieler1 = new Spieler("Nico", true);
		Spieler spieler2 = new Spieler("Julian", false);

	}

	public void spielSpielen(String von, String zu) throws InvalidKoordinatenException
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
