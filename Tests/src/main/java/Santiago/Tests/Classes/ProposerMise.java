package Santiago.Tests.Classes;

public class ProposerMise {
	
	private int idMise;
	private Joueur joueur;
	private TuilePlantation tuile;
	private int montant;
	
	public ProposerMise(int idMise,Joueur joueur, TuilePlantation tuile, int montant){
		this.joueur=joueur;
		this.idMise=idMise;
		this.tuile=tuile;
		this.montant=montant;
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

	public TuilePlantation getTuile() {
		return tuile;
	}

	public void setTuile(TuilePlantation tuile) {
		this.tuile = tuile;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	

}
