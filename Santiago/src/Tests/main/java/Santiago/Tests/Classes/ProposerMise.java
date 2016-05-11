package Classes;

import java.io.Serializable;
import java.util.*;

public class ProposerMise implements Comparable<ProposerMise>, Serializable {
	
	private int idMise;
	private Joueur joueur;
	private int montant;
	
	public ProposerMise(int idMise,Joueur joueur, int montant){
		this.joueur=joueur;
		this.idMise=idMise;
		this.montant=montant;
	}
	
	public ProposerMise(int idMise,Joueur joueur){
		this.joueur=joueur;
		this.idMise=idMise;
		this.montant=0;
	}
	
	public ProposerMise(int idMise, int montant,int id_joueur, ArrayList<Joueur> liste_joueurs){
		this.idMise=idMise;
		this.montant=montant;
		for(Joueur j:liste_joueurs){
			if(j.getId_joueur()==id_joueur){
				this.joueur=j;
			}
		}
	}

	public int getIdMise() {
		return idMise;
	}

	public void setIdMise(int idMise) {
		this.idMise = idMise;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	/**********************COMPARATOR************************************/

	 public static class Comparators {

		 public static final Comparator<ProposerMise> MONTANT = (ProposerMise o1, ProposerMise o2) -> Integer.compare(o1.getMontant(), o2.getMontant());
	        
	    }

	@Override
	public int compareTo(ProposerMise o) {
		return Comparators.MONTANT.compare(this, o);
	}

}
