package Classes;

import java.util.ArrayList;

public class PropositionSoudoiement {

	int idPS;
	int montant;
 	Joueur premier; //nom du premier qui propose ce soudoiement
	Fosse f; // fossé sujet du soudoiement.
	ArrayList<SoutienSoudoiement> supporters; //comprend les joueurs qui soutienenent ce soudoiement. est vide à la base
	boolean etat; //true: en cours : proposition faite à ce tour , fa : passée : proposition archive : un autre joueur ne peut la soutenir.
	
	public PropositionSoudoiement(int idPS,Joueur j,int m, Fosse f){
		this.idPS=idPS;
		this.premier=j;
		this.montant=m;
		this.f=f;
		this.supporters=new ArrayList<SoutienSoudoiement>();
		this.etat=true;
	}
	
	
	
	
	public int getIdPS() {
		return idPS;
	}




	public int getMontant() {
		return montant;
	}




	public void setMontant(int montant) {
		this.montant = montant;
	}




	public Joueur getPremier() {
		return premier;
	}




	public void setPremier(Joueur premier) {
		this.premier = premier;
	}




	public Fosse getF() {
		return f;
	}




	public void setF(Fosse f) {
		this.f = f;
	}




	public ArrayList<SoutienSoudoiement> getSupporters() {
		return supporters;
	}




	public void setSupporters(ArrayList<SoutienSoudoiement> supporters) {
		this.supporters = supporters;
	}




	public boolean isEtat() {
		return etat;
	}




	public void setEtat(boolean etat) {
		this.etat = etat;
	}




	public void setIdPS(int idPS) {
		this.idPS = idPS;
	}
}
