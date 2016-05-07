package Interface;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import Classes.*;

public class Partie extends UnicastRemoteObject implements PartieInterface{

	private int idPartie;
	private int tour;
	private int phase;
	private Plateau plateau;
	private ArrayList<PartieInterface> liste_interface=null;
	private ArrayList<Joueur> liste_joueurs=null;
	private ArrayList<String> couleurs = new ArrayList<String>();
	private boolean start;
	private int max_tour;
	private ArrayList<PileTuile> liste_piles=null;
	private ArrayList<ProposerMise> encheres_courantes;
	private ArrayList<PropositionSoudoiement> soudoiments;
	
/***************************************************************************
 * *******************************CONSTRUCTOR*******************************
 ***************************************************************************/

	public Partie() throws RemoteException, UnknownHostException {
		this.idPartie=(int)(Math.random()*(1000000000-0));
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

		this.liste_piles=new ArrayList<PileTuile>();
		this.encheres_courantes=new ArrayList<ProposerMise>();
	}

	public Partie(int idPartie, int tour, int phase, Plateau plateau, 
			ArrayList<Joueur> liste_joueurs, boolean start, int max_tour,
			ArrayList<PileTuile> liste_piles, ArrayList<ProposerMise> encheres_courantes,
			ArrayList<PropositionSoudoiement> soudoiments) throws RemoteException {
		this.idPartie = idPartie;
		this.tour = tour;
		this.phase = phase;
		this.plateau = plateau;
		this.liste_joueurs = liste_joueurs;
		this.couleurs.add("Noir");
		this.couleurs.add("Gris");
		this.couleurs.add("Violet");
		this.couleurs.add("Beige");
		this.couleurs.add("Rouge");
		this.start = start;
		this.max_tour = max_tour;
		this.liste_piles = liste_piles;
		this.encheres_courantes = encheres_courantes;
		this.soudoiments = soudoiments;
	}

	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/

