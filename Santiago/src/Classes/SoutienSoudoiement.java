package Classes;

import java.util.ArrayList;

public class SoutienSoudoiement {

	int idSS;
	int montant;
	Fosse f; // fossé sujet du soudoiement.
 	Joueur supporter; //nom du joueur qui supporte le soudoiement
	
	public SoutienSoudoiement(int idSS,Joueur j,int m, Fosse f){
		this.idSS=idSS;
		this.supporter=j;
		this.montant=m;
		this.f=f;
	}

	public int getIdSS() {
		return idSS;
	}

	public void setIdSS(int idSS) {
		this.idSS = idSS;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Joueur getSupporter() {
		return supporter;
	}

	public void setSupporter(Joueur sp) {
		this.supporter = sp;
	}

	public Fosse getF() {
		return f;
	}

	public void setF(Fosse f) {
		this.f = f;
	}
}
