import java.util.List;





public class Plateau {

	private int idPlateau;
	private int sourceX;
	private int sourceY;
	private List<Parcelle> parcelles;
	private List<Fosse> fossés;
	
	public Plateau(int idPlateau,int sourceX,int sourceY){
		this.idPlateau=idPlateau;
		this.sourceX=sourceX;
		this.sourceY=sourceY;	
	}
}
