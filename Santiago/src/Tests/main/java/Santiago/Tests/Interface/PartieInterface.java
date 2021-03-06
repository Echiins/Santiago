package Santiago.Tests.Interface;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Santiago.Tests.Classes.Joueur;


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
	public void jouerPhase() throws RemoteException;
	public void phase0() throws RemoteException;
	public void phase1() throws RemoteException;
	public void phase2() throws RemoteException;
	public void phase3() throws RemoteException;
	public void phase4() throws RemoteException;
	public void phase5() throws RemoteException;
	public void phase6() throws RemoteException;
	public void phase7() throws RemoteException;
	public void phase8() throws RemoteException;
}
