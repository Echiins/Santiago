package presentation.control;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.sun.media.jfxmediaimpl.platform.Platform;

import Classes.Joueur;
import Classes.Santiago;
import Interface.PartieInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DemarrageUIController extends DialogUIController{
	private static final String PLATEAU="../view/partieGraphique.fxml";
	
	private Stage primaryStage;
	private PlateauUIController PlateauController;
	
	@FXML
	private Button commencer;
	
	@FXML
	private Button annuler;
	@FXML
	private Label instruction;
	
	@FXML
	private ImageView a1,a2,a3,a4,a5;
	
	Joueur joueur;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("COMMENCER LA PARTIE");
		annuler.setOnAction(event -> {
		dialog.close();});
		
		try {
			String host=santiago.getClient().getHost();
			PartieInterface server=(PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
			for(Joueur j : server.getJoueurs()){
			if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
				joueur=j;
			
			int taille=server.getClient().size();
			if(3>taille){
				commencer.setDisable(true);
				instruction.setText("Il n'y a pas assez de joueurs connectés pour lancer une partie");
				switch (taille){
				case 1:
					a1.setVisible(true);
					break;
				case 2:
					a1.setVisible(true);
					a2.setVisible(true);
					break;
				}
			}
			else if(5>taille && taille>=3){
				switch (taille){
				case 3:
					a3.setVisible(true);
					a1.setVisible(true);
					a2.setVisible(true);
					break;
				case 4:
					a4.setVisible(true);
					a3.setVisible(true);
					a1.setVisible(true);
					a2.setVisible(true);
					break;
					
				case 5:
					a4.setVisible(true);
					a3.setVisible(true);
					a5.setVisible(true);
					a1.setVisible(true);
					a2.setVisible(true);
					break;
				}dialog.close();
				commencer.setDisable(false);
				instruction.setText("Il y a assez de joueurs connectés pour commencer à jouer.\n Après 5 connections la  partie se lancera automatiquement ");
				
			}
			else if(taille==5){
				dialog.close();
				PlateauController = DialogUIController.initDialog(PLATEAU, PlateauUIController.class,primaryStage);
				dialog.close();
				PlateauController.showAndWait();
			}
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
		
		commencer.setOnAction(event->{
			try {
				String host=santiago.getClient().getHost();
				PartieInterface server=(PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
				if (!server.getStart())
				{
					server.lancerLaPartie();
					//LancerPhase 0
					server.jouerPhase();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PlateauController = DialogUIController.initDialog(PLATEAU, PlateauUIController.class,primaryStage);
			dialog.close();
			PlateauController.showAndWait();});
	}

}

