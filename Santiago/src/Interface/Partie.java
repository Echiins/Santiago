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
	private Banque banque;
	private ArrayList<PartieInterface> liste_interface=null;
	private ArrayList<Joueur> liste_joueurs=null;
	private ArrayList<String> couleurs = new ArrayList<String>();
	private boolean start;
	private int max_tour;
	private ArrayList<PileTuile> liste_piles=null;
	private ArrayList<ProposerMise> encheres_courantes;
	private int score;
	
/***************************************************************************
 * *******************************CONSTRUCTOR*******************************
 ***************************************************************************/

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

		this.liste_piles=new ArrayList<PileTuile>();
		this.encheres_courantes=new ArrayList<ProposerMise>();
	}
	
	public Partie(int idPartie, int tour, int phase,Plateau plateau,Banque banque,
			 ArrayList<Joueur> liste_joueurs,boolean start, int max_tour, ArrayList<PileTuile> liste_piles, ArrayList<ProposerMise> encheres_courantes, int score)
				throws RemoteException {
			this.idPartie = idPartie;
			this.tour = tour;
			this.phase = phase;
			this.plateau=plateau;
			this.banque = banque;
			this.liste_joueurs = liste_joueurs;
			this.couleurs = couleurs;
			this.start = start;
			this.max_tour = max_tour;
			this.liste_piles = liste_piles;
			this.encheres_courantes = encheres_courantes;
			this.setScore(score);
		}

	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/

	//************************************GETTER************************************
	public ArrayList<PileTuile> getListe_piles() {
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
	public int getMax_tour() throws RemoteException{
		return this.max_tour;
	}
	
	public ArrayList<ProposerMise> getEncheresCourantes() {
		return encheres_courantes;
	}
	
	
	public int getIdPartie() {
		return idPartie;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public Banque getBanque() {
		return banque;
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
	public Joueur getConstructeur(){
		for(int i=0;i<this.liste_joueurs.size();i++){
			if(this.liste_joueurs.get(i).getConstructeur())
				return this.liste_joueurs.get(i);
		}return null;
	}
	
	//************************************SETTER************************************
	public void setListe_piles(ArrayList<PileTuile> liste_piles) {
		this.liste_piles = liste_piles;
		
	}
	public void setMax_tour(int max_tour) throws RemoteException{
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
			System.out.println("D�j� 5 joueurs connect�s dsl");
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
		this.jouerPhase();
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
	 * 	b)initialiser les foss�es
	 * 2)Initialiser les piles
	 * 	a)Initialiser les tuiles
	 * 	b)distribuer les tuiles
	 * 3)Initialiser le nombre de Tours
	 * 4)Choisisir le constucteur de canal
	 * 5)D�finir le nouvel Ordre de jeu
	 * Phase suivante
	 */
	public void phase0() throws RemoteException{ 
		//On cr�e le tableau de la partie
		plateau=new Plateau(1,5,6);
		System.out.println("Création Tableau");
		plateau.initliste_parcelles();// tableau de parcelle 8X6
		System.out.println("Initialisation parcelles");
		this.plateau.initfosses();//tableau de fosses 16 premier verticaux, 16 d'apres horizontaux
		System.out.println("Initialisation fossés");
		
		//initialisation de la Banque
		//this.banque=new Banque();
		//On g�re les tuiles de plantations
		
			//CREATION DES TUILES
		ArrayList<TuilePlantation> toutesLesTuiles=new ArrayList<TuilePlantation>();
		for(int id1=1 ;id1<=9; id1++){
			toutesLesTuiles.add(new TuilePlantation(id1,"piment",1));
		}
		for(int id2=10 ;id2<=18; id2++){
			toutesLesTuiles.add(new TuilePlantation(id2,"banane",1));
		}
		for(int id3=19 ;id3<=27; id3++){
			toutesLesTuiles.add(new TuilePlantation(id3,"patate",1));
		}
		for(int id4=28 ;id4<=36; id4++){
			toutesLesTuiles.add(new TuilePlantation(id4,"haricot",1));
		}
		for(int id5=37 ;id5<=45; id5++){
			toutesLesTuiles.add(new TuilePlantation(id5,"canne",1));
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
		
	this.phaseSuivante();
	
	}
/**
 * Phase 1: Mise aux ench�res des tuiles de plantation
 * 1)Retourner les Tuiles
 * 2)Proposer les Encheress
 * Phase suivante
 */
	public void phase1() throws RemoteException {
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-\nTour \n=-=-=-=-=-=-=-=-=-="+this.getTour());
		System.out.println("===============\nPhase 1: Mise aux ench�res des tuiles de plantation \n===============");
		
		//RENDRE LES PREMIERES TUILES DE CHAQUE PILES VISIBLES:
		for(int i=0;i<this.liste_piles.size();i++){
			this.liste_piles.get(i).getTuiles().get(0).setVisible(true);
		}
		
		//TRIER TABLEAU DE JOUEUR EN FONCTION DU RANG
		Collections.sort(this.liste_joueurs, Joueur.Comparators.RANG);
		
		for(Joueur j:this.liste_joueurs){
			System.out.println("Joueur: "+j.getNom_joueur()+" rang "+ j.getRang()+" voulez-vous Passer ou Proposer une Enchere 0(Encherir)/1(Passer)");
			Scanner c=new Scanner(System.in);
			int choix=c.nextInt();
			//Choix=Boolean lorqu'on fera l'interface
			/*while (!(choix==0)||!(choix==1))
			{
				System.out.println("Entrer (Encherir)/(Passer)");
				c.next();
				choix=c.nextInt();
			}*/
			
			switch(choix)
			{
			case 0:
				System.out.println("ENCHERIR:");
				int montant=0;
				boolean res=false;
				System.out.println("Le Montant de votre ench�re:");

				while(res==false){
					//TODO VERIFIER QUE LE JOUEUR A ASSEZ D'ARGENT DANS SA CAGNOTTE POUR ENCHERIR
					Scanner m=new Scanner(System.in);
					//Verifier les chiffres
					while (!m.hasNextInt()){
					System.out.println("Entrer un chiffre");
					m.next();
					}
					
					montant=m.nextInt();
					if(0<encheres_courantes.size()){
						System.out.println("Vérification de l'enchere");
					
						if(montant==0){
							System.out.println("Passer par défaut");
							this.addEnchere(j.passer(j.getRang()));
							res=true;
						}
						else{
								if(verifEnchere(montant)==true){
									System.out.println("Enchere validée");
									res=true;
									this.addEnchere(j.proposerEnchere(j.getRang(),montant));
									}
								else{
									res=false;
									System.out.println("Cette enchere � d�ja �t� faite, faites en une autre:");
									}
							}			
					}
					else {
						if(montant==0){
							res=true;
							System.out.println("Passer par défaut");
							this.addEnchere(j.passer(j.getRang()));
						}
						else{
								res=true;
							this.addEnchere(j.proposerEnchere(j.getRang(),montant));
							}
						}
					
				}
				break;
				
			case 1:
				System.out.println("PASSER:");
				this.addEnchere(j.passer(j.getRang()));
				break;
			default:
				System.out.println("Erreur, Je ne comprend pas votre choix");
				break;
			}
		}
		this.phaseSuivante();
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
		this.phaseSuivante();
	}
	/**
	 * Phase 3:
	 * 1)Redefinir ordre de passage
	 * 2)Payer
	 * 
	 */
	 
	public void phase3() throws RemoteException {
		System.out.println("==============\nPhase 3: Changement du constructeur de canal\n===============");
		//redefinir l'ordre de piochage des cartes.
				for(int i=0;i<this.encheres_courantes.size();i++){
					
					for(int j=0;j<this.liste_joueurs.size();j++ ){
						if(this.encheres_courantes.get(i).getJoueur().getId_joueur()==liste_joueurs.get(j).getId_joueur()){
							this.liste_joueurs.get(j).setRang(i);
						}
					}
				}
				//definir le nouvel ordre de passage des des joueur
		Collections.sort(this.liste_joueurs, Joueur.Comparators.RANG);
			
		//CAS 3 joueurs
		if(liste_joueurs.size()==3)	{
			
		}
		//cas 4 5 Joueurs
		else{
			for(Joueur j:this.liste_joueurs){
				System.out.println("Joueur: "+j.getNom_joueur()+" rang "+ j.getRang()+" Quelle tuile voulez vous choisir?");
				//payer
				//choisir ?
				//place la tuile
				//placer les travailleurs agricole
					//tout pour les payé
					//un en moins pour les passer
				//cas 3 joueurs
			}
		}
		this.phaseSuivante();
		
	}

	 
	public void phase4() throws RemoteException {
		System.out.println("Phase 4");
		// TODO Auto-generated method stub
		this.phaseSuivante();
	}

	 /**
	  * Phasre 5:
	  * En commencant par le joueur a gauche du constructeur de canal et en suivant le sens horaire :
	  * Chaque joueur qui possede encore un canal bleu peut choisir de le placer maintenant
	  * sur un double segment (de couleur sombre) non irrigue du plateau.
	  * La phase s'arrete immediatement si un joueur place un canal bleu (en clair, on ne peut
	  * placer qu'un seul canal complementaire par tour de jeu).
	  */
	public void phase5() throws RemoteException {
		System.out.println("==============\nPhase 5: Irrigation Complémentaire\n===============");
		
		boolean aPoser=false;
		int i =0;
		
		while(aPoser==false && i < this.liste_joueurs.size()){
			
			if(this.liste_joueurs.get(i).getCanal_bleu()==true){
				System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" voulez-vous poser votre canal bleu ? 0 (Non) / 1 (Oui)");
				Scanner c=new Scanner(System.in);
				int choix=c.nextInt();
				while ((choix!=0)||(choix!=1))
				{
					System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" voulez-vous poser votre canal bleu ? 0 (Non) / 1 (Oui)");
					c=new Scanner(System.in);
					choix=c.nextInt();
				}
				if(choix==1){
					System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Où voulez-vous poser votre canal bleu ? Saisissez la coordonnée x :");
					c=new Scanner(System.in);
					int coordx=c.nextInt();
					while ((coordx<0) || (coordx>8) )
					{
						System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Où voulez-vous poser votre canal bleu ? Saisissez la coordonnée x :");
						c=new Scanner(System.in);
						coordx=c.nextInt();
					}
					
					System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez la coordonnée y :");
					 c=new Scanner(System.in);
					int coordy=c.nextInt();
					while ((coordy<0) || (coordy>6) )
					{
						System.out.println("Joueur: "+this.liste_joueurs.get(i).getNom_joueur()+" rang "+ this.liste_joueurs.get(i).getRang()+" Saisissez la coordonnée y :");
						c=new Scanner(System.in);
						coordy=c.nextInt();
					}
					//Poser canal Bleu
					aPoser=true;
				}else{
					i++;
				}
			}
		}
		// TODO Auto-generated method stub
		this.phaseSuivante();
		
		if(this.getTour()==this.getMax_tour()){
			//Fin du Jeu
			//Compter les points
			for(int j=0;j<liste_joueurs.size();j++){
				System.out.println("Le joueur "+liste_joueurs.get(j).getNom_joueur()+" a "+this.liste_joueurs.get(j).getCagnotte()+" escudos dans sa cagnotte");
			}
		}
	}

	 /**
	  * Phasre 6:
	  * Pour chaque tuile de plantation qui n’est pas adjacente à un canal bleu :
	  * Si la tuile est couverte d’au moins un travailleur agricole, retirer un travailleur
	  * agricole de la tuile.
	  * Si la tuile n’est couverte d’aucun travailleur agricole, retourner la tuile côté désert.
	  */
	public void phase6() throws RemoteException {
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
							System.out.println("La tuile "+t.getPlante()+" est maintenant un dessert");
							}
						else
							this.plateau.get(t.getSourceX(), t.getSourceX()).setPalmier(false);
					 }
				 
				 }
				
			}
		
		this.phaseSuivante();
	}

	 
	public void phase7() throws RemoteException {
		
		System.out.println("==============\nPhase 7: Revenue\n===============");
		
			for(int i=0;i<liste_joueurs.size();i++){
				this.liste_joueurs.get(i).setCagnotte(this.liste_joueurs.get(i).getCagnotte()+3);
			}
			this.tourSuivant();
		}

	@Override
	public int getMaxTour() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxTour(int maxTour) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
