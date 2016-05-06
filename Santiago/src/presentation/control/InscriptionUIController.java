package presentation.control;

import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;

















import javax.swing.JOptionPane;

import Classes.Santiago;
import Interface.Partie;
import Interface.PartieInterface;
import Reseau.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
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
	@FXML
	private PasswordField password;
	
	@FXML
	private ChoiceBox<String> couleur;
	
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
				// Si deja connecté
					try {
						if(santiago.getClient()!=null){
							String host=santiago.getClient().getHost();
							PartieInterface server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
							boolean commence=server.getStart();
							if(commence){
								JOptionPane.showMessageDialog(null, "Impossible de vous connecter la partie sur ce réseau à déjà commencé");
								}
							else if(server.getClient().size()==5){
								JOptionPane.showMessageDialog(null, "Il y a déja assez de joueurs pouur cette partie");
								
							}
							else{
								dialog.close();
								DemarrageController = DemarrageUIController.initDialog(
									DEMARRAGE, DemarrageUIController.class,
									primaryStage);
								DemarrageController.showAndWait();}
								
							}
						//==null
						else{
						client= new Client(adresse.getText(),pseudo.getText(),password.getText());
						client.creerClient();
						santiago.setClient(client);
						dialog.close();
						String host=santiago.getClient().getHost();
						PartieInterface server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
						boolean commence=server.getStart();
						if(commence){
							JOptionPane.showMessageDialog(null, "Impossible de vous connecter la partie sur ce réseau à déjà commencé");
							}
						else if(server.getClient().size()==5){
							JOptionPane.showMessageDialog(null, "Il y a déja assez de joueurs pouur cette partie");
							
						}
						else{
							dialog.close();
							DemarrageController = DemarrageUIController.initDialog(
								DEMARRAGE, DemarrageUIController.class,
								primaryStage);
							DemarrageController.showAndWait();}
							
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
				
			});
	}

}
