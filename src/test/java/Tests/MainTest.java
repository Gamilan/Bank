package Tests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hu.unideb.inf.pt.Bank.Muveletek;
import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;
import hu.unideb.inf.pt.Bank.model.Szamla.FizetesiOsztaly;

public class MainTest {

	Szamla szamla1 = new Szamla("1", 1000, 0, "Kis Áron", LocalDate.now(), LocalDate.of(1990, 2, 10), "Debrecen", "Bödöm Éva", FizetesiOsztaly.ALACSONY, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla2 = new Szamla("2", 5000, 0, "Nagy Döme", LocalDate.now(), LocalDate.of(1986, 9, 1), "Debrecen", "Veres Erika", FizetesiOsztaly.KOZEPES, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla3 = new Szamla("3", 10000, 0, "Névtér Gertrúd", LocalDate.now(), LocalDate.of(1963, 11, 25), "Debrecen", "Balalajka Anna", FizetesiOsztaly.MAGAS, LocalDate.now(), new ArrayList<Tranzakcio>());

	Tranzakcio tranzakcio = new Tranzakcio(szamla1, szamla3, 100, LocalDate.now());
	List<Szamla> szamlak = new ArrayList<Szamla>();
	
	@Test
	public void addTeszt() {
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla1));
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla2));
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla3));
		assertEquals(false, Muveletek.addSzamlak(szamlak, szamla1));
	}
	
	@Test
	public void torolTeszt(){
		szamlak.clear();
		szamla1.addTranzakcio(tranzakcio);
		szamla3.addTranzakcio(tranzakcio);
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla1));
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla2));
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla3));
		Muveletek.torol(szamlak, szamla1);
		List<Tranzakcio> tranzakcioList = new ArrayList<Tranzakcio>();
		assertEquals(tranzakcioList, szamla3.getTranzakciok());
	}
	
	@Test
	public void adoztatTeszt(){
		szamlak.clear();
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla1));
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla2));
		assertEquals(true, Muveletek.addSzamlak(szamlak, szamla3));
		Muveletek.adoztat(szamlak);
		assertEquals(0, szamla1.getEgyenleg(), 0.1);
		assertEquals(19878.1, szamla1.getElmaradas(), 0.1);
		assertEquals(0, szamla2.getEgyenleg(), 0.1);
		assertEquals(22716, szamla2.getElmaradas(), 0.1);
		assertEquals(0, szamla3.getEgyenleg(), 0.1);
		assertEquals(30950, szamla3.getElmaradas(), 0.1);
	}

}
