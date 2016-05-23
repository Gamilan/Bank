package hu.unideb.inf.pt.Bank.model;

import java.time.LocalDate;

/**
 * Számlák közötti átutalásokat reprezentáló osztály.
 * 
 * @author gamilan
 */
public class Tranzakcio {

	/**
	 * A tranzakciót kezdeményező számla.
	 */
	private Szamla utalo;
	
	/**
	 * A tranzakció kedvezményezett számlája.
	 */
	private Szamla fogado;
	
	/**
	 * A tranzakció során utalandó összeg.
	 */
	private double osszeg;
	
	/**
	 * A tranzakció lefolyásának dátuma.
	 */
	private LocalDate datum;
	
	/**
	 * A tranzakció konstruktora.
	 * 
	 * @param utalo a tranzakciót kezdeményező számla.
	 * @param fogado a tranzakció kedvezményezett számlája.
	 * @param osszeg a tranzakció során utalandó összeg.
	 * @param datum a tranzakció lefolyásának dátuma.
	 */
	public Tranzakcio(Szamla utalo, Szamla fogado, double osszeg, LocalDate datum) {
		this.utalo = utalo;
		this.fogado = fogado;
		this.osszeg = osszeg;
		this.datum = datum;
	}

	/**
	 * A tranzakciót kezdeményező számlát visszaadó metódus.
	 * 
	 * @return a tranzakciót kezdeményező számla.
	 */
	public Szamla getUtalo() {
		return this.utalo;
	}

	/**
	 * A tranzakciót kezdeményező számlát beállító metódus.
	 * 
	 * @param utalo a tranzakciót kezdeményező számla.
	 */
	public void setUtalo(Szamla utalo) {
		this.utalo = utalo;
	}

	/**
	 * A tranzakció által kedvezményezett számlát visszaadó metódus.
	 * 
	 * @return a tranzakció által kedvezményezett számla.
	 */
	public Szamla getFogado() {
		return this.fogado;
	}
	
	/**
	 * A tranzakció által kedvezményezett számlát beállító metódus.
	 * 
	 * @param fogado a tranzakció által kedvezményezett számla.
	 */
	public void setFogado(Szamla fogado) {
		this.fogado = fogado;
	}

	/**
	 * A tranzakció során utalandó összeget visszaadó metódus.
	 * 
	 * @return a tranzakció során utalandó összeg.
	 */
	public double getOsszeg() {
		return this.osszeg;
	}

	/**
	 * A tranzakció során utalandó összeget beállító metódus.
	 * 
	 * @param osszeg a tranzakció során utalandó összeg.
	 */
	public void setOsszeg(double osszeg) {
		this.osszeg = osszeg;
	}

	/**
	 * A tranzakció lefolyásának dátumát visszaadó metódus.
	 * 
	 * @return a tranzakció lefolyásának dátuma.
	 */
	public LocalDate getDatum() {
		return datum;
	}

	/**
	 * A tranzakció lefolyásának dátumát beállító metódus.
	 * 
	 * @param datum a tranzakció lefolyásának dátuma.
	 */
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	// CHECKSTYLE:OFF
	@Override
	public int hashCode(){
		return (int) (this.utalo.hashCode() + this.fogado.hashCode() + this.osszeg + this.datum.hashCode());
	}
	
	@Override
	public boolean equals(Object masik){
		Tranzakcio masikTmp = (Tranzakcio) masik;
		return ((this.utalo.equals(masikTmp.utalo)) && (this.fogado.equals(masikTmp.fogado)) && (this.osszeg == masikTmp.osszeg) && (this.datum.isEqual(masikTmp.datum)));
	}

	@Override
	public String toString() {
		return "Tranzakcio [utalo=" + utalo.getSzamlaSzam() + ", fogado=" + fogado.getSzamlaSzam() + ", osszeg=" + osszeg + ", datum=" + datum + "]";
	}
	// CHECKSTYLE:ON
}
