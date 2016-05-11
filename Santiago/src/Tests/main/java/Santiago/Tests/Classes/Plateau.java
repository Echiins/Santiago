package Classes;

import java.io.IOException;
import java.io.Serializable;
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

public class Plateau implements Serializable{

	private int idPlateau;
	private int sourceX;
	private int sourceY;
	private List<Parcelle> liste_parcelles;
	private List<Fosse> liste_fosses;
	
	/***************************************************************************
	 * *******************************CONSTRUCTOR*******************************
	 ***************************************************************************/
	
	public Plateau(int idPlateau,int sourceX,int sourceY){
		this.idPlateau=idPlateau;
		this.sourceX=sourceX;
		this.sourceY=sourceY;
		this.liste_parcelles=new ArrayList<Parcelle>();
		this.liste_fosses=new ArrayList<Fosse>();
	}
	

	public Plateau(int idPlateau, int sourceX, int sourceY, List<Parcelle> liste_parcelles, List<Fosse> liste_fosses) {
		super();
		this.idPlateau = idPlateau;
		this.sourceX = sourceX;
		this.sourceY = sourceY;
		this.liste_parcelles = liste_parcelles;
		this.liste_fosses = liste_fosses;
	}


	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/
	//************************************INIT************************************
	/**
	 * Permet d'initialiser le tableau et d'instensier toutes les liste_parcelles présentes dessus.
	 * un tableau 1D de 6X8 liste_parcelles
	 */
	public void initliste_parcelles(){
		int id=1;
		for(int i=0;i<6;i++){
			for(int j=0;j<8;j++){
			this.liste_parcelles.add(new Parcelle(id++,i,j,false));
			}
		}
	}
	
