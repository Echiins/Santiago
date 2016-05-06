package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class PropositionSoudoiement implements Serializable {

	int idPS;
	int montant;
 	Joueur premier; //nom du premier qui propose ce soudoiement
	Fosse f; // fossé sujet du soudoiement.
	ArrayList<SoutienSoudoiement> supporters; //comprend les joueurs qui soutienenent ce soudoiement. est vide à la base
	boolean etat; //true: en cours : proposition faite à ce tour , fa : passée : proposition archive : un autre joueur ne peut la soutenir.
	this.accepte=false;
}




public boolean getAccepte() {
	return accepte;
}




public void setAccepte(boolean accepte) {
	this.accepte = accepte;
}
	
	
	
	
	public PropositionSoudoiement(int idPS, int montant, Joueur premier, Fosse f,
			ArrayList<SoutienSoudoiement> supporters, boolean etat) {
		this.idPS = idPS;
		this.montant = montant;
		this.premier = premier;
		this.f = f;
		this.supporters = supporters;
		this.etat = etat;
	}
	public PropositionSoudoiement(int idPS, int montant, int premier, ArrayList<Joueur> liste_joueurs, Fosse f,
			ArrayList<SoutienSoudoiement> supporters, boolean etat) {
		this.idPS = idPS;
		this.montant = montant;
		for(Joueur j:liste_joueurs){
			if (j.getId_joueur()==premier){
				this.premier=j;
			}
		}
		this.f = f;
		this.supporters = supporters;
		this.etat = etat;
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
