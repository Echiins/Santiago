package Classes;


public class Fosse {
	private int idFosse;
	private int coorX;
	private int coorY;
	private boolean irrigue;
	private String sens;
	public Fosse(int idFosse, int coorX, int coorY,String sens, boolean irrigue){
		this.idFosse=idFosse;
		this.coorX=coorX;
		this.coorY=coorY;
		this.sens=sens;
		this.irrigue=irrigue;
		
	}
	public int getIdFosse() {
		return idFosse;
	}
	public void setIdFosse(int idFosse) {
		this.idFosse = idFosse;
	}
	public int getCoorX() {
		return coorX;
	}
	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}
	public int getCoorY() {
		return coorY;
	}
	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}
	public boolean isIrrigue() {
		return irrigue;
	}
	public void setIrrigue(boolean irrigue) {
		this.irrigue = irrigue;
	}
	public String getSens() {
		return sens;
	}
	public void setSens(String sens) {
		this.sens = sens;
	}

}
