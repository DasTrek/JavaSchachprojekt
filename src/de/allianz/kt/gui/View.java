package de.allianz.kt.gui;

import java.util.ArrayList;
import java.util.List;

/* 
 * example snippet: Hello World
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.allianz.figuren.Laeufer;
import de.allianz.kt.spielablauf.Figur;
import de.allianz.kt.spielfeld.Bord;
import de.allianz.kt.spielfeld.BrettKoordinaten;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;
import de.allianz.kt.spielfeld.Koordinaten;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

public class View
{
	private boolean spielbeendet = false;
	private Bord board = null;
	private Display display = new Display();
	private Shell shell = new Shell(display);
	private final Canvas[][] array = new Canvas[size][size];
	private Color COLOR_LIGHT = new Color(display, 253, 245, 230);
	private Color COLOR_DARK = new Color(display, 82, 139, 139);
	private Color COLOR_RED = new Color(display, 139, 26, 26);
	private Color COLOR_GREEN = new Color(display, 162, 205, 90);
	private Color COLOR_GOLD = new Color(display, 218, 165, 32);
	public static final int size = 8;
	public int coordinateKlicked = 0;
	private boolean klick = true;
	private BrettKoordinaten ersterKlick;
	private BrettKoordinaten zweiterKlick;
	boolean rochadenFarbe = false;
	int rochadenZahl;
	private Text t;
	int ausgabe = 0;

	Figur ersteFigur;
	private List<BrettKoordinaten> bewegungsliste = new ArrayList<>();

	public View(Bord board)
	{
		this.board = board;
	}

	public void init() throws InvalidKoordinatenException
	{/* Shellgr��e setzen */
