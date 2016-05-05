package Classes;

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




	public void setIdPS(int idPS) {
		this.idPS = idPS;
	}
