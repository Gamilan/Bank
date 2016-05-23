// CHECKSTYLE:OFF
package hu.unideb.inf.pt.Bank.view;

import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupController {

	private Stage stage;
	
	@FXML
	private Text text;
	
	public Stage getStage() {
		return this.stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private void initialize(){
		this.text = new Text("");		
	}
	
	public void setText(String uzenet){
		this.text.setText(uzenet);
		this.text.setFont(Font.font(18));
	}
	
	@FXML
	private void handleOk(){
		stage.close();
	}
	
}
