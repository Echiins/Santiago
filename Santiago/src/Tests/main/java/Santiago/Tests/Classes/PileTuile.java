package Classes;
import java.io.Serializable;
import java.util.ArrayList;

public class PileTuile implements Serializable {
	private int idPiletuile;
	private ArrayList<TuilePlantation> tuiles;
	
	
	public PileTuile(int idPiletuile){
		
	 this.idPiletuile=idPiletuile;
	 this.tuiles=new ArrayList<TuilePlantation>();
	 
	}
	public PileTuile(int idPiletuile, ArrayList<TuilePlantation> listetuiles){
		
		 this.idPiletuile=idPiletuile;
		 this.tuiles=listetuiles;
		 
		}
	public int getIdPiletuile() {
		return idPiletuile;
	}

	public void setIdPiletuile(int idPiletuile) {
		this.idPiletuile = idPiletuile;
	}

	public ArrayList<TuilePlantation> getTuiles() {
		return tuiles;
	}

	public void setTuiles(ArrayList<TuilePlantation> tuiles) {
		this.tuiles = tuiles;
	}

	public void add(TuilePlantation t){
		this.tuiles.add(t);
	}
	public void addAll(ArrayList<TuilePlantation> l){
		this.tuiles.addAll(l);
	}

	public TuilePlantation retirerTuile(){
		TuilePlantation t=this.tuiles.get(0);
		this.tuiles.remove(0);
		return t;
	}
}
