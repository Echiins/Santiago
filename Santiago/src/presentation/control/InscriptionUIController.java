package presentation.control;

import java.net.URL;
import java.util.ResourceBundle;










import javax.swing.JOptionPane;

import Classes.Santiago;
import Reseau.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InscriptionUIController extends DialogUIController{
	
	private Stage primaryStage;
	private static final String DEMARRAGE="../view/DemarrageJeu.fxml";
	private DemarrageUIController DemarrageController;
	
	@FXML
	private Button joindre;
	@FXML
	private Button annuler;
	
	@FXML
	private TextField pseudo;
	
	@FXML
	private TextField adresse;
	
	Client client;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("JOINDRE UNE PARTIE");
		annuler.setOnAction(event -> dialog.close());
		joindre.setOnAction(event -> {
			if (adresse.getText().isEmpty()&& pseudo.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Veuillez remplir Tous les champs");	
			}
			else if (pseudo.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Entrer votre pseudo");
			}
			else if (adresse.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Entrer l'adresse de votre serveur");	
			}
			else if(pseudo !=null && adresse !=null){
				client= new Client(adresse.getText(),pseudo.getText());
				try {
					client.creerClient();
					santiago.setClient(client);
					DemarrageController = DemarrageUIController.initDialog(
						DEMARRAGE, DemarrageUIController.class,
						primaryStage);
					DemarrageController.showAndWait();
					dialog.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
	}

}
