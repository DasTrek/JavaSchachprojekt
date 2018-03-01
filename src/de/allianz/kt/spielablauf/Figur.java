package de.allianz.kt.spielablauf;

import java.util.ArrayList;
import java.util.List;

import de.allianz.kt.bewegung.*;
import de.allianz.kt.spielfeld.Bord;
import de.allianz.kt.spielfeld.BrettKoordinaten;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;
import de.allianz.kt.spielfeld.Koordinaten;

public abstract class Figur
{
	protected final boolean white;
	protected boolean farbe;
	protected List<Bewegungskoordinate> moves = new ArrayList<>();
	protected boolean ersterBauernZug = false;
	protected boolean ersterKönigsZug = false;
	private boolean istschon = true;

	public Figur(boolean white)
	{
		this.white = white;
	}

	public boolean isWhite()
	{
		return white;
	}

	@Override
	public String toString()
	{
		String kname = getClass().getSimpleName();
		return kname;

	}

	public char getChar()
	{
		String g = toString();
		char wert;

		if (white == true)
		{
			g = g.toUpperCase();
		} else
		{
			g = g.toLowerCase();
		}
		wert = g.charAt(0);
		return wert;

	}

	public List<BrettKoordinaten> bewegungsregeln(BrettKoordinaten bkor, Bord board)
	{
		List<BrettKoordinaten> regeln = new ArrayList<>(); // Arrayliste für die
															// erlaubten Züge
		int spalte = bkor.getSpalte(); // Koordinaten von Figur die gespielt
										// werden sol
		int zeile = bkor.getZeile();
		BrettKoordinaten startkoor = null;
		try
		{
			startkoor = new BrettKoordinaten(spalte, zeile);
		} catch (InvalidKoordinatenException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // Spalte und Zeile in mathematische Koordinate umwandeln
		Figur fig1 = board.getFigur(startkoor); // Object für Figur auf dem
												// Startfeld estellen
		BrettKoordinaten be = null;
		for (Bewegungskoordinate b : moves) // For schleife um durch die Liste
											// der möglichen Bewegungen
		{
			int boffset = b.getOffset();
			int bSpalte = b.getSpalte();
			int bZeile = b.getZeile();
			spalte = bkor.getSpalte();
			zeile = bkor.getZeile();
			for (int i = 1; i <= boffset; i++) // Offsetwiederholungen der
												// Bewegung
			{
				spalte += bSpalte;
				zeile += bZeile;
				try
				{
					if (spalte < 8 && zeile < 8 && spalte >= 0 && zeile >= 0)
					{
						// Überprüfung

						// ob
						// Bewegung
						// auf
						// dem
						// Schachbrett

						be = new BrettKoordinaten(spalte, zeile);
						BrettKoordinaten testkor = new BrettKoordinaten(spalte, zeile);
						Figur fig2 = board.getFigur(testkor);

						if (fig2 != null)
						{
							i = boffset;
							if (fig2.isWhite() != fig1.isWhite())
							{
								regeln.add(be);

							} else
							{
								regeln.add(startkoor);
							}
						} else
						{
							regeln.add(be);
						}

					}
					else
					{
						regeln.add(startkoor);
					}
				}

				catch (InvalidKoordinatenException e)
				{
					e.printStackTrace();
					System.exit(-1);

				}
			}

		}
		return regeln;
	}

	public void setErsterBauernZug(Figur Bauer)
	{
		if (istschon == true)
		{
			Bauer.moves.set(3, Bauer.moves.get(0));
			
		}
		istschon = false;

		this.ersterBauernZug = false;
	}

	public boolean getErsterBauernZug()
	{
		return ersterBauernZug;
	}
	
	public boolean getErsterKönigszug()
	{
		return ersterKönigsZug;
	}
	public void setErsterKönigsZug()
	{
		this.ersterKönigsZug = true;
	}
	

}
