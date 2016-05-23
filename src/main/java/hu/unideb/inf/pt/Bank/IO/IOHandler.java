package hu.unideb.inf.pt.Bank.IO;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;
import hu.unideb.inf.pt.Bank.model.Szamla.FizetesiOsztaly;


/**
 * Kezeli az exportokat és importokat XML-ből.
 * 
 * @author gamilan
 */
public class IOHandler {
	
	/**
	 * Alapértelmezett konstruktor.
	 */
	public IOHandler(){
		
	}
	
	/**
	 * Az összes tranzakciót kigyűjti egy listába duplikációk nélkül.
	 * 
	 * @param szamlak azok a számlák, melyek tranzakcióit ki akarjuk gyűjteni.
	 * @return egy lista, melynek elemei a számlákhoz tartozó tranzakciók duplikációk nélkül.
	 */
	public static List<Tranzakcio> createTranzakcioList(List<Szamla> szamlak){
		Set<Tranzakcio> tranzakcioSet = new HashSet<Tranzakcio>();
		
		List<Tranzakcio> tranzakcioList = new ArrayList<Tranzakcio>();
		for (Szamla currSzamla : szamlak){
			for (Tranzakcio currTranzakcio : currSzamla.getTranzakciok()){
				tranzakcioSet.add(currTranzakcio);
			}
		}
		tranzakcioList.addAll(tranzakcioSet);
		return tranzakcioList;
	}
	
