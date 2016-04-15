package Santiago.Tests.Classes;
import java.util.ArrayList;

public class PileTuile {
	private int idPiletuile;
	private ArrayList<TuilePlantation> tuiles;
	
	
	public PileTuile(int idPiletuile){
		
	 this.idPiletuile=idPiletuile;
	 
	}


	public PileTuile() {
		// TODO Auto-generated constructor stub
	}

	public void add(TuilePlantation t){
		this.tuiles.add(t);
	}
	public void addAll(ArrayList<TuilePlantation> l){
		this.tuiles.addAll(l);
	}
	
}
