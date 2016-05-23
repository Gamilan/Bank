package Tests;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXException;

import hu.unideb.inf.pt.Bank.IO.IOHandler;
import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Szamla.FizetesiOsztaly;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;

public class IOTest {

	List<Szamla> szamlaLista = new ArrayList<Szamla>();
	List<Szamla> tesztLista = null;
	List<Tranzakcio> tranzakcioLista = new ArrayList<Tranzakcio>();
	List<Tranzakcio> tesztTranzakcioLista = null;
	
	Szamla szamla1 = new Szamla("1", 1000, 0, "Kis Áron", LocalDate.now(), LocalDate.of(1990, 2, 10), "Debrecen", "Bödöm Éva", FizetesiOsztaly.ALACSONY, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla2 = new Szamla("2", 5000, 0, "Nagy Döme", LocalDate.now(), LocalDate.of(1986, 9, 1), "Debrecen", "Veres Erika", FizetesiOsztaly.KOZEPES, LocalDate.now(), new ArrayList<Tranzakcio>());
	Szamla szamla3 = new Szamla("3", 10000, 0, "Névtér Gertrúd", LocalDate.now(), LocalDate.of(1963, 11, 25), "Debrecen", "Balalajka Anna", FizetesiOsztaly.MAGAS, LocalDate.now(), new ArrayList<Tranzakcio>());
	
	Tranzakcio tranzakcio1 = new Tranzakcio(szamla3, szamla1, 500, LocalDate.now());
	Tranzakcio tranzakcio2 = new Tranzakcio(szamla2, szamla3, 200, LocalDate.now());
	
	String homeKonyvtar = System.getProperty("user.home");
	File homeFile = new File(homeKonyvtar);
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder(homeFile);
	
	@Test
	public void exportSzamlaTeszt1() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		szamlaLista.add(szamla1);
		szamlaLista.add(szamla2);
		szamlaLista.add(szamla3);
		szamlaLista.add(szamla1);
		File konyvtarFile = folder.newFolder("sub");
		
		IOHandler.exportTranzakcio(konyvtarFile, IOHandler.createTranzakcioList(szamlaLista));
		IOHandler.exportSzamla(konyvtarFile, szamlaLista);
		tesztLista = IOHandler.importSzamlak(new File(konyvtarFile, "Szamlak.xml"));
		assertNotEquals(szamlaLista, tesztLista);
		
	}
	
	@Test
	public void exportSzamlaTeszt2() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		szamlaLista.clear();
		szamlaLista.add(szamla1);
		szamlaLista.add(szamla2);
		szamlaLista.add(szamla3);
		File konyvtarFile = folder.newFolder("sub");

		
		IOHandler.exportTranzakcio(konyvtarFile, IOHandler.createTranzakcioList(szamlaLista));
		IOHandler.exportSzamla(konyvtarFile, szamlaLista);
		tesztLista = IOHandler.importSzamlak(new File(konyvtarFile, "Szamlak.xml"));
		assertEquals(szamlaLista, tesztLista);
		
	}
	
	@Test
	public void exportTranzakcioTeszt2() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		szamlaLista.add(szamla1);
		szamlaLista.add(szamla2);
		szamlaLista.add(szamla3);
		tranzakcio1.getUtalo().addTranzakcio(tranzakcio1);
		tranzakcio1.getFogado().addTranzakcio(tranzakcio1);
		tranzakcio2.getUtalo().addTranzakcio(tranzakcio2);
		tranzakcio2.getFogado().addTranzakcio(tranzakcio2);
		tranzakcioLista.add(tranzakcio1);
		tranzakcioLista.add(tranzakcio2);
		File konyvtarFile = folder.newFolder("sub");

		
		IOHandler.exportTranzakcio(konyvtarFile, IOHandler.createTranzakcioList(szamlaLista));
		IOHandler.exportSzamla(konyvtarFile, szamlaLista);
		tesztTranzakcioLista = IOHandler.importTranzakciok(new File(konyvtarFile, "Tranzakciok.xml"), szamlaLista);
		Set<Tranzakcio> tranzakcioSet = new HashSet<Tranzakcio>();
		tranzakcioSet.addAll(tranzakcioLista);
		Set<Tranzakcio> tesztTranzakcioSet = new HashSet<Tranzakcio>();
		tesztTranzakcioSet.addAll(tesztTranzakcioLista);
		assertEquals(tranzakcioSet, tesztTranzakcioSet);
	}

}
