// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;

import hu.unideb.inf.pt.Bank.model.Szamla;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SzamlaModositasController {

	private Stage stage;
	private Szamla jelenlegiSzamla;
	
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private TextField szamlaSzamField;
	@FXML
	private TextField tulajdonosField;
	@FXML
	private DatePicker tulajdonosSzuletesiDatumaPicker;
	@FXML
	private TextField tulajdonosSzuletesiHelyeField;
	@FXML
	private TextField tulajdonosAnyjaNeveField;
	
	public void setSzamla(Szamla szamla){
		jelenlegiSzamla = szamla;
		
		szamlaSzamField.setText(szamla.getSzamlaSzam());
		tulajdonosField.setText(szamla.getTulajdonos());
		tulajdonosSzuletesiDatumaPicker.setValue(szamla.getTulajdonosSzuletese());
		tulajdonosSzuletesiHelyeField.setText(szamla.getTulajdonosSzuletesiHely());
		tulajdonosAnyjaNeveField.setText(szamla.getTulajdonosAnyjaNeve());
	}
	
	@FXML
	private void handleOk(){
		jelenlegiSzamla.setSzamlaSzam(szamlaSzamField.getText());
		jelenlegiSzamla.setTulajdonos(tulajdonosField.getText());
		jelenlegiSzamla.setTulajdonosSzuletese(tulajdonosSzuletesiDatumaPicker.getValue());
		jelenlegiSzamla.setTulajdonosSzuletesiHely(tulajdonosSzuletesiHelyeField.getText());
		jelenlegiSzamla.setTulajdonosAnyjaNeve(tulajdonosAnyjaNeveField.getText());
		stage.close();
	}
	
	@FXML
	private void handleCancel(){
		stage.close();
	}
	
	
}
