package Tests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;
import hu.unideb.inf.pt.Bank.model.Szamla.FizetesiOsztaly;

public class SzamlaTest {

	Szamla szamla1 = new Szamla("1", 1000, 0, "Kis Áron", LocalDate.now(), LocalDate.of(1990, 2, 10), "Debrecen", "Bödöm Éva", FizetesiOsztaly.ALACSONY, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla2 = new Szamla("2", 5000, 0, "Nagy Döme", LocalDate.now(), LocalDate.of(1986, 9, 1), "Debrecen", "Veres Erika", FizetesiOsztaly.KOZEPES, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla3 = new Szamla("3", 10000, 0, "Névtér Gertrúd", LocalDate.now(), LocalDate.of(1963, 11, 25), "Debrecen", "Balalajka Anna", FizetesiOsztaly.MAGAS, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla4;
	
	@Test
	public void kezelesiDijTeszt1(){
		assertEquals(30, szamla1.kezelesiDij(1000), 0.1);
	}
	
	@Test
	public void kezelesiDijTeszt2(){
		assertEquals(40, szamla2.kezelesiDij(1000), 0.1);
	}
	
	@Test
	public void kezelesiDijTeszt3(){
		assertEquals(50, szamla3.kezelesiDij(1000), 0.1);
	}
	
	@Test
	public void levonTeszt1() {
		assertEquals(true, szamla1.levonhatE(500));
		szamla1.levon(500);
		assertEquals(485, szamla1.getEgyenleg(), 0.1);
	}
	
	@Test
	public void levonTeszt2() {
		assertEquals(true, szamla2.levonhatE(500));
		szamla2.levon(500);
		assertEquals(4480, szamla2.getEgyenleg(), 0.1);
	}
	
	@Test
	public void levonTeszt3() {
		assertEquals(true, szamla3.levonhatE(500));
		szamla3.levon(500);
		assertEquals(9475, szamla3.getEgyenleg(), 0.1);
	}

	@Test
	public void levonTeszt4(){
		szamla1.setEgyenleg(1000);
		assertEquals(false, szamla1.levonhatE(1500));
		szamla1.levon(1500);
		assertEquals(0.0, szamla1.getEgyenleg(), 0.1);
		assertEquals(545, szamla1.getElmaradas(), 0.1);
		assertEquals(false, szamla1.levonhatE(1000));
		szamla1.levon(1000);
		assertEquals(0.0, szamla1.getEgyenleg(), 0.1);
		assertEquals(1575, szamla1.getElmaradas(), 0.1);
		szamla1.setElmaradas(0.0);
	}
	
	@Test
	public void feltoltTeszt1(){
		szamla1.setEgyenleg(1000);
		szamla1.feltolt(500);
		assertEquals(1485, szamla1.getEgyenleg(), 0.1);
	}
	
	@Test
	public void feltoltTeszt2(){
		szamla2.setEgyenleg(5000);
		szamla2.feltolt(500);
		assertEquals(5480, szamla2.getEgyenleg(), 0.1);
	}
	
	@Test
	public void feltoltTeszt3(){
		szamla3.setEgyenleg(10000);
		szamla3.feltolt(500);
		assertEquals(10475, szamla3.getEgyenleg(), 0.1);
	}
	
	@Test
	public void feltoltTeszt4(){
		szamla1.setEgyenleg(1000);
		assertEquals(false, szamla1.levonhatE(1500));
		szamla1.levon(1500);
		assertEquals(0.0, szamla1.getEgyenleg(), 0.1);
		assertEquals(545, szamla1.getElmaradas(), 0.1);
		szamla1.feltolt(1000);
		assertEquals(0.0, szamla1.getElmaradas(), 0.1);
		assertEquals(425, szamla1.getEgyenleg(), 0.1);
	}
	
	@Test
	public void feltoltTeszt5(){
		szamla1.setEgyenleg(0);
		szamla1.setElmaradas(500);
		szamla1.feltolt(200);
		assertEquals(0, szamla1.getEgyenleg(), 0.1);
		assertEquals(306, szamla1.getElmaradas(), 0.1);
	}
	
	@Test
	public void utalTeszt1(){
		szamla1.setEgyenleg(1000);
		szamla2.setEgyenleg(5000);
		assertEquals(true, szamla1.utal(szamla2, 500));
		assertEquals(485, szamla1.getEgyenleg(), 0.1);
		assertEquals(5480, szamla2.getEgyenleg(), 0.1);
	}
	
	@Test
	public void utalTeszt2(){
		szamla1.setEgyenleg(1000);
		szamla2.setEgyenleg(5000);
		assertEquals(true, szamla2.utal(szamla1, 500));
		assertEquals(1485, szamla1.getEgyenleg(), 0.1);
		assertEquals(4480, szamla2.getEgyenleg(), 0.1);
	}
	
	@Test
	public void utalTeszt3(){
		szamla1.setEgyenleg(1000);
		szamla3.setEgyenleg(10000);
		assertEquals(true, szamla1.utal(szamla3, 500));
		assertEquals(485, szamla1.getEgyenleg(), 0.1);
		assertEquals(10475, szamla3.getEgyenleg(), 0.1);
	}
	
	@Test
	public void utalTeszt4(){
		szamla1.setEgyenleg(1000);
		szamla3.setEgyenleg(10000);
		assertEquals(true, szamla3.utal(szamla1, 500));
		assertEquals(1485, szamla1.getEgyenleg(), 0.1);
		assertEquals(9475, szamla3.getEgyenleg(), 0.1);
	}
	
	@Test
	public void utalTeszt5(){
		szamla2.setEgyenleg(5000);
		szamla3.setEgyenleg(10000);
		assertEquals(true, szamla2.utal(szamla3, 500));
		assertEquals(4480, szamla2.getEgyenleg(), 0.1);
		assertEquals(10475, szamla3.getEgyenleg(), 0.1);
	}
	
	@Test
	public void utalTeszt6(){
		szamla2.setEgyenleg(5000);
		szamla3.setEgyenleg(10000);
		assertEquals(true, szamla3.utal(szamla2, 500));
		assertEquals(5480, szamla2.getEgyenleg(), 0.1);
		assertEquals(9475, szamla3.getEgyenleg(), 0.1);
	}
	
	@Test(expected=NullPointerException.class)
	public void utalTesztException(){
		szamla1.setEgyenleg(1000);
		assertEquals(true, szamla1.utal(szamla4, 500));
	}
	
	@Test
	public void utalFalseTeszt1(){
		szamla1.setEgyenleg(1000);
		szamla2.setEgyenleg(5000);
		szamla1.setElmaradas(0);
		szamla2.setElmaradas(0);
		assertEquals(false, szamla1.utal(szamla2, 1500));
		assertEquals(1000, szamla1.getEgyenleg(), 0.1);
		assertEquals(5000, szamla2.getEgyenleg(), 0.1);
		assertEquals(0, szamla1.getElmaradas(), 0.1);
		assertEquals(0, szamla2.getElmaradas(), 0.1);
	}
	
	@Test
	public void utalFalseTeszt2(){
		szamla1.setEgyenleg(1000);
		assertEquals(false, szamla1.utal(szamla1, 1000));
	}
	
	@Test
	public void levonTrueTeszt(){
		szamla1.setEgyenleg(1000);
		szamla1.setElmaradas(0);
		assertEquals(true, szamla1.levonhatE(970.87));
		assertEquals(false, szamla1.levonhatE(971.5));
	}
	
	@Test
	public void utolsoModositasTeszt(){
		szamla1.setEgyenleg(1000);
		assertEquals(LocalDate.now(), szamla1.getUtolsoModositas());
	}
	
	@Test
	public void adozikTeszt1(){
		szamla1.setEgyenleg(50000);
		assertEquals(true, szamla1.adozik());
		assertEquals(15495, szamla1.getEgyenleg(), 0.1);
	}
	
	@Test
	public void adozikTeszt2(){
		szamla2.setEgyenleg(60000);
		assertEquals(true, szamla2.adozik());
		assertEquals(13408, szamla2.getEgyenleg(), 0.1);
	}
	
	@Test
	public void adozikTeszt3(){
		szamla3.setEgyenleg(100000);
		assertEquals(true, szamla3.adozik());
		assertEquals(21250, szamla3.getEgyenleg(), 0.1);
	}
	
	@Test
	public void adozikTeszt4(){
		szamla1.setEgyenleg(10000);
		assertEquals(false, szamla1.adozik());
		assertEquals(0, szamla1.getEgyenleg(), 0.1);
		assertEquals(13381, szamla1.getElmaradas(), 0.1);
	}
	
	@Test
	public void equalsTeszt(){
		assertEquals(true, szamla1.equals(szamla1));
	}
	
	@Test
	public void equalsNotTeszt(){
		assertEquals(false, szamla1.equals(szamla2));
	}
}
