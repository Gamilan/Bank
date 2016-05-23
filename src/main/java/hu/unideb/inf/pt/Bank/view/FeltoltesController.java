// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;


import hu.unideb.inf.pt.Bank.model.Szamla;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FeltoltesController {

	private Stage stage;
	private Szamla jelenlegiSzamla;
	
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
	private TextField feltoltes;
	
	@FXML
	private void handleOk(){
		double feltolt = Double.parseDouble(feltoltes.getText());
		jelenlegiSzamla.feltolt(feltolt);
		stage.close();
	}
	
	@FXML
	private void handleCancel(){
		stage.close();
	}
	
}
