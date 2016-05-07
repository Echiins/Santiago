package presentation.control;


import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Classes.Joueur;
import Classes.Santiago;
import Exception.LogicException;
import Interface.PartieInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainUIController extends DialogUIController{
	private static final String PLATEAU="../view/partieGraphique.fxml";
	private static final String DEMARRAGE="../view/DemarrageJeu.fxml";
	private static final String INSCRIPTION="../view/InscriptionView.fxml";
	private static final String REGLES="../view/reglesView.fxml";

	private Stage primaryStage;
	private InscriptionUIController InscriptionController;
	private PlateauUIController PlateauController;
	private DemarrageUIController DemarrageController;

	private RegleUIController ReglesController;
	
	@FXML 
	private Menu menuPartiePrecedentes;
	
	@FXML 
	private MenuItem menuSortir;
	
	@FXML 
	private MenuItem regles;
	
	@FXML 
	private Menu aide;
	
	@FXML 
	private Button jouer;
	
	Joueur joueur;
	
	@FXML
	void exit(ActionEvent event) {
		Platform.exit();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("MENU SANTIAGO");
				menuSortir.setOnAction(event -> {
					
					//Si la personne est connectée 
					if(santiago.getClient()!=null){
							try {
							PartieInterface server;
							String host=santiago.getClient().getHost();
							server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
							for(Joueur j : server.getJoueurs()){
								if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
									joueur=j;
								
							}
							//Lorsqu'on quitte le jeu, on  deconnecte le joueur déja connecté.
							server.getClient().remove(santiago.getClient());
							joueur.setEnligne(false);
							server.getJoueurs().remove(joueur);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					dialog.close();
					});
					
					jouer.setOnAction(event->{
						if(santiago.getClient()==null){
							//Si on est pas inscrit, on s'inscrit
							InscriptionController = DialogUIController.initDialog(
								INSCRIPTION, InscriptionUIController.class,
								primaryStage);
						InscriptionController.showAndWait();
						}
						else if(santiago.getClient()!=null){
							
							String host=santiago.getClient().getHost();
							PartieInterface server;
							try {
								server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
								boolean commence=server.getStart();
								int tour=server.getTour();
								int phase=server.getPhase();
								
							if(commence && santiago.getClient().getJoueur().isEnligne()){
								//Si on est pas encor au tour 2
								PlateauController = DialogUIController.initDialog(PLATEAU, PlateauUIController.class,primaryStage);
								dialog.close();
								PlateauController.showAndWait();
							}
							else if(!commence){
								DemarrageController = DemarrageUIController.initDialog(
										DEMARRAGE, DemarrageUIController.class,
										primaryStage);
									DemarrageController.showAndWait();
							}
							else{
								//Impossible de rejoindre la partie 
								JOptionPane.showMessageDialog(null, "Impossible de vous connecter la partie sur ce réseau à déjà commencé");
							}	
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//Si on est connecté et que la partie a commencé
							
						}
						
					});
					
					regles.setOnAction(event->{
						ReglesController = RegleUIController.initDialog(
							REGLES, RegleUIController.class,
							primaryStage);
						ReglesController.showAndWait();
					});
					
				 
				
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	

}