// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import hu.unideb.inf.pt.Bank.Main;
import hu.unideb.inf.pt.Bank.Muveletek;
import hu.unideb.inf.pt.Bank.IO.IOHandler;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class RootPaneController {

	private Main main;
	
	public RootPaneController() {
		
	}
	
	public RootPaneController(Main main){
		this.main = main;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	@FXML
	private void handleImport(){
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Számlák importálásának mappája");
			File eleresiUt = fileChooser.showOpenDialog(main.getPrimaryStage());
			main.setSzamlak(IOHandler.importSzamlak(eleresiUt));
			fileChooser.setTitle("Tranzakciók importálásának mappája");
			eleresiUt = fileChooser.showOpenDialog(main.getPrimaryStage());
			IOHandler.importTranzakciok(eleresiUt, main.getSzamlak());
			this.main.createSzamlaView();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleExport(){
		try {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Tranzakciók exportálásának mappája");
			File eleresiUt = directoryChooser.showDialog(main.getPrimaryStage());
			IOHandler.exportTranzakcio(eleresiUt, IOHandler.createTranzakcioList(main.getSzamlak()));
			IOHandler.exportSzamla(eleresiUt, main.getSzamlak());
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleClose(){
		System.exit(0);
	}
	
	@FXML
	private void handleTax(){
		Muveletek.adoztat(main.getSzamlak());
		main.createSzamlaView();
	}
}
	
