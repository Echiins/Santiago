package Santiago.Tests.Classes;


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
	//_____________________GETTER/SETTER_________________________
}
