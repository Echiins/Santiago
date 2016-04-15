package Classes;


public class Parcelle {
	private int idParcelle;
	private int coorX;
	private int coorY;
	private boolean palmier;
	private int nbTAGnecessaires;
	//AJOUTER SUR UML
	private int nbTAGpresent;
	boolean contour;
	
	public Parcelle(int idParcelle,int coorX,int coorY,boolean palmier,int nbTAGnecessaires,int nbTAGpresent){
		this.idParcelle=idParcelle;
		this.coorX=coorX;
		this.palmier=palmier;
		this.nbTAGnecessaires=nbTAGnecessaires;
		this.nbTAGpresent=nbTAGpresent;
		this.contour=false;
	}

	public int getIdParcelle() {
		return idParcelle;
	}

	public void setIdParcelle(int idParcelle) {
		this.idParcelle = idParcelle;
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

	public boolean isPalmier() {
		return palmier;
	}

	public void setPalmier(boolean palmier) {
		this.palmier = palmier;
	}

	public int getNbTAGnecessaires() {
		return nbTAGnecessaires;
	}

	public void setNbTAGnecessaires(int nbTAGnecessaires) {
		this.nbTAGnecessaires = nbTAGnecessaires;
	}

	public int getNbTAGpresent() {
		return nbTAGpresent;
	}

	public void setNbTAGpresent(int nbTAGpresent) {
		this.nbTAGpresent = nbTAGpresent;
	}
	
	
}
