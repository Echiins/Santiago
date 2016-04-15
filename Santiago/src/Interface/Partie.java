package Interface;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import Classes.*;

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
	private ArrayList<PileTuile> liste_piles=null;
	
	
	public ArrayList<PileTuile> getListe_piles() {
		return liste_piles;
	}

	public void setListe_piles(ArrayList<PileTuile> liste_piles) {
		this.liste_piles = liste_piles;
	}

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
		if(this.liste_joueurs.size()>=3|| this.liste_interface.size()>=3){
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
		this.jouerPhase();
	}
	
	 
	public int getPhase() throws RemoteException{
		return this.phase;
	}
	 
	public void phaseSuivante() throws RemoteException{
		this.phase++;
		this.jouerPhase();
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
	
	//Reorganiser lordre de jeu en fonction de id du constructeur
	public void resetOrdre(int id){
		int rang=1;
		this.liste_joueurs.get(id-1).setRang(rang);
		
		while(rang<5){
			if(id==liste_joueurs.size())
			{
				id=1;
				rang++;
				this.liste_joueurs.get(id-1).setRang(rang);
			}
			else{
			id++;
			rang++;
			this.liste_joueurs.get(id-1).setRang(rang);	
			}
		}
	}
	
	public Joueur getConstructeur(){
		for(int i=0;i<this.liste_joueurs.size();i++){
			if(this.liste_joueurs.get(i).getConstructeur())
				return this.liste_joueurs.get(i);
		}return null;
	}
	/**
	 * 
	 */
	public void phase0() throws RemoteException{ 
		//On cr�e le tableau de la partie
		plateau=new Plateau(1,5,6);
		System.out.println("Création Tableau");
		plateau.initparcelles();// tableau de parcelle 8X6
		System.out.println("Initialisation parcelles");
		this.plateau.initfosses();//tableau de fosses 16 premier verticaux, 16 d'apres horizontaux
		System.out.println("Initialisation fossés");
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
			toutesLesTuiles.add(new TuilePlantation(id1,"piment"));
		}
		for(int id2=10 ;id2<=18; id2++){
			toutesLesTuiles.add(new TuilePlantation(id2,"banane"));
		}
		for(int id3=19 ;id3<=27; id3++){
			toutesLesTuiles.add(new TuilePlantation(id3,"piment"));
		}
		for(int id4=28 ;id4<=36; id4++){
			toutesLesTuiles.add(new TuilePlantation(id4,"piment"));
		}
		for(int id5=37 ;id5<=45; id5++){
			toutesLesTuiles.add(new TuilePlantation(id5,"piment"));
		}
		
			//Creation des piles
		if(this.liste_joueurs.size()==5){
			this.liste_piles=new ArrayList<PileTuile>(5);
			PileTuile pile1,pile2,pile3,pile4,pile5;
			
				pile1=new PileTuile(1);
				for(int indicePile1=1;indicePile1<=9;indicePile1++){
					int indice1=(int)Math.random()*(toutesLesTuiles.size()-0);
					pile1.getTuiles().add(toutesLesTuiles.get(indice1));
					toutesLesTuiles.remove(indice1);
					
				} this.liste_piles.add(pile1);
				pile2=new PileTuile(2);
				for(int indicePile2=1;indicePile2<=9;indicePile2++){
					int indice2=(int)(Math.random()*(toutesLesTuiles.size()-0));
					pile2.getTuiles().add(toutesLesTuiles.get(indice2));
					toutesLesTuiles.remove(indice2);
				}this.liste_piles.add(pile2);
				
				pile3=new PileTuile(3);
				for(int indicePile3=1;indicePile3<=9;indicePile3++){
					int indice3=(int)(Math.random()*(toutesLesTuiles.size()-0));
					pile3.getTuiles().add(toutesLesTuiles.get(indice3));
					toutesLesTuiles.remove(indice3);
					
				}this.liste_piles.add(pile3);
				pile4=new PileTuile(4);
				for(int indicePile4=1;indicePile4<=9;indicePile4++){
					int indice4=(int)(Math.random()*(toutesLesTuiles.size()-0));
					pile4.getTuiles().add(toutesLesTuiles.get(indice4));
					toutesLesTuiles.remove(indice4);
					
				}
				this.liste_piles.add(pile4);
				pile5=new PileTuile(5);
				pile5.getTuiles().addAll(toutesLesTuiles);
				this.liste_piles.add(pile5);
				//INIT CONSTRUCTEUR
				int constructeur=(int)(Math.random()*(liste_joueurs.size()-0));
				liste_joueurs.get(constructeur).devenirConstructeur();
				System.out.println("Le joueur "+liste_joueurs.get(constructeur).getNomJoueur()+" est constructeur de canal");
				
				//INIT NB DE TOURS
				this.setMaxTour(9);
				
				//RORGANISER l'ordre de passage
		}
		else{
			this.liste_piles=new ArrayList<PileTuile>(4);
			PileTuile pile1,pile2,pile3,pile4;


			Random r2 = new Random();
			int tuileAsupprimer= r2.nextInt(45);
			toutesLesTuiles.remove(tuileAsupprimer);
				pile1=new PileTuile(1);
				for(int indicePile1=1;indicePile1<=11;indicePile1++){
					int indice1=(int)(Math.random()*(toutesLesTuiles.size()-0));
					pile1.getTuiles().add(toutesLesTuiles.get(indice1));
					toutesLesTuiles.remove(indice1);
					
				} this.liste_piles.add(pile1);
				pile2=new PileTuile(2);
				for(int indicePile2=1;indicePile2<=11;indicePile2++){
					int indice2=(int)(Math.random()*(toutesLesTuiles.size()-0));
					pile2.getTuiles().add(toutesLesTuiles.get(indice2));
					toutesLesTuiles.remove(indice2);
				}this.liste_piles.add(pile2);
				
				pile3=new PileTuile(3);
				for(int indicePile3=1;indicePile3<=11;indicePile3++){
					int indice3=(int)(Math.random()*(toutesLesTuiles.size()-0));
					pile3.getTuiles().add(toutesLesTuiles.get(indice3));
					toutesLesTuiles.remove(indice3);
				}this.liste_piles.add(pile3);
				
				pile4=new PileTuile(4);
				pile4.getTuiles().addAll(toutesLesTuiles);
				this.liste_piles.add(pile4);
				
				int constructeur=(int)(Math.random()*(liste_joueurs.size()-0));
				liste_joueurs.get(constructeur).devenirConstructeur();
				System.out.println("Le joueur "+liste_joueurs.get(constructeur).getNomJoueur()+" est constructeur de canal");
				//INIT NB DE TOURS
				this.setMaxTour(11);
				
				//RORGANISER l'ordre de passage
				this.resetOrdre(liste_joueurs.get(constructeur).getIdJoueur());
				
				}
		
	this.phaseSuivante();
	
	}


	public void phase1() throws RemoteException {
		System.out.println("Tour "+this.getTour());
		System.out.println("Phase 1");
		//returner les tuiles
		for(int i=0;i<this.liste_piles.size();i++){
			this.liste_piles.get(i).getTuiles().get(0).setVisible(true);
		}
		
		//classer les joueurs par ordre
		//faire une mise un a un 
			//passer=mmise a zero
			//ne miser differement.
		
		
		
		
		// TODO Auto-generated method stub
		this.phaseSuivante();
		
	}

	 
	public void phase2() throws RemoteException {
		System.out.println("Phase 2");
		//plus petite mise... deviens constructeur de canal
		//test si plusieur passage
		//donner la figurine
		// TODO Auto-generated method stub
		this.phaseSuivante();
	}

	 
	public void phase3() throws RemoteException {
		System.out.println("Phase 3");
		// TODO Auto-generated method stub
		this.phaseSuivante();
		//comparer les mise et payer
		//ordre de croissant:
			//payer
			//choisir ?
			//place la tuile
			//placer les travailleurs agricole
				//tout pour les payé
				//un en moins pour les passer
			//cas 3 joueurs
	}

	 
	public void phase4() throws RemoteException {
		System.out.println("Phase 4");
		// TODO Auto-generated method stub
		this.phaseSuivante();
	}

	 
	public void phase5() throws RemoteException {
		System.out.println("Phase 5: Irrigation Complémentaire");
		// TODO Auto-generated method stub
		this.phaseSuivante();
	}

	 
	public void phase6() throws RemoteException {
		System.out.println("Phase 6: Secheresse");
		// TODO Auto-generated method stub
		this.phaseSuivante();
	}

	 
	public void phase7() throws RemoteException {
		System.out.println("Phase 7: Revenus");
		// TODO Auto-generated method stub
		if(this.getTour()==this.getMaxTour()){
			//Fin du Jeu
			//Compter les points
			for(int i=0;i<liste_joueurs.size();i++){
				System.out.println("Le joueur "+liste_joueurs.get(i).getNomJoueur()+" a "+this.liste_joueurs.get(i).getCagnotte()+" escudos dans sa cagnotte");
			}
		}
		else{
			//dont de la Banque
			for(int i=0;i<liste_joueurs.size();i++){
				this.liste_joueurs.get(i).setCagnotte(this.liste_joueurs.get(i).getCagnotte()+3);
			}
			this.tourSuivant();
		}
	}
	
	
}
