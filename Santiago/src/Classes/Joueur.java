package Classes;


import static java.net.InetAddress.getLocalHost;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.*;

public class Joueur implements Comparable<Joueur> {
	
	private int id_joueur;
	private String nom_joueur;
	private String mdp;
	private int canal_perso;
	private boolean canal_bleu;
	private int cagnotte;
	private int nb_tag;
	private int rang;
	private boolean est_constructeurdecanal;
	private String couleur;
	private boolean montour;
	private List<TuilePlantation> tuiles_joueur;
	
	
	/***************************************************************************
	 * *******************************CONSTRUCTOR*******************************
	 ***************************************************************************/
	public Joueur(int id_joueur, String nom_joueur, String couleur, int rang){
		this.id_joueur=id_joueur;
		this.nom_joueur=nom_joueur;
		this.canal_perso=1;
		this.canal_bleu=true;
		this.cagnotte=10;
		this.nb_tag=22;
		this.rang=rang;
		this.est_constructeurdecanal=false;
		this.setMontour(false);
		this.couleur=couleur;
		this.tuiles_joueur=new ArrayList<TuilePlantation>();
	}
	
	public Joueur(int id_joueur, String nom_joueur, String mdp, String couleur, int rang){
		this.id_joueur=id_joueur;
		this.nom_joueur=nom_joueur;
		this.mdp=mdp;
		this.canal_perso=1;
		this.canal_bleu=true;
		this.cagnotte=10;
		this.nb_tag=22;
		this.rang=rang;
		this.est_constructeurdecanal=false;
		this.setMontour(false);
		this.couleur=couleur;
		this.tuiles_joueur=new ArrayList<TuilePlantation>();
	}
	
	public Joueur(int id_joueur, String nom_joueur,String couleur, String mdp,int rang,int canal_perso, boolean canal_bleu, int cagnotte, int nb_tag,
			 boolean est_constructeurdecanal,  boolean montour,
			List<TuilePlantation> tuiles_joueur) {
		this.id_joueur = id_joueur;
		this.nom_joueur = nom_joueur;
		this.mdp=mdp;
		this.canal_perso = canal_perso;
		this.canal_bleu = canal_bleu;
		this.cagnotte = cagnotte;
		this.nb_tag = nb_tag;
		this.rang = rang;
		this.est_constructeurdecanal = est_constructeurdecanal;
		this.couleur = couleur;
		this.setMontour(montour);
		this.tuiles_joueur = tuiles_joueur;
	}
	
	
	
	
	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/

	public ProposerMise proposerEnchere(int id, int montant){
		
		ProposerMise mise=new ProposerMise(id,this,montant);
		return mise;
		}
	
	public ProposerMise passer(int id){
		ProposerMise mise=new ProposerMise(id,this);
		return mise;
	}

	//************************************GETTER************************************
	public List<TuilePlantation> getTuilesjoueur() {
		return tuiles_joueur;
	}
	
	public int getId_joueur() {
		return id_joueur;
	}
	public boolean getConstructeur(){
			return this.est_constructeurdecanal;
		}
	public String getNom_joueur() {
		return nom_joueur;
	}
	public int getCanal_perso() {
		return canal_perso;
	}
	public boolean getCanal_bleu() {
		return canal_bleu;
	}
	public int getNb_tag() {
		return nb_tag;
	}
	public int getCagnotte() {
		return cagnotte;
	}

	public int getRang() {
		return rang;
	}
	public boolean getEst_constructeurdecanal() {
		return est_constructeurdecanal;
	}
	
	public String getCouleur() {
		return couleur;
	}
	public String getMdp(){
		return mdp;
	}
	
	//************************************SETTER************************************
	public void setTuilesjoueur(List<TuilePlantation> tuiles_joueur) {
		this.tuiles_joueur = tuiles_joueur;
	}
	
	public void setId_joueur(int id_joueur) {
		this.id_joueur = id_joueur;
	}
	public void setNom_joueur(String nom_joueur) {
		this.nom_joueur = nom_joueur;
	}
	public void setCanal_perso(int canal_perso) {
		this.canal_perso = canal_perso;
	}
	public void setCanal_bleu(boolean canal_bleu) {
		this.canal_bleu = canal_bleu;
	}

	public void setNb_tag(int nbtag) {
		this.nb_tag = nbtag;
	}
	public void setCagnotte(int cagnotte) {
		this.cagnotte = cagnotte;
	}
	public void setEst_constructeurdecanal(boolean estConstructeurdecanal) {
		this.est_constructeurdecanal = estConstructeurdecanal;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public boolean getMontour() {
		return montour;
	}

	public void setMontour(boolean montour) {
		this.montour = montour;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public void setMdp(String mdp){
		this.mdp=mdp;
	}
	//A FACTORISER
	public void estPlusConstructeur(){
		this.est_constructeurdecanal=false;
	}

	public void tirerTuile(TuilePlantation t){
		this.tuiles_joueur.add(t);
	}




/*** phase 4***/
	public void phase4(){
		//demander si passer ou soudoyer ou proposer de soutenir un soudoiement
		//si passer : passer au joueuer suivant
		//sinon
		// proposer les soudoiements des autres et proposer de :
		//soudoyer : demander fossé et montant et lancer méthode soudoyer
		//ou soutenir un soudoiement
		
	}
	
	//phase 4 soudoyer
	public PropositionSoudoiement soudoyer(int id, Fosse f){
	
		//demander montant
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le montant de ton pot de vin?");
		int m = sc.nextInt();	
		
		while(m<0){
			sc = new Scanner(System.in);
			System.out.println("Quel est le montant de ton pot de vin?");
			m = sc.nextInt();		
		}
		PropositionSoudoiement p = new PropositionSoudoiement(id,this,m,f);

		return p;
	}

	//fonction soutenir soudoiement!
	public SoutienSoudoiement soutenir(int id, Fosse f){
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le montant que vous voulez ajouter au pot de vin pour ce faussé?");
		int m = sc.nextInt();	
		
		while(m<0){
			sc = new Scanner(System.in);
			System.out.println("Quel est le montant de ton pot de vin?");
			m = sc.nextInt();		
		}
		SoutienSoudoiement sd = new SoutienSoudoiement(id,this,m,f);
		return sd;
	}
	
	public PropositionSoudoiement decider(ArrayList<PropositionSoudoiement> pss){
		String propositions = "Voici les pots de vin que les joueurs vous propose ! A vous d'en choisir un (en entrant son n°) ou de taper '-1' pour les refuser toutes";
		//
		for (PropositionSoudoiement ps:pss){
			int montantTotal = ps.montant;
			for(SoutienSoudoiement ss : ps.supporters){
				montantTotal = montantTotal+ ss.montant;
			}
			propositions= propositions + "\n pot de vin n°"+ps.idPS + "du montant de "+ montantTotal  +" pour un canal sur le faussé (" + ps.getF().getCoorX() +","+ps.getF().getCoorY()+")";
		}
		Scanner sc = new Scanner(System.in);
		System.out.println(propositions);
		int res = sc.nextInt();
		
		
		
		return null;
	}
	
/**********************COMPARATOR************************************/
	@Override
    public int compareTo(Joueur o) {
        return Comparators.RANG.compare(this, o);
    }

	 public static class Comparators {

		 public static final Comparator<Joueur> RANG = (Joueur o1, Joueur o2) -> Integer.compare(o1.getRang(), o2.getRang());
	        
	    }
}