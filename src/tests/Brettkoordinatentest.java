package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.allianz.kt.spielfeld.BrettKoordinaten;
import de.allianz.kt.spielfeld.InvalidKoordinatenException;

public class Brettkoordinatentest
{

	@Test
	public void testKonstruktorString()
	{
		try
		{
			BrettKoordinaten brett = new BrettKoordinaten("A1");
			int spalte = brett.getSpalte();
			int zeile = brett.getZeile();
			assertSame("Fehler bei der Spaltenberechnung" ,spalte, 0);
			assertSame("Fehler bei der Zeilenberechnung" ,zeile, 0);

		} catch (InvalidKoordinatenException e)
		{
			fail("Konnte keine BrettKoordinate erzeugen");			
		}
	}
	
	@Test
	public void testKonstruktorInt()
	{
		try
		{
			BrettKoordinaten brett = new BrettKoordinaten(0,0);
		} catch (InvalidKoordinatenException e)
		{
			fail("Konnte keine BrettKoordinate erzeugen");			
		}
	}
	
	@Test (expected = InvalidKoordinatenException.class)
	public void Exceptiontest() throws InvalidKoordinatenException
	{

			BrettKoordinaten brett = new BrettKoordinaten(9,0);

	}
	
	@Test
	public void umwandlungtest()
	{
		
		try
		{
			BrettKoordinaten brett = new BrettKoordinaten("A1");
			BrettKoordinaten brett1 = new BrettKoordinaten(0,0);
			
			assertEquals(brett, brett1);
		} catch (InvalidKoordinatenException e)
		{
			fail("Koordinaten bei gleicher Eingabe keine gleiche Rückgabe");
		}
		

	}
	

}