	//************************************GETTER************************************
	public ArrayList<PileTuile> getListe_piles()throws RemoteException {
		return liste_piles;
	}
	public int getTour() throws RemoteException{
		return this.tour;
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
	
	public boolean getStart() throws RemoteException{
		return this.start;
	}
	public int getPhase() throws RemoteException{
		return this.phase;
	}
	public int getMaxTour() throws RemoteException{
		return this.max_tour;
	}
	
	public ArrayList<ProposerMise> getEncheresCourantes() throws RemoteException{
		return encheres_courantes;
	}
	
	public ArrayList<PropositionSoudoiement> getSoudoiments() {
		return soudoiments;
	}
	public int getIdPartie() {
		return idPartie;
	}

	public Plateau getPlateau()throws RemoteException {
		return plateau;
	}

	public ArrayList<PartieInterface> getListe_interface() {
		return liste_interface;
	}

	public ArrayList<Joueur> getListe_joueurs() {
		return liste_joueurs;
	}

	public ArrayList<String> getCouleurs() {
		return couleurs;
	}

	public ArrayList<ProposerMise> getEncheres_courantes() {
		return encheres_courantes;
	}

	public void addEnchere(ProposerMise i) {
		this.encheres_courantes.add(i);
	}
	
	/**
	 * Permet de recuperer le constructeur de canal de la Phase en cours
	 * @return le constructeur de canal
	 */
	public Joueur getConstructeur() throws RemoteException{
		for(int i=0;i<this.liste_joueurs.size();i++){
			if(this.liste_joueurs.get(i).getConstructeur())
				return this.liste_joueurs.get(i);
		}return null;
	}
	
	//************************************SETTER************************************
	public void setListe_piles(ArrayList<PileTuile> liste_piles) {
		this.liste_piles = liste_piles;
		
	}
	public void setMaxTour(int max_tour) throws RemoteException{
		this.max_tour=max_tour;
	}
	//************************************ADDER************************************
	
		public void addJoueur(Joueur j) throws RemoteException{
		this.liste_joueurs.add(j);
	}
		
	/**
	 * 
	 */
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
			System.out.println("Dï¿½jï¿½ 5 joueurs connectï¿½s dsl");
		}
	}
	

	/**
	 * Permet de Lancer la partie a partir de 3 joueurs min.
	 */
	public void lancerLaPartie() throws RemoteException{
		if(this.liste_joueurs.size()>=3|| this.liste_interface.size()>=3){
			this.start=true;
		}
	}
	/**
	 * Permet de quitter/d'arreter la partie.
	 */
	public void quitterPartie() throws RemoteException{
		this.start=false;
		//ici sauvegarde XML ou JSON
	}
	
	/**
	 *  Permet de passer au tours suivant et de lancer la prochaine phase, ici 1
	 */
	public void tourSuivant() throws RemoteException{
		this.tour++;
		this.phase=1;
		this.jouerPhase();
	}
	/**
	 * Permet de passer a la phase suivante et de la lancer
	 */
	 
	public void phaseSuivante() throws RemoteException{
		this.phase++;
	}
	
	public boolean verifEnchere(int montant)throws RemoteException{
		for(int i=0;i<encheres_courantes.size();i++)
		{
			if(encheres_courantes.get(i).getMontant()==montant)
				return false;
		}
		return true;
	}
	
	/*************************************************************************
	 * ***************************PHASES****************************************
	 * ***********************************************************************
	 */
	 
	  /**
	   * Permer de jouer automatiquement les phases les une pares les autres
	   */
	public void jouerPhase() throws RemoteException{ 
		switch (this.phase){
		case 2:
			this.phase2();
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
			
		case 8:
			this.phase8();
			break;
		case 0: //INITIALISATION, une seule fois
			this.phase0();
			break;
		default:
			System.out.println("Erreur .....");
		}
	}
	
	/**
	 * Reorganiser l'ordre de jeu en fonction de id du constructeur
	 * @param id id du constructeur de canal
	 */
	public void resetOrdre(int id){
		
		this.liste_joueurs.get(id-1).setRang(5);
		int rang=0;
		while(rang<liste_joueurs.size()){
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
	
	/**
	 * PHASE 0 : INITIALISATION DU JEU
	 * 1)Initialiser le tableau
	 * 	a)initialiser les parcelles
	 * 	b)initialiser les fossï¿½es
	 * 2)Initialiser les piles
	 * 	a)Initialiser les tuiles
	 * 	b)distribuer les tuiles
	 * 3)Initialiser le nombre de Tours
	 * 4)Choisisir le constucteur de canal
	 * 5)Dï¿½finir le nouvel Ordre de jeu
	 * Phase suivante
	 */
	public void phase0() throws RemoteException{ 
		//On crï¿½e le tableau de la partie
		int x=(int)(Math.random()*(3-0));
		int y=(int)(Math.random()*(3-0));
		plateau=new Plateau(1,5,6);
		System.out.println("CrÃ©ation Tableau");
		plateau.initliste_parcelles();// tableau de parcelle 8X6
		System.out.println("Initialisation parcelles");
		this.plateau.initfosses();//tableau de fosses 16 premier verticaux, 16 d'apres horizontaux
		System.out.println("Initialisation fossÃ©s");
		
		//initialisation de la Banque
		//this.banque=new Banque();
		//On gï¿½re les tuiles de plantations
		
			//CREATION DES TUILES
		ArrayList<TuilePlantation> toutesLesTuiles=new ArrayList<TuilePlantation>();
		for(int id1=1 ;id1<=5; id1++){
			toutesLesTuiles.add(new TuilePlantation(id1,"piment",1,1));
		}
		for(int id11=6 ;id11<=9; id11++){
			toutesLesTuiles.add(new TuilePlantation(id11,"piment",2,1));
		}
		for(int id2=10 ;id2<=14; id2++){
			toutesLesTuiles.add(new TuilePlantation(id2,"banane",1,1));
		}
		for(int id21=15 ;id21<=18; id21++){
			toutesLesTuiles.add(new TuilePlantation(id21,"banane",2,1));
		}
		for(int id3=19 ;id3<=23; id3++){
			toutesLesTuiles.add(new TuilePlantation(id3,"patate",1,1));
		}
		for(int id31=24 ;id31<=27; id31++){
			toutesLesTuiles.add(new TuilePlantation(id31,"patate",2,1));
		}
		for(int id4=28 ;id4<=33; id4++){
			toutesLesTuiles.add(new TuilePlantation(id4,"haricot",1,1));
		}
		for(int id41=34 ;id41<=36; id41++){
			toutesLesTuiles.add(new TuilePlantation(id41,"haricot",2,1));
		}
		for(int id5=37 ;id5<=41; id5++){
			toutesLesTuiles.add(new TuilePlantation(id5,"canne",1,1));
		}
		for(int id51=42 ;id51<=45; id51++){
			toutesLesTuiles.add(new TuilePlantation(id51,"canne",2,1));
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
				liste_joueurs.get(constructeur).setEst_constructeurdecanal(true);
				System.out.println("Le joueur "+liste_joueurs.get(constructeur).getNom_joueur()+" est constructeur de canal");
	
				//RORGANISER l'ordre de passage par rapport au constructeur de canal
				this.resetOrdre(liste_joueurs.get(constructeur).getId_joueur());
				
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
				
				//Choisir en Random le constructeur de Canal
				int constructeur=(int)(Math.random()*(liste_joueurs.size()-0));
				liste_joueurs.get(constructeur).setEst_constructeurdecanal(true);
				System.out.println("Le joueur "+liste_joueurs.get(constructeur).getNom_joueur()+" est constructeur de canal");
	
				
				//RORGANISER l'ordre de passage par rapport au constructeur de canal
				this.resetOrdre(liste_joueurs.get(constructeur).getId_joueur());
				
				}
	phaseSuivante();
	}
/**
 * Phase 1: Mise aux enchï¿½res des tuiles de plantation
 * 1)Retourner les Tuiles
 * 2)Proposer les Encheress
 * Phase suivante
 */
	
	public void miser(Joueur j, int montant)throws RemoteException{
		this.addEnchere(j.proposerEnchere(j.getRang(),montant));
		System.out.println(j.getNom_joueur()+" a enchéri "+montant);
	}
	
	public void passerMise(Joueur j) throws RemoteException{
		this.addEnchere(j.passer(j.getRang()));
		System.out.println(j.getNom_joueur()+"à passé");
	}


	public void setEncheres_courantes(ArrayList<ProposerMise> encheres_courantes) {
		this.encheres_courantes = encheres_courantes;
	}

	/**
	 * Phase 2:Changement du constructeur de canal"
	 * a)Detroner l'ancien constructeur
	 * b)Classer les encheres
	 * c)Trouver le nouveau constructeur
	 * d)Phase suivante
	 */
	public void phase2() throws RemoteException {
		
		//RETIRER LA FIGURINE DE L'ANCIEN CONSTRUCTEUR
		this.liste_joueurs.get(this.getConstructeur().getRang()-1).estPlusConstructeur();
		
		System.out.println("==============\nPhase 2: Changement du constructeur de canal\n===============");
		
		//TRIER TABLEAU DES ENCHERES en fonction du l'ENCHERE/ et Joueurs
		Collections.sort(this.encheres_courantes, ProposerMise.Comparators.MONTANT);
		
		//Redefinir le constructeur de canal
		int idcons=this.encheres_courantes.get(0).getJoueur().getRang();
		this.liste_joueurs.get(idcons-1).setEst_constructeurdecanal(true);
		System.out.println("Le joueur "+this.getConstructeur().getNom_joueur()+" est le nouveau constructeur de canal");	
	}

/**Phase 4 */
	
	
	public ArrayList<Joueur> definirOrdre(){
		//on met tt les rang Ã  0
		for (int i =0; i< this.getListe_joueurs().size();i++){
			this.getListe_joueurs().get(i).setRang(0);
		}
		//on determine le joueur Ã  gauche du constructeur en lui donnant le rang 1
		
		for(int i =0; i< this.getListe_joueurs().size();i++){
			if (this.getListe_joueurs().get(i).getEst_constructeurdecanal()==true){
				if(i>0){
					this.getListe_joueurs().get(i-1).setRang(1);	
				}
				else{
					this.getListe_joueurs().get(this.getListe_joueurs().size()-1).setRang(1);
				}
				break;
			}	
		}
		
		int taille = this.getListe_joueurs().size();
		for (int i = 0; i<taille;i++){
			if (this.getListe_joueurs().get(i).getRang()==1){
				if (i==(taille-5)){ //*---- = 0
					System.out.println("haha5");
					int r=2;
					for (int j=i+1;j<taille;j++){
						if(this.getListe_joueurs().get(j).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(j).setRang(-1);
						}
						else{
							this.getListe_joueurs().get(j).setRang(r);
							r++;
						}
					}
				}
				else if (i==(taille-1)){ //dernier joueur de la liste ----*
					System.out.println("haha1");
					int r =2;
					for (int j=0;j<i;j++){
						if(this.getListe_joueurs().get(j).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(j).setRang(-1);
						}
						else{
							this.getListe_joueurs().get(j).setRang(r);
							r++;
						}
					}
				}
				else if (i==(taille-2)){ //avant dernier ---*-
					System.out.println("haha2");
					int r=2;
					if (this.getListe_joueurs().get(i+1).getEst_constructeurdecanal()==false){
						this.getListe_joueurs().get(i+1).setRang(r);
						r++;
					}
					else{
						this.getListe_joueurs().get(i+1).setRang(-1);
					}
					for (int j=0;j<i;j++){
					
						if(this.getListe_joueurs().get(j).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(j).setRang(-1);
						}
						else{
							this.getListe_joueurs().get(j).setRang(r);
							r++;
						}
					}
					
				}
				else if (i==(taille-3)){//--*--
					System.out.println("haha3");
					int r = 2;
					for (int k = i+1; k< taille ; k++){
						if(this.getListe_joueurs().get(k).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(k).setRang(-1);
						}
						else{
							this.getListe_joueurs().get(k).setRang(r);
							r++;
						}
					}
					for (int j=0;j<i;j++){
						if(this.getListe_joueurs().get(j).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(j).setRang(-1);
						}
						else{
							this.getListe_joueurs().get(j).setRang(r);
							r++;
						}
					}
				}
				
				else if (i==(taille-4)){ //-*---
					System.out.println("haha4");
					int r = 2;
					for (int k = i+1; k<taille ; k++){
						if(this.getListe_joueurs().get(k).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(k).setRang(-1);
						}
						else{
							this.getListe_joueurs().get(k).setRang(r);
							r++;
						}
						
					}
					for (int j=0;j<i;j++){
						if(this.getListe_joueurs().get(j).getEst_constructeurdecanal()==true){
							this.getListe_joueurs().get(j).setRang(-1);
						}
						else{
						this.getListe_joueurs().get(j).setRang(r);
						r++;
						}
					}
				}
				break;
			}
		}
	
		//array des soudoyeurs dans l'ordre de passage
		ArrayList<Joueur> soudoyeurs = new ArrayList<Joueur>();
		int j=1;
		while(soudoyeurs.size()<this.getListe_joueurs().size()-1){
			for( int i=0; i< this.getListe_joueurs().size();i++){
				if(this.getListe_joueurs().get(i).getRang()==j){
					soudoyeurs.add(this.getListe_joueurs().get(i));
					j++;
				}	
			}
		}
		return soudoyeurs;
	}
	
	public int soudoyerPasser(){
		
		//passer ou soudoyer :
			boolean choisi =false;
			Scanner sc = new Scanner(System.in);
			System.out.println("C'est à  ton tour de jouer ! Que veux tu faire? \n 1 = soudoyer \n 2 = passer ");
			int res = sc.nextInt();
			if (res==1 ||res==2){
				choisi=true;
			}
			while(choisi==false){
				sc = new Scanner(System.in);
				System.out.println("Erreur.Réessaye. Que veux tu faire? \n 1 = soudoyer \n 2 = passer ");
				res = sc.nextInt();
				System.out.println(res);
				if (res==1 || res==2){
					choisi=true;
				}
			}
			return res;
	}
	
	
	//Sortir les fausés proches de la source : on prend les coordonnées faussés horisontaux pour la source
	
	public ArrayList<Fosse> procheSource(){
		ArrayList<Fosse> fProchesSource = new ArrayList<Fosse>();
		int x = this.plateau.getSourceX();
		int y = this.plateau.getSourceY();
		fProchesSource.add(plateau.getFosse(x,y,"H"));
		fProchesSource.add(plateau.getFosse(x,y-1,"H"));
		fProchesSource.add(plateau.getFosse(x-1,y+1,"V"));
		fProchesSource.add(plateau.getFosse(x,y+1,"V"));
		return fProchesSource;
	}
	
	public void phase4() throws RemoteException {
		System.out.println("Phase 4");
	
	//1st STEP : definir l'ordre des joueurs : 
		ArrayList<Joueur> soudoyeurs = this.definirOrdre(); // Testée !
	
	//2ND STEP : faire jouer chaque joueur Ã  son tour : soudoiement/ passer / soutenir soudoiement
		int jouer = soudoyerPasser();
		
		for (Joueur gamer : soudoyeurs){
			if(jouer==2){
				System.out.println("Tu passes ton tour ? Ok, au joueur suivant !");
			}
			else if(jouer==1){				
				Fosse f = null;
				
				boolean irrigué =true; //le faussÃ© est dÃ©jÃ  irriguÃ© !
				boolean irriguable = false; // le faussÃ© n'est pas collÃ© Ã  la source ni Ã  un canal du réseau
				while(irrigué==true || irriguable==false){			
					//demander les coordonnÃ©es du faussÃ© demandÃ©
					while(f==null){
						Scanner sc2 = new Scanner(System.in);
						System.out.println("Entre la coordonnee y du fausse que tu veux irriguer");
						int x =sc2.nextInt();
						Scanner sc3 = new Scanner(System.in);
						System.out.println("Entre la coordonnee x du fausse que tu veux irriguer");
						int y =sc3.nextInt();
						
						Scanner sc4 = new Scanner(System.in);
						System.out.println("Entre le sens du fausse que tu veux irriguer");
						String sens =sc4.nextLine();
						
						//faire en sorte qu'au premier tour on prend compte que la 
						for(Fosse faus : this.getPlateau().getListe_fosses()){
							if (faus.getCoorX()==y && faus.getCoorY()==x && faus.getSens().equals(sens)){
							//	System.out.println("Le Faussé a été trouvé." + f.getCoorX() + ","+f.getCoorY() + " "+f.getSens());
							f=faus;
							String sensString ="";
							if (f.getSens()=="V"){
								sensString="vertical";
							}
							else{
								sensString="horisontal";
							}
								System.out.println("Le Faussé  " +  sensString+ " ("+ f.getCoorX() +","+f.getCoorY()+") a été trouvé. Son id est : " + f.getIdFosse() );
								break;
							}
						}
					}
					if(f!=null){
						//verification : le fausse f est il bon?
						irrigué =f.getIrrigue();
						if (this.tour==1){
							ArrayList<Fosse> fProchesS = procheSource();
							for(Fosse fausse : fProchesS){ //comparer si le faussé du gars appartient à un des faussés collés à la source.
								if (fausse==f){
								//	System.out.println("Ce faussé est proche de la source");
									irriguable=true;
									break;
								}
							}
							if (irriguable==false){
							//	System.out.println("Ce faussé n'est pas proche de la source ! Il ne peut être irrigué");
							}
						}else{
							irriguable=this.plateau.getFossesIrrigueAdjacents(f);
						}
						break;
					}
					else{
						System.out.println("Oups petite erreur.");
					}
				}
				
				//chercher si proposition existe, si oui quel est son indice dans liste soudoiements --Verifier l'emplacement de ce if ci dessous!  
				if( irrigué ==  false && irriguable ==true){
					System.out.println("ce faussé n'est pas irrigué et est irriguable");
					boolean existe=false; // on cherche si une proposition de soudoiement existe.
					int indiceExistante=0; //indice de a proposition					
					
					if(this.soudoiments.size()>0){

						for(int i =0; i< this.soudoiments.size(); i++){
							if(this.soudoiments.get(i).getF().equals(f) && this.soudoiments.get(i).isEtat()==true ){
								existe=true;
								System.out.println("Boucle for : Un soudoiement existe");
								indiceExistante =i;
								break;
							}
							else{
								System.out.println("Boucle for : Aucun soudoiement n'existe");
								break;
							}
						}
					}
					
					if (existe==true){ // une proposition existe dÃ©jÃ , le joueur peut la soutenir.
						System.out.println("Soutenir un soudoiement existant : ");
						this.soudoiments.get(indiceExistante).getSupporters().add(gamer.soutenir(gamer.getId_joueur(),f));
					}
					else{//le joueur peut crÃ©er une proposition de soudoiement pour le faussÃ© f 
						System.out.println("Aucun soudoiement n'existe pour ce faussé. Vous pouvez en créer un");
						this.getSoudoiments().add(gamer.soudoyer(gamer.getId_joueur(), f));	//la proposition prend l'id du joueur?						
					}
				}else{
					if (tour==1){
						System.out.println("Ce faussé n'est pas proche de la source. Il n'est donc pas irriguable");	
					}
					else{
						System.out.println("Ce faussé n'est pas proche du réseaux de canaux. Il n'est donc pas irriguable");
					}
				}
			}
		}
		
		//tout les joueurs ont soudoyer ou passer leurs tours: c'est au constructeur de dÃ©cider : 
		ArrayList<PropositionSoudoiement> propositions = new ArrayList<PropositionSoudoiement>();
		for (PropositionSoudoiement ps : this.soudoiments){
			if (ps.isEtat()==true){
				propositions.add(ps);
			}
		}
		for (Joueur jo : this.liste_joueurs){
			if (jo.getEst_constructeurdecanal()==true){
				jo.decider(propositions);
				break;
			}
		}
		
		//Fin phase 4 : on passe ttes les propositions de soudoiement Ã  Ã©tat passÃ© !
		for (PropositionSoudoiement ps : this.soudoiments){
			ps.setEtat(false);
		}
					
	  // PASSAGE A LA PHASE SUIVANTE
		this.phaseSuivante();
	}


	/**
	  * Phasre 5:
	  * En commençant par le joueur à gauche du constructeur de canal et en suivant le sens horaire :
	  * Chaque joueur qui possède encore un canal bleu peut choisir de le placer maintenant
	  * sur un double segment (de couleur sombre) non irrigué du plateau.
	  * La phase s’arrête immédiatement si un joueur place un canal bleu (en clair, on ne peut
	  * placer qu’un seul canal complémentaire par tour de jeu).
	  */
	public void phase5() throws RemoteException {
		System.out.println("==============\nPhase 5: Irrigation Complémentaire\n===============");
		Scanner c=new Scanner(System.in);
		boolean aPoser=false;
		int i =0;
		int x,y;
		String sens;
		//reorganisation du passage des joueurs
		int idc = this.getConstructeur().getId_joueur();
		this.resetOrdre(idc);
		
		while((aPoser==false)||(i < this.liste_joueurs.size())){
			
			if(this.liste_joueurs.get(i).getCanal_bleu()==true){
				System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" voulez-vous poser votre canal bleu ? 0 (Non) / 1 (Oui)");
				
				int choix=c.nextInt();
				while ((choix!=0)||(choix!=1))
				{
					System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" voulez-vous poser votre canal bleu ? 0 (Non) / 1 (Oui)");
					choix=c.nextInt();
				}
				if(choix==1){
					boolean estBienpose = false;
					while (estBienpose == false){
					
						//Coordonnée X
						System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Où voulez-vous poser votre canal bleu ? Saisissez la coordonnée x :");
						int coordx=c.nextInt();
						while ((coordx<0) || (coordx>4) )
						{
							System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez la coordonnée x :");

							coordx=c.nextInt();
						}
						
						//Coordonnée Y
						System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez la coordonnée y :");

						int coordy=c.nextInt();
						if(coordx==4){
							while ((coordy<0) || (coordy>2) )
							{
								System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez la coordonnée y :");

								coordy=c.nextInt();
							}
						}else{
							while ((coordy<0) || (coordy>3) )
							{
								System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez la coordonnée y :");

								coordy=c.nextInt();
							}
						}
						
						//Sens
						if(coordx==4){
							sens="V";
						}else if(coordy==3){
							sens="H";
						}else{
							System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez le sens du canal :");

							sens=c.nextLine(); 
							while ((sens!="V") || (sens!="H") || (sens!="v") || (sens!="h") )
							{
								System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez le sens du canal : ( V : vertical / H : horizontal");

								 sens=c.nextLine();
							}
							if(sens.equals("v")){
								sens="V";
							}else{
								sens="H";
							}
						}
						
						Fosse canal = this.plateau.getFosse(coordx,coordy,sens);
						if (canal.getIrrigue()!=true){
							if(this.plateau.getFossesIrrigueAdjacents(canal)){
								estBienpose=true;
								aPoser=true;
								this.plateau.getFosse(coordx,coordy,sens).setIrrigue(true);
								System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Votre canal a été posé.");
							}else{
								System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Votre canal ne peux pas être posé car il n'est pas relier à un autre canal.");
							}
						}else{
							System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Ce fossé est déjà irrigué.");	
						}
					}
				}else{
						i++;
				}
				
			}
		}
		
		if(this.getTour()==this.getMaxTour()){
			this.phase=8;
			jouerPhase();
		}
	}

	 /**
	  * Phasre 6:
	  * Pour chaque tuile de plantation qui n’est pas adjacente à un canal bleu :
	  * Si la tuile est couverte d’au moins un travailleur agricole, retirer un travailleur
	  * agricole de la tuile.
	  * Si la tuile n’est couverte d’aucun travailleur agricole, retourner la tuile côté désert.
	  */
	public ArrayList<TuilePlantation> phase6() throws RemoteException {
		ArrayList<TuilePlantation> parcellearetourner=new ArrayList<TuilePlantation>();
		System.out.println("==============\nPhase 6: Secheresse\n===============");
		for(Joueur j:this.liste_joueurs){
			System.out.println("Joueur: "+j.getNom_joueur()+" rang "+ j.getRang()+"");
			for(TuilePlantation t:j.getTuilesjoueur()){
			
					 ArrayList<Fosse> fosses=this.plateau.getFossesAdjacents(this.plateau.get(t.getSourceX(), t.getSourceY()));
					 System.out.println("la tuile "+fosses.get(0).getSens()+" est bien arrosée");
					 boolean res=false;
					 	if(fosses.size()==0){
					 		res=false;
					 	}
					 	else
					 	for(int i=0;i<2;i++){
					 		if(fosses.get(i).getIrrigue()==true){
					 			System.out.println("la tuile "+t.getPlante()+" est bien arrosée");
					 			res=true;
					 			break;
					 		}	
					 	}
					 
					 if(res==false && 1<=t.getTag_presents()){
						t.setTag_presents(t.getTag_presents()-1);
						System.out.println("Un TAG en moins sur la tuile "+t.getPlante());
					 }
					 else if(res==false && t.getTag_presents()==0){
						//Cas Palmier
						if(this.plateau.get(t.getSourceX(), t.getSourceX()).getPalmier()==false){
							t.setDesert(true);
							t.setVisible(false);
							parcellearetourner.add(t);
							System.out.println("La tuile "+t.getPlante()+" est maintenant un dessert");
							}
						else
							this.plateau.get(t.getSourceX(), t.getSourceX()).setPalmier(false);
					 }
				 
				 }
				
			}
		return parcellearetourner;
	}

	 
	public void phase7() throws RemoteException {
		
		System.out.println("==============\nPhase 7: Revenue\n===============");
		
			for(int i=0;i<liste_joueurs.size();i++){
				this.liste_joueurs.get(i).setCagnotte(this.liste_joueurs.get(i).getCagnotte()+3);
			}
		}

	
	public void phase8() throws RemoteException {
		
		System.out.println("==============\nPhase 8: Phase Final\n===============");
		
		int tag;
		int score=0;
		Joueur gagnant=null;
				for(Joueur j:this.liste_joueurs){
					for(int i=0;i<j.getTuilesjoueur().size();i++){
						score=score+j.getTuilesjoueur().get(i).getTag_presents()*j.getTuilesjoueur().get(i).getPrix();
					}
					
					j.setScore(score+j.getCagnotte());
					System.out.println("Le joueur "+j.getNom_joueur()+" à un score de: "+j.getScore());
					}
				gagnant=liste_joueurs.get(0);
				for(int i=1;i<liste_joueurs.size();i++){
					if(gagnant.getScore()<liste_joueurs.get(i).getScore())
						gagnant=liste_joueurs.get(i);
					}
				System.out.println("Le gagnant est  "+gagnant.getNom_joueur()+" avec un score de: "+gagnant.getScore());
			this.quitterPartie();
			
		}


}
