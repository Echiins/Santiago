package Santiago.Tests.Classes;


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
	//INUTIL, il aura toujours son canal
	private int canal_perso;
	private boolean canal_bleu;
	private int cagnotte;
	private int nb_tag;
	private int rang;
	private boolean est_constructeurdecanal;
	private String couleur;
	private boolean montour;
	private List<TuilePlantation> tuiles_joueur;
	
	public Joueur(int id_joueur, String nom_joueur, String couleur, int rang){
		this.id_joueur=id_joueur;
		this.nom_joueur=nom_joueur;
		this.canal_perso=1;
		this.canal_bleu=true;
		this.cagnotte=10;
		this.nb_tag=22;
		this.rang=rang;
		this.est_constructeurdecanal=false;
		this.montour=false;
		this.couleur=couleur;
		this.tuiles_joueur=new ArrayList<TuilePlantation>();
	}
	

	public ProposerMise proposerEnchere(int id, int montant){
		
		ProposerMise mise=new ProposerMise(id,this,montant);
		return mise;
			//demander l'enchere ou passer, se fait via l'interface graphique
			//si le joueur passe : l'enchere est -1
			//on cr�er la mise et on retourne le montant
			//si il d�cide de passer le montant vaut -1
		}
	
	public ProposerMise passer(int id){
		ProposerMise mise=new ProposerMise(id,this);
		return mise;
	}

	public List<TuilePlantation> getTuilesjoueur() {
		return tuiles_joueur;
	}

	public void setTuilesjoueur(List<TuilePlantation> tuiles_joueur) {
		this.tuiles_joueur = tuiles_joueur;
	}

	public boolean getConstructeur(){
		return this.est_constructeurdecanal;
	}
	public void estPlusConstructeur(){
		this.est_constructeurdecanal=false;
	}

	public int getId_joueur() {
		return id_joueur;
	}

	public void setId_joueur(int id_joueur) {
		this.id_joueur = id_joueur;
	}

	public String getNom_joueur() {
		return nom_joueur;
	}

	public void setNom_joueur(String nom_joueur) {
		this.nom_joueur = nom_joueur;
	}

	public int getCanal_perso() {
		return canal_perso;
	}

	public void setCanal_perso(int canal_perso) {
		this.canal_perso = canal_perso;
	}

	public boolean isCanal_bleu() {
		return canal_bleu;
	}

	public void setCanal_bleu(boolean canal_bleu) {
		this.canal_bleu = canal_bleu;
	}

	public int getCagnotte() {
		return cagnotte;
	}

	public void setCagnotte(int cagnotte) {
		this.cagnotte = cagnotte;
	}

	public int getNb_tag() {
		return nb_tag;
	}

	public void setNb_tag(int nbtag) {
		this.nb_tag = nbtag;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public boolean Est_constructeurdecanal() {
		return est_constructeurdecanal;
	}

	public void setEst_constructeurdecanal(boolean estConstructeurdecanal) {
		this.est_constructeurdecanal = estConstructeurdecanal;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public void tirerTuile(TuilePlantation t){
		this.tuiles_joueur.add(t);
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