	/**
	 * A megadott tranzakciók exportálása egy adott fájlba.
	 * A fájl elérési útja a kimenetEleres/Tranzakciok.xml lesz.
	 * 
	 * @param kimenetEleres az elérési út a fájl neve nélkül.
	 * @param tranzakciok azok a tranzakciók, melyeket exportálni akarunk a fájlba.
	 * @throws ParserConfigurationException konfigurációs hiba.
	 * @throws TransformerException a transzformáció során előálló kivétel.
	 */
	public static void exportTranzakcio(File kimenetEleres, List<Tranzakcio> tranzakciok) throws ParserConfigurationException, TransformerException{
		
		DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
		
		Document doc = dBuilder.newDocument();
		
		Element root = doc.createElement("tranzakciok");
		doc.appendChild(root);
		
		
		
		for (Tranzakcio currTranzakcio : tranzakciok){
			Element tranzakcio = doc.createElement("tranzakcio");
			
			root.appendChild(tranzakcio);
			
			Element utalo = doc.createElement("utalo");
			utalo.appendChild(doc.createTextNode(currTranzakcio.getUtalo().getSzamlaSzam()));
			tranzakcio.appendChild(utalo);
			
			Element fogado = doc.createElement("fogado");
			fogado.appendChild(doc.createTextNode(currTranzakcio.getFogado().getSzamlaSzam()));
			tranzakcio.appendChild(fogado);
			
			Element osszeg = doc.createElement("osszeg");
			osszeg.appendChild(doc.createTextNode(Double.toString(currTranzakcio.getOsszeg())));
			tranzakcio.appendChild(osszeg);
			
			Element datum = doc.createElement("datum");
			datum.appendChild(doc.createTextNode(currTranzakcio.getDatum().toString()));
			tranzakcio.appendChild(datum);
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(kimenetEleres, "Tranzakciok.xml"));

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(3));
		transformer.transform(source, result);
	}
	
	/**
	 * A megadott számlák exportálása egy adott fájlba.
	 * A fájl elérési útja a kimenetEleres/Szamlak.xml lesz.
	 * 
	 * @param kimenetEleres az elérési út a fájl neve nélkül.
	 * @param szamlak azok a számlák, melyeket exportálni akarunk a fájlba.
	 * @throws ParserConfigurationException konfigurációs hiba.
	 * @throws TransformerException a transzformáció során előálló kivétel.
	 */
	public static void exportSzamla(File kimenetEleres, List<Szamla> szamlak) throws ParserConfigurationException, TransformerException{

		DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
		
		Document doc = dBuilder.newDocument();
		
		Element root = doc.createElement("szamlak");
		doc.appendChild(root);
		
		for (Szamla currSzamla : szamlak){
			Element szamla = doc.createElement("szamla");
			root.appendChild(szamla);
			
			Element szamlaszam = doc.createElement("szamlaszam");
			szamlaszam.appendChild(doc.createTextNode(currSzamla.getSzamlaSzam()));
			szamla.appendChild(szamlaszam);
			
			Element egyenleg = doc.createElement("egyenleg");
			egyenleg.appendChild(doc.createTextNode(Double.toString(currSzamla.getEgyenleg())));
			szamla.appendChild(egyenleg);
			
			Element elmaradas = doc.createElement("elmaradas");
			elmaradas.appendChild(doc.createTextNode(Double.toString(currSzamla.getElmaradas())));
			szamla.appendChild(elmaradas);
			
			Element tulajdonos = doc.createElement("tulajdonos");
			tulajdonos.appendChild(doc.createTextNode(currSzamla.getTulajdonos()));
			szamla.appendChild(tulajdonos);
			
			Element letrehozasDatuma = doc.createElement("letrehozasDatuma");
			letrehozasDatuma.appendChild(doc.createTextNode(currSzamla.getLetrehozasDatum().toString()));
			szamla.appendChild(letrehozasDatuma);
			
			Element tulajdonosSzuletese = doc.createElement("tulajdonosSzuletese");
			tulajdonosSzuletese.appendChild(doc.createTextNode(currSzamla.getTulajdonosSzuletese().toString()));
			szamla.appendChild(tulajdonosSzuletese);
			
			Element tulajdonosSzuletesiHely = doc.createElement("tulajdonosSzuletesiHely");
			tulajdonosSzuletesiHely.appendChild(doc.createTextNode(currSzamla.getTulajdonosSzuletesiHely()));
			szamla.appendChild(tulajdonosSzuletesiHely);
			
			Element tulajdonosAnyjaNeve = doc.createElement("tulajdonosAnyjaNeve");
			tulajdonosAnyjaNeve.appendChild(doc.createTextNode(currSzamla.getTulajdonosAnyjaNeve()));
			szamla.appendChild(tulajdonosAnyjaNeve);
			
			Element fizetesiOsztaly = doc.createElement("fizetesiOsztaly");
			fizetesiOsztaly.appendChild(doc.createTextNode(currSzamla.getFizetesiOsztaly().toString()));
			szamla.appendChild(fizetesiOsztaly);
			
			Element utolsoModositas = doc.createElement("utolsoModositas");
			utolsoModositas.appendChild(doc.createTextNode(currSzamla.getUtolsoModositas().toString()));
			szamla.appendChild(utolsoModositas);
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(kimenetEleres, "Szamlak.xml"));
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(3));
		transformer.transform(source, result);
	}
	
	/**
	 * Egy adott fájlból beimportálja a számlákat.
	 * 
	 * @param bemenet a beimportálandó fájl.
	 * @return a fájlban található számlák listája.
	 * @throws SAXException általános SAX hiba
	 * @throws IOException hibás I/O művelet eredménye
	 * @throws ParserConfigurationException konfigurációs hiba.
	 */
	public static List<Szamla> importSzamlak(File bemenet) throws SAXException, IOException, ParserConfigurationException{
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bemenet);
		
		NodeList nodeList = document.getElementsByTagName("szamla");
		List<Szamla> szamlaLista = new ArrayList<Szamla>();
		
		outerloop:
		for (int i=0; i<nodeList.getLength(); i++){
			Node n = nodeList.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) n;
				
				String szamlaSzam = e.getElementsByTagName("szamlaszam").item(0).getTextContent();
				for (Szamla currSzamla : szamlaLista){
					if (szamlaSzam.equals(currSzamla.getSzamlaSzam())){
						continue outerloop;
					}
				}
				double egyenleg = Double.parseDouble(e.getElementsByTagName("egyenleg").item(0).getTextContent());
				double elmaradas = Double.parseDouble(e.getElementsByTagName("elmaradas").item(0).getTextContent());
				String tulajdonos = e.getElementsByTagName("tulajdonos").item(0).getTextContent();
				LocalDate letrehozasDatum = LocalDate.parse(e.getElementsByTagName("letrehozasDatuma").item(0).getTextContent());
				LocalDate tulajdonosSzuletese = LocalDate.parse(e.getElementsByTagName("tulajdonosSzuletese").item(0).getTextContent());
				String tulajdonosSzuletesiHely = e.getElementsByTagName("tulajdonosSzuletesiHely").item(0).getTextContent();
				String tulajdonosAnyjaNeve = e.getElementsByTagName("tulajdonosAnyjaNeve").item(0).getTextContent();
				FizetesiOsztaly fizetesiOsztaly = FizetesiOsztaly.valueOf(e.getElementsByTagName("fizetesiOsztaly").item(0).getTextContent().toUpperCase());
				LocalDate utolsoModositas = LocalDate.parse(e.getElementsByTagName("utolsoModositas").item(0).getTextContent());
				szamlaLista.add(new Szamla(szamlaSzam, egyenleg, elmaradas, tulajdonos, letrehozasDatum, tulajdonosSzuletese, tulajdonosSzuletesiHely, tulajdonosAnyjaNeve, fizetesiOsztaly, utolsoModositas, new ArrayList<Tranzakcio>()));
			}
		}
		return szamlaLista;
	}
	
	/**
	 * Egy adott fájlból beimportálja a tranzakciókat, valamint hozzáadja a megfelelő számlákhoz.
	 * 
	 * @param bemenet a beimportálandó fájl.
	 * @param szamlak a számlák listája, melyek utalók és fogadók lehetnek.
	 * @return az fájlban található összes tranzakció listája.
	 * @throws SAXException általános SAX hiba
	 * @throws IOException hibás I/O művelet eredménye
	 * @throws ParserConfigurationException konfigurációs hiba.
	 */
	public static List<Tranzakcio> importTranzakciok(File bemenet, List<Szamla> szamlak) throws SAXException, IOException, ParserConfigurationException{
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bemenet);
		
		List<Tranzakcio> tranzakcioLista = new ArrayList<Tranzakcio>();

		NodeList nodeList = document.getElementsByTagName("tranzakcio");
		for (int i=0; i<nodeList.getLength(); i++){
			Node n = nodeList.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) n;
				
				Szamla utalo = null;
				Szamla fogado = null;
				String utaloSzamlaSzam = e.getElementsByTagName("utalo").item(0).getTextContent();
				for (Szamla currSzamla : szamlak){
					if (utaloSzamlaSzam.equals(currSzamla.getSzamlaSzam())){
						utalo = currSzamla;
					}
				}
				String fogadoSzamlaSzam = e.getElementsByTagName("fogado").item(0).getTextContent();
				for (Szamla currSzamla : szamlak){
					if (fogadoSzamlaSzam.equals(currSzamla.getSzamlaSzam())){
						fogado = currSzamla;
					}
				}
				double osszeg = Double.parseDouble(e.getElementsByTagName("osszeg").item(0).getTextContent());
				LocalDate datum = LocalDate.parse(e.getElementsByTagName("datum").item(0).getTextContent());
				Tranzakcio tranzakcio = new Tranzakcio(utalo, fogado, osszeg, datum);
				utalo.addTranzakcio(tranzakcio);
				fogado.addTranzakcio(tranzakcio);
				tranzakcioLista.add(tranzakcio);
			}
		}
		return tranzakcioLista;
		
	}
	
}
