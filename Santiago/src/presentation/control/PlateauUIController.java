package presentation.control;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Classes.*;
import Interface.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
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
	private MenuItem activerchat;
	
	//BOUTon
	@FXML
	private Button passerMise;
	@FXML
	private Button miser;
	
	//IMAGE
	@FXML
	private ImageView C1;
	@FXML
	private ImageView C2;
	@FXML
	private ImageView C3;
	@FXML
	private ImageView C4;
	@FXML
	private ImageView C5;
	@FXML
	private ImageView C6;
	@FXML
	private ImageView C7;
	@FXML
	private ImageView C8;
	@FXML
	private ImageView C9;
	@FXML
	private ImageView C10;
	@FXML
	private ImageView C11;
	@FXML
	private ImageView C12;
	@FXML
	private ImageView C13;
	@FXML
	private ImageView C14;
	@FXML
	private ImageView C15;
	@FXML
	private ImageView C16;
	@FXML
	private ImageView C17;
	@FXML
	private ImageView C18;
	@FXML
	private ImageView C19;
	@FXML
	private ImageView C20;
	@FXML
	private ImageView C21;
	@FXML
	private ImageView C22;
	@FXML
	private ImageView C23;
	@FXML
	private ImageView C24;
	@FXML
	private ImageView C25;
	@FXML
	private ImageView C26;
	@FXML
	private ImageView C28;
	@FXML
	private ImageView C27;
	@FXML
	private ImageView C29;
	@FXML
	private ImageView C30;
	@FXML
	private ImageView C31;
	@FXML
	private ImageView C32;
	@FXML
	private ImageView C33;
	
	@FXML
	private ImageView C35;
	@FXML
	private ImageView C36;
	@FXML
	private ImageView C37;
	@FXML
	private ImageView C38;
	@FXML
	private ImageView C39;
	@FXML
	private ImageView C40;
	@FXML
	private ImageView C41;
	@FXML
	private ImageView C42;
	@FXML
	private ImageView C43;
	@FXML
	private ImageView C44;
	@FXML
	private ImageView C45;
	@FXML
	private ImageView C46;
	@FXML
	private ImageView C47;
	@FXML
	private ImageView C48;
	
	@FXML
	private Separator v1,v2,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15;
	@FXML
	private Separator h1,h2,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16;

	@FXML
	private ImageView constructeur;
	@FXML
	private ImageView constructeur1;
	@FXML
	private ImageView travailleur;
	@FXML
	private ImageView canalBleu;
	@FXML
	private ImageView canalConstruction;
	
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
	private Label consigne;
	@FXML
	private TextField montantMise;
	@FXML
	private TextField soudoiement;
	@FXML
	private TextField message;
	@FXML
	private HBox listePiles;
	@FXML
	private Label nomCons;
	@FXML
	private Button passerSoudoiement;
	@FXML
	private Button soudoyer;
	@FXML
	private Button soutenir;
	@FXML
	private Button envoyer;
	@FXML
	private ComboBox<ProposerMise> momntantSoutien;
	@FXML
	private VBox partieMise;
	@FXML
	private VBox partieSoudoiement;
	
	@FXML
	private VBox chatj;
	
	private Joueur joueur;
	
	public void loadfichejoueur(Partie partie, Joueur joueur){
		
	}
	
	public boolean verifEnchere(int montant)throws RemoteException{
		santiago = Santiago.getSantiago();
		PartieInterface server;
		try {
			server = (PartieInterface)Naming.lookup("rmi://localhost:5755/jeu");
			for(int i=0;i<server.getEncheresCourantes().size();i++)
			{
				if(server.getEncheresCourantes().get(i).getMontant()==montant)
					return false;
			}
			return true;
		} catch (MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
 			return false;
		}
		
		
	}
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("PLATEAU: SANTIAGO");
		//On récupere le joueur et le plateau
		
					
				try {
					PartieInterface server=(PartieInterface)Naming.lookup("rmi://localhost:5755/jeu");
					numTour.setText(server.getTour()+" / "+String.valueOf(server.getMaxTour()));
					numPhase.setText(String.valueOf(server.getPhase()));
					nomCons.setText(server.getConstructeur().getNom_joueur());
					for(Joueur j : server.getJoueurs()){
						if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
							joueur=j;
					}
					
					 PileTuile pilep;
					 String plante;
					 int nbtag;
					 Image image;
					 ImageView pile;
					 /**
					  * ***********************************
					  * *********PHASE 1*******************
					  * ***********************************
					  */
					
						ArrayList<PileTuile>piles=server.getListe_piles();
						if(piles.size()==4)
							pile5.setVisible(false);
						for(int i=0;i<piles.size();i++)
						{
							pilep=piles.get(i);
							plante=pilep.getTuiles().get(0).getPlante();
							nbtag=pilep.getTuiles().get(0).getTag_necessaires();
							pile=(ImageView) listePiles.getChildren().get(i);
							//listePiles.getChildren().remove(i);
							switch (plante){
								case "piment":
									
									if(nbtag==2){
										image=new Image("@../../img/TuPiment1.png",50,47,false,false);
									}
									else{
										image=new Image("@../../img/TuPiment2.png",50,47,false,false);
									}
									pile.setImage(image);
									break;
								case "banane":
									if(nbtag==2){
										image=new Image("@../../img/TuBanane1.png",50,47,false,false);
										
									}
									else{
										image=new Image("@../../img/TuBanane2.png",50,47,false,false);
										
									}
									pile.setImage(image);
									break;
								case "patate":
									if(nbtag==2){
										image=new Image("@../../img/TuPDT1.png",50,47,false,false);
										
									}
									else{
										image=new Image("@../../img/TuPDT2.png",50,47,false,false);
										
									}pile.setImage(image);
									break;
								case "haricot":
									if(nbtag==2){
										image=new Image("@../../img/TuHaricots2.png",50,47,false,false);
										
									}
									else{
										image=new Image("@../../img/TuHaricots1.png",50,47,false,false);
									}
									case "canne":
									if(nbtag==2){
										image=new Image("@../../img/TuCanne2.png",50,47,false,false);
										
									}
									else{
										image=new Image("@../../img/TuCanne1.png",50,47,false,false);
										
									}pile.setImage(image);
									break;
									}
							}
					
					//*************MISER********************************
						miser.setOnAction(event ->{
							System.out.println('1');
							if(Integer.parseInt(montantMise.getText())>joueur.getCagnotte()){
								JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'escudos pour miser cette somme");
							}
							else{System.out.println('2');
								int montant=Integer.parseInt(montantMise.getText());
								if(montant==0){
									try {System.out.println('3');
										server.passerMise(joueur);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								else{
									try {System.out.println('4');
										if(0<server.getEncheresCourantes().size()){
											if(verifEnchere(montant)==true){
												JOptionPane.showMessageDialog(null, "Enchere Validée");
												server.miser(joueur,montant);
												}
											else{
												JOptionPane.showMessageDialog(null, "Il existe déja une enchère pour ce montant: veuillez entrer un autre montant ou passer");
												}
										}
										else{
											JOptionPane.showMessageDialog(null, "Enchere Validée");
											server.miser(joueur,montant);
										}
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							}});
					
					
					//****************PASSER*****************************	
					passerMise.setOnAction(event -> {
						try {
							server.passerMise(santiago.getClient().getJoueur());
							partieMise.setDisable(true);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								}		
									
							});
						
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch (RemoteException e) {
							e.printStackTrace();
						}

		/**
		 * ********************************************************
		 * ***************************************FICHE JOUEUR*****
		 * ********************************************************
		 */
				
		NomJoueur.setText(joueur.getNom_joueur());
		montantCagnotte.setText(String.valueOf(joueur.getCagnotte())+"escudos");
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
		
	
		quitter.setOnAction(event -> {
			
			//Si la personne est connectée 
			if(santiago.getClient()!=null){
					try {
					PartieInterface server;
					server = (PartieInterface)Naming.lookup("rmi://localhost:5755/jeu");
					for(Joueur j : server.getJoueurs()){
						if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
							joueur=j;
						
					}
					//Lorsqu'on quitte le jeu, on  deconnecte le joueur déja connecté.
					server.getClient().remove(santiago.getClient());
					server.getJoueurs().remove(joueur);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.close();
			});
		sauvegarder.setOnAction(event -> dialog.close());
		activerchat.setOnAction(event -> dialog.close());
		regles.setOnAction(event -> dialog.close());
		}

	
}