	/**
	 * * Permet d'initialiser le tableau et d'instensier touts les fossés présentes dessus.
	 * un tableau 1D de 16 fossés horizontaux et 15 fossés verticaux.
	 */
	public void initfosses()
	{
		int id=1;
		//16 foss�s horizontaux
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
			this.liste_fosses.add(new Fosse(id,j,i,"H",false));
			System.out.println(id+":"+i+" "+j);id++;
			
			
			}
		}
		//15 foss�s verticau
		id=1;
		for(int k=0;k<3;k++){
			for(int l=0;l<5;l++){
			this.liste_fosses.add(new Fosse(id,k,l,"V",false));
			id++;
			}
		}	
	}
	/**
	 * Permet de trouver une parcelle directement par ses coordonnées
	 * @param x
	 * @param y
	 * @return la parcelle en xy
	 */
	public Parcelle get(int x,int y){
		for(Parcelle p:this.liste_parcelles){
			if(p.getCoorX()==x && p.getCoorY()==y)
				return p;
			
		}
		return null;
	}
	/**
	 * Permet de trouver un fosse directement par ses coordonnées
	 * @param x
	 * @param y
	 * @return
	 */
	public Fosse getFosse(int x,int y,String sens){

		
		switch (sens){
		case "H":
			for(int i=0;i<16;i++){
				
				if((liste_fosses.get(i).getCoorX()==y) && (liste_fosses.get(i).getCoorY()==x)){
					//System.out.println("H :"+x+","+y+" id: "+liste_fosses.get(i).getIdFosse());
					return liste_fosses.get(i);
				}
				
			}
			break;
		case "V":
			for(int j=16;j<liste_fosses.size();j++){
				if((liste_fosses.get(j).getCoorX()==x) && (liste_fosses.get(j).getCoorY()==y))
				{
					//System.out.println("V :"+x+","+y+""+liste_fosses.get(j).getIdFosse());return liste_fosses.get(j);
				}
					
			}
			break;
		}
		return null;
	}

	/**
	 * Permet de connaitre lensemble des parcelles autours d'un fossé
	 * @param fosse
	 * @return
	 */
	public ArrayList<Parcelle> getparcellesAdjacentes(Fosse fosse){
		ArrayList<Parcelle> parcelles=new ArrayList<Parcelle>();
		if(fosse.getSens().equals("V")){
			int coordx;
			int coordy;
			if(fosse.getIdFosse()%4==0){
				coordy=fosse.getCoorY();System.out.println("ici");
				
			}
			else{
				coordy=(fosse.getIdFosse()%4)*fosse.getCoorY();
			}
			
			if(fosse.getIdFosse()%4==3||fosse.getIdFosse()%4==0){
				coordx=(2*fosse.getCoorX());//+1;
			}
			else{
				coordx=fosse.getCoorX();
			}
			
			if(fosse.getCoorY()==0){

				
				parcelles.add(this.get(coordx,0));
				parcelles.add(this.get(coordx+1,0));
			}
			else if(fosse.getCoorY()==4){
				parcelles.add(this.get(coordx,7));
				parcelles.add(this.get(coordx+1,7));
			}
			else{
				parcelles.add(this.get(coordx,coordy));
				parcelles.add(this.get(coordx+1,coordy));
				parcelles.add(this.get(coordx,coordy+1));
				parcelles.add(this.get(coordx+1,coordy+1));
			}
			//autres bords
			return parcelles;
		}
		else if(fosse.getSens().equals("H")){
			int coordx;
			int coordy;
			if(fosse.getIdFosse()%4==0||fosse.getIdFosse()%4==3){
				coordy=(2*fosse.getCoorY());
			}
			else{
				coordy=(fosse.getIdFosse()%4)*fosse.getCoorY();
			}
			
			if(fosse.getIdFosse()%4==3){
				coordx=fosse.getCoorX()+1;
			}
			else{
				coordx=fosse.getCoorX();
			}
			
			if(fosse.getCoorX()==0){
				parcelles.add(this.get(0,coordy));
				parcelles.add(this.get(0,coordy+1));
			}
			//bords bas
			else if(fosse.getCoorX()==3){
				parcelles.add(this.get(5,coordy));
				parcelles.add(this.get(5,coordy+1));
			}
			//les autres
			else{
				parcelles.add(this.get(coordx,coordy));
				parcelles.add(this.get(coordx,coordy+1));
				parcelles.add(this.get(coordx+1,coordy));
				parcelles.add(this.get(coordx+1,coordy+1));
			}
			return parcelles;
		}
		else
			return null;
	}
	
	/**
	 * Permet de connaitre les fosses autours d'une parcelles
	 * @param parcelle
	 * @return
	 */
	public ArrayList<Fosse> getFossesAdjacents( Parcelle parcelle){
		ArrayList<Fosse> fosses= new ArrayList<Fosse>();
		int x;
		int y;
		
		if((parcelle.getCoorX()%2==1) ){
			x=parcelle.getCoorX()/2+1;
		}
		else
			x=parcelle.getCoorX()/2;
		
		fosses.add(this.getFosse(x,parcelle.getCoorY()/2,"H"));
		
		if((parcelle.getCoorY()%2==1) ){
			y=parcelle.getCoorY()/2+1;
		}
		else
			y=parcelle.getCoorY();
		
		fosses.add(this.getFosse(parcelle.getCoorX()/2,y,"V"));
		return fosses;
	}
	public boolean getFossesIrrigueAdjacents(Fosse fosse){
		boolean irrigue = false;
		int x=fosse.getCoorY();
		int y = fosse.getCoorX();
		String sens = fosse.getSens();
			if((x==0)&&(y==0)){
				if(sens=="V"){
					if(this.getFosse(x+1, y, "V").getIrrigue()){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==1)&&(y==0)){
				if(sens=="V"){
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==2)&&(y==0)){
				if(sens=="V"){
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==3)&&(y==0)){
				if(sens=="V"){
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==4)&&(y==0)){
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			
			if((x==0)&&(y==1)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==1)&&(y==1)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==2)&&(y==1)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==3)&&(y==1)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==4)&&(y==1)){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
			}
			if((x==0)&&(y==2)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==1)&&(y==2)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==2)&&(y==2)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==3)&&(y==2)){
				if(sens=="V"){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
					
				}else{
					if(this.getFosse(x,y,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
				}
			}
			if((x==4)&&(y==2)){
				if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x-1,y+1,"V").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
			}
			if((x==0)&&(y==3)){
					if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
					if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
			}
			if((x==1)&&(y==3)){
				if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
			}
			if((x==2)&&(y==3)){
				if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x+1,y,"V").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x-1,y,"V").getIrrigue()==true){irrigue=true;}
			}
			if((x==3)&&(y==3)){
				if(this.getFosse(x,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x+1,y-1,"H").getIrrigue()==true){irrigue=true;}
				if(this.getFosse(x-1,y,"H").getIrrigue()==true){irrigue=true;}
			}
			return irrigue;
		}
	
	//************************************GETTER************************************
	//************************************SETTER************************************
	
	
	public int getIdPlateau() {
		return idPlateau;
	}

	public void setIdPlateau(int idPlateau) {
		this.idPlateau = idPlateau;
	}

	public int getSourceX() {
		return sourceX;
	}

	public void setSourceX(int sourceX) {
		this.sourceX = sourceX;
	}

	public int getSourceY() {
		return sourceY;
	}

	public void setSourceY(int sourceY) {
		this.sourceY = sourceY;
	}

	public List<Parcelle> getListe_parcelles() {
		return liste_parcelles;
	}

	public void setListe_parcelles(List<Parcelle> liste_parcelles) {
		this.liste_parcelles = liste_parcelles;
	}

	public List<Fosse> getListe_fosses() {
		return liste_fosses;
	}

	public void setListe_fosses(List<Fosse> liste_fosses) {
		this.liste_fosses = liste_fosses;
	}
}