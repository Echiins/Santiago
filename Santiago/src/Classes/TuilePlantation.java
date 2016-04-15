package Classes;


public class TuilePlantation {

	private int idPlantation;
	private int SourceX;
	private int SourceY;
	//AJOUTER DANS UMML
	private boolean desert;
	private String couleur;
	private String plante;
	
	private PileTuile pileTuile;
	
	public TuilePlantation(int idPlantation, int SourceX,int SourceY,PileTuile pileTuile,boolean desert ){
		this.idPlantation=idPlantation;
		this.SourceX=SourceX;
		this.SourceY=SourceY;
		this.pileTuile=pileTuile;
		this.desert=desert;
	}
	
	public TuilePlantation(int id,String couleur, String plante){
		this.SourceX=-1;
		this.SourceY=-1;
		this.couleur=couleur;
		this.plante=plante;
	}
	
	
}
