// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;

import java.time.LocalDate;

import hu.unideb.inf.pt.Bank.Main;
import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TranzakcioKeszitesController {

	private Stage stage;
	private Szamla jelenlegiSzamla;
	
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	private TextField fogadoField;
	@FXML
	private TextField osszegField;
	
	
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setSzamla(Szamla szamla){
		jelenlegiSzamla = szamla;
	}

	@FXML
	private void handleOk(){
		String fogadoSzamlaSzam = fogadoField.getText().trim();
		Szamla fogadoSzamla = null;
		for (Szamla currSzamla : main.getSzamlak()){
			if (fogadoSzamlaSzam.equals(currSzamla.getSzamlaSzam())){
				fogadoSzamla = currSzamla;
			}
		}
		if (fogadoSzamla != null){
			if (jelenlegiSzamla.utal(fogadoSzamla, Integer.parseInt(osszegField.getText()))){
				Tranzakcio tranzakcio = new Tranzakcio(jelenlegiSzamla, fogadoSzamla, Integer.parseInt(osszegField.getText()), LocalDate.now());
				jelenlegiSzamla.addTranzakcio(tranzakcio);
				fogadoSzamla.addTranzakcio(tranzakcio);
			}
			
		} else {
			main.createPopup("Nem hozhazó létre a tranzakció!");
		}
		stage.close();
	}
	
	@FXML
	private void handleCancel(){
		stage.close();
	}
	
}
