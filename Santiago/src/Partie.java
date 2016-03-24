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
	
	Partie() throws RemoteException, UnknownHostException {
		this.liste_joueurs=new ArrayList<Joueur>();
		this.liste_interface=new ArrayList<PartieInterface>();
	}

	@Override
	public void setClient(PartieInterface client)  throws RemoteException {
		//this.client=client;
		if(this.liste_interface.size()<5){
			this.liste_interface.add(client);
			Joueur joueur = new Joueur();
			this.liste_joueurs.add(joueur);
			System.out.println("Nouveau joueur. "+this.liste_interface.size()+" joueur(s) au total");
		}
		else{
			System.out.println("Déjà 5 joueurs connectés");
		}
	}
	
	@Override
    public ArrayList<PartieInterface> getClient() throws RemoteException {
        return this.liste_interface;
    }


}
