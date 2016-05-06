package Classes;

import java.io.Serializable;


public class Parcelle implements Serializable {
	private int idParcelle;
	private int coorX;
	private int coorY;
	private boolean palmier;
	private boolean occupee;
	
	/***************************************************************************
	 * *******************************CONSTRUCTOR*******************************
	 ***************************************************************************/
	public Parcelle(int idParcelle,int coorX,int coorY,boolean palmier){
		this.idParcelle=idParcelle;
		this.coorX=coorX;
		this.coorY=coorY;
		this.palmier=palmier;
		this.occupee=false;
	}

	public Parcelle(int idParcelle,int coorX,int coorY,boolean palmier,boolean occupee){
		this.idParcelle=idParcelle;
		this.coorX=coorX;
		this.coorY=coorY;
		this.palmier=palmier;
		this.occupee=occupee;
	}
	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/
	
	//************************************GETTER************************************
	public int getIdParcelle() {
		return idParcelle;
	}
	public int getCoorX() {
		return coorX;
	}
	public int getCoorY() {
		return coorY;
	}
	public boolean getPalmier() {
		return palmier;
	}


	public boolean getOccupee() {
		return occupee;
	}

	//************************************SETTER************************************
	public void setIdParcelle(int idParcelle) {
		this.idParcelle = idParcelle;
	}
	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}
	public void setPalmier(boolean palmier) {
		this.palmier = palmier;
	}
	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}
	public void setOccupee(boolean occupee) {
		this.occupee = occupee;
	}
}
