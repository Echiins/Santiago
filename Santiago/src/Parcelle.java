
public class Parcelle {
	private int idParcelle;
	private int coorX;
	private int coorY;
	private boolean palmier;
	private int nbTAGnecessaires;
	//AJOUTER SUR UML
	private int nbTAGpresent;
	private Plateau plateau;
	
	public Parcelle(int idParcelle,int coorX,int coorY,boolean palmier,int nbTAGnecessaires,int nbTAGpresent,Plateau plateau){
		this.idParcelle=idParcelle;
		this.coorX=coorX;
		this.palmier=palmier;
		this.nbTAGnecessaires=nbTAGnecessaires;
		this.nbTAGpresent=nbTAGpresent;
		this.plateau=plateau;
	}
}
