package Classes;


public class BilletBanque {
	//IDEE DE CAGNOTE
	
	private Banque banque;
	private int idBilletBanque;
	private String couleur;
	private int montant;
	private int nbbillets;
	
	public BilletBanque(Banque banque,int idBilletBanque,String couleur,int montant, int nbbillets){
		this.banque=banque;
		this.idBilletBanque=idBilletBanque;
		this.couleur=couleur;
		this.montant=montant;
		this.nbbillets=nbbillets;
	}
	public BilletBanque(String couleur, int montant, int nbbillets) {
		this.couleur = couleur;
		this.montant = montant;
		this.nbbillets = nbbillets;
	}
	//_____________________GETTER/SETTER_________________________

	public Banque getBanque() {
		return banque;
	}

	public int getIdBilletBanque() {
		return idBilletBanque;
	}

	public String getCouleur() {
		return couleur;
	}

	public int getMontant() {
		return montant;
	}

	public int getNbbillets() {
		return nbbillets;
	}



	
	
}
