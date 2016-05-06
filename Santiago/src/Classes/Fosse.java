package Classes;

import java.io.Serializable;


public class Fosse implements Serializable{
	private int idFosse;
	private int coorX;
	private int coorY;
	private boolean irrigue;
	private String sens;
	/***************************************************************************
	 * *******************************CONSTRUCTOR*******************************
	 ***************************************************************************/
	public Fosse(int idFosse, int coorX, int coorY,String sens, boolean irrigue){
		this.idFosse=idFosse;
		this.coorX=coorX;
		this.coorY=coorY;
		this.sens=sens;
		this.irrigue=irrigue;
		
	}
	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/
	//************************************GETTER************************************
	public int getIdFosse() {
		return idFosse;
	}
	public int getCoorY() {
		return coorY;
	}
	public int getCoorX() {
		return coorX;
	}
	public String getSens() {
		return sens;
	}
	public boolean getIrrigue() {
		return irrigue;
	}
	//************************************SETTER************************************
	public void setIdFosse(int idFosse) {
		this.idFosse = idFosse;
	}
	
	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}
	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}
	public void setIrrigue(boolean irrigue) {
		this.irrigue = irrigue;
	}
	public void setSens(String sens) {
		this.sens = sens;
	}

}
