import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Plateau extends Application {
	AnchorPane anchorPrincipal = new AnchorPane();
	BorderPane rootPrincipal = new BorderPane();
   
    
    public VBox tuilesEtJr(){ //partieCommune
		VBox vbPartieCommune = new VBox();
		
		//SECTION TUILES : 
		Text tTuiles = new Text("Piles de tuiles :");
		TextFlow tfTuiles = new TextFlow(tTuiles);
        tTuiles.setFont(Font.font("Lucida Bright", 15));
        tTuiles.setStyle("-fx-font-weight: bold;");
		GridPane tuiles = new GridPane();
		vbPartieCommune.setStyle("-fx-padding:15px;");
		
		//repartition des piles de tuiles
		int nbPiles = 5;
		for( int i=0; i<nbPiles; i++){
			Image imDosTuile = new Image("img/DosTuiles.png");
			ImageView ivDosTuile = new ImageView();
			ivDosTuile.setImage(imDosTuile);
			tuiles.add(ivDosTuile,i,0);
		}
		tfTuiles.setPadding(new Insets(0,0,10,0));

		//Création de boutons radio permettant de choisir une tuile
		ToggleGroup groupe = new ToggleGroup();
		RadioButton tuile1 = new RadioButton();
		RadioButton tuile2 = new RadioButton();
		RadioButton tuile3 = new RadioButton();
		RadioButton tuile4 = new RadioButton();
		RadioButton tuile5 = new RadioButton();
		
        tuile1.setToggleGroup(groupe);
        tuile2.setToggleGroup(groupe);
        tuile3.setToggleGroup(groupe);
        tuile4.setToggleGroup(groupe);
        tuile5.setToggleGroup(groupe);
        tuile1.setFocusTraversable(false);
        tuile2.setFocusTraversable(false);
        tuile3.setFocusTraversable(false);
        tuile4.setFocusTraversable(false);
        tuile5.setFocusTraversable(false);

        tuile1.setSelected(true);//le piano est l'instrument sélectionné par défaut
		tuiles.add(tuile1,0,1);
		tuiles.add(tuile2,1,1);
		tuiles.add(tuile3,2,1);
		tuiles.add(tuile4,3,1);
		tuiles.add(tuile5,4,1);
		
		Button choisir = new Button("Choisir");
		choisir.setStyle("-fx-background-color:#D2B48C; -fx-color:#2E8B57;");
		//choix de la tuile !
		choisir.setOnAction(new EventHandler<ActionEvent>() { 
	
		    public void handle(ActionEvent actionEvent) { 
		        System.out.println("Salut le monde !"); 
		    } 
		});
		tuiles.add(choisir,5, 1);
		
		
		//SECTION JOUEURS :
		Text tJr = new Text ("Joueurs : ");
		TextFlow tfJr = new TextFlow(tJr);
        tJr.setFont(Font.font("Lucida Bright", 15));
        tJr.setStyle("-fx-font-weight: bold;");
		GridPane joueurs = new GridPane();

		int nbJrs=5 ; //remplacer par la liste des joueurs et parcourir la liste
		boolean estCon =false;
		for (int i = 0; i<nbJrs;i++){
			int j = i+1;
			Text t = new Text("Joueur "+j+"       ");
	        t.setFont(Font.font("Lucida Bright", 12));
			TextFlow tf = new TextFlow(t);
			joueurs.add(tf,i, 0);
			if(estCon==true){
				Image im2 = new Image("img/figurineConstructeur.png");
				ImageView iv2 = new ImageView();
				iv2.setImage(im2);
				joueurs.add(iv2,i,1);	
			}else{
				Image im2 = new Image("img/joueurNormal.png");
				ImageView iv2 = new ImageView();
				iv2.setImage(im2);
				joueurs.add(iv2,i,1);
			}

			
			if(i==3){
				estCon=true;
			}
		}
		joueurs.setStyle("-fx-padding:15px");

		tfJr.setPadding(new Insets(20,0,5,0));
		vbPartieCommune.getChildren().addAll(tfTuiles,tuiles,tfJr,joueurs);
		vbPartieCommune.setStyle("-fx-padding:15px;");
		return vbPartieCommune;	
    }
    
    public AnchorPane ficheJr(){
    	AnchorPane ap = new AnchorPane();
    	BorderPane b = new BorderPane();
    	b.setStyle("-fx-background-color: #FFFFFF;-fx-border-color: #000000; -fx-border-radius:5px; -fx-border-style: solid; -fx-border-width: 1.5px;");
    	b.setVisible(true);
    	b.setPadding(new Insets(5, 5, 0, 5));
    	
    	Text t = new Text("Fiche Joueur");
    	t.setFont(Font.font("Lucida Bright", 20));
    	t.setStyle("-fx-font-weight: bold;");
    	TextFlow tfFicheJ = new TextFlow(t);
    	tfFicheJ.setTextAlignment(TextAlignment.CENTER);
    	b.setTop(tfFicheJ);
    	
    	VBox vbFicheJoueur = new VBox();
    	
    	//Section MA CAGNOTTE    	
    	Text txtCagnote = new Text("Ma cagnotte");
    	txtCagnote.setFont(Font.font("Lucida Bright", 15));
    	txtCagnote.setStyle("-fx-font-weight: bold;");
    	HBox hbCagnote = new HBox();
    	hbCagnote.setStyle("-fx-padding:15px;");
    	
    	VBox vbCagnotte =new VBox();
    	vbCagnotte.getChildren().addAll(txtCagnote,hbCagnote);
    	vbCagnotte.setStyle("-fx-padding:15px;");
    	
    	//remplir la cagnotte par les billets du joueur : 
    	try{
    		Text t1 = new Text ("1 x ");
			Image im1 = new Image("img/1.jpg");
			ImageView iv1 = new ImageView();
			iv1.setImage(im1);
			
    		Text t2 = new Text ("1 x ");
			Image im2 = new Image("img/2.jpg");
			ImageView iv2 = new ImageView();
			iv2.setImage(im2);
			
			Text t5 = new Text ("1 x ");
			Image im5 = new Image("img/5.jpg");
			ImageView iv5 = new ImageView();
			iv5.setImage(im5);
			
			Text t20 = new Text ("1 x ");
			Image im20 = new Image("img/20.jpg");
			ImageView iv20 = new ImageView();
			iv20.setImage(im20);
			
			Text t50 = new Text ("1 x ");
			Image im50 = new Image("img/50.jpg");
			ImageView iv50 = new ImageView();
			iv50.setImage(im50);
			
			hbCagnote.getChildren().addAll(t1,iv1,t2,iv2,t5,iv5,t20,iv20,t50,iv50);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

    	//Section mes travailleurs
    	Text txtTravailleurs = new Text("Mes travailleurs");
    	txtTravailleurs.setFont(Font.font("Lucida Bright", 15));
    	txtTravailleurs.setStyle("-fx-font-weight: bold;");
    	
    	HBox hbTr1 = new HBox();
    	HBox hbTr2 = new HBox();
    	VBox vbTr = new VBox(); // contiendra les différentes HBox des travailleurs
    	vbTr.getChildren().addAll(hbTr1,hbTr2);
    	vbTr.setStyle("-fx-padding:15px;");

    	
    	String couleur ="blanc"; //le remplacer par la couleur du joueur
    	String url ="img/T"+couleur+".png";
    		
    	int nbtravailleurs = 22 ; //nb de travailleurs restant au joueur :
		int j = nbtravailleurs;
    	
    	int itteration = 1;
		while(j>0){
			int max;
			if (j>11){
				max=11;
			}
			else{
				max=j;
			}
	    	for (int i=1; i<=max ; i++){
	        	Image imTr =new Image(url);
	    		ImageView tr = new ImageView();
	    		tr.setImage(imTr);
	    		
	    		if(itteration==1){
		      		hbTr1.getChildren().add(tr);
	    		}
	    		else {
	    			hbTr2.getChildren().add(tr);
	    		}

	       		j--;
	    	}
	    	itteration++;
		}
    
		//Section mes canaux
    	Text txtCanaux = new Text("Mes Canaux");
    	txtCanaux.setFont(Font.font("Lucida Bright", 15));
    	txtCanaux.setStyle("-fx-font-weight: bold;");

    	VBox vbCanaux = new VBox();
    	HBox hbCanaux1 = new HBox();
    	vbCanaux.getChildren().add(hbCanaux1);

    	vbCanaux.setStyle("-fx-padding:15px;");
    	
    	boolean possède =true; //le joueur a-t-il encore son canal de couleur?
    	String url2 = new String("img/C"+couleur+".png");
    	//ajout du canal de couleur :
    	if (possède==true){
    		Image imTr =new Image(url2);
			ImageView tr = new ImageView();
			tr.setImage(imTr);
			hbCanaux1.getChildren().add(tr);
    	}
    	
    	//ajout des canaux bleu :
    	int nbCanauxbleu = 10;
    	int k = nbCanauxbleu;
    	
    	while(k>0){
    		int max;
			if (k>5){
				max=k;
			}
			else{
				max=k;
			}
			for(int i =1; i<=max ; i++){
	    		Image imTr =new Image("img/CanalBleu.png");
				ImageView tr = new ImageView();
				tr.setImage(imTr);
				hbCanaux1.getChildren().add(tr);					
				k--;
	    	}
    	}
    	b.setCenter(vbFicheJoueur);
    	vbFicheJoueur.getChildren().addAll(vbCagnotte,txtTravailleurs,vbTr,txtCanaux,vbCanaux);
    
    	ap.getChildren().add(b);
    	return ap;
    }
    
    public AnchorPane plateau(){
    	AnchorPane pl=new AnchorPane();
    	
    	GridPane plateau = new GridPane();
    	plateau.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    	plateau.setAlignment(null);
    	plateau.setCenterShape(true);
    	plateau.setPadding(new Insets(0,15,0,15));
    	plateau.setGridLinesVisible(true);
    	
    	for (int i=0;i<13;i++){
    		for (int j=0;j<15;j++){
    			Image imTr =new Image("img/parcelle.jpg");
	    		ImageView tr = new ImageView();
	    		tr.setImage(imTr);
	    		plateau.add(tr, j, i);
    		}
    	}
    	pl.getChildren().add(plateau);
    	return pl;
    }
    
    
    public TextFlow left(){
    	Text t = new Text("But du jeu : \n");
    	t.setFont(Font.font("Lucida Bright", 15));
    	t.setStyle("-fx-font-weight: bold;");
    	t.setTextAlignment(TextAlignment.CENTER);

    	Text rdj = new Text("\n- Chaque joueur achète aux "
    			+ "\nenchères des plantations (Pommes de"
    			+ "\nterre..) et essaye, d'en constituer"
    			+ "\nle plus grand champs possible ! "
    			+ "\n \n-Pour éviter que les plantations "
    			+ "\nne perdent de leur rendement ou ne "
    			+ "\nse dessèchent complètement, il faut "
    			+ "\nque vous les irriguiez en les"
    			+ "\nraccordant au réseau de canaux."
    			+ "\nLe versement de pots de vin au "
    			+ "\n constructeur de canaux est "
    			+ "\nindispensable pour obtenir de"
    			+ "\nnouveaux canaux en direction de ses"
    			+ "\npropres plantations. Plutôt on"
    			+ "\nirrigue ses plantations, et plus le"
    			+ "\nchamp (composé d'une même culture) "
    			+ "\nest important, plus les rendements, "
    			+ "\net donc les revenus, seront importants."
    			+ "\n\n-Le vainqueur sera le joueur, qui sera "
    			+ "\nle plus habile, dans l'achat des "
    			+ "\nplantations, dans leurs irrigations et"
    			+ "\ndans le développement des champs.");
    	rdj.setFont(Font.font("Lucida Bright", 12));
    	TextFlow tfRDJ = new TextFlow(t,rdj);
    	tfRDJ.setTextAlignment(TextAlignment.JUSTIFY);
    	tfRDJ.setPadding(new Insets(100,20,20,20));
    	return tfRDJ;    	
    }
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Santiago");
		try { 
		//titre
			Text titre = new Text("Bienvenu sur Santiago !");
			titre.setStyle("-fx-padding:50px; -fx-color:#1e1e1e;");
	        titre.setFill(Color.BLACK);
	        titre.setFont(Font.font("Lucida Bright", 40));
	        TextFlow tf = new TextFlow(titre);
	        tf.setTextAlignment(TextAlignment.CENTER);
	        
	     //insertion du plateau et de la fiche joueur et partie commune au stage !
			AnchorPane tableau = plateau();
			AnchorPane ficheJ = ficheJr();
			VBox partieCommune = tuilesEtJr();
			AnchorPane commun = new AnchorPane();
			VBox vbCommun = new VBox();
			vbCommun.setPadding(new Insets(5,5,5,5)); // ARRËTE LA !!!!!!
			vbCommun.getChildren().addAll(partieCommune,ficheJ);
			ficheJ.setStyle("-fx-padding:15px;");
			commun.getChildren().add(vbCommun);
			rootPrincipal.topProperty().set(tf);
	        rootPrincipal.setCenter(tableau);
	        rootPrincipal.setRight(commun);
	        TextFlow rdj = left();
	        rootPrincipal.setLeft(rdj);
	        Text bottom = new Text("Ce jeu vous a été proposé par Aminetou, Clément, Fikoul, Julien et Sarah. Miage représente ! ");
	        rootPrincipal.setBottom(bottom);
	        
	        
	        Scene scene = new Scene(anchorPrincipal, 1400, 700, Color.WHITE);
	        //scene.getStylesheets().add(getClass().getResource("StyleOver.css").toExternalForm());
	        rootPrincipal.setStyle("-fx-background-color:#FFFFFF;");
	        anchorPrincipal.getChildren().add(rootPrincipal);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e) {
		e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
