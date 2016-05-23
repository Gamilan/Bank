package hu.unideb.inf.pt.Bank.model;

import java.time.LocalDate;
import java.util.List;

/**
 * A számlákat reprezentáló osztály.
 * Alapvető adatokat tárol a számláról, valamint a hozzá tartozó tranzakciókat is tárolja.
 * 
 * @author gamilan
 */
public class Szamla {

	/**
	 * Az adott számla tulajdonosának fizetését reprezentálja.
	 * 
	 * @author gamilan
	 */
	public enum FizetesiOsztaly {
		ALACSONY("Alacsony"), KOZEPES("Kozepes"), MAGAS("Magas");
		
		/**
		 * Az fizetési besorolás neve.
		 */
		private String osztaly;

	    /**
	     * A fizetési besorolás konstruktora.
	     * 
	     * @param osztaly a fizetési besorolás.
	     */
	    FizetesiOsztaly(String osztaly) {
	        this.osztaly = osztaly;
	    }

	    @Override
	    public String toString() {
	        return osztaly;
	    }
	}
	
	/**
	 * A számla egyedi azonosító száma.
	 */
	private String szamlaSzam;
	
	/**
	 * A számlán található egyenleg.
	 */
	private double egyenleg;
	
	/**
	 * A számla befizetési elmaradásai.
	 */
	private double elmaradas;
	
	/**
	 * A számla tulajdonosának neve.
	 */
	private String tulajdonos;
	
	/**
	 * A számla létrehozásának dátuma.
	 */
	private LocalDate letrehozasDatum;
	
	/**
	 * A számla tulajdonosának születési dátuma.
	 */
	private LocalDate tulajdonosSzuletese;
	
	/**
	 * A számla tulajdonosának születési helye (városa/települése).
	 */
	private String tulajdonosSzuletesiHely;
	
	/**
	 * A számla tulajdonosának az anyjának a lánykori neve.
	 */
	private String tulajdonosAnyjaNeve;
	
	/**
	 * A számla tulajdonosának fizetési besorolása.
	 */
	private FizetesiOsztaly fizetesiOsztaly;
	
	/**
	 * A számla utolsó módosításának dátuma.
	 */
	private LocalDate utolsoModositas;
	
	/**
	 * A számlához tartozó tranzakciók listája.
	 */
	private List<Tranzakcio> tranzakciok;
	
	/**
	 * A számla konstruktora.
	 * 
	 * @param szamlaSzam a számla egyedi azonosító száma.
	 * @param egyenleg a számlán található egyenleg.
	 * @param elmaradas a számla befizetési elmaradásai.
	 * @param tulajdonos a számla tulajdonosának neve.
	 * @param letrehozasDatum a számla létrehozásának dátuma.
	 * @param tulajdonosSzuletese a számla tulajdonosának születési dátuma.
	 * @param tulajdonosSzuletesiHely a számla tulajdonosának születési helye (városa/települése).
	 * @param tulajdonosAnyjaNeve a számla tulajdonosának az anyjának a lánykori neve.
	 * @param fizetesiOsztaly a számla tulajdonosának fizetési besorolása.
	 * @param utolsoModositas a számla utolsó módosításának dátuma.
	 * @param tranzakciok a számlához tartozó tranzakciók listája.
	 */
	public Szamla(String szamlaSzam, double egyenleg, double elmaradas, String tulajdonos, LocalDate letrehozasDatum,
			LocalDate tulajdonosSzuletese, String tulajdonosSzuletesiHely, String tulajdonosAnyjaNeve,
			FizetesiOsztaly fizetesiOsztaly, LocalDate utolsoModositas, List<Tranzakcio> tranzakciok) {
		this.szamlaSzam = szamlaSzam;
		this.egyenleg = egyenleg;
		this.elmaradas = elmaradas;
		this.tulajdonos = tulajdonos;
		this.letrehozasDatum = letrehozasDatum;
		this.tulajdonosSzuletese = tulajdonosSzuletese;
		this.tulajdonosSzuletesiHely = tulajdonosSzuletesiHely;
		this.tulajdonosAnyjaNeve = tulajdonosAnyjaNeve;
		this.fizetesiOsztaly = fizetesiOsztaly;
		this.utolsoModositas = utolsoModositas;
		this.tranzakciok = tranzakciok;
	}
	
