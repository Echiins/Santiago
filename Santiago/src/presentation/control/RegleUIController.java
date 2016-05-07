package presentation.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Classes.Santiago;

public class RegleUIController extends DialogUIController{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("REGLES DE JEU");
	}

}
