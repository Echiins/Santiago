import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Partie extends UnicastRemoteObject implements PartieInterface{

	private int idPartie;
	private int tour;
	private int phase;
	private Plateau plateau;
	private Banque banque;
	private ArrayList<PartieInterface> liste_interface=null;
	private ArrayList<Joueur> liste_joueurs=null;
	private ArrayList<String> couleurs = new ArrayList<String>();
	private boolean start;
	private int maxTour;
	
	Partie() throws RemoteException, UnknownHostException {
		this.liste_joueurs=new ArrayList<Joueur>();
		this.liste_interface=new ArrayList<PartieInterface>();
		this.couleurs.add("Noir");
		this.couleurs.add("Gris");
		this.couleurs.add("Violet");
		this.couleurs.add("Beige");
		this.couleurs.add("Rouge");
		this.start=false;
		this.tour=1;
		this.phase=0;
	}

	@Override
	public void setClient(PartieInterface client)  throws RemoteException {
		//this.client=client;
		if(this.liste_interface.size()<5){
			this.liste_interface.add(client);
			//Joueur joueur = new Joueur();
			//this.liste_joueurs.add(joueur);
			System.out.println("Nouveau joueur. "+this.liste_interface.size()+" joueur(s) au total");
			if(this.liste_interface.size()==5){
				System.out.println("add bon");
				this.start=true;
				System.out.println(start);
			}
		}
		else{
			System.out.println("Déjà  5 joueurs connectés dsl");
		}
	}
	
	@Override
    public ArrayList<PartieInterface> getClient() throws RemoteException {
        return this.liste_interface;
    }
	
	@Override
	public ArrayList<Joueur> getJoueurs() throws RemoteException{
		return this.liste_joueurs;
	}
	

	@Override
	public String getCouleur(int i) throws RemoteException {
		return this.couleurs.get(i);
	}
	
	@Override
	public void addJoueur(Joueur j) throws RemoteException{
		this.liste_joueurs.add(j);
	}
	
	@Override
	public boolean getStart() throws RemoteException{
		return this.start;
	}
	@Override
	public void lancerLaPartie() throws RemoteException{
		if(this.liste_joueurs.size()>=3){
			this.start=true;
		}
	}
	@Override
	public void quitterPartie() throws RemoteException{
		this.start=false;
		//ici sauvegarde XML ou JSON
	}

	@Override
	public int getTour() throws RemoteException{
		return this.tour;
	}
	
	@Override
	public void tourSuivant() throws RemoteException{
		this.tour++;
		this.phase=1;
	}
	
	@Override
	public int getPhase() throws RemoteException{
		return this.phase;
	}
	@Override
	public void phaseSuivante() throws RemoteException{
		this.phase++;
	}
	
	@Override
	public int getMaxTour() throws RemoteException{
		return this.maxTour;
	}
	@Override
	public void setMaxTour(int maxTour) throws RemoteException{
		this.maxTour=maxTour;
	}
	
	@Override
	public void joueurPhase() throws RemoteException{
		switch (this.phase){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 0:
			break;
		default:
			System.out.println("Erreur .....");
		}
	}
	
	
}
