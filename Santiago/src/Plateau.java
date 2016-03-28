import java.util.ArrayList;
import java.util.Random;


public class Plateau {

	private int idPlateau;
	private int sourceX;
	private int sourceY;
	private ArrayList<Parcelle> parcelles;
	private ArrayList<Fosse> fosses;
	//private Partie partie; ----> C'est la partie qui a un plateau et non l'inverse
	
	public Plateau(int idPlateau,int sourceX,int sourceY){
		this.idPlateau=idPlateau;
		this.sourceX=sourceX;
		this.sourceY=sourceY;
	}
	
	public Plateau(){
		this.parcelles=new ArrayList<Parcelle>();
		this.fosses=new ArrayList<Fosse>();
		//ID ?
		//Source -> on la place au hasard dans une intersection à l'interieur du plateau
		//Interieur du tableau = X entre 1 et 7 et Y entre 1 et 5 (inclus)
		Random randX = new Random();
		this.sourceX= randX.nextInt(7 - 1 + 1) + 1;
		Random randY = new Random();
		this.sourceY= randY.nextInt(5 - 1 + 1) + 1;
		
	}
	
}
