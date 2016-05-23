package hu.unideb.inf.pt.Bank;

import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;

/**
 * Az adatokra végrehajtható műveleteket tartalmazó osztály.
 * 
 * @author gamilan
 */
public class Muveletek {

	/**
	 * Az adott számlákból álló listához hozzáad egy számlát, ha az még nem szerepelt benne.
	 * 
	 * @param szamlak a számlákból álló lista.
	 * @param szamla a hozzáadandó számla
	 * @return hozzá lett-e adva a listához a számla.
	 */
	public static boolean addSzamlak(List<Szamla> szamlak, Szamla szamla){
		boolean voltMar = false;
		for (Szamla currSzamla : szamlak){
			if (szamla.getSzamlaSzam().equals(currSzamla.getSzamlaSzam())){
				voltMar = true;
			}
		}
		if (!voltMar){
			szamlak.add(szamla);
			return true;
		}
		return false;
	}
	
	/**
	 * Megadóztat minden listában szereplő számlát.
	 * 
	 * @param szamlak a számlák listája, melyek adózni fognak.
	 */
	public static void adoztat(List<Szamla> szamlak){
		for (Szamla currSzamla : szamlak){
			currSzamla.adozik();
		}
	}
	
	/**
	 * Egy adott számlát és az összes rá hivatkozó tranzakciót töröl.
	 * 
	 * @param szamlak a számlák listája.
	 * @param szamla a törlendő számla.
	 */
	public static void torol(List<Szamla> szamlak, Szamla szamla){
		List<Tranzakcio> torlendo = new ArrayList<Tranzakcio>();
		for (Szamla currSzamla : szamlak){
			for (Tranzakcio currTranzakcio : currSzamla.getTranzakciok()){
				if ((currTranzakcio.getFogado().equals(currSzamla)) || (currTranzakcio.getUtalo().equals(currSzamla))){
					torlendo.add(currTranzakcio);
				}
			}
		}
		for (Szamla currSzamla : szamlak){
			for (Tranzakcio currTranzakcio : torlendo){
				if ((currTranzakcio.getFogado().equals(szamla)) || (currTranzakcio.getUtalo().equals(szamla))){
					currSzamla.getTranzakciok().remove(currTranzakcio);
				}
			}
		}
	}
	
}
