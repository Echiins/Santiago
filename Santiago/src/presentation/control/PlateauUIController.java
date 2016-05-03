package presentation.control;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import Classes.Joueur;
import Classes.Santiago;
import Interface.Partie;
import Interface.PartieInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlateauUIController extends DialogUIController{
	///private Stage primaryStage;
	//MENU
	@FXML
	private MenuItem sauvegarder;
	@FXML
	private MenuItem quitter;
	@FXML
	private MenuItem regles;
	@FXML
	private MenuItem chat;
	//BOUTon
	@FXML
	private Button passer;
	@FXML
	private Button miser;
	//IMAGE
	@FXML
	private ImageView constructeur;
	@FXML
	private ImageView travailleur;
	@FXML
	private ImageView canalBleu;
	@FXML
	private ImageView canalConstruction;
	@FXML
	private ImageView pile1;
	@FXML
	private ImageView pile2;
	@FXML
	private ImageView pile3;
	@FXML
	private ImageView pile4;
	@FXML
	private ImageView pile5;
	//LABEL
	@FXML
	private Label NomJoueur;
	@FXML
	private Label montantCagnotte;
	@FXML
	private Label numPhase;
	@FXML
	private Label numTour;
	@FXML
	private Label nbConstructeurs;
	@FXML
	private Label nbCanaux;
	@FXML
	private ComboBox<Integer> montantMise;
	/*@FXML
	private RadioButton radio5;
	@FXML
	private RadioButton radio4;
	@FXML
	private RadioButton radio3;
	@FXML
	private RadioButton radio1;
	@FXML
	private RadioButton radio2;*/
	
	private Joueur joueur;
	
	public void loadfichejoueur(Partie partie, Joueur joueur){
		
	}
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("PLATEAU: SANTIAGO");
		//On récupere le joueur et le plateau
				try {
					PartieInterface server=(PartieInterface)Naming.lookup("rmi://localhost:5755/jeu");
					
					for(Joueur j : server.getJoueurs()){
					if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
						joueur=j;
				}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (RemoteException e) {
					e.printStackTrace();
				} 
		
		
		/*try {
			numTour.setText(partie.getTour()+" / "+String.valueOf(partie.getMax_tour()));
			numPhase.setText(String.valueOf(partie.getPhase()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}******/
		
		/**
		 * ********************************************************
		 * ***************************************FICHE JOUEUR*****
		 * ********************************************************
		 */
				
		NomJoueur.setText(joueur.getNom_joueur());
		montantCagnotte.setText(String.valueOf(joueur.getCagnotte())+"estucudos");
		nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()));
		if(joueur.getCanal_bleu())
				nbCanaux.setText(String.valueOf(1));
		else
			canalBleu.setVisible(false);

		if(!joueur.getEst_constructeurdecanal())
			constructeur.setVisible(false);
		
		/**
		 * ********************************************************
		 * ***************************************FICHE JEU*****
		 * ********************************************************
		 */
			
	
		quitter.setOnAction(event -> dialog.close());
		sauvegarder.setOnAction(event -> dialog.close());
		chat.setOnAction(event -> dialog.close());
		regles.setOnAction(event -> dialog.close());
		
		miser.setOnAction(event ->{
			//si pas de pile
			//si plusieurs pile
			//si pas de montant
			//si montant deja
		}
		);
	}
}