	/**
	 * A számla számlaszámát visszaadó metódus.
	 * 
	 * @return a számla számlaszáma.
	 */
	public String getSzamlaSzam() {
		return this.szamlaSzam;
	}
	
	/**
	 * A számla számlaszámát beállító metódus.
	 * 
	 * @param szamlaSzam a számlának beállítandó számlaszám.
	 */
	public void setSzamlaSzam(String szamlaSzam) {
		this.szamlaSzam = szamlaSzam;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla egyenlegét visszaadó metódus.
	 * 
	 * @return a számla egyenlege.
	 */
	public double getEgyenleg() {
		return this.egyenleg;
	}

	/**
	 * A számla egyenlegét beállító metódus.
	 * 
	 * @param egyenleg a számlának beállítandó egyenleg.
	 */
	public void setEgyenleg(double egyenleg) {
		this.egyenleg = egyenleg;
		this.utolsoModositas = LocalDate.now();
	}
	
	/**
	 * A számla elmaradását visszaadó metódus.
	 * 
	 * @return a számla elmaradása.
	 */
	public double getElmaradas(){
		return this.elmaradas;
	}
	
	/**
	 * A számla elmaradását beállító metódus.
	 * 
	 * @param elmaradas a számlának beállítandó elmaradás.
	 */
	public void setElmaradas(double elmaradas){
		this.elmaradas = elmaradas;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla tulajdonosának nevét visszaadó metódus.
	 * 
	 * @return a számla tulajdonosának neve.
	 */
	public String getTulajdonos() {
		return this.tulajdonos;
	}

	/**
	 * A számla tulajdonosának nevét beállító metódus.
	 * 
	 * @param tulajdonos a számlának beállítandó tulajdonos neve.
	 */
	public void setTulajdonos(String tulajdonos) {
		this.tulajdonos = tulajdonos;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla létrehozásának dátumát visszaadó metódus.
	 * 
	 * @return a számla létrehozásának dátuma.
	 */
	public LocalDate getLetrehozasDatum() {
		return this.letrehozasDatum;
	}

	/**
	 * A számla létrehozásának dátumát beállító metódus.
	 * 
	 * @param letrehozasDatum a számlának beállítandó létrehozás dátuma.
	 */
	public void setLetrehozasDatum(LocalDate letrehozasDatum) {
		this.letrehozasDatum = letrehozasDatum;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla tulajdonosának születési dátumát visszaadó metódus.
	 * 
	 * @return a számla tulajdonosának születési dátuma.
	 */
	public LocalDate getTulajdonosSzuletese() {
		return tulajdonosSzuletese;
	}

	/**
	 * A számla tulajdonosának születési dátumát beállító metódus.
	 * 
	 * @param tulajdonosSzuletese a számla tulajdonosának születési dátumaként beállítandó dátum.
	 */
	public void setTulajdonosSzuletese(LocalDate tulajdonosSzuletese) {
		this.tulajdonosSzuletese = tulajdonosSzuletese;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla tulajdonosának születési helyét visszaadó metódus.
	 * 
	 * @return a számla tulajdonosának születési helye.
	 */
	public String getTulajdonosSzuletesiHely() {
		return this.tulajdonosSzuletesiHely;
	}

	/**
	 * A számla tulajdonosának születési helyét beállító metódus.
	 * 
	 * @param tulajdonosSzuletesiHely a számla tulajdonosának születési helyének beállítandó hely.
	 */
	public void setTulajdonosSzuletesiHely(String tulajdonosSzuletesiHely) {
		this.tulajdonosSzuletesiHely = tulajdonosSzuletesiHely;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla tulajdonosának anyjának a nevét visszaadó metódus.
	 * 
	 * @return a számla tulajdonosának anyjának a neve.
	 */
	public String getTulajdonosAnyjaNeve() {
		return tulajdonosAnyjaNeve;
	}

	/**
	 * A számla tulajdonosának anyjának a nevét beállító metódus.
	 * 
	 * @param tulajdonosAnyjaNeve a számla tulajdonosának anyjának a beállítandó neve.
	 */
	public void setTulajdonosAnyjaNeve(String tulajdonosAnyjaNeve) {
		this.tulajdonosAnyjaNeve = tulajdonosAnyjaNeve;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla tulajdonosának fizetési besorolását visszaadó metódus.
	 * 
	 * @return a számla tulajdonosának fizetési besorolása.
	 */
	public FizetesiOsztaly getFizetesiOsztaly() {
		return fizetesiOsztaly;
	}

	/**
	 * A számla tulajdonosának fizetési besorolását beállító metódus.
	 * 
	 * @param fizetesiOsztaly a számla tulajdonosának fizetési besorolásának beállítandó érték.
	 */
	public void setFizetesiOsztaly(FizetesiOsztaly fizetesiOsztaly) {
		this.fizetesiOsztaly = fizetesiOsztaly;
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A számla utolsó módosításának dátumát visszaadó metódus.
	 * 
	 * @return a számla utolsó módosításának dátuma.
	 */
	public LocalDate getUtolsoModositas() {
		return utolsoModositas;
	}

	/**
	 * A számla utolsó módosításának dátumát beállító metódus.
	 * 
	 * @param utolsoModositas a számla utolsó módosításának beállítandó dátum.
	 */
	public void setUtolsoModositas(LocalDate utolsoModositas) {
		this.utolsoModositas = utolsoModositas;
	}

	/**
	 * A számla tranzakcióinak listáját visszaadó metódus.
	 * 
	 * @return a számla tranzakcióinak listája.
	 */
	public List<Tranzakcio> getTranzakciok() {
		return tranzakciok;
	}

	/**
	 * A számla tranzakcióinak listáját beállító metódus.
	 * 
	 * @param tranzakciok a számla tranzakcióinak beállítandó tranzakciók listája.
	 */
	public void setTranzakciok(List<Tranzakcio> tranzakciok) {
		this.tranzakciok = tranzakciok;
	}
	
	/**
	 * A számla tranzakcióinak listájához hozzácsatoló metódus.
	 * 
	 * @param tranzakcio a számla tranzakcióinak listájához hozzácsatolandó tranzakció.
	 */
	public void addTranzakcio(Tranzakcio tranzakcio){
		this.tranzakciok.add(tranzakcio);
		this.utolsoModositas = LocalDate.now();
	}

	/**
	 * A bank által a szolgáltatásaiért levont összeg.
	 * 
	 * @param osszeg a levonás vagy feltöltés összege, mely után a bank elszámolja a kezelési költséget.
	 * @return a bank által elszámolt kezelési költség.
	 */
	public double kezelesiDij(double osszeg){
		double kezelesiDij = 0;
		if (this.fizetesiOsztaly == FizetesiOsztaly.ALACSONY){
			kezelesiDij = osszeg * 0.03;
		} else if (this.fizetesiOsztaly == FizetesiOsztaly.KOZEPES){
			kezelesiDij = osszeg * 0.04;
		} else {
			kezelesiDij = osszeg * 0.05;
		}
		return Math.round(kezelesiDij);
	}
	
	/**
	 * A bank le tud-e vonni egy összeget úgy, hogy a számla egyenlege nem lesz negatív.
	 * 
	 * @param levonas a számla egyenlegéről levonandó összeg.
	 * @return le lehet-e vonni az összeget úgy, hogy a számla egyenlege nem lesz negatív.
	 */
	public boolean levonhatE(double levonas){
		if (this.egyenleg - levonas - kezelesiDij(levonas) >= 0){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * A számla egyenlegéről levon egy adott összeget.
	 * Ha a számla egyenlege negatívba menne át, akkor az elmaradásba számolja a többletet.
	 * 
	 * @param levonas a számla egyenlegéről levonandó összeg.
	 */
	public void levon(double levonas){
		if (levonhatE(levonas)){
			this.egyenleg -= Math.round(levonas + kezelesiDij(levonas));
		} else if (this.egyenleg > 0){
			this.elmaradas = Math.round(levonas + kezelesiDij(levonas) - this.egyenleg);
			this.egyenleg = 0.0;
		} else {
			this.elmaradas += Math.round(levonas + kezelesiDij(levonas));
		}
		this.utolsoModositas = LocalDate.now();
	}
	
	/**
	 * A számla egyenlegére feltölt egy adott összeget.
	 * 
	 * @param feltoltes a számla egyenlegére feltöltendő összeg.
	 */
	public void feltolt(double feltoltes){
		if (this.elmaradas == 0.0){
			this.egyenleg += Math.round(feltoltes - kezelesiDij(feltoltes));
		} else if (feltoltes >= this.elmaradas){
			this.egyenleg = Math.round(feltoltes - kezelesiDij(feltoltes) - this.elmaradas);
			this.elmaradas = 0;
		} else {
			this.elmaradas -= Math.round(feltoltes - kezelesiDij(feltoltes));
		}
		this.utolsoModositas = LocalDate.now();
	}
	
	/**
	 * Egyik számláról a másikra történő utalást végző metódus.
	 * Csak akkor utal, ha az utaló számla egyenlege nem lenne negatív.
	 * 
	 * @param masik a kedvezményezett számla.
	 * @param osszeg az utalandó összeg.
	 * @return létrejött-e az utalás.
	 */
	public boolean utal(Szamla masik, double osszeg){
		if (this.szamlaSzam.equals(masik.szamlaSzam)){
			return false;
		}
		if (this.levonhatE(osszeg)){
			this.levon(osszeg);
			masik.feltolt(osszeg);
			this.utolsoModositas = LocalDate.now();
			masik.utolsoModositas = LocalDate.now();
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Az adott számla befizeti az adóit, mely levonásra kerül a számla egyenlegéről.
	 * 
	 * @return elmaradás nélkül teljesült-e az adózás.
	 */
	public boolean adozik(){
		if (this.fizetesiOsztaly == FizetesiOsztaly.ALACSONY){
			this.levon(this.egyenleg*0.27+20000);
		} else if (this.fizetesiOsztaly == FizetesiOsztaly.KOZEPES){
			this.levon(this.egyenleg*0.33+25000);
		} else {
			this.levon(this.egyenleg*0.40+35000);
		}
		if (this.elmaradas > 0){
			return false;
		}
		return true;
	}

	// CHECKSTYLE:OFF
	@Override
	public String toString() {
		return "Szamla [szamlaSzam=" + szamlaSzam + ", egyenleg=" + egyenleg + ", elmaradas=" + elmaradas + ", tulajdonos=" + tulajdonos
				+ ", letrehozasDatum=" + letrehozasDatum + ", tulajdonosSzuletese=" + tulajdonosSzuletese
				+ ", tulajdonosSzuletesiHely=" + tulajdonosSzuletesiHely + ", tulajdonosAnyjaNeve="
				+ tulajdonosAnyjaNeve + ", fizetesiOsztaly=" + fizetesiOsztaly + ", utolsoModositas=" + utolsoModositas
				+ "]";
	}
	
	@Override
	public int hashCode(){
		return Integer.parseInt(this.szamlaSzam);
	}
	
	@Override
	public boolean equals(Object masik){
		Szamla masikTmp = (Szamla) masik;
		try{
			return this.szamlaSzam.equals(masikTmp.getSzamlaSzam());
		} catch (Exception e){
			return false;
		}
	}
	// CHECKSTYLE:ON
	
}
