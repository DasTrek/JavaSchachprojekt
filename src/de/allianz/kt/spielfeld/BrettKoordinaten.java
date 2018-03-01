package de.allianz.kt.spielfeld;

public class BrettKoordinaten extends Koordinaten
{
	private char spaltenchar;
	String umw = "";
	boolean test;
	boolean test1;
	InvalidKoordinatenException exception;
	public BrettKoordinaten(String eingabe) throws InvalidKoordinatenException 
	{
		
		super();

		String regex = "[A-H]{1}[1-8]{1}";
		String regex1 = "[a-h]{1}[1-8]{1}";
		test = eingabe.matches(regex);
		test1 = eingabe.matches(regex1);
		if (test == true || test1 == true) 
		{
			this.umw = eingabe.substring(0, 1);
			umw = umw.toUpperCase();
			this.spaltenchar = umw.charAt(0);
			this.zeile = Integer.parseInt(eingabe.substring(1));

			switch (spaltenchar)
			{
			case 'A':
				spalte = 0;
				break;
			case 'B':
				spalte = 1;
				break;
			case 'C':
				spalte = 2;
				break;
			case 'D':
				spalte = 3;
				break;
			case 'E':
				spalte = 4;
				break;
			case 'F':
				spalte = 5;
				break;
			case 'G':
				spalte = 6;
				break;
			case 'H':
				spalte = 7;
				break;
			}

			zeile = zeile - 1;
		}
		else 
		{
			throw new InvalidKoordinatenException("Falsche Eingabe" + eingabe);
		}
	}
	public BrettKoordinaten(int spalte, int zeile) throws InvalidKoordinatenException 
	{
		if (spalte >= 0 && spalte <8 && zeile >= 0 && zeile <8)
		{
		this.spalte = spalte;
		this.zeile = zeile;
		}
		else throw new InvalidKoordinatenException("HI" + spalte + "test" + zeile );
		{
		}
	}
	
	@Override
	public String toString()
	{
		char rueckgabe = ' ';
		
		switch (spalte)
		{
		case 0:
			rueckgabe = 'A';
			break;
		case 1:
			rueckgabe = 'B';
			break;
		case 2:
			rueckgabe = 'C';
			break;
		case 3:
			rueckgabe = 'D';
			break;
		case 4:
			rueckgabe = 'E';
			break;
		case 5:
			rueckgabe = 'F';
			break;
		case 6:
			rueckgabe = 'G';
			break;
		case 7:
			rueckgabe = 'H';
			break;
		}
		int rzeile = zeile +1;
		return rueckgabe + "" + rzeile;
		
		
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BrettKoordinaten other = (BrettKoordinaten) obj;
		if (spalte != other.spalte)
			return false;
		if (zeile != other.zeile)
			return false;
		return true;
	}

	

}
