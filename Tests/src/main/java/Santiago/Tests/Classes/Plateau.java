package Santiago.Tests.Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class Plateau {

	private int idPlateau;
	private int sourceX;
	private int sourceY;
	private List<Parcelle> parcelles;
	private List<Fosse> fosses;
	
	public Plateau(int idPlateau,int sourceX,int sourceY){
		this.idPlateau=idPlateau;
		this.sourceX=sourceX;
		this.sourceY=sourceY;
		this.parcelles=new ArrayList<Parcelle>();
		this.fosses=new ArrayList<Fosse>();
	}

	public void initparcelles(){
		int id=0;
		for(int i=0;i<6;i++){
			for(int j=0;j<8;j++){
			this.parcelles.add(new Parcelle(id++,j,i,false,0,0));
			}
		}
	}
	
	//Permet davoir la liste des foss�s pouvant etre irrigu� sur le tours
	
	/*public ArrayList<Fosse>getFosseIrrigable(){
		
	}*/
	
	//Permet d'avoir la liste des champs adjacents a un canal
	/*
	public ArrayList<Parcelle>getParcellesIrrigesAdjacents(int idcanal){
		String sens=this.fosses.get(idcanal).getSens();
		List<Parcelle> parcellesAdjacentes= new ArrayList<Parcelle>();
		
		if((idcanal==1 && sens.equals("H")) || (idcanal==2 && sens.equals("H")) || (idcanal==3 && sens.equals("H"))|| (idcanal==4 && sens.equals("H"))){
			parcellesAdjacentes.add(this.parcelles.get((idcanal*2)-1));
			parcellesAdjacentes.add(this.parcelles.get(idcanal*2));
			return (ArrayList<Parcelle>) parcellesAdjacentes;
		}
		else if((idcanal==13 && sens.equals("H")) || (idcanal==14 && sens.equals("H"))){
			parcellesAdjacentes.add(this.parcelles.get((idcanal*3)+2));
			parcellesAdjacentes.add(this.parcelles.get((idcanal*3)+3));
			return (ArrayList<Parcelle>) parcellesAdjacentes;
		}
		else if((idcanal==15 && sens.equals("H"))|| (idcanal==16 && sens.equals("H"))){
			parcellesAdjacentes.add(this.parcelles.get(idcanal*3));
			parcellesAdjacentes.add(this.parcelles.get((idcanal*3)+1));
			return (ArrayList<Parcelle>) parcellesAdjacentes;
		}
		else if(sens.equals("H")){
		//diff cas	
		}
		
		else if((idcanal==17 && sens.equals("V")) || (idcanal==22 && sens.equals("V")) || (idcanal==27 && sens.equals("V"))){
			parcellesAdjacentes.add(this.parcelles.get(idcanal%8));
		}
		
		else if((idcanal==21 && sens.equals("V")) || (idcanal==26 && sens.equals("V")) || (idcanal==31 && sens.equals("V"))){
			
		}
	}
	*/
	
	/*
	// permet davoir la liste des foss�s adjacents a une parcelle;
	public ArrayList<Parcelle> getfossesadjacents(int idparcelle){
	}*/
	public void initfosses()
	{
		int id=0;
		//16 foss�s horizontaux
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
			this.fosses.add(new Fosse(id++,j,i,"H",false));
			}
		}
		//15 foss�s verticaux
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
			this.fosses.add(new Fosse(id++,j,i,"V",false));
			}
		}
		
		
	}
}
