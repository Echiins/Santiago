
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface PartieInterface extends java.rmi.Remote{

	public void setClient(PartieInterface client) throws RemoteException;
	public ArrayList<PartieInterface> getClient() throws RemoteException;
	public ArrayList<Joueur> getJoueurs() throws RemoteException;
	public String getCouleur(int i) throws RemoteException;
	public void addJoueur(Joueur j) throws RemoteException;
	public boolean getStart() throws RemoteException;
	public void lancerLaPartie() throws RemoteException;
	public int getTour() throws RemoteException;
	public void tourSuivant() throws RemoteException;
	public int getPhase() throws RemoteException;
	public void phaseSuivante() throws RemoteException;
	public void quitterPartie() throws RemoteException;
	public int getMaxTour() throws RemoteException;
	public void setMaxTour(int maxTour) throws RemoteException;
	void joueurPhase() throws RemoteException;
}