//		shell.setSize(1600, 1000);
		shell.setBounds(150, 25, 1600, 1000);
		Font fontBorder = new Font(display, "Courier New", 20, SWT.BOLD);
		t = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		t.setBounds(1000, 400, 500, 200);
		 Image small = new Image(display,".\\resources\\Icon.gif");
		 shell.setImage(small);
		char buchstabe;
		BrettKoordinaten G8 = new BrettKoordinaten("G8");

		for (int column = 0; column < size; column++)
		{
			for (int row = 0; row < size; row++)
			{
				array[column][row] = new Canvas(shell, SWT.BORDER);
				array[column][row].setBounds(column * 100 + 100, (8 - row) * 100, 100, 100);
				array[column][row].addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseDown(MouseEvent e)
					{
						Canvas canvas = (Canvas) e.getSource();
						for (int column = 0; column < size; column++)
						{
							for (int row = 0; row < size; row++)
							{
								if (array[column][row] == canvas)
								{
									System.out.println("Sie haben auf " + column + " " + row + " geklickt");
									try
									{
										BrettKoordinaten brettkoor = new BrettKoordinaten(column, row);
										ersteFigur = board.getFigur(brettkoor);
										if (spielbeendet == false)
										{
											if (klick == true)
											{

												if (ersteFigur == null)
												{
													return;
												} else if (ersteFigur.isWhite() != board.iswhiteMoves())
												{
													return;
												} else
												{

													ersterKlick = brettkoor;
													bewegungsliste = ersteFigur.bewegungsregeln(ersterKlick, board);
													rochadenZahl = 0;
													Bauern(ersteFigur);
													könig(ersteFigur);
													rochadenZahl = Rochade(ersteFigur);
													klick = false;
												}

											} else
											{
												Figur zweiteFigur = board.getFigur(brettkoor);
												if (zweiteFigur != null)
												{
													Figur startFigur = board.getFigur(ersterKlick);

													if (zweiteFigur.isWhite() == startFigur.isWhite())
													{
														klick = true;
														ersterKlick = null;
														zweiterKlick = null;
														return;
													} else
													{

														if (bewegungsliste.contains(brettkoor))
														{
															zweiterKlick = brettkoor;
															klick = true;

															übergabe();
															// bewegungsliste.clear();

															draw();

														}

													}

												} else
												{

													if (bewegungsliste.contains(brettkoor))
													{
														zweiterKlick = brettkoor;
														klick = true;
														übergabe();
														// bewegungsliste.clear();

														draw();

													}

												}
											}
										}
									} catch (InvalidKoordinatenException e1)
									{
										e1.printStackTrace();
									}
								}
							}

						}
						draw();
					}
				});

				Label links = new Label(shell, SWT.NONE);
				Label rechts = new Label(shell, SWT.NONE);
				Label oben = new Label(shell, SWT.NONE);
				Label unten = new Label(shell, SWT.NONE);
				buchstabe = (char) (row + 65);
				oben.setBounds(140 + (row * 100), 50, 50, 25);
				oben.setFont(fontBorder);
				oben.setText(String.valueOf(buchstabe));
				unten.setBounds(140 + (row * 100), 930, 50, 25);
				unten.setFont(fontBorder);
				unten.setText(String.valueOf(buchstabe));
				links.setBounds(40, 130 + (row * 100), 50, 25);
				links.setFont(fontBorder);
				links.setText(String.valueOf(8 - row));
				rechts.setBounds(940, 130 + (row * 100), 50, 25);
				rechts.setFont(fontBorder);
				rechts.setText(String.valueOf(8 - row));
			}
		}
		shell.addPaintListener(new PaintListener()
		{

			public void paintControl(PaintEvent e)
			{
				int ZahlWeiß = 0;
				int ZahlSchwarz = 0;
				for (int i = 0; i < 15; i++)
				{
					String weißerName = board.getWeiß(i);
					String schwarzerName = board.getSchwarz(i);
					// e.gc.setBackground(COLOR_RED);
					// e.gc.fillRectangle(1000, 100, 100, 100);
					if (weißerName != null)
					{
						if (ZahlWeiß == 5)
						{
							ZahlWeiß = 0;
						}
						System.out.println("Fehler weiß ausgabe" + weißerName);
						Image weiß = new Image(display, weißerName);
						if (i < 5)
						{
							Image weißScaled = new Image(display, weiß.getImageData().scaledTo(100, 100));
							e.gc.drawImage(weißScaled, 1000 + ZahlWeiß * 100, 100);
						} else if (i < 10)
						{
							Image weißScaled = new Image(display, weiß.getImageData().scaledTo(100, 100));
							e.gc.drawImage(weißScaled, 1000 + ZahlWeiß * 100, 200);
						} else
						{
							Image weißScaled = new Image(display, weiß.getImageData().scaledTo(100, 100));
							e.gc.drawImage(weißScaled, 1000 + ZahlWeiß * 100, 300);
						}
						ZahlWeiß++;

					}
					if (schwarzerName != null)
					{
						System.out.println("Fehler schwarz ausgabe" + schwarzerName);
						if (ZahlSchwarz == 5)
						{
							ZahlSchwarz = 0;
						}
						Image schwarz = new Image(display, schwarzerName);
						if (i < 5)
						{
							Image schwarzScaled = new Image(display, schwarz.getImageData().scaledTo(100, 100));
							e.gc.drawImage(schwarzScaled, 1000 + ZahlSchwarz * 100, 800);
						} else if (i < 10)
						{
							Image schwarzScaled = new Image(display, schwarz.getImageData().scaledTo(100, 100));
							e.gc.drawImage(schwarzScaled, 1000 + ZahlSchwarz * 100, 700);
						} else
						{
							Image schwarzScaled = new Image(display, schwarz.getImageData().scaledTo(100, 100));
							e.gc.drawImage(schwarzScaled, 1000 + ZahlSchwarz * 100, 600);
						}
						ZahlSchwarz++;
					}

				}

			}

		});

		draw();
		shell.open();

		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/** Zeichnet die View immer wieder */
	public void draw()
	{
		BrettKoordinaten G8 = null;
		BrettKoordinaten C8 = null;
		BrettKoordinaten G1 = null;
		BrettKoordinaten C1 = null;
		try
		{
			G8 = new BrettKoordinaten("G8");
			C8 = new BrettKoordinaten("C8");
			G1 = new BrettKoordinaten("G1");
			C1 = new BrettKoordinaten("C1");

		} catch (InvalidKoordinatenException e1)
		{
			e1.printStackTrace();
		}

		for (int column = 0; column < size; column++)
		{

			for (int row = 0; row < size; row++)
			{
				BrettKoordinaten from1 = null;
				try
				{
					from1 = new BrettKoordinaten(column, row);
				} catch (InvalidKoordinatenException e)
				{
					e.printStackTrace();
					System.exit(-2);
				}

				Image imageOld = array[column][row].getBackgroundImage();
				if (imageOld != null)
				{
					imageOld.dispose();
				}
				array[column][row].setBackgroundImage(null);
				array[column][row].setBackground(null);

				Figur figur = board.getFigur(from1);
				String fileName = null;

				if (figur != null)
				{
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

					if (fileName != null)
					{
						Image image = new Image(display, fileName);
						Image imageScaled = new Image(display, image.getImageData().scaledTo(100, 100));

						if (klick == false && from1.equals(ersterKlick))
						{

							imageScaled.setBackground(COLOR_GREEN);
						} else if (klick == false && bewegungsliste.contains(from1))
						{
							array[column][row].setBackground(COLOR_GREEN);
							imageScaled.setBackground(COLOR_RED);
						} else if (column % 2 == row % 2)
						{
							imageScaled.setBackground(COLOR_DARK);
						} else
						{
							imageScaled.setBackground(COLOR_LIGHT);
						}

						array[column][row].setBackgroundImage(imageScaled);

					}

				} else
				{

					if (klick == false && bewegungsliste.contains(from1))
					{
						array[column][row].setBackground(COLOR_GREEN);

					} else if (column % 2 == row % 2)
					{
						array[column][row].setBackground(COLOR_DARK);
					} else
					{
						array[column][row].setBackground(COLOR_LIGHT);
					}

				}

				switch (rochadenZahl)
				{
				case 1:
					array[6][7].setBackground(COLOR_GOLD);
					break;
				case 2:
					array[6][0].setBackground(COLOR_GOLD);
					break;
				case 3:
					array[2][7].setBackground(COLOR_GOLD);
					break;
				case 4:
					array[2][0].setBackground(COLOR_GOLD);
					break;
				case 0:
					
					break;

				}

			}

		}
	}

	public void übergabe() throws InvalidKoordinatenException
	{
		BrettKoordinaten linkerSchwarzerTurm = new BrettKoordinaten("A8");
		BrettKoordinaten rechterSchwarzerTurm = new BrettKoordinaten("H8");
		BrettKoordinaten C8 = new BrettKoordinaten("C8");
		BrettKoordinaten D8 = new BrettKoordinaten("D8");
		BrettKoordinaten F8 = new BrettKoordinaten("F8");
		BrettKoordinaten G8 = new BrettKoordinaten("G8");
		BrettKoordinaten linkerWeißerTurm = new BrettKoordinaten("A1");
		BrettKoordinaten rechterWeißerTurm = new BrettKoordinaten("H1");
		BrettKoordinaten C1 = new BrettKoordinaten("C1");
		BrettKoordinaten D1 = new BrettKoordinaten("D1");
		BrettKoordinaten F1 = new BrettKoordinaten("F1");
		BrettKoordinaten G1 = new BrettKoordinaten("G1");

		try
		{

			Figur figur = board.getFigur(ersterKlick);
			boolean farbAbfrage = figur.isWhite();
			Figur geschlagen = board.getFigur(zweiterKlick);
			String geschlagenName = null;
			Bauern(figur);
			könig(figur);
			System.out.println(bewegungsliste);
			if (bewegungsliste.contains(zweiterKlick))
			{
				if (figur.getChar() == 'b' || figur.getChar() == 'B')
				{
					figur.setErsterBauernZug(figur);
				}

				if (geschlagen != null)
				{
					geschlagenName = board.getFigur(zweiterKlick).toString();
					if (geschlagen.getChar() == 'k')
					{
						t.insert("Weiß hat gewonnen");
						spielbeendet = true;
						// System.exit(-1);
					} else if (geschlagen.getChar() == 'K')
					{
						t.insert("Schwarz hat gewonnen");
						spielbeendet = true;

						// System.exit(-1);
					} else
					{
						board.setgeschlagen(geschlagen);
					}

				}

				if (figur.getChar() == 'k' || figur.getChar() == 'K')
				{
					figur.setErsterKönigsZug();
				}

				if (figur.getChar() == 'k' && zweiterKlick.equals(G8))
				{
					board.setFigur(F8, board.getFigur(rechterSchwarzerTurm));
					board.setFigur(rechterSchwarzerTurm, null);
				} else if (figur.getChar() == 'k' && zweiterKlick.equals(C8))
				{
					board.setFigur(D8, board.getFigur(linkerSchwarzerTurm));
					board.setFigur(linkerSchwarzerTurm, null);
				}
				if (figur.getChar() == 'K' && zweiterKlick.equals(G1))
				{
					board.setFigur(F1, board.getFigur(rechterWeißerTurm));
					board.setFigur(rechterWeißerTurm, null);
				} else if (figur.getChar() == 'K' && zweiterKlick.equals(C1))
				{
					board.setFigur(D1, board.getFigur(linkerWeißerTurm));
					board.setFigur(linkerWeißerTurm, null);
				}

				shell.redraw();
				shell.update();
				String farbe;
				String geschlagenFarbe;
				if (farbAbfrage == true)
				{
					farbe = "Weißer";
					geschlagenFarbe = "schwarzen";

				} else
				{
					farbe = "Schwarzer";
					geschlagenFarbe = "weißen";
				}
				String name = board.getFigur(ersterKlick).toString();
				board.setFigur(zweiterKlick, figur);
				board.setFigur(ersterKlick, null);
				if (spielbeendet == false)
				{
					if (geschlagenName != null)
					{
						t.insert(farbe + " " + name + " schlägt " + geschlagenFarbe + " " + geschlagenName + " von " + ersterKlick + " nach " + zweiterKlick + "\n");
					} else
					{
						t.insert(farbe + " " + name + " zieht von " + ersterKlick + " nach " + zweiterKlick + "\n");
					}
				}

				board.switchmoves();

			}

		} catch (InvalidKoordinatenException e1)
		{
			e1.printStackTrace();
		}

	}

	public void Bauern(Figur figur)
	{
		if (figur != null)
		{
			if (figur.getChar() == 'b' || figur.getChar() == 'B')
			{
				if (bewegungsliste.isEmpty() == false)
				{
					System.out.println(bewegungsliste);
					if (board.getFigur(bewegungsliste.get(0)) != null)
					{

						bewegungsliste.set(0, ersterKlick);
					}

					if (board.getFigur(bewegungsliste.get(1)) == null)
					{
						bewegungsliste.set(1, bewegungsliste.get(0));
					}

					if (board.getFigur(bewegungsliste.get(2)) == null)
					{
						bewegungsliste.set(2, bewegungsliste.get(0));
					}
							
					if (board.getFigur(bewegungsliste.get(bewegungsliste.size()-1)) != null)
					{
						bewegungsliste.set(bewegungsliste.size()-1, bewegungsliste.get(0));
						System.out.println(bewegungsliste.lastIndexOf(bewegungsliste.get(0)));

					}
				}
			}
		}
	}

	public void könig(Figur figur) throws InvalidKoordinatenException
	{
		BrettKoordinaten linkerSchwarzerTurm = new BrettKoordinaten("A8");
		BrettKoordinaten rechterSchwarzerTurm = new BrettKoordinaten("H8");
		BrettKoordinaten B8 = new BrettKoordinaten("B8");
		BrettKoordinaten C8 = new BrettKoordinaten("C8");
		BrettKoordinaten D8 = new BrettKoordinaten("D8");
		BrettKoordinaten F8 = new BrettKoordinaten("F8");
		BrettKoordinaten G8 = new BrettKoordinaten("G8");
		BrettKoordinaten linkerWeißerTurm = new BrettKoordinaten("A1");
		BrettKoordinaten rechterWeißerTurm = new BrettKoordinaten("H1");
		BrettKoordinaten B1 = new BrettKoordinaten("B1");
		BrettKoordinaten C1 = new BrettKoordinaten("C1");
		BrettKoordinaten D1 = new BrettKoordinaten("D1");
		BrettKoordinaten F1 = new BrettKoordinaten("F1");
		BrettKoordinaten G1 = new BrettKoordinaten("G1");

		if (figur != null)
		{
			if (figur.getChar() == 'k')
			{
				if (figur.getErsterKönigszug() == false && (board.getFigur(linkerSchwarzerTurm).getChar() == 't' || board.getFigur(rechterSchwarzerTurm).getChar() == 't'))
				{
					if (board.getFigur(B8) == null && board.getFigur(C8) == null && board.getFigur(D8) == null)
					{
						bewegungsliste.add(C8);

					}
					if (board.getFigur(F8) == null && board.getFigur(G8) == null)
					{
						bewegungsliste.add(G8);
					}

				}
			} else if (figur.getChar() == 'K')
			{
				if (figur.getErsterKönigszug() == false && (board.getFigur(linkerWeißerTurm).getChar() == 'T' || board.getFigur(rechterWeißerTurm).getChar() == 'T'))
				{
					if (board.getFigur(B1) == null && board.getFigur(C1) == null && board.getFigur(D1) == null)
					{
						bewegungsliste.add(C1);

					}
					if (board.getFigur(F1) == null && board.getFigur(G1) == null)
					{
						bewegungsliste.add(G1);
					}
				}

			}
		}

	}

	public int Rochade(Figur figur) throws InvalidKoordinatenException
	{

		BrettKoordinaten C8 = new BrettKoordinaten("C8");
		BrettKoordinaten G8 = new BrettKoordinaten("G8");
		BrettKoordinaten C1 = new BrettKoordinaten("C1");
		BrettKoordinaten G1 = new BrettKoordinaten("G1");

		if (board.getFigur(ersterKlick).getChar() == 'k' && bewegungsliste.contains(G8))
		{
			return 1;
		} else if (board.getFigur(ersterKlick).getChar() == 'K' && bewegungsliste.contains(G1))
		{
			return 2;
		} else if (board.getFigur(ersterKlick).getChar() == 'k' && bewegungsliste.contains(C8))
		{
			return 3;
		} else if (board.getFigur(ersterKlick).getChar() == 'K' && bewegungsliste.contains(C1))
		{
			return 4;
		} else
		{
			return 0;
		}

	}

}