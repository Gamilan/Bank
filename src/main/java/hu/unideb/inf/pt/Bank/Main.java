// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank;

import java.io.IOException;
import java.util.List;

import hu.unideb.inf.pt.Bank.model.Szamla;
import hu.unideb.inf.pt.Bank.view.FeltoltesController;
import hu.unideb.inf.pt.Bank.view.PopupController;
import hu.unideb.inf.pt.Bank.view.RootPaneController;
import hu.unideb.inf.pt.Bank.view.SzamlaController;
import hu.unideb.inf.pt.Bank.view.SzamlaKeszitesController;
import hu.unideb.inf.pt.Bank.view.SzamlaModositasController;
import hu.unideb.inf.pt.Bank.view.TranzakcioKeszitesController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage primaryStage;
	private BorderPane rootPane;
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private ObservableList<Szamla> szamlak = FXCollections.observableArrayList();
	
	public ObservableList<Szamla> getSzamlak() {
		return szamlak;
	}
	
	public void setSzamlak(List<Szamla> szamlak){
		this.szamlak.addAll(szamlak);
	}
	
	public Main(){

	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bank");
		createRootPane();
		createSzamlaView();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void createRootPane(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/RootPane.fxml"));
			rootPane = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootPane);
			
			RootPaneController controller = loader.getController();
			controller.setMain(this);
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createSzamlaView(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/Szamla.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			
			rootPane.setCenter(pane);
			
			SzamlaController controller = loader.getController();
			controller.setMain(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createSzamlaModositas(Szamla szamla){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/SzamlaModositas.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Számla adatainak szerkesztése");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
			SzamlaModositasController controller = loader.getController();
			controller.setStage(stage);
			controller.setSzamla(szamla);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createFeltoltes(Szamla szamla){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/Feltoltes.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Feltöltés a számlára");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
			FeltoltesController controller = loader.getController();
			controller.setStage(stage);
			controller.setSzamla(szamla);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createSzamlaKeszites(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/SzamlaKeszites.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Új számla készítése");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
			SzamlaKeszitesController controller = loader.getController();
			controller.setMain(this);
			controller.setFields();
			controller.setStage(stage);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createTranzakcioKeszites(Szamla szamla){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/TranzakcioKeszites.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Új tranzakció hozzáadása");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
			TranzakcioKeszitesController controller = loader.getController();
			controller.setSzamla(szamla);
			controller.setStage(stage);
			controller.setMain(this);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createPopup(String uzenet){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/Popup.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();
			
			Stage stage = new Stage();
			stage.setTitle("Hiba");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(getPrimaryStage());
			
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			
			PopupController controller = loader.getController();
			controller.setStage(stage);
			controller.setText(uzenet);
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
