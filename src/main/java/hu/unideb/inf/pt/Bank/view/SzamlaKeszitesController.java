// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;

import java.time.LocalDate;

import hu.unideb.inf.pt.Bank.Main;
import hu.unideb.inf.pt.Bank.Muveletek;
import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Szamla.FizetesiOsztaly;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SzamlaKeszitesController {

	private Stage stage;
	
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public SzamlaKeszitesController(Main main) {
		super();
		this.main = main;
	}
	
	public SzamlaKeszitesController() {
		super();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private TextField szamlaSzamField;
	@FXML
	private TextField egyenlegField;
	@FXML
	private TextField tulajdonosField;
	@FXML
	private DatePicker tulajdonosSzuletesiDatumaPicker;
	@FXML
	private TextField tulajdonosSzuletesiHelyeField;
	@FXML
	private TextField tulajdonosAnyjaNeveField;
	@FXML
	private ChoiceBox<FizetesiOsztaly> fizetesiOsztalyBox;
	
	public void setFields(){	
		szamlaSzamField.setText("");
		egyenlegField.setText("0");
		tulajdonosField.setText("");
		tulajdonosSzuletesiDatumaPicker.setValue(LocalDate.now().withYear(1990));
		tulajdonosSzuletesiHelyeField.setText("");
		tulajdonosAnyjaNeveField.setText("");
		ObservableList<FizetesiOsztaly> fizetesiOsztalyok = FXCollections.observableArrayList();
		fizetesiOsztalyok.add(FizetesiOsztaly.ALACSONY);
		fizetesiOsztalyok.add(FizetesiOsztaly.KOZEPES);
		fizetesiOsztalyok.add(FizetesiOsztaly.MAGAS);
		fizetesiOsztalyBox.setItems(fizetesiOsztalyok);
		fizetesiOsztalyBox.setValue(FizetesiOsztaly.ALACSONY);
	}
	
	@FXML
	private void handleOk(){
			if (!Muveletek.addSzamlak(main.getSzamlak(), new Szamla(szamlaSzamField.getText(),
										 	 Double.parseDouble(egyenlegField.getText()),
										 	 0.0,
										 	 tulajdonosField.getText(),
										 	 LocalDate.now(),
										 	 tulajdonosSzuletesiDatumaPicker.getValue(),
										 	 tulajdonosSzuletesiHelyeField.getText(),
										 	 tulajdonosAnyjaNeveField.getText(),
										 	 fizetesiOsztalyBox.getValue(),
										 	 LocalDate.now(),
										 	 FXCollections.observableArrayList()))){
				main.createPopup("Nem lehet létrehozni a számlát.");
			}
		stage.close();
	}
	
	@FXML
	private void handleCancel(){
		stage.close();
	}
	
}
