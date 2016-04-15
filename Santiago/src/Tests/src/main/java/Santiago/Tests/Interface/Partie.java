package Santiago.Tests.Interface;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import Santiago.Tests.Classes.*;

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
	private PileTuile pile1=null;
	private PileTuile pile2=null;
	private PileTuile pile3=null;
	private PileTuile pile4=null;
	private PileTuile pile5=null;
	
	
	public Partie() throws RemoteException, UnknownHostException {
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
			System.out.println("D�j� 5 joueurs connect�s dsl");
		}
	}
	
	public ArrayList<PartieInterface> getClient() throws RemoteException {
        return this.liste_interface;
    }
	
	public ArrayList<Joueur> getJoueurs() throws RemoteException{
		return this.liste_joueurs;
	}
	

	public String getCouleur(int i) throws RemoteException {
		return this.couleurs.get(i);
	}
	
	public void addJoueur(Joueur j) throws RemoteException{
		this.liste_joueurs.add(j);
	}
	
	public boolean getStart() throws RemoteException{
		return this.start;
	}
	public void lancerLaPartie() throws RemoteException{
		if(this.liste_joueurs.size()>=3){
			this.start=true;
		}
	}
	public void quitterPartie() throws RemoteException{
		this.start=false;
		//ici sauvegarde XML ou JSON
	}

	 
	public int getTour() throws RemoteException{
		return this.tour;
	}
	
	 
	public void tourSuivant() throws RemoteException{
		this.tour++;
		this.phase=1;
	}
	
	 
	public int getPhase() throws RemoteException{
		return this.phase;
	}
	 
	public void phaseSuivante() throws RemoteException{
		this.phase++;
	}
	
	 
	public int getMaxTour() throws RemoteException{
		return this.maxTour;
	}
	 
	public void setMaxTour(int maxTour) throws RemoteException{
		this.maxTour=maxTour;
	}
	
	 
	public void jouerPhase() throws RemoteException{ //FONCTION AVEC TOUTES LES PHASES DU JEU
		switch (this.phase){
		case 1:
			this.phase1();
			break;
		case 2:
			this.phase2();
			break;
		case 3:
			this.phase3();
			break;
		case 4:
			this.phase4();
			break;
		case 5:
			this.phase5();
			break;
		case 6:
			this.phase6();
			break;
		case 7:
			this.phase7();
			break;
		case 0: //INITIALISATION, une seule fois
			this.phase0();
			break;
		default:
			System.out.println("Erreur .....");
		}
	}
	

	public void phase0() throws RemoteException{ 
		//On cr�e le tableau de la partie
		this.plateau=new Plateau(1,5,6);
		this.plateau.initparcelles();// tableau de parcelle 8X6
		this.plateau.initfosses();//tableau de fosses 16 premier verticaux, 16 d'apres horizontaux
		//On choisit al�atoirement le constructeur de canal
		Random r = new Random();
		int indiceJoueur= r.nextInt(this.liste_joueurs.size());
		this.liste_joueurs.get(indiceJoueur).devenirConstructeur();
		//initialisation de la Banque
		//this.banque=new Banque();
		//On g�re les tuiles de plantations
		
			//CREATION DES TUILES
		ArrayList<TuilePlantation> toutesLesTuiles=new ArrayList<TuilePlantation>();
		for(int id1=1 ;id1<=9; id1++){
			toutesLesTuiles.add(new TuilePlantation(id1,"rouge","piment"));
		}
		for(int id2=10 ;id2<=18; id2++){
			toutesLesTuiles.add(new TuilePlantation(id2,"jaune","banane"));
		}
		for(int id3=19 ;id3<=27; id3++){
			toutesLesTuiles.add(new TuilePlantation(id3,"rouge","piment"));
		}
		for(int id4=28 ;id4<=36; id4++){
			toutesLesTuiles.add(new TuilePlantation(id4,"rouge","piment"));
		}
		for(int id5=37 ;id5<=45; id5++){
			toutesLesTuiles.add(new TuilePlantation(id5,"rouge","piment"));
		}
			//Creation des piles
		if(this.liste_joueurs.size()==5){
			while(! toutesLesTuiles.isEmpty()){
				this.pile1=new PileTuile();
				for(int indicePile1=1;indicePile1<=9;indicePile1++){
					Random rPile1=new Random();
					int indice1=rPile1.nextInt(toutesLesTuiles.size());
					this.pile1.add(toutesLesTuiles.get(indice1));
					toutesLesTuiles.remove(indice1);
					
				}
				this.pile2=new PileTuile();
				for(int indicePile2=1;indicePile2<=9;indicePile2++){
					Random rPile2=new Random();
					int indice2=rPile2.nextInt(toutesLesTuiles.size());
					this.pile2.add(toutesLesTuiles.get(indice2));
					toutesLesTuiles.remove(indice2);
				}
				this.pile3=new PileTuile();
				for(int indicePile3=1;indicePile3<=9;indicePile3++){
					Random rPile3=new Random();
					int indice3=rPile3.nextInt(toutesLesTuiles.size());
					this.pile3.add(toutesLesTuiles.get(indice3));
					toutesLesTuiles.remove(indice3);
					
				}
				this.pile4=new PileTuile();
				for(int indicePile4=1;indicePile4<=9;indicePile4++){
					Random rPile4=new Random();
					int indice4=rPile4.nextInt(toutesLesTuiles.size());
					this.pile4.add(toutesLesTuiles.get(indice4));
					toutesLesTuiles.remove(indice4);
					
				}
				this.pile5=new PileTuile();
				this.pile5.addAll(toutesLesTuiles);
			}
		}
		else{
			Random r2 = new Random();
			int tuileAsupprimer= r2.nextInt(45);
			toutesLesTuiles.remove(tuileAsupprimer);
			while(! toutesLesTuiles.isEmpty()){
				this.pile1=new PileTuile();
				for(int indicePile1=1;indicePile1<=11;indicePile1++){
					Random rPile1=new Random();
					int indice1=rPile1.nextInt(toutesLesTuiles.size());
					this.pile1.add(toutesLesTuiles.get(indice1));
					toutesLesTuiles.remove(indice1);
					
				}
				this.pile2=new PileTuile();
				for(int indicePile2=1;indicePile2<=11;indicePile2++){
					Random rPile2=new Random();
					int indice2=rPile2.nextInt(toutesLesTuiles.size());
					this.pile2.add(toutesLesTuiles.get(indice2));
					toutesLesTuiles.remove(indice2);
				}
				this.pile3=new PileTuile();
				for(int indicePile3=1;indicePile3<=11;indicePile3++){
					Random rPile3=new Random();
					int indice3=rPile3.nextInt(toutesLesTuiles.size());
					this.pile3.add(toutesLesTuiles.get(indice3));
					toutesLesTuiles.remove(indice3);
					
				}
				this.pile4=new PileTuile();
				this.pile4.addAll(toutesLesTuiles);
			}
		}
	
	}


	public void phase1() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	 
	public void phase2() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	 
	public void phase3() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	 
	public void phase4() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	 
	public void phase5() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	 
	public void phase6() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	 
	public void phase7() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
}
