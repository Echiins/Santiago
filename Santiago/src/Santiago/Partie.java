package Santiago;


import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Partie extends UnicastRemoteObject implements PartieInterface{


	private ArrayList<PartieInterface> liste_joueurs=null;
	
	Partie() throws RemoteException, UnknownHostException {
		this.liste_joueurs=new ArrayList<PartieInterface>();
	}

	@Override
	public void setClient(PartieInterface client)  throws RemoteException {
		//this.client=client;
		if(this.liste_joueurs.size()<5){
			this.liste_joueurs.add(client);
			System.out.println("Nouveau joueur. "+this.liste_joueurs.size()+" joueur(s) au total");
		}
		else{
			System.out.println("D�j� 5 joueurs connect�s");
		}
	}
	
	@Override
    public ArrayList<PartieInterface> getClient() throws RemoteException {
        return this.liste_joueurs;
    }


}
