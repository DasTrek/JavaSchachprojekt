package de.allianz.kt.spielfeld;

public class Koordinaten
{
	protected int zeile;
	protected int spalte;

	public Koordinaten()
	{
		super();
	}

	public Koordinaten(int spalte, int zeile)
	{
		this.spalte = spalte;
		this.zeile = zeile;
	}

	public int getZeile()
	{
		return zeile;
	}

	public int getSpalte()
	{
		return spalte;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + spalte;
		result = prime * result + zeile;
		return result;
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
		Koordinaten other = (Koordinaten) obj;
		if (spalte != other.spalte)
			return false;
		if (zeile != other.zeile)
			return false;
		return true;
	}
	
	

}
