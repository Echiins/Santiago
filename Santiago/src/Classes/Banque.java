package Classes;

import java.awt.List;
import java.util.ArrayList;

import Interface.Partie;

public class Banque {

	private int idBanque;
	private Partie partie;
	private ArrayList<BilletBanque> billetsbanques;
	
	public Banque(int idBanque, Partie partie){
		this.idBanque=idBanque;
		this.partie=partie;
	}
	public Banque(int idBanque,ArrayList<BilletBanque> billetsbanques) {
		this.idBanque=idBanque;
		this.billetsbanques = billetsbanques;
	}

	
	//_____________________GETTER/SETTER_________________________

	public int getIdBanque() {
		return idBanque;
	}

	public void setIdBanque(int idBanque) {
		this.idBanque = idBanque;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public ArrayList<BilletBanque> getBilletsbanques() {
		return billetsbanques;
	}

	public void setBilletsbanques(ArrayList<BilletBanque> billetsbanques) {
		this.billetsbanques = billetsbanques;
	}
	
	
}
