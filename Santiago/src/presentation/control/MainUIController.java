package presentation.control;


import Exception.LogicException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

public class MainUIController {
	private static final String INSCRIPTION="../view/InscriptionView.fxml";
	private static final String REGLES="../view/reglesView.fxml";

	private Stage primaryStage;
	private InscriptionUIController InscriptionController;
	//private ReglesUIController reglesController;
	

	@FXML 
	private Button menuJouer;
	
	@FXML 
	private Menu menuPartiePrecedentes;
	
	@FXML 
	private MenuItem menuSortir;
	@FXML 
	private Menu menuRegles;
	@FXML
	void exit(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	void anciensresultats(ActionEvent event){
		//CHARGER DOC XML avc les resultats
	}
	
	@FXML
	void reglesdujeu(ActionEvent event){
		//Lien vers les regle ou pdf des règle.
	}
	
	@FXML
	void jouer(ActionEvent event){
		InscriptionController = DialogUIController.initDialog(
				INSCRIPTION, InscriptionUIController.class,
				primaryStage);
		InscriptionController.showAndWait();
	}
	

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}