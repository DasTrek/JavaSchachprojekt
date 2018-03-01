package de.allianz.kt.spielfeld;

import de.allianz.figuren.Bauer;
import de.allianz.figuren.Dame;
import de.allianz.figuren.Koenig;
import de.allianz.figuren.Laeufer;
import de.allianz.figuren.Springer;
import de.allianz.figuren.Turm;
import de.allianz.kt.spielablauf.Figur;

public class Bord
{
	String text = "";

	boolean whiteMoves = true;

	public boolean iswhiteMoves()
	{
		return whiteMoves;

	}

	public void switchmoves()
	{
		whiteMoves = !whiteMoves;
	}

	private Figur[][] feld = new Figur[8][8];
	private Figur[] toteWeiﬂe = new Figur[15];
	private Figur[] toteSchwarze = new Figur[15];
	private int schwarzz‰hl = 0;
	private int weiﬂz‰hl = 0;

	public Bord()
	{

		try
		{
			feld[0][0] = new Turm(true);
			feld[1][0] = new Springer(true);
			feld[2][0] = new Laeufer(true);
			feld[3][0] = new Dame(true);
			feld[4][0] = new Koenig(true);
			feld[5][0] = new Laeufer(true);
			feld[6][0] = new Springer(true);
			feld[7][0] = new Turm(true);

			feld[0][7] = new Turm(false);
			feld[1][7] = new Springer(false);
			feld[2][7] = new Laeufer(false);
			feld[3][7] = new Dame(false);
			feld[4][7] = new Koenig(false);
			feld[5][7] = new Laeufer(false);
			feld[6][7] = new Springer(false);
			feld[7][7] = new Turm(false);

			for (int k = 0; k < 8; k++)
			{
				feld[k][1] = new Bauer(true, true);
				feld[k][6] = new Bauer(false, true);
			}

		} catch (InvalidKoordinatenException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String brett()
	{
		text = "";
		String zeile = "";
		String zahl;
		text = text + " |A||B||C||D||E||F||G||H|" + "\n";
		for (int row = 7; row >= 0; row--)
		{
			zahl = "" + (row + 1);
			zeile = zeile + zahl;
			for (int column = 0; column < 8; column++)
			{

				if (feld[column][row] == null)
				{
					zeile = zeile + "|_|";

				} else
				{
					zeile = zeile + "|" + feld[column][row].getChar() + "|";
				}
			}
			zeile = zeile + zahl;
			text = text + zeile + "\n";
			zeile = "";
		}
		text = text + " |A||B||C||D||E||F||G||H|";
		return text;
	}

	public Figur getFigur(BrettKoordinaten bkoor)
	{
		return feld[bkoor.getSpalte()][bkoor.getZeile()];
	}

	public void setFigur(Koordinaten kor, Figur fig) throws InvalidKoordinatenException
	{
		feld[kor.getSpalte()][kor.getZeile()] = fig;
	}

	public void setgeschlagen(Figur Geschlagen)
	{
		if (Geschlagen.isWhite() == true)
		{
			toteWeiﬂe[weiﬂz‰hl] = Geschlagen;
			weiﬂz‰hl++;
		} else if (Geschlagen.isWhite() == false)
		{
			toteSchwarze[schwarzz‰hl] = Geschlagen;
			schwarzz‰hl++;
		}
		if (weiﬂz‰hl != 0)
		{
			System.out.println("Weiﬂ : " + toteWeiﬂe[weiﬂz‰hl - 1]);
		}
		if (schwarzz‰hl != 0)
		{
			System.out.println("     Schwarz:     " + toteSchwarze[schwarzz‰hl - 1]);
		}

	}

	public String getWeiﬂ(int stelle)
	{

		Figur fig = toteWeiﬂe[stelle];
		if (fig != null)
		{
			String fileName = FigurSortieren(fig);
			return fileName;
		} else
		{
			return null;
		}
	}

	public String getSchwarz(int stelle)
	{

		Figur fig = toteSchwarze[stelle];
		if (fig != null)
		{
			String fileName = FigurSortieren(fig);
			return fileName;
		} else
		{
			return null;
		}
	}

	public String FigurSortieren(Figur figur)
	{

		String fileName = null;
		switch (figur.getChar())
		{
		case 'T':
			fileName = ".\\resources\\200px-Chess_rlt45.svg.gif";
			break;
		case 'S':
			fileName = ".\\resources\\200px-Chess_nlt45.svg.gif";
			break;
		case 'L':
			fileName = ".\\resources\\200px-Chess_blt45.svg.gif";
			break;
		case 'D':
			fileName = ".\\resources\\200px-Chess_qlt45.svg.gif";
			break;
		case 'K':
			fileName = ".\\resources\\200px-Chess_klt45.svg.gif";
			break;
		case 'B':
			fileName = ".\\resources\\200px-Chess_plt45.svg.gif";
			break;
		case 't':
			fileName = ".\\resources\\200px-Chess_rdt45.svg.gif";
			break;
		case 's':
			fileName = ".\\resources\\200px-Chess_ndt45.svg.gif";
			break;
		case 'l':
			fileName = ".\\resources\\200px-Chess_bdt45.svg.gif";
			break;
		case 'd':
			fileName = ".\\resources\\200px-Chess_qdt45.svg.gif";
			break;
		case 'k':
			fileName = ".\\resources\\200px-Chess_kdt45.svg.gif";
			break;
		case 'b':
			fileName = ".\\resources\\200px-Chess_pdt45.svg.gif";
			break;

		}
		return fileName;
	}

}
