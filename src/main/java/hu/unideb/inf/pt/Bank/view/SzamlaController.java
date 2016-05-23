// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.pt.Bank.Main;
import hu.unideb.inf.pt.Bank.Muveletek;
import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.model.Tranzakcio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SzamlaController {

	private Main main;
	
	private static Logger logger = LoggerFactory.getLogger(SzamlaController.class);
	
	public void setMain(Main main) {
		this.main = main;
		szamlaTabla.setItems(main.getSzamlak());
		logger.info("Táblázat betöltése");
	}
	
	public SzamlaController(Main main) {
		this.main = main;
	}
	
	public SzamlaController() {

	}
	
	@FXML
	private TableView<Szamla> szamlaTabla;
	@FXML
	private TableColumn<Szamla, String> szamlaSzamOszlop;
	@FXML
	private TableColumn<Szamla, String> tulajdonosOszlop;
	@FXML
	private TableColumn<Szamla, String> egyenlegOszlop;
	
	@FXML
	private TableView<Tranzakcio> tranzakcioTabla;
	@FXML
	private TableColumn<Tranzakcio, String> utaloOszlop;
	@FXML
	private TableColumn<Tranzakcio, String> fogadoOszlop;
	@FXML
	private TableColumn<Tranzakcio, String> osszegOszlop;
	
	@FXML
	private Label tulajdonosSzuleteseLabel;
	@FXML
	private Label anyjaNeveLabel;
	@FXML
	private Label utolsoModositasLabel;
	@FXML
	private Label letrehozasDatumaLabel;
	@FXML
	private Label elmaradasLabel;
	
	@FXML
	private void initialize(){
		szamlaSzamOszlop.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getSzamlaSzam()));
		tulajdonosOszlop.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getTulajdonos()));
		egyenlegOszlop.setCellValueFactory(celldata -> new SimpleStringProperty(Double.toString(celldata.getValue().getEgyenleg())));

		utaloOszlop.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getUtalo().getSzamlaSzam()));
		fogadoOszlop.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getFogado().getSzamlaSzam()));
		osszegOszlop.setCellValueFactory(celldata -> new SimpleStringProperty(Double.toString(celldata.getValue().getOsszeg())));
		
		szamlaTabla
			.getSelectionModel()
			.selectedItemProperty()
			.addListener( (o, oldValue, newValue) -> szamlaMegjelenites(newValue));
	}
	
	private void szamlaMegjelenites(Szamla szamla){
		logger.info("Kiválasztott számla megjelenítése.");
		if (szamla != null){
			ObservableList<Tranzakcio> tranzakciok = FXCollections.observableArrayList();
			tranzakciok.addAll(szamla.getTranzakciok());
			tranzakcioTabla.setItems(tranzakciok);

			tulajdonosSzuleteseLabel.setText(szamla.getTulajdonosSzuletese().toString());
			anyjaNeveLabel.setText(szamla.getTulajdonosAnyjaNeve());
			utolsoModositasLabel.setText(szamla.getUtolsoModositas().toString());
			letrehozasDatumaLabel.setText(szamla.getLetrehozasDatum().toString());
			elmaradasLabel.setText(Double.toString(szamla.getElmaradas()));
		} else{
			logger.warn("Nincs kiválasztott számla.");
			tranzakcioTabla.setItems(null);
			
			tulajdonosSzuleteseLabel.setText("");
			anyjaNeveLabel.setText("");
			utolsoModositasLabel.setText("");
			letrehozasDatumaLabel.setText("");
			elmaradasLabel.setText("");
		}
		
	}
	
	@FXML
	private void szamlaTorles(){
		if (szamlaTabla.getSelectionModel().getSelectedItem() != null){
			Szamla szamla = szamlaTabla.getSelectionModel().getSelectedItem();
			logger.info(szamla + " törlése.");
			Muveletek.torol(main.getSzamlak(), szamla);
			szamlaTabla.getItems().remove(szamlaTabla.getSelectionModel().getSelectedIndex());
		}
	}
	
	@FXML
	private void szamlaModositas(){
		Szamla szamla = szamlaTabla.getSelectionModel().getSelectedItem();
		if (szamla != null){
			logger.info(szamla + " módosítása.");
			main.createSzamlaModositas(szamla);
			main.createSzamlaView();
		}
	}
	
	@FXML
	private void feltolt(){
		Szamla szamla = szamlaTabla.getSelectionModel().getSelectedItem();
		if (szamla != null){
			logger.info(szamla + "-ra feltöltés.");
			main.createFeltoltes(szamla);
			main.createSzamlaView();
		}
	}
	
	@FXML
	private void szamlaKeszites(){
		main.createSzamlaKeszites();
		main.createSzamlaView();
	}
	
	@FXML
	private void tranzakcioKeszites(){
		Szamla szamla = szamlaTabla.getSelectionModel().getSelectedItem();
		if (szamla != null){
			logger.info(szamla + "-hoz úgy tranzakció felvétele.");
			main.createTranzakcioKeszites(szamla);
			main.createSzamlaView();
		}
	}
	
}
