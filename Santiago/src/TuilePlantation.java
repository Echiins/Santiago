
public class TuilePlantation {

	private int idPlantation;
	private int SourceX;
	private int SourceY;
	//AJOUTER DANS UMML
	private boolean dessert;
	
	private PileTuile pileTuile;
	
	public TuilePlantation(int idPlantation, int SourceX,int SourceY,PileTuile pileTuile,boolean dessert ){
		this.idPlantation=idPlantation;
		this.SourceX=SourceX;
		this.SourceY=SourceY;
		this.PileTuile=pileTuile;
		this.dessert=dessert;
	}
	
}
