package presentation.control;

import java.awt.event.KeyEvent;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PlateauUIController extends DialogUIController{
	///private Stage primaryStage;
	private RegleUIController ReglesController;
	private Stage primaryStage;
	private static final String REGLES="../view/reglesView.fxml";
	
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
	private ImageView C34;
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
	private Separator v1;
	@FXML
	private Separator v2;
	@FXML
	private Separator v3;
	@FXML
	private Separator v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15;
	@FXML
	private Separator h1;
	@FXML
	private Separator h2;
	@FXML
	private Separator  h3;
	@FXML
	private Separator h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16;

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
	private Label consigne;
	@FXML
	private TextField montantaMiser;
	@FXML
	private TextField soudoiement;
	@FXML
	private TextField message;
	@FXML
	private HBox listePiles;
	@FXML
	private HBox tag1;
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
	
	@FXML
	private HBox puces;
	@FXML
	private RadioButton p1;
	@FXML
	private RadioButton p2;
	@FXML
	private RadioButton p3;
	@FXML
	private RadioButton p4;
	@FXML
	private RadioButton p5;
	
	private Joueur joueur;
	

	public boolean verifEnchere(int montant)throws RemoteException{
		santiago = Santiago.getSantiago();
		PartieInterface server;
		try {
			String host=santiago.getClient().getHost();
			server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
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

	public void bouger(ImageView tuile, ImageView parcelle){
		Image img=tuile.getImage();
		parcelle.setImage(img);
	}
	public void phase6(){
		//PHASE 6
		String host=santiago.getClient().getHost();
		PartieInterface server;
		try {
			server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
		
		ArrayList<TuilePlantation> tab=server.phase6();
		if(tab.size()>0)
		for(TuilePlantation t:tab)
		{		
			int x=t.getSourceX();
			int y=t.getSourceX();
			Parcelle p=server.getPlateau().get(x,y);
			int id=p.getIdParcelle();
			switch(id){
			case 1:
				C1.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			case 2:
				C2.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			case 3:
				C3.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			case 4:
				C4.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			case 5:
				C5.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			case 6:
				C6.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			case 7:
				C7.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
				break;
			//.....Jusque 48
			}}
		else{
			JOptionPane.showMessageDialog(null, "Toute les plantations sont irrigu�e");
		}
		server.phaseSuivante();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void phase7(){
		String host=santiago.getClient().getHost();
		PartieInterface server;
		try {
			server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
			for(Joueur j : server.getJoueurs()){
				if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
					joueur=j;
			}
			server.jouerPhase();
			montantCagnotte.setText(String.valueOf(joueur.getCagnotte()));
			server.tourSuivant();
			initStage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void phase2(){
		String host=santiago.getClient().getHost();
		PartieInterface server;
			try {
			server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
			server.jouerPhase();
			numPhase.setText(String.valueOf(server.getPhase()));
			for(Joueur j : server.getJoueurs()){
				if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
					joueur=j;
			}
			if(!joueur.getEst_constructeurdecanal())
					constructeur.setVisible(false);
			nomCons.setText(server.getConstructeur().getNom_joueur());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		
		public void initStage(){
	
	String host=santiago.getClient().getHost();
	PartieInterface server;
	try {
		server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
	
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
	 /**
		 * ********************************************************
		 * ***************************************FICHE JOUEUR*****
		 * ********************************************************
		 */
				
		NomJoueur.setText(joueur.getNom_joueur());
		montantCagnotte.setText(String.valueOf(joueur.getCagnotte())+"escudos");
		nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()));
		partieMise.setDisable(false);
		if(joueur.getCanal_bleu()){
			nbCanaux.setText(String.valueOf(1));
		}		
		else
			canalBleu.setVisible(false);

		if(!joueur.getEst_constructeurdecanal())
			constructeur.setVisible(false);
		ArrayList<PileTuile>piles=server.getListe_piles();
		if(piles.size()==4){
			pile5.setVisible(false);
			//p5.setVisible(false);
		}
		
		
		for(int i=0;i<piles.size();i++)
		{
			
			pilep=piles.get(i);
			plante=pilep.getTuiles().get(0).getPlante();
			nbtag=pilep.getTuiles().get(0).getTag_necessaires();
			if(!pilep.getTuiles().get(0).getVisible()){
				pile=(ImageView) listePiles.getChildren().get(i);
				image=new Image("@../../img/DosTuiles.png",50,47,false,false);
				pile.setImage(image);
			}
			else{
			pile=(ImageView) listePiles.getChildren().get(i);
			
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
			}}
		
		remplirCase(C1,0,0);
		remplirCase(C2,0,1);
		remplirCase(C3,0,2);
		remplirCase(C4,0,3);
		remplirCase(C5,0,4);
		remplirCase(C6,0,5);
		remplirCase(C7,0,6);
		remplirCase(C8,0,7);
		remplirCase(C9,1,0);
		remplirCase(C10,1,1);
		remplirCase(C11,1,2);
		remplirCase(C12,1,3);
		remplirCase(C13,1,4);
		remplirCase(C14,1,5);
		remplirCase(C15,1,6);
		remplirCase(C16,1,7);
		remplirCase(C17,2,0);
		remplirCase(C18,2,1);
		remplirCase(C19,2,2);
		remplirCase(C20,2,3);
		remplirCase(C21,2,4);
		remplirCase(C22,2,5);
		remplirCase(C23,2,6);
		remplirCase(C24,2,7);
		remplirCase(C25,3,0);
		remplirCase(C26,3,1);
		remplirCase(C27,3,2);
		remplirCase(C28,3,3);
		remplirCase(C29,3,4);
		remplirCase(C30,3,5);
		remplirCase(C31,3,6);
		remplirCase(C32,3,7);
		remplirCase(C33,4,0);
		remplirCase(C34,4,1);
		remplirCase(C35,4,2);
		remplirCase(C36,4,3);
		remplirCase(C37,4,4);
		remplirCase(C38,4,5);
		remplirCase(C39,4,6);
		remplirCase(C40,4,7);
		remplirCase(C41,5,0);
		remplirCase(C42,5,1);
		remplirCase(C43,5,2);
		remplirCase(C44,5,3);
		remplirCase(C45,5,4);
		remplirCase(C46,5,5);
		remplirCase(C47,5,6);
		remplirCase(C48,5,7);
		
		
	} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
		
		public void mettrePile(ImageView C1,int x,int y){
			
			String host=santiago.getClient().getHost();
			PartieInterface server;
			try {
				server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
				
				ColorAdjust colorAdjust=new ColorAdjust();
				colorAdjust.setBrightness(-0.5);
				
				C1.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
					C1.setEffect(colorAdjust);
			try {
				if(server.getPhase()==3)
				{
					//P1
					if((p1.isSelected()&& !p2.isSelected() && !p3.isSelected() && !p4.isSelected())
							||(!p1.isSelected()&& p2.isSelected() && !p3.isSelected() && !p4.isSelected())
							||(!p1.isSelected()&& !p2.isSelected() && p3.isSelected() && !p4.isSelected())
							||(!p1.isSelected()&& !p2.isSelected() && !p3.isSelected()&& p4.isSelected())
							||(!p1.isSelected()&& !p2.isSelected() && !p3.isSelected()&& p4.isSelected()))
							{
						try {
							joueur.setCagnotte(joueur.getCagnotte()-server.getEncheresCourantes().get(joueur.getRang()-1).getMontant());
							if (server.getPlateau().get(0,0).getOccupee())
								JOptionPane.showMessageDialog(null, "Parcelle occcup�e, choisir une autre parcelle");
							else if(p1.isSelected() && !server.getPlateau().get(0,0).getOccupee()&& server.getListe_piles().get(0).getTuiles().get(0).getVisible()){
								TuilePlantation tuile = server.getListe_piles().get(0).getTuiles().get(0);
								server.getListe_piles().get(0).getTuiles().remove(0);
								joueur.getTuilesjoueur().add(tuile);
								int tag;
								if(server.getEncheresCourantes().get(joueur.getRang()-1).getMontant()!=0){
									tag=tuile.getTag_necessaires()-tuile.getTag_presents();
								}else{
									tag=tuile.getTag_necessaires()-tuile.getTag_presents()-1;
								}
								if(joueur.getNb_tag()-tag>0){
									
									joueur.setNb_tag(joueur.getNb_tag()-tag);
									tuile.setTag_presents(tag);
									tuile.setSourceX(x);
									tuile.setSourceY(y);
									server.getPlateau().get(x,y).setOccupee(true);
									nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()-tag));
									
										//tag1.getChildren().add(img.setImage(travailleur.getImage()));
								}
								
								bouger(pile1,C1);
								pile1.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
								
								if(server.getJoueurs().size()==3 && joueur.equals(server.getJoueurs().get(0))){
									JOptionPane.showMessageDialog(null, "Vous avez eu la plus grosse mise, placer la derni�re tuile");	
								}
								else
									puces.setDisable(true);
							}
							//p2
							else if(p2.isSelected() && !server.getPlateau().get(0,1).getOccupee()&& server.getListe_piles().get(0).getTuiles().get(0).getVisible()){
								TuilePlantation tuile = server.getListe_piles().get(1).getTuiles().get(0);
								server.getListe_piles().get(1).getTuiles().remove(0);
								joueur.getTuilesjoueur().add(tuile);
								int tag;
								if(server.getEncheresCourantes().get(joueur.getRang()-1).getMontant()!=0){
									tag=tuile.getTag_necessaires()-tuile.getTag_presents();
								}else{
									tag=tuile.getTag_necessaires()-tuile.getTag_presents()-1;
								}
								if(joueur.getNb_tag()-tag>0){
									
									joueur.setNb_tag(joueur.getNb_tag()-tag);
									tuile.setTag_presents(tag);
									tuile.setSourceX(x);
									tuile.setSourceY(y);
									server.getPlateau().get(x,y).setOccupee(true);
									
									nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()-tag));
									
									
										//tag1.getChildren().add(img.setImage(travailleur.getImage()));
								}
								
								bouger(pile2,C1);
								pile2.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
								
								if(server.getJoueurs().size()==3 && joueur.equals(server.getJoueurs().get(0))){
									JOptionPane.showMessageDialog(null, "Vous avez eu la plus grosse mise, placer la derni�re tuile");	
								}
								else
									puces.setDisable(true);
							}
							//p3
							else if(p3.isSelected() && !server.getPlateau().get(0,2).getOccupee()&& server.getListe_piles().get(0).getTuiles().get(0).getVisible()){
								TuilePlantation tuile = server.getListe_piles().get(2).getTuiles().get(0);
								server.getListe_piles().get(2).getTuiles().remove(0);
								joueur.getTuilesjoueur().add(tuile);
								int tag;
								if(server.getEncheresCourantes().get(joueur.getRang()-1).getMontant()!=0){
									tag=tuile.getTag_necessaires()-tuile.getTag_presents();
								}else{
									tag=tuile.getTag_necessaires()-tuile.getTag_presents()-1;
								}
								if(joueur.getNb_tag()-tag>0){
									
									joueur.setNb_tag(joueur.getNb_tag()-tag);
									tuile.setTag_presents(tag);
									tuile.setSourceX(x);
									tuile.setSourceY(y);
									server.getPlateau().get(x,y).setOccupee(true);
									server.phaseSuivante();
									
									nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()-tag));
									
									
										//tag1.getChildren().add(img.setImage(travailleur.getImage()));
								}
								
								bouger(pile3,C1);
								pile3.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
								
								if(server.getJoueurs().size()==3 && joueur.equals(server.getJoueurs().get(0))){
									JOptionPane.showMessageDialog(null, "Vous avez eu la plus grosse mise, placer la derni�re tuile");	
								}
								else
									puces.setDisable(true);
							}
							
							//p4
							else if(p4.isSelected() && !server.getPlateau().get(0,3).getOccupee()&& server.getListe_piles().get(0).getTuiles().get(0).getVisible()){
								TuilePlantation tuile = server.getListe_piles().get(3).getTuiles().get(0);
								server.getListe_piles().get(3).getTuiles().remove(0);
								joueur.getTuilesjoueur().add(tuile);
								int tag;
								if(server.getEncheresCourantes().get(joueur.getRang()-1).getMontant()!=0){
									tag=tuile.getTag_necessaires()-tuile.getTag_presents();
								}else{
									tag=tuile.getTag_necessaires()-tuile.getTag_presents()-1;
								}
								if(joueur.getNb_tag()-tag>0){
									
									joueur.setNb_tag(joueur.getNb_tag()-tag);
									tuile.setTag_presents(tag);
									tuile.setSourceX(x);
									tuile.setSourceY(y);
									server.getPlateau().get(x,y).setOccupee(true);
									server.phaseSuivante();
									
									nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()-tag));
									
									
										//tag1.getChildren().add(img.setImage(travailleur.getImage()));
								}
								
								bouger(pile4,C1);
								pile4.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
								
								if(server.getJoueurs().size()==3 && joueur.equals(server.getJoueurs().get(0))){
									JOptionPane.showMessageDialog(null, "Vous avez eu la plus grosse mise, placer la derni�re tuile");	
								}
								else
									puces.setDisable(true);
							}
							//p4
							else if(p5.isSelected() && !server.getPlateau().get(0,4).getOccupee()&& server.getListe_piles().get(0).getTuiles().get(0).getVisible()){
								TuilePlantation tuile = server.getListe_piles().get(4).getTuiles().get(0);
								server.getListe_piles().get(4).getTuiles().remove(0);
								joueur.getTuilesjoueur().add(tuile);
								int tag;
								if(server.getEncheresCourantes().get(joueur.getRang()-1).getMontant()!=0){
									tag=tuile.getTag_necessaires()-tuile.getTag_presents();
								}else{
									tag=tuile.getTag_necessaires()-tuile.getTag_presents()-1;
								}
								if(joueur.getNb_tag()-tag>0){
									
									joueur.setNb_tag(joueur.getNb_tag()-tag);
									tuile.setTag_presents(tag);
									tuile.setSourceX(x);
									tuile.setSourceY(y);
									server.getPlateau().get(x,y).setOccupee(true);
									
									nbConstructeurs.setText(String.valueOf(joueur.getNb_tag()-tag));
									
									
										//tag1.getChildren().add(img.setImage(travailleur.getImage()));
								}
								
								bouger(pile5,C1);
								pile5.setImage(new Image("@../../img/DosTuiles.png",50,47,false,false));
								
								if(server.getJoueurs().size()==3 && joueur.equals(server.getJoueurs().get(0))){
									JOptionPane.showMessageDialog(null, "Vous avez eu la plus grosse mise, placer la derni�re tuile");	
								}
								else
									puces.setDisable(true);
							}
							
							else{
								JOptionPane.showMessageDialog(null, "La tuile n'a pas encore �t� retourn�e"+server.getListe_piles().get(0).getTuiles().get(0).getVisible());
								if(server.getJoueurs().size()==3 && joueur.equals(server.getJoueurs().get(0))){
									puces.setDisable(true);
									}
							}
							
							
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					else{
						JOptionPane.showMessageDialog(null, "Selectioner qu'une seule pile");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Attendre la phase 3");
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
					
			});
				
			} catch (MalformedURLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (NotBoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	
		}
		
		public void mettreFossee(Separator s){
			s.setStyle("-fx-background-color: #409CC8; -fx-translate-y: 3;");
			
			
		}
		
		public void remplirCase(ImageView c,int x,int y){
			String host=santiago.getClient().getHost();
			PartieInterface server;
			Image image;
			
			try {
				server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
				for(Joueur j:server.getJoueurs()){
					
					for(int i=0; i<j.getTuiles_joueur().size();i++){
						int nbtag=j.getTuiles_joueur().get(i).getTag_necessaires();
						String plante=j.getTuiles_joueur().get(i).getPlante();
						switch(plante){
						case "piment":
							
							if(nbtag==2){
								image=new Image("@../../img/TuPiment1.png",50,47,false,false);
							}
							else{
								image=new Image("@../../img/TuPiment2.png",50,47,false,false);
							}
							c.setImage(image);
							break;
						case "banane":
							if(nbtag==2){
								image=new Image("@../../img/TuBanane1.png",50,47,false,false);
								
							}
							else{
								image=new Image("@../../img/TuBanane2.png",50,47,false,false);
								
							}
							c.setImage(image);
							break;
						case "patate":
							if(nbtag==2){
								image=new Image("@../../img/TuPDT1.png",50,47,false,false);
								
							}
							else{
								image=new Image("@../../img/TuPDT2.png",50,47,false,false);
								
							}c.setImage(image);
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
								
							}c.setImage(image);
							break;
							
						}
					}
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	public void initialize(URL location, ResourceBundle resources) {
		santiago = Santiago.getSantiago();
		dialog = new Stage(StageStyle.DECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.setTitle("PLATEAU: SANTIAGO");
		//On r�cupere le joueur et le plateau
		
				try {
		
					String host=santiago.getClient().getHost();
					PartieInterface server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
					initStage();
					
					ColorAdjust colorAdjust=new ColorAdjust();
					colorAdjust.setBrightness(-0.5);
					//*************FOSSES********************************
					
					//*************PARCELLES********************************
					/**
					 * Phase 3:
					 * 1)Redefinir ordre de passage
					 * 2)Payer
					 * 
					 */
										
					
					mettrePile(C1,0,0);
					mettrePile(C2,0,1);
					mettrePile(C3,0,2);
					mettrePile(C4,0,3);
					mettrePile(C5,0,4);
					mettrePile(C6,0,5);
					mettrePile(C7,0,6);
					mettrePile(C8,0,7);
					mettrePile(C9,1,0);
					mettrePile(C10,1,1);
					mettrePile(C11,1,2);
					mettrePile(C12,1,3);
					mettrePile(C13,1,4);
					mettrePile(C14,1,5);
					mettrePile(C15,1,6);
					mettrePile(C16,1,7);
					mettrePile(C17,2,0);
					mettrePile(C18,2,1);
					mettrePile(C19,2,2);
					mettrePile(C20,2,3);
					mettrePile(C21,2,4);
					mettrePile(C22,2,5);
					mettrePile(C23,2,6);
					mettrePile(C24,2,7);
					mettrePile(C25,3,0);
					mettrePile(C26,3,1);
					mettrePile(C27,3,2);
					mettrePile(C28,3,3);
					mettrePile(C29,3,4);
					mettrePile(C30,3,5);
					mettrePile(C31,3,6);
					mettrePile(C32,3,7);
					mettrePile(C33,4,0);
					mettrePile(C34,4,1);
					mettrePile(C35,4,2);
					mettrePile(C36,4,3);
					mettrePile(C37,4,4);
					mettrePile(C38,4,5);
					mettrePile(C39,4,6);
					mettrePile(C40,4,7);
					mettrePile(C41,5,0);
					mettrePile(C42,5,1);
					mettrePile(C43,5,2);
					mettrePile(C44,5,3);
					mettrePile(C45,5,4);
					mettrePile(C46,5,5);
					mettrePile(C47,5,6);
					mettrePile(C48,5,7);
					
					//*************MISER********************************
						miser.setOnAction(event ->{
							
						try {
							if(server.getPhase()==1){
								int montant=0;
								try{
									montant=Integer.parseInt(montantaMiser.getText());
								}catch(NumberFormatException nfe){ 
									
									System.out.println("Exception : "+nfe);
								}
								if(montant>joueur.getCagnotte()){
									JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'escudos pour miser cette somme");
								}
								else{System.out.println('2');
									if(montant==0){
										try {
											server.passerMise(joueur);
											partieMise.setDisable(true);
											
											montantCagnotte.setText(String.valueOf(joueur.getCagnotte()-montant)+"escudos");
											
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									else{
										try {System.out.println('4');
											if(0<server.getEncheresCourantes().size()){
												if(verifEnchere(montant)==true){
													JOptionPane.showMessageDialog(null, "Enchere Valid�e");
													
													server.miser(joueur,montant);
													partieMise.setDisable(true);
													numPhase.setText("3");
													montantCagnotte.setText(String.valueOf(joueur.getCagnotte()-montant)+"escudos");
													
													}
												else{
													JOptionPane.showMessageDialog(null, "Il existe d�ja une ench�re pour ce montant: veuillez entrer un autre montant ou passer");
													}
											}
											else{
												JOptionPane.showMessageDialog(null, "Enchere Valid�e");
												server.miser(joueur,montant);
												partieMise.setDisable(true);
												numPhase.setText("3");
												montantCagnotte.setText(String.valueOf(joueur.getCagnotte()-montant)+"escudos");
												
											}
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								}
								}else
									JOptionPane.showMessageDialog(null, "Attendre Phase 1");
							
									
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
				});
					
					
					//****************PASSER*****************************	
					passerMise.setOnAction(event -> {
					
							try {
								if(server.getPhase()==1){
										try {
								server.passerMise(santiago.getClient().getJoueur());
								partieMise.setDisable(true);
								numPhase.setText("3");
								montantCagnotte.setText(String.valueOf(joueur.getCagnotte())+" escudos");
								
											} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
									}		
										
								}
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
				regles.setOnAction(event->{
					ReglesController = RegleUIController.initDialog(
						REGLES, RegleUIController.class,
						primaryStage);
					ReglesController.showAndWait();
				});		
				
		
		
				//****************QUITTER*****************************	
		quitter.setOnAction(event -> {
			
			//Si la personne est connect�e 
			if(santiago.getClient()!=null){
					try {
					String host=santiago.getClient().getHost();
					PartieInterface server = (PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
					for(Joueur j : server.getJoueurs()){
						if(j.getNom_joueur().equals(santiago.getClient().getJoueur().getNom_joueur()))
							joueur=j;
						
					}
					//Lorsqu'on quitte le jeu, on  deconnecte le joueur d�ja connect�.
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
		}

	
}
